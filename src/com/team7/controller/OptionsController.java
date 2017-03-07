package com.team7.controller;

import com.team7.ConfigurableControls.ConfigReader;
import com.team7.model.Game;
import com.team7.view.OptionsScreen.OptionsScreen;
import com.team7.view.View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * TODO figure out a way to keep special key cases configurable (ie arrow keys) if needed
 * Actionlisteners and interaction for ConfigReader/OptionsScreen
 */
public class OptionsController{
    private OptionsScreen optionsScreen;
    private ConfigReader reader;
    private View view;
    private Game game ;

    public OptionsController(View view, Game game) {
        this.view = view;
        this.game = game;
        this.optionsScreen = view.getOptionScreen();
        reader = new ConfigReader();
        addActionListeners();

        reloadControls(game.getCurrentPlayer().getName());
    }

    private void addActionListeners() {
        optionsScreen.getChangeControlButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == optionsScreen.getChangeControlButton()) {

                    //get currently selected key:value
                    String selectedString = optionsScreen.getControlsList().getSelectedValue();
                    int colonIndex = selectedString.indexOf(':');
                    String key = selectedString.substring(0, colonIndex);
                    String popupMessage = "Change " + key + " *note only one input character alllowed!";

                    if(!key.isEmpty()){
                        String input = JOptionPane.showInputDialog(optionsScreen.getParent(), popupMessage, null);
                        if (input != null){ //user pressed OK, and did not press CANCEL
                            System.out.println(input);
                            //get first char from input
                            String newValue = input.substring(0,1);
                            reader.changeValueByKey(game.getCurrentPlayer().getName(), key, newValue);
                            reloadControls(game.getCurrentPlayer().getName());
                        }
                    }
                }
            }
        });

        optionsScreen.getResetControlsButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == optionsScreen.getResetControlsButton()) {
                    //reset player's config file by overwriting with a default file
                    reader.resetToDefault(game.getCurrentPlayer().getName());
                    reloadControls(game.getCurrentPlayer().getName());
                }
            }
        });




       optionsScreen.getMainScreenButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == optionsScreen.getMainScreenButton() )
                    view.setCurrScreen("MAIN");
            }
        });
        optionsScreen.getStructureScreen().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == optionsScreen.getStructureScreen() )
                    view.setCurrScreen("STRUCTURE_OVERVIEW");
            }
        });


    }

    public void reloadControls(String player){
        optionsScreen.setModel(reader.getAllControlsByPlayer(player));
    }

}
