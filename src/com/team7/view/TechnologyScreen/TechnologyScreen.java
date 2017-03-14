package com.team7.view.TechnologyScreen;

import com.team7.view.ScreenSelectButtons;

import javax.swing.*;
import java.awt.*;

/**
 * view of technology 'tree'
 */
public class TechnologyScreen extends JPanel {

    private ScreenSelectButtons screenSelectBtns = null;

    public TechnologyScreen() {
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
    public JButton getUnitScreenButton() {
        return screenSelectBtns.getUnitScreenButton();
    }
    public JButton getMapScreenSelectButton() {
        return screenSelectBtns.getMapScreenSelectButton();
    }
}

