package com.team7.view;

import com.team7.model.Map;
import com.team7.model.Player;
import com.team7.view.HomeScreen.HomeScreen;
import com.team7.view.MainScreen.CommandSelect;
import com.team7.view.MainScreen.MainScreen;
import com.team7.view.MainScreen.MainViewImage;
import com.team7.view.MainScreen.MainViewInfo;
import com.team7.view.MapScreen.MapScreen;
import com.team7.view.OptionsScreen.OptionsScreen;
import com.team7.view.StructureScreen.StructureScreen;
import com.team7.view.TechnologyScreen.TechnologyScreen;
import com.team7.view.UnitScreen.UnitScreen;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class View
{
    private static final int WIDTH  = (int)java.awt.Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    private static final int HEIGHT = (int)java.awt.Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    private Screen frame = null;

    public View()
    {
        // SwingUtilities.invokeLater causes the Runnable to be executed asynchronously on the Event Dispatch Thread:
        // It queues up a task (GUI update) on the EDT and instantly returns.
        // Used to prevent long tasks from freezing up the GUI
        SwingUtilities.invokeLater( new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        } );
    }

    // Create the GUI and show it.
    private void createAndShowGUI()
    {
        frame = new Screen( WIDTH, HEIGHT);                       // setup new frame
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );    // exit when the user closes the frame
        frame.setVisible( true );                                  // make the frame visible
    }

    public CommandSelect getCommandSelect() {
        return frame.getMainScreen().getCommandSelect();
    }
    public Screen getScreen() {
        return frame;
    }
    public MainScreen getMainScreen() {
        return frame.getMainScreen();
    }
    public HomeScreen getHomeScreen() { return frame.getHomeScreen(); }
    public OptionsScreen getOptionScreen() { return frame.getOptionsScreen(); }
    public StructureScreen getStructureScreen() { return frame.getStructureScreen(); }
    public UnitScreen getUnitScreen() { return frame.getUnitScreen(); }
    public TechnologyScreen getTechnologyScreen() {
        return frame.getTechnologyScreen();
    }

    public void setMap( Map map ) {
        frame.getMainScreen().getMainViewImage().setMap( map );
        frame.getMainScreen().drawMap();
        frame.getMainScreen().setFocusable( true );
        frame.getMainScreen().giveCommandSelectFocus();
    }

    public void setCurrScreen(String selected_screen) {
        frame.setCurrScreen( selected_screen );
    }
    public void setCurrScreen(String structure_overview, Player currentPlayer) {frame.setCurrScreen(structure_overview, currentPlayer);}

    public void redrawView() {
        frame.redrawView();
    }

    public MainViewImage getMainViewImage() {
        return frame.getMainViewImage();
    }
    public MainViewInfo getMainViewInfo() {
        return frame.getMainViewInfo();
    }
    public MapScreen getMapScreen() {
        return frame.getMapScreen();
    }




