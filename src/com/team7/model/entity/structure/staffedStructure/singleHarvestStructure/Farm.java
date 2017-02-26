package com.team7.model.entity.structure.staffedStructure.singleHarvestStructure;

import com.team7.model.entity.structure.staffedStructure.IHarvester;
import com.team7.model.entity.structure.staffedStructure.StaffedStructure;

/**
 * Can harvest food which produces nutrients
 */
public class Farm extends StaffedStructure implements IHarvester {
    @Override
    public void harvestResource() {
        int harvestedResource = getLocation().structureInteractWithTileForResource(getStats().getProductionRates().get("harvestFood"));
        changeAllocatedFood(harvestedResource);
    }
}
