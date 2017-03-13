package com.team7.model.entity;

import com.team7.model.Player;
import com.team7.model.Tile;
import com.team7.model.entity.unit.Unit;

import java.util.ArrayList;

public class Army extends Entity {
    private CommandQueue commandQueue;
    private ArrayList<Unit> units;
    private ArrayList<Worker> workers;
    private int slowestSpeed;
    private int greatestVis;
    private int direction;
    private boolean isPowered;
    private int turnsFrozen;

    public Army(Tile startTile, Player player) {
        setOwner(player);
        setLocation(startTile);
        generateID();
        this.commandQueue = new CommandQueue();
        this.units = new ArrayList<Unit>();
        this.workers = new ArrayList<Worker>();
        this.slowestSpeed = 100;
        this.greatestVis = 1;
        this.turnsFrozen = 0;
    }

    // Adds unit to Army's ArrayList of Units
    public void addUnitToArmy(Unit unit) {
        // Physically add the unit
        this.units.add(unit);
        unit.setArmy(this);

        // Check for new slowest speed
        if(unit.getUnitStats().getMovement() < this.slowestSpeed){
            this.slowestSpeed = unit.getUnitStats().getMovement();
        }

        // Check for new greatest vis
        if(unit.getVisibilityRadius() > this.greatestVis){
            this.greatestVis = unit.getVisibilityRadius();
        }

    }

    // Removes unit from Army's ArrayList of Units
    public void removeUnitFromArmy(Unit unit) {

        this.units.remove(unit);
        unit.setArmy(null);

        resetLowestSpeedGreatestRadius();

    }

    private void resetLowestSpeedGreatestRadius(){
        this.slowestSpeed = 100;
        this.greatestVis = 1;

        for(int i = 0; i < units.size(); i++) {
            // Check for new slowest speed
            if(units.get(i).getUnitStats().getMovement() < this.slowestSpeed){
                this.slowestSpeed = units.get(i).getUnitStats().getMovement();
            }

            // Check for new greatest vis
            if(units.get(i).getVisibilityRadius() > this.greatestVis){
                this.greatestVis = units.get(i).getVisibilityRadius();
            }
        }
    }

    public void powerUp() {


        for(int i = 0; i < units.size(); i++) {
            this.units.get(i).powerUp();
        }

        turnsFrozen = 2;
        isPowered = true;
    }

    public void powerDown() {

        for(int i = 0; i < units.size(); i++) {
            this.units.get(i).powerDown();
        }

        turnsFrozen = 0;
        isPowered = false;
    }


}
