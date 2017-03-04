package com.team7.model.item;

import com.team7.ProbabilityGenerator;

/**
 * Activated and removed from map when touched
 */
public class OneShotItem extends Item {
    public OneShotItem() {
        setPassable(true);
        setStatInfluence(ProbabilityGenerator.randomInteger(15,30));
    }
}
