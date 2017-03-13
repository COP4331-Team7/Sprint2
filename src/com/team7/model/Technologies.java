package com.team7.model;

import java.util.ArrayList;

/**
 * A list of all user technology
 * Own class for separation
 */
public class Technologies {

    //TODO bug:
    //  having more than 1 instance of an entity results in a multiplied stat instead of the same stats

    private ArrayList<Technology> workerTechnologies;
    private ArrayList<Technology> unitTechnologies;
    private ArrayList<Technology> structureTechnologies;
    public Technologies(){
        workerTechnologies = new ArrayList<>();
        unitTechnologies = new ArrayList<>();
        structureTechnologies = new ArrayList<>();

        //Worker technology
        workerTechnologies.add(new Technology("worker", "Worker", "VisibilityRadius", 1, 5));
        workerTechnologies.add(new Technology("worker", "Worker", "Efficiency",0, 3));
        workerTechnologies.add(new Technology("worker", "Worker", "HarvestRadius",0, 3));  //work harvest radius is initially just the structure's hex
        workerTechnologies.add(new Technology("worker", "Tile", "Density", 1,5));

//////////
        //Unit technology
        unitTechnologies.add(new Technology("unit", "Explorer", "VisibilityRadius", 3, 5));
        unitTechnologies.add(new Technology("unit" ,"Explorer",  "AttackStrength", 0, 0));
        unitTechnologies.add(new Technology("unit", "Explorer" , "DefenseStrength", 0, 5));
        unitTechnologies.add(new Technology("unit", "Explorer", "ArmorStrength", 4, 5));
        unitTechnologies.add(new Technology("unit", "Explorer", "MovementRate", 0, 3));
        unitTechnologies.add(new Technology("unit", "Explorer", "Efficiency",0, 3));
        unitTechnologies.add(new Technology("unit", "Explorer", "Health",0, 5));

        unitTechnologies.add(new Technology("unit", "Colonist", "VisibilityRadius", 1, 5));
        unitTechnologies.add(new Technology("unit" ,"Colonist",  "AttackStrength", 0, 0));
        unitTechnologies.add(new Technology("unit", "Colonist" , "DefenseStrength", 0, 5));
        unitTechnologies.add(new Technology("unit", "Colonist", "ArmorStrength", 0, 5));
        unitTechnologies.add(new Technology("unit", "Colonist", "MovementRate", 0, 3));
        unitTechnologies.add(new Technology("unit", "Colonist", "Efficiency",0, 3));
        unitTechnologies.add(new Technology("unit", "Colonist", "Health",0, 5));

        unitTechnologies.add(new Technology("unit", "Melee", "VisibilityRadius", 1, 5));
        unitTechnologies.add(new Technology("unit" ,"Melee",  "AttackStrength", 3, 5));
        unitTechnologies.add(new Technology("unit", "Melee" , "DefenseStrength", 5, 5));
        unitTechnologies.add(new Technology("unit", "Melee", "ArmorStrength", 0, 5));
        unitTechnologies.add(new Technology("unit", "Melee", "MovementRate", 3, 3));
        unitTechnologies.add(new Technology("unit", "Melee", "Efficiency",0, 3));
        unitTechnologies.add(new Technology("unit", "Melee", "Health",0, 5));

        unitTechnologies.add(new Technology("unit", "Ranged", "VisibilityRadius", 2, 5));
        unitTechnologies.add(new Technology("unit" ,"Ranged",  "AttackStrength", 5, 5));
        unitTechnologies.add(new Technology("unit", "Ranged" , "DefenseStrength", 0, 5));
        unitTechnologies.add(new Technology("unit", "Ranged", "ArmorStrength", 0, 5));
        unitTechnologies.add(new Technology("unit", "Ranged", "MovementRate", 0, 3));
        unitTechnologies.add(new Technology("unit", "Ranged", "Efficiency",0, 3));
        unitTechnologies.add(new Technology("unit", "Ranged", "Health",0, 5));


//////////
        //Structure technology
        structureTechnologies.add(new Technology("structure", "Capital", "VisibilityRadius", 1,5));
        structureTechnologies.add(new Technology("structure", "Capital", "AttackStrength", 0, 0));
        structureTechnologies.add(new Technology("structure", "Capital", "DefenseStrength", 0, 0));
        structureTechnologies.add(new Technology("structure", "Capital", "ArmorStrength", 0, 3));
        structureTechnologies.add(new Technology("structure", "Capital", "Health", 0, 3));
        structureTechnologies.add(new Technology("structure", "Capital", "Efficiency", 0, 4)); //reduces amount of ore/energy needed per turn

        //applies to Farm, Mine, PowerPlant, Capital. Researching one will automatically increase the other
        structureTechnologies.add(new Technology("structure", "Harvester", "VisibilityRadius", 1,5));
        structureTechnologies.add(new Technology("structure", "Harvester", "AttackStrength", 0, 0));
        structureTechnologies.add(new Technology("structure", "Harvester", "DefenseStrength", 0, 0));
        structureTechnologies.add(new Technology("structure", "Harvester", "ArmorStrength", 0, 3));
        structureTechnologies.add(new Technology("structure", "Harvester", "Health", 0, 3));
        structureTechnologies.add(new Technology("structure", "Harvester", "Efficiency", 0, 4)); //reduces amount of ore/energy needed per turn

        structureTechnologies.add(new Technology("structure", "Fort", "VisibilityRadius", 1,5));
        structureTechnologies.add(new Technology("structure", "Fort", "AttackStrength", 0, 4));
        structureTechnologies.add(new Technology("structure", "Fort", "DefenseStrength", 0, 3));
        structureTechnologies.add(new Technology("structure", "Fort", "ArmorStrength", 0, 3));
        structureTechnologies.add(new Technology("structure", "Fort", "Health", 0, 3));
        structureTechnologies.add(new Technology("structure", "Fort", "Efficiency", 0, 4)); //reduces amount of ore/energy needed per turn

        structureTechnologies.add(new Technology("structure", "ObservationTower", "VisibilityRadius", 1,5));
        structureTechnologies.add(new Technology("structure", "ObservationTower", "AttackStrength", 0, 0));
        structureTechnologies.add(new Technology("structure", "ObservationTower", "DefenseStrength", 0, 0));
        structureTechnologies.add(new Technology("structure", "ObservationTower", "ArmorStrength", 0, 3));
        structureTechnologies.add(new Technology("structure", "ObservationTower", "Health", 0, 3));
        structureTechnologies.add(new Technology("structure", "ObservationTower", "Efficiency", 0, 4)); //reduces amount of ore/energy needed per turn

        structureTechnologies.add(new Technology("structure", "University", "VisibilityRadius", 1,5));
        structureTechnologies.add(new Technology("structure", "University", "AttackStrength", 0, 0));
        structureTechnologies.add(new Technology("structure", "University", "DefenseStrength", 0, 0));
        structureTechnologies.add(new Technology("structure", "University", "ArmorStrength", 0, 3));
        structureTechnologies.add(new Technology("structure", "University", "Health", 0, 3));
        structureTechnologies.add(new Technology("structure", "University", "Efficiency", 0, 4)); //reduces amount of ore/energy needed per turn

//////////
        //Production Rate technology (only affect structures)
        structureTechnologies.add(new Technology("productionRate", "Harvest", "Ore", 0, 5)); //increases amount of Ore a structure can harvest per worker
        structureTechnologies.add(new Technology("productionRate", "Harvest", "Food", 0, 5));
        structureTechnologies.add(new Technology("productionRate", "Harvest", "Energy", 0, 5));
        structureTechnologies.add(new Technology("productionRate", "Produce", "Explorer", 0, 3));//applies to Capital
        structureTechnologies.add(new Technology("productionRate", "Produce", "Worker", 0, 3));//applies to Capital
        structureTechnologies.add(new Technology("productionRate", "Train", "Worker",0 , 3));//applies to Fort. How quickly a worker can train a recruit
        structureTechnologies.add(new Technology("productionRate", "Train", "Soldier", 0, 3));//applies to Fort. How quickly a soldier can train a recruit
    }

