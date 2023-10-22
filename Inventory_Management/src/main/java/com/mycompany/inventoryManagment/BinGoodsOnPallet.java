/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.inventoryManagment;;

import java.io.Serializable;

/**
 * This class represents BinGoodsOnPallet, a type of Goods that can be stored in the inventory.
 * It extends the Goods class and implements Serializable.
 */
public class BinGoodsOnPallet extends Goods implements Serializable{
    
     private double currentKgPerBin;
     private double maxKgPerBin;
     private double maxKgOnShelf;
     private double currentKgOnShelf;

    public BinGoodsOnPallet(double currentKgPerBin, double maxKgPerBin, double maxKgOnShelf, double currentKgOnShelf, int stockCode, String description, char storageType, int warehouseBayNumber) {
        super(stockCode, description, storageType, warehouseBayNumber);
        this.currentKgPerBin = currentKgPerBin;
        this.maxKgPerBin = maxKgPerBin;
        this.maxKgOnShelf = maxKgOnShelf;
        this.currentKgOnShelf = currentKgOnShelf;
    }
     
  
    public void setMaxKgPerBin(double maxKgPerBin) {
        this.maxKgPerBin = maxKgPerBin;
    }

    public void setCurrentKgPerBin(double currentKgPerBin) {
        this.currentKgPerBin = currentKgPerBin;
    }

    public void setMaxKgOnShelf(double maxKgOnShelf) {
        this.maxKgOnShelf = maxKgOnShelf;
    }

    public void setCurrentKgOnShelf(double currentKgOnShelf) {
        this.currentKgOnShelf = currentKgOnShelf;
    }

   

    public double getMaxKgOnShelf() {
        return maxKgOnShelf;
    }

    public double getCurrentKgOnShelf() {
        return currentKgOnShelf;
    }

    

    public double getMaxKgPerBin() {
        return maxKgPerBin;
    }

    public double getCurrentKgPerBin() {
        return currentKgPerBin;
    }

   

    

    @Override
    public String toString() {
        return "Bin Goods: " +
                "Stock Code: " + getStockCode() +
                ", Description: '" + getDescription() + '\'' +
                ", Storage Type: " + getStorageType() +
                ", Warehouse Bay Number: " + getWarehouseBayNumber() +
                ", Supermarket Bay Number: " + getSupermarketBayNumber() +
                ", Current Kg In Warehouse Bin: " + currentKgPerBin +
                ", Curent Kg on SuperMarket Shelf: " + currentKgOnShelf +
                 ", Max Kg per Bin: " + maxKgPerBin +
                ", Max Kg on shelf: " + maxKgOnShelf;
    }
   
    
    
    
}

