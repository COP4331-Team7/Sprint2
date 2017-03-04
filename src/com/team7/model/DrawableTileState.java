package com.team7.model;

import java.util.ArrayList;

/**
 * Describes the Tile in String format
 * Accessed by the controller
 * Allows Fog of War implementation without need of copying objects, Memento, or complete serialization
 */
public class DrawableTileState {

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

    public DrawableTileState(){
    }

    //copy constructor
    public DrawableTileState(DrawableTileState stateToCopy) {
        this.areaEffectType = stateToCopy.areaEffectType;
        this.decal = stateToCopy.decal;
        this.itemType = stateToCopy.itemType;
        this.resourceType = stateToCopy.resourceType;
        this.resourceQuantity = stateToCopy.resourceQuantity;
        this.structureType = stateToCopy.structureType;
        this.structureStatus = stateToCopy.structureStatus;

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
}
