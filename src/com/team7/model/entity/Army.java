package com.team7.model.entity;

import com.team7.model.Map;
import com.team7.model.Player;
import com.team7.model.Tile;
import com.team7.model.entity.structure.ObservationTower;
import com.team7.model.entity.structure.Structure;
import com.team7.model.entity.structure.staffedStructure.Capital;
import com.team7.model.entity.structure.staffedStructure.Fort;
import com.team7.model.entity.structure.staffedStructure.StaffedStructure;
import com.team7.model.entity.structure.staffedStructure.University;
import com.team7.model.entity.structure.staffedStructure.singleHarvestStructure.Farm;
import com.team7.model.entity.structure.staffedStructure.singleHarvestStructure.Mine;
import com.team7.model.entity.structure.staffedStructure.singleHarvestStructure.PowerPlant;
import com.team7.model.entity.unit.Unit;

import java.util.ArrayList;

public class Army extends Entity {

    private CommandQueue commandQueue;
    private ArrayList<Unit> units;
    private ArrayList<Unit> battleGroup;
    private ArrayList<Unit> reinforcements;
    private ArrayList<Worker> workers;
    private int slowestSpeed;
    private int greatestVis;
    private int direction;
    private boolean isPowered;
    private int turnsFrozen;

    public Army(Tile startTile, Player player) {
        setOwner(player);
        setLocation(startTile);
        generateID();

        this.commandQueue = new CommandQueue();
        this.units = new ArrayList<Unit>();
        this.reinforcements = new ArrayList<Unit>();
        this.battleGroup = new ArrayList<Unit>();
        this.workers = new ArrayList<Worker>();
        this.slowestSpeed = 100;
        this.greatestVis = 1;
        this.turnsFrozen = 0;
        this.direction = 1;
    }


    // Adds unit to Army's ArrayList of Units
    public void addUnitToArmy(Unit unit) {
        // Physically add the unit
        this.units.add(unit);
        unit.setArmy(this);

        if(unit.getLocation() == this.getLocation()){
            this.addUnitToBattlegroup(unit);
        } else {
            this.addUnitToReinforcements(unit);
        }

        // Check for new slowest speed
        if(unit.getUnitStats().getMovement() < this.slowestSpeed){
            this.slowestSpeed = unit.getUnitStats().getMovement();
        }

        // Check for new greatest vis
        if(unit.getVisibilityRadius() > this.greatestVis){
            this.greatestVis = unit.getVisibilityRadius();
        }

    }

    // Removes unit from Army's ArrayList of Units
    public void removeUnitFromArmy(Unit unit) {

        this.units.remove(unit);
        unit.setArmy(null);

        if(unit.getLocation() == this.getLocation()){
            this.removeUnitFromBattlegroup(unit);
        } else {
            this.removeUnitFromReinforcements(unit);
        }

        resetLowestSpeedGreatestRadius();

    }

    private void resetLowestSpeedGreatestRadius(){
        this.slowestSpeed = 100;
        this.greatestVis = 1;

        for(int i = 0; i < units.size(); i++) {
            // Check for new slowest speed
            if(units.get(i).getUnitStats().getMovement() < this.slowestSpeed){
                this.slowestSpeed = units.get(i).getUnitStats().getMovement();
            }

            // Check for new greatest vis
            if(units.get(i).getVisibilityRadius() > this.greatestVis){
                this.greatestVis = units.get(i).getVisibilityRadius();
            }
        }
    }

    public void powerUp() {


        for(int i = 0; i < units.size(); i++) {
            this.units.get(i).powerUp();
        }

        turnsFrozen = 2;
        isPowered = true;
    }

    public void powerDown() {

        for(int i = 0; i < units.size(); i++) {
            this.units.get(i).powerDown();
        }

        turnsFrozen = 0;
        isPowered = false;
    }

    public int getTurnsFrozen() {
        return turnsFrozen;
    }

    public void setTurnsFrozen(int turnsFrozen) {
        this.turnsFrozen = turnsFrozen;
    }

    public void subtractFrozenTurn() {
        if(this.turnsFrozen > 0)
            this.turnsFrozen = this.turnsFrozen - 1;
    }

    public CommandQueue getCommandQueue() {
        return commandQueue;
    }

    public ArrayList<Unit> getUnits() {
        return units;
    }

    public ArrayList<Worker> getWorkers() {
        return workers;
    }

    public int getSlowestSpeed() {
        return slowestSpeed;
    }

    public int getGreatestVis() {
        return greatestVis;
    }

    public int getDirection() {
        return direction;
    }

    public boolean isPowered() {
        return isPowered;
    }

    public void attack(Map map, int direction) {
        Attacker attacker = new Attacker(map, this.units, direction);
        attacker.attack();
    }

    public void setCommandQueue(CommandQueue commandQueue) {
        this.commandQueue = commandQueue;
    }

    public void queueCommand(Command command) {
        if(commandQueue == null)
            return;
        else
            commandQueue.queueCommand( command );
    }

    public void printCommandQueue(){
        System.out.print("Player" + getOwner().getName() + " " + "Army" + " " + getId() + " command queue:   ");

        for(int i = 0; i < commandQueue.getSize(); i++) {
            System.out.print(commandQueue.get(i).getCommandString());
            if( i + 1 < commandQueue.getSize() && commandQueue.get(i+1) != null)
                System.out.print(" , ");
        }
        if(commandQueue.getSize() == 0)
            System.out.print("empty");
        System.out.println();
    }

