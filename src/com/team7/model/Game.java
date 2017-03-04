package com.team7.model;

import com.team7.model.entity.unit.nonCombatUnit.Colonist;
import com.team7.model.entity.unit.nonCombatUnit.Explorer;

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
         players[0].addUnit(new Explorer(this.map.getGrid()[map.getGrid().length - 3][3], players[0]));
         players[0].addUnit(new Explorer(this.map.getGrid()[map.getGrid().length - 4][4], players[0]));
         players[0].addUnit(new Colonist(this.map.getGrid()[map.getGrid().length - 5][4], players[0]));

        players[0].addUnit(new Explorer(this.map.getGrid()[20][20], players[1]));


        players[1].addUnit(new Explorer(this.map.getGrid()[3][map.getGrid().length - 3], players[1]));
         players[1].addUnit(new Explorer(this.map.getGrid()[4][map.getGrid().length - 4], players[1]));
         players[1].addUnit(new Colonist(this.map.getGrid()[4][map.getGrid().length - 5], players[1]));

    }

    //Switches the turn to the next player
    public void nextTurn() {
        //executeQueues();
        //currentPlayer.takeTurn();

        //TODO update tile states:
        //currentPlayer.getAllCurrentTilesWithRadius <-- stored in HashMap<Tile, Radius>
        //for each entry in HashMap
        //  get Set<Tile> of tiles in radius
        //  append Sets to represent ALL visible tiles for currentPlayer
        //for each Tile in GAME MAP
        //  if set contains gamemap[i]
        //      gamemap[i].updateToVisible(currentPlayer.getName)
        //  else
        //      gamemap[i].updateToShrouded(currentPlayer.getName)


        if(currentPlayer.isDefeated()){
            endGame();
        }

        if(currentPlayer == players[0])
            currentPlayer = players[1];
        else
            currentPlayer = players[0];

        turn = ++turn % 2;
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
        Display a game over splash screen and exit the program, gunna wait until the GUI is integrated
        to be able to do this. */

        System.exit(0);

    }

}
