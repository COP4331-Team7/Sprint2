package com.team7.model;

import com.team7.model.terrain.Crater;
import com.team7.model.terrain.Desert;
import com.team7.model.terrain.Flatland;
import com.team7.model.terrain.Mountains;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import static java.lang.StrictMath.abs;

/**
 * Map is composed of a 2d array of Tiles
 */
public class Map{

    private Tile[][] grid;
    private static final int NUM_TILES_X = 40;
    private static final int NUM_TILES_Y = 40;
    int[] direction = {8,9,3,2,1,7};

    public Map() {
        createTilesForMap(); //for purposes of later abstraction
    }
    public Tile[][] getGrid() {
        return grid;
    }
    private ArrayList<Tile> path = new ArrayList<Tile>();
    int index =0;
    private void createTilesForMap() {
        grid = new Tile[NUM_TILES_X][NUM_TILES_Y];

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {

                int n = ThreadLocalRandom.current().nextInt(0, 4);
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
    public Set<Tile> getTilesInRadius(Tile currentTile, int radius, Set<Tile> tileSet) {
        int currentX = currentTile.getxCoordinate();
        int currentY = currentTile.getyCoordinate();
        if(tileSet == null){
            tileSet = new HashSet<Tile>();
        }
        tileSet.add(currentTile);

        if(radius == 0) {
            return tileSet;
        }
        if ((isEven(currentX) && isEven(currentY)) || (!isEven(currentX) && isEven(currentY))){
            //calculate movement for even X, even Y
           // moveEvenXEvenY(currentTile);
            for(int i : direction) {
                tileSet.add(moveTypeOne(currentX, currentY, i));
            }
            for(int i : direction) {
                getTilesInRadius(moveTypeOne(currentX, currentY, i), radius - 1, tileSet);

            }
        }
        else if ((!isEven(currentX) && !isEven(currentY)) || (isEven(currentX) && !isEven(currentY))){
            //calculate movement for even X, odd Y
            //moveEvenXOddY();
            for(int i : direction) {
                tileSet.add(moveTypeTwo(currentX, currentY, i));
            }
            for(int i : direction) {
                getTilesInRadius(moveTypeTwo(currentX, currentY, i), radius - 1, tileSet);
            }
        }
        return tileSet;
    }

    public ArrayList<Tile> findMinPath(Tile start, Tile destination, Set<Tile> openList, Set<Tile> closedList){
        if(openList ==null || closedList ==null){
            openList = new HashSet<>();
            closedList = new HashSet<>();
        }

        openList.add(start);
        if(start.getxCoordinate() == destination.getxCoordinate() && start.getyCoordinate() == destination.getyCoordinate()) {
            System.out.println("Entered if");
            closedList.add(destination);
            path.add(index,destination);
            index++;
            return path;
        }

        else if (isEven(start.getyCoordinate())) {
            for (int i : direction) {
//                        System.out.println("Adding to open List");
                Tile tile = moveTypeOne(start.getxCoordinate(), start.getyCoordinate(), i);
                if (tile.getxCoordinate() == destination.getxCoordinate() && tile.getyCoordinate() == destination.getyCoordinate()) {
                    closedList.add(tile);
                    path.add(index,tile);
                    index++;
                    return path;
                }
                else if (!openList.contains(tile) && !closedList.contains(tile)) {
                    openList.add(moveTypeOne(start.getxCoordinate(), start.getyCoordinate(), i));
                }
//                        else openList.add(moveTypeTwo(start.getxCoordinate(), start.getyCoordinate(), i));
            }

        }

        else {
            for (int i : direction) {

                Tile tile = moveTypeTwo(start.getxCoordinate(), start.getyCoordinate(), i);
                if (tile.getxCoordinate() == destination.getxCoordinate() && tile.getyCoordinate() == destination.getyCoordinate()) {
                    closedList.add(tile);
                    path.add(index,tile);
                    index++;
                    return path;
                }
                else if (!openList.contains(tile) && !closedList.contains(tile)) {
                    openList.add(moveTypeTwo(start.getxCoordinate(), start.getyCoordinate(), i));
                }
            }

        }



        System.out.println("Adding to closed tiles" + start.getxCoordinate() + "," + start.getyCoordinate());
        closedList.add(start);
        path.add(index,start);
        index++;
        openList.remove(start);
        Tile lowest = getLowest(destination, openList);
        if(path!=null){
            index = 0;
            path = new ArrayList<Tile>();
        }
        findMinPath(lowest, destination, openList, closedList);
        path.add(index,lowest);
        index++;

        if(!closedList.contains(lowest))
            closedList.add(lowest);
        openList.remove(lowest);
        return path;
    }

    public Tile getLowest(Tile destination, Set<Tile> openList){

        for (Tile tile : openList) {
            System.out.println("Distance"+tile.getxCoordinate()+","+tile.getyCoordinate()+"   "+String.valueOf(calculateDistance(tile,destination)));
        }
        int min = 30;
        int curr=0;
        Tile minTile=null;
        for(Tile tile : openList){
            curr = calculateDistance(tile, destination);
            if(curr<min){
                min = curr;
                minTile =tile;
            }
        }
        Tile lowest = minTile;
        System.out.println("Lowest node"+lowest.getxCoordinate()+","+lowest.getyCoordinate());
        return lowest;
    }

    public int calculateDistance(Tile start, Tile destination){
        int x1=0,y1=0;
        int x2=0,y2=0;
        x1 = start.getxCoordinate();
        y1 = start.getyCoordinate();
        x2 = destination.getxCoordinate();
        y2 = destination.getyCoordinate();
        int d1 = Math.abs(x1-x2)*2;
        int d2 = Math.abs(y1-y2)/2;
        int d3 = Math.abs((x1-x2)*2+(y1-y2)/2);
//        if(y2%2==1 && y1%2== 1 && x1%2!=0 && x2!=0)
//            return (d1+d2+d3)/2-1;
//        else if(y2%2==1 && y1%2== 1 && x1%2==0 && x2!=0)
//            return (d1+d2+d3)/2-1;
//        else if(y2%2!=0 && x2%2!= 0 && x1%2!=0 && y1%2!=1)
//            return (d1 + d2 + d3) / 2 + 2;
//        else if(x1%2==1 && x2%2==1 && y1%2==0 && y2%2!=0)
//            return (d1 + d2 + d3) / 2 +1 ;
//        else
        return (d1 + d2 + d3) / 2+1 ;

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

        if (isInBounds(tmpX,tmpY)){
            return grid[tmpX][tmpY];
        }


        return grid[x][y];
    }
    public Tile getAdjacentTile(Tile currentTile, int direction){
        int currentX = currentTile.getxCoordinate();
        int currentY = currentTile.getyCoordinate();
        if ((isEven(currentX) && isEven(currentY)) || (!isEven(currentX) && isEven(currentY))){
           return moveTypeOne(currentX,currentY,direction);
        }
        else if ((!isEven(currentX) && !isEven(currentY)) || (isEven(currentX) && !isEven(currentY))) {
            return moveTypeTwo(currentX,currentY,direction);
        }
        return null;
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

        if (isInBounds(tmpX,tmpY)){
            return grid[tmpX][tmpY];
        }


        return grid[x][y];
    }

    private boolean isInBounds(int x, int y){
        if (x >= 0 && x < NUM_TILES_X && y >= 0 && y < NUM_TILES_Y){
            return true;
        }
        return false;
    }

    private boolean isEven(int num){
        return ((num & 1) == 0);
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

    public Tile getTile(int i, int j) {
        return grid[i][j];
    }

}
