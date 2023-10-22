/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.inventoryManagment;

import java.util.ArrayList;

/**
 * Represents a storage type for goods that require room temperature.
 * This class extends the ProductStorageType class and specializes for room temperature goods.
 * It contains a list of goods that belong to the room temperature storage type.
 * 
 * @author Avraa
 */
public class RoomTemperatureType extends ProductStorageType {
    private final char storageType = 'R';

    /**
     * Constructs a RoomTemperatureType object with a list of goods.
     * 
     * @param goods The list of goods that belong to the room temperature storage type.
     */
    public RoomTemperatureType(ArrayList<Goods> goods) {
        super(goods);
    }

    
}


