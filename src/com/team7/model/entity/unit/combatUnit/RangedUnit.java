package com.team7.model.entity.unit.combatUnit;

import com.team7.model.Player;
import com.team7.model.Tile;
import com.team7.model.entity.CommandQueue;
import com.team7.model.entity.unit.UnitStats;

public class RangedUnit extends CombatUnit {

    public RangedUnit(Tile startTile, Player player) {
        setOwner(player);
        setLocation(startTile);
        generateID();
        setUnitStats(new UnitStats(12, 5, 10, 6, 100, 4));
        setCommandQueue(new CommandQueue());
        setType("Ranged");
        setPowered(true);
        setMovesFrozen(0);
        setArmy(null);
        setDirection(2);
        setVisibilityRadius(3);
    }

}
