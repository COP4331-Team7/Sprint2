package com.team7.controller;


import com.team7.model.Map;
import com.team7.model.Player;
import com.team7.model.entity.Command;
import com.team7.model.entity.MovementCommand;
import com.team7.view.MainScreen.MainScreen;
import com.team7.view.MainScreen.MainViewMiniMap;
import com.team7.view.OptionsScreen.ConfigurableControls.ConfigReader;
import com.team7.model.Game;
import com.team7.model.Tile;
import com.team7.model.entity.unit.Unit;
import com.team7.view.MainScreen.CommandSelect;
import com.team7.view.MainScreen.MainViewImage;
import com.team7.view.OptionsScreen.OptionsScreen;
import com.team7.view.View;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Set;

public class PathSelectController {
    private Game game = null;
    private Map map = null;
    private View view = null;
    private OptionsScreen optionsScreen = null;
    private MainViewImage mainViewImage = null;
    private MainViewMiniMap miniMap = null;
    private CommandSelect commandView = null;

    public static boolean isRecording = false;
    private Tile selectedTile = null;
    private Tile startTile = null;
    private Tile destTile = null;
    private ConfigReader configReader;
    public static ArrayList<Tile> pathTile = new ArrayList<Tile>();

    private int moveLimit;
    private int moveAnimationSpeed = 10 * 30;

    public PathSelectController(Game game, View view) {
        this.game = game;
        this.map = game.getMap();
        this.view = view;
        this.optionsScreen = view.getOptionScreen();
        this.mainViewImage = view.getMainViewImage();
        this.miniMap = view.getMainScreen().getMiniMap();
        this.commandView = view.getCommandSelect();

        Player[] players = this.game.getPlayers();
        players[0].setMovementController(this);
        players[1].setMovementController(this);

        configReader = new ConfigReader();

        commandView.setController(this);
    }
    // ===============================================

    public void moveCursor(String direction) {

//        if(pathTile.size() >= moveLimit)
//            return;

//        selectedTile.isSelectedPath = false;

        if (direction.equals(configReader.getValueByKey(game.getCurrentPlayer().getName(), "Northwest"))) {
            selectedTile = map.getAdjacentTile(selectedTile, 7);
        } else if (direction.equals(configReader.getValueByKey(game.getCurrentPlayer().getName(), "Southwest"))) {
            selectedTile = map.getAdjacentTile(selectedTile, 1);
        } else if (direction.equals(configReader.getValueByKey(game.getCurrentPlayer().getName(), "South"))) {
            selectedTile = map.getAdjacentTile(selectedTile, 2);
        } else if (direction.equals(configReader.getValueByKey(game.getCurrentPlayer().getName(), "North"))) {
            selectedTile = map.getAdjacentTile(selectedTile, 8);
        } else if (direction.equals(configReader.getValueByKey(game.getCurrentPlayer().getName(), "Northeast"))) {
            selectedTile = map.getAdjacentTile(selectedTile, 9);
        } else if (direction.equals(configReader.getValueByKey(game.getCurrentPlayer().getName(), "Southeast"))) {
            selectedTile = map.getAdjacentTile(selectedTile, 3);
        }

        destTile = selectedTile;

        pathTile.add(selectedTile);
        selectedTile.isSelectedPath = true;
        mainViewImage.reDrawMap();
    }

    public ArrayList<Tile> getPath(Tile start, Tile destination, Set<Tile> openList, Set<Tile> closedList) {


        return null;
    }

    public void startRecordingPath(Tile startTile, int unitMovement) {

        moveLimit = unitMovement;

        pathTile.clear();
        this.startTile = startTile;

        if (selectedTile != null)
            selectedTile.isSelectedPath = false;

        isRecording = true;
        selectedTile = startTile;
        startTile.isSelectedPath = true;
        mainViewImage.zoomToDestination(startTile.getxCoordinate() - 11 / 2, startTile.getyCoordinate() - 16 / 2, 30);
    }

