package com.team7.view.UnitScreen;

import com.team7.view.ScreenSelectButtons;

import javax.swing.*;
import java.awt.*;

public class UnitScreen extends JPanel {

    private ScreenSelectButtons screenSelectBtns = null;

    public UnitScreen() {

        screenSelectBtns = new ScreenSelectButtons();
        this.add(screenSelectBtns, BorderLayout.NORTH);



        this.setBorder(BorderFactory.createLineBorder(new Color(0xffCABD80), 3));
    }


    public JButton getMainScreenButton() {
        return screenSelectBtns.getMainScreenButton();
    }
    public JButton getOptionScreenButton() {
        return screenSelectBtns.getOptionsScreenSelectButton();
    }
    public JButton getStructureScreenButton() {
        return screenSelectBtns.getStructureScreenButton();
    }
    public JButton getMapScreenSelectButton() {
        return screenSelectBtns.getMapScreenSelectButton();
    }
}
