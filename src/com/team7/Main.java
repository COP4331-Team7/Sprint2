package com.team7;

import com.team7.controller.OptionsController;
import com.team7.view.OptionsScreen;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
	// write your code here



        JFrame frame = new JFrame();
        frame.setSize(new Dimension(800,1200));
        OptionsScreen screen = new OptionsScreen();
        OptionsController optionsController = new OptionsController(screen);
        frame.add(screen);
        frame.setVisible(true);

        frame.repaint();
    }
}
