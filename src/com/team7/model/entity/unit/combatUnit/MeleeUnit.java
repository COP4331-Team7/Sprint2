package com.team7.model.entity.unit.combatUnit;

import com.team7.model.Player;
import com.team7.model.Tile;
import com.team7.model.entity.CommandQueue;
import com.team7.model.entity.unit.UnitStats;

public class MeleeUnit extends CombatUnit {

    public MeleeUnit(Tile startTile, Player player) {
        setOwner(player);
        setLocation(startTile);
        generateID();
        setUnitStats(new UnitStats(20, 10, 15, 15,8, 100, 100,4));
        setCommandQueue(new CommandQueue());
        setType("Melee");
        setPowered(true);
        setTurnsFrozen(0);
        setArmy(null);
        setDirection(2);
        setVisibilityRadius(2);
    }

    @Override
    public void applyTechnology(String techInstance, String technologyStat, int level) {
        if(techInstance.equals("Melee")){

            //reset stats except armor and health
            UnitStats defaultStats = new UnitStats(20, 10, getUnitStats().getArmor(), 15, 8, getUnitStats().getHealth(), 100, 4 );
            setUnitStats(defaultStats);

            switch (technologyStat){
                case "VisibilityRadius":
                    setVisibilityRadius(level);
                    break;
                case "AttackStrength":
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
