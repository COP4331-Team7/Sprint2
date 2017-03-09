package com.team7;

import com.team7.controller.*;

import com.team7.view.OptionsScreen.OptionsScreen;

import com.team7.model.Game;
import com.team7.model.Player;
import com.team7.view.View;

public class Main {

    public static void main(String[] args) {
        // Model
        Player player1 = new Player("One");
        Player player2 = new Player("Two");
        Game game      = new Game(player1, player2);
        game.startGame();

        // view
        View view = new View();

        waitForGUI( view );
        view.setMap( game.getMap() );

        HomeScreenController hsc = new HomeScreenController(game, view);
        StructureOverviewController soc = new StructureOverviewController(game, view);
        UnitOverviewController uoc = new UnitOverviewController(game, view);
        MainScreenController ssc = new MainScreenController(game, view);
        PathSelectController psc = new PathSelectController(game, view );
        CommandSelectController csc = new CommandSelectController(game, view);
        OptionsController optionsController = new OptionsController( view,game );

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
