package com.team7.model.entity.structure.staffedStructure;

import com.team7.model.Tile;
import com.team7.model.entity.Command;
import com.team7.model.entity.Worker;
import com.team7.model.entity.structure.Structure;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * All structures require a staff except Observation Tower
 */
public abstract class StaffedStructure extends Structure {
    private ArrayList<Worker> workerStaff = new ArrayList<>();
    private int allocatedFood;
    private boolean hasEnoughFood;

    public ArrayList<Worker> getWorkerStaff() {
        return workerStaff;
    }

    public void setWorkerStaff(ArrayList<Worker> workerStaff) {
        this.workerStaff = workerStaff;
    }


    public int getAllocatedFood() {
        return allocatedFood;
    }

    public boolean isHasEnoughFood() {
        return hasEnoughFood;
    }

    public void setHasEnoughFood(boolean hasEnoughFood) {
        this.hasEnoughFood = hasEnoughFood;
    }

    public void changeAllocatedFood(int quantity) {
        allocatedFood += quantity;
    }

    public int computeFoodUpkeep() {
        if(!checkConstructionComplete()){
            //food upkeep only necessary once workers are actively staffing the structure
            return 0;
        }
        int totalFoodUpkeep = workerStaff.size() * workerStaff.get(0).getFoodUpkeep();
        changeAllocatedFood(0- totalFoodUpkeep);
        if (allocatedFood >= totalFoodUpkeep) {
            //workers are fed!
            setHasEnoughFood(true);
        }
        else{
            setHasEnoughFood(false);
        }

        return allocatedFood;
    }

    public void influenceWorkersAccordingToFood(){
        if (!hasEnoughFood){
            for (Worker worker : workerStaff){
                worker.changeConstructionRate(-1);
                worker.setFed(false);       //isFed is used when using workers for specific productions
            }
        }
    }

    //iterate through all workers and return their current tile mapped with their harvest radius
    public HashMap<Tile, Integer> getHarvestableTilesByWorkers(){
        HashMap<Tile, Integer> harvestableTiles = new HashMap<>();
        for (Worker worker : workerStaff){
            if(!worker.isAssigned()){
                harvestableTiles.put(worker.getLocation(), worker.getHarvestRadius());
            }
        }

        return harvestableTiles;
    }

    public abstract void beginStructureFunction();

    @Override
    public void setAllocatedResources(String resource, int value) {
        switch(resource) {
            case "Ore":
                allocatedOre =value;
                break;
            case "Energy":
                allocatedEnergy = value;
                break;
            case "Nutrient":
                allocatedFood = value;
        }
    }


    @Override
    public void executeCommandQueue() {

        if(getTurnsFrozen() > 0) {
            subtractFrozenTurn();
            return;
        }

        if(getCommandFromQueue() == null)
            return;

        Command commandToExecute = getCommandFromQueue();
        String commandString = commandToExecute.getCommandString();

        switch ( commandString ) {

            case "DO_SOMETHING":
                // do something
                break;

            default:
                break;
        }

    }

    public void addWorkerToStaff(Worker worker){
        this.workerStaff.add(worker);
    }

}
