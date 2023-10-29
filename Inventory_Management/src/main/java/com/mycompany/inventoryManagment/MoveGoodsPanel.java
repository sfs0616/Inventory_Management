/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.inventoryManagment;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

/**
 *
 * @author Avraam
 */
//This class uses the same formatting as the other panel classes. 
public class MoveGoodsPanel extends JPanel implements Observer {

    private JTextField[] textFields;
    private int cols = 2;
    private String[] columnLabels = {"STOCK CODE", "UNITS TO BE MOVED (KG OR ITEM)"};
    private String[] data;
    private JButton moveGoodsButton;
    private JButton goBack;
    private InventoryModel model;

    private static final Color BACKGROUND_COLOR = new Color(60, 63, 65);
    private static final Color FOREGROUND_COLOR = new Color(187, 187, 187);
    private static final Color TEXTFIELD_BACKGROUND_COLOR = new Color(69, 73, 74);
    private static final Color BUTTON_BACKGROUND_COLOR = new Color(77, 80, 82);
    private static final Color LABEL_FOREGROUND_COLOR = FOREGROUND_COLOR;

    public String[] getData() {
        return data;
    }

    public MoveGoodsPanel(InventoryModel model) {
        setBackground(BACKGROUND_COLOR);
        this.model = model;
        model.addObserver(this);
        data = new String[cols];
        setLayout(new BorderLayout());

        JPanel labelPanel = new JPanel(new GridLayout(1, cols));
        JPanel fieldPanel = new JPanel(new GridLayout(1, cols));

        textFields = new JTextField[cols];
        moveGoodsButton = new JButton("Move Goods");
        goBack = new JButton("Go Back to Inventory");

        // Calculate the width of the longest label to set the width of text fields
        int maxWidth = 0;
        for (String labelStr : columnLabels) {
            JLabel tempLabel = new JLabel(labelStr);
            int width = tempLabel.getPreferredSize().width;
            if (width > maxWidth) {
                maxWidth = width;
            }
        }

        for (int j = 0; j < cols; j++) {
            JLabel label = new JLabel(columnLabels[j], SwingConstants.CENTER);
            label.setForeground(LABEL_FOREGROUND_COLOR);
            labelPanel.add(label);
        }

        this.setActionCommands();

        // Add text fields
        for (int j = 0; j < cols; j++) {
            textFields[j] = new JTextField();
            textFields[j].setPreferredSize(new Dimension(maxWidth, textFields[j].getPreferredSize().height));
            textFields[j].setBackground(TEXTFIELD_BACKGROUND_COLOR);
            textFields[j].setForeground(FOREGROUND_COLOR);
            textFields[j].setCaretColor(FOREGROUND_COLOR);
            fieldPanel.add(textFields[j]);
        }

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout());
        inputPanel.add(labelPanel, BorderLayout.NORTH);
        inputPanel.add(fieldPanel, BorderLayout.CENTER);

        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.add(inputPanel);

        JPanel southPanel = new JPanel();
        southPanel.add(moveGoodsButton);

        JPanel northRightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        northRightPanel.add(goBack);

        add(centerPanel, BorderLayout.CENTER);
        add(southPanel, BorderLayout.SOUTH);
        add(northRightPanel, BorderLayout.NORTH);

        moveGoodsButton.setBackground(BUTTON_BACKGROUND_COLOR);
        moveGoodsButton.setForeground(FOREGROUND_COLOR);

        goBack.setBackground(BUTTON_BACKGROUND_COLOR);
        goBack.setForeground(FOREGROUND_COLOR);
    }

    public void submitData() {
        for (int j = 0; j < cols; j++) {
            data[j] = textFields[j].getText();
            System.out.println("Data at column " + j + " = " + data[j]);
            textFields[j].setText("");

        }

    }

    public void addMoveGoodsButtonListener(ActionListener listener) {

        moveGoodsButton.addActionListener(listener);
    }

    public void addGoBackButtonActionListener(ActionListener listener) {
        goBack.addActionListener(listener);
    }

    public void setActionCommands() {

        moveGoodsButton.setActionCommand("Move Goods");
        goBack.setActionCommand("GoBack");
    }

    public void goodsMovedMessage() {
        UIManager.put("OptionPane.background", BACKGROUND_COLOR);
        UIManager.put("Panel.background", BACKGROUND_COLOR);
        UIManager.put("OptionPane.messageForeground", FOREGROUND_COLOR);
        JOptionPane.showMessageDialog(this, "Goods Moved");
    }

    public void errorInvalidFields() {
        UIManager.put("OptionPane.background", BACKGROUND_COLOR);
        UIManager.put("Panel.background", BACKGROUND_COLOR);
        UIManager.put("OptionPane.messageForeground", FOREGROUND_COLOR);
        JOptionPane.showMessageDialog(this, "Pleas enter valid values into fields");
    }

    public void errorCheckQuantitiesInExistingInventory() {
        UIManager.put("OptionPane.background", BACKGROUND_COLOR);
        UIManager.put("Panel.background", BACKGROUND_COLOR);
        UIManager.put("OptionPane.messageForeground", FOREGROUND_COLOR);
        JOptionPane.showMessageDialog(this, "Error Check Quantities in Existing Inventory, make sure values are correct.");
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
