package com.team7.controller;

import com.team7.model.Game;
import com.team7.view.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class StructureOverviewController {

    private Game game = null;
    private View view = null;

    public StructureOverviewController(Game game, View view) {
        this.game = game;
        this.view = view;
        addActionListeners();
    }

    private void addActionListeners() {

        view.getStructureScreen().getMainScreenButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == view.getStructureScreen().getMainScreenButton())
                    view.setCurrScreen("MAIN");
            }
        });
        view.getStructureScreen().getUnitScreenButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == view.getStructureScreen().getUnitScreenButton())
                    view.setCurrScreen("UNIT_OVERVIEW");
            }
        });
        view.getStructureScreen().getOptionScreenButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == view.getStructureScreen().getOptionScreenButton())
                    view.getOptionScreen().showScreenSelectBtns();
                view.setCurrScreen("OPTIONS");
            }
        });




    }



}
