package com.team7.model.resource;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Food adds to a Player's Nutrients stat
 */
public class Food extends Resource{
    public Food() {
        setDiscovered(false);
        setStatInfluenceQuantity(ThreadLocalRandom.current().nextInt(30, 95));
        setType("Food");
    }

}
