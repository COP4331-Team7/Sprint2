package com.team7.view.OptionsScreen;

import com.team7.view.ScreenSelectButtons;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Set;

/**
 * Used to display configurable controls
 */
public class OptionsScreen extends JPanel{

    private JButton resetControlsButton = new JButton("Reset controls");
    private JButton changeControlButton = new JButton("Update selected key");

    private JList<String> controlsList = new JList<>();
    private DefaultListModel<String> defaultListModel = new DefaultListModel<>();
    private JScrollPane jScrollPane = new JScrollPane(controlsList);

    private JPanel listPanel = new JPanel(new BorderLayout());
    private JPanel buttonPanel = new JPanel(new BorderLayout());
    private JLabel label = new JLabel("Key Bindings:");
    private ScreenSelectButtons screenSelectBtns = null;

    private JSlider s1 = null;
    private JSlider s2 = null;
    private JSlider s3 = null;
    private JToggleButton showResourceButton = null;
    private JToggleButton showUnitsButton = null;
    private JButton homeButton;
    private JPanel bPanel;

    private JButton futureGameButton = null;

    public OptionsScreen() {
      //  resetControlsButton.setForeground( new Color(0xCD3700));
        resetControlsButton.setForeground( new Color(0xff000000));
        resetControlsButton.setFont(new Font("plain", Font.BOLD, 25));


        changeControlButton.setForeground( new Color(0xff000000));
        changeControlButton.setFont(new Font("plain", Font.BOLD, 30));

        label.setForeground( new Color(0xff000000));
        label.setBackground( new Color(0xffF5F5DC) );
        label.setOpaque(true);
        label.setFont(new Font("plain", Font.BOLD, 27));

        homeButton = new JButton("Home");
        homeButton.setForeground( new Color(0xff000000));
        homeButton.setFont(new Font("Comic Sans", Font.BOLD, 27));

        bPanel = new JPanel(new BorderLayout() );
        screenSelectBtns = new ScreenSelectButtons();

        controlsList.setModel(defaultListModel);
        listPanel.add(jScrollPane);
        buttonPanel.add(resetControlsButton, BorderLayout.EAST);
        showScreenSelectBtns();
        bPanel.add(label, BorderLayout.SOUTH);
        listPanel.add(bPanel, BorderLayout.NORTH);

        JPanel sliderHolder = new JPanel(new GridLayout(0,1));
        JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();
        JPanel p3 = new JPanel();
        JLabel l2 = new JLabel("MAIN SCREEN SCROLL SPEED   ");
        p1.add(l2, BorderLayout.EAST);
        s1 = new JSlider(0, 20, 10);
        l2.setFont( new Font(  "plain", Font.BOLD, 18));
        s1.setMinorTickSpacing(1);
        s1.setPaintTicks(true);
        s1.setSnapToTicks(true);
        s1.setMajorTickSpacing(5);
        s1.setPreferredSize(new Dimension(460, 50));
        p1.add(s1, BorderLayout.WEST);
        JLabel l1 = new JLabel("UNIT MOVEMENT SPEED          ");
        l1.setFont( new Font(  "plain", Font.BOLD, 18));
        p2.add(l1, BorderLayout.EAST);
        s2 = new JSlider(0, 20, 10);
        s2.setMinorTickSpacing(1);
        s2.setPaintTicks(true);
        s2.setSnapToTicks(true);
        s2.setMajorTickSpacing(5);
        s2.setPreferredSize(new Dimension(460, 50));
        p2.add(s2, BorderLayout.WEST);
        JLabel l3 = new JLabel("END TURN FOCUS SPEED          ");
        l3.setFont( new Font(  "plain", Font.BOLD, 18));
        s3 = new JSlider(0, 30, 20);
        s3.setMinorTickSpacing(1);
        s3.setPaintTicks(true);
        s3.setSnapToTicks(true);
        s3.setMajorTickSpacing(5);
        s3.setPreferredSize(new Dimension(460, 50));
        p3.add(l3, BorderLayout.WEST);
        p3.add(s3, BorderLayout.EAST);
        JPanel temp2 = new JPanel();
        temp2.add(changeControlButton, BorderLayout.EAST);
        temp2.add(resetControlsButton, BorderLayout.WEST);
        temp2.setBackground( new Color(0xffCABD80) );
        temp2.setOpaque(true);
        p1.setBackground( new Color(0xffF5F5DC) );
        p1.setOpaque(true);

        p2.setBackground( new Color(0xffF5F5DC) );
        p2.setOpaque(true);

        p3.setBackground( new Color(0xffF5F5DC) );
        p3.setOpaque(true);

        showResourceButton = new JToggleButton("Resource display [OFF]");
        showResourceButton.setForeground( new Color(0xCD3700) );
        showResourceButton.setFont(new Font("plain", Font.BOLD, 20));

        showUnitsButton = new JToggleButton("Unit display [ON]");
        showUnitsButton.setForeground( new Color(0, 175, 0, 255) );
        showUnitsButton.setFont(new Font("plain", Font.BOLD, 20));

        JPanel btns = new JPanel( new GridLayout(0, 4) );
        btns.add(changeControlButton, BorderLayout.WEST);
        changeControlButton.setForeground( new Color(0xff000000) );
        changeControlButton.setFont(new Font("plain", Font.BOLD, 20));
        btns.add( showUnitsButton );
        btns.add( showResourceButton );

        futureGameButton = new JButton("fast forward");
        futureGameButton.setForeground( new Color(0xff000000) );
        futureGameButton.setFont(new Font("plain", Font.BOLD, 20));
        futureGameButton.setBackground(new Color(0xffCABD80));
        futureGameButton.setOpaque(true);
        btns.add( futureGameButton );

        btns.setBorder(BorderFactory.createLineBorder(new Color(0xffCABD80), 3));
        btns.setBackground(new Color(0xffCABD80));
        btns.setOpaque(true);

        sliderHolder.add( btns );
        sliderHolder.add(p1);
        sliderHolder.add(p2);
        sliderHolder.add(p3);
        sliderHolder.add(temp2);

        listPanel.add(sliderHolder, BorderLayout.SOUTH);
        this.setLayout(new BorderLayout());
        this.add(listPanel, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);

        this.repaint();
        controlsList.setFont(new Font("plain", Font.BOLD, 40));
        this.setBorder(BorderFactory.createLineBorder(new Color(0xffCABD80), 3));
    }

