package com.team7.view.UnitScreen;

import com.team7.model.entity.*;
import com.team7.model.entity.unit.Unit;
import com.team7.view.ScreenSelectButtons;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class UnitScreen extends JPanel {
    //Lists
    private JList unitList = new JList<>();
    private JList queue = new JList<>();
    private JList armyList = new JList<String>();
    private JList unitsInArmyList = new JList<>();

    //JScrollPanes
    private JScrollPane unitListScrollPane;
    private JScrollPane queueScrollPane;
    private JScrollPane armiesScrollPane;
    private JScrollPane armyContentsScrollPane;

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
    private JTextArea statsArea = new JTextArea();

    //JLabels
    private JLabel unitListLabel = new JLabel("Unit List", SwingConstants.CENTER);
    private JLabel titleLabel = new JLabel("Unit Overview", SwingConstants.CENTER);
    private JLabel queueLabel = new JLabel("Queue", SwingConstants.CENTER);
    private JLabel armiesLabel = new JLabel("Armies", SwingConstants.CENTER);
    private JLabel armyContentsLabel = new JLabel("Units in Army", SwingConstants.CENTER);
    private JLabel statsLabel = new JLabel("Stats", SwingConstants.CENTER);

    //Fonts
    private Font titleFont = new Font("Serif", Font.BOLD, 30);

    //Colors
    private Color lightColor = new Color(0xffF5F5DC);
    private Color darkColor = new Color(0xffCABD80);

    //Borders
    private Border coloredLine;

    public UnitScreen() {

        screenSelectBtns = new ScreenSelectButtons();
        this.add(screenSelectBtns, BorderLayout.PAGE_START);
        this.setBorder(BorderFactory.createLineBorder(new Color(0xffCABD80), 3));

        coloredLine = BorderFactory.createLineBorder(new Color(0xffCABD80), 3);
        JPanel gridPanel = new JPanel(new GridLayout(2,3));
        gridPanel.setBackground(lightColor);
        gridPanel.setOpaque(true);

        //Unit List
        JPanel unitListPanel = new JPanel(new BorderLayout());
        unitListPanel.add(unitListLabel, BorderLayout.NORTH);
        unitListScrollPane = new JScrollPane(unitList);
        unitList.setModel(unitModel);
        unitListPanel.add(unitListScrollPane, BorderLayout.CENTER);
        unitListLabel.setFont(titleFont);
        unitListLabel.setOpaque(true);
        unitListLabel.setBackground(lightColor);
        unitListPanel.setBorder(coloredLine);

        //Title Panel
        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.add(titleLabel, BorderLayout.CENTER);
        titleLabel.setFont(titleFont);
        titleLabel.setBorder(coloredLine);
        titleLabel.setOpaque(true);
        titleLabel.setBackground(lightColor);

        //Queue Panel
        JPanel queuePanel = new JPanel(new BorderLayout());
        JPanel queueButtonsPanel = new JPanel(new GridLayout(1,3));
        //queueButtonsPanel.add(moveOrderUp);
        queueButtonsPanel.add(cancelCommand);
        //queueButtonsPanel.add(moveOrderDown);
        moveOrderUp.setOpaque(true);
        cancelCommand.setOpaque(true);
        moveOrderDown.setOpaque(true);
        moveOrderUp.setBackground(lightColor);
        moveOrderDown.setBackground(lightColor);
        cancelCommand.setBackground(lightColor);
        queueScrollPane = new JScrollPane(queue);
        queue.setModel(queueModel);
        queuePanel.add(queueLabel, BorderLayout.NORTH);
        queuePanel.add(queueButtonsPanel, BorderLayout.SOUTH);
        queueLabel.setFont(titleFont);
        queuePanel.add(queueScrollPane);
        queuePanel.setBorder(coloredLine);
        queuePanel.setOpaque(false);
        queueLabel.setOpaque(false);

        //Armies Panel
        JPanel armiesPanel = new JPanel(new BorderLayout());
        JPanel armiesButtonPanel = new JPanel(new GridLayout(1,2));
        armiesButtonPanel.add(addArmy);
        armiesButtonPanel.add(decomissionArmy);
        addArmy.setOpaque(true);
        addArmy.setBackground(lightColor);
        decomissionArmy.setOpaque(true);
        decomissionArmy.setBackground(lightColor);
        armiesLabel.setFont(titleFont);
        armiesLabel.setOpaque(false);
        armiesPanel.setBorder(coloredLine);
        armiesPanel.setOpaque(false);
        armiesScrollPane = new JScrollPane(armyList);
        armiesScrollPane.setOpaque(false);
        armyList.setModel(armyModel);
        armiesPanel.add(armiesLabel, BorderLayout.NORTH);
        armiesPanel.add(armiesScrollPane, BorderLayout.CENTER);
        armiesPanel.add(armiesButtonPanel, BorderLayout.SOUTH);

        //Army Contents Panel
        JPanel armyContentsPanel = new JPanel(new BorderLayout());
        JPanel armyContentsButtonsPanel = new JPanel(new GridLayout(1, 2));
        armyContentsButtonsPanel.add(addUnit);
        armyContentsButtonsPanel.add(removeUnit);
        addUnit.setOpaque(true);
        addUnit.setBackground(lightColor);
        removeUnit.setOpaque(true);
        removeUnit.setBackground(lightColor);
        armyContentsScrollPane = new JScrollPane(unitsInArmyList);
        unitsInArmyList.setModel(armyContentsModel);
        armyContentsPanel.add(armyContentsLabel, BorderLayout.NORTH);
        armyContentsLabel.setFont(titleFont);
        armyContentsPanel.add(armyContentsScrollPane, BorderLayout.CENTER);
        armyContentsPanel.add(armyContentsButtonsPanel, BorderLayout.SOUTH);
        armyContentsPanel.setOpaque(false);
        armyContentsPanel.setBorder(coloredLine);

        //Stats Panel
        JPanel statsPanel = new JPanel(new BorderLayout());
        statsLabel.setFont(titleFont);
        statsArea.setEditable(false);
        statsArea.setBorder(coloredLine);
        statsPanel.setBorder(coloredLine);
        statsPanel.setOpaque(false);
        statsPanel.add(statsLabel, BorderLayout.NORTH);
        statsPanel.add(statsArea, BorderLayout.CENTER);


        //Mix it in the pot
        gridPanel.add(unitListPanel);
        gridPanel.add(titlePanel);
        gridPanel.add(statsPanel);
        gridPanel.add(armiesPanel);
        gridPanel.add(queuePanel);
        gridPanel.add(armyContentsPanel);
        gridPanel.setPreferredSize(new Dimension((int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth()*.8), (int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight()*.8)));

        this.add(gridPanel, BorderLayout.PAGE_END);
        repaint();
    }

    public void updateUnitModel(ArrayList<Unit> units, ArrayList<Worker> workers) {
        unitModel.clear();
        for (Unit u: units) {
            unitModel.addElement(u.getType() + " " + u.getId());
        }

        for (Worker w: workers) {
            unitModel.addElement("Worker " + w.getId());
        }
        repaint();
    }

    public void updateArmyModel(ArrayList<Army> armies) {
        armyModel.clear();

        for (Army a: armies) {
            armyModel.addElement("Army " + a.getId());
        }
    }

    public void updateStats(Unit u) {
        statsArea.setText(u.printStats());
    }

    public void updateQueue(Unit u) {
        queueModel.clear();
        ArrayList<Command> commands;
        if (u.getArmy() == null) {
            commands = u.getCommandQueue().getCommandsList();
        } else {
            commands = u.getArmy().getCommandQueue().getCommandsList();
        }

        for (Command c: commands) {
            if (c.getCommandString().equals("MOVE")) {
                MovementCommand m = (MovementCommand)(c);
                queueModel.addElement(m.getCommandString() + " " + m.getDestinationTile().print());
            } else {
                queueModel.addElement(c.getCommandString());
            }
        }
    }

    public void updateArmyContentsList(Army a) {
        armyContentsModel.clear();
        ArrayList<Unit> units = a.getUnits();

        for (Unit u: units) {
            armyContentsModel.addElement(u.getType() + " " + u.getId());
        }

    }

    public void addUnitListListener(ListSelectionListener e) {
        unitList.addListSelectionListener(e);
    }

    public void addArmyListListener(ListSelectionListener e) {
        armyList.addListSelectionListener(e);
    }

    public void addMoveCommandUpActionListener(ActionListener e) {
        moveOrderUp.addActionListener(e);
    }

    public void addMoveCommandDownActionListener(ActionListener e) {
        moveOrderDown.addActionListener(e);
    }

    public void addCancelCommandActionListener(ActionListener e) {
        cancelCommand.addActionListener(e);
    }

    public void addAddArmyActionListener(ActionListener e) {
        addArmy.addActionListener(e);
    }

    public void addDecomissionArmyActionListener(ActionListener e) {
        decomissionArmy.addActionListener(e);
    }

    public void addAddUnitActionListener(ActionListener e) {
        addUnit.addActionListener(e);
    }

    public void addRemoveUnitActionListener(ActionListener e) {
        removeUnit.addActionListener(e);
    }

    public Unit updateSelectedUnit(ArrayList<Unit> units) {
        String selected = (String) unitList.getSelectedValue();
        if (selected != null) {
            for (Unit u: units) {
                if (selected.equals(u.getType() + " " + u.getId())) {
                    updateStats(u);
                    updateQueue(u);
                    repaint();
                    return u;
                }
            }
        }

        return null;
    }

    public Army updateSelectedArmy(ArrayList<Army> armies) {
        String selected = (String) armyList.getSelectedValue();
        if (selected != null) {
            for (Army a: armies) {
                if (selected.equals("Army " + a.getId())) {
                    updateArmyContentsList(a);
                    repaint();
                    return a;
                }
            }
        }

        return null;
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
    public JButton getCreateArmyButton() {return addArmy;}
    public JButton getDecomissionArmyButton() {return decomissionArmy;}
    public JButton getAddUnitButton() {return addUnit;}
    public JButton getRemoveUnitButton() {return removeUnit;}
    public JList getQueue() {return queue;}
    public JList getUnitsInArmyList() {return unitsInArmyList;}
}
