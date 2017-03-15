package com.team7.model;

import com.team7.model.areaEffects.DamageAreaEffect;
import com.team7.model.areaEffects.HealAreaEffect;
import com.team7.model.areaEffects.InstantDeathAreaEffect;
import com.team7.model.entity.structure.ObservationTower;
import com.team7.model.entity.structure.staffedStructure.Capital;
import com.team7.model.entity.structure.staffedStructure.Fort;
import com.team7.model.entity.structure.staffedStructure.University;
import com.team7.model.entity.structure.staffedStructure.singleHarvestStructure.Farm;
import com.team7.model.entity.structure.staffedStructure.singleHarvestStructure.Mine;
import com.team7.model.entity.structure.staffedStructure.singleHarvestStructure.PowerPlant;
import com.team7.model.entity.unit.Unit;
import com.team7.model.entity.unit.combatUnit.MeleeUnit;
import com.team7.model.entity.unit.combatUnit.RangedUnit;
import com.team7.model.entity.unit.nonCombatUnit.Colonist;
import com.team7.model.entity.unit.nonCombatUnit.Explorer;
import com.team7.model.terrain.Crater;
import com.team7.model.terrain.Desert;
import com.team7.model.terrain.Flatland;
import com.team7.model.terrain.Mountains;

/**
 * Copy tile state
 */
public class TileState {

    private String terrainType;
    private int colonist;
    private int explorer;
    private int meleeUnit;
    private int rangeUnit;
    private String areaEffectType;
    private String decal;
    private String itemType;
    private String structureType;
    private boolean structureStatus;
    private int ore;
    private int food;
    private int energy;

    public int getRallyPoint() {
        return rallyPoint;
    }

    private int rallyPoint;

    public int getFarm() {
        return farm;
    }

    private int farm;

    public int getFort() {
        return fort;
    }

    private int fort;

    public int getMine() {
        return mine;
    }

    public int getObs_tower() {
        return obs_tower;
    }

    public int getUniversity() {
        return university;
    }

    public int getWorkerUnit() {
        return workerUnit;
    }

    public int getCapital() {
        return capital;
    }

    public int getPowerplant() {
        return powerplant;
    }

    private int mine;
    private int obs_tower;

    private int university;
    private int workerUnit;
    private int capital;
    private int powerplant;

    private boolean prospectedByPlayer1;
    private boolean prospectedByPlayer2;

    private boolean workableByPlayer1;
    private boolean workableByPlayer2;

    public TileState(){}

    //copy constructor
    public TileState(TileState stateToCopy) {
        this.terrainType = stateToCopy.terrainType;
        this.areaEffectType = stateToCopy.areaEffectType;
        this.decal = stateToCopy.decal;
        this.itemType = stateToCopy.itemType;
        this.structureType = stateToCopy.structureType;
        this.structureStatus = stateToCopy.structureStatus;
        this.ore = stateToCopy.ore;
        this.food = stateToCopy.food;
        this.energy = stateToCopy.energy;

        this.farm = stateToCopy.farm;
        this.fort = stateToCopy.fort;
        this.mine = stateToCopy.mine;
        this.obs_tower = stateToCopy.obs_tower;
        this.university = stateToCopy.university;
        this.workerUnit = stateToCopy.workerUnit;
        this.powerplant = stateToCopy.powerplant;
    }

    public void refresh(TileState state) {
        this.terrainType = state.terrainType;
        this.meleeUnit = state.meleeUnit;
        this.rangeUnit = state.rangeUnit;
        this.explorer = state.explorer;
        this.colonist = state.colonist;

        this.farm = state.farm;
        this.fort = state.fort;
        this.mine = state.mine;
        this.obs_tower = state.obs_tower;
        this.university = state.university;
        this.workerUnit = state.workerUnit;
        this.powerplant = state.powerplant;

//        this.ore = state.ore;
//        this.food = state.food;
//        this.energy = state.energy;

        this.areaEffectType = state.areaEffectType;
    }

    public void refreshResources(Tile state) {
        if( state.getResources().size() > 0 )
            this.ore = state.getOre();
        if( state.getResources().size() > 1 )
            this.food = state.getFood();
        if( state.getResources().size() > 2 )
            this.energy = state.getEnergy();
    }

    public void refreshHarvestable(Tile tile) {
    }


