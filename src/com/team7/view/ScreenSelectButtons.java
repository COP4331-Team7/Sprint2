package com.team7.view;

import javax.swing.*;

public class ScreenSelectButtons extends JPanel {

        private JButton mainScreenSelectButton;
        private JButton unitScreenSelectButton;
        private JButton structureScreenSelectButton;
        private JButton optionsScreenSelectButton;

        public ScreenSelectButtons() {
            mainScreenSelectButton      = new JButton("MAIN SCREEN");
            unitScreenSelectButton      = new JButton("UNIT OVERVIEW");
            structureScreenSelectButton = new JButton("STRUCTURE OVERVIEW");
            optionsScreenSelectButton = new JButton("OPTIONS");

            this.add( mainScreenSelectButton );
            this.add( unitScreenSelectButton );
            this.add( structureScreenSelectButton );
            this.add( optionsScreenSelectButton) ;
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
