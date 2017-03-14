package com.team7.controller;

import com.team7.model.Game;
import com.team7.model.Player;
import com.team7.view.OptionsScreen.OptionsScreen;
import com.team7.view.StructureScreen.StructureScreen;
import com.team7.view.View;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class StructureOverviewController {

    private Player currentPlayer = null;
    private View view = null;
    private StructureScreen structureScreen = null;
    private OptionsScreen optionsScreen = null;

    public StructureOverviewController(View view, Player p) {
        this.view = view;
        this.currentPlayer = p;
        this.structureScreen = view.getStructureScreen();
        this.optionsScreen = view.getOptionScreen();
        structureScreen.setStructureModel(p.getStructures());
        addActionListeners();
    }

    private void addActionListeners() {

        //Screen Select Buttons
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

        //Action Listener for Structure List
        //TODO -- Structures don't have command queues
        structureScreen.addStructureListActionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e.getSource() == structureScreen.getStructureList()) {
                    structureScreen.updatedStructureSelection(currentPlayer.getStructures());
                }
            }
        });

        //ActionListeners for Queue Manipulation Buttons
        //TODO -- Structures don't have command queues
        structureScreen.addCommandUpActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == structureScreen.getMoveOrderUp()) {
                    String selected_value = (String) structureScreen.getStrucQueueList().getSelectedValue();

                }
            }
        });


    }



    public void setCurrentPlayer(Player p) {
        currentPlayer = p;
    }



}
