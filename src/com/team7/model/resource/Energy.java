package com.team7.model.resource;

import com.team7.model.ProbabilityGenerator;

/**
 * Energy adds to a Player's Power stat
 */
public class Energy extends Resource{
    public Energy() {
        setDiscovered(false);
        setStatInfluenceQuantity(ProbabilityGenerator.randomInteger(50,80));
        setType("Energy");
    }

}
