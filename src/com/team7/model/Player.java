package com.team7.model;

import com.team7.controller.PathSelectController;
import com.team7.model.decal.Decal;
import com.team7.model.entity.Army;
import com.team7.model.entity.Entity;
import com.team7.model.entity.Worker;
import com.team7.model.entity.structure.ObservationTower;
import com.team7.model.entity.structure.Structure;
import com.team7.model.entity.structure.staffedStructure.Capital;
import com.team7.model.entity.structure.staffedStructure.Fort;
import com.team7.model.entity.structure.staffedStructure.StaffedStructure;
import com.team7.model.entity.structure.staffedStructure.University;
import com.team7.model.entity.structure.staffedStructure.singleHarvestStructure.Farm;
import com.team7.model.entity.structure.staffedStructure.singleHarvestStructure.Mine;
import com.team7.model.entity.structure.staffedStructure.singleHarvestStructure.PowerPlant;
import com.team7.model.entity.unit.Unit;
import com.team7.model.entity.unit.combatUnit.MeleeUnit;
import com.team7.model.entity.unit.combatUnit.RangedUnit;
import com.team7.model.entity.unit.nonCombatUnit.Colonist;
import com.team7.model.entity.unit.nonCombatUnit.Explorer;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;

public class Player {
    private ArrayList<Unit> units;

    private ArrayList<Structure> structures;

    private Technologies technologies;


    private ArrayList<Army> armies;
    private ArrayList<Worker> workers;

    private boolean defeated;
    private int research;
    private int power;      //from harvested energy
    private int nutrients;  //from harvested food
    private int metal;      //from harvested ore
    private PathSelectController controller = null;

    private String name;    //used to check player type easily

    public Player(String name) {
        this.name = name;
        units = new ArrayList<Unit>();                               // max size should be 25

        structures = new ArrayList<>();                              // max size should be 10

        armies = new ArrayList<Army>();                              // max size should be 10
        workers = new ArrayList<Worker>();
        research = 0;
        power = 200;
        nutrients = 200;
        metal = 200;

        technologies = new Technologies();  //init all player technologies


    }

    //method to execute all internal model changes
    //called when a player ends his turn
    public void takeTurn() {
        initiateStructureEffects();
        moveUnitsTowardsArmy();
        subtractMovesFrozen();
        checkUnitArmyStructs();
        subtractUnitUpkeep();
    }

    // move each unit towards destination tile
    private void moveUnitsTowardsArmy() {
        for(int i = 0; i < units.size(); i++) {
            if(units.get(i).getArmy() != null) {
                if(!units.get(i).checkAtRallyPoint()) {

                    System.out.println("Moving towards rallypoint");
                }
                // TODO: if unit is at rallypoint, remove exchange from reinforcements to battlegroup
            }
        }
    }

    private void subtractMovesFrozen() {

        // subtract one from moves frozen for all frozen units
        for (int i = 0; i < this.units.size(); i++) {
            this.units.get(i).subtractFrozenTurn();
        }

        // subtract one from moves frozen for all frozen structures
        for (int i = 0; i < this.structures.size(); i++) {
            this.structures.get(i).subtractFrozenTurn();
        }

        for (int i = 0; i < this.armies.size(); i++) {
            this.armies.get(i).subtractFrozenTurn();
        }

    }

   /* at every turn:
    *  1. build/check if structure is construction complete.
    *  2. check sufficient upkeep, decrement Player's resources.
    *  3. do automatic structure functions (if applicable)
    *  4. execute structure/army Q
   */

