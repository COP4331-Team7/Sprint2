package com.team7.model.entity.unit.nonCombatUnit;

import com.team7.model.Map;
import com.team7.model.Player;
import com.team7.model.Tile;
import com.team7.model.entity.Command;
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

    //TODO fix TDA by having getUnitStats.setMovementToProspecting and getUnitStats.setMovementToNotProspecting() methods
    public void setProspecting(boolean prospecting) {
        if(prospecting)
            getUnitStats().setMovement( getUnitStats().getMovement() / 2 );
        else
            getUnitStats().setMovement( getUnitStats().getMovement() * 2 );

        isProspecting = prospecting;
    }

    @Override
    public void executeCommandQueue(Map map) {


        if(getCommandFromQueue() == null)
            return;

        Command commandToExecute = getCommandFromQueue();

        String commandString = commandToExecute.getCommandString();

        switch ( commandString ) {
            case "PROSPECT MODE ON":
                setProspecting( true );
                removeCommandFromQueue();
                break;

            case "PROSPECT MODE OFF":
                setProspecting( false );
                removeCommandFromQueue();
                break;

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

            default:
                break;
        }


    }

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

