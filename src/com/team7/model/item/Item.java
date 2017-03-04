package com.team7.model.item;

/**
 * Immobile and fixed to a location
 * Either immediately picked up & removed by a Unit, or an Obstacle
 */
public abstract class Item {
    private boolean isPassable;
    private int statInfluence;

    public boolean isPassable() {
        return isPassable;
    }

    public void setPassable(boolean passable) {
        isPassable = passable;
    }

    public int getStatInfluence() {
        return statInfluence;
    }

    public void setStatInfluence(int statInfluence) {
        this.statInfluence = statInfluence;
    }

}
