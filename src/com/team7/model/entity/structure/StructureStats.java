package com.team7.model.entity.structure;

import java.util.HashMap;

public class StructureStats {

    private int offensiveDamage;
    private int defensiveDamage;
    private int armor;
    private HashMap<String,Integer> productionRates; // No. of turns required to produce a specific unit
    private int health;
    //upkeep stat is handled per structure class

    public StructureStats(int offensiveDamage, int defensiveDamage, int armor, HashMap<String, Integer> productionRates, int health) {
        this.offensiveDamage = offensiveDamage;
        this.defensiveDamage = defensiveDamage;
        this.armor = armor;
        this.productionRates = productionRates;
        this.health = health;
    }

    public int getOffensiveDamage() {
        return offensiveDamage;
    }

    public int getDefensiveDamage() {
        return defensiveDamage;
    }

    public int getArmor() {
        return armor;
    }

    public HashMap<String, Integer> getProductionRates() {
        return productionRates;
    }

    public int getHealth() {
        return health;
    }
}
