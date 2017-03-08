package com.team7.view.UnitScreen;

import com.team7.view.ScreenSelectButtons;

import javax.swing.*;
import java.awt.*;

public class UnitScreen extends JPanel {

    private ScreenSelectButtons screenSelectBtns = null;

    public UnitScreen() {

        screenSelectBtns = new ScreenSelectButtons();
        this.add(screenSelectBtns, BorderLayout.NORTH);
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
    public JButton getStructureScreenButton() {
        return screenSelectBtns.getStructureScreenButton();
    }

}
