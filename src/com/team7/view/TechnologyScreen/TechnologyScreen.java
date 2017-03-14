package com.team7.view.TechnologyScreen;

import com.team7.model.Technology;
import com.team7.view.ScreenSelectButtons;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 * view of technology 'tree'
 */
public class TechnologyScreen extends JPanel {

    private ScreenSelectButtons screenSelectBtns = null;
    private JButton unitButton = new JButton("Unit");
    private JButton workerButton = new JButton("Worker");
    private JButton structureButton = new JButton("Structure");

    private JList<String> technologyInstanceList = new JList<>();
    private DefaultListModel<String> defaultInstanceListModel = new DefaultListModel<>();
    private JScrollPane instanceScrollPane = new JScrollPane(technologyInstanceList);



    private JList<String> technologiesList = new JList<>();
    private DefaultListModel<String> defaultTechnologiesListModel = new DefaultListModel<>();
    private JScrollPane technologyStatScrollPane = new JScrollPane(technologiesList);

    private JPanel buttonPanel = new JPanel(new BorderLayout());

    private JPanel buttonTechTypePanel = new JPanel( new GridLayout(0, 3) );

    private JPanel instanceAndStatPanesPanel = new JPanel(new GridLayout(0,2));


    public TechnologyScreen() {
        screenSelectBtns = new ScreenSelectButtons();
        this.add(screenSelectBtns, BorderLayout.NORTH);
        this.setBorder(BorderFactory.createLineBorder(new Color(0xffCABD80), 3));

        //set models of two jlists
        technologyInstanceList.setModel(defaultInstanceListModel);
        technologiesList.setModel(defaultTechnologiesListModel);


        //add lists to the grid (0x2) panel
        instanceAndStatPanesPanel.add(instanceScrollPane);
        instanceAndStatPanesPanel.add(technologyStatScrollPane);



        buttonTechTypePanel.add(unitButton);
        buttonTechTypePanel.add(workerButton);
        buttonTechTypePanel.add(structureButton);

        buttonPanel.add(unitButton, BorderLayout.SOUTH);
        buttonPanel.add(workerButton, BorderLayout.WEST);
        buttonPanel.add(structureButton, BorderLayout.EAST);

        this.add(buttonPanel, BorderLayout.CENTER);
        this.add(buttonTechTypePanel, BorderLayout.SOUTH);
        this.add(instanceAndStatPanesPanel, BorderLayout.EAST);

        this.repaint();

    }


    //adds text to the defaultInstanceListModel
    public void setInstanceListModel(String[] instances){
        defaultInstanceListModel.clear();

        for (String instance : instances){
            defaultInstanceListModel.addElement(instance);
        }
        this.repaint();
    }


    //adds text to the defaultTechnologiesListModel
    public void setTechnologiesListModel(ArrayList<Technology> technologies, String selectedInstance){
        defaultTechnologiesListModel.clear();
        for (Technology technology : technologies){
            if (selectedInstance.equals(technology.getTechnologyInstance())){
                defaultTechnologiesListModel.addElement(technology.getTechnologyStat() + ": Level " + Integer.valueOf(technology.getLevel()) + "/" + Integer.valueOf(technology.getMaxLevel()));
            }
        }
        this.repaint();
    }


    public JButton getUnitButton() {
        return unitButton;
    }

    public JButton getWorkerButton() {
        return workerButton;
    }

    public JButton getStructureButton() {
        return structureButton;
    }



    public DefaultListModel<String> getDefaultInstanceListModel() {
        return defaultInstanceListModel;
    }

    public JList<String> getTechnologyInstanceList() {
        return technologyInstanceList;
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
    public JButton getStructureScreenButton() {
        return screenSelectBtns.getStructureScreenButton();
    }
    public JButton getTechnologyScreenButton() {
        return screenSelectBtns.getTechnologyScreenSelectButton();
    }


    public void clearTechnologyStatListModel() {
        defaultTechnologiesListModel.clear();
    }
}

