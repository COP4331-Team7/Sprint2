package com.team7.model.entity;

import com.team7.model.Player;
import com.team7.model.Tile;

public class Worker extends Entity {

    private Army army;
    private int constructionRate;
    private boolean isFed;
    private boolean assignedEnergy;
    private boolean assignedOre;
    private boolean assignedFood;
    private boolean assignedResearch;
    private int harvestRadius;
    private int foodUpkeep;

    public Worker(Tile startTile, Player player) {
        setOwner(player);
        setLocation(startTile);

        assignedEnergy = false;
        assignedOre = false;
        assignedFood = false;
        assignedResearch = false;

        generateID();
        setArmy(null);
        setVisibilityRadius(2);
        constructionRate = 10;
        setFed(true);   //worker has enough food when created

        foodUpkeep = 5; //initially requires 5 food per turn

        harvestRadius = 0; //a worker can only work on the same tile as a structure initially
    }

    public boolean isFed() {
        return isFed;
    }

    public void setFed(boolean fed) {
        isFed = fed;
    }

    public boolean isAssigned() {
        return assignedEnergy ||assignedFood || assignedOre || assignedResearch;
    }


    public int getConstructionRate() {
        return constructionRate;
    }


    public void changeConstructionRate(int delta){
        constructionRate += delta;
    }


    public Army getArmy() {
        return army;
    }

    public void setArmy(Army army) {
        this.army = army;
    }

    public void applyTechnology(String techInstance, String technologyStat, int level){
        if(techInstance.equals("Worker")){
            switch (technologyStat){
                case "VisibilityRadius":
                    setVisibilityRadius(level);
                    break;
                case "Efficiency":
                    changeFoodUpkeep(0-level);
                    break;
                case "HarvestRadius":
                    setHarvestRadius(level);
                    break;
            }
        }

        if (techInstance.equals("Tile")){
            //TODO change tile density
        }
    }

    private void changeHarvestRadius(int delta){
        harvestRadius += delta;
    }

    public int getFoodUpkeep(){
        return foodUpkeep;
    }

    public int getHarvestRadius(){
        return harvestRadius;
    }

    private void changeFoodUpkeep(int delta){
        foodUpkeep += delta;
    }

    public void setHarvestRadius(int harvestRadius) {
        this.harvestRadius = harvestRadius;
    }

    public boolean isAssignedEnergy() {
        return assignedEnergy;
    }

    public void setAssignedEnergy(boolean assignedEnergy) {
        this.assignedEnergy = assignedEnergy;
    }

    public boolean isAssignedOre() {
        return assignedOre;
    }

    public void setAssignedOre(boolean assignedOre) {
        this.assignedOre = assignedOre;
    }

    public boolean isAssignedFood() {
        return assignedFood;
    }

    public void setAssignedFood(boolean assignedFood) {
        this.assignedFood = assignedFood;
    }

    public boolean isAssignedResearch() {
        return assignedResearch;
    }

    public void setAssignedResearch(boolean assignedResearch) {
        this.assignedResearch = assignedResearch;
    }
}
