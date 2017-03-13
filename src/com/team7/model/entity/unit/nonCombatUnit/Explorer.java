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

            //reset stats except armor and health
            UnitStats defaultStats = new UnitStats(1, 1, getUnitStats().getArmor(), 10, 10, getUnitStats().getHealth(), 100, 3 );
            setUnitStats(defaultStats);

            switch (technologyStat){
                case "VisibilityRadius":
                    setVisibilityRadius(level);
                    break;
                case "AttackStrength":
                    //will always stay at 0
                    getUnitStats().changeOffensiveDamage((level));
                    break;
                case "DefenseStrength":
                    getUnitStats().changeDefensiveDamage((level));
                    break;
                case "ArmorStrength":
                    getUnitStats().changeMaxArmor((level*2));
                    break;
                case "MovementRate":
                    getUnitStats().changeMovement(level);
                    break;
                case "Efficiency":
                    //decrement upkeep -> better efficiency
                    getUnitStats().changeUpkeep((0-level));
                    break;
                case "Health":
                    getUnitStats().changeMaxHealth((level*20));
                    break;
            }
        }

    }
}

