package com.team7.model.entity;

import com.team7.model.Player;
import com.team7.model.Tile;

public abstract class Entity {

    CommandQueue commandQueue;
    private int id;
    private Player owner;
    private Tile location;

    public int getId() {
        return id;
    }

    // Make sure to set the owner before generating an ID in a constructor
    public int generateID() {
        IdManager generator = new IdManager(this.owner);
        return generator.generateID(this);
    };

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public Tile getLocation() {
        return location;
    }

    public void setLocation(Tile location) {
        this.location = location;
    }
    public CommandQueue getCommandQueue() {
        return commandQueue;
    }

}
