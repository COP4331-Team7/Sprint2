package com.team7.view;

import javax.swing.*;
import java.awt.*;

public class HomeButtons extends JPanel {

        private JButton playButton = null;
        private JButton quitButton = null;

        public HomeButtons() {

            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(0,1));

            playButton = new JButton("START GAME");
            quitButton = new JButton("QUIT");

            panel.add( playButton );
            panel.add( quitButton );

            this.add( panel, BorderLayout.CENTER );
       }

       public JButton getQuitButton() {
            return quitButton;
       }
       public JButton getPlayButton() {
            return playButton;
       }
}
