package com.team7.model;

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
//         map.setMapDetails();
//
        // players[0].addUnit(new Explorer(this.map.getGrid()[46][3], players[0]));
        // players[0].addUnit(new Explorer(this.map.getGrid()[48][4], players[0]));
        // players[0].addUnit(new Colonist(this.map.getGrid()[45][4], players[0]));

        // players[1].addUnit(new Explorer(this.map.getGrid()[3][45], players[1]));
        // players[1].addUnit(new Explorer(this.map.getGrid()[47][4], players[1]));
        // players[1].addUnit(new Colonist(this.map.getGrid()[46]3], players[1]));



    }


    //Switches the turn to the next player
    public void nextTurn() {
        //executeQueues();
        //currentPlayer.takeTurn();

        if(currentPlayer.isDefeated() == true){
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
