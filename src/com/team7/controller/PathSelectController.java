package com.team7.controller;


import com.team7.model.Game;
import com.team7.model.Player;
import com.team7.model.Tile;
import com.team7.model.entity.unit.Unit;
import com.team7.view.MainScreen.CommandSelect;
import com.team7.view.View;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class PathSelectController {
    private Game game = null;

    private CommandSelect commandView = null;
    private View view = null;


    boolean isRecording = false;
    Tile selectedTile = null;
    Tile startTile=null;

    ArrayList<Tile> pathTile = new ArrayList<Tile>();;

    public PathSelectController(Game game, View view) {
        this.game = game;
        this.view = view;
        this.commandView = view.getCommandSelect();
        commandView.setController(this);
    }
    // ===============================================

    public void moveCursor(int direction) {

        if (game.getMap().moveUnit(selectedTile, direction).isVisible == false  )
            return;

        selectedTile.isSelectedPath = false;
        selectedTile = game.getMap().moveUnit(selectedTile, direction);
        pathTile.add(selectedTile);
        selectedTile.isSelectedPath = true;
        view.getMainScreen().getMainViewImage().reDrawMap();
    }

    public void startRecordingPath(Tile startTile) {

        pathTile.clear();

        this.startTile = startTile;
        if( selectedTile != null ) {
            selectedTile.isSelectedPath = false;


        }

        isRecording = true;
        selectedTile = startTile;
        startTile.isSelectedPath = true;
        view.getMainScreen().getMainViewImage().reDrawMap();
    }

    public Player getPlayer () {
        return game.getCurrentPlayer();
    }

    public void drawPath(Unit unit){

        startTile.removeUnitFromTile(unit);

        new Thread( new Runnable() {
                public void run() {

                    for (Tile tile:pathTile) {

                        game.getCurrentPlayer().moveUnit(unit, tile);
                        tile.isSelectedPath = false;

                        SwingUtilities.invokeLater(new Runnable()   // queue frame i on EDT for display
                        {
                            public void run() {
                                view.getMainScreen().getMainViewImage().reDrawMap();
                            }
                        });

                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                }


            }).start();



        view.getMainScreen().getMainViewImage().reDrawMap();
       // pathTile.clear();

    }





}
