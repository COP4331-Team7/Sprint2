package com.team7.controller;

import com.team7.Main;
import com.team7.model.DemoGameMode;
import com.team7.model.Player;
import com.team7.view.MainScreen.MainViewImage;
import com.team7.view.OptionsScreen.ConfigurableControls.ConfigReader;
import com.team7.model.Game;
import com.team7.view.OptionsScreen.OptionsScreen;
import com.team7.view.View;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * TODO figure out a way to keep special key cases configurable (ie arrow keys) if needed
 * Actionlisteners and interaction for ConfigReader/OptionsScreen
 */
public class OptionsController{
    private OptionsScreen optionsScreen;
    private ConfigReader reader;
    private View view;
    private Game game ;
    private MainViewImage mainViewImage;

    public OptionsController(View view, Game game) {
        this.view = view;
        this.game = game;
        this.mainViewImage = view.getMainViewImage();
        this.optionsScreen = view.getOptionScreen();
        reader = new ConfigReader();
        addActionListeners();
        reloadControls(game.getCurrentPlayer().getName());
    }

    private void addActionListeners() {
        optionsScreen.getChangeControlButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == optionsScreen.getChangeControlButton()) {

                    //get currently selected key:value
                    String selectedString = optionsScreen.getControlsList().getSelectedValue();
                    if(selectedString == null)
                        return;
                    int colonIndex = selectedString.indexOf(':');
                    String key = selectedString.substring(0, colonIndex);
                    String popupMessage = "Change " + key + " *note only one input character alllowed!";

                    if(!key.isEmpty()){
                        String input = JOptionPane.showInputDialog(optionsScreen.getParent(), popupMessage, null);
                        if (input != null){ //user pressed OK, and did not press CANCEL
                            System.out.println(input);
                            //get first char from input
                            String newValue = input.substring(0,1);
                            reader.changeValueByKey(game.getCurrentPlayer().getName(), key, newValue);
                            reloadControls(game.getCurrentPlayer().getName());
                        }
                    }
                }
            }
        });

        optionsScreen.getResetControlsButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == optionsScreen.getResetControlsButton()) {
                    //reset player's config file by overwriting with a default file
                    reader.resetToDefault(game.getCurrentPlayer().getName());
                    reloadControls(game.getCurrentPlayer().getName());
                    optionsScreen.setSlidersToDefault();
                }
            }
        });

       optionsScreen.getMainScreenButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == optionsScreen.getMainScreenButton() )
                    view.setCurrScreen("MAIN");
            }
        });
        optionsScreen.getUnitScreenButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == optionsScreen.getUnitScreenButton() )
                    view.setCurrScreen("UNIT_OVERVIEW");
            }
        });
        optionsScreen.getStructureScreen().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == optionsScreen.getStructureScreen() )
                    view.setCurrScreen("STRUCTURE_OVERVIEW");
            }
        });
        optionsScreen.getMapScreenSelectButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == optionsScreen.getMapScreenSelectButton())
                    view.getMapScreen().setImage( view.getMainViewImage().getFullMapImage(true) );
                view.setCurrScreen("MAP_SCREEN");
            }
        });

        optionsScreen.getTechnologyScreenButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == optionsScreen.getTechnologyScreenButton()){
                    view.setCurrScreen("TECHNOLOGY");
                }
            }
        });

        optionsScreen.getS1().addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                mainViewImage.setScrollSpeed( optionsScreen.getScrollSpeed() );
            }
        });

        ItemListener itemListener = new ItemListener() {
            public void itemStateChanged(ItemEvent itemEvent) {
                int state = itemEvent.getStateChange();
                if (state == ItemEvent.SELECTED) {
                    mainViewImage.drawResources( true );
                    optionsScreen.getShowResourceButton().setForeground( new Color(0, 175, 0, 255) );

                    optionsScreen.getShowResourceButton().setText("Resource display [ON]");
                } else {
                    mainViewImage.drawResources( false );
                    optionsScreen.getShowResourceButton().setForeground(new Color(0xCD3700) );
                    optionsScreen.getShowResourceButton().setText("Resource display [OFF]");
                }
                mainViewImage.reDrawMap();
            }
        };
        optionsScreen.getShowResourceButton().addItemListener(itemListener);

        ItemListener itemListener2 = new ItemListener() {
            public void itemStateChanged(ItemEvent itemEvent) {
                int state = itemEvent.getStateChange();
                if (state == ItemEvent.SELECTED) {
                    mainViewImage.drawUnits( false );
                    optionsScreen.getShowUnitsButton().setForeground( new Color(0xCD3700));
                    optionsScreen.getShowUnitsButton().setText("Unit display [OFF]");
                } else {
                    mainViewImage.drawUnits( true );
                    optionsScreen.getShowUnitsButton().setForeground( new Color(0, 155, 0, 255) );
                    optionsScreen.getShowUnitsButton().setText("Unit display [ON]");
                }
                mainViewImage.reDrawMap();
            }
        };
        optionsScreen.getShowUnitsButton().addItemListener(itemListener2);

        optionsScreen.getHomeScreenButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == optionsScreen.getHomeScreenButton() )
                    view.setCurrScreen("HOME");
            }
        });

        optionsScreen.getFutureGameButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == optionsScreen.getFutureGameButton() ){
                    optionsScreen.resetS3Value();
                    System.out.println("to the future");
                    Player player1 = new Player("One");
                    Player player2 = new Player("Two");
                    Game demoGame      = new Game(player1, player2);
                    DemoGameMode dgm = new DemoGameMode(demoGame);
                    dgm.activate();
                    //re init all controllers
                    HomeScreenController hsc = new HomeScreenController(demoGame, view);
                    StructureOverviewController soc = new StructureOverviewController(view, demoGame.getCurrentPlayer());
                    UnitOverviewController uoc = new UnitOverviewController(view, demoGame.getCurrentPlayer());
                    PathSelectController psc = new PathSelectController(demoGame, view );
                    CommandSelectController csc = new CommandSelectController(demoGame, view);
                    OptionsController optionsController = new OptionsController(view, demoGame);
                    MapScreenController mapScreenController = new MapScreenController(demoGame, view);
                    ResearchScreenController roc = new ResearchScreenController();
                    MainScreenController ssc = new MainScreenController(demoGame, view, soc, uoc);
                    TechnologyScreenController technologyScreenController = new TechnologyScreenController(demoGame, view);


                    view.setMap(demoGame.getMap());
                    mainViewImage.reDrawMap();


                }
            }
        });



    }

    public void reloadControls(String player){
        optionsScreen.setModel(reader.getAllControlsByPlayer(player));
    }

}
