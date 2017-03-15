package com.team7.controller;

        import com.team7.model.Game;
        import com.team7.model.Player;
        import com.team7.model.entity.Army;
        import com.team7.model.entity.structure.Structure;
        import com.team7.model.entity.unit.Unit;
        import com.team7.view.UnitScreen.UnitScreen;
        import com.team7.view.View;

        import javax.swing.event.ListSelectionEvent;
        import javax.swing.event.ListSelectionListener;
        import java.awt.event.ActionEvent;
        import java.awt.event.ActionListener;


public class UnitOverviewController {

    private Player currentPlayer = null;
    private View view = null;
    private UnitScreen unitScreen = null;
    private Unit currentlySelectedUnit = null;
    private Army currentlySelectedArmy = null;

    public UnitOverviewController(View view, Player p) {
        this.currentPlayer = p;
        this.view = view;
        this.unitScreen = view.getUnitScreen();
        addActionListeners();
        updateUnitModel();
        updateArmyModel();
    }

    private void addActionListeners() {

        unitScreen.getMainScreenButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == unitScreen.getMainScreenButton())
                    view.setCurrScreen("MAIN");
            }
        });
        unitScreen.getStructureScreenButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == unitScreen.getStructureScreenButton())
                    view.setCurrScreen("STRUCTURE_OVERVIEW");
            }
        });
        unitScreen.getOptionScreenButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == unitScreen.getOptionScreenButton())
                    view.getOptionScreen().showScreenSelectBtns();
                view.setCurrScreen("OPTIONS");
            }
        });
        unitScreen.getMapScreenSelectButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == unitScreen.getMapScreenSelectButton())
                    view.getMapScreen().setImage( view.getMainViewImage().getFullMapImage(true) );
                view.setCurrScreen("MAP_SCREEN");
            }
        });

        //Unit List Listener
        unitScreen.addUnitListListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                currentlySelectedUnit = unitScreen.updateSelectedUnit(currentPlayer.getUnits());
            }
        });

        //Army Lists Listener
        unitScreen.addArmyListListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                currentlySelectedArmy = unitScreen.updateSelectedArmy(currentPlayer.getArmies());
            }
        });

        //Queue Manipulation Buttons
        unitScreen.addMoveCommandUpActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selected = (String) unitScreen.getQueue().getSelectedValue();
                if (selected != null && currentlySelectedUnit != null) {
                    if (currentlySelectedUnit.getArmy() == null) {
                        currentlySelectedUnit.moveCommandUp(selected);
                    } else {
                        currentlySelectedUnit.getArmy().moveCommandUp(selected);
                    }
                    unitScreen.updateQueue(currentlySelectedUnit);
                }
            }
        });

        unitScreen.addMoveCommandDownActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selected = (String) unitScreen.getQueue().getSelectedValue();
                if (selected != null && currentlySelectedUnit != null) {
                    if (currentlySelectedUnit.getArmy() == null) {
                        currentlySelectedUnit.moveCommandDown(selected);
                    } else {
                        currentlySelectedUnit.getArmy().moveCommandDown(selected);
                    }
                    unitScreen.updateQueue(currentlySelectedUnit);
                }
            }
        });

        unitScreen.addCancelCommandActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selected = (String) unitScreen.getQueue().getSelectedValue();
                if (selected != null && currentlySelectedUnit != null) {
                    if (currentlySelectedUnit.getArmy() == null) {
                        currentlySelectedUnit.removeCommandByString(selected);
                    } else {
                        currentlySelectedUnit.getArmy().removeCommandByString(selected);
                    }
                    unitScreen.updateQueue(currentlySelectedUnit);
                }
            }
        });

        //Add Army Button
        unitScreen.addAddArmyActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == unitScreen.getCreateArmyButton()) {
                    if (currentlySelectedUnit != null && currentlySelectedUnit.getArmy() == null) {
                        Army fresh = new Army(currentlySelectedUnit.getLocation(), currentPlayer);
                        fresh.addUnitToArmy(currentlySelectedUnit);
                        currentPlayer.addArmy(fresh);
                        unitScreen.updateArmyModel(currentPlayer.getArmies());
                        unitScreen.updateQueue(currentlySelectedUnit);
                    }
                }
            }
        });

        //Decommission Army Button
        unitScreen.addDecomissionArmyActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == unitScreen.getDecomissionArmyButton()) {
                    if (currentlySelectedArmy != null) {
                        currentPlayer.removeArmy(currentlySelectedArmy);
                        currentlySelectedArmy.decommission();
                        unitScreen.updateArmyModel(currentPlayer.getArmies());
                    }
                }
            }
        });

        //Add Unit To Army Button
        unitScreen.addAddUnitActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == unitScreen.getAddUnitButton()) {
                    if (currentlySelectedUnit !=null && currentlySelectedUnit.getArmy() == null && currentlySelectedArmy != null) {
                        currentlySelectedArmy.addUnitToArmy(currentlySelectedUnit);
                        unitScreen.updateArmyContentsList(currentlySelectedArmy);
                        unitScreen.updateQueue(currentlySelectedUnit);
                    }
                }
            }
        });

        //Remove Unit from Army Button
        unitScreen.addRemoveUnitActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == unitScreen.getRemoveUnitButton()) {
                    String selected = (String) unitScreen.getUnitsInArmyList().getSelectedValue();
                    if (currentlySelectedArmy != null && selected != null) {
                        for (Unit u: currentlySelectedArmy.getUnits()) {
                            if (selected.equals(u.getType() + " " + u.getId())) {
                                currentlySelectedArmy.removeUnitFromArmy(u);
                                unitScreen.updateArmyContentsList(currentlySelectedArmy); 
                            }
                        }
                    }
                }
            }
        });

    }
    void updateUnitModel() {
        unitScreen.updateUnitModel(currentPlayer.getUnits(), currentPlayer.getWorkers());
    }

    void updateArmyModel() {
        unitScreen.updateArmyModel(currentPlayer.getArmies());
    }

    public void setCurrentPlayer(Player p) {
        currentlySelectedArmy = null;
        currentlySelectedUnit = null;
        currentPlayer = p;
        updateUnitModel();
        updateArmyModel();
    }
}
