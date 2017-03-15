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

        if(commandString.contains("defend")) {
            this.setDirection(0);       //TODO: FIX!!!!!! HARDCODED!!!!!! need to get direction from controller
            removeCommandFromQueue();
        }
        else if(commandString.contains("decomission")) {
            this.decommission();
            removeCommandFromQueue();
        }
        else if(commandString.contains("down")) {
            this.powerDown();
            removeCommandFromQueue();
        }
        else if(commandString.contains("up")) {
            this.powerUp();
            removeCommandFromQueue();
        }
        else if(commandString.contains("cancel")) {
            this.getCommandQueue().cancelCommands();
            removeCommandFromQueue();
        }
        else if(commandString.contains("assign")) {
            int numberWorkers = Integer.parseInt(commandString.substring(commandString.length() - 1));
            ((StaffedStructure) this).assignHarvestOre(numberWorkers);
            removeCommandFromQueue();
        }
        else if(commandString.contains("unassign")) {
            int numberWorkers = Integer.parseInt(commandString.substring(commandString.length() - 1));
            ((StaffedStructure) this).unassign();
            removeCommandFromQueue();
        }
    }






}
