package com.team7.model.entity;


import com.team7.model.Tile;

public class MovementCommand extends Command {

    Tile destinationTile;

    public MovementCommand(String string, Tile destination) {
        setCommandString("MOVE");
        setWait(0);
        destinationTile = destination;
    }

    public Tile getDestinationTile() {
        return destinationTile;
    }

    public void setDestinationTile(Tile destinationTile) {
        this.destinationTile = destinationTile;
    }
}
