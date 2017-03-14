package com.team7;

import com.team7.controller.*;

import com.team7.view.OptionsScreen.OptionsScreen;

import com.team7.model.Game;
import com.team7.model.Player;
import com.team7.view.View;

public class Main {

    public static void main(String[] args) {
        // Model
        Game game = new Game();
        game.newGameState();

        // View
        View view = new View();
        waitForGUI( view );

        // Controller
        HomeScreenController hsc = new HomeScreenController(game, view);
        StructureOverviewController soc = new StructureOverviewController(game, view);
        UnitOverviewController uoc = new UnitOverviewController(game, view);
        MainScreenController ssc = new MainScreenController(game, view);
        PathSelectController psc = new PathSelectController(game, view );
        CommandSelectController csc = new CommandSelectController(game, view);
        OptionsController optionsController = new OptionsController(view, game);
        MapScreenController mapScreenController = new MapScreenController(game, view);
        TechnologyScreenController technologyScreenController = new TechnologyScreenController(game, view);

    }

    private static void waitForGUI(View view) {
        long startTime = System.nanoTime();
        // in the main method new view() returns instantly because it schedules the GUI creation to be executed asynchronously on the EDT
        while( view.getScreen() == null ) {
            try {
                Thread.sleep(300);
            }
            catch (Exception e) {}
        }
        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1000000;  // time elapsed in milliseconds
        System.out.println("view construction time: " + duration + " ms");

    }
}
