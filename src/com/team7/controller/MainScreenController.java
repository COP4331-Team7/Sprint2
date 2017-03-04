package com.team7.controller;

import com.team7.model.Game;
import com.team7.model.Tile;
import com.team7.view.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

public class MainScreenController {
    private Game game = null;
    private View view = null;

    public MainScreenController(Game game, View view) {
        this.game = game;
        this.view = view;
        addActionListeners();
    }

    // ===============================================

    // add action listeners to Main Screen buttons
    public void addActionListeners() {
        view.getMainScreen().getMainScreenButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == view.getMainScreen().getMainScreenButton())
                    view.setCurrScreen("MAIN");
            }
        });
        view.getMainScreen().getUnitScreenButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == view.getMainScreen().getUnitScreenButton())
                    view.setCurrScreen("UNIT_OVERVIEW");
            }
        });
        view.getMainScreen().getEndTurnButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == view.getMainScreen().getEndTurnButton())
                    game.nextTurn();
                      Set<Tile> tiles = null;
                     view.getMainViewImage().highlightRadius( game.getMap().getTilesInRadius(game.getCurrentPlayer().getRandomUnit().getLocation(), 3, tiles));
                    view.getMainScreen().getMainViewImage().zoomToDestination( game.getCurrentPlayer().getRandomUnit().getLocation().getxCoordinate() - 11/2, game.getCurrentPlayer().getRandomUnit().getLocation().getyCoordinate() - 16/2, 75  );
                    view.getMainScreen().getCommandSelect().setFocusable(true);
                    view.getMainScreen().getCommandSelect().requestFocus();

            }
        });

        view.getMainScreen().getExecuteCommandButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == view.getMainScreen().getExecuteCommandButton())
                    System.out.println("execute command");
            }
        });

    }


}
