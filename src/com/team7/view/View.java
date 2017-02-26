package com.team7.view;

import javax.swing.*;

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
}


