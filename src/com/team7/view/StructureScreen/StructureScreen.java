package com.team7.view.StructureScreen;

import com.team7.model.Player;
import com.team7.model.entity.CommandQueue;
import com.team7.model.entity.structure.Structure;
import com.team7.view.ScreenSelectButtons;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class StructureScreen extends JPanel {

    private ScreenSelectButtons screenSelectBtns = null;

    //Panels
    private JPanel spacer = new JPanel();

    //Lists
    private JList structureList;
    private JList structureQueueList;

    //Models
    private DefaultListModel<String> strucListModel = new DefaultListModel<>();
    private DefaultListModel<String> strucQueueListModel = new DefaultListModel<>();
    private DefaultComboBoxModel<String> dropDownModel = new DefaultComboBoxModel<>();
    private SpinnerNumberModel spinnerModel = new SpinnerNumberModel(0,0,100,1);

    //Buttons
    private JButton cancelCommand;
    private JButton moveOrderUp;
    private JButton moveOrderDown;
    private JButton assignment;

    //TextAreas
    private JTextArea statsTextArea;
    private JTextArea statusTextArea;
    private JTextArea availibleRes;

    //Labels
    private JLabel structureListLabel;
    private JLabel queueLabel;
    private JLabel statsLabel;

    private JScrollPane queueScrollList;

    //Borders
    private Border coloredLine;

    //ComboBoxes (Drop down menus)
    private JComboBox dropDownBox;

    //Spinners (Number input)
    private JSpinner allocationInput;

    //Fonts
    private Font titleFont = new Font("Serif", Font.BOLD, 30);

    //Colors
    private Color lightColor = new Color(0xffF5F5DC);
    private Color darkColor = new Color(0xffCABD80);
    

    public StructureScreen() {
        //Add Screen Select btns
        screenSelectBtns = new ScreenSelectButtons();
        this.setOpaque(true);
        this.setBackground(darkColor);
        this.add(screenSelectBtns, BorderLayout.NORTH);
        this.setBorder(BorderFactory.createLineBorder(new Color(0xffCABD80), 3));

        JPanel structureOverview = new JPanel(new GridLayout(1,2));

        //Add Structure info Pane
        JPanel structureInfoPane = new JPanel(new GridLayout(1, 3));

        JPanel strucListPanel = new JPanel(new BorderLayout());
        structureListLabel = new JLabel("Structures");
        structureListLabel.setFont(titleFont);
        strucListPanel.add(structureListLabel, BorderLayout.NORTH);
        structureList = new JList();
        JScrollPane strucScrollList = new JScrollPane(structureList);
        strucListPanel.add(strucScrollList, BorderLayout.CENTER);

        JPanel queueListPanel = new JPanel(new BorderLayout());
        JPanel queueButtonsPane = new JPanel(new GridLayout(1,3));
        moveOrderUp = new JButton("⟰");
        queueButtonsPane.add(moveOrderUp);
        cancelCommand = new JButton("CANCEL COMMAND");
        queueButtonsPane.add(cancelCommand);
        moveOrderDown = new JButton("⟱");
        queueButtonsPane.add(moveOrderDown);
        queueLabel = new JLabel("Command Queue");
        queueListPanel.add(queueLabel, BorderLayout.NORTH);
        queueLabel.setFont(titleFont);
        structureQueueList = new JList();
        queueScrollList = new JScrollPane(structureQueueList);
        queueListPanel.add(queueScrollList, BorderLayout.CENTER);
        queueListPanel.add(queueButtonsPane, BorderLayout.SOUTH);

        JPanel statsPanel = new JPanel(new BorderLayout());
        statsLabel = new JLabel("Stats");
        statsPanel.add(statsLabel, BorderLayout.NORTH);
        statsLabel.setFont(titleFont);
        statsTextArea = new JTextArea();
        statsPanel.add(statsTextArea, BorderLayout.CENTER);

        structureInfoPane.add(strucListPanel);
        structureInfoPane.add(queueListPanel);
        structureInfoPane.add(statsPanel);

        //Colors
        structureListLabel.setOpaque(true);
        queueLabel.setOpaque(true);
        statsLabel.setOpaque(true);
        structureListLabel.setBackground(lightColor);
        queueLabel.setBackground(lightColor);
        statsLabel.setBackground(lightColor);
        moveOrderDown.setOpaque(true);
        moveOrderUp.setOpaque(true);
        cancelCommand.setOpaque(true);
        moveOrderDown.setBackground(lightColor);
        moveOrderUp.setBackground(lightColor);
        cancelCommand.setBackground(lightColor);


        structureInfoPane.setBackground(lightColor);
        coloredLine = BorderFactory.createLineBorder(new Color(0xffCABD80), 3);
        structureInfoPane.setBorder(BorderFactory.createTitledBorder(coloredLine, "Structure Info"));
        structureOverview.add(structureInfoPane);
        structureInfoPane.setPreferredSize(new Dimension((int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth())/2, (int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight()*.8)));


        //Add Assignments Pane
        JPanel assignmentsPane = new JPanel(new GridLayout(2,1));

        JPanel top = new JPanel(new GridLayout(2,1));
        JPanel statusPanel = new JPanel(new BorderLayout());
        JPanel assignmentSelectPanel = new JPanel(new GridLayout());
        statusTextArea = new JTextArea();
        statusTextArea.setBorder(BorderFactory.createTitledBorder(coloredLine, "Status"));
        statusPanel.add(statusTextArea, BorderLayout.CENTER);
        dropDownBox = new JComboBox();
        assignmentSelectPanel.add(dropDownBox);
        allocationInput = new JSpinner(spinnerModel);
        assignmentSelectPanel.add(allocationInput);
        assignment = new JButton("Assign");
        assignmentSelectPanel.add(assignment);
        top.add(statusPanel, BorderLayout.CENTER);
        top.add(assignmentSelectPanel, BorderLayout.SOUTH);

        JPanel bottom = new JPanel(new BorderLayout());
        availibleRes = new JTextArea();
        availibleRes.setBorder(BorderFactory.createTitledBorder(coloredLine, "Availible Resources"));
        bottom.add(availibleRes, BorderLayout.CENTER);

        assignmentsPane.add(top);
        assignmentsPane.add(bottom);
        structureOverview.add(assignmentsPane);

        this.add(structureOverview);
        repaint();
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

    public void addStructureListActionListener(ListSelectionListener l) {
        structureList.addListSelectionListener(l);
    }

    public void addCommandUpActionListener(ActionListener e) {
        moveOrderUp.addActionListener(e);
    }

    public void setStructureModel(ArrayList<Structure> structures) {
        strucListModel.clear();
        for (Structure s : structures) {
            strucListModel.addElement(s.getType() + " " + s.getId());
        }
    }

    public JList getStructureList() {
        return structureList;
    }

    public void setStrucQueueListModel(Structure s) {
        strucQueueListModel.clear();
    }

    public void setStatsBox(Structure s) {
        statsTextArea.setText(s.printStats());
    }

    public void updatedStructureSelection(ArrayList<Structure> structures) {
        String selectedValue = (String)structureList.getSelectedValue();

        for (Structure s: structures) {
            if (selectedValue.equals(s.getType() + " " + s.getId())) {
                setStatsBox(s);
                setComboBoxModel(s.getType());
                //setStrucQueueListModel(s.getCommands());
                //TODO -- Need a command queue in structures to continue
            }
        }
    }

    private void setComboBoxModel(String s) {
        dropDownModel.removeAllElements();
        dropDownModel.addElement("Allocate Ore");
        dropDownModel.addElement("Allocate Power");
        switch(s) {
            case "Capital":
                dropDownModel.addElement("Assign Worker: Harvest Ore");
                dropDownModel.addElement("Assign Worker: Harvest Energy");
                dropDownModel.addElement("Assign Worker: Harvest Food");
                dropDownModel.addElement("Assign Worker: Heal");
                dropDownModel.addElement("Assign Worker: Produce Explorer");
                break;
            case "Fort":
                dropDownModel.addElement("Assign Worker: Train Melee");
                dropDownModel.addElement("Assign Worker: Train Ranged");
                dropDownModel.addElement("Assign Soldier: Train Melee");
                dropDownModel.addElement("Assign Soldier: Train Ranged");
                break;
            case "Farm":
                dropDownModel.addElement("Assign Worker: Harvest Food");
                break;
            case "Mine":
                dropDownModel.addElement("Assign Worker: Harvest Ore");
                break;
            case "PowerPlant":
                dropDownModel.addElement("Assign Worker: Harvest Energy");
                break;
            case "University":
                dropDownModel.addElement("Assign Worker: Research Technology");
                break;
        }
    }

    public JButton getMoveOrderUp() {
        return moveOrderUp;
    }

    public JButton getMoveOrderDown() {
        return moveOrderDown;
    }

    public JButton getCancelCommand() {
        return cancelCommand;
    }

    public JList getStrucQueueList() {
        return structureQueueList;
    }
}
