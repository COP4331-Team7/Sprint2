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
 * All friendlies that come within influence radius receive a defensive bonus
 */
public class Fort extends StaffedStructure implements IUnitProducer {

    private final String produceMelee = "produceMelee";
    private final String produceRanged = "produceRanged";

    private int workerTrainTicks;
    private int soldierTrainTicks;

    public Fort(Tile location, Player player) {
        setOwner(player);
        setLocation(location);

        HashMap<String, Integer> productionRateMap = new HashMap<>();
        productionRateMap.put(produceMelee, 8);   //can produce a melee unit after 5 ticks
        productionRateMap.put(produceRanged, 8);   //can produce a ranged unit after 5 ticks
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

        //produce soldier via 'training'
        //worker trains 1 tick per turn
        //soldier trains 2 ticks per turn
        workerTrainTicks = 1;
        soldierTrainTicks = 2;
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
        if (techInstance.equals("Train")){
            switch (technologyStat){
                case "Worker":
                    changeWorkerTrainTicks(level);
                    break;
                case "Soldier":
                    changeWorkerTrainTicks(level);
                    break;
            }
        }

        if (techInstance.equals("Fort")){
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

    private void changeWorkerTrainTicks(int delta){
        workerTrainTicks += delta;
    }

    private void changeSoldierTrainTicks(int delta){
        soldierTrainTicks += delta;
    }
}
