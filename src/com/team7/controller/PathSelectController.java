package com.team7.controller;


import com.team7.model.Game;
import com.team7.model.Player;
import com.team7.model.Tile;
import com.team7.view.MainScreen.CommandSelect;
import com.team7.view.View;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PathSelectController {
    private Game game = null;

    private CommandSelect commandView = null;
    private View view = null;


    boolean isRecording = false;
    Tile selectedTile = null;

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
        selectedTile.isSelectedPath = true;
        view.getMainScreen().getMainViewImage().reDrawMap();
    }

    public void startRecordingPath(Tile startTile) {
        isRecording = true;
        selectedTile = startTile;
        startTile.isSelectedPath = true;
        view.getMainScreen().getMainViewImage().reDrawMap();
    }

    public Player getPlayer () {
        return game.getCurrentPlayer();
    }








}
