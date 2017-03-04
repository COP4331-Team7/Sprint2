package com.team7.view.HomeScreen;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

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

    public JButton getQuitButton() {
        return homeButtons.getQuitButton();
    }
    public JButton getPlayButton() {
        return homeButtons.getPlayButton();
    }



    public HomeButtons getHomeButtons() {
            return homeButtons;
    }

    class HomeImage extends JPanel {

        private BufferedImage image;
        private Graphics2D g2d;

        public HomeImage()
        {
            try {
                image = ImageIO.read(getClass().getResourceAsStream("/terrains/bg1.png"));
            }
            catch (IOException e) {
            }

            Dimension size = new Dimension( image.getWidth(), image.getHeight());
            setPreferredSize( size );
        }

        public void paintComponent( Graphics g )
        {
            super.paintComponent( g );
            g.drawImage( image, 0, 0, this );
        }

        public JButton getQuitButton() {
            return homeButtons.getQuitButton();
        }
        public JButton getPlayButton() {
            return homeButtons.getPlayButton();
        }

    }

    class HomeButtons extends JPanel {

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

}
