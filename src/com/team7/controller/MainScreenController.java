package com.team7.controller;

import com.team7.model.Game;
import com.team7.model.Player;
import com.team7.model.Tile;
import com.team7.view.MainScreen.MainScreen;
import com.team7.view.MainScreen.MainViewImage;
import com.team7.view.MainScreen.MainViewInfo;
import com.team7.view.MainScreen.MainViewMiniMap;
import com.team7.view.View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

public class MainScreenController {
    private Game game = null;
    private View view = null;
    private MainScreen mainScreen = null;
    private MainViewMiniMap miniMap = null;
    private MainViewInfo mainViewInfo = null;
    private MainViewImage mainViewImage = null;


    public MainScreenController(Game game, View view) {
        this.game = game;
        this.view = view;
        this.mainViewImage = view.getMainViewImage();
        this.mainScreen = view.getMainScreen();
        this.miniMap = view.getMainScreen().getMiniMap();
        this.mainViewInfo = view.getMainViewInfo();

        addActionListeners();
        mainScreen.getEndTurnButton().doClick();
    }

    // ===============================================


    // add action listeners to Main Screen buttons
    public void addActionListeners() {

        mainScreen.getMainScreenButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == mainScreen.getMainScreenButton())
                    view.setCurrScreen("MAIN");
            }
        });
        mainScreen.getUnitScreenButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == view.getMainScreen().getUnitScreenButton())
                    view.setCurrScreen("UNIT_OVERVIEW");
            }
        });
        mainScreen.getEndTurnButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == view.getMainScreen().getEndTurnButton()) {

                    PathSelectController.isRecording = false;
                    game.nextTurn();

                    endTurnViewRefresh();

                    // TODO: fix
                    mainViewImage.zoomToDestination(game.getCurrentPlayer().getRandomUnit().getLocation().getxCoordinate() - 11 / 2, game.getCurrentPlayer().getRandomUnit().getLocation().getyCoordinate() - 16 / 2, view.getOptionScreen().getFocusSpeed());
                    giveCommandViewFocus();
                }
            }
        });
        mainScreen.getOptionScreenButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == view.getMainScreen().getOptionScreenButton())
                    view.getOptionScreen().showScreenSelectBtns();
                    view.setCurrScreen("OPTIONS");
            }
        });
        mainScreen.getStructureScreenButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == view.getMainScreen().getStructureScreenButton())
                view.setCurrScreen("STRUCTURE_OVERVIEW");
            }
        });


        mainScreen.getExecuteCommandButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == mainScreen.getExecuteCommandButton())


                    System.out.println( mainScreen.getCommandSelect().getCommand() );   // print command
                    clearCommandView();
                    giveCommandViewFocus();
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
        mainViewImage.setCurrPlayer(game.getCurrentPlayer());
        mainViewInfo.setTitle2(game.getCurrentPlayer().getName());
        miniMap.setMiniMapImage( mainViewImage.getFullMapImage() );
    }

    public void clearCommandView() {
        mainScreen.getCommandSelect().clearCommand();   // clear command view
    }

    public void giveCommandViewFocus() {
        mainScreen.getCommandSelect().setFocusable(true);
        mainScreen.getCommandSelect().requestFocus();
    }


}