    public void drawPath(Unit unit) {

        moveAnimationSpeed = optionsScreen.getUnitSpeed() * 20;

        if (unit == null || pathTile.size() == 0)
            return;
        System.out.println("Destin: " + pathTile.get(pathTile.size() - 1).getxCoordinate() + "," + pathTile.get(pathTile.size() - 1).getxCoordinate());

        startTile.removeUnitFromTile(unit);
        startTile.isSelectedPath = false;
        if (unit.getCommandQueue().getCommandsList().size() > 0) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (Command command : unit.getCommandQueue().getCommandsList()) {
                        System.out.println(command);
                        MovementCommand movementCommand = (MovementCommand) command;
                        movementCommand.getDestinationTile().isSelectedPath = false;
//                        tile.isSelectedPath = false;
                    }

                    for (int k = 0; k < unit.getCommandQueue().getCommandsList().size(); k++) {

                        MovementCommand movementCommand = (MovementCommand) unit.getCommandQueue().getCommandsList().get(k);
                        System.out.println("In Command queue" + movementCommand.getDestinationTile().getxCoordinate() + "," + movementCommand.getDestinationTile().getyCoordinate());
                        game.getCurrentPlayer().moveUnit(unit, movementCommand.getDestinationTile());
                        game.updateCurrPlayerTileStates();
                        miniMap.setMiniMapImage(mainViewImage.getFullMapImage(false));

                        final BufferedImage mapSubsection = mainViewImage.drawSubsectionOfMap();
                        SwingUtilities.invokeLater(new Runnable()   // queue frame i on EDT for display
                        {
                            public void run() {
                                mainViewImage.setImage(mapSubsection);
                            }
                        });
                        try {
                            Thread.sleep(moveAnimationSpeed);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        if (k == unit.getCommandQueue().getCommandsList().size() - 1)
                            mainViewImage.zoomToDestination(pathTile.get(pathTile.size() - 1).getxCoordinate() - 11 / 2, pathTile.get(pathTile.size() - 1).getyCoordinate() - 16 / 2, 30);

                    }
                    unit.getCommandQueue().getCommandsList().clear();
                }

            }).start();
        } else {

            new Thread(new Runnable() {
                public void run() {

                    for (Tile tile : pathTile)
                        tile.isSelectedPath = false;

                    int totalMoves = unit.getUnitStats().getMovement();
                    System.out.println("Maximum limit" + totalMoves);

                    for (int i = 0; i < pathTile.size(); i++) {
                        if(unit==null)
                        {
                            System.out.print("Unit died");
                        }
                        else if (!game.getCurrentPlayer().moveUnit(unit, pathTile.get(i))) {
                            mainViewImage.reDrawMap();
                            break;
                        } else
//                        if( unit.getCommandQueue().getCommandsList().size()==0){// move the uni// t
                        {
                            if (i >= totalMoves - 1) {
                                for (int j = i; j < pathTile.size(); j++) {
                                    MovementCommand command = new MovementCommand("MOVE", pathTile.get(j));
                                    System.out.println("Adding to Queue" + pathTile.get(j).getxCoordinate() + "," + pathTile.get(j).getyCoordinate());
                                    unit.getCommandQueue().getCommandsList().add(command);
                                    System.out.println("ArrayList size" + unit.getCommandQueue().getCommandsList().size());
                                }
                                break;
                            }
//                                System.out.println(pathTile.get(i));
//                                Command command = new MovementCommand("MOVE", pathTile.get(i));
//                                System.out.println("Adding to Queue");
//                                unit.getCommandQueue().getCommandsList().add(command);
//                                System.out.println("ArrayList size" + unit.getCommandQueue().getCommandsList().size());


                            else {
                                game.getCurrentPlayer().moveUnit(unit, pathTile.get(i));
                                game.updateCurrPlayerTileStates();
                                miniMap.setMiniMapImage(mainViewImage.getFullMapImage(false));

                                final BufferedImage mapSubsection = mainViewImage.drawSubsectionOfMap();
                                SwingUtilities.invokeLater(new Runnable()   // queue frame i on EDT for display
                                {
                                    public void run() {
                                        mainViewImage.setImage(mapSubsection);
                                    }
                                });
                                try {
                                    Thread.sleep(moveAnimationSpeed);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }

                                if (i == pathTile.size() - 1)
                                    mainViewImage.zoomToDestination(pathTile.get(pathTile.size() - 1).getxCoordinate() - 11 / 2, pathTile.get(pathTile.size() - 1).getyCoordinate() - 16 / 2, 30);
                            }
                        }
                    }
                }


            }).start();


            System.out.println("Command Queue size: " + unit.getCommandQueue().getSize());
        }

    }

    public void zoomToTile(Tile tile) {
        if (tile == null)
            return;
        view.getMainViewImage().zoomToDestination(tile.getxCoordinate() - 11 / 2, tile.getyCoordinate() - 16 / 2, optionsScreen.getFocusSpeed());
    }

    public void reDraw() {
        mainViewImage.reDrawMap();
    }

    public Tile getDestinationTile() {
        return destTile;
    }

    public ArrayList<Tile> reEnforce(Unit unit) {
        ArrayList<Tile> path = null;
            if (unit.getLocation() != game.getCurrentPlayer().getArmies().get(0).getLocation()) {
                path = game.getCurrentPlayer().reEnforce(game.getMap(), unit);
                if(path!=null)
                {
                    for (Tile tile : path) {
                        tile.isSelectedPath = true;
                    }
                    reDraw();
                }

            }
        return path;
        }

    public void drawRenforce(ArrayList<Tile> tiles, Unit unit) {
        isRecording = true;
        if (unit.getCommandQueue().getCommandsList().size() > 0) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (Command command : unit.getCommandQueue().getCommandsList()) {
                        System.out.println(command);
                        MovementCommand movementCommand = (MovementCommand) command;
                        movementCommand.getDestinationTile().isSelectedPath = false;
//                        tile.isSelectedPath = false;
                    }

                    for (int k = 0; k < unit.getCommandQueue().getCommandsList().size(); k++) {

                        MovementCommand movementCommand = (MovementCommand) unit.getCommandQueue().getCommandsList().get(k);
                        System.out.println("In Command queue" + movementCommand.getDestinationTile().getxCoordinate() + "," + movementCommand.getDestinationTile().getyCoordinate());
                        game.getCurrentPlayer().moveUnit(unit, movementCommand.getDestinationTile());
                        game.updateCurrPlayerTileStates();
                        miniMap.setMiniMapImage(mainViewImage.getFullMapImage(false));

                        final BufferedImage mapSubsection = mainViewImage.drawSubsectionOfMap();
                        SwingUtilities.invokeLater(new Runnable()   // queue frame i on EDT for display
                        {
                            public void run() {
                                mainViewImage.setImage(mapSubsection);
                            }
                        });
                        try {
                            Thread.sleep(moveAnimationSpeed);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        if (k == unit.getCommandQueue().getCommandsList().size() - 1)
                            mainViewImage.zoomToDestination(tiles.get(tiles.size() - 1).getxCoordinate() - 11 / 2, tiles.get(tiles.size() - 1).getyCoordinate() - 16 / 2, 30);

                    }
                    unit.getCommandQueue().getCommandsList().clear();
                }

            }).start();
        } else {
            System.out.println("Trying to move");

            new Thread(new Runnable() {
                public void run() {

                    for (Tile tile : tiles)
                        tile.isSelectedPath = false;

                    int totalMoves = unit.getUnitStats().getMovement();
                    System.out.println("Maximum limit" + totalMoves);

                    for (int i = 0; i < tiles.size(); i++) {
                        if(unit==null)
                        {
                            System.out.print("Unit died");
                        }
                        else if (!game.getCurrentPlayer().moveUnit(unit, tiles.get(i))) {
                            mainViewImage.reDrawMap();
                            break;
                        } else
//                        if( unit.getCommandQueue().getCommandsList().size()==0){// move the uni// t
                        {
                            System.out.println("Trying to move deep");
                            if (i >= totalMoves - 1) {
                                for (int j = i; j < tiles.size(); j++) {
                                    MovementCommand command = new MovementCommand("MOVE", tiles.get(j));
                                    System.out.println("Adding to Queue" + tiles.get(j).getxCoordinate() + "," + tiles.get(j).getyCoordinate());
                                    unit.getCommandQueue().getCommandsList().add(command);
                                    System.out.println("ArrayList size" + unit.getCommandQueue().getCommandsList().size());
                                }
                                break;
                            }
//                                System.out.println(pathTile.get(i));
//                                Command command = new MovementCommand("MOVE", pathTile.get(i));
//                                System.out.println("Adding to Queue");
//                                unit.getCommandQueue().getCommandsList().add(command);
//                                System.out.println("ArrayList size" + unit.getCommandQueue().getCommandsList().size());


                            else {
                                game.getCurrentPlayer().moveUnit(unit, tiles.get(i));
                                game.updateCurrPlayerTileStates();
                                miniMap.setMiniMapImage(mainViewImage.getFullMapImage(false));

                                final BufferedImage mapSubsection = mainViewImage.drawSubsectionOfMap();
                                SwingUtilities.invokeLater(new Runnable()   // queue frame i on EDT for display
                                {
                                    public void run() {
                                        mainViewImage.setImage(mapSubsection);
                                    }
                                });
                                try {
                                    Thread.sleep(moveAnimationSpeed);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }

                                if (i == tiles.size() - 1)
                                    mainViewImage.zoomToDestination(tiles.get(tiles.size() - 1).getxCoordinate() - 11 / 2, tiles.get(tiles.size() - 1).getyCoordinate() - 16 / 2, 30);
                            }
                        }
                    }
                }


            }).start();


            System.out.println("Command Queue size: " + unit.getCommandQueue().getSize());
        }

    }
}
