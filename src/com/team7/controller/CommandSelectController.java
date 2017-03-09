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

        if(currMode == 2 && currType == 0) {        // EXPLORER
            return game.getCurrentPlayer().getExplorer( id );
        }
        else if(currMode == 2 && currType == 1) {        // COLONIST
            return game.getCurrentPlayer().getColonist( id );
        }
        else if(currMode == 2 && currType == 2) {        // RANGED UNIT
            return game.getCurrentPlayer().getRanged( id );
        }
        else if(currMode == 2 && currType == 3) {        // MELEE UNIT
            return game.getCurrentPlayer().getMelee( id );
        }
        else
            return null;

    }

    public void updateStatusView(int currMode, int currType, int id) {
        view.getMainScreen().getMainViewInfo().updateStats( getCurrentSelection(currMode, currType, id) );
    }


}
