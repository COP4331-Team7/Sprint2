package com.team7.view;

import javax.swing.*;
import java.awt.*;

public class HomeScreen extends JPanel {

    private HomeImage homeBackground = null;
    private HomeButtons homeButtons = null;

    public HomeScreen() {

        this.setLayout(new BorderLayout());

        homeBackground = new HomeImage();
        homeButtons = new HomeButtons();

        this.add(homeBackground, BorderLayout.CENTER);
        this.add(homeButtons, BorderLayout.SOUTH);
    }

    public HomeButtons getHomeButtons() {
            return homeButtons;
    }

}
