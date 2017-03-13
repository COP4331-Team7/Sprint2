package com.team7.tests;

import com.team7.model.Map;
import com.team7.model.Player;
import com.team7.model.entity.unit.Unit;
import com.team7.model.entity.unit.nonCombatUnit.Colonist;
import com.team7.model.entity.unit.nonCombatUnit.Explorer;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PlayerTests {

    @Test
    // Test that a Player can't add more than 10 units of the same type
    public void checkMaxTypes() throws Exception {

        // create map and player
        Map map = new Map();
        Player testPlayer = new Player("playerOne");

        // attempt to add 15 players
        for(int i = 0; i < 15; i++){
            testPlayer.addUnit(new Colonist(map.getGrid()[0][0], testPlayer));
        }

        //check that only 10 players are added
        int numberColonists = testPlayer.getUnits().size();

        assertEquals(10, numberColonists);

    }



    @Test
    // Create a colonist, make a capital, remove it and see player lose
    public void checkPlayerDefeated() throws Exception {

        // create map and player
        Map map = new Map();
        Player testPlayer = new Player("playerOne");

        // create colonist and base
        Unit colonist = new Colonist(map.getGrid()[0][0], testPlayer);
        testPlayer.addUnit(colonist);
        ((Colonist) colonist).buildCapital();

        // Test removing a worker and that the player is not defeated yet
        assertEquals(testPlayer.getWorkers().size(), 5);
        testPlayer.removeWorker(testPlayer.getWorkers().remove(0));
        assertEquals(testPlayer.getWorkers().size(), 4);
        assertEquals(testPlayer.getStructures().size(), 1);
        assertEquals(testPlayer.isDefeated(), false);

        // remove capital and see if player lost
        testPlayer.removeStructure(testPlayer.getStructures().get(0));

        assertEquals(testPlayer.isDefeated(), true);

    }

    @Test
    // Create a colonist, make a capital, remove it and see player lose
    public void checkIDs() throws Exception {

        // create map and player
        Map map = new Map();
        Player testPlayer1 = new Player("playerOne");
        Player testPlayer2 = new Player("playerTwo");

        // create colonists
        Unit colonist0 = new Colonist(map.getGrid()[0][0], testPlayer1);
        testPlayer1.addUnit(colonist0);
        Unit colonist1 = new Colonist(map.getGrid()[0][0], testPlayer1);
        testPlayer1.addUnit(colonist1);
        Unit explorer0 = new Explorer(map.getGrid()[0][0], testPlayer1);
        testPlayer1.addUnit(explorer0);

        assertEquals(testPlayer1.getUnits().get(0).getId(), 0);
        assertEquals(testPlayer1.getUnits().get(1).getId(), 1);
        assertEquals(testPlayer1.getUnits().get(2).getId(), 0);

//        Unit colonist2 = new Colonist(map.getGrid()[0][0], testPlayer1);
//        testPlayer1.addUnit(colonist2);
//        Unit colonist3 = new Colonist(map.getGrid()[0][0], testPlayer1);
//        testPlayer1.addUnit(colonist3);
//        Unit colonist4 = new Colonist(map.getGrid()[0][0], testPlayer1);
//        testPlayer1.addUnit(colonist4);
//        Unit colonist5 = new Colonist(map.getGrid()[0][0], testPlayer1);
//        testPlayer1.addUnit(colonist5);
//
//
//
//        assertEquals(testPlayer1.getUnits().get(0).getId(), 0);
//        assertEquals(testPlayer1.getUnits().get(1).getId(), 1);
//        assertEquals(testPlayer1.getUnits().get(2).getId(), 2);
//        assertEquals(testPlayer1.getUnits().get(3).getId(), 3);
//        assertEquals(testPlayer1.getUnits().get(4).getId(), 4);
//
//        testPlayer1.removeUnit(testPlayer1.getUnits().get(1));
//        assertEquals(testPlayer1.getUnits().get(0).getId(), 0);
//        assertEquals(testPlayer1.getUnits().get(1).getId(), 2);
//        assertEquals(testPlayer1.getUnits().get(2).getId(), 3);
//        assertEquals(testPlayer1.getUnits().get(3).getId(), 4);
//
//        testPlayer1.removeUnit(testPlayer1.getUnits().get(1));
//        testPlayer1.removeUnit(testPlayer1.getUnits().get(2));
//        testPlayer1.removeUnit(testPlayer1.getUnits().get(0));
//        assertEquals(testPlayer1.getUnits().size(), 1);
//        assertEquals(testPlayer1.getUnits().get(0).getId(), 3);
//
//
//        Unit colonist6 = new Colonist(map.getGrid()[0][0], testPlayer1);
//        testPlayer1.addUnit(colonist6);
//
//        Unit colonist7 = new Colonist(map.getGrid()[0][0], testPlayer2);
//        testPlayer2.addUnit(colonist7);
//        Unit colonist8 = new Colonist(map.getGrid()[0][0], testPlayer2);
//        testPlayer2.addUnit(colonist8);
//
//        assertEquals(testPlayer2.getUnits().get(0).getId(), 0);
//        assertEquals(testPlayer2.getUnits().get(1).getId(), 1);
//
//
//        assertEquals(testPlayer1.getUnits().get(1).getId(), 0);

    }


    // TODO: add test for checkUnitArmyStructs

}
