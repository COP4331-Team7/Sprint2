package com.team7.view.StructureScreen;

import com.team7.model.Player;
import com.team7.model.entity.Command;
import com.team7.model.entity.CommandQueue;
import com.team7.model.entity.structure.Structure;
import com.team7.view.ScreenSelectButtons;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class StructureScreen extends JPanel {

    private ScreenSelectButtons screenSelectBtns = null;

    //Panels
    private JPanel spacer = new JPanel();

    //Lists
    private JList structureList = new JList();
    private JList structureQueueList = new JList();

    //Models
    private DefaultListModel<String> strucListModel = new DefaultListModel<>();
    private DefaultListModel<String> strucQueueListModel = new DefaultListModel<>();
    private DefaultComboBoxModel<String> dropDownModel = new DefaultComboBoxModel<>();
    private SpinnerNumberModel spinnerModel = new SpinnerNumberModel(0,0,100,1);

    //Buttons
    private JButton cancelCommand = new JButton("CANCEL COMMAND");
    private JButton moveOrderUp = new JButton("⟰");
    private JButton moveOrderDown = new JButton("⟱");
    private JButton assignment = new JButton("Assign");

    //TextAreas
    private JTextArea statsTextArea = new JTextArea();
    private JTextArea statusTextArea = new JTextArea();
    private JTextArea availibleRes = new JTextArea();

    //Labels
    private JLabel structureListLabel = new JLabel("Structures");
    private JLabel queueLabel = new JLabel("Command Queue");
    private JLabel statsLabel = new JLabel("Stats");

    private JScrollPane queueScrollList = new JScrollPane(structureQueueList);

    //Borders
    private Border coloredLine;

    //ComboBoxes (Drop down menus)
    private JComboBox dropDownBox = new JComboBox();

    //Spinners (Number input)
    private JSpinner allocationInput = new JSpinner(spinnerModel);

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
        structureListLabel.setFont(titleFont);
        strucListPanel.add(structureListLabel, BorderLayout.NORTH);
        JScrollPane strucScrollList = new JScrollPane(structureList);
        strucListPanel.add(strucScrollList, BorderLayout.CENTER);

        JPanel queueListPanel = new JPanel(new BorderLayout());
        JPanel queueButtonsPane = new JPanel(new GridLayout(1,3));
        queueButtonsPane.add(moveOrderUp);
        queueButtonsPane.add(cancelCommand);
        queueButtonsPane.add(moveOrderDown);
        queueListPanel.add(queueLabel, BorderLayout.NORTH);
        queueLabel.setFont(titleFont);
        queueListPanel.add(queueScrollList, BorderLayout.CENTER);
        queueListPanel.add(queueButtonsPane, BorderLayout.SOUTH);

        JPanel statsPanel = new JPanel(new BorderLayout());
        statsPanel.add(statsLabel, BorderLayout.NORTH);
        statsLabel.setFont(titleFont);
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
        statusTextArea.setBorder(BorderFactory.createTitledBorder(coloredLine, "Status"));
        statusPanel.add(statusTextArea, BorderLayout.CENTER);
        assignmentSelectPanel.add(dropDownBox);
        assignmentSelectPanel.add(allocationInput);
        assignmentSelectPanel.add(assignment);
        top.add(statusPanel, BorderLayout.CENTER);
        top.add(assignmentSelectPanel, BorderLayout.SOUTH);

        statusTextArea.setEditable(false);
        availibleRes.setEditable(false);
        statsTextArea.setEditable(false);


        JPanel bottom = new JPanel(new BorderLayout());
        availibleRes.setBorder(BorderFactory.createTitledBorder(coloredLine, "Available Resources"));
        bottom.add(availibleRes, BorderLayout.CENTER);

        assignmentsPane.add(top);
        assignmentsPane.add(bottom);
        structureOverview.add(assignmentsPane);

        this.add(structureOverview);
        allocationInput.setModel(spinnerModel);
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
    public JButton getAssignmentButton() {
        return assignment;
    }

    public void addStructureListActionListener(ListSelectionListener l) {
        structureList.addListSelectionListener(l);
    }

    public void addCommandUpActionListener(ActionListener e) {
        moveOrderUp.addActionListener(e);
    }

    public void addCommandDownActionListener(ActionListener e) {
        moveOrderDown.addActionListener(e);
    }

    public void addCommandCancelActionListener(ActionListener e) {
        cancelCommand.addActionListener(e);
    }

    public void addAssignmentActionListener(ActionListener e) {
        assignment.addActionListener(e);
    }

    public void setStructureModel(ArrayList<Structure> structures) {
        strucListModel.clear();
        for (Structure s : structures) {
            strucListModel.addElement(s.getType() + " " + s.getId());
        }
        structureList.setModel(strucListModel);
        repaint();
    }

    public JList getStructureList() {
        return structureList;
    }

    public void setStrucQueueListModel(Structure s) {
        strucQueueListModel.clear();
        ArrayList<Command> commands = s.getCommandQueue().getCommandsList();
        for (Command c: commands) {
            strucQueueListModel.addElement(c.getCommandString());
        }
        structureQueueList.setModel(strucQueueListModel);
    }

    public void setStatsBox(Structure s) {
        statsTextArea.setText(s.printStats());
    }

    public void setAvailibleResources(int metal, int power, int nutrient) {
        availibleRes.setText("Metal: " + metal +
                            "\nPower: " + power +
                            "\nNutrients: " + nutrient);
    }

    public void setStatusBox(Structure s) {
        String isSupplied;
        if(s.isSufficientlySupplied()) {
            isSupplied = "Sufficient";
        } else {
            isSupplied = "Not sufficient";
        }
        String isFed;
        if (s.getWorkerAssigned().size() != 0) {
            if (s.getWorkerAssigned().get(0).isFed()) {
                isFed = "Sufficient";
            } else {
                isFed = "Not sufficient";
            }
        } else {
            isFed = "Sufficient";
        }

        statusTextArea.setText("Allocated metal and power: " + isSupplied
                                + "\nAllocated nutrient: " + isFed);
    }

    public Structure updatedStructureSelection(ArrayList<Structure> structures) {
        String selectedValue = (String)structureList.getSelectedValue();

        for (Structure s: structures) {
            if (selectedValue.equals(s.getType() + " " + s.getId())) {
                setStatsBox(s);
                setComboBoxModel(s.getType());
                setStrucQueueListModel(s);
                setStatusBox(s);
                return s;
            }
        }
        return null;
    }

    private void setComboBoxModel(String s) {
        dropDownModel.removeAllElements();
        dropDownModel.addElement("Allocate Metal (Percentage 0-100)");
        dropDownModel.addElement("Allocate Power (Percentage 0-100)");
        switch(s) {
            case "University":
            case "Capital":
            case "Fort":
            case "Farm":
            case "Mine":
            case "PowerPlant":
                dropDownModel.addElement("Allocate Nutrient (Percentage 0-100)");
                break;

        }
        dropDownBox.setModel(dropDownModel);
        repaint();
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

    public JComboBox getDropDownBar() {
        return dropDownBox;
    }

    public JSpinner getAllocationInput() {
        return allocationInput;
    }
}
