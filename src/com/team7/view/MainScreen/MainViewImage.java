package com.team7.view.MainScreen;

import com.team7.Main;
import com.team7.controller.PathSelectController;
import com.team7.model.DrawableTileState;
import com.team7.model.Map;
import com.team7.model.Player;
import com.team7.model.Tile;
import com.team7.model.entity.Command;
import com.team7.model.terrain.Crater;
import com.team7.model.terrain.Desert;
import com.team7.model.terrain.Flatland;
import com.team7.model.terrain.Mountains;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Set;

public class MainViewImage extends JPanel implements MouseListener, MapStats {

        private int MAP_IMAGE_WIDTH_IN_PIXELS;
        private int MAP_IMAGE_HEIGHT_IN_PIXELS;
        private int TILES_VISIBLE_X;                    // # tiles visible on map
        private int TILES_VISIBLE_Y;
        private final static double mapScale_x = 0.95;  // what % of screen the map takes up
        private final static double mapScale_y = 0.60;

        
        public static BufferedImage image;
        private BufferedImage tileImage_1, tileImage_2, tileImage_3, tileImage_4;
        private BufferedImage moneyBagImage;
        private BufferedImage moonRockImage;
        private BufferedImage hieroglyphicBookImage;
        private BufferedImage elixerShowerImage;
        private BufferedImage stormImage;
        private BufferedImage colonistImage;
        private BufferedImage explorerImage;
        private BufferedImage obstacleImage;
        private BufferedImage oneShotImage;
        private BufferedImage meleeImage;
        private BufferedImage rangeImage;
        private BufferedImage armyImage;
        private BufferedImage ventImage;
        private BufferedImage skullImage;
        private BufferedImage baseImage;
        private BufferedImage highlightImage;
         private BufferedImage invisible;


    public int x_center, y_center;    // where the window is focused on
        public int x_dest, y_dest;        // where the window should be focused on

         private  BufferedImage ghostImage;

         BufferedImage tempImg ;
        Graphics2D g2ds;

        private MainViewMiniMap mainViewSelection;
        private Tile[][] grid;

        /****************************************************************/
        Player player = null;

        public MainViewImage( MainViewMiniMap ms )
        {
            MAP_IMAGE_WIDTH_IN_PIXELS = (int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth()   * mapScale_x);
            MAP_IMAGE_HEIGHT_IN_PIXELS = (int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight() * mapScale_y);
            TILES_VISIBLE_Y = (int)((MAP_IMAGE_WIDTH_IN_PIXELS / (2 * TILE_SIZE - TILE_SIZE/1.73))) - 1;
            TILES_VISIBLE_X = (2 * MAP_IMAGE_HEIGHT_IN_PIXELS / TILE_SIZE) + 2;

            addMouseListener(this);
            this.mainViewSelection = ms;

            try {
               tileImage_1 = ImageIO.read(Main.class.getClass().getResourceAsStream("/terrains/a.png"));
               tileImage_2 = ImageIO.read(Main.class.getClass().getResourceAsStream("/terrains/a.png"));
               tileImage_3 = ImageIO.read(Main.class.getClass().getResourceAsStream("/terrains/c.png"));
               tileImage_4 = ImageIO.read(Main.class.getClass().getResourceAsStream("/terrains/c.png"));
               moneyBagImage = ImageIO.read(Main.class.getClass().getResourceAsStream("/items/moneyBag.png"));
               moonRockImage = ImageIO.read(Main.class.getClass().getResourceAsStream("/items/moonRock.png"));
               hieroglyphicBookImage = ImageIO.read(Main.class.getClass().getResourceAsStream("/items/bookImage1.png"));
               elixerShowerImage = ImageIO.read(Main.class.getClass().getResourceAsStream("/areaEffects/elixirShowerImage.png"));
               stormImage = ImageIO.read(Main.class.getClass().getResourceAsStream("/areaEffects/stormImageBig.png"));
               colonistImage = ImageIO.read(Main.class.getClass().getResourceAsStream("/units/colonistImage.png"));
               explorerImage = ImageIO.read(Main.class.getClass().getResourceAsStream("/units/explorerImage.png"));
               oneShotImage = ImageIO.read(Main.class.getClass().getResourceAsStream("/obstacles/oneShot.png"));
               obstacleImage = ImageIO.read(Main.class.getClass().getResourceAsStream("/obstacles/stopIcon.png"));

                meleeImage = ImageIO.read(Main.class.getClass().getResourceAsStream("/units/meleeImage.png"));
                rangeImage = ImageIO.read(Main.class.getClass().getResourceAsStream("/units/rangeImage.png"));
                armyImage = ImageIO.read(Main.class.getClass().getResourceAsStream("/units/armyImagepng.png"));
                ventImage = ImageIO.read(Main.class.getClass().getResourceAsStream("/areaEffects/vent.png"));

                skullImage = ImageIO.read(Main.class.getClass().getResourceAsStream("/decals/skullImage.png"));

                baseImage = ImageIO.read(Main.class.getClass().getResourceAsStream("/structures/baseImage.png"));

                ghostImage = ImageIO.read(Main.class.getClass().getResourceAsStream("/terrains/dark_image.png"));
                highlightImage = ImageIO.read(Main.class.getClass().getResourceAsStream("/terrains/highlight.png"));
                invisible = ImageIO.read(Main.class.getClass().getResourceAsStream("/terrains/invisible.png"));


            }
            catch (IOException e) {}

            mainViewSelection.setMainViewImage( this );
            tempImg = new BufferedImage( MAP_IMAGE_WIDTH_IN_PIXELS, MAP_IMAGE_HEIGHT_IN_PIXELS, BufferedImage.TYPE_INT_ARGB);
            g2ds = (Graphics2D)tempImg.createGraphics();
        }

