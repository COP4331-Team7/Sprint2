package com.team7.controller;

import com.team7.model.Game;
import com.team7.view.OptionsScreen.OptionsScreen;
import com.team7.view.StructureScreen.StructureScreen;
import com.team7.view.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class StructureOverviewController {

    private Game game = null;
    private View view = null;
    private StructureScreen structureScreen = null;
    private OptionsScreen optionsScreen = null;

    public StructureOverviewController(Game game, View view) {
        this.game = game;
        this.view = view;
        this.structureScreen = view.getStructureScreen();
        this.optionsScreen = view.getOptionScreen();
        addActionListeners();
    }

    private void addActionListeners() {

        structureScreen.getMainScreenButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == structureScreen.getMainScreenButton())
                    view.setCurrScreen("MAIN");
            }
        });
        structureScreen.getUnitScreenButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == structureScreen.getUnitScreenButton())
                    view.setCurrScreen("UNIT_OVERVIEW");
            }
        });
        structureScreen.getOptionScreenButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == structureScreen.getOptionScreenButton())
                    optionsScreen.showScreenSelectBtns();
                view.setCurrScreen("OPTIONS");
            }
        });
        structureScreen.getMapScreenSelectButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == structureScreen.getMapScreenSelectButton())
                    view.getMapScreen().setImage( view.getMainViewImage().getFullMapImage(true) );
                view.setCurrScreen("MAP_SCREEN");
            }
        });




    }



}
