package com.team7.tests;

import com.team7.model.Map;
import com.team7.model.Player;
import com.team7.model.entity.unit.Unit;
import com.team7.model.entity.unit.nonCombatUnit.Colonist;
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
        Player testPlayer = new Player("playerOne");

        // create colonists
        Unit colonist1 = new Colonist(map.getGrid()[0][0], testPlayer);
        testPlayer.addUnit(colonist1);
        Unit colonist2 = new Colonist(map.getGrid()[0][0], testPlayer);
        testPlayer.addUnit(colonist2);
        Unit colonist3 = new Colonist(map.getGrid()[0][0], testPlayer);
        testPlayer.addUnit(colonist3);
        Unit colonist4 = new Colonist(map.getGrid()[0][0], testPlayer);
        testPlayer.addUnit(colonist4);
        Unit colonist5 = new Colonist(map.getGrid()[0][0], testPlayer);
        testPlayer.addUnit(colonist5);



        assertEquals(testPlayer.getUnits().get(0).getId(), 0);
        assertEquals(testPlayer.getUnits().get(1).getId(), 1);
        assertEquals(testPlayer.getUnits().get(2).getId(), 2);
        assertEquals(testPlayer.getUnits().get(3).getId(), 3);
        assertEquals(testPlayer.getUnits().get(4).getId(), 4);

        testPlayer.removeUnit(testPlayer.getUnits().get(1));
        assertEquals(testPlayer.getUnits().get(0).getId(), 0);
        assertEquals(testPlayer.getUnits().get(1).getId(), 2);
        assertEquals(testPlayer.getUnits().get(2).getId(), 3);
        assertEquals(testPlayer.getUnits().get(3).getId(), 4);

        testPlayer.removeUnit(testPlayer.getUnits().get(1));
        testPlayer.removeUnit(testPlayer.getUnits().get(1));
        testPlayer.removeUnit(testPlayer.getUnits().get(1));
        assertEquals(testPlayer.getUnits().size(), 1);

        Unit colonist6 = new Colonist(map.getGrid()[0][0], testPlayer);
        testPlayer.addUnit(colonist6);

        assertEquals(testPlayer.getUnits().get(1).getId(), 1);

    }


    // TODO: add test for checkUnitArmyStructs

}
