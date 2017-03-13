package com.team7.model.entity.unit;

public class UnitStats {
    private int offensiveDamage;
    private int defensiveDamage;
    private int armor;
    private int maxArmor;
    private int movement;
    private int health;
    private int maxHealth;
    private int upkeep;

    public UnitStats(int od, int dd, int a, int ma, int m, int h, int mh, int u) {
        offensiveDamage = od;
        defensiveDamage = dd;
        armor = a;
        maxArmor = ma;
        movement = m;
        health = h;
        maxHealth = mh;
        upkeep = u;
    }

    public void changeOffensiveDamage(int delta){
        offensiveDamage += delta;
    }

    public void changeDefensiveDamage(int delta){
        defensiveDamage += delta;
    }

    public void changeArmor(int delta){
        armor += delta;
        if(armor > maxArmor) {
            armor = maxArmor;
        }
    }

    public void changeMovement(int delta){
        movement += delta;
    }

    public void changeHealth(int delta){
        health += delta;

        if (health > maxHealth){
            health = maxHealth;
        }
    }

    public void changeUpkeep(int delta){
        upkeep += delta;
    }

    public int getOffensiveDamage() {
        return offensiveDamage;
    }

    public void setOffensiveDamage(int offensiveDamage) {
        this.offensiveDamage = offensiveDamage;
    }

    public int getDefensiveDamage() {
        return defensiveDamage;
    }

    public void setDefensiveDamage(int defensiveDamage) {
        this.defensiveDamage = defensiveDamage;
    }

    public int getArmor() {
        return armor;
    }

    public void setArmor(int a) {
        if(a > maxArmor) {
            a = maxArmor;
        }
        this.armor = a;
    }

    public int getMovement() {
        return movement;
    }

    public void setMovement(int movement) {
        this.movement = movement;
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

    public int getUpkeep() {
        return upkeep;
    }

    public void setUpkeep(int upkeep) {
        this.upkeep = upkeep;
    }
}



