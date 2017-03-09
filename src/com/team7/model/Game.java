package com.team7.model;

import com.team7.controller.PathSelectController;
import com.team7.model.entity.structure.ObservationTower;
import com.team7.model.entity.unit.Unit;
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

    public void startGame() {

        // create map and populate with items/resources/area effects
        this.map = new Map();

        //TODO check if this violates TDA
        players[0].addUnit(new Explorer(this.map.getGrid()[4][7], players[0]));
        players[0].addUnit(new Explorer(this.map.getGrid()[5][19], players[0]));
        players[0].addUnit(new Colonist(this.map.getGrid()[1][14], players[0]));

        // players[0].addObservationTower(new ObservationTower(this.map.getGrid()[20][20], players[0]));
        //players[0].addObservationTower(new ObservationTower(this.map.getGrid()[35][5], players[0]));
        players[1].addUnit(new Explorer(this.map.getGrid()[40-8][40-7], players[1]));
        players[1].addUnit(new Explorer(this.map.getGrid()[40-7][40-19], players[1]));
        players[1].addUnit(new Colonist(this.map.getGrid()[40-5][40-10], players[1]));


        updateTileGameState();
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
            visibleTiles.addAll(map.getTilesInRadius(tileKey, 2, null));
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
        //executeQueues();
//        if(currentPlayer.isDefeated()){
//            endGame();
//        }

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

    public void setCurrentPlayer(int num) {
        if (num > players.length-1) {
            System.out.println("ERROR: Out of bounds request for setCurrentPlayer()");
            return;
        }
        currentPlayer = players[num];
    }


    public void endGame() {

        System.out.println("GAME OVER!!!!" );

        /*   --TODO--
        Display game over splash screen */

        System.exit(0);
    }

}
