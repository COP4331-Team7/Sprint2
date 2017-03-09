package com.team7.model.resource;

import com.team7.model.ProbabilityGenerator;

/**
 * Ore adds to a Player's Metal stat
 */
public class Ore extends Resource {
    public Ore(){
        setDiscovered(false);
        setStatInfluenceQuantity(ProbabilityGenerator.randomInteger(50,80));
        setType("Ore");
    }


}
