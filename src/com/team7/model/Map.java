package com.team7.model;

import com.team7.ProbabilityGenerator;
import com.team7.model.terrain.Crater;
import com.team7.model.terrain.Desert;
import com.team7.model.terrain.Flatland;
import com.team7.model.terrain.Mountains;

import java.awt.*;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

import static java.lang.StrictMath.abs;

/**
 * Map is composed of a 2d array of Tiles
 */
public class Map{


    private Tile[][] grid;
    private static final int NUM_TILES_X = 40;
    private static final int NUM_TILES_Y = 40;

    public Map() {
        createTilesForMap(); //for purposes of later abstraction
    }
    public Tile[][] getGrid() {
        return grid;
    }
    //hardcoded Tiles to create our map grid
    private void createTilesForMap() {
        grid = new Tile[NUM_TILES_X][NUM_TILES_Y];

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {

                int n = ProbabilityGenerator.randomInteger(0, 3);
                switch (n) {
                    case 0:
                        grid[i][j] = new Tile(new Mountains(), i, j);
                        break;
                    case 1:
                        grid[i][j] = new Tile(new Desert(), i, j);
                        break;
                    case 2:
                        grid[i][j] = new Tile(new Flatland(), i, j);
                        break;
                    case 3:
                        grid[i][j] = new Tile(new Crater(), i, j);
                        break;
                    default:
                        System.out.println("Incorrect range in createTilesForMap, n = " + n);
                        break;
                }
            }
        }
    }



    //calculates all Tiles in the radius of influence/visibility of the selected entity
    private Set<Tile> getTilesInRadius(Tile currentTile, int radius) {
        int currentX = currentTile.getxCoordinate();
        int currentY = currentTile.getyCoordinate();
        TreeSet<Tile> treeSet = new TreeSet<Tile>();
        if(radius == 0)
        {
         return treeSet;
        }
        if ((isEven(currentX) && isEven(currentY)) || (!isEven(currentX) && !isEven(currentY))){
            //calculate movement for even X, even Y
           // moveEvenXEvenY(currentTile);
            treeSet.add(moveTypeOne(currentTile,"8"));
            treeSet.add(moveTypeOne(currentTile,"9"));
            treeSet.add(moveTypeOne(currentTile,"3"));
            treeSet.add(moveTypeOne(currentTile,"2"));
            treeSet.add(moveTypeOne(currentTile,"1"));
            treeSet.add(moveTypeOne(currentTile,"7"));
            getTilesInRadius(moveTypeOne(currentTile,"8"),radius-1);
            getTilesInRadius(moveTypeOne(currentTile,"9"),radius-1);
            getTilesInRadius(moveTypeOne(currentTile,"3"),radius-1);
            getTilesInRadius(moveTypeOne(currentTile,"3"),radius-1);
            getTilesInRadius(moveTypeOne(currentTile,"1"),radius-1);
            getTilesInRadius(moveTypeOne(currentTile,"7"),radius-1);

        } else if ((isEven(currentX) && !isEven(currentY)) || (isEven(currentX) && !isEven(currentY))){
            //calculate movement for even X, odd Y
            //moveEvenXOddY();
            treeSet.add(moveTypeTwo(currentTile,"8"));
            treeSet.add(moveTypeTwo(currentTile,"9"));
            treeSet.add(moveTypeTwo(currentTile,"3"));
            treeSet.add(moveTypeTwo(currentTile,"2"));
            treeSet.add(moveTypeTwo(currentTile,"1"));
            treeSet.add(moveTypeTwo(currentTile,"7"));
            getTilesInRadius(moveTypeTwo(currentTile,"8"),radius-1);
            getTilesInRadius(moveTypeTwo(currentTile,"9"),radius-1);
            getTilesInRadius(moveTypeTwo(currentTile,"3"),radius-1);
            getTilesInRadius(moveTypeTwo(currentTile,"3"),radius-1);
            getTilesInRadius(moveTypeTwo(currentTile,"1"),radius-1);
            getTilesInRadius(moveTypeTwo(currentTile,"7"),radius-1);

        }
        return null;
    }

    private Tile moveTypeOne(Tile tileToMove, String command){
        //8 move north: y-2
        //9 move northeast: x+1, y-1
        //3 move southeast: x+1, y+1
        //2 move south: y+2
        //1 move southwest: y+1
        //7 move northwest: y-1
        return null;
    }

    private Tile moveTypeTwo(Tile tileToMove, String command){
        //8 move north: y-2
        //9 move northeast: y-1
        //3 move southeast: y+1
        //2 move south: y+2
        //1 move southwest: x-1, y+1
        //7 move northwest: x-1, y-1
        return null;
    }
    private boolean isEven(int num){
        return ((num & 1) == 0);
    }

}
