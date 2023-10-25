/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.inventoryManagment;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 *
 * @author Avraam
 */
public class InventoryController implements ActionListener {

    private InventoryModel model;

    private InventoryView view;

    public InventoryController(InventoryModel model, InventoryView view) {
        this.model = model;
        this.view = view;
        view.setVisible(true);
        
        this.initializeController();
        
        
        
    view.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    handleWindowClosing();
                } catch (IOException ex) {
                    Logger.getLogger(InventoryController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        
       
    }

    
    private void handleWindowClosing() throws IOException {
        model.saveGoodsData();
        
        System.exit(0); // Optionally, exit the application
    }
    
    
    public void initializeController() {
        view.addController(this);      
        view.setVisible(true);
    }
    
     public void addModel(InventoryModel model) {
        this.model = model;
    }

    public InventoryModel getModel() {
        return model;
    }

    public InventoryView getView() {
        return view;
    }

    public void setModel(InventoryModel model) {
        this.model = model;
    }

    public void setView(InventoryView view) {
        this.view = view;
    }
    
     
    

    @Override
public void actionPerformed(ActionEvent e) {
    String command = e.getActionCommand(); // Get the action command of the clicked button
    
    // Handle button clicks based on the action command
    switch (command) {
        case "Login":
           String username = view.getUserPanel().getUsername();
           model.findUser(username);
           System.out.println("Login Button Clicked findUser is called");
           view.getCardLayout().show(view.getCardPanel(), "InventoryPanel");
           
            break;
        case "Frozen":
            view.getInventoryPanel().updateTableData(view.getInventoryPanel().getFrozengoods());
            break;
        case "Flammable":
            view.getInventoryPanel().updateTableData(view.getInventoryPanel().getFlammablegoods());
            break;
        case "Refrigerated":
            view.getInventoryPanel().updateTableData(view.getInventoryPanel().getRefrigeratedgoods());
            break;
        case "RoomTemperature":
            view.getInventoryPanel().updateTableData(view.getInventoryPanel().getRoomtemperaturegoods());
            break;
        case "AddBinGoods":
            view.getCardLayout().show(view.getCardPanel(), "AddBinGoods");
            break;
        case "SubmitBinGoods":
            view.getAddBinGoodsPanel().submitData();
            break;
        case "Add Cartonized Goods":
            view.getCardLayout().show(view.getCardPanel(), "AddCartonizedGoods");
            break;
        case "GoBack":
            view.getCardLayout().show(view.getCardPanel(), "InventoryPanel");
            break;
        // Add more cases for other buttons if needed
    }
}

}
