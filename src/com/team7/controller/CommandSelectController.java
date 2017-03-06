package com.team7.controller;


import com.team7.model.Map;
import com.team7.model.Tile;
import com.team7.view.View;

import java.util.Set;

public class CommandSelectController {

    private Map map = null;
    private View view = null;

    public CommandSelectController(Map map, View view) {
        this.map = map;
        this.view = view;
    }

    // ===============================================

    public void getRadius(Tile currTile, int radius) {
        Set<Tile> tiles = null;
        view.getMainViewImage().highlightRadius( map.getTilesInRadius(currTile, 2, tiles));
        view.redrawView();
    }


}
