package com.team7.model;

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
import java.util.concurrent.ThreadLocalRandom;


/**
 * Hex shaped which builds the game map
 * Fog of War causes a Tile to have 3 states:
 *  1. Visible: in line of sight of player's avatar
 *  2. Non-visible: not currently in line of sight of player's avatar, display last seen state of tile
 *  3. Shrouded: never been seen
 * Each Tile must be described by a Terrain, all other properties are optionally generated 'randomly'
 * Notes on drawing:
 *  Controller->View will draw the terrain in ALL circumstances
 *  A Nonvisible tile will draw nothing else
 *  A Visible tile will return 'realDraw' to Controller
 *  A Shrouded tile will return 'playerXDraw' to Controller
 */
public class Tile {
    private AreaEffect areaEffect;
    private Decal decal;
    private Item item;

    private Resource energy;
    private Resource food;
    private Resource ore;

    private Terrain terrain;
    private int xCoordinate;
    private int yCoordinate;
    private Structure structure;


    ArrayList<Unit> units;
    ArrayList<Army> armies;
    ArrayList<Worker> workers;

    public boolean isSelectedPath = false;

    private TileState playerOneDraw;
    private TileState playerTwoDraw;
    private TileState realDraw;

    private enum VisibilityState {
        Visible, NonVisible, Shrouded
    }

    private VisibilityState playerOneVisibility;
    private VisibilityState playerTwoVisibility;

    //a Tile must have a terrain, and an x/y coordinate
    public Tile(Terrain terrain, int xCoordinate, int yCoordinate) {
        this.terrain = terrain;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        units = new ArrayList<>();
        armies = new ArrayList<>();
        workers = new ArrayList<>();


        realDraw = new TileState();
        realDraw.setTerrainType(terrain.getTerrainType());

        populateTileBasedOnTerrain(terrain);

        //copy real state to both players when Tile is initialized
        playerOneDraw = new TileState(realDraw);
        playerTwoDraw = new TileState(realDraw);

        //set Tile enum visibility
        playerOneVisibility = VisibilityState.NonVisible;
        playerTwoVisibility = VisibilityState.NonVisible;
    }

    //check tile terrain to populate Tile components
    private void populateTileBasedOnTerrain(Terrain terrain) {
        //Desert has 10% AreaEffect, 5% Item, 5% Resource
        //Flatland has 20% AreaEffect, 15% Item, 30% Resource
        //Crater has 20% AreaEffect, 5% Item, 25% Resource
        //Mountain has 0% AreaEffect, 0% Item, 0% Resource
        if (terrain instanceof Desert) {
            populateAreaEffect(0.1);
            populateItem(0.05);
            populateResource(0.05, .7, .25);

        } else if (terrain instanceof Flatland) {
            populateAreaEffect(0.2);
            populateItem(0.15);
            populateResource(0.3,.07, .7);
        } else if (terrain instanceof Crater) {
            populateAreaEffect(0.2);
            populateItem(0.05);
            populateResource(0.25, .8, .01);
        } else if (terrain instanceof Mountains) {
            populateAreaEffect(0);
            populateItem(0);
            populateResource(0,0,.99);
        }
    }

    //Populate Resource for each tile
    public void populateResource(double probEnergy, double probOre, double probFood) {
        if (Math.random() < probEnergy) {
            energy = new Energy();
        }
        if (Math.random() < probOre) {
            ore = new Ore();
        }
        if (Math.random() < probFood) {
            food = new Food();
        }
    }

    //Populate Item for each tile
    public void populateItem(double prob) {
        if (Math.random() < prob) {
            int rand = ThreadLocalRandom.current().nextInt(0, 2);
            if (rand == 0)
                setItem(new OneShotItem());

            else if (rand == 1)
                setItem(new Obstacle());
        }
    }

    //TODO figure out if this violate TDA
    //Populate AreaEffect for each tile
    private void populateAreaEffect(double prob) {
        if (Math.random() < prob) {
            int rand = ThreadLocalRandom.current().nextInt(0, terrain.getAreaEffects().size());
            setAreaEffect(terrain.getAreaEffects().get(rand));
        }
    }


    public AreaEffect getAreaEffect() {
        return areaEffect;
    }

    public void setAreaEffect(AreaEffect areaEffect) {
        this.areaEffect = areaEffect;
        realDraw.setAreaEffectType(areaEffect.getType());
        //  realTileState.setAreaEffect(areaEffect);
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
        realDraw.setItemType(item.getType());
        // realTileState.setItem(item);
    }

    public Terrain getTerrain() {
        return terrain;
    }


    public ArrayList<Resource> getResources() {
        ArrayList<Resource> resources = new ArrayList<Resource>();
        resources.add(0, energy);
        resources.add(1, ore);
        resources.add(2, food);
        return  resources;
    }

    public void setResource(Resource resource) {
//        this.resource = resource;
    }


    public int getxCoordinate() {
        return xCoordinate;
    }

    public int getyCoordinate() {
        return yCoordinate;
    }

    //once a Tile is visible to a Player, his Tile state would match the real state
    public void updateTileToVisible(String playerToUpdate) {
        if (playerToUpdate.contains("One")) {
            playerOneVisibility = VisibilityState.Visible;
            playerOneDraw = new TileState(realDraw);

        } else {
            playerTwoVisibility = VisibilityState.Visible;
            playerTwoDraw = new TileState(realDraw);
        }
    }

