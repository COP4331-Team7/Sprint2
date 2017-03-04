package com.team7.model.item;

/**
 * An impassible item
 */
public class Obstacle extends Item {
    public Obstacle() {
        setPassable(false);
        setStatInfluence(0);
        setType("Obstacle");
    }
}
