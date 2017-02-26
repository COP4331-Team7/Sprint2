package com.team7.view;

// import com.team7.controller.MainScreenController;

import javax.swing.*;
import java.awt.*;

public class MainScreen extends JPanel {

    private ScreenSelectButtons screenSelectBtns = null;
    private MainViewImage mainAreaView = null;
    private MainViewInfo mainStatusInfo = null;
    // private Command commandSelecter = null;
    private MainViewSelection mainViewSelection = null;
    // private MainScreenController msc = null;

    public MainScreen() {

        this.setLayout(new BorderLayout());

        screenSelectBtns = new ScreenSelectButtons();
        mainViewSelection = new MainViewSelection(  );
        mainAreaView = new MainViewImage( mainViewSelection );
        mainStatusInfo = new MainViewInfo( );
//        try {commandSelecter = new Command( ); }
//        catch (Exception e) {
//
//        }


        this.add( screenSelectBtns, BorderLayout.NORTH );
        this.add( mainAreaView, BorderLayout.CENTER );

        JPanel temp = new JPanel(); // create JPanel to hold two other JPanels
                                    // only one JPanel can be in a specific location, so we add 2 panels to a single panel
                                    // then add the single panel where we want both panels

        //temp.add( mainStatusInfo, BorderLayout.WEST );
        //temp.add( commandSelecter, BorderLayout.SOUTH);
        temp.add( mainViewSelection, BorderLayout.NORTH);

        this.add( temp, BorderLayout.SOUTH );
    }

    public void giveCommandFocus() {          // give JPanel (commandSelecter) focus to 'make its KeyListener is active'
//        commandSelecter.setFocusable(true);
//        commandSelecter.requestFocus();
    }

    public ScreenSelectButtons getScreenSelectButtons() {
            return screenSelectBtns;
    }

    public void drawMap() {
        mainAreaView.reDrawMap();
    }

    public MainViewImage getMainViewImage() {
        return mainAreaView;
    }

    public MainViewInfo getMainViewInfo() {
        return mainStatusInfo;
    }

//    public Command getCommand() {
//        return commandSelecter;
//    }
}
