package com.team7.view.StructureScreen;

import com.team7.view.ScreenSelectButtons;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class StructureScreen extends JPanel {

    private ScreenSelectButtons screenSelectBtns = null;

    //Lists
    private JList structureList = new JList();
    private JList structureQueueList = new JList();

    //List models
    private DefaultListModel<String> strucListModel = new DefaultListModel<>();
    private DefaultListModel<String> strucQueueListModel = new DefaultListModel<>();

    //Buttons
    private JButton cancelCommand = new JButton("CANCEL COMMAND");
    private JButton moveOrderUp = new JButton("⟰");
    private JButton moveOrderDown = new JButton("⟱");

    //TextAreas
    private JTextArea statsTextArea = new JTextArea();
    private JTextArea statusTextArea = new JTextArea();

    //Labels
    private JLabel structureListLabel = new JLabel("Structures");
    private JLabel queueLabel = new JLabel("Command Queue");
    private JLabel statsLabel = new JLabel("Stats");

    //ScrollPanes
    private JScrollPane strucScrollList = new JScrollPane(structureList);
    private JScrollPane queueScrollList = new JScrollPane(structureQueueList);

    //Borders
    private Border coloredLine = BorderFactory.createLineBorder(new Color(0xffCABD80), 3);
    

    public StructureScreen() {
        //Add Screen Select btns
        screenSelectBtns = new ScreenSelectButtons();
        this.add(screenSelectBtns, BorderLayout.NORTH);
        this.setBorder(BorderFactory.createLineBorder(new Color(0xffCABD80), 3));

        JPanel structureOverview = new JPanel(new GridLayout(1,2));

        //Add Structure info Pane
        JPanel structureInfoPane = new JPanel(new GridLayout(1, 3));

        JPanel strucListPanel = new JPanel(new BorderLayout());
        strucListPanel.add(structureListLabel, BorderLayout.NORTH);
        strucListPanel.add(strucScrollList, BorderLayout.CENTER);

        JPanel queueListPanel = new JPanel(new BorderLayout());
        JPanel queueButtonsPane = new JPanel(new GridLayout(1,3));
        queueButtonsPane.add(moveOrderUp);
        queueButtonsPane.add(cancelCommand);
        queueButtonsPane.add(moveOrderDown);
        queueListPanel.add(queueLabel, BorderLayout.NORTH);
        queueListPanel.add(queueScrollList, BorderLayout.CENTER);
        queueListPanel.add(queueButtonsPane, BorderLayout.SOUTH);

        JPanel statsPanel = new JPanel(new BorderLayout());
        statsPanel.add(statsLabel, BorderLayout.NORTH);
        statsPanel.add(statsTextArea, BorderLayout.CENTER);

        structureInfoPane.add(strucListPanel);
        structureInfoPane.add(queueListPanel);
        structureInfoPane.add(statsPanel);

        structureOverview.add(structureInfoPane);
        structureInfoPane.setBorder(BorderFactory.createTitledBorder(coloredLine, "Structure Info"));


        //Add Assignments Pane


    }

    public JButton getMainScreenButton() {
        return screenSelectBtns.getMainScreenButton();
    }
    public JButton getOptionScreenButton() {
        return screenSelectBtns.getOptionsScreenSelectButton();
    }
    public JButton getUnitScreenButton() {
        return screenSelectBtns.getUnitScreenButton();
    }
    public JButton getMapScreenSelectButton() {
        return screenSelectBtns.getMapScreenSelectButton();
    }
}
