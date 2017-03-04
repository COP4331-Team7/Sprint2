package com.team7.view.MainScreen;

import com.team7.view.ScreenSelectButtons;

import javax.swing.*;
import java.awt.*;

public class MainScreen extends JPanel {

    private ScreenSelectButtons screenSelectBtns = null;
    private MainViewImage mainAreaView = null;
    private MainViewInfo mainStatusInfo = null;
    private MainViewMiniMap mainViewSelection = null;

    public MainScreen() {

        this.setLayout(new BorderLayout());

        screenSelectBtns = new ScreenSelectButtons();
        mainViewSelection = new MainViewMiniMap(  );
        mainAreaView = new MainViewImage( mainViewSelection );
        mainStatusInfo = new MainViewInfo( );

        this.add( screenSelectBtns, BorderLayout.NORTH );
        this.add( mainAreaView, BorderLayout.CENTER );

        JPanel temp = new JPanel(); // create JPanel to hold two other JPanels
                                    // only one JPanel can be in a specific location, so we add 2 panels to a single panel
                                    // then add the single panel where we want both panels
        //temp.add( mainStatusInfo, BorderLayout.WEST );
        temp.add( mainViewSelection, BorderLayout.NORTH);
        this.add( temp, BorderLayout.SOUTH );
    }

    public void drawMap() {
        mainAreaView.reDrawMap();
    }
    public ScreenSelectButtons getScreenSelectButtons() {
            return screenSelectBtns;
    }
    public MainViewImage getMainViewImage() {
        return mainAreaView;
    }
    public MainViewInfo getMainViewInfo() {
        return mainStatusInfo;
    }
}
