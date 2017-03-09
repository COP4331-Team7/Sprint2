package com.team7.model.entity;

import com.team7.model.Player;
import com.team7.model.Tile;
import com.team7.model.entity.unit.Unit;

import java.util.ArrayList;

public class Army extends Entity {

    private static int army_Ids;
    private CommandQueue commandQueue;
    private ArrayList<Unit> units;
    private ArrayList<Worker> workers;
    private int slowestSpeed;
    private int greatestVis;
    private int direction;
    private int turnsFrozen;

    public Army(Tile startTile, Player player) {
        setOwner(player);
        setLocation(startTile);

        generateID();

        this.commandQueue = new CommandQueue();
        this.units = new ArrayList<Unit>();
        this.workers = new ArrayList<Worker>();
        this.slowestSpeed = 100;
        this.greatestVis = 1;
        this.turnsFrozen = 0;
    }

    public int takeId() {
        for(int i = 0; i < 10; i++) {
            if( ((army_Ids >> i) & 1) == 0 ) {
                returnId(i);
                return  i;
            }
        }
        return  -1;
    }
    public void returnId(int id) {
        army_Ids = (army_Ids ^ (1 << id) );
    }

}
