package com.team7.model;

import com.team7.controller.PathSelectController;
import com.team7.model.entity.Army;
import com.team7.model.entity.Command;
import com.team7.model.entity.Worker;
import com.team7.model.entity.structure.ObservationTower;
import com.team7.model.entity.structure.Structure;
import com.team7.model.entity.structure.staffedStructure.Capital;
import com.team7.model.entity.structure.staffedStructure.Fort;
import com.team7.model.entity.structure.staffedStructure.University;
import com.team7.model.entity.structure.staffedStructure.singleHarvestStructure.Farm;
import com.team7.model.entity.unit.Unit;
import com.team7.model.entity.unit.combatUnit.MeleeUnit;
import com.team7.model.entity.unit.combatUnit.RangedUnit;
import com.team7.model.entity.unit.nonCombatUnit.Colonist;
import com.team7.model.entity.unit.nonCombatUnit.Explorer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class Game {

    private Player[] players = null;
    private Map map;
    private int turn;
    private Player currentPlayer;

    public Game() {
        turn = 0;
    }

    public void newGameState() {

        // create two players
        this.players = new Player[]{
                                     new Player("One"),
                                     new Player("Two")
                                    };


        // create map and populate it with items/resources/area effects
        this.map = new Map();

        currentPlayer = players[0];
        turn = 0;

        //TODO: fix
        // set Player One starting units
        addUnitToPlayer( players[0], new Explorer(this.map.getGrid()[5][7], players[0]) );
        //addUnitToPlayer( players[0], new Explorer(this.map.getGrid()[5][10], players[0]) );
        addUnitToPlayer( players[0], new MeleeUnit(this.map.getGrid()[5][22], players[0]) );
        addUnitToPlayer( players[0], new MeleeUnit(this.map.getGrid()[1][14], players[0]) );
        addUnitToPlayer( players[0], new Colonist(this.map.getGrid()[10][20], players[0]) );
        addUnitToPlayer( players[0], new RangedUnit(this.map.getGrid()[10][30], players[0]) );
        // set Player Two starting units
        addUnitToPlayer( players[1], new Explorer(this.map.getGrid()[40-12][40-9],  players[1]) );
       // addUnitToPlayer( players[1], new Explorer(this.map.getGrid()[40-12][40-5],  players[1]) );
        addUnitToPlayer( players[1], new MeleeUnit(this.map.getGrid()[40-7][40-25], players[1]) );
        addUnitToPlayer( players[1], new MeleeUnit(this.map.getGrid()[40-5][40-15], players[1]) );
        addUnitToPlayer( players[1], new Colonist(this.map.getGrid()[40-15][10],  players[1]) );
        addUnitToPlayer( players[1], new RangedUnit(this.map.getGrid()[28][15], players[1]) );


        //adding all types of structure to p1 for testing
        Structure c1 = new Capital(map.getGrid()[25][20],  players[0]);
        Structure university = new University(map.getGrid()[20][20], players[0]);
        Structure obsTower = new ObservationTower(map.getGrid()[20][15], players[0]);
        Structure fort = new Fort(map.getGrid()[20][25], players[0]);
        Structure farm = new Farm(map.getGrid()[15][20], players[0]);
       // Structure farm2 = new Farm(map.getGrid()[16][20], players[0]);

        players[0].addStructure(c1);
        players[0].addStructure(university);
        players[0].addStructure(obsTower);
        players[0].addStructure(fort);
        players[0].addStructure(farm);
        //players[0].addStructure(farm2);


        Worker w1 = new Worker(map.getGrid()[25][20], players[0]);
        addWorkerToPlayer(players[0], w1);
    

        Army army0 = new Army(map.getGrid()[1][30],  players[0]);
        Army army1 = new Army(map.getGrid()[1][29],  players[1]);
        Army army2 = new Army(map.getGrid()[1][31],  players[1]);

        players[0].addArmy(army0);
        players[1].addArmy(army1);
        players[1].addArmy(army2);


        Unit melee1 = new  MeleeUnit(this.map.getGrid()[1][30], players[0]);
        Unit melee2 = new  MeleeUnit(this.map.getGrid()[1][29], players[1]);
        Unit melee3 = new  MeleeUnit(this.map.getGrid()[1][31], players[1]);


        addUnitToPlayer( players[0], melee1 );
        addUnitToPlayer( players[1], melee2 );
        addUnitToPlayer( players[1], melee3 );


        army0.addUnitToArmy(melee1);
        army1.addUnitToArmy(melee2);
        army2.addUnitToArmy(melee3);

        players[0].addArmy( army0 );
        players[1].addArmy( army1 );
        players[1].addArmy( army2 );

        army0.queueCommand(new Command("attack 1"));

        updateCurrPlayerTileStates();  // update tile states so view renders accordingly
    }


    // create a unit, add to player, place on map at [x][y]
    public void addUnitToPlayer(Player player, Unit unit) {
        player.addUnit(unit);
    }

    public void addWorkerToPlayer(Player player, Worker worker){
        player.addWorker(worker);
    }

    public void addStructureToPlayer(Player player, Structure structure){
        player.addStructure(structure);
    }

    public void addArmyToPlayer(Player player, Army army){
        player.addArmy(army);
    }

    //called at the end of each turn and everytime gamestate changes (namely movement)
    //1. find all tiles currently visited by current player, along with the radius
    //2. use radius to collect a set of all visible tiles to current player
    //3. iterate through game map to mark tiles correctly
    public void updateCurrPlayerTileStates(){

        HashMap<Tile, Integer> currentPlayerTileRadiusMap = currentPlayer.getAllTileRadiusMap();
        HashMap<Tile, Integer> currentPlayerProspectedTiles = currentPlayer.getAllProspectedTile();
        HashMap<Tile, Integer> currentPlayerHarvestableTiles = currentPlayer.getAllHarvestableTilesForUnassignedWorkers();

        Set<Tile> visibleTiles = new HashSet<>();
        Set<Tile> prospectedTiles = new HashSet<>();
        Set<Tile> harvestableTiles = new HashSet<>();
        //1
        for (Tile tileKey: currentPlayerTileRadiusMap.keySet()){
            visibleTiles.addAll(map.getTilesInRadius(tileKey, currentPlayerTileRadiusMap.get(tileKey), null));
        }
        for (Tile tileKey: currentPlayerProspectedTiles.keySet()){
            prospectedTiles.addAll(map.getTilesInRadius(tileKey, currentPlayerProspectedTiles.get(tileKey), null));
        }
        for (Tile tileKey : currentPlayerHarvestableTiles.keySet()){
            harvestableTiles.addAll(map.getTilesInRadius(tileKey, currentPlayerHarvestableTiles.get(tileKey), null));
        }

        //2
        //iterate through entire map and update each tile
        for (Tile[] tileArray : map.getGrid()) {
            for (Tile tile : tileArray) {
                //3

                tile.refreshDrawableState();    // update realDraw

                if (harvestableTiles.contains(tile)){
                    tile.refreshDrawableState_harvestable(currentPlayer.getName());
                }

                if( prospectedTiles.contains( tile )  ) {
                    tile.refreshDrawableState_resources( currentPlayer.getName() );
                }

                if( visibleTiles.contains(tile ) ) {

                    tile.markVisible(currentPlayer.getName());

                }
                else if(tile.getVisible(currentPlayer.getName()) && !visibleTiles.contains( tile ) && PathSelectController.isRecording) {
                    tile.markShrouded( currentPlayer.getName() );
                }

            }
        }
    }

    //Switches the turn to the next player
    public void nextTurn() {

        if(currentPlayer == players[0])
            currentPlayer = players[1];
        else
            currentPlayer = players[0];

        turn = ++turn % 2;

        System.out.println(" ");
        currentPlayer.takeTurn();
        executeCommandQueues();
        applyAllTechnologies();
        removeDeadUnits();
        updateCurrPlayerTileStates();
    }

    private void applyAllTechnologies() {
        for(Player p : players){
            p.applyAllTechnologies();
        }
    }

    public void removeDeadUnits() {

        ArrayList<Unit> p1_units = players[0].getUnits();
        ArrayList<Unit> p2_units = players[1].getUnits();
        ArrayList<Unit> all_units = new ArrayList<>(p1_units);
        all_units.addAll(p2_units);

        for(Unit u : all_units)
            if(!u.isAlive())
                u.getOwner().removeUnit( u );

    }


    public void executeCommandQueues() {

        ArrayList<Unit> p1_units = players[0].getUnits();
        ArrayList<Unit> p2_units = players[1].getUnits();
        ArrayList<Unit> all_units = new ArrayList<>(p1_units);
        all_units.addAll( p2_units );

        ArrayList<Structure> p1_structures = players[0].getStructures();
        ArrayList<Structure> p2_structures = players[1].getStructures();
        ArrayList<Structure> all_structures = new ArrayList<>(p1_structures);
        all_structures.addAll( p2_structures );

        ArrayList<Army> p1_armies = players[0].getArmies();
        ArrayList<Army> p2_armies = players[1].getArmies();
        ArrayList<Army> all_armies = new ArrayList<>(p1_armies);
        all_armies.addAll( p2_armies );

        // execute queues of all units in game
        // some commands won't finish executing within 1 tick, they update & remain in queue
        System.out.println("\nUNITS:");
        for(Unit u : all_units) {
            u.printCommandQueue();
            u.executeCommandQueue();
        }

        System.out.println("\nSTRUCTURES:");
        for(Structure s : all_structures) {
            s.printCommandQueue();
            s.executeCommandQueue();
        }

        System.out.println("\nARMIES:");
        for(Army a : all_armies) {
            a.printCommandQueue();
            // s.executeCommandQueue();
        }


    }

    public void printCommandQueues() {
        for(Unit u : currentPlayer.getUnits()) {
            u.printCommandQueue();
        }
    }


    //Get the current player
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    //Get the current turn
    public int getTurn() {
        return turn;
    }

    //Get the map
    public Map getMap() {
        return map;
    }

    //Get list of players
    public Player[] getPlayers() {
        return players;
    }

    public void setTurn(int num) {
        turn = num;
    }

    public void endGame() {

        for(int i = 0; i < 10; i++)
            System.out.println("GAME OVER!!!!" );

        /*   --TODO--
        Display game over splash screen */

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.exit(0);
    }


}
