package com.team7.view.OptionsScreen;

import com.team7.view.MainScreen.MainViewImage;
import com.team7.view.MainScreen.MainViewInfo;
import com.team7.view.ScreenSelectButtons;

import javax.imageio.plugins.jpeg.JPEGHuffmanTable;
import javax.swing.*;
import javax.swing.plaf.DimensionUIResource;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Set;

/**
 * Used to display configurable controls
 */
public class OptionsScreen extends JPanel{

    private JButton resetControlsButton = new JButton("Reset Controls");
    private JButton changeControlButton = new JButton("Change selected control");

    private JList<String> controlsList = new JList<>();
    private DefaultListModel<String> defaultListModel = new DefaultListModel<>();
    private JScrollPane jScrollPane = new JScrollPane(controlsList);

    private JPanel listPanel = new JPanel(new BorderLayout());


    private JPanel buttonPanel = new JPanel(new BorderLayout());

    private JLabel label = new JLabel("Controls:");

    private ScreenSelectButtons screenSelectBtns = null;

    public JSlider getS1() {
        return s1;
    }

    public JSlider getS2() {
        return s2;
    }

    private JSlider s1 = null;
    private JSlider s2 = null;

    public OptionsScreen() {

        JPanel temp = new JPanel(new BorderLayout() );
        screenSelectBtns = new ScreenSelectButtons();

        controlsList.setModel(defaultListModel);
        listPanel.add(jScrollPane);

        buttonPanel.add(resetControlsButton, BorderLayout.EAST);
        buttonPanel.add(changeControlButton, BorderLayout.WEST);

        temp.add(screenSelectBtns, BorderLayout.NORTH);
        temp.add(label, BorderLayout.SOUTH);

        listPanel.add(temp, BorderLayout.NORTH);

        JPanel sliderHolder = new JPanel(new GridLayout(0,1));
        JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();
        JLabel l2 = new JLabel("MAIN SCREEN SCROLL SPEED ");
        p1.add(l2, BorderLayout.EAST);
        s1 = new JSlider(0, 20, 10);
        l2.setForeground(new Color(0xFF00AAFF));
        l2.setFont( new Font(  "Serif", Font.BOLD, 18));
        s1.setMinorTickSpacing(1);
        s1.setPaintTicks(true);
        s1.setPaintLabels(true);
        s1.setSnapToTicks(true);
        s1.setMajorTickSpacing(5);
        p1.add(s1, BorderLayout.WEST);
        JLabel l1 = new JLabel("UNIT MOVEMENT SPEED          ");
        l1.setForeground(new Color(0xFF00AAFF));
        l1.setFont( new Font(  "Serif", Font.BOLD, 18));
        p2.add(l1, BorderLayout.EAST);
        s2 = new JSlider(0, 20, 10);
        s2.setMinorTickSpacing(1);
        s2.setPaintTicks(true);
        s2.setSnapToTicks(true);
        s2.setPaintLabels(true);
        s2.setMajorTickSpacing(5);
        p2.add(s2, BorderLayout.WEST);
        sliderHolder.add(p1);
        sliderHolder.add(p2);
        listPanel.add(sliderHolder, BorderLayout.SOUTH);
        this.setLayout(new BorderLayout());
        this.add(listPanel, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);

        this.repaint();

        controlsList.setFont(new Font("Serif", Font.BOLD, 50));
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




}
