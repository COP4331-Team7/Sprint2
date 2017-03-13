package com.team7.model.entity.structure.staffedStructure;

import com.team7.model.Player;
import com.team7.model.Tile;
import com.team7.model.entity.structure.StructureStats;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * staffed by workers
 */
public class University extends StaffedStructure implements ITechnologyProducer {

    public University(Tile location, Player player) {
        setOwner(player);
        setLocation(location);

        HashMap<String, Integer> productionRateMap = new HashMap<>();
        //key: TYPE INSTANCE TECHNOLOGY
        //value Integer = number of turns needed to increment research level

        //Worker technology
        productionRateMap.put("worker Worker VisibilityRadius", 2);
        productionRateMap.put("worker Worker MovementRate",2);
        productionRateMap.put("worker Worker Efficiency", 2); //reduces amount of food a worker needs per turn
        productionRateMap.put("worker Worker HarvestRadius", 2); //aka work radius
        //Tile technology
        productionRateMap.put("worker Tile Density", 1);

        //Unit technology
        productionRateMap.put("unit Explorer VisibilityRadius", 2);
        productionRateMap.put("unit Explorer AttackStrength",2);
        productionRateMap.put("unit Explorer DefenseStrength",2);
        productionRateMap.put("unit Explorer ArmorStrength", 2);
        productionRateMap.put("unit Explorer MovementRate",2);
        productionRateMap.put("unit Explorer Efficiency", 2); //reduces amount of food a unit needs per turn
        productionRateMap.put("unit Explorer Health",2);

        productionRateMap.put("unit Colonist VisibilityRadius", 2);
        productionRateMap.put("unit Colonist AttackStrength",2);
        productionRateMap.put("unit Colonist DefenseStrength",2);
        productionRateMap.put("unit Colonist ArmorStrength", 2);
        productionRateMap.put("unit Colonist MovementRate",2);
        productionRateMap.put("unit Colonist Efficiency", 2); //reduces amount of food a unit needs per turn
        productionRateMap.put("unit Colonist Health",2);

        productionRateMap.put("unit Melee VisibilityRadius", 2);
        productionRateMap.put("unit Melee AttackStrength",2);
        productionRateMap.put("unit Melee DefenseStrength",2);
        productionRateMap.put("unit Melee ArmorStrength", 2);
        productionRateMap.put("unit Melee MovementRate",2);
        productionRateMap.put("unit Melee Efficiency", 2); //reduces amount of food a unit needs per turn
        productionRateMap.put("unit Melee Health",2);

        productionRateMap.put("unit Ranged VisibilityRadius", 2);
        productionRateMap.put("unit Ranged AttackStrength",2);
        productionRateMap.put("unit Ranged DefenseStrength",2);
        productionRateMap.put("unit Ranged ArmorStrength", 2);
        productionRateMap.put("unit Ranged MovementRate",2);
        productionRateMap.put("unit Ranged Efficiency", 2); //reduces amount of food a unit needs per turn
        productionRateMap.put("unit Ranged Health",2);


        //Structure technology
        productionRateMap.put("structure Capital VisibilityRadius", 2);
        productionRateMap.put("structure Capital AttackStrength", 2);
        productionRateMap.put("structure Capital DefenseStrength", 2);
        productionRateMap.put("structure Capital ArmorStrength", 2);
        productionRateMap.put("structure Capital Health", 2);
        productionRateMap.put("structure Capital Efficiency", 2); //reduces amount of ore/energy needed per turn

        //applies to Farm, Mine, PowerPlant. Researching one will automatically increase the other
        productionRateMap.put("structure Harvester VisibilityRadius", 2);
        productionRateMap.put("structure Harvester AttackStrength", 2);
        productionRateMap.put("structure Harvester DefenseStrength", 2);
        productionRateMap.put("structure Harvester ArmorStrength", 2);
        productionRateMap.put("structure Harvester Health", 2);
        productionRateMap.put("structure Harvester Efficiency", 2); //reduces amount of ore/energy needed per turn

        productionRateMap.put("structure Fort VisibilityRadius", 2);
        productionRateMap.put("structure Fort AttackStrength", 2);
        productionRateMap.put("structure Fort DefenseStrength", 2);
        productionRateMap.put("structure Fort ArmorStrength", 2);
        productionRateMap.put("structure Fort Health", 2);
        productionRateMap.put("structure Fort Efficiency", 2); //reduces amount of ore/energy needed per turn

        productionRateMap.put("structure ObservationTower VisibilityRadius", 2);
        productionRateMap.put("structure ObservationTower AttackStrength", 2);
        productionRateMap.put("structure ObservationTower DefenseStrength", 2);
        productionRateMap.put("structure ObservationTower ArmorStrength", 2);
        productionRateMap.put("structure ObservationTower Health", 2);
        productionRateMap.put("structure ObservationTower Efficiency", 2); //reduces amount of ore/energy needed per turn

        productionRateMap.put("structure University VisibilityRadius", 2);
        productionRateMap.put("structure University AttackStrength", 2);
        productionRateMap.put("structure University DefenseStrength", 2);
        productionRateMap.put("structure University ArmorStrength", 2);
        productionRateMap.put("structure University Health", 2);
        productionRateMap.put("structure University Efficiency", 2); //reduces amount of ore/energy needed per turn

        //Production Rate technology
        productionRateMap.put("productionRate Harvest Ore", 2); //increases amount of Ore a structure can harvest per worker
        productionRateMap.put("productionRate Harvest Food", 2);
        productionRateMap.put("productionRate Harvest Energy", 2);
        productionRateMap.put("productionRate Produce Explorer", 2);//applies to Capital
        productionRateMap.put("productionRate Produce Worker", 2);//applies to Capital
        productionRateMap.put("productionRate Train Worker", 2);//applies to Fort. How quickly a worker can train a recruit
        productionRateMap.put("productionRate Train Soldier", 2);//applies to Fort. How quickly a soldier can train a recruit



        setStats(new StructureStats(
                0,
                100,
                100,
                productionRateMap,
                100)
        );
        setType("University");
        setPowered(false);
        setMovesFrozen(0);
        setVisibilityRadius(3);
        setEnergyUpkeep(5);
        setOreUpkeep(5);
        setWorkerStaff(new ArrayList<>());
        setLevelOfCompletion(80);
    }

    @Override
    public void produceTechnology() {
        
    }

    @Override
    public void beginStructureFunction() {

    }

    @Override
    public void applyTechnology(String techInstance, String technologyStat, int level) {
        if (techInstance.equals("University")){
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
}
