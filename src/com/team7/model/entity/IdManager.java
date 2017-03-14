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
                ArrayList<Integer> availableIDs= new ArrayList<Integer>(Arrays.asList(0,1,2,3,4,5,6,7,8,9));
                for(int i = 0; i < player.getUnits().size(); i++){
                    if(player.getUnits().get(i) instanceof Explorer){
                        availableIDs.remove(Integer.valueOf(player.getUnits().get(i).getId()));
                    }
                }
                ID = availableIDs.get(0);
            }
            else if(entity instanceof Colonist) {
                ArrayList<Integer> availableIDs= new ArrayList<Integer>(Arrays.asList(0,1,2,3,4,5,6,7,8,9));
                for(int i = 0; i < player.getUnits().size(); i++){
                    if(player.getUnits().get(i) instanceof Colonist){
                        availableIDs.remove(Integer.valueOf(player.getUnits().get(i).getId()));
                    }
                }
                ID = availableIDs.get(0);
            }
            else if(entity instanceof MeleeUnit) {
                ArrayList<Integer> availableIDs= new ArrayList<Integer>(Arrays.asList(0,1,2,3,4,5,6,7,8,9));
                for(int i = 0; i < player.getUnits().size(); i++){
                    if(player.getUnits().get(i) instanceof MeleeUnit){
                        availableIDs.remove(Integer.valueOf(player.getUnits().get(i).getId()));
                    }
                }
                ID = availableIDs.get(0);
            }
            else if(entity instanceof RangedUnit) {
                ArrayList<Integer> availableIDs= new ArrayList<Integer>(Arrays.asList(0,1,2,3,4,5,6,7,8,9));
                for(int i = 0; i < player.getUnits().size(); i++){
                    if(player.getUnits().get(i) instanceof RangedUnit){
                        availableIDs.remove(Integer.valueOf(player.getUnits().get(i).getId()));
                    }
                }
                ID = availableIDs.get(0);
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