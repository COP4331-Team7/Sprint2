package com.team7.model.entity.structure.staffedStructure;

import com.team7.model.entity.structure.Structure;
import com.team7.model.entity.unit.Unit;

import java.util.ArrayList;

/**
 * All structures require a staff except Observation Tower
 */
public abstract class StaffedStructure extends Structure {
    private ArrayList<Unit> workerStaff = new ArrayList<>();
    private int foodUpkeep; //all staffedStructures requires Nutrient from Player
    private int allocatedFood;

    public ArrayList<Unit> getWorkerStaff() {
        return workerStaff;
    }

    public void setWorkerStaff(ArrayList<Unit> workerStaff) {
        this.workerStaff = workerStaff;
    }

    public int getFoodUpkeep() {
        return foodUpkeep;
    }

    public void setFoodUpkeep(int foodUpkeep) {
        this.foodUpkeep = foodUpkeep;
    }

    public int getAllocatedFood() {
        return allocatedFood;
    }

    public void changeAllocatedFood(int quantity) {
        this.allocatedFood += quantity;
    }
}
