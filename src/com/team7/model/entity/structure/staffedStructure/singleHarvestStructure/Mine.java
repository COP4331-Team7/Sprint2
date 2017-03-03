package com.team7.model.entity.structure.staffedStructure.singleHarvestStructure;

import com.team7.model.Tile;
import com.team7.model.entity.structure.staffedStructure.IHarvester;
import com.team7.model.entity.structure.staffedStructure.StaffedStructure;

import java.util.ArrayList;

/**
 * Can harvest ore which produces metal
 */
public class Mine extends StaffedStructure implements IHarvester {
    @Override
    public void harvestResource(Tile tile) {
        int harvestedResource = tile.structureHarvestOre(getStats().getProductionRates().get("harvestOre"));
        changeAllocatedOre(harvestedResource);
    }

    @Override
    public void beginStructureFunction() {
        ArrayList<Tile> tilesInRadius = computeTilesInRadius();
        for(Tile availableTile : tilesInRadius) {
            harvestResource(availableTile);
        }
    }
}
