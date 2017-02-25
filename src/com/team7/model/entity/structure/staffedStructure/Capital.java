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

    public Capital(Player player, Tile location) {
        setId(1);
        setOwner(player);
        setLocation(location);

        HashMap<String, Integer> productionRateMap = new HashMap<>();
        productionRateMap.put("test", 1);       //TODO figure out how production is used
        setStats(new StructureStats(
                0,
                100,
                100,
                productionRateMap,
                100)
        );
        setType("Capital");
        setPowered(true);
        setMovesFrozen(0);
        setInfluenceRadius(5);
        setEnergyUpkeep(5);
        setOreUpkeep(5);
        setWorkerStaff(new ArrayList<>()); //TODO fill up with 5 workers
        setFoodUpkeep(2);       //amount of upkeep needed PER WORKER

    }

    //can harvest, and thus produce, Energy/Food/Ore -> Power/Nutrients/Metal
    @Override
    public void harvestResource() {

    }

    @Override
    public void processResource() {

    }

    @Override
    public void increaseHarvestedResource() {

    }

    @Override
    public Unit produceUnit(String unitType) {
        return null;
    }
}
