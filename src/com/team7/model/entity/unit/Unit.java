package com.team7.model.entity.unit;


import com.team7.model.Map;
import com.team7.model.Tile;
import com.team7.model.entity.*;
import com.team7.model.entity.unit.combatUnit.CombatUnit;
import com.team7.model.entity.unit.nonCombatUnit.NonCombatUnit;

import java.lang.reflect.Array;
import java.util.ArrayList;


public abstract class Unit extends Entity {
    private CommandQueue commandQueue;
    private String type;
    private UnitStats unitStats;
    private boolean isPowered;
    int turnsFrozen;
    private Army army;
    private int direction;
    private MovementCommand movementCommandQueue;


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

    public void powerUp() {

        if(this instanceof CombatUnit) {
            this.getUnitStats().setUpkeep(4);
        }
        if(this instanceof NonCombatUnit) {
            this.getUnitStats().setUpkeep(3);
        }

        this.setTurnsFrozen(2);
        isPowered = true;
    }

    public void powerDown() {

        this.getUnitStats().setUpkeep(1);
        this.getUnitStats().setMovement(0);
        isPowered = false;
    }

    // see if this unit is at the rally point or not
    public boolean checkAtRallyPoint() {
        if(this.getArmy() != null) {
            if(this.getArmy().getLocation() == this.getLocation()){
                return true;
            }
            else {
                return false;
            }
        }
        return false;
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

    public void executeCommandQueue(Map map) {

        if(getCommandFromQueue() == null)
            return;

        Command commandToExecute = getCommandFromQueue();
        String commandString = commandToExecute.getCommandString();

        if(commandString.contains("REINFORCE")) {
            int ID = Integer.parseInt(commandString.substring(commandString.length() - 1));
            this.getOwner().getArmyByID(ID).addUnitToArmy(this);
            removeCommandFromQueue();
        }
        else if(commandString.contains("DECOMMISSION")) {
            this.decommission();
            removeCommandFromQueue();
        }
        else if(commandString.contains("DOWN")) {
            this.powerDown();
            removeCommandFromQueue();
        }
        else if(commandString.contains("UP")) {
            this.powerUp();
            removeCommandFromQueue();
        }
        else if(commandString.contains("MOVE")) {
            if(commandToExecute instanceof MovementCommand){
                ArrayList<Tile> tiles = map.findMinPath(this.getLocation(), ((MovementCommand) commandToExecute).getDestinationTile(), null, null);
                ArrayList<Tile> reachableTiles = new ArrayList<Tile>();
                for(int i = 0; i < this.getUnitStats().getMovement(); i++) {
                    reachableTiles.add(tiles.get(tiles.size() - i - 1));
                }

                for(int i = 0; i < reachableTiles.size() - 1; i++) {
                    this.getOwner().moveUnit(this, reachableTiles.get(i + 1));
                }
            }
        }


    }

    public boolean isAlive() {
        return unitStats.getHealth() > 0;
    }

    public abstract void applyTechnology(String techInstance, String technologyStat, int level);

    public MovementCommand getMovementCommandQueue() {
        return movementCommandQueue;
    }

    public void setMovementCommandQueue(MovementCommand movementCommandQueue) {
        this.movementCommandQueue = movementCommandQueue;
    }
}
