package com.team7.model;

import com.team7.controller.PathSelectController;
import com.team7.model.entity.structure.ObservationTower;
import com.team7.model.entity.unit.Unit;
import com.team7.model.entity.unit.combatUnit.MeleeUnit;
import com.team7.model.entity.unit.combatUnit.RangedUnit;
import com.team7.model.entity.unit.nonCombatUnit.Colonist;
import com.team7.model.entity.unit.nonCombatUnit.Explorer;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Game {

    private Player[] players = new Player[2];
    private Map map;
    private int turn;
    private Player currentPlayer;

    public Game(Player p1, Player p2) {
        players[0] = p1;
        players[1] = p2;
        turn = 0;
        currentPlayer = players[0];
    }

    public void newGameState() {

        // create map and populate with items/resources/area effects
        this.map = new Map();

        //TODO: fix
        // create Player One starting units
        allocateUnitToPlayer( players[0], new Explorer(this.map.getGrid()[5][7], players[0]) );
        allocateUnitToPlayer( players[0], new MeleeUnit(this.map.getGrid()[5][22], players[0]) );
        allocateUnitToPlayer( players[0], new MeleeUnit(this.map.getGrid()[1][14], players[0]) );
        allocateUnitToPlayer( players[0], new Colonist(this.map.getGrid()[10][20], players[0]) );
        allocateUnitToPlayer( players[0], new RangedUnit(this.map.getGrid()[10][30], players[0]) );
        // create Player Two starting units
        allocateUnitToPlayer( players[1], new Explorer(this.map.getGrid()[40-12][40-9],  players[1]) );
        allocateUnitToPlayer( players[1], new MeleeUnit(this.map.getGrid()[40-7][40-25], players[1]) );
        allocateUnitToPlayer( players[1], new MeleeUnit(this.map.getGrid()[40-5][40-15], players[1]) );
        allocateUnitToPlayer( players[1], new Colonist(this.map.getGrid()[40-15][10],  players[1]) );
        allocateUnitToPlayer( players[1], new RangedUnit(this.map.getGrid()[28][15], players[1]) );

        updateTileGameState();
    }


    // create a unit, add to player, place on map at [x][y]
    public void allocateUnitToPlayer(Player player, Unit unit) {
        player.addUnit(unit);
    }


    //called at the end of each turn and everytime gamestate changes (namely movement)
    //1. find all tiles currently visited by current player, along with the radius
    //2. use radius to collect a set of all visible tiles to current player
    //3. iterate through game map to mark tiles correctly
    public void updateTileGameState(){

        HashMap<Tile, Integer> currentPlayerTileRadiusMap = currentPlayer.getAllTileRadiusMap();
        Set<Tile> visibleTiles = new HashSet<>();
        //1
        for (Tile tileKey: currentPlayerTileRadiusMap.keySet()){
            visibleTiles.addAll(map.getTilesInRadius(tileKey, currentPlayerTileRadiusMap.get(tileKey), null));
        }
        //2
        //iterate through entire map and update each tile
        for (Tile[] tileArray : map.getGrid()) {
            for (Tile tile : tileArray) {
                //3
                if( visibleTiles.contains(tile ) ) {
                    tile.markVisible(currentPlayer.getName());
                }
                else if(tile.getVisible(currentPlayer.getName()) && !visibleTiles.contains( tile ) && PathSelectController.isRecording) {
                   tile.markShrouded( currentPlayer.getName() );
                }
                tile.refreshDrawableState();
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

        updateTileGameState();
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
