package com.team7.view.MainScreen;

import com.team7.model.entity.Command;
import com.team7.view.ScreenSelectButtons;

import javax.swing.*;
import java.awt.*;

public class MainScreen extends JPanel {

    private ScreenSelectButtons screenSelectBtns = null;
    private MainViewImage mainAreaView = null;
    private MainViewInfo mainStatusInfo = null;
    private MainViewMiniMap mainViewSelection = null;
    private CommandSelect commandSelect = null;

    public MainScreen() {

        this.setLayout(new BorderLayout());

        screenSelectBtns = new ScreenSelectButtons();
        mainViewSelection = new MainViewMiniMap(  );
        mainAreaView = new MainViewImage( mainViewSelection );
        mainStatusInfo = new MainViewInfo( );
        commandSelect = new CommandSelect();

        this.add( screenSelectBtns, BorderLayout.NORTH );
        this.add( mainAreaView, BorderLayout.CENTER );

        JPanel temp = new JPanel(); // create JPanel to hold two other JPanels
                                    // only one JPanel can be in a specific location, so we add 2 panels to a single panel
                                    // then add the single panel where we want both panels

        temp.add( mainStatusInfo, BorderLayout.WEST );
        temp.add( commandSelect, BorderLayout.EAST);
        temp.add( mainViewSelection, BorderLayout.NORTH);

        temp.setBackground(new Color(0x0FCABD80));
        temp.setOpaque(true);

        this.setBorder(BorderFactory.createLineBorder(new Color(0xffCABD80), 3));
        this.add( temp, BorderLayout.SOUTH );
    }

    public void giveCommandSelectFocus() {
        commandSelect.setFocusable( true );
        commandSelect.requestFocus();
    }

    public void drawMap() {
        mainAreaView.reDrawMap();
    }
    public void redraw() { mainAreaView.reDrawMap(); }
    public JButton getMainScreenButton() { return screenSelectBtns.getMainScreenButton(); }
    public JButton getUnitScreenButton() {
        return screenSelectBtns.getUnitScreenButton();
    }
    public JButton getStructureScreenButton() {
        return screenSelectBtns.getStructureScreenButton();
    }
    public JButton getOptionScreenButton() {
        return screenSelectBtns.getOptionsScreenSelectButton();
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
    public CommandSelect getCommandSelect() {
        return commandSelect;
    }
    public JButton getEndTurnButton() {
        return commandSelect.getEndTurnButton();
    }
    public JButton getExecuteCommandButton() {
        return commandSelect.getExecuteCommandButton();
    }
    public MainViewMiniMap getMiniMap() {
        return mainViewSelection;
    }
}
