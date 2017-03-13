package com.team7.model.entity.unit;

public class UnitStats {
    private int offensiveDamage;
    private int defensiveDamage;
    private int armor;
    private int movement;
    private int health;
    private int upkeep;

    public UnitStats(int od, int dd, int a, int m, int h, int u) {
        offensiveDamage = od;
        defensiveDamage = dd;
        armor = a;
        movement = m;
        health = h;
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
    }

    public void changeMovement(int delta){
        movement += delta;
    }

    public void changeHealth(int delta){
        health += delta;
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

    public void setArmor(int armor) {
        this.armor = armor;
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

    public void setHealth(int health) {
        if (health > 100){
            health = 100;
        }
        this.health = health;
    }

    public int getUpkeep() {
        return upkeep;
    }

    public void setUpkeep(int upkeep) {
        this.upkeep = upkeep;
    }
}



