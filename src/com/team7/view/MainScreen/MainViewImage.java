package com.team7.view.MainScreen;

import com.team7.Main;
import com.team7.model.TileState;
import com.team7.model.Map;
import com.team7.model.Player;
import com.team7.model.Tile;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class MainViewImage extends JPanel implements MouseListener, MapStats {

        private int MAP_IMAGE_WIDTH_IN_PIXELS;
        private int MAP_IMAGE_HEIGHT_IN_PIXELS;
        private int TILES_VISIBLE_X;                    // # tiles visible on map
        private int TILES_VISIBLE_Y;
        private final static double mapScale_x = 0.95;  // what % of screen the map takes up
        private final static double mapScale_y = 0.60;

        public BufferedImage image;
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
        private BufferedImage ghostImage;
        private BufferedImage yellowHighlightImage;
        private BufferedImage workerImage;

         private BufferedImage farmImage;
         private BufferedImage mineImage;
         private BufferedImage powerplantImage;
         private BufferedImage fortImage;
         private BufferedImage obsTowerImage;
         private BufferedImage universityImage;

         private BufferedImage rallyImage;
         private static final long serialVersionUID = 1L;


    public int x_center, y_center;    // where the window is focused on
        private Timer timer = null;
        int x_dir;
        int y_dir;

        BufferedImage mapImage;
        Graphics2D g2ds;

        private MainViewMiniMap mainViewSelection;
        private Tile[][] grid;

        private int scrollSpeed = 300; // ms
        private boolean drawResources = false;
        private boolean drawUnits = true;
        private boolean drawAreaEffects = true;

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
               tileImage_1 = ImageIO.read(Main.class.getClass().getResourceAsStream("/resource/terrains/a.png"));
               tileImage_2 = ImageIO.read(Main.class.getClass().getResourceAsStream("/resource/terrains/b.png"));
               tileImage_3 = ImageIO.read(Main.class.getClass().getResourceAsStream("/resource/terrains/c.png"));
               tileImage_4 = ImageIO.read(Main.class.getClass().getResourceAsStream("/resource/terrains/d.png"));
               moneyBagImage = ImageIO.read(Main.class.getClass().getResourceAsStream("/resource/items/moneyBag.png"));
               moonRockImage = ImageIO.read(Main.class.getClass().getResourceAsStream("/resource/items/moonRock.png"));
               hieroglyphicBookImage = ImageIO.read(Main.class.getClass().getResourceAsStream("/resource/items/bookImage1.png"));
               elixerShowerImage = ImageIO.read(Main.class.getClass().getResourceAsStream("/resource/areaEffects/elixir.png"));
               stormImage = ImageIO.read(Main.class.getClass().getResourceAsStream("/resource/areaEffects/stormImageBig.png"));
               colonistImage = ImageIO.read(Main.class.getClass().getResourceAsStream("/resource/units/colonistImage.png"));
               explorerImage = ImageIO.read(Main.class.getClass().getResourceAsStream("/resource/units/explorerImage.png"));
               oneShotImage = ImageIO.read(Main.class.getClass().getResourceAsStream("/resource/obstacles/oneShot.png"));
               obstacleImage = ImageIO.read(Main.class.getClass().getResourceAsStream("/resource/obstacles/stopIcon.png"));
               meleeImage = ImageIO.read(Main.class.getClass().getResourceAsStream("/resource/units/meleeImage.png"));
               rangeImage = ImageIO.read(Main.class.getClass().getResourceAsStream("/resource/units/rangeImage.png"));
               armyImage = ImageIO.read(Main.class.getClass().getResourceAsStream("/resource/units/armyImagepng.png"));
               ventImage = ImageIO.read(Main.class.getClass().getResourceAsStream("/resource/areaEffects/vent.png"));
               skullImage = ImageIO.read(Main.class.getClass().getResourceAsStream("/resource/decals/skullImage.png"));
               baseImage = ImageIO.read(Main.class.getClass().getResourceAsStream("/resource/structures/baseImage.png"));
               ghostImage = ImageIO.read(Main.class.getClass().getResourceAsStream("/resource/terrains/dark_image.png"));
               highlightImage = ImageIO.read(Main.class.getClass().getResourceAsStream("/resource/terrains/highlight.png"));
               invisible = ImageIO.read(Main.class.getClass().getResourceAsStream("/resource/terrains/invisible.png"));
               yellowHighlightImage = ImageIO.read(Main.class.getClass().getResourceAsStream("/resource/terrains/yellowHighlight.png"));

                farmImage  = ImageIO.read(Main.class.getClass().getResourceAsStream("/resource/structures/farm.png"));
                mineImage = ImageIO.read(Main.class.getClass().getResourceAsStream("/resource/structures/mine.png"));
                powerplantImage = ImageIO.read(Main.class.getClass().getResourceAsStream("/resource/structures/powerplant.png"));
                fortImage = ImageIO.read(Main.class.getClass().getResourceAsStream("/resource/structures/fort.png"));
                obsTowerImage = ImageIO.read(Main.class.getClass().getResourceAsStream("/resource/structures/ot.png"));
                universityImage = ImageIO.read(Main.class.getClass().getResourceAsStream("/resource/structures/university.png"));

                rallyImage = ImageIO.read(Main.class.getClass().getResourceAsStream("/resource/structures/flag.png"));
               workerImage = ImageIO.read(Main.class.getClass().getResourceAsStream("/resource/units/workerImage.png"));
            }
            catch (IOException e) {}

            mainViewSelection.setMainViewImage( this );
            mapImage = new BufferedImage( MAP_IMAGE_WIDTH_IN_PIXELS, MAP_IMAGE_HEIGHT_IN_PIXELS, BufferedImage.TYPE_INT_ARGB);
            g2ds = mapImage.createGraphics();
            g2ds.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2ds.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);

            addMouseListener( new MouseAdapter()
            {
                public void mousePressed( MouseEvent event )
                {
                    if(event.getButton() == MouseEvent.BUTTON1)
                    {
                        LMBisPressed( event.getPoint() );
                    }
                    else if(event.getButton() == MouseEvent.BUTTON2)
                    {
                        RMBisPressed( event.getPoint() );
                    }
                    else if(event.getButton() == MouseEvent.BUTTON3)
                    {
                        RMBisPressed( event.getPoint() );
                    }
                }
            } );
            addMouseMotionListener( new MouseMotionAdapter()
            {
                public void mouseDragged(MouseEvent event)
                {
                    updateSelection(event.getPoint());           }
            } );
            addMouseListener( new MouseAdapter()
            {
                public void mouseReleased(MouseEvent event)
                {
                    mouseIsReleased( event.getPoint() );
                }
            } );

            timer = new Timer(scrollSpeed, new ActionListener()
            {
                public void actionPerformed( ActionEvent e)
                {
                    timer.stop();
                    zoomToDestination( x_center + x_dir, y_center + y_dir * 2, 30 );
                    timer.restart();
                }
            } );
        }

    private void updateSelection( Point p )
    {
        setScrollDir( p );
    }

    private void LMBisPressed( Point p )
    {
        setScrollDir( p );
        timer.start();
    }

    private void RMBisPressed( Point p )
    {
        setScrollDir( p );
        timer.start();
    }

    private void setScrollDir(Point p) {
        x_dir = ((int)p.getX() - 667)/60;
        y_dir = ((int)p.getY() - 240)/40;
        if(x_dir > 1 )
            x_dir = 1;
        else if (x_dir < -1)
            x_dir = -1;
        else
            x_dir = 0;

        if(y_dir > 1 )
            y_dir = 1;
        else if (y_dir < -1)
            y_dir = -1;
        else
            y_dir = 0;
    }

    private void mouseIsReleased( Point p )
    {
        timer.stop();                       // stop zooming
    }

        public void setMap(Map map) {
            this.grid = map.getGrid();
            mainViewSelection.setMiniMapImage( getFullMapImage(false    ), TILES_VISIBLE_X, TILES_VISIBLE_Y );
            if(player == null)
                return;
            zoomToDestination(player.getFirstUnit().getLocation().getxCoordinate() - 11/2, player.getFirstUnit().getLocation().getyCoordinate() - 16/2, 60);
        }

         public void setCurrPlayer(Player p) {
              player = p;
            }

         public BufferedImage getFullMapImage(boolean drawAll) {

             BufferedImage mapImage;
             mapImage = new BufferedImage(TILE_SIZE*(MAP_TILE_WIDTH+1) + (int)(MAP_TILE_WIDTH * (TILE_SIZE - TILE_SIZE / 1.73) + TILE_SIZE)  , (int)(TILE_SIZE*MAP_TILE_HEIGHT/1.5), BufferedImage.TYPE_INT_ARGB);
             Graphics2D g2 = mapImage.createGraphics();
             g2.setColor(new Color(0xFF000000));
             g2.fillRect(0, 0, mapImage.getWidth(), mapImage.getHeight());
             g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                     RenderingHints.VALUE_INTERPOLATION_BILINEAR);
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

                 for (int i = 0; i < MAP_TILE_HEIGHT; i++) {
                     counter = 0;

                     int xx = i;                   //  index in Map's grid[][]
                     int yy = j;

                     if (xx < 0)                     // adjust if out of bounds
                         xx = 0;
                     else if (xx >= MAP_TILE_WIDTH)
                         xx = MAP_TILE_WIDTH - 1;
                     if (yy < 0)
                         yy = 0;
                     else if (yy >= MAP_TILE_HEIGHT)
                         yy = MAP_TILE_HEIGHT - 1;

                     x_coord = i * TILE_SIZE;
                     y_coord = (int) (j * TILE_SIZE / 2.4);

                     x_offset += changePerStep * ++counter;

                     TileState tileState = null;

                     // get tile state based on current player
                     if (player != null) {
                         if (grid[xx][yy].getDrawableStateByPlayer(player.getName()) != null) {
                             tileState = grid[xx][yy].getDrawableStateByPlayer(player.getName());
                         }
                     }
                     // if tile not visible
                     if (tileState == null) {
                         g2.drawImage(ghostImage, x_coord + x_offset, y_coord, null);
                     }
                     else {
                         // draw terrain
                         if (tileState.getTerrainType().equals("Mountains")) {
                             g2.drawImage(tileImage_1, x_coord + x_offset, y_coord, null);
                         } else if (tileState.getTerrainType().equals("Crater")) {
                            g2.drawImage(tileImage_2, x_coord + x_offset, y_coord, null);
                         } else if (tileState.getTerrainType().equals("Desert")) {
                             g2.drawImage(tileImage_3, x_coord + x_offset, y_coord, null);
                         } else if (tileState.getTerrainType().equals("Flatland")) {
                             g2.drawImage(tileImage_4, x_coord + x_offset, y_coord, null);
                         }
                         if(drawAll) {
                             // draw units
                             if (drawUnits) {
                                 // explorer
                                 if (tileState.getExplorer() == 1)
                                     g2.drawImage(explorerImage, x_coord + x_offset + 10, y_coord, null);
                                 // colonist
                                 if (tileState.getColonist() == 1 )
                                     g2.drawImage(colonistImage, x_coord + x_offset + 10, y_coord, null);
                                 // melee
                                 if (tileState.getMeleeUnit() == 1)
                                     g2.drawImage(meleeImage, x_coord + x_offset + 10, y_coord, null);
                                 // ranged
                                 if (tileState.getRangeUnit() == 1)
                                     g2.drawImage(rangeImage, x_coord + x_offset + 10, y_coord, null);
                                 if (tileState.getWorkerUnit() > 0){
                                     g2.drawImage(workerImage, x_coord + x_offset + 10, y_coord, null);
                                 }
                             }
                             //draw resource counts
                             if (drawResources) {
                                 // ore
                                 if (tileState.getOreQuantity() > 0) {
                                     g2.setColor(new Color(0xFFDDAAAA));
                                     g2.drawString(Integer.toString(tileState.getOreQuantity()), x_coord + x_offset + 16, y_coord + 30);
                                 }
                                 // energy
                                 if (tileState.getEnergyQuantity() > 0) {
                                     g2.setColor(new Color(0xaf75fff8));
                                     g2.drawString(Integer.toString(tileState.getEnergyQuantity()), x_coord + x_offset + 30, y_coord + 30);
                                 }
                                 //food
                                 if (tileState.getFoodQuantity() > 0) {
                                     g2.setColor(new Color(0xAFAFFC00));
                                     g2.drawString(Integer.toString(tileState.getFoodQuantity()), x_coord + x_offset + 44, y_coord + 30);
                                 }
                             }
                             if(drawAreaEffects && tileState.getAreaEffectType()!=null) {
                                 if (tileState.getAreaEffectType().equalsIgnoreCase("Heal")) {
                                     g2.drawImage(elixerShowerImage, x_coord + x_offset + 10, y_coord, null);
                                 }
                                 if (tileState.getAreaEffectType().equalsIgnoreCase("Damage")) {
                                     g2.drawImage(stormImage, x_coord + x_offset + 10, y_coord, null);
                                 }
                                 if (tileState.getAreaEffectType().equalsIgnoreCase("InstantDeath")) {
                                     g2.drawImage(ventImage, x_coord + x_offset + 10, y_coord, null);
                                 }
                             }
                             if(tileState.getDecal()!=null){
                                 g2.drawImage(skullImage, x_coord + x_offset + 10, y_coord, null);
                                 System.out.println("Settinfg decal");
                             }
                             if(tileState.getArmy() > 0)
                             {
                                 g2.drawImage(armyImage, x_coord + x_offset + 10, y_coord, null);
                             }





                             if (tileState.getCapital() > 0) {
                                 g2.drawImage(baseImage, x_coord + x_offset + 10, y_coord, null);
                             }
                             if (tileState.getFort() > 0) {
                                 g2.drawImage(fortImage, x_coord + x_offset + 10, y_coord, null);
                             }
                             if (tileState.getMine() > 0) {
                                 g2.drawImage(mineImage, x_coord + x_offset + 10, y_coord, null);
                             }
                             if (tileState.getUniversity() > 0) {
                                 g2.drawImage(universityImage, x_coord + x_offset + 10, y_coord, null);
                             }
                             if (tileState.getObs_tower() > 0) {
                                 g2.drawImage(obsTowerImage, x_coord + x_offset + 10, y_coord, null);
                             }
                             if (tileState.getFarm() > 0) {
                                 g2.drawImage(farmImage, x_coord + x_offset + 10, y_coord, null);
                             }


                             if(tileState.getWorkerUnit() > 0) {
                                 g2ds.setColor(new Color(250, 128, 100, 85));
                                 g2ds.fillOval(x_coord + x_offset + 25, y_coord + 32, 20, 20);
                                 g2ds.setColor(new Color(255, 255, 255, 255));
                                 g2ds.drawString( Integer.toString( tileState.getWorkerUnit() ),  x_coord + x_offset + 29, y_coord + 46 );
                             }
                         }




                         // shroud tile
                         if(player != null)
                           if (grid[xx][yy].getShrouded(player.getName())) {
                              g2.drawImage(ghostImage, x_coord + x_offset, y_coord, null);
                          }

                         // highlight tile path
                         if (grid[xx][yy].isSelectedPath) {
                              g2.drawImage(highlightImage, x_coord + x_offset, y_coord, null);
                         }
                     }
                 }
             }

             return mapImage;
         }

        private BufferedImage drawSubsectionOfMap(int x, int y) {

            /*
             *  --------------------------------------------------
             * |   (x, y)      (x + 1, y)        (x + 2, y)    .. |
             * | (x, y + 1)  (x + 1, y + 1)    (x + 2, y + 1)  .. |
             * |     .             .                 .            |
             * |     .             .                 .            |
             *  --------------------------------------------------
             */

            g2ds.setFont(new Font("Arial", 0, 9));
            g2ds.setColor( new Color(0xffF5F5DC) );
            g2ds.fillRect(0, 0, mapImage.getWidth(), mapImage.getHeight());

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

                    TileState tileState = null;

                    // get tile state based on current player
                    if( player != null) {
                        if (grid[xx][yy].getDrawableStateByPlayer(player.getName()) != null) {
                            tileState = grid[xx][yy].getDrawableStateByPlayer(player.getName());
                        }
                    }
                    // if tile not visible
                    if(tileState == null)
                        g2ds.drawImage(ghostImage, x_coord + x_offset, y_coord, null);

                    else {
                        // draw terrain
                        if( tileState.getTerrainType().equals("Mountains"))
                            g2ds.drawImage(tileImage_1, x_coord + x_offset, y_coord, null);
                        else if (tileState.getTerrainType().equals("Crater"))
                            g2ds.drawImage(tileImage_2, x_coord + x_offset, y_coord, null);
                        else if (tileState.getTerrainType().equals("Desert"))
                            g2ds.drawImage(tileImage_3, x_coord + x_offset, y_coord, null);
                        else if (tileState.getTerrainType().equals("Flatland"))
                            g2ds.drawImage(tileImage_4, x_coord + x_offset, y_coord, null);

                        // draw units
                        if(drawUnits) {
                            g2ds.setColor(Color.white);
                            // explorer
                            if (tileState.getExplorer() == 1) {
                                g2ds.drawImage(explorerImage, x_coord + x_offset + 10, y_coord, null);
//                                g2ds.drawString( player.getName() , x_coord + x_offset + 20, y_coord + 30);
                            }
                            // colonist
                            if (tileState.getColonist() == 1) {
                                g2ds.drawImage(colonistImage, x_coord + x_offset + 10, y_coord, null);
//                                g2ds.drawString( player.getName() , x_coord + x_offset + 20, y_coord + 30);
                            }
                            // melee
                            if (tileState.getMeleeUnit() == 1) {
                                g2ds.drawImage(meleeImage, x_coord + x_offset + 10, y_coord, null);
//                                g2ds.drawString( player.getName() , x_coord + x_offset + 20, y_coord + 30);
                            }
                            // ranged
                            if (tileState.getRangeUnit() ==1){
                                g2ds.drawImage(rangeImage, x_coord + x_offset + 10, y_coord, null);
//                                g2ds.drawString( player.getName() , x_coord + x_offset + 20, y_coord + 30);
                            }

                            if (tileState.getExplorer() > 1) {
                                g2ds.drawImage(explorerImage, x_coord + x_offset + 10, y_coord, null);
                                g2ds.drawString( Integer.toString(tileState.getExplorer()) , x_coord + x_offset + 45, y_coord + 40);
                            }
                            // colonist
                            if (tileState.getColonist() > 1) {
                                g2ds.drawImage(colonistImage, x_coord + x_offset + 10, y_coord, null);
                                g2ds.drawString( Integer.toString(tileState.getExplorer())  , x_coord + x_offset + 45, y_coord + 40);
                            }
                            // melee
                            if (tileState.getMeleeUnit() > 1) {
                                g2ds.drawImage(meleeImage, x_coord + x_offset + 10, y_coord, null);
                                g2ds.drawString( Integer.toString(tileState.getExplorer())  , x_coord + x_offset + 45, y_coord + 40);
                            }
                            // ranged
                            if (tileState.getRangeUnit() > 1){
                                g2ds.drawImage(rangeImage, x_coord + x_offset + 10, y_coord, null);
                                g2ds.drawString( Integer.toString(tileState.getExplorer())  , x_coord + x_offset + 45, y_coord + 40);
                            }

//                            if (tileState.getWorkerUnit() > 0){
//                                g2ds.drawImage(workerImage, x_coord + x_offset + 10, y_coord, null);
//                                g2ds.drawString( player.getName() , x_coord + x_offset + 20, y_coord + 30);
//                            }
                        }
                        //draw resource counts
                        if(drawResources) {


                            if(player.getName().equals("One")){
                                if(tileState.isProspectedByPlayer1()){
                                    //draw ore
                                    if (tileState.getOreQuantity() > 0) {
                                        g2ds.setColor(new Color(0xFFDDAAAA));
                                        g2ds.drawString(Integer.toString(tileState.getOreQuantity()), x_coord + x_offset + 16, y_coord + 30);
                                    }
                                    // energy
                                    if (tileState.getEnergyQuantity() > 0) {
                                        g2ds.setColor(new Color(0xaf75fff8));
                                        g2ds.drawString(Integer.toString(tileState.getEnergyQuantity()), x_coord + x_offset + 30, y_coord + 30);
                                    }
                                    //food
                                    if (tileState.getFoodQuantity() > 0) {
                                        g2ds.setColor(new Color(0xAFAFFC00));
                                        g2ds.drawString(Integer.toString(tileState.getFoodQuantity()), x_coord + x_offset + 44, y_coord + 30);
                                    }
                                }
                            }

                            if(player.getName().equals("Two")){
                                if(tileState.isProspectedByPlayer2()){
                                    //draw ore
                                    if (tileState.getOreQuantity() > 0) {
                                        g2ds.setColor(new Color(0xFFDDAAAA));
                                        g2ds.drawString(Integer.toString(tileState.getOreQuantity()), x_coord + x_offset + 16, y_coord + 30);
                                    }
                                    // energy
                                    if (tileState.getEnergyQuantity() > 0) {
                                        g2ds.setColor(new Color(0xaf75fff8));
                                        g2ds.drawString(Integer.toString(tileState.getEnergyQuantity()), x_coord + x_offset + 30, y_coord + 30);
                                    }
                                    //food
                                    if (tileState.getFoodQuantity() > 0) {
                                        g2ds.setColor(new Color(0xAFAFFC00));
                                        g2ds.drawString(Integer.toString(tileState.getFoodQuantity()), x_coord + x_offset + 44, y_coord + 30);
                                    }
                                }
                            }

                        }
                        if(drawAreaEffects && tileState.getAreaEffectType()!=null) {
                            if (tileState.getAreaEffectType().equalsIgnoreCase("Heal")) {
                                g2ds.drawImage(elixerShowerImage, x_coord + x_offset + 10, y_coord, null);
                            }
                            if (tileState.getAreaEffectType().equalsIgnoreCase("Damage")) {
                                g2ds.drawImage(stormImage, x_coord + x_offset + 10, y_coord, null);
                            }
                            if (tileState.getAreaEffectType().equalsIgnoreCase("InstantDeath")) {
                                g2ds.drawImage(ventImage, x_coord + x_offset + 10, y_coord, null);
                            }
                        }
                        if(tileState.getDecal()!=null){
                            g2ds.drawImage(skullImage, x_coord + x_offset + 10, y_coord, null);
                        }


                        if (tileState.getCapital() > 0) {
                            g2ds.drawImage(baseImage, x_coord + x_offset + 10, y_coord, null);
                        }
                        if (tileState.getFort() > 0) {
                            g2ds.drawImage(fortImage, x_coord + x_offset + 10, y_coord, null);
                        }
                        if (tileState.getMine() > 0) {
                            g2ds.drawImage(mineImage, x_coord + x_offset + 10, y_coord, null);
                        }
                        if (tileState.getUniversity() > 0) {
                            g2ds.drawImage(universityImage, x_coord + x_offset + 10, y_coord, null);
                        }
                        if (tileState.getObs_tower() > 0) {
                            g2ds.drawImage(obsTowerImage, x_coord + x_offset + 10, y_coord, null);
                        }
                        if (tileState.getFarm() > 0) {
                            g2ds.drawImage(farmImage, x_coord + x_offset + 10, y_coord, null);
                        }

                        if(tileState.getArmy() > 0)
                        {
                            System.out.println("Creating army");
                            g2ds.drawImage(armyImage, x_coord + x_offset + 10, y_coord, null);
                        }


//                        if(tileState.getMeleeUnit()  + tileState.getRangeUnit() >1)
//                        {
//                         //   System.out.println("Creating army");
//                            g2ds.drawImage(armyImage, x_coord + x_offset + 10, y_coord, null);
//                        }

                        if (tileState.getRallyPoint() > 0) {
                            g2ds.drawImage(rallyImage, x_coord + x_offset + 10, y_coord, null);
                        }


                        //drawing workable tiles overlay
                        if(player.getName().equals("One")){
                            if(tileState.isWorkableByPlayer1()){
                                //draw tile overlay
                                g2ds.drawImage(yellowHighlightImage, x_coord + x_offset, y_coord, null);
                            }
                        }

                        if(player.getName().equals("Two")){
                            if(tileState.isWorkableByPlayer2()){
                                //draw tile overlay
                                g2ds.drawImage(yellowHighlightImage, x_coord + x_offset, y_coord, null);
                            }
                        }

                        if(tileState.getWorkerUnit() > 0) {
                            g2ds.setColor(new Color(250, 128, 100, 85));
                            g2ds.fillOval(x_coord + x_offset + 25, y_coord + 32, 20, 20);
                            g2ds.setColor(new Color(255, 255, 255, 255));
                            g2ds.drawString( Integer.toString( tileState.getWorkerUnit() ),  x_coord + x_offset + 29, y_coord + 46 );
                        }

                        // shroud tile
                        if(grid[xx][yy].getShrouded(player.getName()))
                            g2ds.drawImage(ghostImage, x_coord + x_offset, y_coord, null);

                    }

                    if( grid[xx][yy].isSelectedPath )
                        g2ds.drawImage(highlightImage, x_coord + x_offset, y_coord, null);




                    //g2ds.setColor( Color.white );
                    //draw x y coordinates
                    //g2ds.drawString( Integer.toString( xx ), x_coord + x_offset + 15, y_coord + 32);
                    //g2ds.drawString( Integer.toString( yy ),  x_coord + x_offset + 35, y_coord + 32 );


//                      //draw bottom left circle, to be used later
//                      g2ds.setColor(new Color(255, 128, 100, 150));
//                      g2ds.fillOval(x_coord + x_offset + 15, y_coord + 32, 18, 18);
//
//                      //draw bottom right circle, to be used later
//                      g2ds.setColor(new Color(255, 128, 100, 150));
//                      g2ds.fillOval(x_coord + x_offset + 35, y_coord + 32, 18, 18);




                }
            }

            return mapImage;
        }

    public BufferedImage drawSubsectionOfMap() {

            /*
             *  --------------------------------------------------
             * |   (x, y)      (x + 1, y)        (x + 2, y)    .. |
             * | (x, y + 1)  (x + 1, y + 1)    (x + 2, y + 1)  .. |
             * |     .             .                 .            |
             * |     .             .                 .            |
             *  --------------------------------------------------
             */

            int x = x_center;
            int y = y_center;

        g2ds.setFont(new Font("Arial", 0, 9));
        g2ds.setColor( new Color(0xffF5F5DC) );
        g2ds.fillRect(0, 0, mapImage.getWidth(), mapImage.getHeight());

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

                TileState tileState = null;

                // get tile state based on current player
                if( player != null) {
                    if (grid[xx][yy].getDrawableStateByPlayer(player.getName()) != null) {
                        tileState = grid[xx][yy].getDrawableStateByPlayer(player.getName());
                    }
                }
                // if tile not visible
                if(tileState == null)
                    g2ds.drawImage(ghostImage, x_coord + x_offset, y_coord, null);

                else {
                    // draw terrain
                    if( tileState.getTerrainType().equals("Mountains"))
                        g2ds.drawImage(tileImage_1, x_coord + x_offset, y_coord, null);
                    else if (tileState.getTerrainType().equals("Crater"))
                        g2ds.drawImage(tileImage_2, x_coord + x_offset, y_coord, null);
                    else if (tileState.getTerrainType().equals("Desert"))
                        g2ds.drawImage(tileImage_3, x_coord + x_offset, y_coord, null);
                    else if (tileState.getTerrainType().equals("Flatland"))
                        g2ds.drawImage(tileImage_4, x_coord + x_offset, y_coord, null);

                    // draw units
                    if(drawUnits) {
                        // explorer
                        if (tileState.getExplorer() == 1)
                            g2ds.drawImage(explorerImage, x_coord + x_offset + 10, y_coord, null);
                        // colonist
                        if (tileState.getColonist() == 1)
                            g2ds.drawImage(colonistImage, x_coord + x_offset + 10, y_coord, null);
                        // melee
                        if (tileState.getMeleeUnit() == 1)
                            g2ds.drawImage(meleeImage, x_coord + x_offset + 10, y_coord, null);
                        // ranged
                        if (tileState.getRangeUnit() ==1)
                            g2ds.drawImage(rangeImage, x_coord + x_offset + 10, y_coord, null);

                        if (tileState.getWorkerUnit() > 0){
                            g2ds.drawImage(workerImage, x_coord + x_offset + 10, y_coord, null);
                        }
                    }

                    if(tileState.getArmy() > 0)
                    {
                        g2ds.drawImage(armyImage, x_coord + x_offset + 10, y_coord, null);
                    }

                    //draw resource counts
                    if(drawResources) {
                        // ore
                        if (tileState.getOreQuantity() > 0) {
                            g2ds.setColor(new Color(0xFFDDAAAA));
                            g2ds.drawString(Integer.toString(tileState.getOreQuantity()), x_coord + x_offset + 16, y_coord + 30);
                        }
                        // energy
                        if (tileState.getEnergyQuantity() > 0) {
                            g2ds.setColor(new Color(0xaf75fff8));
                            g2ds.drawString(Integer.toString(tileState.getEnergyQuantity()), x_coord + x_offset + 30, y_coord + 30);
                        }
                        //food
                        if (tileState.getFoodQuantity() > 0) {
                            g2ds.setColor(new Color(0xAFAFFC00));
                            g2ds.drawString(Integer.toString(tileState.getFoodQuantity()), x_coord + x_offset + 44, y_coord + 30);
                        }
                    }
                    if(drawAreaEffects && tileState.getAreaEffectType()!=null) {
                        if (tileState.getAreaEffectType().equalsIgnoreCase("Heal")) {
                            g2ds.drawImage(elixerShowerImage, x_coord + x_offset + 10, y_coord, null);
                        }
                        if (tileState.getAreaEffectType().equalsIgnoreCase("Damage")) {
                            g2ds.drawImage(stormImage, x_coord + x_offset + 10, y_coord, null);
                        }
                        if (tileState.getAreaEffectType().equalsIgnoreCase("InstantDeath")) {
                            g2ds.drawImage(ventImage, x_coord + x_offset + 10, y_coord, null);
                        }
                    }
                    if(tileState.getDecal()!=null){
                        g2ds.drawImage(skullImage, x_coord + x_offset + 10, y_coord, null);
                    }


                    if (tileState.getFarm() > 0) {

                    }
                    if (tileState.getFort() > 0) {

                    }
                    if (tileState.getMine() > 0) {

                    }
                    if (tileState.getObs_tower() > 0) {

                    }
                    if (tileState.getPowerplant() > 0) {

                    }
                    if (tileState.getUniversity() > 0) {

                    }
                    if (tileState.getWorkerUnit() > 0) {

                    }

                    // shroud tile
                    if(grid[xx][yy].getShrouded(player.getName()))
                        g2ds.drawImage(ghostImage, x_coord + x_offset, y_coord, null);

                }

                if( grid[xx][yy].isSelectedPath )
                    g2ds.drawImage(highlightImage, x_coord + x_offset, y_coord, null);

                    /* draw bottom left circle, to be used later
                      g2ds.setColor(new Color(255, 128, 100, 150));
                      g2ds.fillOval(x_coord + x_offset + 15, y_coord + 32, 18, 18);

                      draw bottom right circle, to be used later
                      g2ds.setColor(new Color(255, 128, 100, 150));
                      g2ds.fillOval(x_coord + x_offset + 35, y_coord + 32, 18, 18);
                    */

            }
        }

        return mapImage;
    }

        public void paintComponent( Graphics g )
        {
            super.paintComponent( g );
            g.setColor(new Color(0xffF5F5DC));
            g.fillRect(0, 0, this.getWidth(), this.getHeight());
            g.drawImage( image, (int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth()  * (1-mapScale_x)), 0, this );
        }

        public void reDrawMap() {
            image = drawSubsectionOfMap(x_center, y_center);
            repaint();
        }

        public void mousePressed(MouseEvent e) {
            timer.start();
        }
        public void mouseReleased(MouseEvent e) {
            timer.stop();
        }
        public void mouseEntered(MouseEvent e) {}
        public void mouseExited(MouseEvent e) {}
        public void mouseClicked(MouseEvent e) {
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
                            try{ Thread.sleep(delayInMs); }
                            catch(Exception e) {}
                        }
                    }
                } ).start();
            }
        }

    public void setScrollSpeed(int scrollSpeed) {
        this.scrollSpeed = scrollSpeed * 30;
        timer = new Timer(this.scrollSpeed, new ActionListener()
        {
            public void actionPerformed( ActionEvent e)
            {
                timer.stop();
                zoomToDestination( x_center + x_dir, y_center + y_dir * 2, 15 );
                timer.restart();
            }
        } );
    }

    public void setImage(BufferedImage img) {
            this.image = img;
            repaint();
    }

    public void drawResources(boolean draw) {
            this.drawResources = draw;
    }
    public void drawUnits(boolean draw) {
        this.drawUnits = draw;
    }
    public MainViewMiniMap getMiniMap() {
            return  mainViewSelection;
    }

}
