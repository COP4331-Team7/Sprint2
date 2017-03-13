package com.team7.model.entity.structure;

import java.util.HashMap;

public class StructureStats {

    private int offensiveDamage;
    private int defensiveDamage;
    private int armor;
    private int maxArmor;
    private HashMap<String,Integer> productionRates; // No. of turns required to produce a specific unit
    private int health;
    private int maxHealth;
    //upkeep stat is handled per structure class

    public StructureStats(int offensiveDamage, int defensiveDamage, int armor, int maxArmor, HashMap<String, Integer> productionRates, int health, int maxHealth) {
        this.offensiveDamage = offensiveDamage;
        this.defensiveDamage = defensiveDamage;
        this.armor = armor;
        this.maxArmor = maxArmor;
        this.productionRates = productionRates;
        this.health = health;
        this.maxHealth = maxHealth;
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

    //takes in an exact string from productionRate in structure initialization
    public void changeProduction(String key, int delta){
        if(productionRates.containsKey(key)){
            productionRates.put(key, productionRates.get(key) + delta);
            System.out.println("productionRate for " + key + " has been updated to: " + productionRates.get(key));
        }

    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int h) {
        if (h > maxHealth){
            h = maxHealth;
        }

        this.health = h;
    }

    public void changeHealth(int delta){
        health += delta;
        if (health > maxHealth){
            health = maxHealth;
        }
    }

    public void changeMaxHealth(int delta){
        maxHealth += delta;
    }

    public void changeOffensiveDamage(int delta){
        offensiveDamage += delta;
    }

    public void changeDefensiveDamage(int delta){
        defensiveDamage += delta;
    }

    public void changeArmor(int delta){
        armor += delta;
        if (armor > maxArmor){
            armor = maxArmor;
        }
    }

    public void setArmor(int a) {
        if(a > maxArmor) {
            a = maxArmor;
        }
        this.armor = a;
    }

    public void changeMaxArmor(int delta){
        maxArmor += delta;
    }
}
