package com.team7.model.entity;

import com.team7.model.Player;
import com.team7.model.entity.structure.Structure;
import com.team7.model.entity.unit.Unit;
import com.team7.model.entity.unit.combatUnit.MeleeUnit;
import com.team7.model.entity.unit.combatUnit.RangedUnit;
import com.team7.model.entity.unit.nonCombatUnit.Colonist;
import com.team7.model.entity.unit.nonCombatUnit.Explorer;

import java.util.ArrayList;
import java.util.Arrays;

public class IdManager {

    Player player;

    static ArrayList<Integer> availableIDs = new ArrayList<Integer>(Arrays.asList(0,1,2,3,4,5,6,7,8,9));
    static ArrayList<Integer> availableColonistIds_p1= new ArrayList<Integer>(Arrays.asList(0,1,2,3,4,5,6,7,8,9));
    static ArrayList<Integer> availableColonistIds_p2= new ArrayList<Integer>(Arrays.asList(0,1,2,3,4,5,6,7,8,9));

    static ArrayList<Integer> availableExplorerIds_p1 = new ArrayList<Integer>(Arrays.asList(0,1,2,3,4,5,6,7,8,9));
    static ArrayList<Integer> availableExplorerIds_p2 = new ArrayList<Integer>(Arrays.asList(0,1,2,3,4,5,6,7,8,9));

    static ArrayList<Integer> availableMeleeIds_p1 = new ArrayList<Integer>(Arrays.asList(0,1,2,3,4,5,6,7,8,9));
    static ArrayList<Integer> availableMeleeIds_p2 = new ArrayList<Integer>(Arrays.asList(0,1,2,3,4,5,6,7,8,9));

    static ArrayList<Integer> availableRangeIds_p1 = new ArrayList<Integer>(Arrays.asList(0,1,2,3,4,5,6,7,8,9));
    static ArrayList<Integer> availableRangeIds_p2 = new ArrayList<Integer>(Arrays.asList(0,1,2,3,4,5,6,7,8,9));


    public IdManager(Player p) {
        this.player = p;
    }


    // loop through player's entities to get available IDs and generate one
    // returns an id of -1 if something is wrong!!
    public int generateID(Entity entity) {
        int ID = -1;

        if(entity instanceof Unit) {
            if(player.checkMaxUnitsIndividual()){
                return -1;
            }

            if(entity instanceof Explorer) {
                        if(player.getName().contains("One")){
                            ID = availableExplorerIds_p1.get(0);
                            availableExplorerIds_p1.remove( 0 );
                        }
                        else {
                            ID = availableExplorerIds_p2.get(0);
                            availableExplorerIds_p2.remove( 0 );
                        }
            }
            else if(entity instanceof Colonist) {
                        if(player.getName().contains("One")){
                            ID = availableColonistIds_p1.get(0);
                            availableColonistIds_p1.remove( 0 );
                        }
                        else {
                            ID = availableColonistIds_p2.get(0);
                            availableColonistIds_p2.remove( 0 );
                        }
            }
            else if(entity instanceof MeleeUnit) {
                if(player.getName().contains("One")){
                    ID = availableMeleeIds_p1.get(0);
                    availableMeleeIds_p1.remove( 0 );
                }
                else {
                    ID = availableMeleeIds_p2.get(0);
                    availableMeleeIds_p2.remove( 0 );
                }
            }
            else if(entity instanceof RangedUnit) {
                if(player.getName().contains("One")){
                    ID = availableRangeIds_p1.get(0);
                    availableRangeIds_p1.remove( 0 );
                }
                else {
                    ID = availableRangeIds_p2.get(0);
                    availableRangeIds_p2.remove( 0 );
                }
            }

        }
        else if(entity instanceof Worker) {
            if(player.getWorkers().size() >= 99){
                return -1;
            }

            ArrayList<Integer> availableIDs= new ArrayList<Integer>();
            for(int i = 0; i <= 99; i++){
                availableIDs.add(i);
            }

            for(int i = 0; i < player.getWorkers().size(); i++){
                    availableIDs.remove(Integer.valueOf(player.getWorkers().get(i).getId()));
            }
            ID = availableIDs.get(0);
        }
        //TODO figure out logic now that structures is separated into two lists
        else if(entity instanceof Structure) {

            if(player.getStructures().size() >= 10) {

                return -1;
            }

            ArrayList<Integer> availableIDs= new ArrayList<Integer>(Arrays.asList(0,1,2,3,4,5,6,7,8,9));

            for(int i = 0; i < player.getStructures().size(); i++){
                availableIDs.remove(Integer.valueOf(player.getStructures().get(i).getId()));

            }
            ID = availableIDs.get(0);
        }
        else if(entity instanceof Army) {
            if(player.getArmies().size() >= 10) {
                return -1;
            }

            ArrayList<Integer> availableIDs= new ArrayList<Integer>(Arrays.asList(0,1,2,3,4,5,6,7,8,9));
            for(int i = 0; i < player.getArmies().size(); i++){
                availableIDs.remove(Integer.valueOf(player.getArmies().get(i).getId()));
            }
            ID = availableIDs.get(0);
        }

        return ID;
    }



}
