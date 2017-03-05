package com.team7.model;

import com.team7.model.entity.Army;
import com.team7.model.entity.Entity;
import com.team7.model.entity.Worker;
import com.team7.model.entity.structure.ObservationTower;
import com.team7.model.entity.structure.staffedStructure.Capital;
import com.team7.model.entity.structure.staffedStructure.StaffedStructure;
import com.team7.model.entity.unit.Unit;
import com.team7.model.entity.unit.combatUnit.MeleeUnit;
import com.team7.model.entity.unit.combatUnit.RangedUnit;
import com.team7.model.entity.unit.nonCombatUnit.Colonist;
import com.team7.model.entity.unit.nonCombatUnit.Explorer;

import java.util.ArrayList;
import java.util.HashMap;

public class Player {
    private ArrayList<Unit> units;
    private ArrayList<StaffedStructure> staffedStructures;
    private ArrayList<ObservationTower> observationTowers;
    private ArrayList<Army> armies;
    private ArrayList<Worker> workers;

    private int research;
    private int power;      //from harvested energy
    private int nutrients;  //from harvested food
    private int metal;      //from harvested ore

    private String name;    //used to check player type easily

    public Player(String name) {
        this.name = name;
        units = new ArrayList<Unit>();                               // max size should be 25
        staffedStructures = new ArrayList<>();                        // max size of staffed + observation should be 10
        observationTowers = new ArrayList<>();
        armies = new ArrayList<Army>();                              // max size should be 10
        workers = new ArrayList<Worker>();
        research = 0;
        power = 200;
        nutrients = 200;
        metal = 200;

    }

    //method to execute all internal model changes
    //called when a player ends his turn
    public void takeTurn(){
        initiateStructureEffects();

    }


   /* at every turn:
    *  1. build/check if structure is construction complete.
    *  2. check sufficient upkeep, decrement Player's resources.
    *  3. do automatic structure functions (if applicable)
    *  4. execute structure Q
   */
    private void initiateStructureEffects() {
        int energyLevelsOfStructures = 0;
        int foodLevelOfStructures = 0;
        int oreLevelOfStructures = 0;

        int foodCostOfConstruction = 0;

        //iterate through all staffed structures, which also have a function
        for(StaffedStructure staffedStructure : staffedStructures){
            foodCostOfConstruction += staffedStructure.advanceConstruction();    //builds a structure, does nothing if already complete

            energyLevelsOfStructures += staffedStructure.computeEnergyUpkeep();
            foodLevelOfStructures += staffedStructure.computeFoodUpkeep();
            oreLevelOfStructures += staffedStructure.computeOreUpkeep();

            staffedStructure.influenceStructureAccordingToSupply();
            staffedStructure.influenceWorkersAccordingToFood();

            staffedStructure.beginStructureFunction();

        }

        //iterate through obsv towers to ensure they are build and supplied with energy/ore
        for(ObservationTower observationTower : observationTowers){
            foodCostOfConstruction += observationTower.advanceConstruction();

            energyLevelsOfStructures += observationTower.computeEnergyUpkeep();
            oreLevelOfStructures += observationTower.computeOreUpkeep();

            observationTower.influenceStructureAccordingToSupply();
        }

        nutrients -= foodCostOfConstruction;

        power += energyLevelsOfStructures;
        nutrients += foodLevelOfStructures;
        metal += oreLevelOfStructures;
    }

    //TODO simplify iterating via Entities
    public HashMap<Tile, Integer> getAllTileRadiusMap() {
        HashMap<Tile, Integer> tileRadiusMap = new HashMap<>();
        for (Unit unit : units) {
            if(unit.isPowered()){
                Tile tile = unit.getLocation();
                int radius = unit.getVisibilityRadius();
                tileRadiusMap.put(tile, radius);
            }
        }

        for (StaffedStructure structure : staffedStructures) {
            if (structure.isPowered()){
                Tile tile = structure.getLocation();
                int radius = structure.getVisibilityRadius();
                tileRadiusMap.put(tile, radius);
            }
        }

        for (ObservationTower observationTower : observationTowers) {
            //if (observationTower.isPowered()){    COMMENTED OUT FOR TESTING
                Tile tile = observationTower.getLocation();
                int radius = observationTower.getVisibilityRadius();
                tileRadiusMap.put(tile, radius);
          //  }
        }

        return tileRadiusMap;
    }

