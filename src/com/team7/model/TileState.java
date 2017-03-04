package com.team7.model;

import com.team7.model.areaEffects.AreaEffect;
import com.team7.model.decal.Decal;
import com.team7.model.entity.Army;
import com.team7.model.entity.Worker;
import com.team7.model.entity.structure.Structure;
import com.team7.model.entity.unit.Unit;
import com.team7.model.item.Item;
import com.team7.model.resource.Resource;
import com.team7.model.terrain.Terrain;

import java.util.ArrayList;

/**
 * Holds the state of a Tile regarding:
 *  areaEffect
 *  item
 *  resource
 *  structure
 *  units
 *  armies
 *  workers
 */
public class TileState {
    private AreaEffect areaEffect;
    private Decal decal;
    private Item item;
    private Resource resource;
    private Structure structure;
    ArrayList<Unit> units;
    ArrayList<Army> armies;
    ArrayList<Worker> workers;

    //TileState is first defined when a game starts, thus it only has game neutral state 'areaEffect', 'item', and 'resource'
    public TileState(AreaEffect areaEffect, Item item, Resource resource){
        this.areaEffect = areaEffect;
        this.item = item;
        this.resource = resource;

        units = new ArrayList<>();
        armies = new ArrayList<>();
        workers = new ArrayList<>();
    }

    public TileState() {

    }

    //copy constructor
    public TileState(TileState tileStateToCopy) {
        this.item = tileStateToCopy.getItem();  //will this.item be a NEW item, or a REFERENCE to tileStateToCopy?
        this.resource = tileStateToCopy.getResource();
        //etc...
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

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public Structure getStructure() {
        return structure;
    }

    public void setStructure(Structure structure) {
        this.structure = structure;
    }

    public ArrayList<Unit> getUnits() {
        return units;
    }

    public void setUnits(ArrayList<Unit> units) {
        this.units = units;
    }

    public ArrayList<Army> getArmies() {
        return armies;
    }

    public void setArmies(ArrayList<Army> armies) {
        this.armies = armies;
    }

    public ArrayList<Worker> getWorkers() {
        return workers;
    }

    public void setWorkers(ArrayList<Worker> workers) {
        this.workers = workers;
    }

    public void addUnit(Unit unit) {
        units.add(unit);
    }

    public void removeUnit(Unit unit) {
        units.remove(unit);
    }

    public void addWorker(Worker worker) {
        workers.add(worker);
    }

    public void removeWorker(Worker worker) {
        workers.remove(worker);
    }

    public void update(TileState tileStateToCopy) {

    }
}