    //once a Tile is shrouded to a Player, his Tile state should remain in the last seen version
    //a Tile can only become shrouded if it was visible beforehand!
    public void updateTileToShrouded(String playerToUpdate) {
        if (playerToUpdate.contains("One")) {
            if (playerOneVisibility == VisibilityState.Visible) {
                playerOneVisibility = VisibilityState.Shrouded;
            }
        } else {
            if (playerTwoVisibility == VisibilityState.Visible) {
                playerTwoVisibility = VisibilityState.Shrouded;
            }

        }
    }

    public void refreshDrawableState() {
        realDraw.refresh( this);
    }


    public void refreshDrawableState_resources(String s) {
        realDraw.refreshResources(this);
        if(s.contains("One")){
            realDraw.setProspectedByPlayer1(true);
            playerOneDraw.setProspectedByPlayer1(true);
            playerOneDraw.refreshResources(this);
        }
        if(s.contains("Two")){
            realDraw.setProspectedByPlayer2(true);
            playerTwoDraw.setProspectedByPlayer2(true);
            playerTwoDraw.refreshResources(this);
        }
    }

    public void refreshDrawableState_harvestable(String name){
        if(name.contains("One")){
            realDraw.setWorkableByPlayer1(true);
            playerOneDraw.setWorkableByPlayer1(true);
        }
        if(name.contains("Two")){
            realDraw.setWorkableByPlayer2(true);
            playerTwoDraw.setWorkableByPlayer2(true);
        }
    }



    // Adds unit to Tile's ArrayList of Units
    public Unit addUnitToTile(Unit unit) {

        // Physically add the unit
        this.units.add(unit);

        return unit;
    }

    // Removes unit from Tile's ArrayList of Units
    public void removeUnitFromTile(Unit unit) {
        this.units.remove(unit);
                                            // TODO: fix
        if( getDrawableStateByPlayer( unit.getOwner().getName() ) == null ){
            return;
        }


        this.getDrawableStateByPlayer( unit.getOwner().getName() ).decremenUnits(unit );
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
    public void removeWorkerFromTile(Worker worker) {

        this.workers.remove(worker);

        if( getDrawableStateByPlayer( worker.getOwner().getName() ) == null ){
            return;
        }
        this.getDrawableStateByPlayer( worker.getOwner().getName() ).decrementWorkers();

    }

    public Structure getStructure() {
        return structure;
    }

    public void setStructure(Structure structure) {
        this.structure = structure;
        if(structure != null) {
            realDraw.setStructureType(structure.getType());
            realDraw.setStructureStatus(structure.isPowered());
        }
    }

    //called by controller to determine which tile state to draw
    public TileState getDrawableStateByPlayer(String playerName) {
        if(playerName.contains(("real"))) {
            return realDraw;
        }
        if (playerName.contains("One")) {
            switch (playerOneVisibility) {
                case Shrouded:
                    return playerOneDraw;
                case NonVisible:
                    return null;
                case Visible:
                    return realDraw;
            }
        } else {
            switch (playerTwoVisibility) {
                case Shrouded:
                    return playerTwoDraw;
                case NonVisible:
                    return null;
                case Visible:
                    return realDraw;
            }
        }
        return null;
    }

    public ArrayList<Unit> getUnits() {
        return units;
    }

    public boolean getVisible(String name) {
         if(name.equals("One")) {
            return playerOneVisibility == VisibilityState.Visible;
        }
        else
            return playerTwoVisibility == VisibilityState.Visible;

    }

    public boolean getShrouded(String name) {
        if(name.equals("One")) {
            return playerOneVisibility == VisibilityState.Shrouded;
        }
        else
            return playerTwoVisibility == VisibilityState.Shrouded;

    }

    public void markVisible(String name) {
        if(name.equals("One")) {
            playerOneVisibility = VisibilityState.Visible;
            playerOneDraw.refresh(realDraw);

        }
        else {
            playerTwoVisibility = VisibilityState.Visible;
            playerTwoDraw.refresh(realDraw);
        }
        }

    public void markShrouded(String name) {
        if(name.equals("One")) {
            playerOneVisibility = VisibilityState.Shrouded;
            playerOneDraw.refresh(realDraw);
        }
        else {
            playerTwoVisibility = VisibilityState.Shrouded;
            playerTwoDraw.refresh(realDraw);
        }
        }

    public void markHidden(String name) {
        if(name.equals("One")) {
            playerOneVisibility = VisibilityState.NonVisible;
        }
        else
            playerTwoVisibility = VisibilityState.NonVisible;
    }

    public int getOre() {
        if(ore == null) return 0;

        return ore.getStatInfluenceQuantity();
    }

    public int getFood() {
        if(food == null) return 0;

        return food.getStatInfluenceQuantity();
    }

    public int getEnergy() {
        if(energy == null) return 0;

        return energy.getStatInfluenceQuantity();
    }

    public ArrayList<Army> getArmies() {
        return armies;
    }

    public ArrayList<Worker> getWorkers() {
        return workers;
    }

}
