/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.inventoryManagment;

import java.awt.CardLayout;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Avraam
 */
public class InventoryView extends JFrame{
    
    InventoryModel model;
    private JPanel cardPanel;
    private CardLayout cardLayout;
    private UserPanel userPanel;
    private InventoryPanel inventoryPanel;
    private AddCartonizedGoodsPanel addCartonGoodsPanel;
    private AddBinGoodsPanel addBinGoodsPanel;

   

    public InventoryModel getModel() {
        return model;
    }

    public UserPanel getUserPanel() {
        return userPanel;
    }

    public InventoryPanel getInventoryPanel() {
        return inventoryPanel;
    }

    public JPanel getCardPanel() {
        return cardPanel;
    }

    public CardLayout getCardLayout() {
        return cardLayout;
    }

    public AddCartonizedGoodsPanel getAddCartonGoodsPanel() {
        return addCartonGoodsPanel;
    }

    public AddBinGoodsPanel getAddBinGoodsPanel() {
        return addBinGoodsPanel;
    }
    
   
    
    
    
    public InventoryView(InventoryModel model){
        this.model = model;
         setTitle("Inventory Management System");
        setExtendedState(JFrame.MAXIMIZED_BOTH);  // Maximize the frame
        //setUndecorated(true);  // Remove the window decorations (optional)
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        
        userPanel = new UserPanel(model);
        cardPanel.add(userPanel, "Log in:");
        
        inventoryPanel = new InventoryPanel(model);
        cardPanel.add(inventoryPanel, "InventoryPanel");
        
        addCartonGoodsPanel = new AddCartonizedGoodsPanel(model);
        cardPanel.add(addCartonGoodsPanel, "AddCartonizedGoods");
        
        addBinGoodsPanel = new AddBinGoodsPanel(model);
        cardPanel.add(addBinGoodsPanel, "AddBinGoods");
        
        cardLayout.show(cardPanel, "UserPanel");
        
        getContentPane().add(cardPanel);
        
        model.addObserver(this.inventoryPanel);
        
    }
    
    public void addController(ActionListener controller){
        userPanel.addloginButtonListener(controller);
        userPanel.addtextFieldActionListener(controller);
        inventoryPanel.addFrozenButtonListener(controller);
        inventoryPanel.addFlammableButtonListener(controller);
        inventoryPanel.addRefrigeratedButtonListener(controller);
        inventoryPanel.addRoomTemperatureButtonListener(controller);
        inventoryPanel.addAddCartonizedGoodsButtonListener(controller);
        inventoryPanel.addChangePalletsButtonListener(controller);
        inventoryPanel.addChangeSupermarketShelvesButtonListener(controller);
        inventoryPanel.addMoveGoodsButtonListener(controller);
        addCartonGoodsPanel.addSubmitButtonListener(controller);
        addCartonGoodsPanel.addGoBackButtonActionListener(controller);
        inventoryPanel.addAddBinGoodsButtonListener(controller);
        addBinGoodsPanel.addSubmitButtonListener(controller);
        addBinGoodsPanel.addGoBackButtonActionListener(controller);
        
    }

    

    

    
    
    
    
}
