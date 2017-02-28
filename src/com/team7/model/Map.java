package com.team7.model;

import com.team7.ProbabilityGenerator;
import com.team7.model.terrain.Crater;
import com.team7.model.terrain.Desert;
import com.team7.model.terrain.Flatland;
import com.team7.model.terrain.Mountains;

import java.awt.*;
import java.util.*;

import static java.lang.StrictMath.abs;

/**
 * Map is composed of a 2d array of Tiles
 */
public class Map{


    private Tile[][] grid;
    Set<Tile> treeSet = new HashSet<Tile>();
    private static final int NUM_TILES_X = 40;
    private static final int NUM_TILES_Y = 40;
    int[] direction = {8,9,3,2,1,7};

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


 /* //TODO figure if this iterative method is needed, as it requires no global Set for Tiles
    //TODO fix iterating by depth (radius)
 //calculates all Tiles in the radius of influence/visibility of the selected entity
    public Set<Tile> getTilesInRadius(Tile currentTile, int radius) {
        int currentX;
        int currentY;
        int[] possibleDirections = {8, 9, 3, 2, 1, 7};

        Set<Tile> tilesInRadius = new HashSet<>();
        //bfs iteratively
        Queue<Tile> tileQ = new LinkedList<>();
        Queue<Integer> depthQ = new LinkedList<>();
        depthQ.add(0);
        tilesInRadius.add(currentTile);
        tileQ.add(currentTile);
        int currentDepth = 0;

        while (!tileQ.isEmpty() && radius > currentDepth){
            currentDepth = depthQ.remove();

            Tile tmp = tileQ.remove();  //operate on current
            tilesInRadius.add(tmp);
            currentX = tmp.getxCoordinate();
            currentY = tmp.getyCoordinate();
            Tile childTile = tmp;
            for(int direction : possibleDirections){
                if (isEven(currentX) && isEven(currentY)){
                    //calculate movement for even X, even Y
                    childTile = moveTypeOne(currentX, currentY, direction);
                } else if (isEven(currentX) && !isEven(currentY)){
                    //calculate movement for even X, odd Y
                    childTile = moveTypeTwo(currentX, currentY, direction);
                } else if (!isEven(currentX) && isEven(currentY)){
                    //calculate movement for odd X, even Y
                    childTile = moveTypeOne(currentX, currentY, direction);
                } else if (!isEven(currentX) && !isEven(currentY)) {
                    //calculate movement for odd X, odd Y
                    childTile = moveTypeTwo(currentX, currentY, direction);
                }
                // System.out.println("New Tile coords: " + childTile.getxCoordinate() + ", " + childTile.getyCoordinate());
                if (!tilesInRadius.contains(childTile)){
                    //tile is unvisited
                    // System.out.println("adding new Tile to q");
                    depthQ.add(currentDepth+1);
                    tilesInRadius.add(childTile);
                    tileQ.add(childTile);
                }
            }

        }

        return tilesInRadius;
    }*/


    //calculates all Tiles in the radius of influence/visibility of the selected entity
    public Set<Tile> getTilesInRadius(Tile currentTile, int radius) {
        int currentX = currentTile.getxCoordinate();
        int currentY = currentTile.getyCoordinate();
        treeSet.add(currentTile);


        if(radius == 0) {
            return treeSet;
        }
        if ((isEven(currentX) && isEven(currentY)) || (!isEven(currentX) && isEven(currentY))){
            //calculate movement for even X, even Y
           // moveEvenXEvenY(currentTile);
            for(int i : direction) {
                treeSet.add(moveTypeOne(currentX, currentY, i));
            }
            for(int i : direction) {
            getTilesInRadius(moveTypeOne(currentX, currentY, i), radius - 1);
            }
        }
        else if ((!isEven(currentX) && !isEven(currentY)) || (isEven(currentX) && !isEven(currentY))){
            //calculate movement for even X, odd Y
            //moveEvenXOddY();
            for(int i : direction) {
                treeSet.add(moveTypeTwo(currentX, currentY, i));
            }
            for(int i : direction) {
                getTilesInRadius(moveTypeTwo(currentX, currentY, i), radius - 1);
            }
        }
        return treeSet;
    }

    private Tile moveTypeOne(int x, int y, int direction){
        //8 move north: y-2
        //9 move northeast: x+1, y-1
        //3 move southeast: x+1, y+1
        //2 move south: y+2
        //1 move southwest: y+1
        //7 move northwest: y-1
        int tmpX = x;
        int tmpY = y;
        switch (direction){
            case 8:
                tmpY = y-2;
                break;
            case 9:
                tmpX = x+1;
                tmpY = y-1;
                break;
            case 3:
                tmpX = x+1;
                tmpY = y+1;
                break;
            case 2:
                tmpY = y+2;
                break;
            case 1:
                tmpY = y+1;
                break;
            case 7:
                tmpY = y-1;
                break;
            default:
                break;
        }

        if (isInbounds(tmpX,tmpY)){
            return grid[tmpX][tmpY];
        }


        return grid[x][y];
    }

    private Tile moveTypeTwo(int x, int y, int direction){
        //8 move north: y-2
        //9 move northeast: y-1
        //3 move southeast: y+1
        //2 move south: y+2
        //1 move southwest: x-1, y+1
        //7 move northwest: x-1, y-1
        int tmpX = x;
        int tmpY = y;
        switch (direction){
            case 8:
                tmpY = y-2;
                break;
            case 9:
                tmpY = y-1;
                break;
            case 3:
                tmpY = y+1;
                break;
            case 2:
                tmpY = y+2;
                break;
            case 1:
                tmpX = x-1;
                tmpY = y+1;
                break;
            case 7:
                tmpX = x-1;
                tmpY = y-1;
                break;
            default:
                break;
        }

        if (isInbounds(tmpX,tmpY)){
            return grid[tmpX][tmpY];
        }


        return grid[x][y];
    }

    private boolean isInbounds(int x, int y){
        if (x >= 0 && x < NUM_TILES_X && y >= 0 && y < NUM_TILES_Y){
            return true;
        }
        return false;
    }

    private boolean isEven(int num){
        return ((num & 1) == 0);
    }

}
