package com.team7.model.resource;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Energy adds to a Player's Power stat
 */
public class Energy extends Resource{
    public Energy() {
        setDiscovered(false);
        setStatInfluenceQuantity(ThreadLocalRandom.current().nextInt(30, 95));
        setType("Energy");
    }

}
