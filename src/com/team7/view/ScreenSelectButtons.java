package com.team7.view;

import javax.swing.*;
import java.awt.*;

public class ScreenSelectButtons extends JPanel {

        private JButton mainScreenSelectButton;
        private JButton unitScreenSelectButton;
        private JButton structureScreenSelectButton;
        private JButton optionsScreenSelectButton;

        public ScreenSelectButtons() {
            mainScreenSelectButton      = new JButton("MAIN SCREEN");
            unitScreenSelectButton      = new JButton("UNIT OVERVIEW");
            structureScreenSelectButton = new JButton("STRUCTURE OVERVIEW");
            optionsScreenSelectButton = new JButton("CONTROLS");

            this.add( mainScreenSelectButton );
            this.add( unitScreenSelectButton );
            this.add( structureScreenSelectButton );
            this.add( optionsScreenSelectButton) ;

            this.setForeground( new Color(0xAAffccf8) );
       }

       public JButton getMainScreenButton() { return mainScreenSelectButton; }
       public JButton getUnitScreenButton() {
            return unitScreenSelectButton;
       }
       public JButton getStructureScreenButton() {
            return structureScreenSelectButton;
       }
    public JButton getOptionsScreenSelectButton() {return optionsScreenSelectButton;}

}
