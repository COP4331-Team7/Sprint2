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
        this.setBorder(BorderFactory.createLineBorder(new Color(0xffCABD80), 1));
    }

    public JButton getQuitButton() {
        return homeButtons.getQuitButton();
    }
    public JButton getPlayButton() {
        return homeButtons.getPlayButton();
    }
    public JButton getOptionButton() {
        return homeButtons.getOptionButton();
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
                image = ImageIO.read(getClass().getResourceAsStream("/terrains/homeScreenImage.png"));
            }
            catch (IOException e) {
            }

           // Dimension size = new Dimension( image.getWidth(), image.getHeight());
          //  setPreferredSize( size );
        }

        public void paintComponent( Graphics g )
        {
            super.paintComponent( g );

            // scale image to fill screen
            g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), 0, 0, image.getWidth(),
                    image.getHeight(), null);
//            System.out.println(this.getWidth());
//            System.out.println(this.getHeight());
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
        private JButton options = null;

        public HomeButtons() {

            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(0,1));

            playButton = new JButton("START GAME");
            playButton.setPreferredSize(new Dimension(300, 40));
            options = new JButton("OPTIONS");
            options.setPreferredSize(new Dimension(300, 40));
            quitButton = new JButton("QUIT");
            quitButton.setPreferredSize(new Dimension(300, 40));

            options.setFont(new Font("Serif", Font.BOLD, 20));
            options.setBackground( new Color(0xffCABD80) );
            options.setForeground(Color.black);
            options.setOpaque(true);

            playButton.setFont(new Font("Serif", Font.BOLD, 20));
            playButton.setBackground( new Color(0xffCABD80) );
            playButton.setForeground(Color.black);
            playButton.setOpaque(true);

            quitButton.setFont(new Font("Serif", Font.BOLD, 20));
            quitButton.setBackground( new Color(0xffCABD80) );
            quitButton.setForeground(Color.black);
            quitButton.setOpaque(true);

            panel.setLayout(new GridLayout(0, 3));

            panel.add( playButton  );
            panel.add( options );
            panel.add( quitButton );

            this.add( panel, BorderLayout.CENTER );
        }

        public JButton getQuitButton() {
            return quitButton;
        }
        public JButton getOptionButton() {
            return options;
        }

        public JButton getPlayButton() {
            return playButton;
        }
    }

}
