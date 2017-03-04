package com.team7;


import com.team7.controller.OptionsController;
import com.team7.view.OptionsScreen;

import javax.swing.*;
import java.awt.*;
import com.team7.controller.InfluenceRadiusController;
import com.team7.controller.ScreenSelectController;
import com.team7.model.Game;
import com.team7.model.Player;
import com.team7.view.View;


public class Main {

    public static void main(String[] args) {
        // Model
        Player player1 = new Player("playerOne");
        Player player2 = new Player("playerTwo");
        Game game      = new Game(player1, player2);
        game.startGame();

        // view
        View view = new View();

        waitForGUI( view );
        view.setMap( game.getMap() );

        InfluenceRadiusController c = new InfluenceRadiusController(game.getMap(), view);
        
        OptionsScreen screen = new OptionsScreen();
        OptionsController optionsController = new OptionsController(screen);

        c.getRadius( player1.getUnits().get(0).getLocation(),4 );
//        c.getRadius( player1.getUnits().get(1).getLocation(),3 );



    }



    private static void waitForGUI(View view) {
        long startTime = System.nanoTime();
        // waiting on view construction because:
        // in the main method new view() returns instantly because it schedules the GUI creation to be executed asynchronously on the EDT
        while( view.getScreen() == null ) {
            try {
                Thread.sleep(50);
            }
            catch (Exception e) {}
        }
        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1000000;  // time elapsed in milliseconds
        System.out.println("view construction time: " + duration + " ms");

    }
}
