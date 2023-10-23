
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.inventoryManagment;;

import java.io.Serializable;

/**
 * This class represents abstract Goods that can be stored in the inventory.
 * It implements Serializable for object serialization and Comparable for sorting.
 */
public abstract class Goods implements Serializable, Comparable<Goods> {
    // Instance variables to store goods information
    private int stockCode;
    private String description;
    private char storageType;
    private int warehouseBayNumber;
    private int supermarketBayNumber;

    /**
     * Constructor to initialize Goods object with provided information.
     */
    public Goods(int stockCode, String description, char storageType, int warehouseBayNumber) {
        this.stockCode = stockCode;
        this.description = description;
        this.storageType = storageType;
        this.warehouseBayNumber = warehouseBayNumber;
        this.supermarketBayNumber = warehouseBayNumber;
    }

    // Getter methods for instance variables
    public int getStockCode() {
        return stockCode;
    }

    public String getDescription() {
        return description;
    }

    public char getStorageType() {
        return storageType;
    }

    public int getWarehouseBayNumber() {
        return warehouseBayNumber;
    }

    public int getSupermarketBayNumber() {
        return supermarketBayNumber;
    }

    // Setter methods for instance variables
    public void setStockCode(int stockCode) {
        this.stockCode = stockCode;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStorageType(char storageType) {
        this.storageType = storageType;
    }

    public void setWarehouseBayNumber(int warehouseBayNumber) {
        this.warehouseBayNumber = warehouseBayNumber;
    }

    public void setSupermarketBayNumber(int supermarketBayNumber) {
        this.supermarketBayNumber = supermarketBayNumber;
    }
    
    

    /**
     * Overrides the toString method to provide a string representation of the Goods object.
     */
    @Override
    public String toString() {
        return "Goods{" +
                "Stock Code: " + stockCode +
                ", Description: '" + description + '\'' +
                ", Storage Type: " + storageType +
                ", Warehouse Bay Number: " + warehouseBayNumber +
                ", Supermarket Bay Number: " + supermarketBayNumber +
                '}';
    }

    /**
     * Implements the compareTo method for sorting Goods objects based on their description.
     */
    @Override
    public int compareTo(Goods other) {
        return this.getDescription().compareTo(other.getDescription());
    }
}