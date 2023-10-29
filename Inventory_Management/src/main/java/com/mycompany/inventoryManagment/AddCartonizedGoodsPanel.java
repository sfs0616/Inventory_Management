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
import java.util.Observable;
import java.util.Observer;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

/**I used chat gpt to help me format the location of buttons and set colour schemes for this panel. 
 * The AddCartonizedGoodsPanel class represents a panel for adding cartonized goods into the inventory.
 * This panel provides input fields for the user to fill out details of the goods, along with buttons
 * to submit the data or return to the inventory page.
 *
 * @author Avraam
 */
public class AddCartonizedGoodsPanel extends JPanel {

    // Defining constant colors for UI components for consistency and ease of changes
    private static final Color BACKGROUND_COLOR = new Color(60, 63, 65);
    private static final Color FOREGROUND_COLOR = new Color(187, 187, 187);
    private static final Color TEXTFIELD_BACKGROUND_COLOR = new Color(69, 73, 74);
    private static final Color BUTTON_BACKGROUND_COLOR = new Color(77, 80, 82);
    private static final Color LABEL_FOREGROUND_COLOR = FOREGROUND_COLOR;

    // Array to hold the text fields for user inputs
    private JTextField[] textFields;

    // Number of columns and text fields in the panel
    private int cols = 7;
    // Labels for the columns
    private String[] columnLabels = {"STOCK CODE", "PRODUCT DESCRIPTION", "STORAGE TYPE 'R,E,F,C'", "WAREHOUSE BAY NUM", "SUPERMARKET BAY NUM", "CURRENT WAREHOUSE ITEMS TOTAL", "CURRENT TOTAL ITEMS SHELF"};

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
     * Constructor for the AddCartonizedGoodsPanel class.
     * Initializes the UI components and sets up the layout.
     */
    public AddCartonizedGoodsPanel() {
        // Initialize the data array
        data = new String[cols];
        // Set the layout manager for this panel
        setLayout(new BorderLayout());

        // Create panels for labels, text fields, and buttons
        JPanel labelPanel = new JPanel(new GridLayout(1, cols));
        JPanel fieldPanel = new JPanel(new GridLayout(1, cols));

        // Initialize the text fields array and the buttons
        textFields = new JTextField[cols];
        submitButton = new JButton("Submit Item");
        goBack = new JButton("Go Back to Inventory");

        // Set dimensions for buttons
        goBack.setPreferredSize(new Dimension(200, 50));
        submitButton.setPreferredSize(new Dimension(200, 50));

        // Add column labels to the label panel
        for (int j = 0; j < cols; j++) {
            JLabel label = new JLabel(columnLabels[j], SwingConstants.CENTER);
            label.setForeground(LABEL_FOREGROUND_COLOR);
            labelPanel.add(label);
        }

        // Set action commands for buttons to identify action events later
        this.setActionCommands();

        // Add text fields to the field panel
        for (int j = 0; j < cols; j++) {
            textFields[j] = new JTextField();
            textFields[j].setPreferredSize(new Dimension(textFields[j].getPreferredSize().width, 50));  // Set height of text field to 50 pixels
            textFields[j].setBackground(TEXTFIELD_BACKGROUND_COLOR);
            textFields[j].setForeground(FOREGROUND_COLOR);
            textFields[j].setCaretColor(FOREGROUND_COLOR);  // Set text caret color
            fieldPanel.add(textFields[j]);
        }

        // Set colors for buttons
        submitButton.setBackground(BUTTON_BACKGROUND_COLOR);
        submitButton.setForeground(FOREGROUND_COLOR);

        goBack.setBackground(BUTTON_BACKGROUND_COLOR);
        goBack.setForeground(FOREGROUND_COLOR);

        // Panel for holding labels and text fields
        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.add(labelPanel, BorderLayout.NORTH);
        inputPanel.add(fieldPanel, BorderLayout.CENTER);

        // Panel to center the input panel in Y dimension
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.add(inputPanel);

        // Panel for submit button at the bottom
        JPanel southPanel = new JPanel();
        southPanel.add(submitButton);

        // Panel for go back button at the top right
        JPanel northRightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        northRightPanel.add(goBack);

        // Add panels to the main panel
        add(centerPanel, BorderLayout.CENTER);
        add(southPanel, BorderLayout.SOUTH);
        add(northRightPanel, BorderLayout.NORTH);
    }

    /**
     * Submits the data from the text fields to the data array and clears the text fields.
     */
    public void submitData() {
        for (int j = 0; j < cols; j++) {
            data[j] = textFields[j].getText();
            System.out.println("Data at column " + j + " = " + data[j]);
            textFields[j].setText("");
        }
    }

    /**
     * Adds an action listener for the submit button.
     * 
     * @param listener The ActionListener to be added.
     */
    public void addSubmitButtonListener(ActionListener listener) {
        submitButton.addActionListener(listener);
    }

    /**
     * Adds an action listener for the go back button.
     * 
     * @param listener The ActionListener to be added.
     */
    public void addGoBackButtonActionListener(ActionListener listener) {
        goBack.addActionListener(listener);
    }

    /**
     * Sets the action commands for buttons to identify action events.
     */
    public void setActionCommands() {
        submitButton.setActionCommand("SubmitCartonGoods");
        goBack.setActionCommand("GoBack");
    }

    /**
     * Shows a message dialog with an error message about invalid storage type.
     */
    public void invalidStorageCartonMessage() {
        UIManager.put("OptionPane.background", BACKGROUND_COLOR);
        UIManager.put("Panel.background", BACKGROUND_COLOR);
        UIManager.put("OptionPane.messageForeground", FOREGROUND_COLOR);
        JOptionPane.showMessageDialog(this, "Error, Invalid storage type. Please enter from one of the following character types: \n 'E':Flammable, 'C':Refrigerated, 'F': Frozen, 'R': Room temperature");
    }

    /**
     * Shows a message dialog confirming that goods were added to the inventory.
     */
    public void cartonGoodsAddedMessage() {
        UIManager.put("OptionPane.background", BACKGROUND_COLOR);
        UIManager.put("Panel.background", BACKGROUND_COLOR);
        UIManager.put("OptionPane.messageForeground", FOREGROUND_COLOR);
        JOptionPane.showMessageDialog(this, "Goods Added to inventory. Check inventory page.");
    }

    /**
     * Shows a message dialog with an error message about invalid fields.
     */
    public void invalidFields() {
        UIManager.put("OptionPane.background", BACKGROUND_COLOR);
        UIManager.put("Panel.background", BACKGROUND_COLOR);
        UIManager.put("OptionPane.messageForeground", FOREGROUND_COLOR);
        JOptionPane.showMessageDialog(this, "Invalid fields. Please make sure all fields have values.");
    }

    /**
     * Shows a message dialog with an error message about an already existing primary key.
     */
    public void primaryKeyAlreadyExists() {
        UIManager.put("OptionPane.background", BACKGROUND_COLOR);
        UIManager.put("Panel.background", BACKGROUND_COLOR);
        UIManager.put("OptionPane.messageForeground", FOREGROUND_COLOR);
        JOptionPane.showMessageDialog(this, "Stock code already exists in lists. Please choose a different stock code.");
        System.out.println("Invalid stock code message displayed");
    }
}
