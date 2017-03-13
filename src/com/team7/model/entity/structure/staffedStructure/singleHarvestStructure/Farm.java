package com.team7.model.entity.structure.staffedStructure.singleHarvestStructure;

import com.team7.model.Player;
import com.team7.model.Tile;
import com.team7.model.entity.structure.StructureStats;
import com.team7.model.entity.structure.staffedStructure.IHarvester;
import com.team7.model.entity.structure.staffedStructure.StaffedStructure;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Can harvest food which produces nutrients
 */
public class Farm extends StaffedStructure implements IHarvester {

    private final String harvestFood = "harvestFood";

    public Farm(Tile location, Player player) {
        setOwner(player);
        setLocation(location);

        HashMap<String, Integer> productionRateMap = new HashMap<>();
        productionRateMap.put(harvestFood, 2);   //can harvest 2 food per turn per resource per worker
        setStats(new StructureStats(
                0,
                100,
                100,
                productionRateMap,
                100)
        );
        setType("Farm");
        setPowered(false);
        setMovesFrozen(0);
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
        if (techInstance.equals("Farm")){
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


        if (techInstance.equals("Harvester")){
            //harvest related
            if (technologyStat.equals("Food")){
                getStats().changeProduction(harvestFood, level);
            }
        }
    }
}
