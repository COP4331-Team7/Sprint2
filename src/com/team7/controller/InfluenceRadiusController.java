package com.team7.controller;


import com.team7.model.Map;
import com.team7.model.Tile;
import com.team7.view.View;

import java.util.Set;

public class InfluenceRadiusController {

    private Map map = null;
    private View view = null;

    public InfluenceRadiusController(Map map, View view) {
        this.map = map;
        this.view = view;
    }


    public void getRadius(Tile currTile, int radius) {
        Set<Tile> tiles = null;
        view.getMainScreen().getMainViewImage().highlightRadius( map.getTilesInRadius(currTile, radius, tiles));
        view.getMainScreen().getMainViewImage().reDrawMap();

    }


}