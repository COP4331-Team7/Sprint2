package com.team7.model.entity.unit;


import com.team7.model.entity.Army;
import com.team7.model.entity.Command;
import com.team7.model.entity.CommandQueue;
import com.team7.model.entity.Entity;


public class Unit extends Entity {
    private CommandQueue commandQueue;
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
        System.out.print("Player" + getOwner().getName() + " " + type + " " + getId() + " command queue:        ");

        for(int i = 0; i < commandQueue.getSize(); i++) {
            System.out.print(commandQueue.get(i).getCommandString() + " , ");
        }
        if(commandQueue.getSize() == 0)
            System.out.print("empty");
        System.out.println();
    }

    private Command getCommandFromQueue() {
        if(commandQueue.getSize() == 0)
            return null;
        else
            return commandQueue.get(0);
    }

    public void executeCommandQueue() {

        Command commandToExecute = getCommandFromQueue();

        // do something with the command
        // each unit/structure receives specific list of commands
        // so this could be abstract and implemented in the subclasses


    }
}
