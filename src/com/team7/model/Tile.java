package com.team7.model;

import com.team7.ProbabilityGenerator;
import com.team7.model.areaEffects.AreaEffect;
import com.team7.model.decal.Decal;
import com.team7.model.entity.Army;
import com.team7.model.entity.Worker;
import com.team7.model.entity.structure.Structure;
import com.team7.model.entity.unit.Unit;
import com.team7.model.item.Item;
import com.team7.model.item.Obstacle;
import com.team7.model.item.OneShotItem;
import com.team7.model.resource.Energy;
import com.team7.model.resource.Food;
import com.team7.model.resource.Ore;
import com.team7.model.resource.Resource;
import com.team7.model.terrain.*;

import java.util.ArrayList;


/**
 * Hex shaped which builds the game map
 * Fog of War causes a Tile to have 3 states:
 *  1. Visible: in line of sight of player's avatar
 *  2. Non-visible: not currently in line of sight of player's avatar, display last seen state of tile
 *  3. Shrouded: never been seen
 * Each Tile must be described by a Terrain, all other properties are optionally generated 'randomly'
 */
public class Tile {
    private AreaEffect areaEffect;
    private Decal decal;
    private Item item;
    private Resource resource;
    private Terrain terrain;
    private int xCoordinate;
    private int yCoordinate;
    private Structure structure;
    ArrayList<Unit> units;
    ArrayList<Army> armies;
    ArrayList<Worker> workers;

    //a Tile must have a terrain, and an x/y coordinate
    public Tile(Terrain terrain, int xCoordinate, int yCoordinate){
        this.terrain = terrain;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        units = new ArrayList<Unit>();
        armies = new ArrayList<Army>();
        workers = new ArrayList<Worker>();

        populateTileBasedOnTerrain(terrain);

    }
    //check tile terrain to populate Tile components
    private void populateTileBasedOnTerrain(Terrain terrain) {
        //Desert has 10% AreaEffect, 5% Item, 5% Resource
        //Flatland has 20% AreaEffect, 15% Item, 30% Resource
        //Crater has 20% AreaEffect, 5% Item, 25% Resource
        //Mountain has 0% AreaEffect, 0% Item, 0% Resource
        if(terrain instanceof Desert){
            populateAreaEffect(0.1);
            populateItem(0.05);
            populateResource(0.05);

        }
        else if(terrain instanceof Flatland){
            populateAreaEffect(0.2);
            populateItem(0.15);
            populateResource(0.3);
        }
        else if(terrain instanceof Crater){
            populateAreaEffect(0.2);
            populateItem(0.05);
            populateResource(0.25);
        }
        else if(terrain instanceof Mountains){
            populateAreaEffect(0);
            populateItem(0);
            populateResource(0);
        }
    }


    //Populate Resource for each tile
    public void populateResource(double prob) {
        if(ProbabilityGenerator.willOccur(prob)){
            int rand = ProbabilityGenerator.randomInteger(0,2);
            if(rand == 0)
                setResource(new Energy());
            else if(rand == 1)
                setResource(new Food());
            else if(rand == 2)
                setResource(new Ore());
        }
    }

    //Populate Item for each tile
    public void populateItem(double prob) {
        if(ProbabilityGenerator.willOccur(prob)){
            int rand = ProbabilityGenerator.randomInteger(0,1);
            if(rand == 0)
                setItem(new OneShotItem());

            else if(rand == 1)
                setItem(new Obstacle());
        }
    }

    //TODO figure out if this violate TDA
    //Populate AreaEffect for each tile
    private void populateAreaEffect(double prob) {

        //        int rand = ProbabilityGenerator.randomInteger(0, terrain.getAreaEffects().size()-1);
//        setAreaEffect(terrain.getAreaEffects().get(rand));

//        if (ProbabilityGenerator.willOccur(prob)) {
//            int rand = ProbabilityGenerator.randomInteger(0, terrain.getAreaEffects().size() - 1);
//            setAreaEffect(terrain.getAreaEffects().get(rand));
//        }
    }

    //Structure will only interact with Tile for its Resource
    //called for each Tile in the Structure's available radius
    public int structureInteractWithTileForResource(int quantityOfResourceToHarvest){
        if (resource != null){
            int resourceQuantity = resource.getStatInfluenceQuantity();
            resource.decrementResourceQuantity(quantityOfResourceToHarvest);
            if (resourceQuantity == 0 && !(resource instanceof Food)) {
                resource = null;    //resource has been depleted and nonrenewable, set to NULL
            }
            return resourceQuantity;
        }
        return 0;
    }




    public AreaEffect getAreaEffect() {
        return areaEffect;
    }

    public void setAreaEffect(AreaEffect areaEffect) {
        this.areaEffect = areaEffect;
    }

    public Decal getDecal() {
        return decal;
    }

    public void setDecal(Decal decal) {
        this.decal = decal;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Terrain getTerrain() {
        return terrain;
    }

    public void setTerrain(Terrain terrain) {
        this.terrain = terrain;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }
    public int getxCoordinate() {
        return xCoordinate;
    }

    public int getyCoordinate() {
        return yCoordinate;
    }


    // Adds unit to Tile's ArrayList of Units
    public Unit addUnitToTile(Unit unit) {

        // Physically add the unit
        this.units.add(unit);

        return unit;
    }

    // Removes unit from Tile's ArrayList of Units
    public Unit removeUnitFromTile(Unit unit) {

        this.units.remove(unit);

        return unit;
    }

    // Adds army to Tile's ArrayList of Armies
    public Army addArmyToTile(Army army) {

        // Physically add the unit
        this.armies.add(army);

        return army;
    }

    // Removes army from Tile's ArrayList of Armies
    public Army removeArmyFromTile(Army army) {

        this.armies.remove(army);

        return army;
    }

    // Adds worker to Tile's ArrayList of Workers
    public Worker addWorkerToTile(Worker worker) {

        // Physically add the unit
        this.workers.add(worker);

        return worker;
    }

    // Removes worker from Tile's ArrayList of Workers
    public Worker removeWorkerFromTile(Worker worker) {

        this.workers.remove(worker);

        return worker;
    }

    public Structure getStructure() {
        return structure;
    }

    public void setStructure(Structure structure) {
        this.structure = structure;
    }

}
