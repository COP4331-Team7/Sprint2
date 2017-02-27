package com.team7.tests;

import com.team7.model.Map;
import com.team7.model.Tile;
import org.junit.Test;

import java.util.Set;

/**
 * Testing map functionality
 */
public class MapTest {
    @Test
    public void getTilesInRadius() throws Exception{
        Map map = new Map();
        Tile[][] grid = map.getGrid();
        Set<Tile> tiles = map.getTilesInRadius(grid[4][4],6);
        System.out.println(tiles.size());
        for (Tile t : tiles){
            System.out.println(t.getxCoordinate()+","+ t.getyCoordinate());
        }
    }
}
