package com.team7.controller;

import com.team7.model.Game;
import com.team7.view.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeScreenController {
    private Game game = null;
    private View view = null;

    public HomeScreenController(Game game, View view) {
        this.game = game;
        this.view = view;
        addActionListeners();
    }

    // ===============================================

    public void addActionListeners() {
        view.getHomeScreen().getQuitButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == view.getHomeScreen().getQuitButton())
                    System.exit( 0 );
            }
        });
        view.getHomeScreen().getPlayButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == view.getHomeScreen().getPlayButton())
                    view.setCurrScreen("MAIN");
            }
        });
    }



}
