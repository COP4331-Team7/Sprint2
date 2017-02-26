package com.team7.model.entity.unit.nonCombatUnit;

import com.team7.model.Player;
import com.team7.model.Tile;
import com.team7.model.entity.CommandQueue;
import com.team7.model.entity.unit.UnitStats;

public class Colonist extends NonCombatUnit {

    public Colonist(Tile startTile, Player player) {
        setOwner(player);
        setLocation(startTile);
        generateID();
        setUnitStats(new UnitStats(0, 0, 5, 5, 100, 3));
        setCommandQueue(new CommandQueue());
        setType("Colonist");
        setPowered(true);
        setMovesFrozen(0);
        setArmy(null);
        setDirection(2);
        setVisibilityRadius(3);
    }

}
