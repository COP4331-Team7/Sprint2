package com.team7.controller;

        import com.team7.model.Game;
        import com.team7.model.Player;
        import com.team7.view.UnitScreen.UnitScreen;
        import com.team7.view.View;

        import java.awt.event.ActionEvent;
        import java.awt.event.ActionListener;


public class UnitOverviewController {

    private Player currentPlayer = null;
    private View view = null;
    private UnitScreen unitScreen = null;

    public UnitOverviewController(View view, Player p) {
        this.currentPlayer = p;
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
        unitScreen.getMapScreenSelectButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == unitScreen.getMapScreenSelectButton())
                    view.getMapScreen().setImage( view.getMainViewImage().getFullMapImage(true) );
                view.setCurrScreen("MAP_SCREEN");
            }
        });

    }

    public void setCurrentPlayer(Player p) {
        currentPlayer = p;
    }
}