    public Command getCommandFromQueue() {
        if(commandQueue.getSize() == 0)
            return null;
        else
            return commandQueue.get(0);
    }

    public void removeCommandFromQueue() {
        if(commandQueue.getSize() == 0)
            return;
        else
            commandQueue.removeCommand();
    }

    public void executeCommandQueue(Map map) {

        if(getCommandFromQueue() == null)
            return;

        Command commandToExecute = getCommandFromQueue();
        String commandString = commandToExecute.getCommandString();

        if(commandString.contains("ATTACK")) {
            int dir = Integer.parseInt(commandString.substring(commandString.length() - 1));
            attack(map, dir);
            removeCommandFromQueue();
        }
        else if(commandString.contains("DEFEND")) {
            int dir = Integer.parseInt(commandString.substring(commandString.length() - 1));
            this.setDirection(dir);
            removeCommandFromQueue();
        }
        else if(commandString.contains("MOVE")) {

        }
        else if(commandString.contains("WAIT")) {
            System.out.println("WAIT :)");
            removeCommandFromQueue();
        }
        else if(commandString.contains("DISBAND")) {
            this.disband();
            removeCommandFromQueue();
        }
        else if(commandString.contains("DECOMISSION")) {
            this.decommission();
            removeCommandFromQueue();
        }
        else if(commandString.contains("DOWN")) {
            this.powerDown();
            removeCommandFromQueue();
        }
        else if(commandString.contains("UP")) {
            this.powerUp();
            removeCommandFromQueue();
        }
        else if(commandString.contains("CANCEL")) {
            this.commandQueue.cancelCommands();
        }


    }

    public void decommission() {
        for(int i = 0; i < this.units.size(); i++){
            this.units.get(i).decommission();
        }
        this.getOwner().removeArmy(this);
    }

    public void disband() {
        for(int i = 0; i < this.units.size(); i++){
            removeUnitFromArmy(this.units.get(i));
        }
        this.getOwner().removeArmy(this);
    }


    // Worker helpers
    // Adds unit to Army's ArrayList of Units
    public void addWorkerToArmy(Worker worker) {
        // Physically add the unit
        this.workers.add(worker);
        worker.setArmy(this);


        // Check for new greatest vis
        if(worker.getVisibilityRadius() > this.greatestVis){
            this.greatestVis = worker.getVisibilityRadius();
        }

    }

    // Adds unit to Army's ArrayList of Units
    public void removeWorkerFromArmy(Worker worker) {

        this.units.remove(worker);
        worker.setArmy(null);

    }

    public void addUnitToBattlegroup(Unit unit){
        if(unit.getLocation() == this.getLocation()){
            this.battleGroup.add(unit);
        }
    }

    public void addUnitToReinforcements(Unit unit){
        if(unit.getLocation() != this.getLocation()){
            this.reinforcements.add(unit);
        }
    }

    public void removeUnitFromBattlegroup(Unit unit){
            this.battleGroup.remove(unit);
    }

    public void removeUnitFromReinforcements(Unit unit){
        this.reinforcements.remove(unit);
    }

    // releases all workers at structure if they are on a structure
    public void dropWorkersAtStructure() {

        if(this.getLocation().getStructure() != null) {
            if(this.getLocation().getStructure() instanceof StaffedStructure){
                for(int i = 0; i < workers.size(); i++){
                    ((StaffedStructure) this.getLocation().getStructure()).addWorkerToStaff(workers.get(i));
                    this.removeWorkerFromArmy(workers.get(i));
                }
            }
        } else {
            System.out.println("You need a structure to drop your workers at.");
        }

    }

    //TODO
    public void buildStructure(String buildingType) {
        // create structure
        Structure structure;
        if(buildingType == "observationTower") {
            structure = new ObservationTower(this.getLocation(), this.getOwner());
        }
        else if(buildingType == "capital") {
            structure = new Capital(this.getLocation(), this.getOwner());
        }
        else if(buildingType == "fort") {
            structure = new Fort(this.getLocation(), this.getOwner());
        }
        else if(buildingType == "university") {
            structure = new University(this.getLocation(), this.getOwner());
        }
        else if(buildingType == "farm") {
            structure = new Farm(this.getLocation(), this.getOwner());
        }
        else if(buildingType == "mine") {
            structure = new Mine(this.getLocation(), this.getOwner());
        }
        else if(buildingType == "powerplant") {
            structure = new PowerPlant(this.getLocation(), this.getOwner());
        }
        else {
            System.out.println("Not a correct building type");
            return;
        }

        if(workers.size() <= 0) {
            System.out.println("Not enough workers to build");
            return;
        }

        // add workers to construction
        for(int i = 0; i < workers.size(); i++) {
            structure.addWorkerToConstruction(workers.get(i));
        }

        // add worker to tile, advancement is handled at the end of each turn
        this.getOwner().addStructure(structure);

    }

    public void moveCommandUp(String selected_value) {
        if (commandQueue.getSize() != 0) {
            commandQueue.raiseCommand(selected_value);
        }
    }


    public void moveCommandDown(String selected_value) {
        if (commandQueue.getSize() != 0) {
            commandQueue.lowerCommand(selected_value);
        }
    }

    public void removeCommandByString(String selected_value) {
        if (commandQueue.getSize() != 0) {
            commandQueue.removeCommandByString(selected_value);
        }
    }


    public void setDirection(int direction) {
        this.direction = direction;
        for(int i = 0; i < units.size(); i++){
            units.get(i).setDirection(direction);
        }
    }
}
