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
 * Actionlisteners and interaction for ConfigReader/OptionsScreen
 */
public class OptionsController{
    private OptionsScreen optionsScreen;
    private ConfigReader reader;

    public OptionsController(OptionsScreen optionsScreen) {
        this.optionsScreen = optionsScreen;
        reader = new ConfigReader();
        addActionListeners();

        optionsScreen.setModel(reader.getAllControlsByPlayer("playerOne"));
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
                    String newControlValueToParse = optionsScreen.getKeyInputArea();
                    System.out.println(newControlValueToParse);
                    int lastColonIndex = newControlValueToParse.lastIndexOf(':');
                    String value = newControlValueToParse.substring(lastColonIndex, newControlValueToParse.length());
                    System.out.println(value);
                    //change properties files
                  //  reader.changeValueByKey("playerOne", key, newControlValue);
                }
            }
        });
    }

}
