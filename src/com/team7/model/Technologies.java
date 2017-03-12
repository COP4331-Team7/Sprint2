package com.team7.model;

import java.util.ArrayList;

/**
 * A list of all user technology
 * Own class for separation
 */
public class Technologies {
    private ArrayList<Technology> technologies;
    public Technologies(){
        technologies = new ArrayList<>();

        technologies.add(new Technology("tile", "Worker", "Density", 1,5));

        technologies.add(new Technology("unit", "Explorer", "VisibilityRadius", 0, 5));  //only one hex available
        technologies.add(new Technology("unit" ,"Explorer",  "AttackStrength", 1, 5));
        technologies.add(new Technology("unit", "Explorer" , "DefenseStrength", 1, 5));
        technologies.add(new Technology("unit", "Explorer", "ArmorStrength", 1, 5));
        technologies.add(new Technology("unit", "Explorer", "MovementRate", 1, 3));
        technologies.add(new Technology("unit", "Explorer", "Health",1, 5));

        //Production Rate technology
        technologies.add(new Technology("productionRate", "Harvest", "Ore", 1, 5)); //increases amount of Ore a structure can harvest per worker
        technologies.add(new Technology("productionRate", "Harvest", "Food", 1, 5));
        technologies.add(new Technology("productionRate", "Harvest", "Energy", 1, 5));
        technologies.add(new Technology("productionRate", "Produce", "Explorer", 1, 2));//applies to Capital
        technologies.add(new Technology("productionRate", "Produce", "Worker", 1, 2));//applies to Capital
        technologies.add(new Technology("productionRate", "Train", "Worker",1 , 3));//applies to Fort. How quickly a worker can train a recruit
        technologies.add(new Technology("productionRate", "Train", "Soldier", 1, 3));//applies to Fort. How quickly a soldier can train a recruit
    }

    public ArrayList<Technology> getTechnologies() {
        return technologies;
    }
}
