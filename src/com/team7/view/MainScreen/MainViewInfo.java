package com.team7.view.MainScreen;

import com.team7.model.entity.unit.Unit;
import com.team7.model.entity.unit.UnitStats;

import javax.swing.*;
import java.awt.*;

public class MainViewInfo extends JPanel {

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
    private JLabel playerNameLabel;
    private JPanel jPanel;
    private JLabel currSelectionName;


    public MainViewInfo()
        {
            jPanel = new JPanel();
            jPanel.setLayout(new GridLayout(0,2));

            currSelectionName = new JLabel(" ");
            currSelectionName.setFont(new Font("Serif", Font.BOLD, 22));
            currSelectionName.setForeground( new Color(0xff000000) );
            currSelectionName.setBackground(new Color(0xffF5F5DC));
            currSelectionName.setOpaque(true);



            offensiveDamageLabel = new JLabel("Offense: ");
            offensiveDamageLabel.setForeground(new Color(0xff644D50));
            offensiveDamageLabel.setBackground( new Color(0xffF5F5DC) );
            offensiveDamageLabel.setOpaque(true);
            offensiveDamageLabel.setFont(new Font("Serif", Font.BOLD, 18));

            defensiveDamageLabel = new JLabel("Defense:");
            defensiveDamageLabel.setForeground(new Color(0xff644D50));
            defensiveDamageLabel.setFont(new Font("Serif", Font.BOLD, 18));
            defensiveDamageLabel.setBackground( new Color(0xffF5F5DC) );
            defensiveDamageLabel.setOpaque(true);

            armorLabel = new JLabel("Armor:");
            armorLabel.setFont(new Font("Serif", Font.BOLD, 18));
            armorLabel.setBackground( new Color(0xffF5F5DC) );
            armorLabel.setOpaque(true);
            armorLabel.setForeground(new Color(0xff644D50));

            healthLabel = new JLabel("Health:");
            healthLabel.setFont(new Font("Serif", Font.BOLD, 18));
            healthLabel.setBackground( new Color(0xffF5F5DC) );
            healthLabel.setOpaque(true);
            healthLabel.setForeground(new Color(0xff644D50));

            upkeepLabel = new JLabel("Upkeep:");
            upkeepLabel.setFont(new Font("Serif", Font.BOLD, 18));
            upkeepLabel.setBackground( new Color(0xffF5F5DC) );
            upkeepLabel.setOpaque(true);
            upkeepLabel.setForeground(new Color(0xff644D50));

            productionLabel = new JLabel("Production rate:");
            productionLabel.setBackground( new Color(0xffF5F5DC) );
            productionLabel.setOpaque(true);
            productionLabel.setForeground(new Color(0xff644D50));
            productionLabel.setFont(new Font("Serif", Font.BOLD, 18));

            itemLabel = new JLabel("Items:");
            itemLabel.setFont(new Font("Serif", Font.BOLD, 18));
            itemLabel.setBackground( new Color(0xffF5F5DC) );
            itemLabel.setForeground(new Color(0xff644D50));
            itemLabel.setOpaque(true);

            JLabel temps = new JLabel();
            temps.setBackground( new Color(0xffF5F5DC) );
            temps.setOpaque(true);

            playerNameLabel = new JLabel("Player Stats: ");
            playerNameLabel.setFont(new Font("Serif", Font.BOLD, 22));
            playerNameLabel.setForeground( new Color(0xff000000) );
            playerNameLabel.setBackground(new Color(0xffF5F5DC));
            playerNameLabel.setOpaque(true);

            lifeLabel = new JLabel("Nutrients:");
            lifeLabel.setFont(new Font("Serif", Font.BOLD, 18));
            lifeLabel.setForeground(new Color(0xff644D50));
            lifeLabel.setBackground( new Color(0xffF5F5DC ));
            lifeLabel.setOpaque(true);

            researchLabel = new JLabel("Research:");
            researchLabel.setFont(new Font("Serif", Font.BOLD, 18));
            researchLabel.setForeground(new Color(0xff644D50));
            researchLabel.setBackground( new Color(0xffF5F5DC) );
            researchLabel.setOpaque(true);

            constructionLabel = new JLabel("Metal:");
            constructionLabel.setFont(new Font("Serif", Font.BOLD, 18));
            constructionLabel.setBackground( new Color(0xffF5F5DC) );
            constructionLabel.setForeground(new Color(0xff644D50));
            constructionLabel.setOpaque(true);

            powerLabel = new JLabel("Power: ");
            powerLabel.setFont(new Font("Serif", Font.BOLD, 18));
            powerLabel.setBackground( new Color(0xffF5F5DC) );
            powerLabel.setOpaque(true);
            powerLabel.setForeground(new Color(0xff644D50));


            jPanel.add( currSelectionName );
            JLabel j = new JLabel();
            j.setBackground(   new Color(0xffF5F5DC) );
            j.setOpaque(true);
            jPanel.add( j );
            jPanel.add(offensiveDamageLabel);
            jPanel.add(armorLabel);
            jPanel.add(defensiveDamageLabel);
            jPanel.add(healthLabel);
            jPanel.add(itemLabel);
            jPanel.add(upkeepLabel);
            jPanel.add(productionLabel);
            JLabel jj = new JLabel();
            jj.setBackground(   new Color(0xffF5F5DC) );
            jj.setOpaque(true);
            jPanel.add( jj );
            jPanel.add(playerNameLabel);
            JLabel jjj = new JLabel();
            jjj.setBackground(   new Color(0xffF5F5DC) );
            jjj.setOpaque(true);
            jPanel.add( jjj );

            jPanel.add(lifeLabel);
            jPanel.add(constructionLabel);
            jPanel.add(powerLabel);
            jPanel.add(researchLabel);

            this.add(jPanel, BorderLayout.SOUTH );

            setMinimumSize(new Dimension(323, 200));
            //this.setBorder(BorderFactory.createLineBorder(new Color(0xffCABD80), 3));

            repaint();
        }

    public void clearStats() {
        offensiveDamageLabel.setText("Offense:");
        defensiveDamageLabel.setText("Defense:");
        healthLabel.setText("Health:");
        armorLabel.setText("Armor:");
        upkeepLabel.setText("Upkeep:");
        productionLabel.setText("Production rate:");
        itemLabel.setText("Items:");
        currSelectionName.setText(" no selection ");
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
    public void setPowerLabel(String s) { powerLabel.setText("Power:  " + s);
    }
    public void setPlayerNameLabel(String s) {
        playerNameLabel.setText("Player " + s + ":");
    }
    public void setItemLabel(String s) {
        itemLabel.setText("Items: " + s);
    }
    public void setCurrSelectionName(String s) {
        currSelectionName.setText(s);
    }


    public  void updateStats( Unit unit ) {
        if(unit == null) {
            clearStats();
            return;
        }
        UnitStats stats = unit.getUnitStats();
        setOffensiveDamageLabel( Integer.toString( stats.getOffensiveDamage() ) );
        setDefensiveDamageLabel( Integer.toString( stats.getDefensiveDamage() ) );
        setArmorLabel( Integer.toString( stats.getArmor() ) );
        setHealthLabel( Integer.toString( stats.getHealth() ) );
        setUpkeepLabel( Integer.toString( stats.getUpkeep() ) );
        setProductionLabel( "0" );
        setUpkeepLabel(  Integer.toString( stats.getUpkeep() ) );
        setCurrSelectionName(unit.getType() + " " + unit.getId());
        setItemLabel( "none" );
    }
}




