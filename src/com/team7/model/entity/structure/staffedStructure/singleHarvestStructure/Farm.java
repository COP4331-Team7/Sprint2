package com.team7.model.entity.structure.staffedStructure.singleHarvestStructure;

import com.team7.model.Player;
import com.team7.model.Tile;
import com.team7.model.entity.structure.StructureStats;
import com.team7.model.entity.structure.staffedStructure.IHarvester;
import com.team7.model.entity.structure.staffedStructure.StaffedStructure;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Can harvest food which produces nutrients
 */
public class Farm extends StaffedStructure implements IHarvester {

    public Farm(Tile location, Player player) {
        setOwner(player);
        setLocation(location);

        HashMap<String, Integer> productionRateMap = new HashMap<>();
        productionRateMap.put("harvestFood", 20);   //can harvest 20 food per turn per resource
        setStats(new StructureStats(
                0,
                100,
                100,
                productionRateMap,
                100)
        );
        setType("Farm");
        setPowered(false);
        setMovesFrozen(0);
        setInfluenceRadius(3);
        setEnergyUpkeep(5);
        setOreUpkeep(5);
        setWorkerStaff(new ArrayList<>()); //TODO fill up with 5 workers
        setFoodUpkeepPerWorker(2);       //amount of upkeep needed PER WORKER
        setLevelOfCompletion(80);
    }

    @Override
    public void harvestResource(Tile tile) {
        int harvestedResource = tile.structureInteractWithTileForResource(getStats().getProductionRates().get("harvestFood"));
        changeAllocatedFood(harvestedResource);
    }

    @Override
    public void beginStructureFunction() {
        ArrayList<Tile> tilesInRadius = computeTilesInRadius();
        for(Tile availableTile : tilesInRadius) {
            harvestResource(availableTile);
        }
    }


}
