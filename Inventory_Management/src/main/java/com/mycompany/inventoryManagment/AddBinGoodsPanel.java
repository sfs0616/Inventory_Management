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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

/**I used chat gpt to help me format the location of buttons and set colour schemes for this panel. 
 * This class represents a panel in a GUI application for adding goods to a bin.
 * It extends JPanel, allowing it to be added to a JFrame or another container.
 * The panel includes input fields for the user to enter details of the goods,
 * as well as buttons to submit the data or go back to the inventory page.
 *
 * @author Avraam
 */
public class AddBinGoodsPanel extends JPanel {

    // Define constant colors used for the UI components
    private static final Color BACKGROUND_COLOR = new Color(60, 63, 65);
    private static final Color FOREGROUND_COLOR = new Color(187, 187, 187);
    private static final Color TEXTFIELD_BACKGROUND_COLOR = new Color(69, 73, 74);
    private static final Color BUTTON_BACKGROUND_COLOR = new Color(77, 80, 82);
    private static final Color LABEL_BACKGROUND_COLOR = new Color(60, 63, 65);
    private static final Color LABEL_FOREGROUND_COLOR = new Color(187, 187, 187);

    // Array to store the text fields for user input
    private JTextField[] textFields;
    // Number of columns (and text fields) in the panel
    private int cols = 7;
    // Labels for the columns
    private String[] columnLabels = {"STOCK CODE", "PRODUCT DESCRIPTION", "STORAGE TYPE 'R,E,F,C'", "WAREHOUSE BAY NUM", "SUPERMARKET BAY NUM", "CURRENT KG IN WAREHOUSE BIN", "CURRENT KG ON SHELF"};

    // Array to store the user's input data from the text fields
    private String[] data;
    // Buttons for submitting data and going back to the inventory page
    private JButton submitButton, goBack;

    /**
     * Gets the user's input data.
     * 
     * @return An array of strings containing the data from the text fields.
     */
    public String[] getData() {
        return data;
    }

    /**
     * Constructor for the AddBinGoodsPanel class.
     * Initializes the UI components and sets up the layout.
     */
    public AddBinGoodsPanel() {
        // Initialize the data array
        data = new String[cols];
        // Set the layout manager for this panel
        setLayout(new BorderLayout());

        // Panel for displaying column labels
        JPanel labelPanel = new JPanel(new GridLayout(1, cols));
        labelPanel.setBackground(LABEL_BACKGROUND_COLOR);
        // Panel for the user input text fields
        JPanel fieldPanel = new JPanel(new GridLayout(1, cols));
        fieldPanel.setBackground(BACKGROUND_COLOR);

        // Initialize the text fields
        textFields = new JTextField[cols];
        // Buttons for submitting data and going back to the inventory page
        submitButton = new JButton("Submit Item");
        goBack = new JButton("Go Back to Inventory");

        // Add labels to the label panel
        for (int j = 0; j < cols; j++) {
            JLabel label = new JLabel(columnLabels[j], SwingConstants.CENTER);
            label.setForeground(LABEL_FOREGROUND_COLOR);
            labelPanel.add(label);
        }

        // Set action commands for buttons
        this.setActionCommands();

        // Add text fields to the field panel
        for (int j = 0; j < cols; j++) {
            textFields[j] = new JTextField();
            textFields[j].setPreferredSize(new Dimension(textFields[j].getPreferredSize().width, 50));  // Set height to 50 pixels
            textFields[j].setBackground(TEXTFIELD_BACKGROUND_COLOR);
            textFields[j].setForeground(FOREGROUND_COLOR);
            fieldPanel.add(textFields[j]);
        }

        // Panel to hold the label and field panels
        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.add(labelPanel, BorderLayout.NORTH);
        inputPanel.add(fieldPanel, BorderLayout.CENTER);

        // Center the input panel in the Y dimension
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.add(inputPanel);

        // Panel at the bottom for the submit button
        JPanel southPanel = new JPanel();
        southPanel.setBackground(BACKGROUND_COLOR);
        southPanel.add(submitButton);
        submitButton.setBackground(BUTTON_BACKGROUND_COLOR);
        submitButton.setForeground(FOREGROUND_COLOR);

        // Panel at the top right for the go back button
        JPanel northRightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        northRightPanel.setBackground(BACKGROUND_COLOR);
        northRightPanel.add(goBack);
        goBack.setBackground(BUTTON_BACKGROUND_COLOR);
        goBack.setForeground(FOREGROUND_COLOR);

        // Add panels to the main panel
        add(centerPanel, BorderLayout.CENTER);
        add(southPanel, BorderLayout.SOUTH);
        add(northRightPanel, BorderLayout.NORTH);
    }

    /**
     * Submits the data entered by the user.
     * The data from the text fields are stored in the data array,
     * and the text fields are cleared for the next entry.
     */
    public void submitData() {
        for (int j = 0; j < cols; j++) {
            data[j] = textFields[j].getText();
            System.out.println("Data at column " + j + " = " + data[j]);
            textFields[j].setText("");
        }
    }

    /**
     * Adds an ActionListener to the submit button.
     * 
     * @param listener The ActionListener to be added.
     */
    public void addSubmitBinGoodsButtonListener(ActionListener listener) {
        submitButton.addActionListener(listener);
    }

    /**
     * Adds an ActionListener to the go back button.
     * 
     * @param listener The ActionListener to be added.
     */
    public void addGoBackBinButtonActionListener(ActionListener listener) {
        goBack.addActionListener(listener);
    }

    /**
     * Sets the action commands for the buttons.
     * This helps to identify the source of an action event in the ActionListener.
     */
    public void setActionCommands() {
        submitButton.setActionCommand("SubmitBinGoods");
        goBack.setActionCommand("GoBack");
    }

    /**
     * Displays a message dialog notifying the user of an invalid storage type entry.
     */
    public void invalidStorageMessage() {
        customizeOptionPane();
        JOptionPane.showMessageDialog(this, "Error, Invalid storage type. Please enter from one of the following character types: \n 'E':Flammable, 'C':Refrigerated, 'F': Frozen, 'R': Room temperature");
    }

    /**
     * Displays a message dialog notifying the user that the goods have been added to the inventory.
     */
    public void goodsAddedMessage() {
        customizeOptionPane();
        JOptionPane.showMessageDialog(this, "Goods Added to inventory. Check inventory page.");
    }

    /**
     * Displays a message dialog notifying the user of invalid fields entry.
     */
    public void invalidFields() {
        customizeOptionPane();
        JOptionPane.showMessageDialog(this, "Invalid fields. Please make sure all fields have values.");
        System.out.println("Invalid storage message displayed");
    }

    /**
     * Displays a message dialog notifying the user that the stock code already exists in the inventory.
     */
    public void primaryKeyAlreadyExists() {
        customizeOptionPane();
        JOptionPane.showMessageDialog(this, "Stock code already exists in lists. Please choose a different stock code.");
        System.out.println("Invalid stock code message displayed");
    }

    /**
     * Customizes the appearance of the JOptionPane to match the panel's theme.
     */
    private void customizeOptionPane() {
        UIManager.put("OptionPane.background", BACKGROUND_COLOR);
        UIManager.put("Panel.background", BACKGROUND_COLOR);
        UIManager.put("OptionPane.messageForeground", FOREGROUND_COLOR);
    }
}
