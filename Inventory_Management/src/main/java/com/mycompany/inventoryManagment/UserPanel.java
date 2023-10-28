/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.inventoryManagment;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


/**
 *
 * @author Avraam
 */


public class UserPanel extends JPanel {
    InventoryModel model;
    private JButton loginButton;
    private JTextField textField;
 
    public UserPanel(InventoryModel model) {
        this.model = model;
        
        setLayout(new GridBagLayout());
        loginButton = new JButton("Login");
        textField = new JTextField("Enter UserName Here");
        
        GridBagConstraints gbc = new GridBagConstraints();
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10,10,10,10);
        
        add(loginButton);
        gbc.gridy = 1;
        add(textField);
        
        setActionCommands();
        
    }
    
    public void setActionCommands(){
       loginButton.setActionCommand("Login"); 
    }
    
    public String getUsername() {
        return textField.getText();
    }

    
    public void addloginButtonListener(ActionListener listener){
        loginButton.addActionListener(listener);
        
    }
    
    public void addtextFieldActionListener(ActionListener listener){
        textField.addActionListener(listener);
    }

    public void errorUserNameMessage() {
        JOptionPane.showMessageDialog(this, "Please enter appropriate username");
    }
    
}
