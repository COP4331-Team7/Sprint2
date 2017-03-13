package com.team7.model.entity.unit.nonCombatUnit;

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
        setUnitStats(new UnitStats(1, 1, 10, 5, 100, 3));
        setCommandQueue(new CommandQueue());
        setType("Explorer");
        setPowered(true);
        setMovesFrozen(0);
        setArmy(null);
        setDirection(2);
        setProspecting(false);
        setVisibilityRadius(3);
    }

    public boolean isProspecting() {
        return isProspecting;
    }

    public void setProspecting(boolean prospecting) {
        if(prospecting)
            getUnitStats().setMovement( getUnitStats().getMovement() / 2 );
        else
            getUnitStats().setMovement( getUnitStats().getMovement() * 2 );

        isProspecting = prospecting;
    }

    @Override
    public void executeCommandQueue() {

        if(getCommandFromQueue() == null)
            return;

        Command commandToExecute = getCommandFromQueue();

        String commandString = commandToExecute.getCommandString();

        System.out.println( commandString );

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

}
