package com.team7.model;

import com.team7.model.areaEffects.DamageAreaEffect;
import com.team7.model.areaEffects.HealAreaEffect;
import com.team7.model.areaEffects.InstantDeathAreaEffect;
import com.team7.model.entity.Entity;
import com.team7.model.entity.Worker;
import com.team7.model.entity.unit.Unit;
import com.team7.model.entity.unit.combatUnit.MeleeUnit;
import com.team7.model.entity.unit.combatUnit.RangedUnit;
import com.team7.model.entity.unit.nonCombatUnit.Colonist;
import com.team7.model.entity.unit.nonCombatUnit.Explorer;
import com.team7.model.resource.Energy;
import com.team7.model.resource.Food;
import com.team7.model.resource.Ore;
import com.team7.model.resource.Resource;
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
    private int workerUnit;
    private String areaEffectType;
    private String decal;
    private String itemType;
    private String structureType;
    private boolean structureStatus;
    private int ore;
    private int food;
    private int energy;

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
    }

    public void refresh(TileState state) {
        this.terrainType = state.terrainType;
        this.meleeUnit = state.meleeUnit;
        this.rangeUnit = state.rangeUnit;
        this.explorer = state.explorer;
        this.colonist = state.colonist;
        this.workerUnit = state.workerUnit;

        this.ore = state.ore;
        this.food = state.food;
        this.energy = state.energy;

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
        meleeUnit = 0; rangeUnit = 0; explorer = 0; colonist = 0; workerUnit = 0;
        for(Unit unit : tile.getUnits()) {
            if(unit instanceof MeleeUnit)
                meleeUnit++;
            if(unit instanceof RangedUnit)
                rangeUnit++;
            if(unit instanceof Explorer)
                explorer++;
            if(unit instanceof Colonist)
                colonist++;
            if((Entity)unit instanceof Worker)
                workerUnit++;
        }

//        // set resources
//        energy = 0; ore = 0; food = 0;
//        for(Resource resource : tile.getResources()) {
//            if(resource instanceof Energy)
//                energy += resource.getStatInfluenceQuantity();
//            if(resource instanceof Food)
//                food += resource.getStatInfluenceQuantity();
//            if(resource instanceof Ore)
//                ore += resource.getStatInfluenceQuantity();
//        }

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

    public int getWorkerUnit() {
        return workerUnit;
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

        System.out.println(unit.getType() + "decremented");
    }

}
