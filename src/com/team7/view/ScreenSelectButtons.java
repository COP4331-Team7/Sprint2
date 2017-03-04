package com.team7.view;

import javax.swing.*;

public class ScreenSelectButtons extends JPanel {

        private JButton mainScreenSelectButton;
        private JButton unitScreenSelectButton;
        private JButton structureScreenSelectButton;

        public ScreenSelectButtons() {
            mainScreenSelectButton      = new JButton("MAIN SCREEN");
            unitScreenSelectButton      = new JButton("UNIT OVERVIEW");
            structureScreenSelectButton = new JButton("STRUCTURE OVERVIEW");

            this.add( mainScreenSelectButton );
            this.add( unitScreenSelectButton );
            this.add( structureScreenSelectButton );
       }

       public JButton getMainScreenButton() { return mainScreenSelectButton; }
       public JButton getUnitScreenButton() {
            return unitScreenSelectButton;
       }
       public JButton getStructureScreenButton() {
            return structureScreenSelectButton;
       }
}
