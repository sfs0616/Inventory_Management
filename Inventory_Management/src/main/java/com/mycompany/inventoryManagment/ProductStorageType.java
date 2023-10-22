/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.inventoryManagment;;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * An abstract class representing a product storage type.
 * This class defines common properties and behaviors for different storage types.
 * Subclasses are expected to provide specific implementations for the behavior methods.
 * 
 * The class provides methods to change the number of pallet bays and shelves in the supermarket.
 * It also manages properties related to goods and storage.
 * 
 * @author Avraa
 */
public abstract class ProductStorageType implements Serializable{
    
    // Properties
    ArrayList<Goods> goods;
    private static char storageType = 10;
    private int maxNumberPalletBays = 10;
    private int maxNumberOfShelvesInSuperMarket;
    
    /**
     * Constructor for initializing a product storage type with a list of goods.
     * 
     * @param goods The list of goods associated with this storage type.
     */
    public ProductStorageType(ArrayList<Goods> goods) {
        this.goods = goods;
    }

    // Getter and Setter methods
    
    public ArrayList<Goods> getGoods() {
        return goods;
    }

    public static char getStorageType() {
        return storageType;
    }

    public int getMaxNumberPalletBays() {
        return maxNumberPalletBays;
    }

    public void setObjects(ArrayList<Goods> goods) {
        this.goods = goods;
    }

    public int getMaxNumberOfShelvesInSuperMarket() {
        return maxNumberOfShelvesInSuperMarket;
    }

    public void setMaxNumberPalletBays(int maxNumberPalletBays) {
        this.maxNumberPalletBays = maxNumberPalletBays;
    }

    public void setGoods(ArrayList<Goods> goods) {
        this.goods = goods;
    }

    public static void setStorageType(char storageType) {
        ProductStorageType.storageType = storageType;
    }

    public void setMaxNumberOfShelvesInSuperMarket(int maxNumberOfShelvesInSuperMarket) {
        this.maxNumberOfShelvesInSuperMarket = maxNumberOfShelvesInSuperMarket;
    }
   
    // Behavior methods
    
    /**
     * Change the number of pallet bays for this storage type.
     * Prompts the user for input and handles invalid input.
     */
    public void ChangePalletsNumber() {
        System.out.println("The current number of Pallets is: " + this.getMaxNumberPalletBays());
        System.out.println("How many Pallet bays would you like: ");
        Scanner keyboard = new Scanner(System.in);
        try {
            int PalletBaysNumber = keyboard.nextInt();
            this.setMaxNumberPalletBays(PalletBaysNumber);
            System.out.println("The new number of Pallets is: " + this.getMaxNumberPalletBays());
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid integer.");
            keyboard.nextLine(); 
            ChangePalletsNumber();
        }
    }
    
    /**
     * Change the number of shelves for this storage type in the supermarket.
     * Prompts the user for input and handles invalid input.
     */
    public void ChangeShelvesNumber() {
        System.out.println("The current number of shelves is: " + this.getMaxNumberOfShelvesInSuperMarket());
        System.out.println("How many shelve bays would you like: ");
        Scanner keyboard = new Scanner(System.in);
        try {
            int shelveBaysNumber = keyboard.nextInt();
            this.setMaxNumberOfShelvesInSuperMarket(shelveBaysNumber);
            System.out.println("The new number of shelve bays in supermarket is: " + this.getMaxNumberOfShelvesInSuperMarket());
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid integer.");
            keyboard.nextLine(); 
            ChangeShelvesNumber(); 
        }
    }
}

