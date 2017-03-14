package com.team7.model.entity.unit.nonCombatUnit;

import com.team7.model.Player;
import com.team7.model.Tile;
import com.team7.model.entity.Command;
import com.team7.model.entity.CommandQueue;
import com.team7.model.entity.Worker;
import com.team7.model.entity.structure.Structure;
import com.team7.model.entity.structure.staffedStructure.Capital;
import com.team7.model.entity.structure.staffedStructure.StaffedStructure;
import com.team7.model.entity.unit.UnitStats;
import com.team7.model.entity.unit.combatUnit.MeleeUnit;

public class Colonist extends NonCombatUnit {

    private static int colonistIds = 0;

    public Colonist(Tile startTile, Player player) {
        setOwner(player);
        setLocation(startTile);
        generateID();
        setUnitStats(new UnitStats(1, 1, 5, 5,5 ,100, 100,3));
        setCommandQueue(new CommandQueue());
        setType("Colonist");
        setPowered(true);
        setTurnsFrozen(0);
        setArmy(null);
        setDirection(2);
        setVisibilityRadius(2);
    }

    public void buildCapital() {
        // create capital, 5 workers and 2 melee units
        Structure capital = new Capital(this.getLocation(), this.getOwner());
        this.getOwner().addStructure(capital);

        Worker worker1 = new Worker(this.getLocation(), this.getOwner());
        Worker worker2 = new Worker(this.getLocation(), this.getOwner());
        Worker worker3 = new Worker(this.getLocation(), this.getOwner());
        Worker worker4 = new Worker(this.getLocation(), this.getOwner());
        Worker worker5 = new Worker(this.getLocation(), this.getOwner());

        this.getOwner().addWorker(worker1);
        this.getOwner().addWorker(worker2);
        this.getOwner().addWorker(worker3);
        this.getOwner().addWorker(worker4);
        this.getOwner().addWorker(worker5);
        ((StaffedStructure) capital).addWorkerToStaff(worker1);
        ((StaffedStructure) capital).addWorkerToStaff(worker2);
        ((StaffedStructure) capital).addWorkerToStaff(worker3);
        ((StaffedStructure) capital).addWorkerToStaff(worker4);
        ((StaffedStructure) capital).addWorkerToStaff(worker5);

        this.getOwner().addUnit(new MeleeUnit(this.getLocation(), this.getOwner()));
        this.getOwner().addUnit(new MeleeUnit(this.getLocation(), this.getOwner()));

        // sacrifice colonist from tile and player
        this.getLocation().removeUnitFromTile(this);
        this.getOwner().removeUnit(this);

    }

    @Override
    public void executeCommandQueue() {

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
                //TODO
                // move unit furthest allowable distance.
                // if move doesn't complete in 1 turn, leave in queue
                break;

            case "MAKE BASE":
                if(commandToExecute.getWait() == 0) {
                    buildCapital();
                    removeCommandFromQueue();
                }
                else {
                    commandToExecute.decrementWait();
                }
                break;

            default:
                break;
        }

    }



    @Override
    public void applyTechnology(String techInstance, String technologyStat, int level) {
        if(techInstance.equals("Colonist")){

            //reset stats except armor and health
            UnitStats defaultStats = new UnitStats(1, 1, getUnitStats().getArmor(), 5, 10, getUnitStats().getHealth(), 100, 3 );
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
                    getUnitStats().changeMaxArmor((2*level));
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
