package com.team7.view.OptionsScreen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Set;

/**
 * Used to display configurable controls
 */
public class OptionsScreen extends JPanel{

    private JButton resetControlsButton = new JButton("Reset Controls");
    private JButton changeControlButton = new JButton("Change selected control");
    private JButton saveControlButton = new JButton("Save changes and exit");

    private JList<String> controlsList = new JList<>();
    private DefaultListModel<String> defaultListModel = new DefaultListModel<>();
    private JScrollPane jScrollPane = new JScrollPane(controlsList);

    private JPanel listPanel = new JPanel(new BorderLayout());


    private JPanel buttonPanel = new JPanel(new BorderLayout());

    private JLabel label = new JLabel("Controls:");

    public OptionsScreen() {

        controlsList.setModel(defaultListModel);
        listPanel.add(jScrollPane);

        buttonPanel.add(resetControlsButton, BorderLayout.WEST);
        buttonPanel.add(changeControlButton, BorderLayout.EAST);
        buttonPanel.add(saveControlButton, BorderLayout.CENTER);

        listPanel.add(label, BorderLayout.NORTH);

        this.setLayout(new BorderLayout());
        this.add(listPanel, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);



        this.repaint();

        controlsList.setFont(new Font("Serif", Font.BOLD, 50));

    }

    //adds text to the defaultListModel
    public void setModel(HashMap<String, String> controls){
        defaultListModel.clear();

        Set<String> allKeys = controls.keySet();
        for (String key : allKeys){
            defaultListModel.addElement(key + ": " + controls.get(key) );
        }
        this.repaint();
    }


    public JButton getResetControlsButton() {
        return resetControlsButton;
    }

    public JButton getChangeControlButton() {
        return changeControlButton;
    }

    public JList<String> getControlsList() {
        return controlsList;
    }


    public JButton getSaveControlButton() {
        return saveControlButton;
    }



}
