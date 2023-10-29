/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.inventoryManagment;

import java.sql.SQLException;

/**
 *
 * @author Avraam
 *///Main run method for the GUI.
public class InventoryRunGUI {
    public static void main(String[] args) throws SQLException {
        InventoryModel model = new InventoryModel();
        InventoryView view = new InventoryView(model);
        InventoryController controller = new InventoryController(model, view);
        controller.initializeController();
        
    }
}
