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
    private Resource resource;
    private Terrain terrain;
    private int xCoordinate;
    private int yCoordinate;
    private Structure structure;

    public ArrayList<Unit> getUnits() {
        return units;
    }

    ArrayList<Unit> units;
    ArrayList<Army> armies;
    ArrayList<Worker> workers;


    public boolean isVisible = false;
    public boolean isSelectedPath = false;


    private DrawableTileState playerOneDraw;
    private DrawableTileState playerTwoDraw;
    private DrawableTileState realDraw;

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


        realDraw = new DrawableTileState();
        realDraw.setTerrainType(terrain.getTerrainType());

        populateTileBasedOnTerrain(terrain);


        //copy real state to both players when Tile is initialized
        playerOneDraw = new DrawableTileState(realDraw);
        playerTwoDraw = new DrawableTileState(realDraw);

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
            populateResource(0.05);

        } else if (terrain instanceof Flatland) {
            populateAreaEffect(0.2);
            populateItem(0.15);
            populateResource(0.3);
        } else if (terrain instanceof Crater) {
            populateAreaEffect(0.2);
            populateItem(0.05);
            populateResource(0.25);
        } else if (terrain instanceof Mountains) {
            populateAreaEffect(0);
            populateItem(0);
            populateResource(0);
        }
    }


    //Populate Resource for each tile
    public void populateResource(double prob) {
        if (ProbabilityGenerator.willOccur(prob)) {
            int rand = ProbabilityGenerator.randomInteger(0, 2);
            if (rand == 0)
                setResource(new Energy());
            else if (rand == 1)
                setResource(new Food());
            else if (rand == 2)
                setResource(new Ore());
        }
    }

    //Populate Item for each tile
    public void populateItem(double prob) {
        if (ProbabilityGenerator.willOccur(prob)) {
            int rand = ProbabilityGenerator.randomInteger(0, 1);
            if (rand == 0)
                setItem(new OneShotItem());

            else if (rand == 1)
                setItem(new Obstacle());
        }
    }

    //TODO figure out if this violate TDA
    //Populate AreaEffect for each tile
    private void populateAreaEffect(double prob) {
        if (ProbabilityGenerator.willOccur(prob)) {
            int rand = ProbabilityGenerator.randomInteger(0, terrain.getAreaEffects().size() - 1);
            setAreaEffect(terrain.getAreaEffects().get(rand));
        }
    }

    //Structure will only interact with Tile for its Resource
    //called for each Tile in the Structure's available radius
    //TODO determine how harvesting will work: via structure? worker? resource itself?
    public int structureInteractWithTileForResource(int quantityOfResourceToHarvest) {
        if (resource != null) {
            int resourceQuantity = resource.getStatInfluenceQuantity();
            resource.changeResourceQuantity(0-quantityOfResourceToHarvest);
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


    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
        realDraw.setResourceType(resource.getType());
        realDraw.setResourceQuantity(String.valueOf(resource.getStatInfluenceQuantity()));
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
            playerOneDraw = new DrawableTileState(realDraw);

        } else {
            playerTwoVisibility = VisibilityState.Visible;
            playerTwoDraw = new DrawableTileState(realDraw);
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


    // Adds unit to Tile's ArrayList of Units
    public Unit addUnitToTile(Unit unit) {

        // Physically add the unit
        this.units.add(unit);
        realDraw.incrementPlayerOneUnits(1);
        realDraw.incrementPlayerTwoUnits(1);


        return unit;
    }

    // Removes unit from Tile's ArrayList of Units
    public void removeUnitFromTile(Unit unit) {
        this.units.remove(unit);
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
        realDraw.setStructureType(structure.getType());
        realDraw.setStructureStatus(structure.isPowered());
    }

    //called by controller to determine which tile state to draw
    public DrawableTileState getDrawableStateByPlayer(String playerName) {
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

    public boolean getVisible(String name) {
         if(name == "One") {
            return playerOneVisibility == VisibilityState.Visible;
        }
        else
            return playerTwoVisibility == VisibilityState.Visible;

    }

    public boolean getShrouded(String name) {
        if(name == "One") {
            return playerOneVisibility == VisibilityState.Shrouded;
        }
        else
            return playerTwoVisibility == VisibilityState.Shrouded;

    }

    public void markVisible(String name) {
        if(name == "One") {
            playerOneVisibility = VisibilityState.Visible;
            playerOneDraw.refresh(realDraw);
        }
        else {
            playerTwoVisibility = VisibilityState.Visible;
            playerTwoDraw.refresh(realDraw);
        }
        }

    public void markShrouded(String name) {
        if(name == "One") {
            playerOneVisibility = VisibilityState.Shrouded;
            playerOneDraw.refresh(realDraw);
        }
        else {
            playerTwoVisibility = VisibilityState.Shrouded;
            playerTwoDraw.refresh(realDraw);
        }
        }

    public void markHidden(String name) {
        if(name == "One") {
            playerOneVisibility = VisibilityState.NonVisible;
        }
        else
            playerTwoVisibility = VisibilityState.NonVisible;
    }
}
