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

    private JButton resetControlsButton = new JButton("Reset controls to default");
    private JButton changeControlButton = new JButton("Update selected control");

    private JList<String> controlsList = new JList<>();
    private DefaultListModel<String> defaultListModel = new DefaultListModel<>();
    private JScrollPane jScrollPane = new JScrollPane(controlsList);

    private JPanel listPanel = new JPanel(new BorderLayout());


    private JPanel buttonPanel = new JPanel(new BorderLayout());

    private JLabel label = new JLabel("Key Bindings:");


    private ScreenSelectButtons screenSelectBtns = null;

    public JSlider getS1() {
        return s1;
    }

    public JSlider getS2() {
        return s2;
    }
    public JSlider getS3() {
        return s3;
    }

    private JSlider s1 = null;
    private JSlider s2 = null;
    private JSlider s3 = null;


    public OptionsScreen() {

        resetControlsButton.setForeground( new Color(0xFFFF0000));
        resetControlsButton.setFont(new Font("Serif", Font.BOLD, 30));

        changeControlButton.setForeground( new Color(0xFF0FAAF0));
        changeControlButton.setFont(new Font("Serif", Font.BOLD, 30));

        label.setForeground( new Color(0xFF0FAAF0));
        label.setFont(new Font("Serif", Font.BOLD, 45));

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
        JPanel p3 = new JPanel();
        JLabel l2 = new JLabel("MAIN SCREEN SCROLL SPEED ");
        p1.add(l2, BorderLayout.EAST);
        s1 = new JSlider(0, 20, 10);
        l2.setForeground(new Color(0xFF00AAFF));
        l2.setFont( new Font(  "Serif", Font.BOLD, 18));
        s1.setMinorTickSpacing(1);
        s1.setPaintTicks(true);
        //s1.setPaintLabels(true);
        s1.setSnapToTicks(true);
        s1.setMajorTickSpacing(5);
        s1.setPreferredSize(new Dimension(400, 50));
        p1.add(s1, BorderLayout.WEST);
        JLabel l1 = new JLabel("UNIT MOVEMENT SPEED          ");
        l1.setForeground(new Color(0xFF00AAFF));
        l1.setFont( new Font(  "Serif", Font.BOLD, 18));
        p2.add(l1, BorderLayout.EAST);
        s2 = new JSlider(0, 20, 10);
        s2.setMinorTickSpacing(1);
        s2.setPaintTicks(true);
        s2.setSnapToTicks(true);
        //s2.setPaintLabels(true);
        s2.setMajorTickSpacing(5);
        s2.setPreferredSize(new Dimension(400, 50));
        p2.add(s2, BorderLayout.WEST);
        JLabel l3 = new JLabel("END TURN FOCUS SPEED          ");
        l3.setForeground(new Color(0xFF00AAFF));
        l3.setFont( new Font(  "Serif", Font.BOLD, 18));
        s3 = new JSlider(0, 20, 15);
        s3.setMinorTickSpacing(1);
        s3.setPaintTicks(true);
        s3.setSnapToTicks(true);
        // s3.setPaintLabels(true);
        s3.setMajorTickSpacing(5);
        s3.setPreferredSize(new Dimension(400, 50));
        JPanel temp1 = new JPanel();
        p3.add(l3, BorderLayout.WEST);
        p3.add(s3, BorderLayout.EAST);
        JPanel temp2 = new JPanel();
        temp2.add(changeControlButton, BorderLayout.EAST);
        temp2.add(resetControlsButton, BorderLayout.WEST);
        sliderHolder.add(p1);
        sliderHolder.add(p2);
        sliderHolder.add(p3);
        sliderHolder.add(temp2);

        listPanel.add(sliderHolder, BorderLayout.SOUTH);
        this.setLayout(new BorderLayout());
        this.add(listPanel, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);

        this.repaint();
        controlsList.setFont(new Font("Serif", Font.BOLD, 45));
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
        if(s3.getValue() == s3.getMaximum())
            return 1 * 10;
        else
            return 20 * Math.abs(s3.getMaximum() - s3.getValue());
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
    public void setSlidersToDefault() {
        s1.setValue(10);
        s2.setValue(10);
        s3.setValue(15);
    }



}
