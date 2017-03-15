package com.team7.model;

import com.team7.model.entity.Worker;
import com.team7.model.entity.structure.ObservationTower;
import com.team7.model.entity.structure.Structure;
import com.team7.model.entity.structure.staffedStructure.Capital;
import com.team7.model.entity.unit.Unit;
import com.team7.model.entity.unit.combatUnit.RangedUnit;
import com.team7.model.entity.unit.nonCombatUnit.Explorer;

import java.util.ArrayList;

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
        game.setMap(new Map());
        Player[] resetPlayers = {new Player("One"), new Player("Two")};
        game.setPlayers(resetPlayers);
        game.setCurrentPlayer(game.getPlayers()[1]);


        Player player1 = game.getPlayers()[0];
        Player player2 = game.getPlayers()[1];
        Tile[][] grid = game.getMap().getGrid();

        //add an explorer to both players
        addUnitToPlayer(player1, new Explorer(grid[10][24], player1));
        addUnitToPlayer(player2, new Explorer(grid[28][19], player2));

        //add a ranged unit to player 1
        addUnitToPlayer(player1, new RangedUnit(grid[5][13], player1));


        //give both players a lot of nutrients, power, metal
        for(Player player : game.getPlayers()){
            player.setMetal(1000);
            player.setNutrients(1000);
            player.setPower(1000);
        }


        //add a capital with two workers to both players
            //p1
        Capital p1c = new Capital(grid[4][27], player1);
        addStructureToPlayer(player1, p1c);

        Worker p1w1 = new Worker(grid[4][27], player1);
        Worker p1w2 = new Worker(grid[4][27], player1);
        player1.addWorker(p1w1);
        player1.addWorker(p1w2);
        p1c.addWorkerToStaff(p1w1);
        p1c.addWorkerToStaff(p1w2);

            //p2
        Capital p2c = new Capital(grid[35][27], player2);
        addStructureToPlayer(player2, p2c);

        Worker p2w1 = new Worker(grid[35][27], player2);
        Worker p2w2 = new Worker(grid[35][27], player2);
        player2.addWorker(p2w1);
        player2.addWorker(p2w2);
        p2c.addWorkerToStaff(p2w1);
        p2c.addWorkerToStaff(p2w2);

        //add observation towers to both players
        addStructureToPlayer(player1, new ObservationTower(grid[13][8], player1));
        addStructureToPlayer(player1, new ObservationTower(grid[17][25], player1));
        addStructureToPlayer(player1, new ObservationTower(grid[23][7], player1));

        addStructureToPlayer(player2, new ObservationTower(grid[32][11], player2));
        addStructureToPlayer(player2, new ObservationTower(grid[21][26], player2));




        //advance p1 technologies
        Technologies p1Technologies = player1.getTechnologies();
        ArrayList<Technology> p1workerTech = p1Technologies.getWorkerTechnologies();
        ArrayList<Technology> p1structureTech = p1Technologies.getStructureTechnologies();
        ArrayList<Technology> p1unitTech = p1Technologies.getUnitTechnologies();

        for(Technology t : p1structureTech){
            if (t.getTechnologyInstance().equals("ObservationTower") && t.getTechnologyStat().equals("VisibilityRadius")){
                t.incrementTechnologyLevel();
                t.incrementTechnologyLevel();
            }
            if (t.getTechnologyInstance().equals("Capital") && t.getTechnologyStat().equals("VisibilityRadius")){
                t.incrementTechnologyLevel();
                t.incrementTechnologyLevel();
            }
        }


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

        game.updateCurrPlayerTileStates();

    }

    // create a unit, add to player, place on map at [x][y]
    public void addUnitToPlayer(Player player, Unit unit) {
        player.addUnit(unit);
    }

    //create structure
    public void addStructureToPlayer(Player player, Structure structure){
        player.addStructure(structure);
    }

}
