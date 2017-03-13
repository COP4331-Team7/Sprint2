package com.team7.model.entity.unit.combatUnit;

import com.team7.model.Player;
import com.team7.model.Tile;
import com.team7.model.entity.CommandQueue;
import com.team7.model.entity.unit.UnitStats;

public class RangedUnit extends CombatUnit {

    public RangedUnit(Tile startTile, Player player) {
        setOwner(player);
        setLocation(startTile);
        generateID();
        setUnitStats(new UnitStats(12, 5, 10, 6, 100, 4));
        setCommandQueue(new CommandQueue());
        setType("Ranged");
        setPowered(true);
        setMovesFrozen(0);
        setArmy(null);
        setDirection(2);
        setVisibilityRadius(2);
    }


    @Override
    public void applyTechnology(String techInstance, String technologyStat, int level) {
        if(techInstance.equals("Ranged")){
            switch (technologyStat){
                case "VisibilityRadius":
                    setVisibilityRadius(level);
                    break;
                case "AttackStrength":
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