    public ArrayList<Technology> getWorkerTechnologies() {
        return workerTechnologies;
    }

    public ArrayList<Technology> getUnitTechnologies() {
        return unitTechnologies;
    }

    public ArrayList<Technology> getStructureTechnologies() {
        return structureTechnologies;
    }
}






/////////DEFAULT VALUES, DO NOT REMOVE COMMENT!!!!
/*
//Worker technology
        workerTechnologies.add(new Technology("worker", "Worker", "VisibilityRadius", 1, 5));
                workerTechnologies.add(new Technology("worker", "Worker", "Efficiency",0, 3));
                workerTechnologies.add(new Technology("worker", "Worker", "HarvestRadius",0, 3));  //work harvest radius is initially just the structure's hex
                workerTechnologies.add(new Technology("worker", "Tile", "Density", 1,5));

//////////
                //Unit technology
                unitTechnologies.add(new Technology("unit", "Explorer", "VisibilityRadius", 1, 5));
                unitTechnologies.add(new Technology("unit" ,"Explorer",  "AttackStrength", 0, 0));
                unitTechnologies.add(new Technology("unit", "Explorer" , "DefenseStrength", 0, 5));
                unitTechnologies.add(new Technology("unit", "Explorer", "ArmorStrength", 0, 5));
                unitTechnologies.add(new Technology("unit", "Explorer", "MovementRate", 0, 3));
                unitTechnologies.add(new Technology("unit", "Explorer", "Efficiency",0, 3));
                unitTechnologies.add(new Technology("unit", "Explorer", "Health",0, 5));

                unitTechnologies.add(new Technology("unit", "Colonist", "VisibilityRadius", 1, 5));
                unitTechnologies.add(new Technology("unit" ,"Colonist",  "AttackStrength", 0, 0));
                unitTechnologies.add(new Technology("unit", "Colonist" , "DefenseStrength", 0, 5));
                unitTechnologies.add(new Technology("unit", "Colonist", "ArmorStrength", 0, 5));
                unitTechnologies.add(new Technology("unit", "Colonist", "MovementRate", 0, 3));
                unitTechnologies.add(new Technology("unit", "Colonist", "Efficiency",0, 3));
                unitTechnologies.add(new Technology("unit", "Colonist", "Health",0, 5));

                unitTechnologies.add(new Technology("unit", "Melee", "VisibilityRadius", 1, 5));
                unitTechnologies.add(new Technology("unit" ,"Melee",  "AttackStrength", 0, 5));
                unitTechnologies.add(new Technology("unit", "Melee" , "DefenseStrength", 0, 5));
                unitTechnologies.add(new Technology("unit", "Melee", "ArmorStrength", 0, 5));
                unitTechnologies.add(new Technology("unit", "Melee", "MovementRate", 0, 3));
                unitTechnologies.add(new Technology("unit", "Melee", "Efficiency",0, 3));
                unitTechnologies.add(new Technology("unit", "Melee", "Health",0, 5));

                unitTechnologies.add(new Technology("unit", "Ranged", "VisibilityRadius", 1, 5));
                unitTechnologies.add(new Technology("unit" ,"Ranged",  "AttackStrength", 0, 5));
                unitTechnologies.add(new Technology("unit", "Ranged" , "DefenseStrength", 0, 5));
                unitTechnologies.add(new Technology("unit", "Ranged", "ArmorStrength", 0, 5));
                unitTechnologies.add(new Technology("unit", "Ranged", "MovementRate", 0, 3));
                unitTechnologies.add(new Technology("unit", "Ranged", "Efficiency",0, 3));
                unitTechnologies.add(new Technology("unit", "Ranged", "Health",0, 5));


//////////
                //Structure technology
                structureTechnologies.add(new Technology("structure", "Capital", "VisibilityRadius", 1,5));
                structureTechnologies.add(new Technology("structure", "Capital", "AttackStrength", 0, 0));
                structureTechnologies.add(new Technology("structure", "Capital", "DefenseStrength", 0, 3));
                structureTechnologies.add(new Technology("structure", "Capital", "ArmorStrength", 0, 3));
                structureTechnologies.add(new Technology("structure", "Capital", "Health", 0, 3));
                structureTechnologies.add(new Technology("structure", "Capital", "Efficiency", 0, 4)); //reduces amount of ore/energy needed per turn

                //applies to Farm, Mine, PowerPlant, Capital. Researching one will automatically increase the other
                structureTechnologies.add(new Technology("structure", "Harvester", "VisibilityRadius", 1,5));
                structureTechnologies.add(new Technology("structure", "Harvester", "AttackStrength", 0, 0));
                structureTechnologies.add(new Technology("structure", "Harvester", "DefenseStrength", 0, 3));
                structureTechnologies.add(new Technology("structure", "Harvester", "ArmorStrength", 0, 3));
                structureTechnologies.add(new Technology("structure", "Harvester", "Health", 0, 3));
                structureTechnologies.add(new Technology("structure", "Harvester", "Efficiency", 0, 4)); //reduces amount of ore/energy needed per turn

                structureTechnologies.add(new Technology("structure", "Fort", "VisibilityRadius", 1,5));
                structureTechnologies.add(new Technology("structure", "Fort", "AttackStrength", 0, 4));
                structureTechnologies.add(new Technology("structure", "Fort", "DefenseStrength", 0, 3));
                structureTechnologies.add(new Technology("structure", "Fort", "ArmorStrength", 0, 3));
                structureTechnologies.add(new Technology("structure", "Fort", "Health", 0, 3));
                structureTechnologies.add(new Technology("structure", "Fort", "Efficiency", 0, 4)); //reduces amount of ore/energy needed per turn

                structureTechnologies.add(new Technology("structure", "ObservationTower", "VisibilityRadius", 1,5));
                structureTechnologies.add(new Technology("structure", "ObservationTower", "AttackStrength", 0, 0));
                structureTechnologies.add(new Technology("structure", "ObservationTower", "DefenseStrength", 0, 3));
                structureTechnologies.add(new Technology("structure", "ObservationTower", "ArmorStrength", 0, 3));
                structureTechnologies.add(new Technology("structure", "ObservationTower", "Health", 0, 3));
                structureTechnologies.add(new Technology("structure", "ObservationTower", "Efficiency", 0, 4)); //reduces amount of ore/energy needed per turn

                structureTechnologies.add(new Technology("structure", "University", "VisibilityRadius", 1,5));
                structureTechnologies.add(new Technology("structure", "University", "AttackStrength", 0, 0));
                structureTechnologies.add(new Technology("structure", "University", "DefenseStrength", 0, 3));
                structureTechnologies.add(new Technology("structure", "University", "ArmorStrength", 0, 3));
                structureTechnologies.add(new Technology("structure", "University", "Health", 0, 3));
                structureTechnologies.add(new Technology("structure", "University", "Efficiency", 0, 4)); //reduces amount of ore/energy needed per turn

//////////
                //Production Rate technology (only affect structures)
                structureTechnologies.add(new Technology("productionRate", "Harvest", "Ore", 0, 5)); //increases amount of Ore a structure can harvest per worker
                structureTechnologies.add(new Technology("productionRate", "Harvest", "Food", 0, 5));
                structureTechnologies.add(new Technology("productionRate", "Harvest", "Energy", 0, 5));
                structureTechnologies.add(new Technology("productionRate", "Produce", "Explorer", 0, 3));//applies to Capital
                structureTechnologies.add(new Technology("productionRate", "Produce", "Worker", 0, 3));//applies to Capital
                structureTechnologies.add(new Technology("productionRate", "Train", "Worker",0 , 3));//applies to Fort. How quickly a worker can train a recruit
                structureTechnologies.add(new Technology("productionRate", "Train", "Soldier", 0, 3));//applies to Fort. How quickly a soldier can train a recruit*/
