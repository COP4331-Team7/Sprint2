package com.team7.view.MainScreen;

import com.team7.model.*;
import com.team7.model.entity.structure.Structure;
import com.team7.model.entity.unit.Unit;
import com.team7.model.entity.unit.UnitStats;

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

    private JLabel offensiveDamageLabel_bar;
    private JLabel defensiveDamageLabel_bar;
    private JLabel armorLabel_bar;
    private JLabel healthLabel_bar;
    private JLabel upkeepLabel_bar;
    private JLabel productionLabel_bar;
    private JLabel itemLabel_bar;
    private JLabel typeLabel_bar;
    private JLabel lifeLabel_bar;
    private JLabel researchLabel_bar;
    private JLabel constructionLabel_bar;
    private JLabel powerLabel_bar;

    JPanel t;


    public MainViewInfo()
        {
            t = new JPanel();
            t.setLayout(new GridLayout(0,2));

            offensiveDamageLabel = new JLabel("Offensive damage: ");
            offensiveDamageLabel_bar = new JLabel("     ");
            offensiveDamageLabel_bar.setBackground( new Color(0xFF00FF00) );
            offensiveDamageLabel_bar.setOpaque(true);

            offensiveDamageLabel.setFont(new Font("Serif", 0, 12));
            t.add(offensiveDamageLabel);
            t.add(offensiveDamageLabel_bar);
            defensiveDamageLabel = new JLabel("Defensive damage:");
            defensiveDamageLabel.setFont(new Font("Serif", 0, 12));
            defensiveDamageLabel_bar = new JLabel("     ");
            defensiveDamageLabel_bar.setBackground( new Color(0xFFAFFC00) );
            defensiveDamageLabel_bar.setOpaque(true);



            t.add(defensiveDamageLabel);
            t.add(defensiveDamageLabel_bar);

            armorLabel = new JLabel("Armor:");
            armorLabel.setFont(new Font("Serif", 0, 12));
            armorLabel_bar = new JLabel("     ");
            armorLabel_bar.setBackground( new Color(0xFF00FF00) );
            armorLabel_bar.setOpaque(true);

            t.add(armorLabel);
            t.add(armorLabel_bar);

            healthLabel = new JLabel("Health:");
            healthLabel.setFont(new Font("Serif", 0, 12));
            healthLabel_bar = new JLabel("     ");
            healthLabel_bar.setBackground( new Color(0xFFAFFC00) );
            healthLabel_bar.setOpaque(true);

            t.add(healthLabel);
            t.add(healthLabel_bar);

            upkeepLabel = new JLabel("Upkeep:");
            upkeepLabel.setFont(new Font("Serif", 0, 12));
            upkeepLabel_bar = new JLabel("     ");
            upkeepLabel_bar.setBackground( new Color(0xFF00FF00) );
            upkeepLabel_bar.setOpaque(true);
            t.add(upkeepLabel);
            t.add(upkeepLabel_bar);

            productionLabel = new JLabel("Production rates:");
            t.add(productionLabel);
            productionLabel_bar = new JLabel("     ");
            productionLabel_bar.setBackground( new Color(0xFFAFFC00) );
            productionLabel_bar.setOpaque(true);
            t.add(productionLabel_bar);

            itemLabel = new JLabel("Items:");
            productionLabel.setFont(new Font("Serif", 0, 12));
            itemLabel.setFont(new Font("Serif", 0, 12));
            t.add(itemLabel);
            t.add(new JLabel());

            lifeLabel = new JLabel("Nutrients:");
            lifeLabel.setFont(new Font("Serif", Font.BOLD, 15));
            lifeLabel.setForeground(Color.blue);
            lifeLabel_bar = new JLabel("     ");
            lifeLabel_bar.setBackground( new Color(0xFF00FF00) );
            lifeLabel_bar.setOpaque(true);
            t.add(lifeLabel);
            t.add(lifeLabel_bar);

            researchLabel = new JLabel("Research:");
            researchLabel.setFont(new Font("Serif", Font.BOLD, 15));
            researchLabel_bar = new JLabel("     ");
            researchLabel_bar.setBackground( new Color(0xFFAFFC00) );
            researchLabel_bar.setOpaque(true);
            researchLabel.setForeground(Color.blue);
            t.add(researchLabel);
            t.add(researchLabel_bar);

            constructionLabel = new JLabel("Metal:");
            constructionLabel.setFont(new Font("Serif", Font.BOLD, 15));
            constructionLabel_bar = new JLabel("     ");
            constructionLabel_bar.setBackground( new Color(0xFF00FF00) );
            constructionLabel_bar.setOpaque(true);
            researchLabel.setForeground(Color.blue);
            constructionLabel.setForeground(Color.blue);
            t.add(constructionLabel);
            t.add(constructionLabel_bar);

            researchLabel.setForeground(Color.blue);
            powerLabel = new JLabel("Power: ");
            powerLabel.setForeground(Color.blue);
            powerLabel.setFont(new Font("Serif", Font.BOLD, 15));
            powerLabel_bar = new JLabel("     ");
            powerLabel_bar.setBackground( new Color(0xFFAFFC00) );
            powerLabel_bar.setOpaque(true);
            t.add(powerLabel);
            t.add(powerLabel_bar);

            this.add( t, BorderLayout.SOUTH );

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

    public void updateStats( Unit unit ) {
        UnitStats stats = unit.getUnitStats();
        setOffensiveDamageLabel( Integer.toString( stats.getOffensiveDamage() ) );
        setDefensiveDamageLabel( Integer.toString( stats.getDefensiveDamage() ) );
        setArmorLabel( Integer.toString( stats.getArmor() ) );
        setHealthLabel( Integer.toString( stats.getHealth() ) );
        // setUpkeepLabel( Integer.toString( stats.getUpkeep() ) );
        setProductionLabel( "N/A" );
        setUpkeepLabel(  Integer.toString( stats.getUpkeep() ) );
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