        public void setMap(Map map) {
            this.grid = map.getGrid();
            mainViewSelection.setMiniMapImage( getFullMapImage(), TILES_VISIBLE_X, TILES_VISIBLE_Y );
            zoomToDestination(28, 6, 60);
        }

         public void setCurrPlayer(Player p) {
              player = p;
            }

         public BufferedImage getFullMapImage() {
             BufferedImage mapImage;
             mapImage = new BufferedImage(TILE_SIZE*MAP_TILE_WIDTH + (int)(MAP_TILE_WIDTH * (TILE_SIZE - TILE_SIZE / 1.73) + TILE_SIZE)  , (int)(TILE_SIZE*MAP_TILE_HEIGHT/1.5), BufferedImage.TYPE_INT_ARGB);
             Graphics2D g2 = (Graphics2D)mapImage.createGraphics();
             int x_coord, y_coord;   // pixel coordinates of top left corner of image drawn
             int x_offset, counter, step = 0;
             int changePerStep = TILE_SIZE - (int)(TILE_SIZE/1.73);

             for(int j = 0; j < MAP_TILE_WIDTH; j++) {          // tile index on sub-screen
                 x_offset = changePerStep;
                 if(step % 2 == 0) {
                     x_offset += changePerStep;
                 }
                 else {
                     x_offset -= changePerStep;
                     x_offset += 10;
                 }
                 step++;

                 for(int i = 0; i < MAP_TILE_HEIGHT; i++) {
                     counter = 1;

                     int xx = i;                   //  index in Map's grid[][]
                     int yy = j;

                     if(xx < 0)                     // adjust if out of bounds
                         xx = 0;
                     else if (xx >= MAP_TILE_WIDTH)
                         xx = MAP_TILE_WIDTH - 1;
                     if(yy < 0)
                         yy = 0;
                     else if(yy >= MAP_TILE_HEIGHT)
                         yy = MAP_TILE_HEIGHT - 1;

                     x_coord = i * TILE_SIZE;
                     y_coord = (int)(j * TILE_SIZE / 2.4);

                     if( grid[xx][yy].getTerrain() instanceof Mountains) {
                         g2.drawImage(tileImage_1, x_coord + x_offset, y_coord, null);
                         x_offset += changePerStep * counter;
                         counter++;
                     }
                     else if (grid[xx][yy].getTerrain() instanceof Crater) {
                         g2.drawImage(tileImage_2, x_coord + x_offset, y_coord, null);
                         x_offset += changePerStep * counter;
                         counter++;
                     }
                     else if (grid[xx][yy].getTerrain() instanceof Desert) {
                         g2.drawImage(tileImage_3, x_coord + x_offset, y_coord, null);
                         x_offset += changePerStep * counter;
                         counter++;
                     }
                     else if (grid[xx][yy].getTerrain() instanceof Flatland) {
                         g2.drawImage(tileImage_4, x_coord + x_offset, y_coord, null);
                         x_offset += changePerStep * counter;
                         counter++;
                     }
                 }
             }

             return mapImage;
         }

