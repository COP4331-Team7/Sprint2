package com.team7.model.entity.structure.staffedStructure;

import com.team7.model.Player;
import com.team7.model.Tile;
import com.team7.model.entity.structure.StructureStats;
import com.team7.model.entity.unit.Unit;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Producers soldiers (melee/ranged unit)
 * Workers are trained to become soldiers:
 *  if trained by workers: slower
 *  if trained by soldiers: faster
 * All enemies that come within influence radius are automatically attacked
 * All friendlies that come within influence radius recieve a defensive bonus
 */
public class Fort extends StaffedStructure implements IUnitProducer {

    public Fort(Tile location, Player player) {
        setOwner(player);
        setLocation(location);

        HashMap<String, Integer> productionRateMap = new HashMap<>();
        productionRateMap.put("produceMelee", 4);   //can produce a melee unit after 4 ticks
        productionRateMap.put("produceRanged", 4);   //can produce a ranged unit after 4 ticks
        setStats(new StructureStats(
                0,
                100,
                100,
                productionRateMap,
                100)
        );
        setType("Fort");
        setPowered(false);
        setMovesFrozen(0);
        setVisibilityRadius(3);
        setEnergyUpkeep(5);
        setOreUpkeep(5);
        setWorkerStaff(new ArrayList<>());
        setLevelOfCompletion(80);
    }

    //produce soldier via 'training'
    //worker trains 1 tick per turn
    //soldier trains 2 ticks per turn
    @Override
    public Unit produceUnit(String unitType) {
        return null;
    }

    @Override
    public void beginStructureFunction() {

    }
}
