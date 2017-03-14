package com.team7.controller;

import com.team7.model.Game;
import com.team7.model.Technology;
import com.team7.view.MainScreen.MainScreen;
import com.team7.view.MainScreen.MainViewImage;
import com.team7.view.MainScreen.MainViewInfo;
import com.team7.view.MainScreen.MainViewMiniMap;
import com.team7.view.MapScreen.MapScreen;
import com.team7.view.OptionsScreen.OptionsScreen;
import com.team7.view.TechnologyScreen.TechnologyScreen;
import com.team7.view.View;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * controls technology interaction with model/view
 */
public class TechnologyScreenController {
    private Game game = null;
    private View view = null;
    private MainScreen mainScreen = null;
    private MainViewMiniMap miniMap = null;
    private MainViewInfo mainViewInfo = null;
    private MainViewImage mainViewImage = null;
    private OptionsScreen optionScreen = null;
    private MapScreen mapScreen = null;
    private TechnologyScreen technologyScreen = null;

    private String currentMode = null;

    private final String[] textListOfUnits = {"Explorer", "Colonist", "Melee", "Ranged"};
    private final String[] textListOfStructures = {"Capital", "Fort", "University", "ObservationTower", "Farm", "PowerPlant", "Mine", "Harvest", "Produce", "Train"};
    private final String[] textListOfWorkers = {"Worker", "Tile"};



    public TechnologyScreenController(Game game, View view) {
        this.game = game;
        this.view = view;
        this.mainViewImage = view.getMainViewImage();
        this.mainScreen = view.getMainScreen();
        this.miniMap = view.getMainScreen().getMiniMap();
        this.mainViewInfo = view.getMainViewInfo();
        this.optionScreen = view.getOptionScreen();
        this.mapScreen = view.getMapScreen();
        this.technologyScreen = view.getTechnologyScreen();

        addActionListeners();

    }





    // add action listeners to Main Screen buttons
    public void addActionListeners() {

        //set instance list model according to which type of technology selected
        technologyScreen.getUnitButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == technologyScreen.getUnitButton()){
                    technologyScreen.setInstanceListModel(textListOfUnits);
                    currentMode = "Unit";
                    technologyScreen.clearTechnologyStatListModel();
                }
            }
        });
        technologyScreen.getStructureButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == technologyScreen.getStructureButton()){
                    technologyScreen.setInstanceListModel(textListOfStructures);
                    currentMode = "Structure";
                    technologyScreen.clearTechnologyStatListModel();
                }
            }
        });
        technologyScreen.getWorkerButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == technologyScreen.getWorkerButton()){
                    technologyScreen.setInstanceListModel(textListOfWorkers);
                    currentMode = "Worker";
                    technologyScreen.clearTechnologyStatListModel();
                }
            }
        });


        technologyScreen.getTechnologyInstanceList().addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                JList<String> list = (JList<String>)evt.getSource();
                if (evt.getClickCount() == 2) {
                    ArrayList<Technology> currentTechnology = new ArrayList<>();
                    switch (currentMode){
                        case "Unit":
                            currentTechnology = game.getCurrentPlayer().getTechnologies().getUnitTechnologies();
                            break;
                        case "Worker":
                            currentTechnology = game.getCurrentPlayer().getTechnologies().getWorkerTechnologies();
                            break;
                        case "Structure":
                            currentTechnology = game.getCurrentPlayer().getTechnologies().getStructureTechnologies();
                            break;

                    }
                    technologyScreen.setTechnologiesListModel(currentTechnology, list.getSelectedValue());
                }
            }
        });



        //screen select buttons
        technologyScreen.getMainScreenButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == technologyScreen.getMainScreenButton())
                    view.setCurrScreen("MAIN");
            }
        });
        technologyScreen.getUnitScreenButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == technologyScreen.getUnitScreenButton())
                    view.setCurrScreen("UNIT_OVERVIEW");
            }
        });
        technologyScreen.getOptionScreenButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == technologyScreen.getOptionScreenButton()){
                    optionScreen.showScreenSelectBtns();
                    view.setCurrScreen("OPTIONS");
                }

            }
        });
        technologyScreen.getStructureScreenButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == technologyScreen.getStructureScreenButton())
                    view.setCurrScreen("STRUCTURE_OVERVIEW");
            }
        });
        technologyScreen.getMapScreenSelectButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == technologyScreen.getMapScreenSelectButton())
                    view.getMapScreen().setImage( view.getMainViewImage().getFullMapImage(true) );
                view.setCurrScreen("MAP_SCREEN");
            }
        });

        technologyScreen.getTechnologyScreenButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == technologyScreen.getTechnologyScreenButton()){
                    view.setCurrScreen("TECHNOLOGY");
                }
            }
        });

    }
}
