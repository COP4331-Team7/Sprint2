package com.team7.controller;


import com.team7.model.Map;
import com.team7.view.MainScreen.MainScreen;
import com.team7.view.MainScreen.MainViewMiniMap;
import com.team7.view.OptionsScreen.ConfigurableControls.ConfigReader;
import com.team7.model.Game;
import com.team7.model.Player;
import com.team7.model.Tile;
import com.team7.model.entity.unit.Unit;
import com.team7.view.MainScreen.CommandSelect;
import com.team7.view.MainScreen.MainViewImage;
import com.team7.view.OptionsScreen.OptionsScreen;
import com.team7.view.View;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Set;

public class PathSelectController {
    private Game game = null;
    private Map map = null;
    private View view = null;
    private OptionsScreen optionsScreen = null;
    private MainViewImage mainViewImage = null;
    private MainScreen mainScreen = null;
    private MainViewMiniMap miniMap = null;
    private CommandSelect commandView = null;

    public static boolean isRecording = false;
    Tile selectedTile = null;
    Tile startTile = null;
    ConfigReader configReader;

    ArrayList<Tile> pathTile = new ArrayList<Tile>();;

    private int moveAnimationSpeed = 10 * 30;

    public PathSelectController(Game game, View view) {
        this.game = game;
        this.map = game.getMap();
        this.view = view;
        this.optionsScreen = view.getOptionScreen();
        this.mainViewImage = view.getMainViewImage();
        this.mainScreen = view.getMainScreen();
        this.miniMap = view.getMainScreen().getMiniMap();
        this.commandView = view.getCommandSelect();


         configReader = new ConfigReader();

        commandView.setController(this);
    }
    // ===============================================

    public void moveCursor(String direction) {

        selectedTile.isSelectedPath = false;

        if(direction.equals(configReader.getValueByKey(game.getCurrentPlayer().getName(), "Northwest"))) {
            selectedTile = map.moveUnit(selectedTile, 7);
        }
        else if(direction.equals(configReader.getValueByKey(game.getCurrentPlayer().getName(), "Southwest"))) {
            selectedTile = map.moveUnit(selectedTile, 1);
        }
        else if(direction.equals(configReader.getValueByKey(game.getCurrentPlayer().getName(), "South"))) {
            selectedTile = map.moveUnit(selectedTile, 2);
        }
        else if(direction.equals(configReader.getValueByKey(game.getCurrentPlayer().getName(), "North"))) {
            selectedTile = map.moveUnit(selectedTile, 8);
        }
        else if(direction.equals(configReader.getValueByKey(game.getCurrentPlayer().getName(), "Northeast"))) {
            selectedTile = map.moveUnit(selectedTile, 9);
        }
        else if(direction.equals(configReader.getValueByKey(game.getCurrentPlayer().getName(), "Southeast"))) {
            selectedTile = map.moveUnit(selectedTile, 3);
        }

        pathTile.add(selectedTile);
        selectedTile.isSelectedPath = true;
        mainViewImage.reDrawMap();
    }

    public void startRecordingPath(Tile startTile) {

        pathTile.clear();
        this.startTile = startTile;

        if( selectedTile != null )
            selectedTile.isSelectedPath = false;

        isRecording = true;
        selectedTile = startTile;
        startTile.isSelectedPath = true;
        mainViewImage.reDrawMap();
    }

    public void drawPath(Unit unit){

        moveAnimationSpeed = optionsScreen.getUnitSpeed() * 20;

        if(unit == null)
            return;

        startTile.removeUnitFromTile(unit);
        startTile.isSelectedPath = false;

        new Thread( new Runnable() {
                public void run() {

                    for(Tile tile: pathTile)
                        tile.isSelectedPath = false;

                    for (int i = 0; i < pathTile.size(); i++) {
                        game.getCurrentPlayer().moveUnit(unit, pathTile.get(i)); //  move the unit
                        game.updateTileGameState();
                        miniMap.setMiniMapImage( mainViewImage.getFullMapImage() );
                        SwingUtilities.invokeLater(new Runnable()   // queue frame i on EDT for display
                        {
                            public void run() {
                                mainViewImage.reDrawMap();
                            }
                        });
                        try {
                            Thread.sleep(moveAnimationSpeed);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if(i == pathTile.size() - 1)
                            mainViewImage.zoomToDestination( pathTile.get(pathTile.size() - 1).getxCoordinate() - 11/2, pathTile.get(pathTile.size() - 1).getyCoordinate() - 16/2, 30  );

                    }
                }
        }).start();

    }

    public void zoomToTile( Tile tile ) {
        if(tile == null)
            return;
        view.getMainViewImage().zoomToDestination(tile.getxCoordinate() - 11 / 2, tile.getyCoordinate() - 16 / 2, optionsScreen.getFocusSpeed());
    }

}