        public void highlightRadius(Set<Tile> tiles) {

            if(player == null)
                return;

          for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[i].length; j++) {
                    if( tiles.contains( grid[i][j] ) ) {
                        grid[i][j].markVisible(player.getName());
                    }
                    else if(grid[i][j].getVisible(player.getName()) && !tiles.contains( grid[i][j] ) && PathSelectController.isRecording) {
                        grid[i][j].markShrouded( player.getName() );
                    }

                    grid[i][j].refreshDrawableState();
                }
          }

        }

        private BufferedImage drawSubsectionOfMap(int x, int y) {

             /* ----------------------------------------------------
             * |   (x, y)      (x + 1, y)        (x + 2, y)   ..    |
             * | (x, y + 1)  (x + 1, y + 1)    (x + 2, y + 1) ..    |
             * |     .             .                 .              |
             * |     .             .                 .              |
                --------------------------------------------------- */

           // System.out.println(x + "," + y);
            //if(y%2!=0){
               // System.out.println("Error");
           // }
            g2ds.setFont(new Font("default", Font.BOLD, 11));
            g2ds.setColor( new Color(238, 238, 238, 238) );

            g2ds.fillRect(0, 0, tempImg.getWidth(), tempImg.getHeight());

            int x_coord, y_coord;   // pixel coordinates of top left corner of image drawn
            int x_offset, counter, step = 0;
            int changePerStep = TILE_SIZE - (int)(TILE_SIZE/1.73);

            for(int j = 0; j < TILES_VISIBLE_X; j++) {          // tile index on sub-screen
                x_offset = changePerStep;
                if(step % 2 == 0) {
                    x_offset += changePerStep;
                }
                else {
                    x_offset -= changePerStep;
                    x_offset += 10;
                }
                step++;

                for(int i = 0; i < TILES_VISIBLE_Y; i++) {
                counter = 0;

                    int xx = x + i;                // tile index on whole map
                    int yy;
                   //Fixing the bug
                     if(y%2==1)
                        yy = y-1 + j;
                    else
                       yy = y+j;
                    if(xx < 0)                     // adjust if out of bounds
                        xx = 0;
                    else if (xx >= MAP_TILE_WIDTH)
                        xx = MAP_TILE_WIDTH - 1;
                    if(yy < 0)
                        yy = 0;
                    else if(yy >= MAP_TILE_HEIGHT)
                        yy = MAP_TILE_HEIGHT - 1;

                    x_coord = i * TILE_SIZE;
                    y_coord = (int)(j * TILE_SIZE / 2.4);

                    x_offset += changePerStep * ++counter;

                    DrawableTileState tileState = null;

                    if( player != null) {
                        if (grid[xx][yy].getDrawableStateByPlayer(player.getName()) != null) {
                            tileState = grid[xx][yy].getDrawableStateByPlayer(player.getName());
                        }
                    }

                    if(tileState == null) {
                       // tileState = grid[xx][yy].getDrawableStateByPlayer("real");
                        g2ds.drawImage(ghostImage, x_coord + x_offset, y_coord, null);
                    }
                    else {

                        // draw terrain
                        if( tileState.getTerrainType().equals("Mountains")) {
                            g2ds.drawImage(tileImage_1, x_coord + x_offset, y_coord, null);
                        }
                        else if (tileState.getTerrainType().equals("Crater")) {
                            g2ds.drawImage(tileImage_2, x_coord + x_offset, y_coord, null);
                        }
                        else if (tileState.getTerrainType().equals("Desert")) {
                            g2ds.drawImage(tileImage_3, x_coord + x_offset, y_coord, null);
                        }
                        else if (tileState.getTerrainType().equals("Flatland")) {
                            g2ds.drawImage(tileImage_4, x_coord + x_offset, y_coord, null);
                        }

                        // draw units
                        if (tileState.getExplorer() > 0) {
                                g2ds.drawImage(explorerImage, x_coord + x_offset+10, y_coord, null);
                        }
                        if (tileState.getColonist() > 0) {
                            g2ds.drawImage(colonistImage, x_coord + x_offset+10, y_coord, null);
                        }
                        if (tileState.getMeleeUnit() > 0) {
                            g2ds.drawImage(meleeImage, x_coord + x_offset+10, y_coord, null);
                        }
                        if (tileState.getRangeUnit() > 0) {
                            g2ds.drawImage(rangeImage, x_coord + x_offset+10, y_coord, null);
                        }


                        // shroud tile
                        if(grid[xx][yy].getShrouded(player.getName())) {
                            g2ds.drawImage(ghostImage, x_coord + x_offset, y_coord, null);
                        }
                    }

                    if( grid[xx][yy].isSelectedPath ) {
                        g2ds.drawImage(highlightImage, x_coord + x_offset, y_coord, null);
                    }

                    int s1_x = -15;
                    int s1_y =  31;

                    g2ds.setColor( new Color(0, 30, 230, 90)  ); // blue
                    if(drawOnTile) {
                        g2ds.fillOval(x_coord + x_offset + s1_x, y_coord + s1_y, 18, 18);
                        g2ds.setColor(new Color(255, 255, 100, 70));
                        g2ds.setColor(new Color(255, 128, 100, 150));
                        g2ds.fillOval(x_coord + x_offset + 5, y_coord + s1_y, 18, 18);

                        g2ds.setColor(new Color(255, 255, 255, 255));

                        String s = Integer.toString(xx);
                        g2ds.drawString(s, x_coord + x_offset + s1_x + 3, y_coord + s1_y + 13);
                        s = Integer.toString(yy);
                        g2ds.drawString(s, x_coord + x_offset + s1_x + 22, y_coord + s1_y + 13);
                    }
                }
            }

            return tempImg;
        }

        public void paintComponent( Graphics g )
        {
            super.paintComponent( g );
            g.drawImage( image, (int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth()  * (1-mapScale_x)), 0, this );
        }

    public void reDrawMap() {
        image = drawSubsectionOfMap(x_center, y_center);
        repaint();
    }

    private int[] getFocus() {
        int[] center = {x_center, y_center};
        return center;
    }

        public BufferedImage getCurrImage() {
            return image;
        }

        public void mousePressed(MouseEvent e) {}
        public void mouseReleased(MouseEvent e) {}
        public void mouseEntered(MouseEvent e) {}
        public void mouseExited(MouseEvent e) {}
        public void mouseClicked(MouseEvent e) {

            int center_pixel_x = MAP_IMAGE_WIDTH_IN_PIXELS  / 2;
            int center_pixel_y = MAP_IMAGE_HEIGHT_IN_PIXELS / 2;
            double x_offset = (e.getX() - center_pixel_x)/(double)TILE_SIZE;   // offset in number of tiles
            double y_offset = -1*(center_pixel_y - e.getY())/(double)TILE_SIZE;

           if(x_offset % 1 > 0.5) {
            x_offset += 1;
           }
           else if(x_offset % 1 < -0.5) {
            x_offset += -1;
           }
           if(y_offset % 1 > 0.5) {
            y_offset += 1;
           }
           else if(y_offset % 1 < -0.5) {
            y_offset += -1;
           }

            //System.out.println("offset (" + (int)x_offset + ", " + (int)y_offset + ")" );
            x_dest = x_center + (int)x_offset;
            y_dest = y_center + (int)(y_offset * 3.5);
           //System.out.println("center (" + (int)x_center + ", " + (int)y_center + ")" );
           // System.out.println("dest (" + (int)x_dest + ", " + (int)y_dest + ")" );
            zoomToDestination( x_dest, y_dest, 50 );
        }

        public void zoomToDestination(int x_dest, int y_dest, int delayInMs) {

            if(x_dest < 0)              // adjust if out of bounds
                x_dest = 0;
            else if (x_dest >= MAP_TILE_WIDTH - TILES_VISIBLE_Y)
                x_dest = MAP_TILE_WIDTH - TILES_VISIBLE_Y;
            if(y_dest < 0)
                y_dest = 0;
            else if(y_dest >= MAP_TILE_HEIGHT - TILES_VISIBLE_X)
                y_dest = MAP_TILE_HEIGHT - TILES_VISIBLE_X;

            final int x_destination  = x_dest;
            final int y_destination  = y_dest;
            //System.out.println("zoom to: " + x_dest + "," + y_dest);
            if( x_center != x_destination || y_center != y_destination) {
                new Thread( new Runnable()
                {


                    public void run()
                    {
                        int x_diff = (x_destination - x_center);
                        int y_diff = (y_destination - y_center);

                        int delta_x = 0, delta_y = 0;

                        if(x_diff != 0) {
                            delta_x = ((x_destination - x_center) > 0) ? 1 : -1;
                        }
                        if(y_diff != 0) {
                            delta_y = ((y_destination - y_center) > 0) ? 1 : -1;
                        }

                        while (x_diff != 0 || y_diff != 0) {    // while view isnt focused on destination tile
                            if(x_diff != 0) {                   // move focus 1 unit towards destination
                                x_center += delta_x;
                                x_diff -= delta_x;
                            }
                            if(y_diff != 0) {
                                y_center += delta_y;
                                y_diff -= delta_y;
                            }

                           // System.out.println("get frame focus at (" + (int)x_center + ", " + (int)y_center + ")" );
                            final BufferedImage mapSubsection = drawSubsectionOfMap(x_center, y_center);
                            mainViewSelection.setFocus(x_center, y_center);

                            SwingUtilities.invokeLater( new Runnable()   // queue frame i on EDT for display
                            {
                                public void run()
                                {
                                    image = mapSubsection;
                                    repaint();
                                }
                            });

                            try{
                                Thread.sleep(delayInMs);
                            }
                            catch(Exception e) {}
                        }   // end of while loop
                    }
                } ).start();
            }
        }



    //        public void setCurrentPlayer( Player player ) {
//            this.currentPlayer = player;
//            // focus on one of the current players units.
//            ArrayList<Unit> units = (ArrayList<Unit>) currentPlayer.getUnits();
//            if( !units.isEmpty() ) {
//                zoomToDestination( units.get(0).getLocation().getxCoordinate() - TILES_VISIBLE_X/2, units.get(0).getLocation().getyCoordinate() - TILES_VISIBLE_Y/2, 50);
//            }
//         }
}
