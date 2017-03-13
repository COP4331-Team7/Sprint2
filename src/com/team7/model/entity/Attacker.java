package com.team7.model.entity;

import com.team7.model.Map;
import com.team7.model.Tile;
import com.team7.model.entity.unit.Unit;
import com.team7.model.entity.unit.combatUnit.MeleeUnit;
import com.team7.model.entity.unit.combatUnit.RangedUnit;

import java.util.ArrayList;

public class Attacker {

    Map map;

    ArrayList<Unit> selectedUnits;
    ArrayList<Tile> targetTiles;

    int attackDirection;
    int totalMeleeDamage;
    int totalRangedDamage;


    // constructor for single unit
    public Attacker(Map m, Unit unit, int direction){
        this.map = m;
        this.selectedUnits = new ArrayList<Unit>();
        this.selectedUnits.add(unit);
        this.attackDirection = direction;
        this.totalMeleeDamage = calcTotalMeleeDamage();
        this.totalRangedDamage = calcTotalRangedDamage();
        this.targetTiles = calcTargetTiles();
    }

    // constructor for armies
    public Attacker(Map m, ArrayList<Unit> units, int direction){
        this.map = m;
        this.selectedUnits = units;
        this.attackDirection = direction;
        this.totalMeleeDamage = calcTotalMeleeDamage();
        this.totalRangedDamage = calcTotalRangedDamage();
        this.targetTiles = calcTargetTiles();
    }

    // calculate total melee damage from this tile
    private int calcTotalMeleeDamage() {
        int sum = 0;

        for(int i = 0; i < selectedUnits.size(); i++){
            if(selectedUnits.get(i) instanceof MeleeUnit)
                sum += selectedUnits.get(i).getUnitStats().getOffensiveDamage();
        }

        return sum;
    }

    // calculate total damage from this tile
    private int calcTotalRangedDamage() {
        int sum = 0;

        for(int i = 0; i < selectedUnits.size(); i++){
            if(selectedUnits.get(i) instanceof RangedUnit)
                sum += selectedUnits.get(i).getUnitStats().getOffensiveDamage();
        }

        return sum;
    }

    // This method returns the list of tiles able to be attacked. Public for testing
    // Returns one tile if only melee, 5 tiles in that direction if ranged
    public ArrayList<Tile> calcTargetTiles() {
        ArrayList<Tile> targetTiles = new ArrayList<Tile>();

        // check if there is ranged unit
        boolean hasRanged = false;
        for(int i = 0; i < this.selectedUnits.size(); i++){
            if(this.selectedUnits.get(i) instanceof RangedUnit){
                hasRanged = true;
            }
        }

        int x = this.selectedUnits.get(0).getLocation().getxCoordinate();
        int y = this.selectedUnits.get(0).getLocation().getyCoordinate();

        // looping 5 because that is the max range for ranged units (HARDCODED)
        for(int i = 0; i < 5; i++) {
            // for type one tiles
            if ((isEven(x) && isEven(y)) || (!isEven(x) && isEven(y))) {
                if(attackDirection == 1) {
                    y = y + 1;
                }
                else if(attackDirection == 2) {
                    y = y + 2;
                }
                else if(attackDirection == 3) {
                    x = x + 1;
                    y = y + 1;
                }
                else if(attackDirection == 7) {
                    y = y - 1;
                }
                else if(attackDirection == 8) {
                    y = y - 2;
                }
                else if(attackDirection == 9) {
                    x = x + 1;
                    y = y - 1;
                } else {
                    System.out.println("Invalid attack direction");
                    break;
                }
            }
            else if((!isEven(x) && !isEven(y)) || (isEven(x) && !isEven(y))){
                if(attackDirection == 1) {
                    x = x - 1;
                    y = y + 1;
                }
                else if(attackDirection == 2) {
                    y = y + 2;
                }
                else if(attackDirection == 3) {
                    y = y + 1;
                }
                else if(attackDirection == 7) {
                    x = x - 1;
                    y = y - 1;
                }
                else if(attackDirection == 8) {
                    y = y - 2;
                }
                else if(attackDirection == 9) {
                   y = y - 1;
                } else {
                    System.out.println("Invalid attack direction");
                    break;
                }
            }


            if(isInBounds(x, y)){
                targetTiles.add(map.getTile(x, y));
            }
            else{
                break;
            }

            if(!hasRanged){
                break;
            }
        }


        return targetTiles;
    }

    // check if x and y are in bounds
    private boolean isInBounds(int x, int y) {
        if (x <= 39 && x >= 0 && y <= 39 && y >= 0) {
            return true;
        }
        return false;
    }

    private boolean isEven(int num){
        return ((num & 1) == 0);
    }

}
