package com.team7.model.entity.unit.nonCombatUnit;

import com.team7.model.Player;
import com.team7.model.Tile;
import com.team7.model.entity.CommandQueue;
import com.team7.model.entity.unit.UnitStats;

public class Explorer extends NonCombatUnit {

    boolean isProspecting;

    public Explorer(Tile startTile, Player player) {
        setOwner(player);
        setLocation(startTile);
        generateID();
        setUnitStats(new UnitStats(0, 0, 10, 10, 100, 3));
        setCommandQueue(new CommandQueue());
        setType("Explorer");
        setPowered(true);
        setMovesFrozen(0);
        setArmy(null);
        setDirection(2);
        setProspecting(false);
        setVisibilityRadius(3);
    }

    public boolean isProspecting() {
        return isProspecting;
    }

    public void setProspecting(boolean prospecting) {
        isProspecting = prospecting;
    }
}
