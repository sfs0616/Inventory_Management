/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.inventoryManagment;

/**
 * An interface representing an inventory manager with various methods for managing inventory.
 * This interface declares methods that define the behavior of an inventory manager.
 * Implementing classes are expected to provide concrete implementations for these methods.
 * 
 * The methods declared here include behaviors such as changing the number of pallets, changing the number of shelves,
 * adding goods, printing inventory, moving items, and sorting goods.
 * 
 * @author Avraa
 */
interface InventoryManager {
    
    /**
     * Change the number of pallets in the inventory.
     */
    public void changeNumberOfPallets();
    
    /**
     * Change the number of shelves in the inventory.
     */
    public void changeNumberOfShelves();
    
    /**
     * Add new goods to the inventory.
     */
    public void addGoods();
    
    /**
     * Print the current inventory.
     */
    public void printInventory();
    
    /**
     * Move items from the warehouse to supermarket shelves.
     */
    public void moveItemFromWarehouseToShelf();
    
    /**
     * Sort the goods in the inventory.
     */
    public void goodsSorter();
    
}
