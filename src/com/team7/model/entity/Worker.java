package com.team7.model.entity;

import com.team7.model.Player;
import com.team7.model.Tile;

public class Worker extends Entity {

    private Army army;
    private int constructionRate;
    private boolean isFed;
    private boolean isAssigned;

    public Worker(Tile startTile, Player player) {
        setOwner(player);
        setLocation(startTile);

        generateID();
        setArmy(null);
        setVisibilityRadius(2);
        constructionRate = 10;
        setFed(true);   //worker has enough food when created
        setAssigned(false); //worker is not assigned until specified
    }

    public boolean isFed() {
        return isFed;
    }

    public void setFed(boolean fed) {
        isFed = fed;
    }

    public boolean isAssigned() {
        return isAssigned;
    }

    public void setAssigned(boolean assigned) {
        isAssigned = assigned;
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
}
