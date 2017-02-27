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

        // attempt to add 11 players
        for(int i = 0; i < 11; i++){
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

        assertEquals(testPLayer.getWorkers().size(), 5);
        assertEquals(testPLayer.isDefeated(), false);

        // remove capital and see if player lost
        testPLayer.removeStructure(testPLayer.getStructures().get(0));
        assertEquals(testPLayer.isDefeated(), true);

    }


}
