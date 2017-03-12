package com.team7.view;

import javax.swing.*;
import java.awt.*;

public class ScreenSelectButtons extends JPanel {

        private JButton mainScreenSelectButton;
        private JButton unitScreenSelectButton;
        private JButton structureScreenSelectButton;
        private JButton optionsScreenSelectButton;
        private JButton mapScreenSelectButton;

        public ScreenSelectButtons() {
            mainScreenSelectButton      = new JButton("MAIN SCREEN");
            unitScreenSelectButton      = new JButton("UNIT OVERVIEW");
            structureScreenSelectButton = new JButton("STRUCTURE OVERVIEW");
            optionsScreenSelectButton   = new JButton("CONTROLS");
            mapScreenSelectButton       = new JButton("MAP OVERVIEW");

            mainScreenSelectButton.setFont(new Font("plain", Font.BOLD, 13));
            unitScreenSelectButton.setFont(new Font("plain", Font.BOLD, 13));
            structureScreenSelectButton.setFont(new Font("plain", Font.BOLD, 13));
            optionsScreenSelectButton.setFont(new Font("plain", Font.BOLD, 13));
            mapScreenSelectButton.setFont(new Font("plain", Font.BOLD, 13));

            this.add( mainScreenSelectButton );
            this.add( unitScreenSelectButton );
            this.add( structureScreenSelectButton );
            this.add( mapScreenSelectButton );
            this.add( optionsScreenSelectButton) ;

           // this.setForeground( new Color(0xAAffccf8) );
            this.setBackground(new Color(0xffCABD80));
            this.setOpaque(true);
       }

       public JButton getMainScreenButton() { return mainScreenSelectButton; }
       public JButton getUnitScreenButton() {
            return unitScreenSelectButton;
       }
       public JButton getStructureScreenButton() {
            return structureScreenSelectButton;
       }
       public JButton getOptionsScreenSelectButton() {return optionsScreenSelectButton;}
       public JButton getMapScreenSelectButton() {return mapScreenSelectButton;}

}
