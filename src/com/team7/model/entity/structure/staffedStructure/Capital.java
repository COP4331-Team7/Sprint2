package com.team7.model.entity.structure.staffedStructure;

import com.team7.model.Player;
import com.team7.model.Tile;
import com.team7.model.entity.structure.StructureStats;
import com.team7.model.entity.unit.Unit;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Capital can only exist once
 */
public class Capital extends StaffedStructure implements IHarvester, IUnitProducer{

    public Capital(Tile location, Player player) {
        setOwner(player);
        setLocation(location);

        HashMap<String, Integer> productionRateMap = new HashMap<>();
        productionRateMap.put("harvestOre", 2);   //can harvest 2 ore per turn per worker per resource
        productionRateMap.put("harvestEnergy", 2);   //can harvest 2 energy per turn per worker per resource
        productionRateMap.put("harvestFood", 2);   //can harvest 2 food per turn per resource per worker
        productionRateMap.put("heal", 20);      //can heal all units on tile by +20 per turn
        productionRateMap.put("produceExplorer", 2); //takes 2 turns to produce an explorer
        productionRateMap.put("produceWorker", 1);  //takes 1 turn to produce a worker
        setStats(new StructureStats(
                0,
                100,
                100,
                productionRateMap,
                100)
        );
        setType("Capital");
        setPowered(false);
        setMovesFrozen(0);
        setVisibilityRadius(4);
        setEnergyUpkeep(5);
        setOreUpkeep(5);
        setWorkerStaff(new ArrayList<>());
        setLevelOfCompletion(80);

    }

    //can harvest, and thus produce, Energy/Food/Ore -> Power/Nutrients/Metal
    @Override
    public void harvestResource(Tile tile) {

    }

    @Override
    public Unit produceUnit(String unitType) {
        return null;
    }

    @Override
    public void beginStructureFunction() {

    }

    @Override
    public void applyTechnology(String techInstance, String technologyStat, int level) {
        if (techInstance.equals("Produce")){
            //production rate stuff
            switch (technologyStat){
                case "Explorer":
                    break;
                case "Worker":
                    break;
            }
        }

        if (techInstance.equals("Capital")){
            //all structure specific stuff
            switch (technologyStat){
                case "VisibilityRadius":
                    break;
                case "AttackStrength":
                    break;
                case "DefenseStrength":
                    break;
                case "ArmorStrength":
                    break;
                case "Health":
                    break;
                case "Efficiency":
                    break;
            }
        }


        if (techInstance.equals("Harvester")){
            //harvest related
            switch (technologyStat){
                case "Ore":
                    break;
                case "Food":
                    break;
                case "Energy":
                    break;
            }

        }
    }
}
