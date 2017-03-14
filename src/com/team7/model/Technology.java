package com.team7.model;

import com.team7.model.entity.Entity;

/**
 * An object defined by a string -> int mapping
 */
public class Technology {
    private String technologyType;
    private String technologyInstance;
    private String technologyStat;
    private int level;
    private int maxLevel;

    public Technology(String technologyType, String technologyInstance, String technologyStat, int level, int maxLevel){
        this.technologyType = technologyType;
        this.technologyInstance = technologyInstance;
        this.technologyStat = technologyStat;
        this.level = level;
        this.maxLevel = maxLevel;
    }

    public Technology(Entity type, String technologyStat, int level){
        //create Technology
    }

    public String getTechnologyType() {
        return technologyType;
    }


    public String getTechnologyInstance() {
        return technologyInstance;
    }

    public String getTechnologyStat() {
        return technologyStat;
    }

    public int getLevel() {
        return level;
    }

    public int getMaxLevel() {
        return maxLevel;
    }

    public void incrementTechnologyLevel(){
        if(level < maxLevel){
            level += 1;
        }

    }
}
