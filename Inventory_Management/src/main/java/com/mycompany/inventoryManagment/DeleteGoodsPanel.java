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

/**I used chat gpt to help me format the location of buttons and set colour schemes for this panel.
 * The DeleteGoodsPanel class provides a graphical user interface for deleting goods from the inventory.
 * It extends JPanel and implements Observer to observe changes in the InventoryModel.
 * Users can input the stock code and item description of the goods they want to delete.
 */
public class DeleteGoodsPanel extends JPanel implements Observer {

    // Array to hold the text fields for user input
    private JTextField[] textFields;

    // Number of columns and text fields in the panel
    private int cols = 2;
    // Labels for the columns
    private String[] columnLabels = {"STOCK CODE", "Goods Item description"};

    // Array to store the user's input data from the text fields
    private String[] data;

    // Buttons for deleting goods and going back to the inventory page
    private JButton deleteGoodsButton, goBack;

    // Model for inventory management
    private InventoryModel model;

    // Constant colors for UI components
    private static final Color BACKGROUND_COLOR = new Color(60, 63, 65);
    private static final Color FOREGROUND_COLOR = new Color(187, 187, 187);
    private static final Color TEXTFIELD_BACKGROUND_COLOR = new Color(69, 73, 74);
    private static final Color BUTTON_BACKGROUND_COLOR = new Color(77, 80, 82);
    private static final Color LABEL_FOREGROUND_COLOR = FOREGROUND_COLOR;

    /**
     * Gets the user's input data.
     * 
     * @return An array of strings containing the data from the text fields.
     */
    public String[] getData() {
        return data;
    }

    /**
     * Constructor for the DeleteGoodsPanel class.
     * Initializes the UI components and sets up the layout.
     * 
     * @param model The inventory model that this panel will observe.
     */
    public DeleteGoodsPanel(InventoryModel model) {
        // Set the background color of the panel
        setBackground(BACKGROUND_COLOR);
        // Initialize the model and add this panel as an observer
        this.model = model;
        model.addObserver(this);
        // Initialize the data array
        data = new String[cols];
        // Set the layout manager for this panel
        setLayout(new BorderLayout());

        // Create panels for labels, text fields, and buttons
        JPanel labelPanel = new JPanel(new GridLayout(1, cols));
        JPanel fieldPanel = new JPanel(new GridLayout(1, cols));

        // Initialize the text fields array and the buttons
        textFields = new JTextField[cols];
        deleteGoodsButton = new JButton("Delete Goods");
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
            textFields[j].setPreferredSize(new Dimension(maxWidth, textFields[j].getPreferredSize().height));
            textFields[j].setBackground(TEXTFIELD_BACKGROUND_COLOR);
            textFields[j].setForeground(FOREGROUND_COLOR);
            textFields[j].setCaretColor(FOREGROUND_COLOR);
            fieldPanel.add(textFields[j]);
        }

        // Panel for holding labels and text fields
        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.add(labelPanel, BorderLayout.NORTH);
        inputPanel.add(fieldPanel, BorderLayout.CENTER);

        // Panel to center the input panel in Y dimension
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.add(inputPanel);

        // Panel for delete button at the bottom
        JPanel southPanel = new JPanel();
        southPanel.add(deleteGoodsButton);

        // Panel for go back button at the top right
        JPanel northRightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        northRightPanel.add(goBack);

        // Add panels to the main panel
        add(centerPanel, BorderLayout.CENTER);
        add(southPanel, BorderLayout.SOUTH);
        add(northRightPanel, BorderLayout.NORTH);

        // Set the button's colors
        deleteGoodsButton.setBackground(BUTTON_BACKGROUND_COLOR);
        deleteGoodsButton.setForeground(FOREGROUND_COLOR);

        goBack.setBackground(BUTTON_BACKGROUND_COLOR);
        goBack.setForeground(FOREGROUND_COLOR);
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
     * Adds an action listener to the delete goods button.
     * 
     * @param listener The ActionListener to be added.
     */
    public void addDeleteGoodsButtonActionListener(ActionListener listener) {
        deleteGoodsButton.addActionListener(listener);
    }

    /**
     * Adds an action listener to the go back button.
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
        deleteGoodsButton.setActionCommand("DeleteGoods");
        goBack.setActionCommand("GoBack");
    }

    /**
     * Displays a message dialog confirming that goods were deleted.
     */
    public void goodsDeletedMessage() {
        UIManager.put("OptionPane.background", BACKGROUND_COLOR);
        UIManager.put("Panel.background", BACKGROUND_COLOR);
        UIManager.put("OptionPane.messageForeground", FOREGROUND_COLOR);
        JOptionPane.showMessageDialog(this, "Goods Deleted");
    }

    /**
     * Displays a message dialog with an error message about invalid fields.
     */
    public void errorInvalidFields() {
        UIManager.put("OptionPane.background", BACKGROUND_COLOR);
        UIManager.put("Panel.background", BACKGROUND_COLOR);
        UIManager.put("OptionPane.messageForeground", FOREGROUND_COLOR);
        JOptionPane.showMessageDialog(this, "Please enter valid values into fields");
    }

    /**
     * Displays a message dialog with an error message asking user to check quantities in existing inventory.
     */
    public void errorCheckQuantitiesInExistingInventory() {
        UIManager.put("OptionPane.background", BACKGROUND_COLOR);
        UIManager.put("Panel.background", BACKGROUND_COLOR);
        UIManager.put("OptionPane.messageForeground", FOREGROUND_COLOR);
        JOptionPane.showMessageDialog(this, "Error Check Quantities in Existing Inventory, make sure values are correct.");
    }

    /**
     * Method defined by the Observer interface.
     * It is called when the observed object is changed.
     * 
     * @param o the observable object.
     * @param arg an argument passed to the notifyObservers method.
     */
    public void update(Observable o, Object arg) {
        // Currently not implemented
    }
}