// ==================== INNER CLASS ==========================

    class Screen extends JFrame
    {
        private HomeScreen homeScreen = null;
        private MainScreen mainScreen = null;
        private OptionsScreen optionsScreen = null;
        private UnitScreen unitScreen = null;
        private StructureScreen structureScreen = null;
        private MapScreen mapScreen = null;
        private TechnologyScreen technologyScreen = null;

        public Screen( int width, int height)
        {
            this.setTitle( " " );
            this.setSize( width, height );
            addMenu();

            homeScreen = new HomeScreen();
            mainScreen = new MainScreen();
            optionsScreen = new OptionsScreen();
            unitScreen = new UnitScreen();
            structureScreen = new StructureScreen();
            mapScreen = new MapScreen();
            technologyScreen = new TechnologyScreen();

            setCurrScreen("HOME");
            this.setVisible( true );
        }

        public MainViewImage getMainViewImage() {
            return mainScreen.getMainViewImage();
        }
        public MainViewInfo getMainViewInfo() {
            return mainScreen.getMainViewInfo();
        }
        public HomeScreen getHomeScreen() {
            return homeScreen;
        }
        public MainScreen getMainScreen() {
            return mainScreen;
        }
        public OptionsScreen getOptionsScreen() { return  optionsScreen; }
        public UnitScreen getUnitScreen() {
                    return unitScreen;
            }
        public StructureScreen getStructureScreen() {
                    return structureScreen;
            }
        public MapScreen getMapScreen() {
            return mapScreen;
        }

        public TechnologyScreen getTechnologyScreen() {
            return technologyScreen;
        }

        public void setCurrScreen(String selected_screen) {

            getContentPane().removeAll();   // clear screen

            switch (selected_screen) {
                case "HOME":
                    displayHomeScreen();
                    break;
                case "MAIN":
                    displayMainScreen();
                    mainScreen.getCommandSelect().setFocusable(true);
                    mainScreen.getCommandSelect().requestFocus();
                    break;
                case "UNIT_OVERVIEW":
                    displayUnitOverviewScreen();
                    break;
                case "STRUCTURE_OVERVIEW":
                    displayStructureOverviewScreen();
                    break;
                case "OPTIONS":
                    displayOptionScreen();
                    break;
                case "MAP_SCREEN":
                    displayMapScreen();
                    break;
                case "TECHNOLOGY":
                    displayTechnologyScreen();
                    break;
            }
            revalidate();
            repaint();
        }

        public void setCurrScreen(String selected_string, Player currentPlayer) {
            switch(selected_string) {
                case "STRUCTURE_OVERVIEW":
                    structureScreen.setStructureModel(currentPlayer.getStructures());
                    setCurrScreen(selected_string);
            }
        }

        private void displayHomeScreen() {
            this.getContentPane().add( homeScreen );
        }
        private void displayMainScreen() {
            this.getContentPane().add( mainScreen );
        }
        private void displayOptionScreen() {
            this.getContentPane().add( optionsScreen );
        }
        private void displayUnitOverviewScreen() {
            this.getContentPane().add( unitScreen );
        }
        private void displayStructureOverviewScreen() {
                this.getContentPane().add( structureScreen );
        }
        private void displayTechnologyScreen(){
            this.getContentPane().add(technologyScreen);
        }

        private void displayMapScreen() {
            this.getContentPane().add( mapScreen );
        }

        private void addMenu()
        {                       	   // addMenu() method used to setup a frame's menu bar
            JMenu fileMenu = new JMenu( "File" );
            fileMenu.setFont(new Font("plain", Font.BOLD, 12));

            JMenuItem exitItem = new JMenuItem( "Exit" );
            exitItem.setFont(new Font("Arial", Font.BOLD, 15));
            exitItem.setBackground(new Color(0xffF5F5DC));
            exitItem.setOpaque(true);

            exitItem.addActionListener( new ActionListener() 	// define what happens when this menu item is selected
            {
                public void actionPerformed( ActionEvent event )
                {
                    System.exit( 0 );
                }
            } );
            JMenuItem saveItem = new JMenuItem( "Save full size map image" );     // create a new menu item
            saveItem.setFont(new Font("Arial", Font.BOLD, 15));
            saveItem.setBackground(new Color(0xffF5F5DC));
            saveItem.setOpaque(true);

            saveItem.addActionListener( new ActionListener()
            {
                public void actionPerformed( ActionEvent event )
                {
                    saveImage();
                }                                                                      // given valid input, it will display an image
            } );
            fileMenu.add( saveItem );
            fileMenu.add( exitItem );

            JMenuBar menuBar = new JMenuBar();                  // create a new menu bar
            menuBar.add( fileMenu );                           	// add the "File" menu to the menu bar
            this.setJMenuBar( menuBar );                        // attach the menu bar to this frame
        }

        public void redrawView() {
            mainScreen.redraw();
        }

        private void saveImage()    // prompt the user to specify the size of the n by n image
        {
            BufferedImage temp_img = getMainScreen().getMainViewImage().getFullMapImage(true);
            String inputString = JOptionPane.showInputDialog("ouput file?");

            if(inputString == null)
                return;

            try
            {
                File outputFile = new File( inputString );
                ImageIO.write( temp_img, "png", outputFile );
            }
            catch ( IOException e )
            {
                JOptionPane.showMessageDialog( this,
                        "Error saving file",
                        "oops!",
                        JOptionPane.ERROR_MESSAGE );
            }
        }
    }

}




