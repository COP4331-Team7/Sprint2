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
    public int generateID(Entity entity) {

        if(entity instanceof Unit) {
            ArrayList<Integer> availableIDs= new ArrayList<Integer>(Arrays.asList(1,2,3,4,5,6,7,8,9));
            if(entity instanceof Explorer) {

            }
            else if(entity instanceof Colonist) {

            }
            else if(entity instanceof MeleeUnit) {

            }
            else if(entity instanceof RangedUnit) {

            }
        }
        else if(entity instanceof Worker) {

        }
        else if(entity instanceof  Structure) {

        }
        else if(entity instanceof Army) {

        }


        return 0;
    }



}
