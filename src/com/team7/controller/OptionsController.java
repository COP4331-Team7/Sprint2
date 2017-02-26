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
public class OptionsController implements KeyListener{
    private OptionsScreen optionsScreen;
    private ConfigReader reader;

    public OptionsController(OptionsScreen optionsScreen) {
        this.optionsScreen = optionsScreen;
        reader = new ConfigReader();
        addActionListeners();

        optionsScreen.setModel(reader.getAllControlsByPlayer("PlayerOne"));

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
                    String newControlValue = optionsScreen.getKeyInputArea();

                    //change properties files
                    reader.changeValueByKey(key, newControlValue);
                }
            }
        });
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println(e.getKeyChar());
        System.out.println(e.getKeyCode());
        optionsScreen.changeKeyInputArea(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
