package com.team7.controller;

        import com.team7.model.Game;
        import com.team7.view.UnitScreen.UnitScreen;
        import com.team7.view.View;

        import java.awt.event.ActionEvent;
        import java.awt.event.ActionListener;


public class UnitOverviewController {

    private Game game = null;
    private View view = null;
    private UnitScreen unitScreen = null;

    public UnitOverviewController(Game game, View view) {
        this.game = game;
        this.view = view;
        this.unitScreen = view.getUnitScreen();
        addActionListeners();
    }

    private void addActionListeners() {

        unitScreen.getMainScreenButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == unitScreen.getMainScreenButton())
                    view.setCurrScreen("MAIN");
            }
        });
        unitScreen.getStructureScreenButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == unitScreen.getStructureScreenButton())
                    view.setCurrScreen("STRUCTURE_OVERVIEW");
            }
        });
        unitScreen.getOptionScreenButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == unitScreen.getOptionScreenButton())
                    view.getOptionScreen().showScreenSelectBtns();
                view.setCurrScreen("OPTIONS");
            }
        });

    }
}
