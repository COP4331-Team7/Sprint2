package com.team7.tests;

import com.team7.model.Map;
import com.team7.model.Player;
import com.team7.model.entity.Army;
import com.team7.model.entity.Attacker;
import com.team7.model.entity.unit.Unit;
import com.team7.model.entity.unit.combatUnit.MeleeUnit;
import com.team7.model.entity.unit.combatUnit.RangedUnit;
import com.team7.model.entity.unit.nonCombatUnit.Colonist;
import com.team7.model.entity.unit.nonCombatUnit.Explorer;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
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


    }


    @Test
    // Tests that if a unit's health is 0 that it is deleted from army and player array and from Tile
    // function checkUnitArmyStructs does a sweep to remove all dead things
    public void testCheckUnitArmyStructs() throws Exception {

        // create map, player, unit, army structure
        Map map = new Map();
        Player testPLayer = new Player("playerOne");
        Unit melee = new MeleeUnit(map.getGrid()[0][0], testPLayer);
        Unit colonist = new Colonist(map.getGrid()[1][0], testPLayer);
        Army army = new Army(map.getGrid()[0][0],  testPLayer);


        // add units and armies
        testPLayer.addUnit(melee);
        testPLayer.addUnit(colonist);
        testPLayer.addArmy(army);
        army.addUnitToArmy(melee);

        // kill unit and make sure it is out of Player array and tile array
        melee.getUnitStats().setHealth(0);
        testPLayer.checkUnitArmyStructs();
        assertTrue(testPLayer.getUnits().size() == 1);          // melee should be dead, colonist should be alive
        assertTrue(testPLayer.getArmies().size() == 0);         // army should be empty
        assertTrue(map.getGrid()[0][0].getUnits().size() == 0); // 00 tile should be empty


        //destroy structure
        ((Colonist) colonist).buildCapital();
        testPLayer.getStructures().get(0).getStats().setHealth(0);
        testPLayer.checkUnitArmyStructs();


        // check that the player lost
        assertEquals(testPLayer.isDefeated(), true);

    }

    @Test
    // This tests the attacking for a single unit
    public void testAttackMelee() throws Exception {

        // create map and player
        Map map = new Map();
        Player player1 = new Player("One");
        Player player2 = new Player("Two");

        // create units and give them to player
        Unit melee = new MeleeUnit(map.getGrid()[1][29], player1);
        Unit ranged = new RangedUnit(map.getGrid()[1][31], player2);
        player1.addUnit(melee);
        player2.addUnit(ranged);

        // check Melee units only get the tile next to it
        Attacker attacker = new Attacker(map, melee, 2);

        attacker.attack();

        assertEquals(player2.getUnits().get(0).getUnitStats().getArmor(), 0);
        assertTrue(player2.getUnits().get(0).getUnitStats().getHealth() < 100);


//        // check ranged attack
//        attacker = new Attacker(map, ranged, 4);
//
//        attacker.attack();
//        assertEquals(player1.getUnits().get(0).getUnitStats().getArmor(), 0);
//        assertTrue(player1.getUnits().get(0).getUnitStats().getHealth() < 100);


        // check you can't attack a teammate
//        attacker = new Attacker(map, melee, 2);
//        attacker.attack();
//
//        assertEquals(player1.getUnits().get(1).getUnitStats().getArmor(), 10);
//        assertEquals(player1.getUnits().get(1).getUnitStats().getHealth(), 100);


    }


}