    // run this function at the end of each turn to see if there are any dead structures
    // units or armies that need to be removed from the array lists
    public void checkUnitArmyStructs() {

        // check if any units are dead, if so remove from list
        int unitSize = this.units.size();
        for (int i = unitSize - 1; i >= 0; i--) {
            if (!this.units.get(i).isAlive()) {
                removeUnit(this.units.get(i));
            }
        }

        int armySize = this.armies.size();
        // check if any army units are dead, if so remove them
        // then check if army is empty, if so, remove it
        for (int i = armySize - 1; i >= 0; i--) {
            int armyUnitSize = this.armies.get(i).getUnits().size();
            for (int j = armyUnitSize - 1; j >= 0; j--) {
                // if any unit in the army is dead, remove it from the army
                if (!this.armies.get(i).getUnits().get(j).isAlive()) {
                    removeUnit(this.armies.get(i).getUnits().get(j));
                    this.armies.get(i).removeUnitFromArmy(this.armies.get(i).getUnits().get(j));
                }
            }
            if (this.armies.get(i).getUnits().size() == 0) {
                removeArmy(this.armies.get(i));
            }
        }

        int structureSize = this.structures.size();
        // check for any dead structures
        for (int i = structureSize - 1; i >= 0; i--) {
            if (!this.structures.get(i).isAlive()) {
                removeStructure(this.structures.get(i));
            }
        }

    }

    private void subtractUnitUpkeep() {
        int sum = 0;

        // add all unit stats
        for (int i = 0; i < this.units.size(); i++) {
            sum += this.units.get(i).getUnitStats().getUpkeep();
        }

        nutrients = nutrients - sum;

    }


    private void initiateStructureEffects() {
        int energyLevelsOfStructures = 0;
        int foodLevelOfStructures = 0;
        int oreLevelOfStructures = 0;


        for (Structure structure : structures) {
            structure.advanceConstruction();    //builds a structure, does nothing if already complete
            int foodCostOfConstruction = 0;


            energyLevelsOfStructures += structure.computeEnergyUpkeep();
            if (structure instanceof StaffedStructure) {
                foodLevelOfStructures += ((StaffedStructure) structure).computeFoodUpkeep();
            }
            oreLevelOfStructures += structure.computeOreUpkeep();


            if (structure instanceof StaffedStructure) {
                ((StaffedStructure) structure).beginStructureFunction();
            }
        }

        power += energyLevelsOfStructures;
        nutrients += foodLevelOfStructures;
        metal += oreLevelOfStructures;
    }

    //TODO simplify iterating via Entities
    public HashMap<Tile, Integer> getAllTileRadiusMap() {
        HashMap<Tile, Integer> tileRadiusMap = new HashMap<>();
        for (Unit unit : units) {
            if (unit.isPowered()) {
                Tile tile = unit.getLocation();
                int radius = unit.getVisibilityRadius();
                tileRadiusMap.put(tile, radius);
            }
        }

        for (Structure structure : structures) {
            //     if (structure.isPowered()){
            Tile tile = structure.getLocation();
            int radius = structure.getVisibilityRadius();
            tileRadiusMap.put(tile, radius);
            //  }
        }

        return tileRadiusMap;
    }

    public HashMap<Tile, Integer> getAllProspectedTile() {
        HashMap<Tile, Integer> tileRadiusMap = new HashMap<>();

        for (Unit unit : units) {
            if (unit.getType().contains("Explorer") && ((Explorer) (unit)).isProspecting()) {
                Tile tile = unit.getLocation();
                int radius = unit.getVisibilityRadius();
                tileRadiusMap.put(tile, radius);
            }
        }

        return tileRadiusMap;
    }

    public HashMap<Tile, Integer> getAllHarvestableTilesForUnassignedWorkers() {
        HashMap<Tile, Integer> harvestableTilesByWorkers = new HashMap<>();

        for (Structure structure : structures) {
            if (structure instanceof StaffedStructure) {
                harvestableTilesByWorkers.putAll(((StaffedStructure) structure).getHarvestableTilesByWorkers());
            }
        }

        return harvestableTilesByWorkers;
    }


    // Unit and Army helper functions
    public ArrayList<Unit> getUnits() {
        return units;
    }

    // Adds unit to Player's ArrayList of Units
    public Unit addUnit(Unit unit) {

        // Ensures we are able to have a unit
        if (checkMaxUnitsFull() || checkMaxUnitsIndividual()) {
            return null;
        }

        // Physically add the unit to player and put it on the map
        units.add(unit);
        unit.getLocation().addUnitToTile(unit);

        //whenever a unit is added, alter its stats according to technology
        for (Technology unitTechnology : technologies.getUnitTechnologies()) {
            applyTechnology(unitTechnology);
        }

        return unit;
    }

