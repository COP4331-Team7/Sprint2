package com.team7.model.entity.structure.staffedStructure;

import com.team7.model.Player;
import com.team7.model.Tile;
import com.team7.model.entity.CommandQueue;
import com.team7.model.entity.structure.StructureStats;
import com.team7.model.entity.unit.Unit;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Capital can only exist once
 */
public class Capital extends StaffedStructure implements IHarvester, IUnitProducer{

    //Strings to be entered into production rate
    private final String harvestOre = "harvestOre";
    private final String harvestEnergy = "harvestEnergy";
    private final String harvestFood = "harvestFood";
    private final String heal = "heal";
    private final String produceExplorer = "produceExplorer";
    private final String produceWorker = "produceWorker";

    public Capital(Tile location, Player player) {
        setOwner(player);
        setLocation(location);
        setCommandQueue( new CommandQueue() );

        HashMap<String, Integer> productionRateMap = new HashMap<>();
        productionRateMap.put(harvestOre, 2);   //can harvest 2 ore per turn per worker per resource
        productionRateMap.put(harvestEnergy, 2);   //can harvest 2 energy per turn per worker per resource
        productionRateMap.put(harvestFood, 2);   //can harvest 2 food per turn per resource per worker
        productionRateMap.put(heal, 20);      //can heal all units on tile by +20 per turn
        productionRateMap.put(produceExplorer, 6); //takes 6 turns to produce an explorer
        productionRateMap.put(produceWorker, 4);  //takes 4 turn to produce a worker
        setStats(new StructureStats(
                0,
                0,
                10,
                10,
                productionRateMap,
                100,
                100)
        );
        setType("Capital");
        setPowered(false);

        setTurnsFrozen(0);
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
                    getStats().changeProduction(produceExplorer, 0-level);
                    break;
                case "Worker":
                    getStats().changeProduction(produceWorker, 0-level);
                    break;
            }
        }

        if (techInstance.equals("Capital")){

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
                    getStats().changeDefensiveDamage((level));
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
            switch (technologyStat){
                case "Ore":
                    getStats().changeProduction(harvestOre, level);
                    break;
                case "Food":
                    getStats().changeProduction(harvestFood, level);
                    break;
                case "Energy":
                    getStats().changeProduction(harvestEnergy, level);
                    break;
            }

        }
    }
}
