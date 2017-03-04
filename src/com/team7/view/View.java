package com.team7.view;

import com.team7.model.Map;
import com.team7.view.HomeScreen.HomeScreen;
import com.team7.view.MainScreen.MainScreen;

import javax.imageio.ImageIO;
import javax.swing.*;
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

    public Screen getScreen() {
        return frame;
    }
    public MainScreen getMainScreen() {
        return frame.getMainScreen();
    }

    public void setMap( Map map ) {
        frame.getMainScreen().getMainViewImage().setMap( map );
        frame.getMainScreen().drawMap();
    }

// ==================== INNER CLASS ==========================

    class Screen extends JFrame
    {
        private HomeScreen homeScreen = null;
        private MainScreen mainScreen = null;
        //private UnitScreen unitScreen = null;
        //private StructureScreen structureScreen = null;

        public Screen( int width, int height)
        {
            this.setTitle( " " );
            this.setSize( width, height );
            addMenu();

            homeScreen = new HomeScreen();
            mainScreen = new MainScreen();
            // unitScreen = new UnitScreen();
            // structureScreen = new StructureScreen();

            setCurrScreen("MAIN");
            this.setVisible( true );
        }

        public HomeScreen getHomeScreen() {
            return homeScreen;
        }
        public MainScreen getMainScreen() {
            return mainScreen;
        }
        //    public UnitScreen getUnitScreen() {
        //            return unitScreen;
        //    }
        //    public StructureScreen getStructureScreen() {
        //            return structureScreen;
        //    }

        public void setCurrScreen(String selected_screen) {

            getContentPane().removeAll();   // clear screen

            if(selected_screen == "HOME") {
                displayHomeScreen();
            }
            else if (selected_screen == "MAIN") {
                displayMainScreen();
            }
            else if (selected_screen == "UNIT_OVERVIEW") {
                displayUnitOverviewScreen();
            }
            else if (selected_screen == "STRUCTURE_OVERVIEW") {
                displayStructureOverviewScreen();
            }

            revalidate();
            repaint();
        }

        private void displayHomeScreen() {
            this.getContentPane().add( homeScreen );
        }
        private void displayMainScreen() {
            this.getContentPane().add( mainScreen );
        }
        private void displayUnitOverviewScreen() {
            // this.getContentPane().add( unitScreen );
        }
        private void displayStructureOverviewScreen() {
            //    this.getContentPane().add( structureScreen );
        }

        private void addMenu()
        {                       	   // addMenu() method used to setup a frame's menu bar
            JMenu fileMenu = new JMenu( "File" );
            JMenuItem exitItem = new JMenuItem( "Exit" );
            exitItem.addActionListener( new ActionListener() 	// define what happens when this menu item is selected
            {
                public void actionPerformed( ActionEvent event )
                {
                    System.exit( 0 );
                }
            } );
            fileMenu.add( exitItem );
            JMenuItem drawMapItem = new JMenuItem( "Re-draw map" );
            drawMapItem.addActionListener( new ActionListener()    // define what happens when this menu item is selected
            {
                public void actionPerformed( ActionEvent event )
                {
                    mainScreen.drawMap();
                }
            } );
            fileMenu.add( drawMapItem );
            JMenuItem saveItem = new JMenuItem( "Save full size map image" );     // create a new menu item
            saveItem.addActionListener( new ActionListener()
            {
                public void actionPerformed( ActionEvent event )
                {
                    saveImage();
                }                                                                      // given valid input, it will display an image
            } );
            fileMenu.add( saveItem );
            JMenuBar menuBar = new JMenuBar();                  // create a new menu bar
            menuBar.add( fileMenu );                           	// add the "File" menu to the menu bar
            this.setJMenuBar( menuBar );                        // attach the menu bar to this frame
        }

        private void saveImage()    // prompt the user to specify the size of the n by n image
        {
            BufferedImage temp_img = getMainScreen().getMainViewImage().getFullMapImage();
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




