package com.team7.controller;

import com.team7.model.Game;
import com.team7.model.Player;
import com.team7.model.entity.structure.Structure;
import com.team7.view.OptionsScreen.OptionsScreen;
import com.team7.view.StructureScreen.StructureScreen;
import com.team7.view.View;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class StructureOverviewController {

    private Player currentPlayer = null;
    private View view = null;
    private StructureScreen structureScreen = null;
    private OptionsScreen optionsScreen = null;
    private Structure currentlySelectedStructure = null;

    public StructureOverviewController(View view, Player p) {
        this.view = view;
        this.currentPlayer = p;
        this.structureScreen = view.getStructureScreen();
        this.optionsScreen = view.getOptionScreen();
        structureScreen.setStructureModel(p.getStructures());
        addActionListeners();
    }

    private void addActionListeners() {

        //Screen Select Buttons
        structureScreen.getMainScreenButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == structureScreen.getMainScreenButton())
                    view.setCurrScreen("MAIN");
            }
        });
        structureScreen.getUnitScreenButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == structureScreen.getUnitScreenButton())
                    view.setCurrScreen("UNIT_OVERVIEW");
            }
        });
        structureScreen.getOptionScreenButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == structureScreen.getOptionScreenButton())
                    optionsScreen.showScreenSelectBtns();
                view.setCurrScreen("OPTIONS");
            }
        });
        structureScreen.getMapScreenSelectButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == structureScreen.getMapScreenSelectButton())
                    view.getMapScreen().setImage(view.getMainViewImage().getFullMapImage(true));
                view.setCurrScreen("MAP_SCREEN");
            }
        });

        //Action Listener for Structure List
        //TODO -- Structures don't have command queues
        structureScreen.addStructureListActionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e.getSource() == structureScreen.getStructureList()) {
                    currentlySelectedStructure = structureScreen.updatedStructureSelection(currentPlayer.getStructures());
                }
            }
        });

        //ActionListeners for Queue Manipulation Buttons
        structureScreen.addCommandUpActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == structureScreen.getMoveOrderUp()) {
                    String selected_value = (String) structureScreen.getStrucQueueList().getSelectedValue();
                    if (selected_value != null && currentlySelectedStructure != null) {
                        currentlySelectedStructure.moveCommandUp(selected_value);
                        structureScreen.setStrucQueueListModel(currentlySelectedStructure);
                    }
                }
            }
        });

        structureScreen.addCommandDownActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == structureScreen.getMoveOrderDown()) {
                    String selectedValue = (String) structureScreen.getStrucQueueList().getSelectedValue();
                    if (selectedValue != null && currentlySelectedStructure != null) {
                        currentlySelectedStructure.moveCommandDown(selectedValue);
                        structureScreen.setStrucQueueListModel(currentlySelectedStructure);
                    }
                }
            }
        });

        structureScreen.addCommandCancelActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == structureScreen.getCancelCommand()) {
                    String selectedValue = (String) structureScreen.getStrucQueueList().getSelectedValue();
                    if (selectedValue != null && currentlySelectedStructure != null) {
                        currentlySelectedStructure.removeCommandByString(selectedValue);
                        structureScreen.setStrucQueueListModel(currentlySelectedStructure);
                    }
                }
            }
        });

        structureScreen.addAssignmentActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == structureScreen.getAssignmentButton()) {
                    String assignmentType = (String) structureScreen.getDropDownBar().getSelectedItem();
                    int allocationPercent = (int) structureScreen.getAllocationInput().getValue();
                    if (currentlySelectedStructure != null) {
                        int allocationValue = 0;
                        switch (assignmentType) {
                            case "Allocate Metal (Percentage 0-100)":
                                allocationValue = (int)allocationPercent/100*currentPlayer.getMetal();
                                if (allocationValue <= currentPlayer.getMetal()) {
                                    currentlySelectedStructure.setAllocatedResources("Ore", allocationValue);
                                    currentPlayer.spendMetal(allocationValue);
                                }
                                break;
                            case "Allocate Power (Percentage 0-100)":
                                allocationValue = (int)allocationPercent/100*currentPlayer.getPower();
                                if (allocationValue <= currentPlayer.getPower()) {
                                    currentlySelectedStructure.setAllocatedResources("Energy", allocationValue);
                                    currentPlayer.spendPower(allocationValue);
                                }
                                break;
                            case "Allocate Nutrient (Percentage 0-100)":
                                allocationValue = (int)allocationPercent/100*currentPlayer.getNutrients();
                                if (allocationValue <= currentPlayer.getNutrients()) {
                                    currentlySelectedStructure.setAllocatedResources("Nutrients", allocationValue);
                                    currentPlayer.spendFood(allocationValue);
                                }
                        }
                        structureScreen.setAvailibleResources(currentPlayer.getMetal(), currentPlayer.getPower(), currentPlayer.getNutrients());
                        System.out.println("Allocated " + allocationValue);
                    }
                }
            }
        });
    }




    public void setCurrentPlayer(Player p) {
        currentPlayer = p;
        structureScreen.setAvailibleResources(p.getMetal(), p.getPower(), p.getNutrients());
    }




}
