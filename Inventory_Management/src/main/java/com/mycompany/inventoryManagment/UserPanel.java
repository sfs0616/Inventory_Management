/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.inventoryManagment;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

/**
 *
 * @author Avraam
 */
public class UserPanel extends JPanel {

    InventoryModel model;
    private JButton loginButton;
    private JTextField textField;

    private static final Color BACKGROUND_COLOR = new Color(60, 63, 65);
    private static final Color FOREGROUND_COLOR = new Color(187, 187, 187);
    private static final Color TEXTFIELD_BACKGROUND_COLOR = new Color(69, 73, 74);
    private static final Color BUTTON_BACKGROUND_COLOR = new Color(77, 80, 82);

    public UserPanel(InventoryModel model) {
        setBackground(BACKGROUND_COLOR);
        
        
        this.model = model;

        setLayout(new GridBagLayout());
        loginButton = new JButton("Login");
        textField = new JTextField("Enter UserName Here");
        loginButton.setBackground(BUTTON_BACKGROUND_COLOR);
        loginButton.setForeground(FOREGROUND_COLOR);
        
        textField.setBackground(TEXTFIELD_BACKGROUND_COLOR);
        textField.setForeground(FOREGROUND_COLOR);
        textField.setCaretColor(FOREGROUND_COLOR); // To 

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);

        add(loginButton);
        gbc.gridy = 1;
        add(textField);

        setActionCommands();

    }

    public void setActionCommands() {
        loginButton.setActionCommand("Login");
    }

    public String getUsername() {
        return textField.getText();
    }

    public void addloginButtonListener(ActionListener listener) {
        loginButton.addActionListener(listener);

    }

    public void addtextFieldActionListener(ActionListener listener) {
        textField.addActionListener(listener);
    }

    public void errorUserNameMessage() {
         UIManager.put("OptionPane.background", BACKGROUND_COLOR);
        UIManager.put("Panel.background", BACKGROUND_COLOR);
        UIManager.put("OptionPane.messageForeground", FOREGROUND_COLOR);
        JOptionPane.showMessageDialog(this, "Please enter appropriate username");
    }

}
