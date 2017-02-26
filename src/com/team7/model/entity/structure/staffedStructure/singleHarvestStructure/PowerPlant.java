package com.team7.model.entity.structure.staffedStructure.singleHarvestStructure;

import com.team7.model.entity.structure.staffedStructure.IHarvester;
import com.team7.model.entity.structure.staffedStructure.StaffedStructure;

/**
 * Can harvest energy which produces power
 */
public class PowerPlant extends StaffedStructure implements IHarvester {
    @Override
    public void harvestResource() {
        int harvestedResource = getLocation().structureInteractWithTileForResource(getStats().getProductionRates().get("harvestEnergy"));
        changeAllocatedEnergy(harvestedResource);
    }
}
