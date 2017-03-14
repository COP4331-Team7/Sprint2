package com.team7.controller;


import com.team7.model.Game;
import com.team7.model.Player;
import com.team7.model.entity.Command;
import com.team7.model.entity.structure.Structure;
import com.team7.model.entity.unit.Unit;
import com.team7.view.MainScreen.MainScreen;
import com.team7.view.MainScreen.MainViewImage;
import com.team7.view.MainScreen.MainViewInfo;
import com.team7.view.OptionsScreen.OptionsScreen;
import com.team7.view.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CommandSelectController {

    private MainViewInfo mainViewInfo = null;
    private MainViewImage mainViewImage = null;
    private OptionsScreen optionsScreen = null;
    private MainScreen mainScreen = null;
    private Game game = null;


    public CommandSelectController(Game game, View view) {
        this.game = game;
        this.mainViewImage = view.getMainViewImage();
        this.mainViewInfo = view.getMainViewInfo();
        this.optionsScreen = view.getOptionScreen();
        this.mainScreen = view.getMainScreen();
        view.getMainScreen().getCommandSelect().setController( this );
        addActionListeners();
    }

    // ===============================================

    public int getNumExplorers() {
        return game.getCurrentPlayer().getNumExplorers();
    }
    public int getNumColonist() {
        return game.getCurrentPlayer().getNumColonist();
    }
    public int getNumMelee() {
        return game.getCurrentPlayer().getNumMelee();
    }
    public int getNumRanged() {
        return game.getCurrentPlayer().getNumRanged();
    }

    public Unit getCurrentUnitSelection(int currMode, int currType, int id) {

        Player currentPlayer = game.getCurrentPlayer();
        Unit currSelection = null;

        if(currMode == 2 && currType == 0)           // EXPLORER
            currSelection = currentPlayer.getExplorer( id );
        else if(currMode == 2 && currType == 1)       // COLONIST
            currSelection = currentPlayer.getColonist( id );
        else if(currMode == 2 && currType == 2)         // RANGED UNIT
            currSelection = currentPlayer.getRanged( id );
        else if(currMode == 2 && currType == 3)        // MELEE UNIT
            currSelection = currentPlayer.getMelee( id );

        return currSelection;
    }

    public Structure getCurrentStructureSelection(int currMode, int currType, int id) {
        Player currentPlayer = game.getCurrentPlayer();
        Structure currSelection = null;

        if(currMode == 1 && currType == 0)           // CAPITAL
            currSelection = currentPlayer.getCapital( id );
        else if(currMode == 1 && currType == 1)       // FORT
            currSelection = currentPlayer.getFort( id );
        else if(currMode == 1 && currType == 2)         // UNIVERSITY
            currSelection = currentPlayer.getUniversity( id );
        else if(currMode == 1 && currType == 3)        // OBSERVATION TOWER
            currSelection = currentPlayer.getObservationTower( id );
        else if(currMode == 1 && currType == 4)        // FARM
            currSelection = currentPlayer.getFarm( id );
        else if(currMode == 1 && currType == 5)        // MINE
            currSelection = currentPlayer.getMine( id );
        else if(currMode == 1 && currType == 6)        // POWERPLANT
            currSelection = currentPlayer.getPowerPlant( id );

        return currSelection;
    }

    public void updateStatusView(int currMode, int currType, int id) {
        if(id == -1){
            return;
        }
        switch(currMode){
            case 1: //structure
                mainViewInfo.updateStructureStats( getCurrentStructureSelection(currMode, currType, id));
                break;
            case 2: //unit
                mainViewInfo.updateUnitStats( getCurrentUnitSelection(currMode, currType, id) );
                break;
        }

    }

    public void zoomToCurrSelection( int currMode, int currType, int currTypeInstance ) {
        Unit unit = getCurrentUnitSelection(  currMode, currType, currTypeInstance );
        if(unit == null)
            return;
        mainViewImage.zoomToDestination(unit.getLocation().getxCoordinate() - 11 / 2, unit.getLocation().getyCoordinate() - 16 / 2, optionsScreen.getFocusSpeed());
    }

    public void addActionListeners() {
        // execute command
        mainScreen.getExecuteCommandButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == mainScreen.getExecuteCommandButton())

                    queueCommand();
                    game.printCommandQueues();

                clearCommandView();
                giveCommandViewFocus();
            }
        });
    }


    public void queueCommand() {
        if(getCurrentUnitSelection(mainScreen.getCommandSelect().getCurrMode(), mainScreen.getCommandSelect().getCurrType(), mainScreen.getCommandSelect().getCurrTypeInstance()) == null)
            return;
        Command command = new Command( mainScreen.getCommandSelect().getCommand() );
        Unit unit = getCurrentUnitSelection(mainScreen.getCommandSelect().getCurrMode(), mainScreen.getCommandSelect().getCurrType(), mainScreen.getCommandSelect().getCurrTypeInstance());
        unit.queueCommand( command );

      //  printCurrPlayersUnitsCommandQueues();
    }

    public void printCurrPlayersUnitsCommandQueues(){
        System.out.println("\nPlayer " + game.getCurrentPlayer().getName() + "'s unit's command queues: ");
        game.getCurrentPlayer().printUnitCommandQueues();
    }

    public void giveCommandViewFocus() {
        mainScreen.getCommandSelect().setFocusable(true);
        mainScreen.getCommandSelect().requestFocus();
    }

    public void clearCommandView() {
        mainScreen.getCommandSelect().clearCommand();
    }

    public void clearStatusInfoView() {
        mainViewInfo.clearStats();
    }

    public void executeReinforeCommand(Character c, Unit u) {

        int armyID = Integer.valueOf( c - 48 );

        // System.out.println(armyID);

        queueCommand();
        clearCommandView();
        giveCommandViewFocus();
    }

    public int getNumCapital() {
        return game.getCurrentPlayer().getNumCapital();
    }
    public int getNumFort() {
        return game.getCurrentPlayer().getNumFort();
    }
    public int getNumFarm() {
        return game.getCurrentPlayer().getNumFarm();
    }
    public int getNumMine() {
        return game.getCurrentPlayer().getNumMine();
    }
    public int getNumObsTower() {
        return game.getCurrentPlayer().getNumObservationTower();
    }
    public int getNumPowerPlant() {
        return game.getCurrentPlayer().getNumPowerPlant();
    }
    public int getNumUniversity() {
        return game.getCurrentPlayer().getNumUniversity();
    }



}
