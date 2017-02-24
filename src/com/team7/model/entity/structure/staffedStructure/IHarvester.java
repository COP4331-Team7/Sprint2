package com.team7.model.entity.structure.staffedStructure;

/**
 * Structures may harvest, and therefore produce, a certain Resource
 */
public interface IHarvester {
    void harvestResource();
    void processResource(); //once a resource is harvested, it is processed and then added to Player
    void increaseHarvestedResource();
}