    // Unit and Army helper functions
    public ArrayList<Unit> getUnits() {
        return units;
    }

    // Adds unit to Player's ArrayList of Units
    public Unit addUnit(Unit unit) {

        // Ensures we are able to have a unit
        if(checkMaxUnitsFull() || checkMaxUnitsIndividual()){
            return null;
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

    // Checks if we have 25 Units, returns true if too many
    public boolean checkMaxUnitsFull(){
        if(this.units.size() == 25){
            System.out.println("You have too many units.");
            return true;
        }
        return false;
    }

    // Check if we have 10 units of a certain type, returns true if too many
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

            if(colonistCount >= 10 || explorerCount >= 10 || meleeCount >= 10 || rangedCount >= 10) {
                System.out.println("You have too many units of a particular type.");
                return true;
            }
        }
        return false;
    }


    // Worker helpers


    public ArrayList<Worker> getWorkers() {
        return workers;
    }

    // Adds unit to Player's ArrayList of Units
    public Worker addWorker(Worker worker) {

        // Ensures we are able to have a unit
        if(this.workers.size() == 100){
            return null;
        }

        // Physically add the unit to player and put it on the map
        this.workers.add(worker);
        worker.getLocation().addWorkerToTile(worker);

        return worker;
    }

    // Removes worker from Player's ArrayList of Units
    public Worker removeWorker(Worker worker) {

        this.workers.remove(worker);
        worker.getLocation().removeWorkerFromTile(worker);

        return worker;
    }

    // Structure helpers

    public ObservationTower addObservationTower(ObservationTower observationTower){

        // Ensures we are able to add a structure
        if(staffedStructures.size() + observationTowers.size() == 10){
            System.out.println("You have too many structures.");
            return null;
        }

        // Physically add the structure and put it on the map
        observationTowers.add(observationTower);
        observationTower.addStructureToCurrentTile();

        return observationTower;
    }

    public ObservationTower removeObservationTower(ObservationTower observationTower) {
        // Physically remove unit form player and tile
        observationTowers.remove(observationTower);
        observationTower.removeStructureFromCurrentTile();

        return observationTower;
    }
    public ArrayList<StaffedStructure> getStaffedStructures() {
        return staffedStructures;
    }

    public StaffedStructure addStaffedStructure(StaffedStructure staffedStructure) {
        // Ensures we are able to add a structure
        if(staffedStructures.size() + observationTowers.size() == 10){
            System.out.println("You have too many structures.");
            return null;
        }

        // Physically add the structure and put it on the map
        staffedStructures.add(staffedStructure);
        staffedStructure.addStructureToCurrentTile();

        return staffedStructure;
    }

    // Removes staffedStructure from Player's ArrayList of staffedStructures
    public StaffedStructure removeStaffedStructure(StaffedStructure staffedStructure) {

        // Physically remove unit form player and tile
        staffedStructures.remove(staffedStructure);
        staffedStructure.removeStructureFromCurrentTile();

        return staffedStructure;
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

        for(int i = 0; i < staffedStructures.size(); i++){
            if(staffedStructures.get(i) instanceof Capital){
                return true;
            }
        }

        return false;
    }
    public void moveUnit(Unit unit, Tile destination){
        unit.getLocation().removeUnitFromTile(unit);
        destination.addUnitToTile(unit);
        unit.setLocation(destination);
    }
    // Army helpers

    public ArrayList<Army> getArmies() {
        return armies;
    }

    public int getResearch() {
        return research;
    }

    public void setResearch(int research) {
        this.research = research;
    }


    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getNutrients() {
        return nutrients;
    }

    public void setNutrients(int nutrients) {
        this.nutrients = nutrients;
    }

    public int getMetal() {
        return metal;
    }

    public void setMetal(int metal) {
        this.metal = metal;
    }

    public Unit getRandomUnit() {
        if (units != null && units.size() > 0) {
            return units.get(0);
        }
        return null;

    }

    public String getName() {
        return name;
    }

}
