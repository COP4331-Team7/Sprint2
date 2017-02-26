package com.team7.model;

import com.team7.model.entity.Army;
import com.team7.model.entity.structure.Structure;
import com.team7.model.entity.structure.staffedStructure.Capital;
import com.team7.model.entity.unit.Unit;
import com.team7.model.entity.unit.nonCombatUnit.Colonist;

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
