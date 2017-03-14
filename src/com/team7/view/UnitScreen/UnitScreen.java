package com.team7.view.UnitScreen;

import com.team7.view.ScreenSelectButtons;

import javax.swing.*;
import java.awt.*;

public class UnitScreen extends JPanel {
    //Lists
    private JList unitList = new JList<>();
    private JList queue = new JList<>();
    private JList armyList = new JList<String>();
    private JList unitsInArmyList = new JList<>();

    //JScrollPanes
    private JScrollPane jScrollPane;
    private JScrollPane queueScrollPane;

    //List Models
    DefaultListModel<String> armyModel = new DefaultListModel<String>();
    DefaultListModel<String> queueModel = new DefaultListModel<String>();
    DefaultListModel<String> unitModel = new DefaultListModel<>();
    DefaultListModel<String> armyContentsModel = new DefaultListModel<>();

    //Buttons
    private JButton addArmy = new JButton("CREATE ARMY");
    private JButton decomissionArmy = new JButton("DISBAND ARMY");
    private JButton addUnit = new JButton("ADD UNIT");
    private JButton removeUnit = new JButton("REMOVE UNIT");
    private JButton cancelCommand = new JButton("CANCEL COMMAND");
    private JButton moveOrderUp = new JButton("⟰");
    private JButton moveOrderDown = new JButton("⟱");
    private ScreenSelectButtons screenSelectBtns = null;

    //JTextAreas
    private JTextArea textArea = new JTextArea();

    //Fonts
    private Font titleFont = new Font("Serif", Font.BOLD, 30);

    //Colors
    private Color lightColor = new Color(0xffF5F5DC);
    private Color darkColor = new Color(0xffCABD80);

    public UnitScreen() {

        screenSelectBtns = new ScreenSelectButtons();
        this.add(screenSelectBtns, BorderLayout.NORTH);
        this.setBorder(BorderFactory.createLineBorder(new Color(0xffCABD80), 3));
    }


    public JButton getMainScreenButton() {
        return screenSelectBtns.getMainScreenButton();
    }
    public JButton getOptionScreenButton() {
        return screenSelectBtns.getOptionsScreenSelectButton();
    }
    public JButton getStructureScreenButton() {
        return screenSelectBtns.getStructureScreenButton();
    }
    public JButton getMapScreenSelectButton() {
        return screenSelectBtns.getMapScreenSelectButton();
    }
}
