package com.team7.controller;

import com.team7.model.Game;
import com.team7.model.Player;
import com.team7.view.MainScreen.MainScreen;
import com.team7.view.MainScreen.MainViewImage;
import com.team7.view.MainScreen.MainViewInfo;
import com.team7.view.MainScreen.MainViewMiniMap;
import com.team7.view.OptionsScreen.OptionsScreen;
import com.team7.view.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainScreenController {
    private Game game = null;
    private View view = null;
    private MainScreen mainScreen = null;
    private MainViewMiniMap miniMap = null;
    private MainViewInfo mainViewInfo = null;
    private MainViewImage mainViewImage = null;
    private OptionsScreen optionScreen = null;


    public MainScreenController(Game game, View view) {
        this.game = game;
        this.view = view;
        this.mainViewImage = view.getMainViewImage();
        this.mainScreen = view.getMainScreen();
        this.miniMap = view.getMainScreen().getMiniMap();
        this.mainViewInfo = view.getMainViewInfo();
        this.optionScreen = view.getOptionScreen();

        addActionListeners();
        view.setMap( game.getMap() );
        mainScreen.getEndTurnButton().doClick();
    }

    // ===============================================


    // add action listeners to Main Screen buttons
    public void addActionListeners() {

        // end turn
        mainScreen.getEndTurnButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == mainScreen.getEndTurnButton()) {

                    PathSelectController.isRecording = false;
                    game.nextTurn();

                    endTurnViewRefresh();

                    // TODO: fix
                    mainViewImage.zoomToDestination(game.getCurrentPlayer().getRandomUnit().getLocation().getxCoordinate() - 11 / 2, game.getCurrentPlayer().getRandomUnit().getLocation().getyCoordinate() - 16 / 2, view.getOptionScreen().getFocusSpeed());
                    giveCommandViewFocus();
                }
            }
        });

        // execute command
        mainScreen.getExecuteCommandButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == mainScreen.getExecuteCommandButton())


                    System.out.println( mainScreen.getCommandSelect().getCommand() );   // print command
                clearCommandView();
                giveCommandViewFocus();
            }
        });

        //screen select buttons
        mainScreen.getMainScreenButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == mainScreen.getMainScreenButton())
                    view.setCurrScreen("MAIN");
            }
        });
        mainScreen.getUnitScreenButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == mainScreen.getUnitScreenButton())
                    view.setCurrScreen("UNIT_OVERVIEW");
            }
        });
        mainScreen.getOptionScreenButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == mainScreen.getOptionScreenButton())
                    optionScreen.showScreenSelectBtns();
                    view.setCurrScreen("OPTIONS");
            }
        });
        mainScreen.getStructureScreenButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == mainScreen.getStructureScreenButton())
                view.setCurrScreen("STRUCTURE_OVERVIEW");
            }
        });

    }

    // update status info view when turn changes
    public void updatePlayerStatusInfo() {
        Player currPlayer = game.getCurrentPlayer();
        mainViewInfo.setLifeLabel( Integer.toString( currPlayer.getNutrients() ) );
        mainViewInfo.setResearchLabel( Integer.toString( currPlayer.getResearch() ) );
        mainViewInfo.setConstructionLabel( Integer.toString( currPlayer.getMetal() ) );
        mainViewInfo.setPowerLabel( Integer.toString( currPlayer.getPower() ) );
    }

    public void endTurnViewRefresh() {
        updatePlayerStatusInfo();
        clearCommandView();
        clearStatusInfoView();
        mainViewImage.setCurrPlayer(game.getCurrentPlayer());
        mainViewInfo.setPlayerNameLabel(game.getCurrentPlayer().getName());
        miniMap.setMiniMapImage( mainViewImage.getFullMapImage() );
    }

    public void clearCommandView() {
        mainScreen.getCommandSelect().clearCommand();
    }

    public void clearStatusInfoView() {
        mainViewInfo.clearStats();
    }


    public void giveCommandViewFocus() {
        mainScreen.getCommandSelect().setFocusable(true);
        mainScreen.getCommandSelect().requestFocus();
    }


}
