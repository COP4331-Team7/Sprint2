package com.team7.view.MainScreen;

import com.team7.controller.CommandSelectController;
import com.team7.controller.PathSelectController;
import com.team7.model.entity.Command;
import com.team7.model.entity.MovementCommand;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class CommandSelect extends JPanel implements KeyListener, MapStats {

    private JButton executeCommandButton = null;
    private JButton endTurnButton = null;
    private CommandSelectController controller = null;

    private JLabel modeLabel;
    private JLabel typeLabel;
    private JLabel typeInstanceLabel;
    private JLabel commandLabel;

    private final static String[] armyCommands = {
            "ATTACK",
            "DEFEND",
            "MOVE",
            "WAIT",
            "DISBAND",
            "DECOMISSION",
            "POWER DOWN",
            "POWER UP",
            "CANCEL QUEUED ORDERS",
            "BUILD"
    };
    private final static String[] unitCommands = {
            "REINFORCE",
            "DECOMMISSION",
            "POWER DOWN",
            "POWER UP",
            "MOVE",
    };
    private final static String[] structureTypes = {
            "CAPITAL",
            "FORT",
            "UNIVERSITY",
            "OBSERVATION_TOWER",
            "FARM",
            "MINE",
            "POWERPLANT"
    };
    private final static String[] unitTypes = {
            "EXPLORER",
            "COLONIST",
            "RANGED_UNIT",
            "MELEE_UNIT"
    };
    private final static String[] modes = {
            "RALLY POINT",
            "STRUCTURE",
            "UNIT",
            "ARMY"
    };
    private final static String[] armySubTypes = {
            "ENTIRE ARMY",
            "BATTLE GROUP",
            "REINFORCEMENTS"
    };

    // unit specific command lists
    private final static String[] meleeCommands = {
            "REINFORCE",
            "DECOMMISSION",
            "POWER DOWN",
            "POWER UP"
    };
    private final static String[] rangedCommands = {
            "REINFORCE",
            "DECOMMISSION",
            "POWER DOWN",
            "POWER UP"
    };
    private final static String[] colonistCommands = {
            "DECOMMISSION",
            "POWER UP",
            "POWER DOWN",
            "MOVE",
            "MAKE BASE"
    };
    private final static String[] explorerCommands = {
            "PROSPECT MODE ON",
            "PROSPECT MODE OFF",
            "DECOMMISSION",
            "POWER UP",
            "POWER DOWN",
            "MOVE"
    };

    private final static String[] powerPlantCommands = {
            "defend",
            "decommission",
            "power down",
            "power up",
            "cancel queued orders",
            "assign workers to harvest energy",
            "unassign all workers"
    };
    private final static String[] mineCommands = {
            "defend",
            "decommission",
            "power down",
            "power up",
            "cancel queued orders",
            "assign workers to harvest ore",
            "unassign all workers"
    };
    private final static String[] farmCommands = {
            "defend",
            "decommission",
            "power down",
            "power up",
            "cancel queued orders",
            "assign workers to harvest food",
            "unassign all workers"
    };
    private final static String[] observationCommands = {
            "defend",
            "decommission",
            "power down",
            "power up",
            "cancel queued orders"
    };
    private final static String[] universityCommands = {
            "defend",
            "decommission",
            "power down",
            "power up",
            "cancel queued orders"

    };
    private final static String[] capitalCommands = {
            "make explorer",
            "make worker",
            "defend",
            "heal/repair unit",
            "decommission",
            "power down",
            "power up",
            "cancel queued orders",
            "assign workers to harvest food",
            "assign workers to harvest ore",
            "assign workers to harvest energy",
            "unassign all workers"

    };
    private final static String[] fortCommands = {
            "attack",
            "train melee with worker",
            "train ranged with soldier",
            "defend",
            "decommission",
            "power down",
            "power up",
            "cancel queued orders"

    };

    private int currMode = -1;
    private int currType = -1;
    private int currTypeInstance = -1;
    private int currCommand = -1;

    private static final int CONTROL_KEY_CODE = 1;
    private static final int LEFT_KEY_CODE = 37;
    private static final int UP_KEY_CODE = 38;
    private static final int RIGHT_KEY_CODE = 39;
    private static final int DOWN_KEY_CODE = 40;

    private char key_char = '1';
    private boolean isRecordingPath = false;
    private PathSelectController pathSelectController = null;
    /////////////////////////////////////////////////////////////////////////////////////////




    /* CONSTRUCTOR AT BOTTOM OF FILE */

    // update the text displaying the currently selected command
    private void updateCommand() {

        modeLabel.setText("MODE (SHIFT + \u2191 / \u2193): " + ((currMode != -1)?modes[currMode]:"") ); //up / down arrow

        // type based off of mode
        if(currMode == 1)
            typeLabel.setText("TYPE (SHIFT + \u2190 / \u2192): " + ((currType != -1)?structureTypes[currType]:"")); //left / right arrow
        else if (currMode == 2)
            typeLabel.setText("TYPE (SHIFT + \u2190 / \u2192): " + ((currType != -1)?unitTypes[currType]:"")); //left / right arrow
        else if (currMode == 3)
            typeLabel.setText("TYPE (SHIFT + \u2190 / \u2192): " + ((currType != -1)?armySubTypes[currType]:"")); //left / right arrow
        else
            typeLabel.setText("TYPE (SHIFT + \u2190 / \u2192): " ); //left / right arrow

        if(currMode == 1 && currType == 0)           // CAPITAL
            commandLabel.setText("COMMAND (\u2191 / \u2193): " + ((currCommand != -1)?capitalCommands[currCommand]:"")); //up / down arrow
        else if(currMode == 1 && currType == 1)       // FORT
            commandLabel.setText("COMMAND (\u2191 / \u2193): " + ((currCommand != -1)?fortCommands[currCommand]:"")); //up / down arrow
        else if(currMode == 1 && currType == 2)         // UNIVERSITY
            commandLabel.setText("COMMAND (\u2191 / \u2193): " + ((currCommand != -1)?universityCommands[currCommand]:"")); //up / down arrow
        else if(currMode == 1 && currType == 3)        // OBSERVATION TOWER
            commandLabel.setText("COMMAND (\u2191 / \u2193): " + ((currCommand != -1)?observationCommands[currCommand]:"")); //up / down arrow
        else if(currMode == 1 && currType == 4)        // FARM
            commandLabel.setText("COMMAND (\u2191 / \u2193): " + ((currCommand != -1)?farmCommands[currCommand]:"")); //up / down arrow
        else if(currMode == 1 && currType == 5)        // MINE
            commandLabel.setText("COMMAND (\u2191 / \u2193): " + ((currCommand != -1)?mineCommands[currCommand]:"")); //up / down arrow
        else if(currMode == 1 && currType == 6)        // POWER PLANT
            commandLabel.setText("COMMAND (\u2191 / \u2193): " + ((currCommand != -1)?powerPlantCommands[currCommand]:"")); //up / down arrow

        else if (currMode == 2 && currType == 0)    //explorer
            commandLabel.setText("COMMAND (\u2191 / \u2193): " + ((currCommand != -1)?explorerCommands[currCommand]:"")); //up / down arrow
        else if (currMode == 2 && currType == 1)    //colonist
            commandLabel.setText("COMMAND (\u2191 / \u2193): " + ((currCommand != -1)?colonistCommands[currCommand]:"")); //up / down arrow
        else if (currMode == 2 && currType == 2)    //explorer
            commandLabel.setText("COMMAND (\u2191 / \u2193): " + ((currCommand != -1)?rangedCommands[currCommand]:"")); //up / down arrow
        else if (currMode == 2 && currType == 3)    //colonist
            commandLabel.setText("COMMAND (\u2191 / \u2193): " + ((currCommand != -1)?meleeCommands[currCommand]:"")); //up / down arrow

        else if (currMode == 3)
            commandLabel.setText("COMMAND (\u2191 / \u2193): " + ((currCommand != -1)?armyCommands[currCommand]:"")); //up / down arrow
        else if (currMode == 0 && currTypeInstance != -1)
            commandLabel.setText("COMMAND (\u2191 / \u2193): " + "move");
        else
            commandLabel.setText("COMMAND (\u2191 / \u2193): ");

        if(currTypeInstance == -1) {
            typeInstanceLabel.setText("TYPE INSTANCE (\u2190 / \u2192): ");
        }
        else {
            switch(currMode){
                case 1: //structure
                    typeInstanceLabel.setText("TYPE INSTANCE (\u2190 / \u2192): " +  controller.getCurrentStructureSelection(  currMode, currType, currTypeInstance ).getId() );
                    break;
                case 2: //unit
                    typeInstanceLabel.setText("TYPE INSTANCE (\u2190 / \u2192): " +  controller.getCurrentUnitSelection(  currMode, currType, currTypeInstance ).getId() );
                    break;
                case 3: //army
                    typeInstanceLabel.setText("TYPE INSTANCE (\u2190 / \u2192): " +  controller.getCurrentArmySelection( currType, currTypeInstance ).getId() );
                    break;
            }
            controller.zoomToCurrSelection( currMode, currType, currTypeInstance );
            controller.updateStatusView( currMode, currType, currTypeInstance  );
        }

    }

    public void keyTyped(KeyEvent e)    {}
    public void keyReleased(KeyEvent e) {}
    public void keyPressed(KeyEvent e)  {

        // zoom to current selection
        if( e.getKeyChar() == 'f') {
            controller.zoomToCurrSelection( currMode, currType, currTypeInstance );
        }

        // start recording path
        if(isRecordingPath == false && e.getKeyChar() == '5') {        // TODO: make compatible with both structures/ units
            if(controller.getCurrentUnitSelection(  currMode, currType, currTypeInstance ) == null)
                return;
            pathSelectController.startRecordingPath( controller.getCurrentUnitSelection(  currMode, currType, currTypeInstance ).getLocation(), controller.getCurrentUnitSelection(  currMode, currType, currTypeInstance ).getUnitStats().getMovement()   );
            isRecordingPath = true;
        }
        // execute recorded path
        else if(isRecordingPath == true && e.getKeyChar() == '5') {

            System.out.println(pathSelectController.getDestinationTile().getxCoordinate() + " " + pathSelectController.getDestinationTile().getyCoordinate());

            pathSelectController.drawPath( controller.getCurrentUnitSelection(  currMode, currType, currTypeInstance ) );
            isRecordingPath = false;
        }
        // add key to recorded path
        else if( isRecordingPath ) {           // if recording path, pass key to controller to update path
            pathSelectController.moveCursor(String.valueOf( e.getKeyChar() ) );
        }

        key_char = e.getKeyChar();

        if(e.getKeyCode() == UP_KEY_CODE && e.getModifiers() == CONTROL_KEY_CODE ) {

            currMode = ++currMode % modes.length;

            currType = -1;      // when the user changes MODE, reset the currently selected TYPE
            currTypeInstance = -1;
        }
        else if(e.getKeyCode() == DOWN_KEY_CODE && e.getModifiers() == CONTROL_KEY_CODE ) {

            if (currMode > 0) currMode--;
            else currMode = modes.length - 1;

            currType = -1;      // when the user changes MODE, reset the currently selected TYPE
            currTypeInstance = -1;
        }
        else if(e.getKeyCode() == LEFT_KEY_CODE && e.getModifiers() == CONTROL_KEY_CODE ) {

            if (currType > 0) currType--;
            else currType = getNumTypes( currMode ) - 1;

            currCommand = -1;   // when the user changes TYPE, reset the currently selected COMMAND
            currTypeInstance = -1;
            controller.updateStatusView(currMode, currType, currTypeInstance);
        }
        else if(e.getKeyCode() == RIGHT_KEY_CODE && e.getModifiers() == CONTROL_KEY_CODE ) {

            if( getNumTypes( currMode ) != 0)
                currType = ++currType % getNumTypes( currMode );

            currCommand = -1;   // when the user changes TYPE, reset the currently selected COMMAND
            currTypeInstance = -1;
            controller.updateStatusView(currMode, currType, currTypeInstance);
        }
        else if(e.getKeyCode() == UP_KEY_CODE) {

            if( getNumCommands( currType, currMode ) != 0)
                currCommand = ++currCommand % getNumCommands( currType, currMode );

        }
        else if(e.getKeyCode() == DOWN_KEY_CODE) {

            if (currCommand > 0) currCommand--;
            else currCommand = getNumCommands( currType, currMode ) - 1;

        }
        else if(e.getKeyCode() == RIGHT_KEY_CODE) {

            if( getNumInstances( currMode, currType ) != 0)
                currTypeInstance = ++currTypeInstance % getNumInstances( currMode, currType );

        }
        else if(e.getKeyCode() == LEFT_KEY_CODE) {

            if (currTypeInstance > 0) currTypeInstance--;
            else currTypeInstance = getNumInstances( currMode, currType ) - 1;

        }

        updateCommand();
    }

    private int getNumTypes(int currMode) {        // get # of options the current MODE has
        int size = 0;
        if(currMode == 1)
            size = structureTypes.length;
        else if (currMode == 2)
            size = unitTypes.length;
        else if (currMode == 3)
            size = armySubTypes.length;
        return size;
    }

    private int getNumCommands(int currType, int currMode) {      // get # of options the current TYPE has
        int size = 0;

        if(currMode == 1 && currType == 0)           // CAPITAL
            return capitalCommands.length;
        else if(currMode == 1 && currType == 1)       // FORT
            return fortCommands.length;
        else if(currMode == 1 && currType == 2)         // UNIVERSITY
            return unitCommands.length;
        else if(currMode == 1 && currType == 3)        // OBSERVATION TOWER
            return observationCommands.length;
        else if(currMode == 1 && currType == 4)        // FARM
            return farmCommands.length;
        else if(currMode == 1 && currType == 5)        // MINE
            return mineCommands.length;
        else if(currMode == 1 && currType == 6)        // POWER PLANT
            return powerPlantCommands.length;

        else if(currMode == 2 && currType == 0) {        // EXPLORER
            return explorerCommands.length;
        }
        else if(currMode == 2 && currType == 1) {        // COLONIST
            return colonistCommands.length;
        }
        else if(currMode == 2 && currType == 2) {        // RANGED UNIT
            return rangedCommands.length;
        }
        else if(currMode == 2 && currType == 3) {        // MELEE UNIT
            return meleeCommands.length;
        }

        else if (currMode == 3)
            size = armyCommands.length;
        return size;
    }

    private int getNumInstances(int currMode, int currType) {      // get # instances of a given type

        if(currMode == 2 && currType == 0) {        // EXPLORER
                return controller.getNumExplorers();
        }
        else if(currMode == 2 && currType == 1) {        // COLONIST
                 return controller.getNumColonist();
        }
        else if(currMode == 2 && currType == 2) {        // RANGED UNIT
                 return controller.getNumRanged();
        }
        else if(currMode == 2 && currType == 3) {        // MELEE UNIT
                 return controller.getNumMelee();
        }


        if(currMode == 1 && currType == 0)           // CAPITAL
            return controller.getNumCapital();
        else if(currMode == 1 && currType == 1)       // FORT
            return controller.getNumFort();
        else if(currMode == 1 && currType == 2)         // UNIVERSITY
            return controller.getNumUniversity();
        else if(currMode == 1 && currType == 3)        // OBSERVATION TOWER
            return controller.getNumObsTower();
        else if(currMode == 1 && currType == 4)        // FARM
            return controller.getNumFarm();
        else if(currMode == 1 && currType == 5)        // MINE
            return controller.getNumMine();
        else if(currMode == 1 && currType == 6)        // POWER PLANT
            return controller.getNumPowerPlant();

        if(currMode == 3 && currType == 0)        // ARMY : ENTIRE ARMY
            return controller.getNumArmy();


        else
              return 0;
    }

    public String getCommand() {
        StringBuilder sb = new StringBuilder();

        if(currMode == 1 && currType == 0) {         // CAPITAL
            sb.append(((currCommand != -1) ? capitalCommands[currCommand] : "")); //up / down arrow
        System.out.println("inserting to command");
        }
        else if(currMode == 1 && currType == 1)       // FORT
            sb.append(((currCommand != -1)?fortCommands[currCommand]:"")); //up / down arrow
        else if(currMode == 1 && currType == 2)         // UNIVERSITY
            sb.append(((currCommand != -1)?universityCommands[currCommand]:"")); //up / down arrow
        else if(currMode == 1 && currType == 3)        // OBSERVATION TOWER
            sb.append(((currCommand != -1)?observationCommands[currCommand]:"")); //up / down arrow
        else if(currMode == 1 && currType == 4)        // FARM
            sb.append(((currCommand != -1)?farmCommands[currCommand]:"")); //up / down arrow
        else if(currMode == 1 && currType == 5)        // MINE
            sb.append(((currCommand != -1)?mineCommands[currCommand]:"")); //up / down arrow
        else if(currMode == 1 && currType == 6)        // POWER PLANT
            sb.append(((currCommand != -1)?powerPlantCommands[currCommand]:"")); //up / down arrow


        else if (currMode == 2 && currType == 0)    //explorer command
            sb.append(((currCommand != -1)?explorerCommands[currCommand]:"")); //up / down arrow
        else if (currMode == 2 && currType == 1)    //colonist
            sb.append(((currCommand != -1)?colonistCommands[currCommand]:"")); //up / down arrow
        else if (currMode == 2 && currType == 2)    //explorer
            sb.append(((currCommand != -1)?rangedCommands[currCommand]:"")); //up / down arrow
        else if (currMode == 2 && currType == 3)    //colonist
            sb.append(((currCommand != -1)?meleeCommands[currCommand]:"")); //up / down arrow



        else if (currMode == 3)
            sb.append(((currCommand != -1)?armyCommands[currCommand]:"")); //up / down arrow
        else if (currMode == 0 && currTypeInstance != -1)
            sb.append("move");


        return sb.toString();
    }

    public void clearCommand() {
        currMode = -1;
        currType = -1;
        currTypeInstance = -1;
        currCommand = -1;
        updateCommand();
    }

    public void setController(CommandSelectController controller) {
        this.controller = controller;
    }
    public JButton getEndTurnButton() {
        return endTurnButton;
    }
    public JButton getExecuteCommandButton() {
        return executeCommandButton;
    }




    public CommandSelect() {

        JPanel commandSelectPanel = new JPanel();
        commandSelectPanel.setLayout(new GridLayout(0,1));

        modeLabel = new JLabel("MODE: "); //up / down arrow
        typeLabel = new JLabel("TYPE: "); //left / right arrow
        typeInstanceLabel = new JLabel("TYPE INSTANCE: "); //left / right arrow
        commandLabel = new JLabel("COMMAND: "); //up / down arrow

        modeLabel.setFont(new Font("Serif", Font.BOLD, 18));
        modeLabel.setForeground(new Color(0xff000000));
        modeLabel.setBackground(new Color(0xffF5F5DC));
        modeLabel.setOpaque(true);
        typeLabel.setFont(new Font("Serif", Font.BOLD, 18));
        typeLabel.setForeground(new Color(0xff000000));
        typeLabel.setBackground(new Color(0xffF5F5DC));
        typeLabel.setOpaque(true);
        typeInstanceLabel.setFont(new Font("Serif", Font.BOLD, 18));
        typeInstanceLabel.setForeground(new Color(0xff000000));
        typeInstanceLabel.setBackground(new Color(0xffF5F5DC));
        typeInstanceLabel.setOpaque(true);
        commandLabel.setFont(new Font("Serif", Font.BOLD, 18));
        commandLabel.setForeground(new Color(0xff000000));
        commandLabel.setBackground(new Color(0xffF5F5DC));
        commandLabel.setOpaque(true);

        commandSelectPanel.add(modeLabel);
        commandSelectPanel.add(typeLabel);
        commandSelectPanel.add(typeInstanceLabel);
        commandSelectPanel.add(commandLabel);

        executeCommandButton = new JButton("ISSUE COMMAND");
        executeCommandButton.setFont(new Font("plain", Font.BOLD, 15));
        executeCommandButton.setForeground(Color.black);
        executeCommandButton.setBackground( new Color(0xffF5F5DC) );
        executeCommandButton.setOpaque(true);

        endTurnButton = new JButton("END TURN");
        endTurnButton.setFont(new Font("plain", Font.BOLD, 15));
        endTurnButton.setBackground( new Color(0xffF5F5DC) );
        endTurnButton.setForeground(Color.black);
        endTurnButton.setOpaque(true);

        JPanel temp = new JPanel();
        temp.add(executeCommandButton);
        temp.add( endTurnButton );
        temp.setBackground(new Color(0xffF5F5DC));
        temp.setOpaque(true);

        commandSelectPanel.add( temp );

        this.add( commandSelectPanel, BorderLayout.SOUTH );

        addKeyListener(this);

        setMinimumSize(new Dimension(323, 200));
    }

    public void setController( PathSelectController msc ) {
        this.pathSelectController = msc;
    }
    public int getCurrMode() {
        return  currMode;
    }
    public int getCurrType() {
        return  currType;
    }
    public int getCurrTypeInstance() {
        return  currTypeInstance;
    }
    public int getCurrCommand() {
        return  currCommand;
    }

}