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
        setMovesFrozen(0);
        setInfluenceRadius(5);
        setEnergyUpkeep(5);
        setOreUpkeep(5);
        setLevelOfCompletion(95);
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
