package com.team7.model;

import com.team7.model.entity.structure.ObservationTower;
import com.team7.model.entity.unit.nonCombatUnit.Explorer;

/**
 * class that forwards the game state tremendously
 * violates OOP principles (LOD/TDA) in order to set everything in the model
 */
public class DemoGameMode {
    Game game;

    public DemoGameMode(Game game){
        this.game = game;
    }


    //adds and changes everything!!!
    public void activate(){
        //first reset game to default state
        game.newGameState();

        Player player1 = game.getPlayers()[0];
        Player player2 = game.getPlayers()[1];

        //give both players a lot of nutrients, power, metal
        for(Player player : game.getPlayers()){
            player.setMetal(1000);
            player.setNutrients(1000);
            player.setPower(1000);
            player.addStructure(new ObservationTower(game.getMap().getGrid()[12][12], player));
        }


        ObservationTower obsv1 = new ObservationTower(game.getMap().getGrid()[5][5], player1);
        player1.addStructure(obsv1);
        //give both players observation towers throughout the map
        //player1.addStructure(new ObservationTower(game.getMap().getGrid()[5][5], player1));
        player1.addStructure(new ObservationTower(game.getMap().getGrid()[12][12], player1));
        player1.addStructure(new ObservationTower(game.getMap().getGrid()[25][25], player1));

        player2.addStructure(new ObservationTower(game.getMap().getGrid()[5][5], player2));
        player2.addStructure(new ObservationTower(game.getMap().getGrid()[12][12], player2));
        player2.addStructure(new ObservationTower(game.getMap().getGrid()[25][25], player2));




        player1.addUnit(new Explorer(game.getMap().getGrid()[21][22], player1));
        player1.addUnit(new Explorer(game.getMap().getGrid()[31][30], player1));
        player1.addUnit(new Explorer(game.getMap().getGrid()[30][30], player1));
        //have player1 be much better than player 2

        //player 1
        //advance observation radius technology to 3
        //advance worker work radius technology to 3
        //advance ranged unit attack strength to level 3, show that melee is still at 1
        //create explorer with very large movement and prospecting
        //show a food resource renewing


        //player 2
        //advance observation radius technology to 2
        //worker work radius technology stays at 0
        //create university, study work radius
        //create explorer without prospecting, move him through instant death

    }
}
