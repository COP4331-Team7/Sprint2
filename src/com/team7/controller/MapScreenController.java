package com.team7.controller;

import com.team7.model.Game;
import com.team7.view.MapScreen.MapScreen;
import com.team7.view.HomeScreen.HomeScreen;
import com.team7.view.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MapScreenController {
    private Game game = null;
    private View view = null;
    private HomeScreen homeScreen = null;
    private MapScreen mapScreen = null;

    public MapScreenController(Game game, View view) {
        this.game = game;
        this.view = view;
        this.homeScreen = view.getHomeScreen();
        this.mapScreen = view.getMapScreen();
        addActionListeners();
    }

    // ===============================================

    public void addActionListeners() {
        //screen select buttons
        mapScreen.getScreenSelectButtons().getMainScreenButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == mapScreen.getScreenSelectButtons().getMainScreenButton())
                    view.setCurrScreen("MAIN");
            }
        });
        mapScreen.getScreenSelectButtons().getUnitScreenButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == mapScreen.getScreenSelectButtons().getUnitScreenButton())
                    view.setCurrScreen("UNIT_OVERVIEW");
            }
        });
        mapScreen.getScreenSelectButtons().getOptionsScreenSelectButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == mapScreen.getScreenSelectButtons().getOptionsScreenSelectButton())
                    view.getOptionScreen().showScreenSelectBtns();
                view.setCurrScreen("OPTIONS");
            }
        });
        mapScreen.getScreenSelectButtons().getStructureScreenButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == mapScreen.getScreenSelectButtons().getStructureScreenButton())
                    view.setCurrScreen("STRUCTURE_OVERVIEW");
            }
        });
    }



}
