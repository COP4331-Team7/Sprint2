package com.team7.model.entity.structure.staffedStructure;

import com.team7.model.Player;
import com.team7.model.Tile;
import com.team7.model.entity.Command;
import com.team7.model.entity.CommandQueue;
import com.team7.model.entity.structure.StructureStats;
import com.team7.model.entity.unit.Unit;
import com.team7.model.entity.unit.combatUnit.MeleeUnit;
import com.team7.model.entity.unit.combatUnit.RangedUnit;

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
        generateID();

        setCommandQueue( new CommandQueue() );

        HashMap<String, Integer> productionRateMap = new HashMap<>();
        productionRateMap.put(produceMelee, 8);   //can produce a melee unit after 5 ticks
        productionRateMap.put(produceRanged, 8);   //can produce a ranged unit after 5 ticks
        setStats(new StructureStats(
                5,
                5,
                10,
                10,
                productionRateMap,
                100,
                100)
        );
        setType("Fort");
        setPowered(false);
        setTurnsFrozen(0);
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

            //reset stats except armor and health
            StructureStats defaultStats = new StructureStats(
                    5,
                    5,
                    getStats().getArmor(),
                    10,
                    getStats().getProductionRates(),
                    getStats().getHealth(),
                    100);
            setStats(defaultStats);



            //all structure specific stuff
            switch (technologyStat){
                case "VisibilityRadius":
                    setVisibilityRadius(level);
                    break;
                case "AttackStrength":
                    getStats().changeOffensiveDamage((level*5));
                    break;
                case "DefenseStrength":
                    getStats().changeDefensiveDamage((level*5));
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

    private void changeWorkerTrainTicks(int delta){
        workerTrainTicks += delta;
    }

    private void changeSoldierTrainTicks(int delta){
        soldierTrainTicks += delta;
    }

    public void buildUnit(String unitType) {
        // create structure
        Unit unit = new MeleeUnit(this.getLocation(), this.getOwner());;
        if(unitType == "Melee") {
            unit = new MeleeUnit(this.getLocation(), this.getOwner());
        }
        else if(unitType == "Ranged") {
            unit = new RangedUnit(this.getLocation(), this.getOwner());
        }


        // add worker to tile, advancement is handled at the end of each turn
        this.getOwner().addUnit(unit);

    }

    @Override
    public void executeCommandQueue() {

        if(getTurnsFrozen() > 0) {
            subtractFrozenTurn();
            return;
        }

        if(getCommandFromQueue() == null)
            return;

        Command commandToExecute = getCommandFromQueue();
        String commandString = commandToExecute.getCommandString();

        switch ( commandString ) {

            case "defend":
                this.setDirection(0);       //TODO: FIX!!!!!! HARDCODED!!!!!! need to get direction from controller
                removeCommandFromQueue();
                break;

            case "decomission":
                this.decommission();
                removeCommandFromQueue();
                break;

            case "down":
                this.powerDown();
                removeCommandFromQueue();
                break;

            case "up":
                this.powerUp();
                removeCommandFromQueue();
                break;

            case "cancel":
                this.getCommandQueue().cancelCommands();
                removeCommandFromQueue();
                break;

            case "attack":
                int dir = Integer.parseInt(commandString.substring(commandString.length() - 1));
                // TODO: Attack function
                removeCommandFromQueue();
                break;

            case "melee":
                buildUnit("Melee");
                removeCommandFromQueue();
                break;

            case "ranged":
                buildUnit("Ranged");
                removeCommandFromQueue();
                break;

            default:
                break;

        }

    }
}
