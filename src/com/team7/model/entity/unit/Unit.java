package com.team7.model.entity.unit;


import com.team7.model.entity.Army;
import com.team7.model.entity.CommandQueue;
import com.team7.model.entity.Entity;
import com.team7.model.entity.unit.combatUnit.CombatUnit;
import com.team7.model.entity.unit.nonCombatUnit.NonCombatUnit;


public class Unit extends Entity {
    private CommandQueue commandQueue;
    private String type;
    private UnitStats unitStats;
    private boolean isPowered;
    int turnsFrozen;
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

        if(this instanceof CombatUnit)
            this.getUnitStats().setUpkeep(4);
        if(this instanceof NonCombatUnit)
            this.getUnitStats().setUpkeep(3);

        this.setTurnsFrozen(2);
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

    public int getTurnsFrozen() {
        return turnsFrozen;
    }

    public void setTurnsFrozen(int movesFrozen) {
        this.turnsFrozen = movesFrozen;
    }

    public void subtractFrozenTurn() {
        if(this.turnsFrozen > 0)
            this.turnsFrozen = this.turnsFrozen - 1;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int dir) {
        this.direction = dir;
    }

    public CommandQueue getCommandQueue() {
        return commandQueue;
    }

    public void setCommandQueue(CommandQueue commandQueue) {
        this.commandQueue = commandQueue;
    }
}
