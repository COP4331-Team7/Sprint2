package com.team7.controller;

        import com.team7.model.Game;
        import com.team7.view.View;

        import java.awt.event.ActionEvent;
        import java.awt.event.ActionListener;


public class UnitOverviewController {

    private Game game = null;
    private View view = null;

    public UnitOverviewController(Game game, View view) {
        this.game = game;
        this.view = view;
        addActionListeners();
    }

    private void addActionListeners() {

        view.getUnitScreen().getMainScreenButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == view.getUnitScreen().getMainScreenButton())
                    view.setCurrScreen("MAIN");
            }
        });
        view.getUnitScreen().getStructureScreenButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == view.getUnitScreen().getStructureScreenButton())
                    view.setCurrScreen("STRUCTURE_OVERVIEW");
            }
        });
        view.getUnitScreen().getOptionScreenButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == view.getUnitScreen().getOptionScreenButton())
                    view.getOptionScreen().showScreenSelectBtns();
                view.setCurrScreen("OPTIONS");
            }
        });




    }



}
