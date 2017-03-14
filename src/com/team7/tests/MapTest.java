package com.team7.tests;

import com.team7.model.Map;
import com.team7.model.Tile;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Testing map functionality
 */
public class MapTest {
    @Test
    public void getTilesInRadius() throws Exception{
        Map map = new Map();
        Tile[][] grid = map.getGrid();
        Set<Tile> tiles = null;
        tiles  = map.getTilesInRadius(grid[4][4],2,tiles);
        System.out.println(tiles.size());
        for (Tile t : tiles){
            System.out.println(t.getxCoordinate()+","+ t.getyCoordinate());
        }

    }
    @Test
    public void findPath() throws Exception{
        Map map = new Map();
        Tile[][] grid = map.getGrid();
        ArrayList<Tile> tiles = null;
        Set<Tile> openList = null;
        Set<Tile> closedList = null;
        tiles  = map.findMinPath(grid[19][23], grid[21][16], openList, closedList);
        for (int i=tiles.size()-1;i>=0;i--){
            System.out.println(tiles.get(i).getxCoordinate()+","+ tiles.get(i).getyCoordinate());
        }
    }
}
