package com.team7.view;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Set;

/**
 * Used to display configurable controls
 */
public class OptionsScreen extends JPanel{

    private JButton resetControlsButton = new JButton("Reset Controls");
    private JButton changeControlButton = new JButton("Change selected control");

    private JList<String> controlsList = new JList<>();
    private DefaultListModel<String> defaultListModel = new DefaultListModel<>();
    private JScrollPane jScrollPane = new JScrollPane(controlsList);

    private JPanel gridPanel = new JPanel(new GridLayout(2,1));
    private JPanel listPanel = new JPanel(new BorderLayout());

    private JPanel textPanel = new JPanel(new BorderLayout());
    private JTextArea keyInputArea = new JTextArea();

    private JPanel buttonPanel = new JPanel(new BorderLayout());

    private JLabel label = new JLabel("Controls");
    private JLabel textInputLabel = new JLabel("Enter one key");

    public OptionsScreen() {
        gridPanel.add(listPanel);
        gridPanel.add(textPanel);

        controlsList.setModel(defaultListModel);
        listPanel.add(jScrollPane);

        buttonPanel.add(resetControlsButton, BorderLayout.WEST);
        buttonPanel.add(changeControlButton, BorderLayout.EAST);

        listPanel.add(label, BorderLayout.NORTH);
        textPanel.add(keyInputArea);
        textPanel.add(textInputLabel, BorderLayout.NORTH);
       // keyInputArea.setEditable(false);

        this.setLayout(new BorderLayout());
        this.add(gridPanel, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);



        this.repaint();

        controlsList.setFont(new Font("Serif", Font.BOLD, 50));

    }

    //adds text to the defaultListModel
    public void setModel(HashMap<String, String> controls){
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

    public String getKeyInputArea() {
        return keyInputArea.getText();
    }

    public void changeKeyInputArea(int keyCode) {
        keyInputArea.setText("" + keyCode);
    }
}
