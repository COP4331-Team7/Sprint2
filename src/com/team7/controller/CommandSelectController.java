package com.team7.controller;


import com.team7.model.Game;
import com.team7.model.Map;
import com.team7.model.Tile;
import com.team7.model.entity.unit.Unit;
import com.team7.view.View;

import java.util.Set;

public class CommandSelectController {

    private Game game = null;
    private View view = null;

    public CommandSelectController(Game game, View view) {
        this.game = game;
        this.view = view;
        view.getMainScreen().getCommandSelect().setController( this );
    }

    // ===============================================

    public int getNumExplorers() {
        return game.getCurrentPlayer().getNumExplorers();
    }
    public int getNumColonist() {
        return game.getCurrentPlayer().getNumColonist();
    }
    public int getNumMelee() {
        return game.getCurrentPlayer().getNumMelee();
    }
    public int getNumRanged() {
        return game.getCurrentPlayer().getNumRanged();
    }

    public Unit getCurrentSelection(int currMode, int currType, int id) {

        game.getCurrentPlayer().printUnitIds();

        Unit currSelection = null;

        if(currMode == 2 && currType == 0) {        // EXPLORER
            currSelection = game.getCurrentPlayer().getExplorer( id );
        }
        else if(currMode == 2 && currType == 1) {        // COLONIST
            currSelection = game.getCurrentPlayer().getColonist( id );
        }
        else if(currMode == 2 && currType == 2) {        // RANGED UNIT
            currSelection = game.getCurrentPlayer().getRanged( id );
        }
        else if(currMode == 2 && currType == 3) {        // MELEE UNIT
            currSelection = game.getCurrentPlayer().getMelee( id );
        }

        return currSelection;

    }

    public void updateStatusView(int currMode, int currType, int id) {
        view.getMainScreen().getMainViewInfo().updateStats( getCurrentSelection(currMode, currType, id) );
    }

    public void zoomToCurrSelection( int currMode, int currType, int currTypeInstance ) {
        Unit unit = getCurrentSelection(  currMode, currType, currTypeInstance );
        if(unit == null)
            return;
        view.getMainViewImage().zoomToDestination(unit.getLocation().getxCoordinate() - 11 / 2, unit.getLocation().getyCoordinate() - 16 / 2, view.getOptionScreen().getFocusSpeed());
    }


}
