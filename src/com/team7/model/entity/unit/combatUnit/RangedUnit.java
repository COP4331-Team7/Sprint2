package com.team7.model.entity.unit.combatUnit;

import com.team7.model.Player;
import com.team7.model.Tile;
import com.team7.model.entity.Command;
import com.team7.model.entity.CommandQueue;
import com.team7.model.entity.unit.UnitStats;

public class RangedUnit extends CombatUnit {

    public RangedUnit(Tile startTile, Player player) {
        setOwner(player);
        setLocation(startTile);
        generateID();
        setUnitStats(new UnitStats(12, 5, 10, 10,6, 100, 100,4));
        setCommandQueue(new CommandQueue());
        setType("Ranged");
        setPowered(true);
        setTurnsFrozen(0);
        setArmy(null);
        setDirection(2);
        setVisibilityRadius(2);
    }

    @Override
    public void executeCommandQueue() {

        if(getTurnsFrozen() > 0) {
            subtractFrozenTurn();
            return;
        }

        if(getCommandFromQueue() == null)
            return;

        Command commandToExecute = getCommandFromQueue();

        String commandString = commandToExecute.getCommandString();

        switch ( commandString ) {
            case "DECOMMISSION":
                decommission( );
                removeCommandFromQueue();
                break;

            case "POWER UP":
                powerUp( );
                removeCommandFromQueue();
                break;

            case "POWER DOWN":
                powerDown( );
                removeCommandFromQueue();
                break;

            case "MOVE":
                // move unit furthest allowable distance.
                // if move doesn't complete in 1 turn, leave in queue
                break;

            case "REINFORCE":
                break;


            case "ATTACK":

                break;

            default:
                break;
        }

    }


    @Override
    public void applyTechnology(String techInstance, String technologyStat, int level) {
        if(techInstance.equals("Ranged")){

            //reset stats except armor and health
            UnitStats defaultStats = new UnitStats(12, 5, getUnitStats().getArmor(), 10, 6, getUnitStats().getHealth(), 100, 4 );
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
