package com.team7.view.TechnologyScreen;

import com.team7.model.Technology;
import com.team7.model.entity.structure.staffedStructure.University;
import com.team7.view.ScreenSelectButtons;

import javax.swing.*;
import javax.swing.border.Border;
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

    private JList<String> universitiesList = new JList<>();
    private DefaultListModel<String> defaultUniversitiesListModel = new DefaultListModel<>();
    private JScrollPane universitiesScrollPane = new JScrollPane(universitiesList);

    private JPanel buttonPanel = new JPanel(new GridLayout(0, 3));

    private JPanel instanceAndStatPanesPanel = new JPanel(new GridLayout(0,2));

    private JPanel universityPanel = new JPanel(new BorderLayout());

    private JLabel universityLabel = new JLabel("All Universities");


    public TechnologyScreen() {
        screenSelectBtns = new ScreenSelectButtons();
        this.add(screenSelectBtns, BorderLayout.NORTH);
        this.setBorder(BorderFactory.createLineBorder(new Color(0xffCABD80), 3));

        //set models of jlists
        technologyInstanceList.setModel(defaultInstanceListModel);
        technologiesList.setModel(defaultTechnologiesListModel);
        universitiesList.setModel(defaultUniversitiesListModel);


        //add lists to the grid (0x2) panel
        instanceAndStatPanesPanel.add(instanceScrollPane);
        instanceAndStatPanesPanel.add(technologyStatScrollPane);

        universityPanel.add(universitiesScrollPane);

        workerButton.setFont(new Font("Serif", Font.BOLD, 22));
        workerButton.setForeground( new Color(0xff000000) );
        workerButton.setBackground(new Color(0xffF5F5DC));
        workerButton.setOpaque(true);
        workerButton.setPreferredSize( new Dimension(200, 50  ) );

        unitButton.setFont(new Font("Serif", Font.BOLD, 22));
        unitButton.setForeground( new Color(0xff000000) );
        unitButton.setBackground(new Color(0xffF5F5DC));
        unitButton.setOpaque(true);
        unitButton.setPreferredSize( new Dimension(200, 50  ) );

        structureButton.setFont(new Font("Serif", Font.BOLD, 22));
        structureButton.setForeground( new Color(0xff000000) );
        structureButton.setBackground(new Color(0xffF5F5DC));
        structureButton.setOpaque(true);
        structureButton.setPreferredSize( new Dimension(200, 50  ) );

        technologyInstanceList.setFont(new Font("Serif", Font.BOLD, 25));

        technologiesList.setFont(new Font("Serif", Font.BOLD, 22));

        universitiesList.setFont(new Font("Serif", Font.BOLD, 30));

        instanceScrollPane.setBackground(new Color(0xffF5F5DC));
        instanceScrollPane.setOpaque(true);

        buttonPanel.add(unitButton, BorderLayout.SOUTH);
        buttonPanel.add(workerButton, BorderLayout.WEST);
        buttonPanel.add(structureButton, BorderLayout.EAST);

        JPanel temp = new JPanel( new BorderLayout() );
        temp.add(buttonPanel, BorderLayout.NORTH );
        temp.add(instanceAndStatPanesPanel, BorderLayout.CENTER);

        JPanel t = new JPanel( new BorderLayout() );

        universityLabel.setFont(new Font("Serif", Font.BOLD, 35));
        universityLabel.setBackground(new Color(0xffF5F5DC));
        universityLabel.setOpaque(true);

        t.add(universityLabel, BorderLayout.NORTH);
        t.add(universityPanel, BorderLayout.CENTER);

        temp.add(t, BorderLayout.SOUTH);
        this.add(temp, BorderLayout.SOUTH);

        temp.setPreferredSize( new Dimension( (int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.8), (int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.75) ) );

        this.setBackground(new Color(0xffF5F5DC));
        this.setOpaque(true);

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

    private void setUniversitiesListModel(ArrayList<University> universities){
        defaultUniversitiesListModel.clear();

        for (University university : universities){
            defaultUniversitiesListModel.addElement("University id: " + university.getId() + " : in research? " + university.isInResearch());
            if (university.isInResearch()){
                defaultUniversitiesListModel.addElement("     " + university.getTechnologyInResearch().getTechnologyInstance() + " " + university.getTechnologyInResearch().getTechnologyStat());
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

    public DefaultListModel<String> getDefaultTechnologiesListModel() {
        return defaultTechnologiesListModel;
    }

    public JList<String> getTechnologyInstanceList() {
        return technologyInstanceList;
    }

    public JList<String> getTechnologiesList() {
        return technologiesList;
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

    public void populatePlayerUniversities(ArrayList<University> universities) {
        setUniversitiesListModel(universities);
    }
}

