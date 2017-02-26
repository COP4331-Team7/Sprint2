package com.team7.model;

import com.team7.model.entity.Army;
import com.team7.model.entity.structure.Structure;
import com.team7.model.entity.structure.staffedStructure.Capital;
import com.team7.model.entity.unit.Unit;
import com.team7.model.entity.unit.combatUnit.MeleeUnit;
import com.team7.model.entity.unit.combatUnit.RangedUnit;
import com.team7.model.entity.unit.nonCombatUnit.Colonist;
import com.team7.model.entity.unit.nonCombatUnit.Explorer;

import java.util.ArrayList;

public class Player {
    private ArrayList<Unit> units;
    private ArrayList<Structure> structures;
    private ArrayList<Army> armies;
    private int research;
    private int construction;
    private int money;


    public Player() {
        units = new ArrayList<Unit>();                               // max size should be 25
        structures = new ArrayList<Structure>();                     // max size should be 10
        armies = new ArrayList<Army>();                              // max size should be 10
        research = 0;
        construction = 0;
        money = 500;

    }

    // Unit and Army helper function
    public ArrayList<Unit> getUnits() {
        return units;
    }

    // Adds unit to Player's ArrayList of Units
    public Unit addUnit(Unit unit) {

        // Ensures we are able to have a unit
        if(checkMaxUnitsFull() || checkMaxUnitsIndividual()){
            return unit;
        }

        // Physically add the unit to player and put it on the map
        this.units.add(unit);
        unit.getLocation().addUnitToTile(unit);

        return unit;
    }

    // Removes unit from Player's ArrayList of Units
    public Unit removeUnit(Unit unit) {

        this.units.remove(unit);
        unit.getLocation().removeUnitFromTile(unit);

        return unit;
    }

    // Checks if we have 25 Units
    public boolean checkMaxUnitsFull(){
        if(this.units.size() == 25){
            System.out.println("You have too many units.");
            return true;
        }
        return false;
    }

    // Check if we have 10 units of a certain type
    public boolean checkMaxUnitsIndividual(){
        int explorerCount = 0;
        int colonistCount = 0;
        int meleeCount = 0;
        int rangedCount = 0;

        for(int i = 0; i < this.units.size(); i++){
            Unit unit = this.units.get(i);

            if(unit instanceof Colonist){
                colonistCount++;
            }
            else if(unit instanceof Explorer){
                explorerCount++;
            }
            else if(unit instanceof MeleeUnit){
                meleeCount++;
            }
            else if(unit instanceof RangedUnit){
                rangedCount++;
            }

            if(colonistCount == 10 || explorerCount == 10 || meleeCount == 10 || rangedCount == 10) {
                System.out.println("You have too many units of a particular type.");
                return true;
            }
        }
        return false;
    }



    public boolean isDefeated() {
        return !hasCapital();
    }


    // check if the player has either capital or colonist
    public boolean hasCapital() {

        for(int i = 0; i < this.units.size(); i++){
            if(this.units.get(i) instanceof Colonist){
                return true;
            }
        }

        for(int i = 0; i < this.structures.size(); i++){
            if(this.structures.get(i) instanceof Capital){
                return true;
            }
        }

        return false;
    }

}
