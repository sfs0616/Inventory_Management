/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.inventoryManagment;;

import java.util.ArrayList;

/**
 * Represents a storage type for refrigerated goods. Extends the ProductStorageType class
 * and provides a specific storage type character 'C' for refrigerated goods.
 * 
 * This class allows managing and categorizing refrigerated goods within the inventory.
 * It inherits methods and properties from the ProductStorageType class.
 * 
 * @author Avraa
 */
public class RefrigeratedType extends ProductStorageType {
    private final char storageType = 'C';
 
    /**
     * Constructs a RefrigeratedType object with the given list of refrigerated goods.
     * 
     * @param goods The list of refrigerated goods to be associated with this storage type.
     */
    public RefrigeratedType(ArrayList<Goods> goods) {
        super(goods);
    }
 
}
