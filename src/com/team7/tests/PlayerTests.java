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
        Player testPLayer = new Player();

        // attempt to add 15 players
        for(int i = 0; i < 15; i++){
            testPLayer.addUnit(new Colonist(map.getGrid()[0][0], testPLayer));
        }

        //check that only 10 players are added
        int numberColonists = testPLayer.getUnits().size();

        assertEquals(10, numberColonists);

    }



    @Test
    // Create a colonist, make a capital, remove it and see player lose
    public void checkPlayerDefeated() throws Exception {

        // create map and player
        Map map = new Map();
        Player testPLayer = new Player();

        // create colonist and base
        Unit colonist = new Colonist(map.getGrid()[0][0], testPLayer);
        testPLayer.addUnit(colonist);
        ((Colonist) colonist).buildCapital();

        // Test removing a worker and that the player is not defeated yet
        assertEquals(testPLayer.getWorkers().size(), 5);
        testPLayer.removeWorker(testPLayer.getWorkers().remove(0));
        assertEquals(testPLayer.getWorkers().size(), 4);
        assertEquals(testPLayer.isDefeated(), false);

        // remove capital and see if player lost
        testPLayer.removeStaffedStructure(testPLayer.getStaffedStructures().get(0));
        assertEquals(testPLayer.isDefeated(), true);

    }

    @Test
    // Create a colonist, make a capital, remove it and see player lose
    public void checkIDs() throws Exception {

        // create map and player
        Map map = new Map();
        Player testPLayer = new Player();

        // create colonists
        Unit colonist1 = new Colonist(map.getGrid()[0][0], testPLayer);
        testPLayer.addUnit(colonist1);
        Unit colonist2 = new Colonist(map.getGrid()[0][0], testPLayer);
        testPLayer.addUnit(colonist2);
        Unit colonist3 = new Colonist(map.getGrid()[0][0], testPLayer);
        testPLayer.addUnit(colonist3);
        Unit colonist4 = new Colonist(map.getGrid()[0][0], testPLayer);
        testPLayer.addUnit(colonist4);
        Unit colonist5 = new Colonist(map.getGrid()[0][0], testPLayer);
        testPLayer.addUnit(colonist5);



        assertEquals(testPLayer.getUnits().get(0).getId(), 0);
        assertEquals(testPLayer.getUnits().get(1).getId(), 1);
        assertEquals(testPLayer.getUnits().get(2).getId(), 2);
        assertEquals(testPLayer.getUnits().get(3).getId(), 3);
        assertEquals(testPLayer.getUnits().get(4).getId(), 4);

        testPLayer.removeUnit(testPLayer.getUnits().get(1));
        assertEquals(testPLayer.getUnits().get(0).getId(), 0);
        assertEquals(testPLayer.getUnits().get(1).getId(), 2);
        assertEquals(testPLayer.getUnits().get(2).getId(), 3);
        assertEquals(testPLayer.getUnits().get(3).getId(), 4);

        testPLayer.removeUnit(testPLayer.getUnits().get(1));
        testPLayer.removeUnit(testPLayer.getUnits().get(1));
        testPLayer.removeUnit(testPLayer.getUnits().get(1));
        assertEquals(testPLayer.getUnits().size(), 1);

        Unit colonist6 = new Colonist(map.getGrid()[0][0], testPLayer);
        testPLayer.addUnit(colonist6);

        assertEquals(testPLayer.getUnits().get(1).getId(), 1);

    }


}
