package com.team7.model.entity.structure;

import com.team7.model.Player;
import com.team7.model.Tile;
import com.team7.model.entity.CommandQueue;
import com.team7.model.entity.Entity;
import com.team7.model.entity.Worker;
import com.team7.model.entity.unit.Unit;

import java.util.ArrayList;

public abstract class Structure extends Entity {
    private CommandQueue commandQueue;
    private StructureStats stats;
    private String type;
    private boolean isPowered;
    private int turnsFrozen;
    private int influenceRadius;
    private int energyUpkeep;   //requires Power from Player
    private int oreUpkeep;      //requires Metal from Player
    private int allocatedEnergy;
    private int allocatedOre;
    private int levelOfCompletion;  //range 0 to 100, incremented at a rate of +1 per worker per turn
    private boolean isSufficientlySupplied;
    private ArrayList<Worker> workerAssigned = new ArrayList<>();

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

    public int getInfluenceRadius() {
        return influenceRadius;
    }

    public void setInfluenceRadius(int influenceRadius) {
        this.influenceRadius = influenceRadius;
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

    private boolean checkConstructionComplete(){       //called at end of every turn
        if(levelOfCompletion >= 100){
            levelOfCompletion = 100;
            return true;
        }
        return false;
    }

    public void advanceConstruction() {
        if (!checkConstructionComplete()) {
            //construction not complete
            for(Worker worker : workerAssigned){
                changeLevelOfCompletion(worker.getConstructionRate());  //increment construction according to number of workers
            }
            if(checkConstructionComplete()){
                setPowered(true);   //construction has finished at this turn
            }
        }
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

    public ArrayList<Tile> computeTilesInRadius() {
        //call method compute(location, radius)
        return null;
    }

    public void subtractFrozenTurn() {
        if(this.turnsFrozen > 0)
            this.turnsFrozen = this.turnsFrozen - 1;
    }

}
