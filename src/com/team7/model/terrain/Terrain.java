package com.team7.model.terrain;

import com.team7.model.areaEffects.AreaEffect;

import java.util.ArrayList;

/**
 * Describes the landscape
 * May be impassable by certain units
 * May alter unit movement
 * May contain a specific AreaEffect
 */
public abstract class Terrain {
    private long id;
    private boolean isPassable;
    private int movementInfluence;
    private ArrayList<AreaEffect> areaEffects;
    public boolean isPassable() {
        return isPassable;
    }

    public void setPassable(boolean passable) {
        isPassable = passable;
    }

    public int getMovementInfluence() {
        return movementInfluence;
    }

    public void setMovementInfluence(int movementInfluence) {
        this.movementInfluence = movementInfluence;
    }

    public ArrayList<AreaEffect> getAreaEffects() {
        return areaEffects;
    }

    public void setAreaEffects(ArrayList<AreaEffect> areaEffects) {
        this.areaEffects = areaEffects;
    }
}