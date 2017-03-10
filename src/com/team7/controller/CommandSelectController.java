package com.team7.controller;


import com.team7.model.Game;
import com.team7.model.Map;
import com.team7.model.Player;
import com.team7.model.Tile;
import com.team7.model.entity.unit.Unit;
import com.team7.view.MainScreen.MainScreen;
import com.team7.view.MainScreen.MainViewImage;
import com.team7.view.MainScreen.MainViewInfo;
import com.team7.view.MainScreen.MainViewMiniMap;
import com.team7.view.OptionsScreen.OptionsScreen;
import com.team7.view.View;

import java.util.Set;

public class CommandSelectController {

    private Game game = null;
    private View view = null;
    private Player currentPlayer = null;
    private MainViewInfo mainViewInfo = null;
    private MainViewImage mainViewImage = null;
    private OptionsScreen optionsScreen = null;


    public CommandSelectController(Game game, View view) {
        this.game = game;
        this.view = view;
        this.mainViewImage = view.getMainViewImage();
        this.mainViewInfo = view.getMainViewInfo();
        this.currentPlayer = game.getCurrentPlayer();
        this.optionsScreen = view.getOptionScreen();
        view.getMainScreen().getCommandSelect().setController( this );
    }

    // ===============================================

    public int getNumExplorers() {
        return currentPlayer.getNumExplorers();
    }
    public int getNumColonist() {
        return currentPlayer.getNumColonist();
    }
    public int getNumMelee() {
        return currentPlayer.getNumMelee();
    }
    public int getNumRanged() {
        return currentPlayer.getNumRanged();
    }

    public Unit getCurrentSelection(int currMode, int currType, int id) {

        //  game.getCurrentPlayer().printUnitIds();
        Unit currSelection = null;

        if(currMode == 2 && currType == 0) {        // EXPLORER
            currSelection = currentPlayer.getExplorer( id );
        }
        else if(currMode == 2 && currType == 1) {        // COLONIST
            currSelection = currentPlayer.getColonist( id );
        }
        else if(currMode == 2 && currType == 2) {        // RANGED UNIT
            currSelection = currentPlayer.getRanged( id );
        }
        else if(currMode == 2 && currType == 3) {        // MELEE UNIT
            currSelection = currentPlayer.getMelee( id );
        }

        return currSelection;
    }

    public void updateStatusView(int currMode, int currType, int id) {
        mainViewInfo.updateStats( getCurrentSelection(currMode, currType, id) );
    }

    public void zoomToCurrSelection( int currMode, int currType, int currTypeInstance ) {
        Unit unit = getCurrentSelection(  currMode, currType, currTypeInstance );
        if(unit == null)
            return;
        mainViewImage.zoomToDestination(unit.getLocation().getxCoordinate() - 11 / 2, unit.getLocation().getyCoordinate() - 16 / 2, optionsScreen.getFocusSpeed());
    }

}
