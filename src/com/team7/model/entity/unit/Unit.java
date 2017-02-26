package com.team7.model.entity.unit;


import com.team7.model.entity.Army;
import com.team7.model.entity.Entity;
import com.team7.model.entity.unit.combatUnit.RangedUnit;

import java.util.ArrayList;
import java.util.Arrays;

public class Unit extends Entity {
    private String type;
    private UnitStats unitStats;
    private boolean isPowered;
    int movesFrozen;
    private Army army;
    private int direction;


    public UnitStats getUnitStats() {
        return unitStats;
    }

    public void setUnitStats(UnitStats unitStats) {
        this.unitStats = unitStats;
    }

    public boolean isPowered() {
        return isPowered;
    }

    public void setPowered(boolean powered) {
        isPowered = powered;
    }

    // TODO: change this so it is not hardcoded
    public void powerUp() {
        this.getUnitStats().setUpkeep(4);
        this.setMovesFrozen(2);
        isPowered = true;
    }

    // TODO: change this so it is not hardcoded
    public void powerDown() {

        this.getUnitStats().setUpkeep(1);
        this.getUnitStats().setMovement(0);
        isPowered = false;
    }


    public void decommission() {
        this.getUnitStats().setHealth(0);
    }

    public String getType() { return type; }

    public void setType(String type) { this.type = type; }

    public Army getArmy() {
        return army;
    }

    public void setArmy(Army army) {
        this.army = army;
    }

    public int getMovesFrozen() {
        return movesFrozen;
    }

    public void setMovesFrozen(int movesFrozen) {
        this.movesFrozen = movesFrozen;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int dir) {
        this.direction = dir;
    }

}
