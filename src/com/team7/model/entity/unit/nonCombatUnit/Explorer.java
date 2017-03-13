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
        setUnitStats(new UnitStats(1, 1, 10, 10, 10,100, 100,3));
        setCommandQueue(new CommandQueue());
        setType("Explorer");
        setPowered(true);
        setTurnsFrozen(0);
        setArmy(null);
        setDirection(2);
        setProspecting(false);
        setVisibilityRadius(1); //start technology level 1
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
                    //will always stay at 0
                    getUnitStats().changeOffensiveDamage((level*10));
                    break;
                case "DefenseStrength":
                    getUnitStats().changeDefensiveDamage((level*10));
                    break;
                case "ArmorStrength":
                    getUnitStats().changeArmor((level*10));
                    break;
                case "MovementRate":
                    getUnitStats().changeMovement(level);
                    break;
                case "Efficiency":
                    //decrement upkeep -> better efficiency
                    getUnitStats().changeUpkeep((0-level));
                    break;
                case "Health":
                    getUnitStats().changeHealth((level*10));
                    break;
            }
        }

    }
}

