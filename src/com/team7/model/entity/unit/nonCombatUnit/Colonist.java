package com.team7.model.entity.unit.nonCombatUnit;

import com.team7.model.Player;
import com.team7.model.Tile;
import com.team7.model.entity.CommandQueue;
import com.team7.model.entity.Worker;
import com.team7.model.entity.structure.Structure;
import com.team7.model.entity.structure.staffedStructure.Capital;
import com.team7.model.entity.structure.staffedStructure.StaffedStructure;
import com.team7.model.entity.unit.UnitStats;
import com.team7.model.entity.unit.combatUnit.MeleeUnit;

public class Colonist extends NonCombatUnit {

    private static int colonistIds = 0;

    public Colonist(Tile startTile, Player player) {
        setOwner(player);
        setLocation(startTile);
        generateID();
        setUnitStats(new UnitStats(1, 1, 5, 5, 100, 3));
        setCommandQueue(new CommandQueue());
        setType("Colonist");
        setPowered(true);
        setMovesFrozen(0);
        setArmy(null);
        setDirection(2);
        setVisibilityRadius(2);
    }

    public void buildCapital() {
        // create capital, 5 workers and 2 melee units
        StaffedStructure capital = new Capital(this.getLocation(), this.getOwner());
        this.getOwner().addStaffedStructure(capital);
        this.getOwner().addWorker(new Worker(this.getLocation(), this.getOwner()));
        this.getOwner().addWorker(new Worker(this.getLocation(), this.getOwner()));
        this.getOwner().addWorker(new Worker(this.getLocation(), this.getOwner()));
        this.getOwner().addWorker(new Worker(this.getLocation(), this.getOwner()));
        this.getOwner().addWorker(new Worker(this.getLocation(), this.getOwner()));
        this.getOwner().addUnit(new MeleeUnit(this.getLocation(), this.getOwner()));
        this.getOwner().addUnit(new MeleeUnit(this.getLocation(), this.getOwner()));

        // sacrifice colonist from tile and player
        this.getLocation().removeUnitFromTile(this);
        this.getOwner().removeUnit(this);

    }


    @Override
    public void applyTechnology(String techInstance, String technologyStat, int level) {

    }
}
