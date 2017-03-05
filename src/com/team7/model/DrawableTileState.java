package com.team7.model;

import com.team7.model.terrain.Crater;
import com.team7.model.terrain.Desert;
import com.team7.model.terrain.Flatland;
import com.team7.model.terrain.Mountains;

import java.util.ArrayList;

/**
 * Describes the Tile in String format
 * Accessed by the controller
 * Allows Fog of War implementation without need of copying objects, Memento, or complete serialization
 */
public class DrawableTileState {

    private String terrainType;
    private int playerOneUnits;
    private int playerTwoUnits;
    boolean isShrouded;

    private String areaEffectType;
    private String decal;
    private String itemType;
    private String resourceType;
    private String resourceQuantity;
    private String structureType;
    private String structureStatus;
    private ArrayList<String> units;
    private ArrayList<String> armies;
    private ArrayList<String> workers;

    private int numUnits = 0;

    public DrawableTileState(Tile tile){

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
        if (tile.getUnits()!=null) {
            playerOneUnits = tile.getUnits().size();
            playerTwoUnits = tile.getUnits().size();
        }

    }

    //copy constructor
    public DrawableTileState(DrawableTileState stateToCopy) {

        this.terrainType = stateToCopy.terrainType;
        this.playerOneUnits = stateToCopy.playerOneUnits;
        this.playerTwoUnits = stateToCopy.playerTwoUnits;

        this.areaEffectType = stateToCopy.areaEffectType;
        this.decal = stateToCopy.decal;
        this.itemType = stateToCopy.itemType;
        this.resourceType = stateToCopy.resourceType;
        this.resourceQuantity = stateToCopy.resourceQuantity;
        this.structureType = stateToCopy.structureType;
        this.structureStatus = stateToCopy.structureStatus;
        this.numUnits = numUnits;
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
        if (tile.getUnits()!=null) {
            playerOneUnits = tile.getUnits().size();
            playerTwoUnits = tile.getUnits().size();
        }
    }

    public int getPlayerOneUnits() {
        return  playerOneUnits;
    }

    public int getPlayerTwoUnits() {
        return  playerTwoUnits;
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

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public String getResourceQuantity() {
        return resourceQuantity;
    }

    public void setResourceQuantity(String resourceQuantity) {
        this.resourceQuantity = resourceQuantity;
    }

    public String getStructureType() {
        return structureType;
    }

    public void setStructureType(String structureType) {
        this.structureType = structureType;
    }

    public String getStructureStatus() {
        return structureStatus;
    }

    public void setStructureStatus(String structureStatus) {
        this.structureStatus = structureStatus;
    }

    public void incrementPlayerOneUnits(int amount) {
        playerOneUnits += amount;
    }
    public void incrementPlayerTwoUnits(int amount) {
        playerTwoUnits += amount;
    }




}
