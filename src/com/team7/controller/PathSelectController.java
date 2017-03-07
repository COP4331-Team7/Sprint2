package com.team7.controller;


import com.team7.ConfigurableControls.ConfigReader;
import com.team7.model.Game;
import com.team7.model.Player;
import com.team7.model.Tile;
import com.team7.model.entity.unit.Unit;
import com.team7.view.MainScreen.CommandSelect;
import com.team7.view.MainScreen.MainViewImage;
import com.team7.view.View;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Set;

public class PathSelectController {
    private Game game = null;
    private View view = null;
    private MainViewImage mainViewImage = null;

    private CommandSelect commandView = null;

    public static boolean isRecording = false;
    Tile selectedTile = null;
    Tile startTile = null;
    ConfigReader configReader;

    ArrayList<Tile> pathTile = new ArrayList<Tile>();;

    public PathSelectController(Game game, View view) {
        this.game = game;
        this.view = view;
        this.mainViewImage = view.getMainViewImage();
        this.commandView = view.getCommandSelect();

         configReader = new ConfigReader();

        commandView.setController(this);
    }
    // ===============================================

    public void moveCursor(String direction) {

        selectedTile.isSelectedPath = false;

        if(direction.equals(configReader.getValueByKey(game.getCurrentPlayer().getName(), "Northwest"))) {
            selectedTile = game.getMap().moveUnit(selectedTile, 7);
        }
        else if(direction.equals(configReader.getValueByKey(game.getCurrentPlayer().getName(), "Southwest"))) {
            selectedTile = game.getMap().moveUnit(selectedTile, 1);
        }
        else if(direction.equals(configReader.getValueByKey(game.getCurrentPlayer().getName(), "South"))) {
            selectedTile = game.getMap().moveUnit(selectedTile, 2);
        }
        else if(direction.equals(configReader.getValueByKey(game.getCurrentPlayer().getName(), "North"))) {
            selectedTile = game.getMap().moveUnit(selectedTile, 8);
        }
        else if(direction.equals(configReader.getValueByKey(game.getCurrentPlayer().getName(), "Northeast"))) {
            selectedTile = game.getMap().moveUnit(selectedTile, 9);
        }
        else if(direction.equals(configReader.getValueByKey(game.getCurrentPlayer().getName(), "Southeast"))) {
            selectedTile = game.getMap().moveUnit(selectedTile, 3);
        }

//        if ( selectedTile.getVisible(game.getCurrentPlayer().getName()) == false )
//            return;

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
                        view.getMainViewImage().highlightRadius( game.getMap().getTilesInRadius(tile, 2, tiles));

                        SwingUtilities.invokeLater(new Runnable()   // queue frame i on EDT for display
                        {
                            public void run() {
                                mainViewImage.reDrawMap();
                            }
                        });

                        try {
                            Thread.sleep(120);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        if(i == pathTile.size() - 1)
                            view.getMainScreen().getMainViewImage().zoomToDestination( pathTile.get(pathTile.size() - 1).getxCoordinate() - 11/2, pathTile.get(pathTile.size() - 1).getyCoordinate() - 16/2, 50  );


                    }
                }
        }).start();




    }

}
