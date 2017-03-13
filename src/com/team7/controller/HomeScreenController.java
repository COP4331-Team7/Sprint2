package com.team7.controller;

import com.team7.model.Game;
import com.team7.view.HomeScreen.HomeScreen;
import com.team7.view.OptionsScreen.OptionsScreen;
import com.team7.view.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeScreenController {
    private Game game = null;
    private View view = null;
    private HomeScreen homeScreen = null;
    private OptionsScreen optionsScreen = null;

    public HomeScreenController(Game game, View view) {
        this.game = game;
        this.view = view;
        this.homeScreen = view.getHomeScreen();
        this.optionsScreen = view.getOptionScreen();
        addActionListeners();
    }

    // ===============================================

    public void addActionListeners() {
        homeScreen.getQuitButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == homeScreen.getQuitButton())
                    System.exit( 0 );
            }
        });
        homeScreen.getPlayButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == homeScreen.getPlayButton())
                    view.setCurrScreen("MAIN");
            }
        });
        homeScreen.getOptionButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == homeScreen.getOptionButton())
                    optionsScreen.showHomeScreenOnly();
                    view.setCurrScreen("OPTIONS");
            }
        });
    }



}
