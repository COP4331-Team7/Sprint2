package com.team7.model.entity.unit.combatUnit;

import com.team7.model.Player;
import com.team7.model.Tile;
import com.team7.model.entity.CommandQueue;
import com.team7.model.entity.unit.UnitStats;

public class MeleeUnit extends CombatUnit {

    public MeleeUnit(Tile startTile, Player player) {
        setOwner(player);
        setLocation(startTile);
        generateID();
        setUnitStats(new UnitStats(20, 10, 15, 8, 100, 4));
        setCommandQueue(new CommandQueue());
        setType("Melee");
        setPowered(true);
        setMovesFrozen(0);
        setArmy(null);
        setDirection(2);
        setVisibilityRadius(2);
    }

    @Override
    public void applyTechnology(String techInstance, String technologyStat, int level) {

    }
}
