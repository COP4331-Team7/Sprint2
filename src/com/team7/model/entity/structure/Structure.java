package com.team7.model.entity.structure;

import com.team7.model.Player;
import com.team7.model.Tile;
import com.team7.model.entity.Command;
import com.team7.model.entity.CommandQueue;
import com.team7.model.entity.Entity;
import com.team7.model.entity.Worker;
import com.team7.model.entity.structure.staffedStructure.StaffedStructure;
import com.team7.model.entity.unit.Unit;

import java.util.ArrayList;

public abstract class Structure extends Entity {
    private CommandQueue commandQueue;
    private StructureStats stats;
    private String type;
    private boolean isPowered;

    private int turnsFrozen;
 
    private int energyUpkeep;   //requires Power from Player
    private int oreUpkeep;      //requires Metal from Player
    private int allocatedEnergy;
    private int allocatedOre;
    private int levelOfCompletion;  //range 0 to 100, incremented at a rate of +1 per worker per turn
    private boolean isSufficientlySupplied;
    private ArrayList<Worker> workerAssigned = new ArrayList<>();


    public abstract void applyTechnology(String techInstance, String technologyStat, int level);

    protected boolean checkConstructionComplete(){       //called at end of every turn
        if(levelOfCompletion >= 100){
            levelOfCompletion = 100;
            return true;
        }
        return false;
    }


    public int advanceConstruction() {    //returns how much food was necessary that turn

        int foodUpkeepDuringConstruction = 0;
        if (!checkConstructionComplete()) {
            //construction not complete
            for(Worker worker : getWorkerAssigned()){
                foodUpkeepDuringConstruction += 2;
                changeLevelOfCompletion(worker.getConstructionRate());  //increment construction according to number of workers
            }
            if(checkConstructionComplete()){
                if (this instanceof StaffedStructure){
                    ((StaffedStructure)this).setWorkerStaff(getWorkerAssigned());  //move workers from building to staff
                }
                setPowered(true);   //construction has finished at this turn
            }
        }
        return foodUpkeepDuringConstruction;
    }

    public void influenceStructureAccordingToSupply(){
        if (!isSufficientlySupplied){
            degradeStructurePerformance();
        }
    }

    private void degradeStructurePerformance() {
        stats.changeHealth(-10);
    }


    //called at the end of every turn for all Player structures
    public int computeOreUpkeep() {
        changeAllocatedOre(0-oreUpkeep);
        if (allocatedOre >= oreUpkeep){
            //structure is in good standing
            setSufficientlySupplied(true);
        }
        else {
            setSufficientlySupplied(false);
        }

        return allocatedOre;
    }

    public int computeEnergyUpkeep() {
        changeAllocatedEnergy(0-energyUpkeep);
        if (allocatedEnergy >= energyUpkeep) {
            //structure is in good standing
            setSufficientlySupplied(true);
        }
        else{
            setSufficientlySupplied(false);
        }

        return allocatedEnergy;
    }

    public void changeEnergyUpkeep(int delta){
        energyUpkeep += delta;
    }

    public void changeOreUpkeep(int delta){
        oreUpkeep += delta;
    }

    public void addStructureToCurrentTile(){
        getLocation().setStructure(this);
    }

    public void removeStructureFromCurrentTile(){
        getLocation().setStructure(null);
    }

    public void addWorkerToConstruction(Worker worker){
        workerAssigned.add(worker);
    }

    public StructureStats getStats() {
        return stats;
    }

    public void setStats(StructureStats stats) {
        this.stats = stats;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isPowered() {
        return isPowered;
    }

    public void setPowered(boolean powered) {
        isPowered = powered;
    }

    public int getTurnsFrozen() {
        return turnsFrozen;
    }

    public void setTurnsFrozen(int movesFrozen) {
        this.turnsFrozen = movesFrozen;
    }

    public int getEnergyUpkeep() {
        return energyUpkeep;
    }

    public void setEnergyUpkeep(int energyUpkeep) {
        this.energyUpkeep = energyUpkeep;
    }

    public int getOreUpkeep() {
        return oreUpkeep;
    }

    public void setOreUpkeep(int oreUpkeep) {
        this.oreUpkeep = oreUpkeep;
    }

    public int getAllocatedEnergy() {
        return allocatedEnergy;
    }

    public int getAllocatedOre() {
        return allocatedOre;
    }

    public int getLevelOfCompletion() {
        return levelOfCompletion;
    }

    public void setLevelOfCompletion(int levelOfCompletion) {
        this.levelOfCompletion = levelOfCompletion;
    }

    public void changeLevelOfCompletion(int delta){
        this.levelOfCompletion += delta;
    }

    public boolean isSufficientlySupplied() {
        return isSufficientlySupplied;
    }

    public void setSufficientlySupplied(boolean sufficientlySupplied) {
        isSufficientlySupplied = sufficientlySupplied;
    }

    public void changeAllocatedEnergy(int quantity) {
        this.allocatedEnergy += quantity;
    }

    public void changeAllocatedOre(int quantity) {
        this.allocatedOre += quantity;
    }

    public ArrayList<Worker> getWorkerAssigned() {
        return workerAssigned;
    }

    public void setWorkerAssigned(ArrayList<Worker> workerAssigned) {
        this.workerAssigned = workerAssigned;
    }

    public void subtractFrozenTurn() {
        if(this.turnsFrozen > 0)
            this.turnsFrozen = this.turnsFrozen - 1;
    }

    public String printStats() {
        return getStats().print() +
                "\nVisibility Radius: " + getVisibilityRadius()
                +"\nEnergy Upkeep: " + energyUpkeep
                +"\nOre Upkeep: " + oreUpkeep;
    }
    public void setCommandQueue(CommandQueue commandQueue) {
        this.commandQueue = commandQueue;
    }

    public void queueCommand(Command command) {
        if(commandQueue == null)
            return;
        else
            commandQueue.queueCommand( command );
    }

    public void printCommandQueue(){
        System.out.print("Player" + getOwner().getName() + " " + type + " " + getId() + " command queue:   ");

        for(int i = 0; i < commandQueue.getSize(); i++) {
            System.out.print(commandQueue.get(i).getCommandString());
            if( i + 1 < commandQueue.getSize() && commandQueue.get(i+1) != null)
                System.out.print(" , ");
        }
        if(commandQueue.getSize() == 0)
            System.out.print("empty");
        System.out.println();
    }

    public Command getCommandFromQueue() {
        if(commandQueue.getSize() == 0)
            return null;
        else
            return commandQueue.get(0);
    }

    public void removeCommandFromQueue() {
        if(commandQueue.getSize() == 0)
            return;
        else
            commandQueue.removeCommand();
    }

    public void executeCommandQueue() {

        Command commandToExecute = getCommandFromQueue();

        // do something with the command
        // each unit/structure receives specific list of commands
        // this could be abstract and implemented in the subclasses

    }

}
