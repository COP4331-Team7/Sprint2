package com.team7.model.resource;

/**
 * Superclass of a Resource
 * May be on a Tile in a discrete quantity
 * Harvestable
 */
public abstract class Resource {
    private boolean isDiscovered; //a Resource isDiscovered when it is revealed by Explorer
    private int statInfluenceQuantity; //affects Power, Nutrients, or Metal
    abstract void addResourceToPlayer(int harvestedQuantity);

    public boolean isDiscovered() {
        return isDiscovered;
    }

    public void setDiscovered(boolean discovered) {
        isDiscovered = discovered;
    }

    public int getStatInfluenceQuantity() {
        return statInfluenceQuantity;
    }

    public void setStatInfluenceQuantity(int statInfluenceQuantity) {
        this.statInfluenceQuantity = statInfluenceQuantity;
    }
}
