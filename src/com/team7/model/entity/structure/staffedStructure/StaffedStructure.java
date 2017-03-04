package com.team7.model.entity.structure.staffedStructure;

import com.team7.model.entity.Worker;
import com.team7.model.entity.structure.Structure;

import java.util.ArrayList;

/**
 * All structures require a staff except Observation Tower
 */
public abstract class StaffedStructure extends Structure {
    private ArrayList<Worker> workerStaff = new ArrayList<>();
    private int foodUpkeepPerWorker; //all staffedStructures requires Nutrient from Player
    private int allocatedFood;
    private boolean hasEnoughFood;

    public ArrayList<Worker> getWorkerStaff() {
        return workerStaff;
    }

    public void setWorkerStaff(ArrayList<Worker> workerStaff) {
        this.workerStaff = workerStaff;
    }

    public int getFoodUpkeepPerWorker() {
        return foodUpkeepPerWorker;
    }

    public void setFoodUpkeepPerWorker(int foodUpkeepPerWorker) {
        this.foodUpkeepPerWorker = foodUpkeepPerWorker;
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
        int totalFoodUpkeep = workerStaff.size() * foodUpkeepPerWorker;
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

    public abstract void beginStructureFunction();

}
