package com.team7.model.visitor;

import com.team7.ProbabilityGenerator;
import com.team7.model.resource.Resource;

/**
 * TileVisitor will visit each component of a Tile:
 *      AreaEffect, Item, Resource, Terrain
 */
public class TileVisitor implements Visitor {
    private int resourceHarvestedAmount;

    //visiting a resource means harvesting a quantity if possible, and sending it to the playerstat processed
    @Override
    public void visit(Resource resource) {
        int remainingQuantity = resource.getStatInfluenceQuantity();
        if(remainingQuantity > 0) {
            resourceHarvestedAmount = ProbabilityGenerator.randomInteger(10,20);   //each turn allows the harvesting of a random value between 10-20 of the Resource
            remainingQuantity -= resourceHarvestedAmount;
            resource.setStatInfluenceQuantity(remainingQuantity);
        } else{
            resourceHarvestedAmount = 0;
        }
    }

    public int getResourceHarvestedAmount() {
        return resourceHarvestedAmount;
    }
}
