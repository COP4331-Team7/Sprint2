package com.team7.model.item;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Activated and removed from map when touched
 */
public class OneShotItem extends Item {
    public OneShotItem() {
        setPassable(true);
        setStatInfluence(ThreadLocalRandom.current().nextInt(15, 30));
        setType("OneShotItem");
    }
}
