package com.team7.view.MainScreen;

import com.team7.model.*;
import com.team7.model.entity.structure.Structure;
import com.team7.model.entity.unit.Unit;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MainViewInfo extends JPanel {

//    private Unit unit = null;
//    private Structure structure = null;
//    private Player currentPlayer = null;

    private JLabel offensiveDamageLabel;
    private JLabel defensiveDamageLabel;
    private JLabel armorLabel;
    private JLabel healthLabel;
    private JLabel upkeepLabel;
    private JLabel productionLabel;
    private JLabel itemLabel;
    private JLabel typeLabel;
    private JLabel lifeLabel;
    private JLabel researchLabel;
    private JLabel constructionLabel;
    private JLabel powerLabel;


    public MainViewInfo()
        {
            JPanel t = new JPanel();
            t.setLayout(new GridLayout(0,1));

            offensiveDamageLabel = new JLabel("Offensive damage:");
            offensiveDamageLabel.setFont(new Font("Serif", 0, 12));
            t.add(offensiveDamageLabel);
            defensiveDamageLabel = new JLabel("Defensive damage:");
            defensiveDamageLabel.setFont(new Font("Serif", 0, 12));
            t.add(defensiveDamageLabel);
            armorLabel = new JLabel("Armor:");
            armorLabel.setFont(new Font("Serif", 0, 12));
            t.add(armorLabel);
            healthLabel = new JLabel("Health:");
            healthLabel.setFont(new Font("Serif", 0, 12));
            t.add(healthLabel);
            upkeepLabel = new JLabel("Upkeep:");
            upkeepLabel.setFont(new Font("Serif", 0, 12));
            t.add(upkeepLabel);
            productionLabel = new JLabel("Production rates:");
            t.add(productionLabel);
            itemLabel = new JLabel("Items:");
            productionLabel.setFont(new Font("Serif", 0, 12));
            itemLabel.setFont(new Font("Serif", 0, 12));
            t.add(itemLabel);




            lifeLabel = new JLabel("Nutrients:");
            lifeLabel.setFont(new Font("Serif", Font.BOLD, 15));
            lifeLabel.setForeground(Color.blue);
            t.add(lifeLabel);
            researchLabel = new JLabel("Research:");
            researchLabel.setFont(new Font("Serif", Font.BOLD, 15));
            researchLabel.setForeground(Color.blue);
            t.add(researchLabel);
            constructionLabel = new JLabel("Metal:");
            constructionLabel.setFont(new Font("Serif", Font.BOLD, 15));
            researchLabel.setForeground(Color.blue);
            constructionLabel.setForeground(Color.blue);
            t.add(constructionLabel);
            researchLabel.setForeground(Color.blue);
            powerLabel = new JLabel("Power: ");
            powerLabel.setForeground(Color.blue);
            powerLabel.setFont(new Font("Serif", Font.BOLD, 15));
            t.add(powerLabel);
            this.add( t, BorderLayout.SOUTH );

            setPreferredSize(new Dimension(200, 200));

            repaint();
        }

    public void clearStats() {
        offensiveDamageLabel.setText("Offensive damage:");
        defensiveDamageLabel.setText("Defensive damage:");
        healthLabel.setText("Health");
        armorLabel.setText("Armor");
        upkeepLabel.setText("Upkeep");
        productionLabel.setText("Production rates:");
        itemLabel.setText("Items");
        typeLabel.setText("Unit/Structure: ");
    }

    public void clearPlayerStats() {
        lifeLabel.setText("Nutrients:");
        researchLabel.setText("Research:");
        constructionLabel.setText("Metal:");
        powerLabel.setText("Power:");

    }

    public void setOffensiveDamageLabel(String s) {
        offensiveDamageLabel.setText("Offensive damage: " + s);
    }
    public void setDefensiveDamageLabel(String s) {
        defensiveDamageLabel.setText("Defensive damage: " + s);
    }
    public void setArmorLabel(String s) {
        armorLabel.setText("Armor: " + s);
    }
    public void setHealthLabel(String s) {
        healthLabel.setText("Health: " + s);
    }
    public void setUpkeepLabel(String s) {
        upkeepLabel.setText("Upkeep: " + s);
    }
    public void setProductionLabel(String s) {
        productionLabel.setText("Production rates: " + s);
    }
    public void setLifeLabel(String s) {
        lifeLabel.setText("Nutrients: " + s);
    }
    public void setResearchLabel(String s) {
        researchLabel.setText("Research: " + s);
    }
    public void setConstructionLabel(String s) {
        constructionLabel.setText("Metal:  " + s);
    }
    public void setPowerLabel(String s) {
        powerLabel.setText("Power:  " + s);
    }

    public void setItemLabel(String s) {
        itemLabel.setText("Items: " + s);
    }
    }

    //    public void setUnit(Unit unit) {
//        if(unit == null)
//            clearStats();
//        else
//            updateStats( unit );
//    }
//
//    public void setStructure(Structure structure) {
//        if(structure == null)
//            clearStats();
//        else
//            updateStats( structure );
//    }

//    public  void updateStats( Unit unit ) {
//        UnitStats stats = unit.getUnitStats();
//        setOffensiveDamageLabel( Integer.toString( stats.getOffensiveDamage() ) );
//        setDefensiveDamageLabel( Integer.toString( stats.getDefensiveDamage() ) );
//        setArmorLabel( Integer.toString( stats.getArmor() ) );
//        setHealthLabel( Integer.toString( stats.getHealth() ) );
//        // setUpkeepLabel( Integer.toString( stats.getUpkeep() ) );
//        setProductionLabel( "N/A" );
//        setUpkeepLabel(  Integer.toString( stats.getUpkeep() ) );
//        // setItemLabel( );
//    }

//    public void updateStats(Player player) {
//        currentPlayer = player;
//        setLifeLabel( Integer.toString( player.getMoney() ) );
//        setResearchLabel( Integer.toString( player.getResearch() ) );
//        setConstructionLabel( Integer.toString( player.getConstruction() ) );
//    }

//
//    public void updateStats() {
//        currentPlayer = currentPlayer;
//        setLifeLabel( Integer.toString( currentPlayer.getMoney() ) );
//        setResearchLabel( Integer.toString( currentPlayer.getResearch() ) );
//        setConstructionLabel( Integer.toString( currentPlayer.getConstruction() ) );
//    }
//    public void setCurrentPlayer( Player player ) {
//          updateStatsteStats( player );
//    }



