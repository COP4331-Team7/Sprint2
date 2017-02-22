package com.team7.model.resource;

import com.team7.ProbabilityGenerator;

/**
 *
 */
public class Food extends Resource{
    public Food() {
        setDiscovered(false);
        setStatInfluenceQuantity(ProbabilityGenerator.randomInteger(50,80));
    }

    //Food adds to a Player's Nutrients stat
    @Override
    void addResourceToPlayer(int harvestedQuantity) {

    }
}
