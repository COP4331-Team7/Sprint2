package com.team7.model.resource;

import com.team7.ProbabilityGenerator;

/**
 *
 */
public class Energy extends Resource{
    public Energy() {
        setDiscovered(false);
        setStatInfluenceQuantity(ProbabilityGenerator.randomInteger(50,80));
    }

    //Energy adds to a Player's Power stat
    @Override
    void addResourceToPlayer(int harvestedQuantity) {

    }
}
