package com.team7.model.entity;

import com.team7.model.Player;
import com.team7.model.Tile;

public class Worker extends Entity {

    private Army army;
    private int constructionRate;
    private boolean isFed;

    public Worker(Tile startTile, Player player) {
        setOwner(player);
        setLocation(startTile);
        generateID();
        setArmy(null);
        setVisibilityRadius(3);
        constructionRate = 10;
        setFed(true);   //worker has enough food when created
    }

    public boolean isFed() {
        return isFed;
    }

    public void setFed(boolean fed) {
        isFed = fed;
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
