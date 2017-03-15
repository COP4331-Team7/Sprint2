package com.team7.model.entity.structure;

import com.team7.model.Player;
import com.team7.model.Tile;
import com.team7.model.entity.Command;
import com.team7.model.entity.CommandQueue;
import com.team7.model.entity.Worker;
import com.team7.model.entity.structure.staffedStructure.StaffedStructure;


/**
 * Only used to extend range of visible tiles for the player
 */
public class ObservationTower extends Structure {
    public ObservationTower(Tile location, Player player) {
        setOwner(player);
        setLocation(location);
        generateID();
        setCommandQueue( new CommandQueue() );

        setStats(new StructureStats(
                0,
                3,
                10,
                10,
                null,   //no production for Observation Tower
                100,
                100)
        );
        setType("Observation Tower");
        setPowered(false);
        setTurnsFrozen(0);
        setVisibilityRadius(4);
        setEnergyUpkeep(5);
        setOreUpkeep(5);
        setLevelOfCompletion(100);
    }

    @Override
    public void applyTechnology(String techInstance, String technologyStat, int level) {
        if (techInstance.equals("ObservationTower")){

            setStats(new StructureStats(
                    0,
                    3,
                    getStats().getMaxArmor(),
                    20,
                    null,   //no production for Observation Tower
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

    }

}
