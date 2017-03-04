package com.team7.model.entity.structure.staffedStructure;

import com.team7.model.Tile;

/**
 * Structures may harvest, and therefore produce, a certain Resource
 */
public interface IHarvester {
    void harvestResource(Tile tile);
}
