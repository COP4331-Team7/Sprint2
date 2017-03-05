package com.team7.controller;


import com.team7.model.Game;
import com.team7.model.Player;
import com.team7.model.Tile;
import com.team7.model.entity.unit.Unit;
import com.team7.view.MainScreen.CommandSelect;
import com.team7.view.View;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Set;

public class PathSelectController {
    private Game game = null;
    private View view = null;

    private CommandSelect commandView = null;

    boolean isRecording = false;
    Tile selectedTile = null;
    Tile startTile = null;

    ArrayList<Tile> pathTile = new ArrayList<Tile>();;

    public PathSelectController(Game game, View view) {
        this.game = game;
        this.view = view;
        this.commandView = view.getCommandSelect();
        commandView.setController(this);
    }
    // ===============================================

    public void moveCursor(int direction) {

       // if (game.getMap().moveUnit(selectedTile, direction).isVisible == false  )
           // return;

        selectedTile.isSelectedPath = false;
        selectedTile = game.getMap().moveUnit(selectedTile, direction);
        pathTile.add(selectedTile);
        selectedTile.isSelectedPath = true;
        view.redrawView();
    }

    public void startRecordingPath(Tile startTile) {

        pathTile.clear();
        this.startTile = startTile;

        if( selectedTile != null )
            selectedTile.isSelectedPath = false;

        isRecording = true;
        selectedTile = startTile;
        startTile.isSelectedPath = true;
        view.redrawView();
    }

    public Player getPlayer () {
        return game.getCurrentPlayer();
    } // TODO: remove this was for testing

    public void drawPath(Unit unit){

        if(unit == null)
            return;

        startTile.removeUnitFromTile(unit);
        startTile.isSelectedPath = false;

        new Thread( new Runnable() {
                public void run() {
                    for(Tile tile: pathTile)
                        tile.isSelectedPath = false;

                    for (int i = 0; i < pathTile.size(); i++) {
                        Tile tile = pathTile.get(i);
                        game.getCurrentPlayer().moveUnit(unit, tile); //  move the unit
                        Set<Tile> tiles = null;
                        view.getMainViewImage().highlightRadius( game.getMap().getTilesInRadius(tile, 3, tiles));


                        SwingUtilities.invokeLater(new Runnable()   // queue frame i on EDT for display
                        {
                            public void run() {
                                view.redrawView();
                            }
                        });

                        try {
                            Thread.sleep(300);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                                                                                        // 11 = numTilesVisibleX, 16 = numTilesVisibleY
                        view.getMainScreen().getMainViewImage().zoomToDestination( tile.getxCoordinate() - 11/2, tile.getyCoordinate() - 16/2, 200  );

                    }
                }
        }).start();

        view.redrawView();
    }

}
