package com.team7.view.MainScreen;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;

public class MainViewMiniMap extends JPanel implements MouseListener, MapStats {

    public static BufferedImage image, fullMapImage, backgroundImg, backgroundImg2, backgroundImg3;
    private Graphics2D g2d, g2ds, g2dss, g2dsss;
    private final static int zoomRate = 45; // 1000 / 40 = 25 frames per second
    private final static int SIZE = 200;
    private int TILES_VISIBLE_X, TILES_VISIBLE_Y;
    private static int WIDTH, HEIGHT;
    private static int SUB_WIDTH, SUB_HEIGHT;
    private final static int BORDER_WIDTH = 30;
    private final static int BORDER_WIDTH2 = 10;
    private final static int BORDER_WIDTH3 = 10;


    private MainViewImage mainViewImage;
    public int x_center, y_center;    // where the window in focused on

    public MainViewMiniMap()
    {
        fullMapImage = new BufferedImage(TILE_SIZE*MAP_TILE_WIDTH + (int)(MAP_TILE_WIDTH * (TILE_SIZE - TILE_SIZE / 1.73) + TILE_SIZE)  , (int)(TILE_SIZE*MAP_TILE_HEIGHT/1.5), BufferedImage.TYPE_INT_ARGB);
        double ratio = fullMapImage.getWidth()/fullMapImage.getHeight();
        WIDTH =  (int)(SIZE *  ratio);
        HEIGHT = (int)(SIZE /  ratio);
        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
        backgroundImg = new BufferedImage(WIDTH + BORDER_WIDTH/2, HEIGHT + BORDER_WIDTH/2, BufferedImage.TYPE_INT_ARGB);
        backgroundImg2 = new BufferedImage(WIDTH + BORDER_WIDTH/2 + BORDER_WIDTH2, HEIGHT + BORDER_WIDTH/2  + BORDER_WIDTH2, BufferedImage.TYPE_INT_ARGB);
        backgroundImg3 = new BufferedImage(WIDTH + BORDER_WIDTH/2 + BORDER_WIDTH2 + BORDER_WIDTH3, HEIGHT + BORDER_WIDTH/2  + BORDER_WIDTH2 + BORDER_WIDTH3, BufferedImage.TYPE_INT_ARGB);
        g2dsss = (Graphics2D)backgroundImg3.createGraphics();
        g2dsss.setColor(new Color(0xFF777777));
        g2dsss.fillRect(0, 0, backgroundImg3.getWidth(), backgroundImg3.getHeight() );

        g2dss = (Graphics2D)backgroundImg2.createGraphics();
        g2dss.setColor(new Color(0xAAAA8888));
        g2dss.fillRect(0, 0, backgroundImg2.getWidth(), backgroundImg2.getHeight() );


        g2ds = (Graphics2D)backgroundImg.createGraphics();
        g2ds.setColor(new Color(0xFF000000));
        g2ds.fillRect(0, 0, backgroundImg.getWidth(), backgroundImg.getHeight());
        g2d = (Graphics2D)image.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        setPreferredSize(  new Dimension( backgroundImg3.getWidth(), backgroundImg3.getHeight()) );
      // this.setBorder(BorderFactory.createLineBorder(new Color(0xFF000000), 1));
        drawMapArea();
        addMouseListener(this);
    }

    public void setMainViewImage(MainViewImage mainViewImage) {
        this.mainViewImage = mainViewImage;
    }


    public void paintComponent( Graphics g )
    {
        super.paintComponent( g );
        g.drawImage( backgroundImg3, 0, 0, this );
        g.drawImage( backgroundImg2, BORDER_WIDTH3/2, BORDER_WIDTH3/2, this );
        g.drawImage( backgroundImg, BORDER_WIDTH2/2 + BORDER_WIDTH3/2, BORDER_WIDTH2/2 + BORDER_WIDTH3/2, this );
        g.drawImage( image, BORDER_WIDTH/2 + BORDER_WIDTH3/2, BORDER_WIDTH/2 + BORDER_WIDTH3/2, this );
    }

    public void drawMapArea() {

        g2d.drawImage(fullMapImage, 0, 0, WIDTH, (int)(HEIGHT * 1.5), 0, 0, fullMapImage.getWidth(),
                fullMapImage.getHeight(), null);

        shadeUnselectedArea();
        repaint();
    }

    public void shadeUnselectedArea() {
        double shade_factor = .3;// shade factor, [0, 1]
        int newR, newG, newB, newColor;
        for(int i = 0; i < WIDTH; i++) {
            for(int j = 0; j < HEIGHT; j++) {
                if (i == x_center || i == x_center + SUB_WIDTH || j == y_center || j == y_center + SUB_HEIGHT) {
                    image.setRGB( i, j, 0xAAAA8888);
                    continue;
                }

                if( !(i > x_center && i < x_center + SUB_WIDTH && j > y_center && j < y_center + SUB_HEIGHT) ) {
                    int intARGB = image.getRGB(i, j);
                    newR = (int) ( (( intARGB >> 16) & 0xFF) * (1 - shade_factor) );
                    newG = (int) ( (( intARGB >>  8) & 0xFF) * (1 - shade_factor) );
                    newB = (int) ( (( intARGB >>  0) & 0xFF) * (1 - shade_factor) );
                    newColor = ( 0xFF000000 | (newR << 16) | (newG << 8) | (newB << 0) );
                    image.setRGB( i, j, newColor);
                }

            }
        }
    }

    public void setMiniMapImage(BufferedImage img, int x, int y) {
        TILES_VISIBLE_X = x;
        TILES_VISIBLE_Y = y;
        SUB_WIDTH  = (int) (WIDTH * TILES_VISIBLE_Y /  MAP_TILE_WIDTH);
        SUB_HEIGHT = (int)(HEIGHT * TILES_VISIBLE_X / MAP_TILE_HEIGHT);
        fullMapImage = img;
    }

    public void setMiniMapImage(BufferedImage img) {
        fullMapImage = img;
        drawMapArea();
    }

    public void setFocus(int x, int y) {
        x_center = (int)(x  * WIDTH / MAP_TILE_WIDTH);
        y_center = (int)(y  * HEIGHT / MAP_TILE_HEIGHT);

        if(x_center < 0)              // adjust if out of bounds
            x_center = 0;
        else if (x_center > WIDTH - SUB_WIDTH)
            x_center = WIDTH - SUB_WIDTH;
        if(y_center < 0)
            y_center = 0;
        else if(y_center > HEIGHT - SUB_HEIGHT)
            y_center = HEIGHT - SUB_HEIGHT;

        drawMapArea();
    }

    // implement MouseListener interface methods:
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseClicked(MouseEvent e) {

        double x_offset = ( (double)e.getX() / WIDTH ) * MAP_TILE_WIDTH - 7;
        double y_offset = ( (double)e.getY() / HEIGHT ) * MAP_TILE_HEIGHT - 15;

        if(x_offset < 0)              // adjust if out of bounds
            x_offset = 0;
        else if (x_offset >= MAP_TILE_WIDTH - TILES_VISIBLE_X/2)
            x_offset = MAP_TILE_WIDTH - TILES_VISIBLE_X/2;
        if(y_offset < 0)
            y_offset = 0;
        else if(y_offset >= MAP_TILE_HEIGHT - TILES_VISIBLE_Y)
            y_offset = MAP_TILE_HEIGHT - TILES_VISIBLE_Y;

        mainViewImage.zoomToDestination( (int)x_offset, (int)y_offset, zoomRate );
    }
}