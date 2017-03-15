package com.team7.controller;


import com.team7.model.Map;
import com.team7.model.Player;
import com.team7.view.MainScreen.MainScreen;
import com.team7.view.MainScreen.MainViewMiniMap;
import com.team7.view.OptionsScreen.ConfigurableControls.ConfigReader;
import com.team7.model.Game;
import com.team7.model.Tile;
import com.team7.model.entity.unit.Unit;
import com.team7.view.MainScreen.CommandSelect;
import com.team7.view.MainScreen.MainViewImage;
import com.team7.view.OptionsScreen.OptionsScreen;
import com.team7.view.View;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Set;

public class PathSelectController {
    private Game game = null;
    private Map map = null;
    private View view = null;
    private OptionsScreen optionsScreen = null;
    private MainViewImage mainViewImage = null;
    private MainViewMiniMap miniMap = null;
    private CommandSelect commandView = null;

    public static boolean isRecording = false;
    private Tile selectedTile = null;
    private Tile startTile = null;
    private Tile destTile = null;
    private ConfigReader configReader;
    private ArrayList<Tile> pathTile = new ArrayList<Tile>();;
    private int moveLimit;
    private int moveAnimationSpeed = 10 * 30;

    public PathSelectController(Game game, View view) {
        this.game = game;
        this.map = game.getMap();
        this.view = view;
        this.optionsScreen = view.getOptionScreen();
        this.mainViewImage = view.getMainViewImage();
        this.miniMap = view.getMainScreen().getMiniMap();
        this.commandView = view.getCommandSelect();

        Player[] players = this.game.getPlayers();
        players[0].setMovementController(this);
        players[1].setMovementController(this);

        configReader = new ConfigReader();

        commandView.setController(this);
    }
    // ===============================================

    public void moveCursor(String direction) {

//        if(pathTile.size() >= moveLimit)
//            return;

//        selectedTile.isSelectedPath = false;

        if(direction.equals(configReader.getValueByKey(game.getCurrentPlayer().getName(), "Northwest"))) {
            selectedTile = map.getAdjacentTile(selectedTile, 7);
        }
        else if(direction.equals(configReader.getValueByKey(game.getCurrentPlayer().getName(), "Southwest"))) {
            selectedTile = map.getAdjacentTile(selectedTile, 1);
        }
        else if(direction.equals(configReader.getValueByKey(game.getCurrentPlayer().getName(), "South"))) {
            selectedTile = map.getAdjacentTile(selectedTile, 2);
        }
        else if(direction.equals(configReader.getValueByKey(game.getCurrentPlayer().getName(), "North"))) {
            selectedTile = map.getAdjacentTile(selectedTile, 8);
        }
        else if(direction.equals(configReader.getValueByKey(game.getCurrentPlayer().getName(), "Northeast"))) {
            selectedTile = map.getAdjacentTile(selectedTile, 9);
        }
        else if(direction.equals(configReader.getValueByKey(game.getCurrentPlayer().getName(), "Southeast"))) {
            selectedTile = map.getAdjacentTile(selectedTile, 3);
        }

        destTile = selectedTile;

        pathTile.add(selectedTile);
        selectedTile.isSelectedPath = true;
        mainViewImage.reDrawMap();
    }

    public ArrayList<Tile> getPath(Tile start, Tile destination, Set<Tile> openList, Set<Tile> closedList){





        return null;
    }

    public void startRecordingPath(Tile startTile, int unitMovement) {

        moveLimit = unitMovement;

        pathTile.clear();
        this.startTile = startTile;

        if( selectedTile != null )
            selectedTile.isSelectedPath = false;

        isRecording = true;
        selectedTile = startTile;
        startTile.isSelectedPath = true;
        mainViewImage.zoomToDestination( startTile.getxCoordinate() - 11/2, startTile.getyCoordinate() - 16/2, 30);
    }

    public void drawPath(Unit unit){

        moveAnimationSpeed = optionsScreen.getUnitSpeed() * 20;

        if(unit == null || pathTile.size() == 0)
            return;



        startTile.removeUnitFromTile(unit);
        startTile.isSelectedPath = false;

        new Thread( new Runnable() {
                public void run() {

                    for (Tile tile : pathTile)
                        tile.isSelectedPath = false;

                    for (int i = 0; i < pathTile.size(); i++) {
                        if (!game.getCurrentPlayer().moveUnit(unit, pathTile.get(i))) {
//                            game.updateCurrPlayerTileStates();
                            mainViewImage.reDrawMap();
                            break;
                            }
                        else {// move the unit
                            game.updateCurrPlayerTileStates();
                            miniMap.setMiniMapImage(mainViewImage.getFullMapImage(false));

                            final BufferedImage mapSubsection = mainViewImage.drawSubsectionOfMap();
                            SwingUtilities.invokeLater(new Runnable()   // queue frame i on EDT for display
                            {
                                public void run() {
                                    mainViewImage.setImage(mapSubsection);
                                }
                            });
                            try {
                                Thread.sleep(moveAnimationSpeed);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                            if (i == pathTile.size() - 1)
                                mainViewImage.zoomToDestination(pathTile.get(pathTile.size() - 1).getxCoordinate() - 11 / 2, pathTile.get(pathTile.size() - 1).getyCoordinate() - 16 / 2, 30);
                        }
                    }
                }
        }).start();



    }

    public void zoomToTile( Tile tile ) {
        if(tile == null)
            return;
        view.getMainViewImage().zoomToDestination(tile.getxCoordinate() - 11 / 2, tile.getyCoordinate() - 16 / 2, optionsScreen.getFocusSpeed());
    }

    public void reDraw() {
        mainViewImage.reDrawMap();
    }

    public Tile getDestinationTile() {
        return destTile;
    }

}
