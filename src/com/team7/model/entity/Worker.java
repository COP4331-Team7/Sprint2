package com.team7.model.entity;

import com.team7.model.Player;
import com.team7.model.Tile;
import com.team7.model.entity.unit.UnitStats;

public class Worker extends Entity {

    private Army army;

    public Worker(Tile startTile, Player player) {
        setOwner(player);
        setLocation(startTile);
        generateID();
        setArmy(null);
        setVisibilityRadius(3);
    }

    public Army getArmy() {
        return army;
    }

    public void setArmy(Army army) {
        this.army = army;
    }
}
