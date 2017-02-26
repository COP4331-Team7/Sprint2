package com.team7.model.entity.structure.staffedStructure.singleHarvestStructure;

import com.team7.model.entity.structure.staffedStructure.IHarvester;
import com.team7.model.entity.structure.staffedStructure.StaffedStructure;

/**
 * Can harvest ore which produces metal
 */
public class Mine extends StaffedStructure implements IHarvester {
    @Override
    public void harvestResource() {
        int harvestedResource = getLocation().structureInteractWithTileForResource(getStats().getProductionRates().get("harvestOre"));
        changeAllocatedOre(harvestedResource);
    }
}
