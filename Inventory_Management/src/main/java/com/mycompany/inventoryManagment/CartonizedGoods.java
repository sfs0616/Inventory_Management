/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.inventoryManagment;;

import java.io.Serializable;

/**
 * This class represents CartonizedGoods, a type of Goods that can be stored in the inventory.
 * It extends the Goods class and implements Serializable.
 */
public class CartonizedGoods extends Goods implements Serializable{
    
    private int MAX_NUMBER_CARTONS_PER_PALLET;
    private int MAX_NUMBER_GOODS_PER_CARTON;
    private int currentGoodsNumber; 
    private int currentCartonsNumber; 
    private int maxNumberOfItemsShelf; 
    private int currentNumberOfItemsOnShelf;

    public CartonizedGoods(int MAX_NUMBER_CARTONS_PER_PALLET, int MAX_NUMBER_GOODS_PER_CARTON, int currentGoodsNumber, int maxNumberOfItemsShelf, int currentNumberOfItemsOnShelf, int stockCode, String description, char storageType, int warehouseBayNumber, int supermarketBayNumber) {
        super(stockCode, description, storageType, warehouseBayNumber, supermarketBayNumber);
        this.MAX_NUMBER_CARTONS_PER_PALLET = MAX_NUMBER_CARTONS_PER_PALLET;
        this.MAX_NUMBER_GOODS_PER_CARTON = MAX_NUMBER_GOODS_PER_CARTON;
        this.currentGoodsNumber = currentGoodsNumber;
        this.currentCartonsNumber = ((currentGoodsNumber)/(MAX_NUMBER_GOODS_PER_CARTON));
       
    }

   
    public int getMAX_NUMBER_CARTONS_PER_PALLET() {
        return MAX_NUMBER_CARTONS_PER_PALLET;
    }

    public int getMAX_NUMBER_GOODS_PER_CARTON() {
        return MAX_NUMBER_GOODS_PER_CARTON;
    }

    public int getCurrentGoodsNumber() {
        return currentGoodsNumber;
    }

    public int getCurrentCartonsNumber() {
        return currentCartonsNumber;
    }

    public int getMaxNumberOfItemsShelf() {
        return maxNumberOfItemsShelf;
    }

    public int getCurrentNumberOfItemsOnShelf() {
        return currentNumberOfItemsOnShelf;
    }

    public void setMaxNumberOfItemsShelf(int maxNumberOfItemsShelf) {
        this.maxNumberOfItemsShelf = maxNumberOfItemsShelf;
    }

    public void setCurrentNumberOfItemsOnShelf(int currentNumberOfItemsOnShelf) {
        this.currentNumberOfItemsOnShelf = currentNumberOfItemsOnShelf;
    }

    
   

    public void setMAX_NUMBER_CARTONS_PER_PALLET(int MAX_NUMBER_CARTONS_PER_PALLET) {
        this.MAX_NUMBER_CARTONS_PER_PALLET = MAX_NUMBER_CARTONS_PER_PALLET;
    }

    public void setMAX_NUMBER_GOODS_PER_CARTON(int MAX_NUMBER_GOODS_PER_CARTON) {
        this.MAX_NUMBER_GOODS_PER_CARTON = MAX_NUMBER_GOODS_PER_CARTON;
    }

    public void setCurrentGoodsNumber(int currentGoodsNumber) {
        this.currentGoodsNumber = currentGoodsNumber;
    }

    public void setCurrentCartonsNumber(int currentCartonsNumber) {
        this.currentCartonsNumber = currentCartonsNumber;
    }

    
 
    
 @Override
    public String toString() {
        return " Cartonized Goods: " +
                "Stock Code: " + getStockCode() +
                ", Description: '" + getDescription() + '\'' +
                ", Storage Type: " + getStorageType() +
                ", Warehouse Bay Number: " + getWarehouseBayNumber() +
                ", Supermarket Bay Number: " + getSupermarketBayNumber() +
                "  Max number of cartons per pallet: " + MAX_NUMBER_CARTONS_PER_PALLET +
                ", Max number of goods percarton: " + MAX_NUMBER_GOODS_PER_CARTON +
                ", Current total goods in warehouse: " + currentGoodsNumber +
                ", Current total cartons left in warehouse: " + currentCartonsNumber +
                ", Max number of goods that can fit on supermarket shelf: " + maxNumberOfItemsShelf +
                ", Current number of goods on supermarket shelf: " + currentNumberOfItemsOnShelf;
    }
    

    
    
    
}