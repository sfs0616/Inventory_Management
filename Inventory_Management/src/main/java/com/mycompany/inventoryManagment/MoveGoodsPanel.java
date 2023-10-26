/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.inventoryManagment;

import java.awt.BorderLayout;
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

/**
 *
 * @author Avraam
 */
public class MoveGoodsPanel extends JPanel implements Observer {
    private JTextField[] textFields;
    private int cols = 2;
    private String[] columnLabels = {"STOCK CODE", "UNITS TO BE MOVED (KG OR ITEM)"};
    private String[] data;
    private JButton moveGoodsButton;
    private JButton goBack;
    private InventoryModel model;

    public String[] getData() {
        return data;
    }

    public MoveGoodsPanel(InventoryModel model) {
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

        // Add labels
        for (int j = 0; j < cols; j++) {
            JLabel label = new JLabel(columnLabels[j], SwingConstants.CENTER);
            labelPanel.add(label);
        }

        this.setActionCommands();

        // Add text fields with the width of the longest label
        for (int j = 0; j < cols; j++) {
            textFields[j] = new JTextField();
            textFields[j].setPreferredSize(new Dimension(maxWidth, textFields[j].getPreferredSize().height));
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
    }

    public void submitData() {
        for (int j = 0; j < cols; j++) {
            data[j] = textFields[j].getText();
            System.out.println("Data at column " + j + " = " + data[j]);
        }
    }

    public void addMoveGoodsButtonListener(ActionListener listener) {
        Exception e = new Exception("addMoveGoodsButtonListener called");
        e.printStackTrace();
        System.out.println("ActionListeners added");
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
        JOptionPane.showMessageDialog(this, "Goods Moved");
    }

    public void errorCheckStockNumber() {
        JOptionPane.showMessageDialog(this, "Error Check stock number");
    }

    public void errorCheckQuantitiesInExistingInventory() {
        JOptionPane.showMessageDialog(this, "Error Check Quantities in Existing Inventory");
    }

    @Override
    public void update(Observable o, Object arg) {
        
    }
}
