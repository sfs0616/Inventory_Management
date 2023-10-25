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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author Avraam
 */
public class AddCartonizedGoodsPanel extends JPanel{

    private JTextField[] textFields;
    
    private int cols = 7;
    private String[] columnLabels = {"STOCK CODE", "PRODUCT DESCRIPTION", "STORAGE TYPE", "WAREHOUSE BAY NUM", "SUPERMARKET BAY NUM", "CURRENT GOODS TOTAL", "CURRENT TOTAL ITEMS SHELF"};
    private InventoryModel model;
    private String[] data;
    private JButton submitButton;
    private JButton goBack;

    public String[] getData() {
        return data;
    }
    
    
    

    public AddCartonizedGoodsPanel(InventoryModel model) {
        this.model = model;
        setLayout(new BorderLayout());

        JPanel labelPanel = new JPanel(new GridLayout(1, cols));
        JPanel fieldPanel = new JPanel(new GridLayout(1, cols));
        
        textFields = new JTextField[cols];
        submitButton = new JButton("Submit Item");
        goBack = new JButton("Go Back to Inventory");

        // Setting button dimensions
        goBack.setPreferredSize(new Dimension(200, 50));
        submitButton.setPreferredSize(new Dimension(200, 50));
        
        // Add labels
    for (int j = 0; j < cols; j++) {
        JLabel label = new JLabel(columnLabels[j], SwingConstants.CENTER);
        labelPanel.add(label);
    }

    this.setActionCommands();

    // Add text fields
    for (int j = 0; j < cols; j++) {
        textFields[j] = new JTextField();
        textFields[j].setPreferredSize(new Dimension(textFields[j].getPreferredSize().width, 50));  // Set height of text field to 50 pixels
        fieldPanel.add(textFields[j]);
    }

    JPanel inputPanel = new JPanel(new BorderLayout());
    inputPanel.add(labelPanel, BorderLayout.NORTH);
    inputPanel.add(fieldPanel, BorderLayout.CENTER);

    JPanel centerPanel = new JPanel(new GridBagLayout()); // A new panel to center in Y dimension
    centerPanel.add(inputPanel);

    JPanel southPanel = new JPanel();
    southPanel.add(submitButton);

    JPanel northRightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    northRightPanel.add(goBack);

    add(centerPanel, BorderLayout.CENTER); // centering the input panel
    add(southPanel, BorderLayout.SOUTH);
    add(northRightPanel, BorderLayout.NORTH);
    }
    

    // Method to handle the data submission
    public void submitData() {
        for (int j = 0; j < cols; j++) {
            data[j] = textFields[j].getText();
            System.out.println("Data at column " + j + " = " + data);
        }
    }
    
     public void addSubmitButtonListener(ActionListener listener) {
        submitButton.addActionListener(listener);
    }
     
     public void addGoBackButtonActionListener(ActionListener listener){
         goBack.addActionListener(listener);
     }
     
    
    public void setActionCommands() {
        submitButton.setActionCommand("SubmitCartonGoods");
        goBack.setActionCommand("GoBack");
    }

    
}
