package com.team7.model.entity.structure.staffedStructure.singleHarvestStructure;

import com.team7.model.Player;
import com.team7.model.Tile;
import com.team7.model.entity.Command;
import com.team7.model.entity.CommandQueue;
import com.team7.model.entity.structure.StructureStats;
import com.team7.model.entity.structure.staffedStructure.IHarvester;
import com.team7.model.entity.structure.staffedStructure.StaffedStructure;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Can harvest ore which produces metal
 */
public class Mine extends StaffedStructure implements IHarvester {

    private final String harvestOre = "harvestOre";

    public Mine(Tile location, Player player) {
        setOwner(player);
        setLocation(location);
        generateID();
        setCommandQueue( new CommandQueue() );

        HashMap<String, Integer> productionRateMap = new HashMap<>();
        productionRateMap.put(harvestOre, 2);   //can harvest 2 ore per turn per worker per resource
        setStats(new StructureStats(
                0,
                3,
                10,
                10,
                productionRateMap,
                100,
                100)
        );
        setType("Mine");
        setPowered(false);
        setTurnsFrozen(0);
        setVisibilityRadius(3);
        setEnergyUpkeep(5);
        setOreUpkeep(5);
        setWorkerStaff(new ArrayList<>());
        setLevelOfCompletion(80);
    }

    @Override
    public void harvestResource(Tile tile) {
    }

    @Override
    public void beginStructureFunction() {
    }

    @Override
    public void applyTechnology(String techInstance, String technologyStat, int level) {
        if (techInstance.equals("Mine")){

            setStats(new StructureStats(
                    0,
                    0,
                    getStats().getArmor(),
                    10,
                    getStats().getProductionRates(),
                    getStats().getHealth(),
                    100)
            );

            //all structure specific stuff
            switch (technologyStat){
                case "VisibilityRadius":
                    setVisibilityRadius(level);
                    break;
                case "AttackStrength":
                    getStats().changeOffensiveDamage((level));
                    break;
                case "DefenseStrength":
                    getStats().changeDefensiveDamage((level*3));
                    break;
                case "ArmorStrength":
                    getStats().changeMaxArmor((level*2));
                    break;
                case "Health":
                    getStats().changeMaxHealth((level*20));
                    break;
                case "Efficiency":
                    changeEnergyUpkeep((0-level));
                    changeOreUpkeep((0-level));
                    break;
            }
        }


        if (techInstance.equals("Harvester")){
            //harvest related
            if (technologyStat.equals("Ore")){
                getStats().changeProduction(harvestOre, level);
            }
        }

    }

    @Override
    public void executeCommandQueue() {


        if(getCommandFromQueue() == null)
            return;

        Command commandToExecute = getCommandFromQueue();
        String commandString = commandToExecute.getCommandString();

        switch ( commandString ) {

            case "defend":
                this.setDirection(0);       //TODO: FIX!!!!!! HARDCODED!!!!!! need to get direction from controller
                removeCommandFromQueue();
                break;

            case "decomission":
                this.decommission();
                removeCommandFromQueue();
                break;

            case "down":
                this.powerDown();
                removeCommandFromQueue();
                break;

            case "up":
                this.powerUp();
                removeCommandFromQueue();
                break;

            case "cancel":
                this.getCommandQueue().cancelCommands();
                removeCommandFromQueue();
                break;

            case "assign":
                int numberWorkers = Integer.parseInt(commandString.substring(commandString.length() - 1));
                ((StaffedStructure) this).assignHarvestOre(numberWorkers);
                removeCommandFromQueue();
                break;

            case "unassign":
                unassign();
                removeCommandFromQueue();
                break;

            default:
                break;
        }

    }






}