    //adds text to the defaultListModel
    public void setModel(HashMap<String, String> controls){
        defaultListModel.clear();
        Set<String> allKeys = controls.keySet();
        for (String key : allKeys){
            defaultListModel.addElement(key + ": " + controls.get(key) );
        }
        this.repaint();
    }

    public int getUnitSpeed() {
        if(s2.getValue() == s2.getMaximum())
            return 1;
        else
            return Math.abs(s2.getMaximum() - s2.getValue());
    }
    public int getScrollSpeed() {
        if(s1.getValue() == s1.getMaximum())
            return 1;
        else
            return Math.abs(s1.getMaximum() - s1.getValue());
    }
    public int getFocusSpeed() {
        if(s3.getValue() == s3.getMaximum() )
            return 10;
        else if ( (s3.getValue() == s3.getMinimum()) ) {
            return 0;
        }
        else
            return 7 * Math.abs(s3.getMaximum() - s3.getValue());
    }

    public JButton getResetControlsButton() {
        return resetControlsButton;
    }

    public JButton getChangeControlButton() {
        return changeControlButton;
    }

    public JList<String> getControlsList() {
        return controlsList;
    }

    public JButton getMainScreenButton() {
        return screenSelectBtns.getMainScreenButton();
    }
    public JButton getUnitScreenButton() {
        return screenSelectBtns.getUnitScreenButton();
    }
    public JButton getStructureScreen() {
        return screenSelectBtns.getStructureScreenButton();
    }
    public JButton getHomeScreenButton() {
        return homeButton;
    }
    public JButton getMapScreenSelectButton() {
        return screenSelectBtns.getMapScreenSelectButton();
    }
    public JButton getTechnologyScreenButton(){
        return screenSelectBtns.getTechnologyScreenSelectButton();
    }


    public void setSlidersToDefault() {
        s1.setValue(10);
        s2.setValue(10);
        s3.setValue(15);
    }

    public JToggleButton getShowResourceButton() {
        return showResourceButton;
    }
    public JToggleButton getShowUnitsButton() {
        return showUnitsButton;
    }

    public JButton getFutureGameButton() {
        return futureGameButton;
    }

    public void showHomeScreenOnly() {
        bPanel.remove(screenSelectBtns);
        bPanel.add(homeButton);
        repaint();
    }
    public void showScreenSelectBtns() {
        bPanel.remove(homeButton);
        bPanel.add(screenSelectBtns);
        repaint();
    }

    public JSlider getS1() {
        return s1;
    }


    public JSlider getS3() {
        return s3;
    }

    public void setS3(JSlider s3) {
        this.s3 = s3;
    }

    public void resetS3Value(){
        s3.setValue(s3.getMinimum());
    }
}
