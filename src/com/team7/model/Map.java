package com.team7.model;

import com.team7.ProbabilityGenerator;
import com.team7.model.terrain.Crater;
import com.team7.model.terrain.Desert;
import com.team7.model.terrain.Flatland;
import com.team7.model.terrain.Mountains;
import com.team7.view.MapStats;

import java.util.ArrayList;

import static java.lang.StrictMath.abs;

/**
 * Map is composed of a 2d array of Tiles
 */
public class Map implements MapStats {


    private Tile[][] grid;
    private static final int NUM_TILES_X = MAP_TILE_WIDTH;
    private static final int NUM_TILES_Y = MAP_TILE_HEIGHT;

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
}