package com.team7.controller;

import com.team7.ConfigurableControls.ConfigReader;
import com.team7.view.OptionsScreen;

import javax.swing.*;
import javax.swing.text.View;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * TODO figure out a way to keep special key cases configurable (ie arrow keys) if needed
 * Actionlisteners and interaction for ConfigReader/OptionsScreen
 */
public class OptionsController{
    private OptionsScreen optionsScreen;
    private ConfigReader reader;

    public OptionsController(OptionsScreen optionsScreen) {
        this.optionsScreen = optionsScreen;
        reader = new ConfigReader();
        addActionListeners();

        reloadControls("playerOne");

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
                            reader.changeValueByKey("playerOne", key, newValue);
                            reloadControls("playerOne");
                        }
                    }
                }
            }
        });

        optionsScreen.getSaveControlButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == optionsScreen.getSaveControlButton()) {
                    //return to main screen
                }
            }
        });


        optionsScreen.getResetControlsButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == optionsScreen.getResetControlsButton()) {
                    //reset player's config file by overwriting with a default file
                    reader.resetToDefault("playerOne");
                    reloadControls("playerOne");
                }
            }
        });
    }

    private void reloadControls(String player){
        optionsScreen.setModel(reader.getAllControlsByPlayer("playerOne"));
    }

}
