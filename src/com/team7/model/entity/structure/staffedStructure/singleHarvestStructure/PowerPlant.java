package com.team7.model.entity.structure.staffedStructure.singleHarvestStructure;

import com.team7.model.Player;
import com.team7.model.Tile;
import com.team7.model.entity.structure.StructureStats;
import com.team7.model.entity.structure.staffedStructure.IHarvester;
import com.team7.model.entity.structure.staffedStructure.StaffedStructure;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Can harvest energy which produces power
 */
public class PowerPlant extends StaffedStructure implements IHarvester {
    public PowerPlant(Tile location, Player player) {
        setOwner(player);
        setLocation(location);

        HashMap<String, Integer> productionRateMap = new HashMap<>();
        productionRateMap.put("harvestEnergy", 2);   //can harvest 2 energy per turn per worker per resource
        setStats(new StructureStats(
                0,
                100,
                100,
                productionRateMap,
                100)
        );
        setType("PowerPlant");
        setPowered(false);
        setMovesFrozen(0);
        setVisibilityRadius(3);
        setEnergyUpkeep(5);
        setOreUpkeep(5);
        setWorkerStaff(new ArrayList<>());
        setLevelOfCompletion(80);
    }

    @Override
    public void harvestResource(Tile tile) {

    }

    @Override
    public void beginStructureFunction() {

    }

    @Override
    public void applyTechnology(String techInstance, String technologyStat, int level) {

    }
}