    // Removes unit from Player's ArrayList of Units
    public Unit removeUnit(Unit unit) {
        this.units.remove(unit);
        unit.getLocation().removeUnitFromTile(unit);

        return unit;
    }

    // Checks if we have 25 Units, returns true if too many

    public boolean checkMaxUnitsFull() {
        if (this.units.size() >= 25) {
            System.out.println("You have too many units.");
            return true;
        }
        return false;
    }

    // Check if we have 10 units of a certain type, returns true if too many
    public boolean checkMaxUnitsIndividual() {
        int explorerCount = 0;
        int colonistCount = 0;
        int meleeCount = 0;
        int rangedCount = 0;

        for (int i = 0; i < this.units.size(); i++) {
            Unit unit = this.units.get(i);

            if (unit instanceof Colonist) {
                colonistCount++;
            } else if (unit instanceof Explorer) {
                explorerCount++;
            } else if (unit instanceof MeleeUnit) {
                meleeCount++;
            } else if (unit instanceof RangedUnit) {
                rangedCount++;
            }

            if (colonistCount >= 10 || explorerCount >= 10 || meleeCount >= 10 || rangedCount >= 10) {
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
        if (this.workers.size() >= 100) {
            return null;
        }

        // Physically add the unit to player and put it on the map
        this.workers.add(worker);
        worker.getLocation().addWorkerToTile(worker);

        //whenever a worker is added, alter its stats according to technology
        for (Technology workerTechnology : technologies.getWorkerTechnologies()) {
            applyTechnology(workerTechnology);
        }

        return worker;
    }

    // Removes worker from Player's ArrayList of Units
    public Worker removeWorker(Worker worker) {

        this.workers.remove(worker);
        worker.getLocation().removeWorkerFromTile(worker);

        return worker;
    }

    // Structure helpers


    public ArrayList<Structure> getStructures() {
        return structures;
    }

    public Structure addStructure(Structure structure) {
        // Ensures we are able to add a structure
        if (structures.size() == 10) {
            // Ensures we are able to add a structure
            if (structures.size() >= 10) {
                System.out.println("You have too many structures.");
                return null;
            }

            this.structures.add(structure);
            structure.getLocation().setStructure(structure);

            //whenever a structure is added, alter its stats according to technology
            for (Technology structureTechnology : technologies.getStructureTechnologies()) {
                applyTechnology(structureTechnology);
            }
        }

            return structure;
        }




        // Removes staffedStructure from Player's ArrayList of staffedStructures

    public Structure removeStructure(Structure structure) {

        // Physically remove unit form player and tile

        structures.remove(structure);
        structure.getLocation().setStructure(null);


        return structure;
    }


    public boolean isDefeated() {
        hasCapital();
        return defeated;
    }


    // check if the player has either capital or colonist
    public void hasCapital() {

        for (int i = 0; i < this.units.size(); i++) {
            if (this.units.get(i) instanceof Colonist) {
                defeated = false;
                return;
            }
        }

        for (int i = 0; i < structures.size(); i++) {
            if (structures.get(i) instanceof Capital) {
                defeated = false;
                return;
            }
        }

        defeated = true;
    }

    public boolean moveUnit(Unit unit, Tile destination) {
        if (!unit.isAlive()) {
            removeUnit(unit);
            destination.setDecal(new Decal());
            unit.setLocation(null);
            System.out.println("Unit died");
            return false;
        }
        TileState t = destination.getDrawableStateByPlayer(unit.getOwner().getName());
        if (t.getAreaEffectType() != null) {
            if (t.getAreaEffectType().equalsIgnoreCase("InstantDeath")) {
                unit.getUnitStats().setHealth(0);
                System.out.println(unit.getUnitStats().getHealth());

            } else {
                unit.getUnitStats().changeHealth(destination.getAreaEffect().getHealthEffect());
                System.out.println(unit.getUnitStats().getHealth());
            }

        }
        if (!unit.isAlive()) {
            removeUnit(unit);
            destination.setDecal(new Decal());
            unit.setLocation(null);
            System.out.println("Unit died");
            return false;
        } else {
            unit.getLocation().removeUnitFromTile(unit);
            destination.addUnitToTile(unit);
            unit.setLocation(destination);


            return true;
        }
    }

    // Army helpers

    public ArrayList<Army> getArmies() {
        return armies;
    }

    public int getResearch() {
        return research;
    }

    // Adds army to Player's ArrayList of armies
    public Army addArmy(Army army) {

        // Ensures we are able to have a unit
        if (this.armies.size() >= 10) {
            System.out.println("You have too many armies.");
            return army;
        }

        // Physically add the unit and put it on the map
        this.armies.add(army);
        army.getLocation().addArmyToTile(army);

        return army;
    }


    // Removes army to Player's ArrayList of armies
    public Army removeArmy(Army army) {

        // physically remove the army
        this.armies.remove(army);
        army.getLocation().removeArmyFromTile(army);

        return army;
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

    public Unit getFirstUnit() {
        if (units != null && units.size() > 0) {
            return units.get(0);
        }
        return null;
    }

    public int getNumExplorers() {
        int n = 0;
        for (int i = 0; i < units.size(); i++)
            if (units.get(i) instanceof Explorer)
                n++;
        return n;
    }

    public int getNumColonist() {
        int n = 0;
        for (int i = 0; i < units.size(); i++)
            if (units.get(i) instanceof Colonist)
                n++;
        return n;
    }

    public int getNumMelee() {
        int n = 0;
        for (int i = 0; i < units.size(); i++)
            if (units.get(i) instanceof MeleeUnit)
                n++;
        return n;
    }

    public int getNumRanged() {
        int n = 0;
        for (int i = 0; i < units.size(); i++)
            if (units.get(i) instanceof RangedUnit)
                n++;
        return n;
    }

    public int getNumCapital() {
        int n = 0;
        for (int i = 0; i < structures.size(); i++)
            if (structures.get(i) instanceof Capital)
                n++;
        return n;
    }

    public int getNumFort() {
        int n = 0;
        for (int i = 0; i < structures.size(); i++)
            if (structures.get(i) instanceof Fort)
                n++;
        return n;
    }

    public int getNumUniversity() {
        int n = 0;
        for (int i = 0; i < structures.size(); i++)
            if (structures.get(i) instanceof University)
                n++;
        return n;
    }

    public int getNumObservationTower() {
        int n = 0;
        for (int i = 0; i < structures.size(); i++)
            if (structures.get(i) instanceof ObservationTower)
                n++;
        return n;
    }

    public int getNumMine() {
        int n = 0;
        for (int i = 0; i < structures.size(); i++)
            if (structures.get(i) instanceof Mine)
                n++;
        return n;
    }

    public int getNumFarm() {
        int n = 0;
        for (int i = 0; i < structures.size(); i++)
            if (structures.get(i) instanceof Farm)
                n++;
        return n;
    }

    public int getNumPowerPlant() {
        int n = 0;
        for (int i = 0; i < structures.size(); i++)
            if (structures.get(i) instanceof PowerPlant)
                n++;
        return n;
    }

    public int getNumArmy() {
        return armies.size();
    }

    public Unit getExplorer(int id) {
        for (int i = 0; i < units.size(); i++)
            if (units.get(i) instanceof Explorer && units.get(i).getId() == id)
                return units.get(i);
        return null;
    }

    public Unit getMelee(int id) {
        for (int i = 0; i < units.size(); i++)
            if (units.get(i) instanceof MeleeUnit && units.get(i).getId() == id)
                return units.get(i);
        return null;
    }

    public Unit getRanged(int id) {
        for (int i = 0; i < units.size(); i++)
            if (units.get(i) instanceof RangedUnit && units.get(i).getId() == id)
                return units.get(i);
        return null;
    }

    public Unit getColonist(int id) {
        for (int i = 0; i < units.size(); i++)
            if (units.get(i) instanceof Colonist && units.get(i).getId() == id)
                return units.get(i);
        return null;
    }

    public Army getArmy(int id) {
        for (int i = 0; i < armies.size(); i++)
            if (armies.get(i).getId() == id)
                return armies.get(i);
        return null;
    }

    public Structure getCapital(int id) {
        for (int i = 0; i < structures.size(); i++) {
            if (structures.get(i) instanceof Capital && structures.get(i).getId() == id)
                return structures.get(i);
        }
        return null;
    }

    public Structure getFort(int id) {
        for (int i = 0; i < structures.size(); i++)
            if (structures.get(i) instanceof Fort && structures.get(i).getId() == id)
                return structures.get(i);
        return null;
    }

    public Structure getUniversity(int id) {
        for (int i = 0; i < structures.size(); i++)
            if (structures.get(i) instanceof University && structures.get(i).getId() == id)
                return structures.get(i);
        return null;
    }

    public Structure getObservationTower(int id) {
        for (int i = 0; i < structures.size(); i++)
            if (structures.get(i) instanceof ObservationTower && structures.get(i).getId() == id)
                return structures.get(i);
        return null;
    }

    public Structure getMine(int id) {
        for (int i = 0; i < structures.size(); i++)
            if (structures.get(i) instanceof Mine && structures.get(i).getId() == id)
                return structures.get(i);
        return null;
    }

    public Structure getFarm(int id) {
        for (int i = 0; i < structures.size(); i++)
            if (structures.get(i) instanceof Farm && structures.get(i).getId() == id)
                return structures.get(i);
        return null;
    }

    public Structure getPowerPlant(int id) {
        for (int i = 0; i < structures.size(); i++)
            if (structures.get(i) instanceof PowerPlant && structures.get(i).getId() == id)
                return structures.get(i);
        return null;
    }


    public String getName() {
        return name;
    }

    public void printUnitIds() {
        for (Unit u : units) {
            System.out.println(u.getType() + " : " + u.getId());
        }
        System.out.println();
    }

    public void printUnitCommandQueues() {
        for (Unit u : units) {
            System.out.print("commands for " + u.getType() + " " + u.getId() + ":\n");
            u.printCommandQueue();
        }
        System.out.println();
    }

    public void setMovementController(PathSelectController controller) {
        this.controller = controller;
    }

    private void applyTechnology(Technology tech) {
        String techType = tech.getTechnologyType();
        String techInstance = tech.getTechnologyInstance();
        String techStat = tech.getTechnologyStat();
        int currentLevel = tech.getLevel();

        switch (techType) {
            case "unit":
                for (Unit unit : units) {
                    unit.applyTechnology(techInstance, techStat, currentLevel);
                }
                break;
            case "structure":
                for (Structure structure : structures) {
                    structure.applyTechnology(techInstance, techStat, currentLevel);
                }
                break;
            case "worker":
                for (Worker worker : workers) {
                    worker.applyTechnology(techInstance, techStat, currentLevel);
                }
                break;
            case "productionRate":
                //productionRate only applies to staffed structures
                //harvest, produce, or train
                for (Structure structure : structures) {
                    structure.applyTechnology(techInstance, techStat, currentLevel);
                }
                break;
        }
    }

    public Technologies getTechnologies() {
        return technologies;
    }

    public void spendMetal(int amount) {
        metal -= amount;
    }

    public void spendFood(int amount) {
        nutrients -= amount;
    }

    public void spendPower(int amount) {
        power -= amount;
    }

    public void applyAllTechnologies() {
        for (Technology unitTechnology : technologies.getUnitTechnologies()) {
            applyTechnology(unitTechnology);
        }
        for (Technology workerTechnology : technologies.getWorkerTechnologies()) {
            applyTechnology(workerTechnology);
        }
        for (Technology structureTechnology : technologies.getStructureTechnologies()) {
            applyTechnology(structureTechnology);
        }
    }

    public Army getArmyByID(int ID) {
        for(int i = 0; i < armies.size(); i++) {
            if(armies.get(i).getId() == ID) {
                return armies.get(i);
            }
        }
        return null;
    }

}
