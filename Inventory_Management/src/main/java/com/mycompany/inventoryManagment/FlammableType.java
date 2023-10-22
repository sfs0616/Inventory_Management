/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.inventoryManagment;;


import java.util.ArrayList;

/**
 * A class representing a flammable product storage type.
 * This class extends the ProductStorageType class and provides specific properties and behaviors
 * for flammable products.
 * 
 * The class includes properties for the storage type, maximum number of pallet bays, and maximum number of shelves.
 * It inherits behavior methods from its parent class.
 * 
 * @author Avraa
 */
public class FlammableType extends ProductStorageType {
    private final char storageType = 'E';
    private int maxNumberPalletBays;
    private int maxNumberOfShelvesInSuperMarket;

    /**
     * Constructor for initializing a flammable product storage type with a list of goods.
     * 
     * @param goods The list of goods associated with this storage type.
     */
    public FlammableType(ArrayList<Goods> goods) {
        super(goods);
    }

    
}


