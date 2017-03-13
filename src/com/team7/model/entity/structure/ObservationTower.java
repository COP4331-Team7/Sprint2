package com.team7.model.entity.structure;

import com.team7.model.Player;
import com.team7.model.Tile;
import com.team7.model.entity.Worker;


/**
 * Only used to extend range of visible tiles for the player
 */
public class ObservationTower extends Structure {
    public ObservationTower(Tile location, Player player) {
        setOwner(player);
        setLocation(location);
        setStats(new StructureStats(
                0,
                100,
                100,
                null,   //no production for Observation Tower
                100)
        );
        setType("Observation Tower");
        setPowered(false);
        setTurnsFrozen(0);
        setVisibilityRadius(4);
        setEnergyUpkeep(5);
        setOreUpkeep(5);
        setLevelOfCompletion(95);
    }

    @Override
    public void applyTechnology(String techInstance, String technologyStat, int level) {
        if (techInstance.equals("ObservationTower")){
            //all structure specific stuff
            switch (technologyStat){
                case "VisibilityRadius":
                    setVisibilityRadius(level);
                    break;
                case "AttackStrength":
                    getStats().changeOffensiveDamage((level*10));
                    break;
                case "DefenseStrength":
                    getStats().changeDefensiveDamage((level*10));
                    break;
                case "ArmorStrength":
                    getStats().changeArmor((level*10));
                    break;
                case "Health":
                    getStats().changeHealth((level*10));
                    break;
                case "Efficiency":
                    changeEnergyUpkeep((0-level));
                    changeOreUpkeep((0-level));
                    break;
            }
        }
    }

    public int advanceConstruction() {
        int foodUpkeepDuringConstruction = 0;
        if (!checkConstructionComplete()) {
            //construction not complete
            for(Worker worker : getWorkerAssigned()){
                foodUpkeepDuringConstruction += 2;
                changeLevelOfCompletion(worker.getConstructionRate());  //increment construction according to number of workers
            }
            if(checkConstructionComplete()){
                setPowered(true);   //construction has finished at this turn
            }
        }
        return foodUpkeepDuringConstruction;
    }
}
