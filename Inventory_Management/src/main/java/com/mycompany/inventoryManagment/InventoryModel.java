/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.inventoryManagment;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Observable;
import java.util.Scanner;

/**
 *
 * @author Avraam
 */
public class InventoryModel extends Observable {

    private InventoryLists warehouseSuperMarket;
    private UserManagement userManagement;
    private User userToWrite;
    private int moveGoodsStockCode = 0;

    public InventoryLists getWarehouseSuperMarket() {
        return warehouseSuperMarket;
    }

    public UserManagement getUserManagement() {
        return userManagement;
    }

    public User getUserToWrite() {
        return userToWrite;
    }
    
    
    
    

    public void setMoveGoodsStockCode(int moveGoodsStockCode) {
        this.moveGoodsStockCode = moveGoodsStockCode;
    }

    public int getMoveGoodsStockCode() {
        return moveGoodsStockCode;
    }

    public InventoryModel() throws SQLException {
        this.userManagement = new UserManagement();
        this.warehouseSuperMarket = new InventoryLists();
        this.userManagement.loadFromFile("users.dat");
    }

    public void findUserGUI(String username) {

        ///Take input from user panel in view for username//
        //String userName = e.g userpanel textbox input for username
        boolean ifUserExists = false;
        for (User user : userManagement.users) {
            if (user.getUserName().equals(username)) {
                ifUserExists = true;
                break;
            }
        }
        if (ifUserExists) {
            System.out.println("User exists");
            // Load matched user's data. 
            userToWrite = userManagement.getUserByUsername(username);
            if (userToWrite != null) {

                System.out.println(userToWrite.toString());

                warehouseSuperMarket.setUser(userToWrite);
                warehouseSuperMarket.loadSerializedDataForUser(userToWrite);
                warehouseSuperMarket.establishDatabase();

            }
        } else {

            // Create a new user
            userToWrite = new User();
            userToWrite.setUserName(username);
            userManagement.addUser(userToWrite);
            warehouseSuperMarket.setUser(userToWrite);

            // Create a directory for the new user
            String userDirectoryPath = System.getProperty("user.dir") + File.separator + username;
            File userDirectory = new File(userDirectoryPath);
            boolean directoryCreated = userDirectory.mkdir();

            if (directoryCreated) {
                System.out.println("User directory created.");
            } else {
                System.out.println("Failed to create user directory.");
            }
            warehouseSuperMarket.establishDatabase();
        }

        Boolean userDat = warehouseSuperMarket.dbManager.checkUserDataBaseExists();
        warehouseSuperMarket.dbManager.establishConnection();
        this.setChanged();
        this.notifyObservers();

    }

    public void addBinGoodsGUI(String[] goods) {
        //STOCK_CODE INT, PRODUCT_DESCRIPTION VARCHAR(100), STORAGE_TYPE
//VARCHAR(1), WAREHOUSE_BAY_NUM INT, SUPERMARKET_BAY_NUM INT, CURRENT_KG_BIN INT, CURRENT_KG_SHELF INT

        int stockCode = Integer.parseInt(goods[0]);
        String productDescript = goods[1];
        char storageType = goods[2].charAt(0);
        int wareHouseNum = Integer.parseInt(goods[3]);
        int superMarketNum = Integer.parseInt(goods[4]);
        double currentKgInBin = Double.parseDouble(goods[5]);
        double currentKgOnShelf = Double.parseDouble(goods[6]);
        Goods newGood = new BinGoodsOnPallet(currentKgInBin, 1000, 100, currentKgOnShelf, stockCode, productDescript, storageType, wareHouseNum, superMarketNum);
        if (storageType == 'E') {
            warehouseSuperMarket.flammablegoods.add(newGood);
        } else if (storageType == 'F') {
            warehouseSuperMarket.frozengoods.add(newGood);
        } else if (storageType == 'C') {
            warehouseSuperMarket.refrigeratedgoods.add(newGood);
        } else if (storageType == 'R') {
            warehouseSuperMarket.roomtemperaturegoods.add(newGood);
            this.setChanged();
            this.notifyObservers();
        }

    }

    public void addCartonGoodsGUI(String[] goods) {
        //STOCK_CODE INT, PRODUCT_DESCRIPTION VARCHAR(100), STORAGE_TYPE
//VARCHAR(1), WAREHOUSE_BAY_NUM INT, SUPERMARKET_BAY_NUM INT, CURRENT_GOODS_TOTAL INT, CURRENT_CARTONS_TOTAL INT, CURRENT_TOTAL_ITEMS_SHELF INT, 

        int stockCode = Integer.parseInt(goods[0]);
        String productDescript = goods[1];
        char storageType = goods[2].charAt(0);
        int wareHouseNum = Integer.parseInt(goods[3]);
        int superMarketNum = Integer.parseInt(goods[4]);
        int currentGoodsNum = Integer.parseInt(goods[5]);

        int currentShelfItemsOnShelfNum = Integer.parseInt(goods[6]);
        Goods newGood = new CartonizedGoods(1000, 1000, currentGoodsNum, 100, currentShelfItemsOnShelfNum, stockCode, productDescript, storageType, wareHouseNum, superMarketNum);

        if (storageType == 'E') {
            warehouseSuperMarket.flammablegoods.add(newGood);
        } else if (storageType == 'F') {
            warehouseSuperMarket.frozengoods.add(newGood);
        } else if (storageType == 'C') {
            warehouseSuperMarket.refrigeratedgoods.add(newGood);
        } else if (storageType == 'R') {
            warehouseSuperMarket.roomtemperaturegoods.add(newGood);
            this.setChanged();
            this.notifyObservers();
        }

    }

   

