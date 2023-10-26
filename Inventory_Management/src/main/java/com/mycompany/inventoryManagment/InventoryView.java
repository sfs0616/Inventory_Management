/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.inventoryManagment;

import java.awt.CardLayout;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author Avraam
 */
public class InventoryView extends JFrame{
    
    
    private JPanel cardPanel;
    private CardLayout cardLayout;
    private UserPanel userPanel;
    private InventoryPanel inventoryPanel;
    private AddCartonizedGoodsPanel addCartonGoodsPanel;
    private AddBinGoodsPanel addBinGoodsPanel;
    private MoveGoodsPanel moveGoodsPanel;
    private InventoryModel model;
    private DeleteGoodsPanel deleteGoodsPanel;

   

   

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

    public MoveGoodsPanel getMoveGoodsPanel() {
        return moveGoodsPanel;
    }

    public DeleteGoodsPanel getDeleteGoodsPanel() {
        return deleteGoodsPanel;
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
        
        addCartonGoodsPanel = new AddCartonizedGoodsPanel();
        cardPanel.add(addCartonGoodsPanel, "AddCartonizedGoods");
        
        addBinGoodsPanel = new AddBinGoodsPanel();
        cardPanel.add(addBinGoodsPanel, "AddBinGoods");
        
        deleteGoodsPanel = new DeleteGoodsPanel(model);
        cardPanel.add(deleteGoodsPanel, "Delete Goods Panel");
        
        moveGoodsPanel = new MoveGoodsPanel(model);
        cardPanel.add(moveGoodsPanel, "Move Goods Panel");
        
        cardLayout.show(cardPanel, "UserPanel");
        
        getContentPane().add(cardPanel);
        
        model.addObserver(this.inventoryPanel);
        
    }
    
    
    
    public void addController(ActionListener controller){
        SwingUtilities.invokeLater(new Runnable() {
    @Override
    public void run() {
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
        addBinGoodsPanel.addSubmitBinGoodsButtonListener(controller);
        addBinGoodsPanel.addGoBackBinButtonActionListener(controller);
        moveGoodsPanel.addGoBackButtonActionListener(controller);
        moveGoodsPanel.addMoveGoodsButtonListener(controller);
        deleteGoodsPanel.addDeleteGoodsButtonActionListener(controller);
        deleteGoodsPanel.addGoBackButtonActionListener(controller);
        inventoryPanel.addDeleteGoodsButtonActionListener(controller);
        
    }
});
        
    }

    
    

    

    
    
    
    
}
