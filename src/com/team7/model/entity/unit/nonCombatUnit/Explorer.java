package com.team7.model.entity.unit.nonCombatUnit;

import com.team7.model.Player;
import com.team7.model.Tile;
import com.team7.model.entity.CommandQueue;
import com.team7.model.entity.unit.UnitStats;

public class Explorer extends NonCombatUnit {

    boolean isProspecting;

    public Explorer(Tile startTile, Player player) {
        setOwner(player);
        setLocation(startTile);
        generateID();
        setUnitStats(new UnitStats(1, 1, 10, 10, 100, 3));
        setCommandQueue(new CommandQueue());
        setType("Explorer");
        setPowered(true);
        setMovesFrozen(0);
        setArmy(null);
        setDirection(2);
        setProspecting(false);
        setVisibilityRadius(0); //start technology level 0
    }

    public boolean isProspecting() {
        return isProspecting;
    }

    public void setProspecting(boolean prospecting) {
        isProspecting = prospecting;
    }

    @Override
    public void applyTechnology(String techInstance, String technologyStat, int level){
        if(techInstance.equals("Explorer")){
            switch (technologyStat){
                case "VisibilityRadius":
                    setVisibilityRadius(level);
                    break;
                case "AttackStrength":
                    break;
                case "DefenseStrength":
                    break;
                case "ArmorStrength":
                    break;
                case "MovementRate":
                    getUnitStats().changeMovement(level);
                    break;
                case "Efficiency":
                    break;
                case "Health":
                    break;
            }
        }

    }
}


/*

        technologies.add(new Technology("unit", "Explorer", "VisibilityRadius", 1));  //only one hex available
                technologies.add(new Technology("unit" ,"Explorer",  "AttackStrength", 1));
                technologies.add(new Technology("unit", "Explorer" , "DefenseStrength", 1));
                technologies.add(new Technology("unit", "Explorer", "ArmorStrength", 1));
                technologies.add(new Technology("unit", "Explorer", "MovementRate", 1));
                technologies.add(new Technology("unit", "Explorer", "Health",1));*/
