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
    private JLabel lifeLabel;
    private JLabel researchLabel;
    private JLabel constructionLabel;
    private JLabel powerLabel;

    public void setTitle2(String s) {
        title2.setText("Player " + s + ":");
    }

    JLabel title2;

    JPanel t;


    public MainViewInfo()
        {
            t = new JPanel();
            t.setLayout(new GridLayout(0,2));

            JLabel title = new JLabel("Unit Stats: ");
            title.setFont(new Font("Serif", Font.BOLD, 22));
            title.setForeground( new Color(0xff644D50) );
            t.add(title);
            t.add(new JLabel());

            offensiveDamageLabel = new JLabel("Offensive damage: ");
            offensiveDamageLabel.setForeground(new Color(0xAACAECCF));
            offensiveDamageLabel.setBackground( new Color(0xffCABD80) );
            offensiveDamageLabel.setOpaque(true);
            offensiveDamageLabel.setFont(new Font("Serif", Font.BOLD, 18));
            t.add(offensiveDamageLabel);
            defensiveDamageLabel = new JLabel("Defensive damage:");
            defensiveDamageLabel.setForeground(new Color(0xAACAECCF));

            defensiveDamageLabel.setFont(new Font("Serif", Font.BOLD, 18));
            defensiveDamageLabel.setBackground( new Color(0xffCABD80) );
            defensiveDamageLabel.setOpaque(true);

            t.add(defensiveDamageLabel);

            armorLabel = new JLabel("Armor:");
            armorLabel.setFont(new Font("Serif", Font.BOLD, 18));
            armorLabel.setBackground( new Color(0xffCABD80) );
            armorLabel.setOpaque(true);
            armorLabel.setForeground(new Color(0xAACAECCF));

            t.add(armorLabel);

            healthLabel = new JLabel("Health:");
            healthLabel.setFont(new Font("Serif", Font.BOLD, 18));
            healthLabel.setBackground( new Color(0xffCABD80) );
            healthLabel.setOpaque(true);
            healthLabel.setForeground(new Color(0xAACAECCF));

            t.add(healthLabel);

            upkeepLabel = new JLabel("Upkeep:");
            upkeepLabel.setFont(new Font("Serif", Font.BOLD, 18));
            upkeepLabel.setBackground( new Color(0xffCABD80) );
            upkeepLabel.setOpaque(true);
            upkeepLabel.setForeground(new Color(0xAACAECCF));

            t.add(upkeepLabel);

            productionLabel = new JLabel("Production rates:");
            t.add(productionLabel);
            productionLabel.setBackground( new Color(0xffCABD80) );
            productionLabel.setOpaque(true);
            productionLabel.setForeground(new Color(0xAACAECCF));

            itemLabel = new JLabel("Items:");
            productionLabel.setFont(new Font("Serif", Font.BOLD, 18));
            itemLabel.setFont(new Font("Serif", Font.BOLD, 18));
            t.add(itemLabel);
            itemLabel.setBackground( new Color(0xffCABD80) );
            itemLabel.setForeground(new Color(0xAACAECCF));

            itemLabel.setOpaque(true);
            JLabel temps = new JLabel();
            temps.setBackground( new Color(0xffCABD80) );
            temps.setOpaque(true);
            t.add(temps);

            title2 = new JLabel("Player Stats: ");
            title2.setFont(new Font("Serif", Font.BOLD, 22));
            title2.setForeground( new Color(0xff644D50) );
            t.add(title2);
            t.add(new JLabel());

            lifeLabel = new JLabel("Nutrients:");
            lifeLabel.setFont(new Font("Serif", Font.BOLD, 18));
            lifeLabel.setForeground(new Color(0xAACAECCF));

            lifeLabel.setBackground( new Color(0xffCABD80 ));
            lifeLabel.setOpaque(true);
            t.add(lifeLabel);

            researchLabel = new JLabel("Research:");
            researchLabel.setFont(new Font("Serif", Font.BOLD, 18));
            researchLabel.setForeground(new Color(0xAACAECCF));

            researchLabel.setBackground( new Color(0xffCABD80) );
            researchLabel.setOpaque(true);

            constructionLabel = new JLabel("Metal:");
            constructionLabel.setFont(new Font("Serif", Font.BOLD, 18));
            constructionLabel.setBackground( new Color(0xffCABD80) );
            constructionLabel.setForeground(new Color(0xAACAECCF));

            constructionLabel.setOpaque(true);
            t.add(constructionLabel);

            powerLabel = new JLabel("Power: ");
            powerLabel.setFont(new Font("Serif", Font.BOLD, 18));
            powerLabel.setBackground( new Color(0xffCABD80) );
            powerLabel.setOpaque(true);
            powerLabel.setForeground(new Color(0xAACAECCF));

            t.add(powerLabel);
            t.add(researchLabel);

            this.add( t, BorderLayout.SOUTH );

            this.setBorder(BorderFactory.createLineBorder(new Color(0xffCABD80), 3));
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

      public  void updateStats( Unit unit ) {
        UnitStats stats = unit.getUnitStats();
        setOffensiveDamageLabel( Integer.toString( stats.getOffensiveDamage() ) );
        setDefensiveDamageLabel( Integer.toString( stats.getDefensiveDamage() ) );
        setArmorLabel( Integer.toString( stats.getArmor() ) );
        setHealthLabel( Integer.toString( stats.getHealth() ) );
        // setUpkeepLabel( Integer.toString( stats.getUpkeep() ) );
        setProductionLabel( "N/A" );
        setUpkeepLabel(  Integer.toString( stats.getUpkeep() ) );
        // setItemLabel( );
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



