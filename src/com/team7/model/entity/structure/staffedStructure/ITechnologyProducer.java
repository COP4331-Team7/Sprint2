package com.team7.model.entity.structure.staffedStructure;

/**
 * Only a University may currently produce technological advancements
 * Only one advancement can be researched per university at a time
 */
public interface ITechnologyProducer {
    void produceTechnology();    //increments current level by +1
}
