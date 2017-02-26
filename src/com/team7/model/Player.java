package com.team7.model;

import com.team7.model.entity.Army;
import com.team7.model.entity.structure.Structure;
import com.team7.model.entity.unit.Unit;

import java.util.ArrayList;

public class Player {
    private ArrayList<Unit> units;
    private ArrayList<Structure> structures;
    private ArrayList<Army> armies;
    private int research;
    private int construction;
    private int money;
    private boolean noUnits;
    private boolean noStructures;
    private boolean noArmies;

    public Player() {
        units = new ArrayList<Unit>();                               // max size should be 25
        structures = new ArrayList<Structure>();                     // max size should be 10
        armies = new ArrayList<Army>();                              // max size should be 10
        research = 0;
        construction = 0;
        money = 500;
        noUnits = true;
        noStructures = true;
        noArmies = true;
    }





    public boolean isDefeated() {
        return noArmies && noUnits && noStructures;
    }

    public boolean isNoUnits() {
        return noUnits;
    }

    public boolean isNoStructures() {
        return noStructures;
    }

    public boolean isNoArmies() {
        return noArmies;
    }

}
