package com.team7.model.resource;


/**
 * Superclass of a Resource
 * May be on a Tile in a discrete quantity
 * Harvestable
 */
public abstract class Resource{
    private boolean isDiscovered; //a Resource isDiscovered when it is revealed by Explorer
    private int statInfluenceQuantity; //affects Power, Nutrients, or Metal
    public String type;

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

    public void changeResourceQuantity(int delta) {
        statInfluenceQuantity += delta;
        if(statInfluenceQuantity < 0){
            statInfluenceQuantity = 0;  //reset to 0
        }
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
