package com.team7.model.resource;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Ore adds to a Player's Metal stat
 */
public class Ore extends Resource {
    public Ore(){
        setDiscovered(false);
        setStatInfluenceQuantity(ThreadLocalRandom.current().nextInt(30, 95));
        setType("Ore");
    }


}
