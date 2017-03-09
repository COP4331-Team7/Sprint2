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
            title.setForeground( new Color(0xff000000) );
            title.setBackground(new Color(0xffF5F5DC));
            title.setOpaque(true);

            offensiveDamageLabel = new JLabel("Offense: ");
            offensiveDamageLabel.setForeground(new Color(0xff644D50));
//            offensiveDamageLabel.setBackground( new Color(0xffCABD80) );
//            offensiveDamageLabel.setOpaque(true);
            offensiveDamageLabel.setFont(new Font("Serif", Font.BOLD, 18));

            defensiveDamageLabel = new JLabel("Defense:");
            defensiveDamageLabel.setForeground(new Color(0xff644D50));
            defensiveDamageLabel.setFont(new Font("Serif", Font.BOLD, 18));
//            defensiveDamageLabel.setBackground( new Color(0xffCABD80) );
//            defensiveDamageLabel.setOpaque(true);

            armorLabel = new JLabel("Armor:");
            armorLabel.setFont(new Font("Serif", Font.BOLD, 18));
//            armorLabel.setBackground( new Color(0xffCABD80) );
//            armorLabel.setOpaque(true);
            armorLabel.setForeground(new Color(0xff644D50));

            healthLabel = new JLabel("Health:");
            healthLabel.setFont(new Font("Serif", Font.BOLD, 18));
//            healthLabel.setBackground( new Color(0xffCABD80) );
//            healthLabel.setOpaque(true);
            healthLabel.setForeground(new Color(0xff644D50));

            upkeepLabel = new JLabel("Upkeep:");
            upkeepLabel.setFont(new Font("Serif", Font.BOLD, 18));
//            upkeepLabel.setBackground( new Color(0xffCABD80) );
//            upkeepLabel.setOpaque(true);
            upkeepLabel.setForeground(new Color(0xff644D50));

            productionLabel = new JLabel("Production rate:");
//            productionLabel.setBackground( new Color(0xffCABD80) );
//            productionLabel.setOpaque(true);
            productionLabel.setForeground(new Color(0xff644D50));
            productionLabel.setFont(new Font("Serif", Font.BOLD, 18));

            itemLabel = new JLabel("Items:");
            itemLabel.setFont(new Font("Serif", Font.BOLD, 18));
//            itemLabel.setBackground( new Color(0xffCABD80) );
            itemLabel.setForeground(new Color(0xff644D50));
//            itemLabel.setOpaque(true);

            JLabel temps = new JLabel();
//            temps.setBackground( new Color(0xffCABD80) );
//            temps.setOpaque(true);

            title2 = new JLabel("Player Stats: ");
            title2.setFont(new Font("Serif", Font.BOLD, 22));
            title2.setForeground( new Color(0xff000000) );
            title2.setBackground(new Color(0xffF5F5DC));
            title2.setOpaque(true);

            lifeLabel = new JLabel("Nutrients:");
            lifeLabel.setFont(new Font("Serif", Font.BOLD, 18));
            lifeLabel.setForeground(new Color(0xff644D50));
//            lifeLabel.setBackground( new Color(0xffCABD80 ));
//            lifeLabel.setOpaque(true);

            researchLabel = new JLabel("Research:");
            researchLabel.setFont(new Font("Serif", Font.BOLD, 18));
            researchLabel.setForeground(new Color(0xff644D50));
//            researchLabel.setBackground( new Color(0xffCABD80) );
//            researchLabel.setOpaque(true);

            constructionLabel = new JLabel("Metal:");
            constructionLabel.setFont(new Font("Serif", Font.BOLD, 18));
//            constructionLabel.setBackground( new Color(0xffCABD80) );
            constructionLabel.setForeground(new Color(0xff644D50));
//            constructionLabel.setOpaque(true);

            powerLabel = new JLabel("Power: ");
            powerLabel.setFont(new Font("Serif", Font.BOLD, 18));
//            powerLabel.setBackground( new Color(0xffCABD80) );
//            powerLabel.setOpaque(true);
            powerLabel.setForeground(new Color(0xff644D50));


            t.add(title);
            t.add(new JLabel());
            t.add(offensiveDamageLabel);
            t.add(armorLabel);
            t.add(defensiveDamageLabel);
            t.add(healthLabel);
            t.add(itemLabel);
            t.add(upkeepLabel);
            t.add(productionLabel);
            t.add(new JLabel());
            t.add(title2);
            t.add(new JLabel());

            t.add(lifeLabel);
            t.add(constructionLabel);
            t.add(powerLabel);
            t.add(researchLabel);

            this.add( t, BorderLayout.SOUTH );

            setMinimumSize(new Dimension(323, 200));
            //this.setBorder(BorderFactory.createLineBorder(new Color(0xffCABD80), 3));

            repaint();
        }

    public void clearStats() {
        offensiveDamageLabel.setText("Offense:");
        defensiveDamageLabel.setText("Defense:");
        healthLabel.setText("Health");
        armorLabel.setText("Armor");
        upkeepLabel.setText("Upkeep");
        productionLabel.setText("Production rate:");
        itemLabel.setText("Items");
    }

    public void clearPlayerStats() {
        lifeLabel.setText("Nutrients:");
        researchLabel.setText("Research:");
        constructionLabel.setText("Metal:");
        powerLabel.setText("Power:");
    }

    public void setOffensiveDamageLabel(String s) {
        offensiveDamageLabel.setText("Offense: " + s);
    }
    public void setDefensiveDamageLabel(String s) {
        defensiveDamageLabel.setText("Defense: " + s);
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
        productionLabel.setText("Production rate: " + s);
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
        if(unit == null)
            return;
        UnitStats stats = unit.getUnitStats();
        setOffensiveDamageLabel( Integer.toString( stats.getOffensiveDamage() ) );
        setDefensiveDamageLabel( Integer.toString( stats.getDefensiveDamage() ) );
        setArmorLabel( Integer.toString( stats.getArmor() ) );
        setHealthLabel( Integer.toString( stats.getHealth() ) );
        // setUpkeepLabel( Integer.toString( stats.getUpkeep() ) );
        setProductionLabel( "0" );
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