    ///The saveGoodsDataGUI method should be triggered on close operation of the gui to ensure that all data is saved correctly. 
    public void saveGoodsDataGUI() throws IOException {
        userManagement.saveToFile("users.dat");
        System.out.println("Saved user data");
        warehouseSuperMarket.goodsSorter();
        warehouseSuperMarket.saveToFile(userToWrite);

        warehouseSuperMarket.syncObjectsToDB(warehouseSuperMarket.frozengoods, "FROZEN_GOODS");
        warehouseSuperMarket.syncObjectsToDB(warehouseSuperMarket.flammablegoods, "FLAMMABLE_GOODS ");
        warehouseSuperMarket.syncObjectsToDB(warehouseSuperMarket.roomtemperaturegoods, "ROOM_TEMP_GOODS ");
        warehouseSuperMarket.syncObjectsToDB(warehouseSuperMarket.refrigeratedgoods, "REFRIGERATED_GOODS ");
        System.out.println("Saved market data");
        warehouseSuperMarket.dbManager.closeConnections();

        this.setChanged();
        this.notifyObservers();

    }

    public void moveItemFromWarehouseToShelfGUI(String[] itemsToBeMoved) {
        Scanner keyboard = new Scanner(System.in);

        int stockCodeSearchable = Integer.parseInt(itemsToBeMoved[0]);
        double units = Double.parseDouble(itemsToBeMoved[1]);

        Goods foundGood = null;

        for (Goods item : warehouseSuperMarket.frozengoods) {
            if (item.getStockCode() == stockCodeSearchable) {
                foundGood = item;
                break;
            }
        }

        if (foundGood == null) {
            for (Goods item : warehouseSuperMarket.flammablegoods) {
                if (item.getStockCode() == stockCodeSearchable) {
                    foundGood = item;
                    break;
                }
            }
        }

        if (foundGood == null) {
            for (Goods item : warehouseSuperMarket.refrigeratedgoods) {
                if (item.getStockCode() == stockCodeSearchable) {
                    foundGood = item;
                    break;
                }
            }
        }

        if (foundGood == null) {
            for (Goods item : warehouseSuperMarket.roomtemperaturegoods) {
                if (item.getStockCode() == stockCodeSearchable) {
                    foundGood = item;
                    break;
                }
            }
        }

        if (foundGood != null) {
            System.out.println("Here is the product that you requested: ");
            System.out.println(foundGood.toString());
            if (foundGood instanceof CartonizedGoods) {
                System.out.println("How many units would you like to take from the warehouse and add to the supermarket bay?");

                CartonizedGoods cartonizedGoods = (CartonizedGoods) foundGood;
                int cartonUnits = (int) units;
                warehouseSuperMarket.CartonizedMoveUnits(cartonizedGoods, cartonUnits);
            } else if (foundGood instanceof BinGoodsOnPallet) {
                System.out.println("How many kg would you like to take from the warehouse and add to the supermarket bay?");

                BinGoodsOnPallet bingoods = (BinGoodsOnPallet) foundGood;
                warehouseSuperMarket.binGoodsmoveUnits(bingoods, units);
            }
            setMoveGoodsStockCode(foundGood.getStockCode());
        } else {
            System.out.println("Could not find a product with that StockCode.");

        }
        this.setChanged();
        this.notifyObservers();
    }

    public Boolean deleteGoodsItemGUI(String[] itemsToDeleted) {

        Boolean itemDeleted = false;

        int stockCodeSearchable = Integer.parseInt(itemsToDeleted[0]);
        String description = itemsToDeleted[1];

        Goods foundGood = null;

        for (Goods item : warehouseSuperMarket.frozengoods) {
            if (item.getStockCode() == stockCodeSearchable && item.getDescription().equals(description)) {
                foundGood = item;
                warehouseSuperMarket.frozengoods.remove(foundGood);
                itemDeleted = true;
                break;
            }
        }

        if (foundGood == null) {
            for (Goods item : warehouseSuperMarket.flammablegoods) {
                if (item.getStockCode() == stockCodeSearchable && item.getDescription().equals(description)) {
                    foundGood = item;
                    warehouseSuperMarket.flammablegoods.remove(foundGood);
                    itemDeleted = true;
                    break;
                }
            }
        }

        if (foundGood == null) {
            for (Goods item : warehouseSuperMarket.refrigeratedgoods) {
                if (item.getStockCode() == stockCodeSearchable && item.getDescription().equals(description)) {
                    foundGood = item;
                    warehouseSuperMarket.refrigeratedgoods.remove(foundGood);
                    itemDeleted = true;
                    break;
                }
            }
        }

        if (foundGood == null) {
            for (Goods item : warehouseSuperMarket.roomtemperaturegoods) {
                if (item.getStockCode() == stockCodeSearchable && item.getDescription().equals(description)) {
                    foundGood = item;
                    warehouseSuperMarket.roomtemperaturegoods.remove(foundGood);
                    itemDeleted = true;
                    break;
                }
            }
        }
        this.setChanged();
        this.notifyObservers();
        return itemDeleted;
    }
    
     

}
