package com.team7.view.MapScreen;

import com.team7.view.ScreenSelectButtons;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;

public class MapScreen extends JPanel  {

    public static BufferedImage image;
    private static int MAP_IMAGE_WIDTH_IN_PIXELS;
    private static int MAP_IMAGE_HEIGHT_IN_PIXELS;
    private ScreenSelectButtons screenSelectBtns = null;


    public MapScreen()
    {
        MAP_IMAGE_WIDTH_IN_PIXELS = (int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth() );
        screenSelectBtns = new ScreenSelectButtons();
        this.add(screenSelectBtns);
    }

    public void setImage(BufferedImage img) {
        this.image = img;

        MAP_IMAGE_HEIGHT_IN_PIXELS = ( MAP_IMAGE_WIDTH_IN_PIXELS * image.getHeight() / image.getWidth() );

        repaint();
    }

    public void paintComponent( Graphics g )
    {
        super.paintComponent( g );

        g.setColor(new Color(0xffCABD80));

        g.fillRect( 0, 0, MAP_IMAGE_WIDTH_IN_PIXELS, (int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight() ));

        g.drawImage(image, 0, 100, (int)(MAP_IMAGE_WIDTH_IN_PIXELS), MAP_IMAGE_HEIGHT_IN_PIXELS, 0, 0, image.getWidth(),
                image.getHeight(), null);

    }

    public ScreenSelectButtons getScreenSelectButtons() {
        return screenSelectBtns;
    }
}