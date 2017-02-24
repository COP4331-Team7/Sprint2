package com.team7.model.entity.structure.staffedStructure;

import com.team7.model.entity.unit.Unit;

/**
 * Structures may produce Units
 * Capital and Fort may produce Units
 */
public interface IUnitProducer {
    Unit produceUnit(String unitType);
}