    public void refresh(Tile tile) {

        // set terrain
        if( tile.getTerrain() instanceof Mountains) {
            terrainType = "Mountains";
        }
        else if (tile.getTerrain() instanceof Crater) {
            terrainType = "Crater";
        }
        else if (tile.getTerrain() instanceof Desert) {
            terrainType = "Desert";
        }
        else if (tile.getTerrain() instanceof Flatland) {
            terrainType = "Flatland";
        }

        // set units
        meleeUnit = 0; rangeUnit = 0; explorer = 0; colonist = 0;
        for(Unit unit : tile.getUnits()) {
            if(unit instanceof MeleeUnit)
                meleeUnit++;
            if(unit instanceof RangedUnit)
                rangeUnit++;
            if(unit instanceof Explorer)
                explorer++;
            if(unit instanceof Colonist)
                colonist++;
        }

        // structures
        if(tile.getStructure() instanceof Capital)
            capital++;
        if(tile.getStructure() instanceof Farm)
            farm++;
        if(tile.getStructure() instanceof PowerPlant)
            powerplant++;
        if(tile.getStructure() instanceof Mine)
            mine++;
        if(tile.getStructure() instanceof Fort)
            fort++;
        if(tile.getStructure() instanceof University)
            university++;
        if(tile.getStructure() instanceof ObservationTower)
            obs_tower++;

        workerUnit = 0;
        workerUnit += tile.getWorkers().size();

        // area affect
        if(tile.getAreaEffect() instanceof DamageAreaEffect)
            areaEffectType = "Damage";
        if(tile.getAreaEffect() instanceof HealAreaEffect)
            areaEffectType = "Heal";
        if(tile.getAreaEffect() instanceof InstantDeathAreaEffect)
            areaEffectType = "InstantDeath";
    }

    public String getTerrainType() {
        return terrainType;
    }

    public String getAreaEffectType() {
        return areaEffectType;
    }

    public void setAreaEffectType(String areaEffectType) {
        this.areaEffectType = areaEffectType;
    }

    public String getDecal() {
        return decal;
    }

    public void setDecal(String decal) {
        this.decal = decal;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public int getFoodQuantity() {
        return food;
    }

    public int getOreQuantity() {
        return ore;
    }

    public int getEnergyQuantity() {
        return energy;
    }
    public String getStructureType() {
        return structureType;
    }

    public void setStructureType(String structureType) {
        this.structureType = structureType;
    }

    public boolean getStructureStatus() {
        return structureStatus;
    }

    public void setStructureStatus(boolean structureStatus) {
        this.structureStatus = structureStatus;
    }

    public void setTerrainType(String terrainType) {
        this.terrainType = terrainType;
    }

    public int getRangeUnit() {
        return rangeUnit;
    }

    public int getMeleeUnit() {
        return meleeUnit;
    }

    public int getExplorer() {
        return explorer;
    }

    public int getColonist() {
        return colonist;
    }

    public void decremenUnits(Unit unit) {
        if (unit.getType().contains("Explorer"))
            explorer--;
        if (unit.getType().contains("Colonist"))
            colonist--;
        if (unit.getType().contains("Melee"))
            meleeUnit--;
        if (unit.getType().contains("Range"))
            rangeUnit--;
    }

    public boolean isProspectedByPlayer1() {
        return prospectedByPlayer1;
    }

    public void setProspectedByPlayer1(boolean prospectedByPlayer1) {
        this.prospectedByPlayer1 = prospectedByPlayer1;
    }

    public boolean isProspectedByPlayer2() {
        return prospectedByPlayer2;
    }

    public void setProspectedByPlayer2(boolean prospectedByPlayer2) {
        this.prospectedByPlayer2 = prospectedByPlayer2;
    }

    public boolean isWorkableByPlayer1() {
        return workableByPlayer1;
    }

    public void setWorkableByPlayer1(boolean workableByPlayer1) {
        this.workableByPlayer1 = workableByPlayer1;
    }

    public boolean isWorkableByPlayer2() {
        return workableByPlayer2;
    }

    public void setWorkableByPlayer2(boolean workableByPlayer2) {
        this.workableByPlayer2 = workableByPlayer2;
    }

    public void decrementWorkers() {
        workerUnit--;
    }

}
