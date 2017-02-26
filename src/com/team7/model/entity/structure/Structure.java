package com.team7.model.entity.structure;

import com.team7.model.Player;
import com.team7.model.Tile;
import com.team7.model.entity.Entity;
import com.team7.model.entity.unit.Unit;

import java.util.ArrayList;

public abstract class Structure extends Entity{

    private StructureStats stats;
    private String type;
    private boolean isPowered;
    private int movesFrozen;
    private int influenceRadius;
    private int energyUpkeep;   //requires Power from Player
    private int oreUpkeep;      //requires Metal from Player

    public StructureStats getStats() {
        return stats;
    }

    public void setStats(StructureStats stats) {
        this.stats = stats;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isPowered() {
        return isPowered;
    }

    public void setPowered(boolean powered) {
        isPowered = powered;
    }

    public int getMovesFrozen() {
        return movesFrozen;
    }

    public void setMovesFrozen(int movesFrozen) {
        this.movesFrozen = movesFrozen;
    }

    public int getInfluenceRadius() {
        return influenceRadius;
    }

    public void setInfluenceRadius(int influenceRadius) {
        this.influenceRadius = influenceRadius;
    }

    public int getEnergyUpkeep() {
        return energyUpkeep;
    }

    public void setEnergyUpkeep(int energyUpkeep) {
        this.energyUpkeep = energyUpkeep;
    }

    public int getOreUpkeep() {
        return oreUpkeep;
    }

    public void setOreUpkeep(int oreUpkeep) {
        this.oreUpkeep = oreUpkeep;
    }
}
