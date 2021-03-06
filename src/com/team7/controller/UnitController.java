package com.team7.controller;

import com.team7.model.Game;
import com.team7.model.entity.unit.combatUnit.MeleeUnit;
import com.team7.model.entity.unit.combatUnit.RangedUnit;
import com.team7.model.entity.unit.nonCombatUnit.Colonist;
import com.team7.model.entity.unit.nonCombatUnit.Explorer;
import com.team7.view.View;

public class UnitController {
    private Game game = null;
    private View view = null;

    public UnitController(Game game, View view) {
        this.game = game;
        this.view = view;
    }

    // TODO: maybe use controller to handle creating units

    // create unit and place on the map
    public void createUnit(String type, int x, int y) {

        if(type == "Explorer")
            game.addUnitToPlayer(game.getCurrentPlayer(), new Explorer(game.getMap().getGrid()[x][x], game.getCurrentPlayer()));
        else if(type == "MeleeUnit")
            game.addUnitToPlayer(game.getCurrentPlayer(), new MeleeUnit(game.getMap().getGrid()[x][x], game.getCurrentPlayer()));
        else if(type == "RangedUnit")
            game.addUnitToPlayer(game.getCurrentPlayer(), new RangedUnit(game.getMap().getGrid()[x][x], game.getCurrentPlayer()));
        else if(type == "Colonist")
            game.addUnitToPlayer(game.getCurrentPlayer(), new Colonist(game.getMap().getGrid()[x][x], game.getCurrentPlayer()));

    }





}