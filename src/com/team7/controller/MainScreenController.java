package com.team7.controller;

import com.team7.model.Game;
import com.team7.model.Player;
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


    // update status info when turn changes
    public void updatePlayerStatusInfo() {
        Player currPlayer = game.getCurrentPlayer();
        view.getMainScreen().getMainViewInfo().setLifeLabel( Integer.toString( currPlayer.getNutrients() ) );
        view.getMainScreen().getMainViewInfo().setResearchLabel( Integer.toString( currPlayer.getResearch() ) );
        view.getMainScreen().getMainViewInfo().setConstructionLabel( Integer.toString( currPlayer.getMetal() ) );
        view.getMainScreen().getMainViewInfo().setPowerLabel( Integer.toString( currPlayer.getPower() ) );
    }

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
                if (e.getSource() == view.getMainScreen().getEndTurnButton()) {
                    PathSelectController.isRecording = false;
                    game.nextTurn();
                    updatePlayerStatusInfo();
                    // BAD!!!!!!!!! TODO: fix
                    view.getMainScreen().getMainViewImage().setCurrPlayer(game.getCurrentPlayer());
                    view.getMainScreen().getMainViewInfo().updateStats(game.getCurrentPlayer().getRandomUnit());
                    Set<Tile> tiles = null;
                    view.getMainViewImage().highlightRadius(game.getMap().getTilesInRadius(game.getCurrentPlayer().getRandomUnit().getLocation(), 2, tiles));

                    view.getMainScreen().getMainViewImage().getMiniMap().setMiniMapImage( view.getMainScreen().getMainViewImage().getFullMapImage() );

                    // TODO: fix
                    view.getMainScreen().getMainViewImage().zoomToDestination(game.getCurrentPlayer().getRandomUnit().getLocation().getxCoordinate() - 11 / 2, game.getCurrentPlayer().getRandomUnit().getLocation().getyCoordinate() - 16 / 2, view.getOptionScreen().getFocusSpeed());
                    view.getMainScreen().getCommandSelect().setFocusable(true);
                    view.getMainScreen().getCommandSelect().requestFocus();
                    OptionsController optionsController = new OptionsController(view,game);
                    optionsController.reloadControls(game.getCurrentPlayer().getName());
                }
            }
        });
        view.getMainScreen().getOptionScreenButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == view.getMainScreen().getOptionScreenButton())
                    view.setCurrScreen("OPTIONS");
            }
        });


        view.getMainScreen().getExecuteCommandButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == view.getMainScreen().getExecuteCommandButton())
                    System.out.println("do command");
            }
        });



    }


}
