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

    public InventoryModel() {
        try {
            this.userManagement = new UserManagement();
            this.warehouseSuperMarket = new InventoryLists();
            this.userManagement.loadFromFile("users.dat");
        } catch (SQLException e) {
            System.out.println("Error initializing the InventoryModel: " + e.getMessage());
        }
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
        if (ifUserExists == true) {
            System.out.println("User exists");
            // Load matched user's data. 
            userToWrite = userManagement.getUserByUsername(username);
            if (userToWrite != null) {

                System.out.println(userToWrite.toString());

                warehouseSuperMarket.setUser(userToWrite);

                warehouseSuperMarket.establishDatabaseManager();
                warehouseSuperMarket.dbManager.establishConnection();
                warehouseSuperMarket.loadDbTablesIntoInventory();

            }
        } else {

            // Create a new user
            userToWrite = new User();
            userToWrite.setUserName(username);
            userManagement.addUser(userToWrite);
            warehouseSuperMarket.setUser(userToWrite);
            warehouseSuperMarket.establishDatabaseManager();
            

            // Create a directory for the new user
            String userDirectoryPath = System.getProperty("user.dir") + File.separator + username;
            File userDirectory = new File(userDirectoryPath);
            boolean directoryCreated = userDirectory.mkdir();

            if (directoryCreated) {
                System.out.println("User directory created.");
            } else {
                System.out.println("Failed to create user directory.");
            }
            warehouseSuperMarket.dbManager.createDataBaseForNewUser();
            Boolean userDat = warehouseSuperMarket.dbManager.checkURLexists();
            warehouseSuperMarket.dbManager.checkDbUrlConnection();
            warehouseSuperMarket.dbManager.establishConnection();
        if (userDat == true) {
            System.out.println("Database for new user created.");
            this.setChanged();
            this.notifyObservers();
        } else {
            System.out.println("Error connecting to new user db, line 109 InventoryModel");
        }

        }

       

    }

    public Boolean addBinGoodsGUI(String[] goods) {
        //STOCK_CODE INT, PRODUCT_DESCRIPTION VARCHAR(100), STORAGE_TYPE
//VARCHAR(1), WAREHOUSE_BAY_NUM INT, SUPERMARKET_BAY_NUM INT, CURRENT_KG_BIN INT, CURRENT_KG_SHELF INT

        try {
            int stockCode = Integer.parseInt(goods[0]);
            String productDescript = goods[1];
            char storageType = goods[2].charAt(0);
            int wareHouseNum = Integer.parseInt(goods[3]);
            int superMarketNum = Integer.parseInt(goods[4]);
            double currentKgInBin = Double.parseDouble(goods[5]);
            double currentKgOnShelf = Double.parseDouble(goods[6]);
            Goods newGood = new BinGoodsOnPallet(currentKgInBin, 1000, 10, currentKgOnShelf, stockCode, productDescript, storageType, wareHouseNum, superMarketNum);
            if (storageType == 'E') {
                warehouseSuperMarket.flammablegoods.add(newGood);
            } else if (storageType == 'F') {
                warehouseSuperMarket.frozengoods.add(newGood);
            } else if (storageType == 'C') {
                warehouseSuperMarket.refrigeratedgoods.add(newGood);
            } else if (storageType == 'R') {
                warehouseSuperMarket.roomtemperaturegoods.add(newGood);

            }

        } catch (NumberFormatException e) {
            System.out.println("Error parsing input values: " + e.getMessage());
            return false;

        } catch (Exception e) {
            System.out.println("Error adding bin goods: " + e.getMessage());
            return false;
        }
        this.setChanged();
        this.notifyObservers();
        return true;
    }

    public Boolean addCartonGoodsGUI(String[] goods) {

        try {
            int stockCode = Integer.parseInt(goods[0]);
            String productDescript = goods[1];
            char storageType = goods[2].charAt(0);
            int wareHouseNum = Integer.parseInt(goods[3]);
            int superMarketNum = Integer.parseInt(goods[4]);
            int currentGoodsNum = Integer.parseInt(goods[5]);

            int currentNumberItemsOnShelf = Integer.parseInt(goods[6]);
            Goods newGood = new CartonizedGoods(1000, 10, currentGoodsNum, 100, currentNumberItemsOnShelf, stockCode, productDescript, storageType, wareHouseNum, superMarketNum);

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
        } catch (NumberFormatException e) {
            System.out.println("Error parsing input values: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Error adding carton goods: " + e.getMessage());
            return false;
        }
        this.setChanged();
        this.notifyObservers();
        return true;

    }

    ///The saveGoodsDataGUI method should be triggered on close operation of the gui to ensure that all data is saved correctly. 
    public void saveGoodsDataGUI() throws IOException {

        try {
            warehouseSuperMarket.goodsSorter();
            userManagement.saveToFile("users.dat");
            System.out.println("Saved user data");

            warehouseSuperMarket.saveToFile(userToWrite);
            warehouseSuperMarket.syncDatabase();
            warehouseSuperMarket.upateUserExcelSpreadSheets();

            System.out.println("Saved market data");
            warehouseSuperMarket.dbManager.closeConnections();
            System.out.println("Connections closed");
            this.setChanged();
            this.notifyObservers();

        } catch (Exception e) {
            System.out.println("Unexpected error while saving goods data: " + e.getMessage());
        }
    }

    public Boolean moveItemFromWarehouseToShelfGUI(String[] itemsToBeMoved) {
        Boolean itemFound = false;
        try {
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

                    CartonizedGoods cartonizedGoods = (CartonizedGoods) foundGood;
                    int cartonUnits = (int) units;
                    warehouseSuperMarket.CartonizedMoveUnits(cartonizedGoods, cartonUnits);
                    this.setChanged();
                    this.notifyObservers();
                    return true;
                } else if (foundGood instanceof BinGoodsOnPallet) {

                    BinGoodsOnPallet bingoods = (BinGoodsOnPallet) foundGood;
                    warehouseSuperMarket.binGoodsmoveUnits(bingoods, units);
                    this.setChanged();
                    this.notifyObservers();
                    return true;
                }

            } else {
                System.out.println("Could not find a product with that StockCode: " + foundGood.getStockCode());
                return false;
            }
            this.setChanged();
            this.notifyObservers();
        } catch (NumberFormatException e) {
            System.out.println("Error parsing input values: " + e.getMessage());
            return false;
        } catch (InputMismatchException e) {
            System.out.println("Invalid input provided: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Error moving items: " + e.getMessage());
            return false;
        }

        return false;
    }

    public Boolean deleteGoodsItemGUI(String[] itemsToDeleted) {

        try {

            int stockCodeSearchable = Integer.parseInt(itemsToDeleted[0]);
            String description = itemsToDeleted[1];

            Goods foundGood = null;

            for (Goods item : warehouseSuperMarket.frozengoods) {
                if (item.getStockCode() == stockCodeSearchable && item.getDescription().equals(description)) {
                    foundGood = item;
                    warehouseSuperMarket.frozengoods.remove(foundGood);
                    this.setChanged();
                    this.notifyObservers();
                    return true;

                }
            }

            if (foundGood == null) {
                for (Goods item : warehouseSuperMarket.flammablegoods) {
                    if (item.getStockCode() == stockCodeSearchable && item.getDescription().equals(description)) {
                        foundGood = item;
                        warehouseSuperMarket.flammablegoods.remove(foundGood);
                        this.setChanged();
                        this.notifyObservers();
                        return true;

                    }
                }
            }

            if (foundGood == null) {
                for (Goods item : warehouseSuperMarket.refrigeratedgoods) {
                    if (item.getStockCode() == stockCodeSearchable && item.getDescription().equals(description)) {
                        foundGood = item;
                        warehouseSuperMarket.refrigeratedgoods.remove(foundGood);
                        this.setChanged();
                        this.notifyObservers();
                        return true;

                    }
                }
            }

            if (foundGood == null) {
                for (Goods item : warehouseSuperMarket.roomtemperaturegoods) {
                    if (item.getStockCode() == stockCodeSearchable && item.getDescription().equals(description)) {
                        foundGood = item;
                        warehouseSuperMarket.roomtemperaturegoods.remove(foundGood);
                        this.setChanged();
                        this.notifyObservers();
                        return true;

                    }
                }
            }
            this.setChanged();
            this.notifyObservers();
            return false;
        } catch (NumberFormatException e) {
            System.out.println("Error parsing input values: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Error deleting goods item: " + e.getMessage());
            return false;
        }

    }

    public Goods findGood(String[] goodsData) {

        if (goodsData == null || goodsData.length < 2) {
            throw new IllegalArgumentException("Invalid goodsData provided.");
        }

        Goods goodsItem = null;
        int stockCode;

        try {
            stockCode = Integer.parseInt(goodsData[0]);
        } catch (NumberFormatException e) {
            return goodsItem;

        }

        for (Goods item : warehouseSuperMarket.frozengoods) {
            if (item.getStockCode() == stockCode) {
                goodsItem = item;
                break;
            }
        }

        if (goodsItem == null) {
            for (Goods item : warehouseSuperMarket.flammablegoods) {
                if (item.getStockCode() == stockCode) {
                    goodsItem = item;
                    break;
                }
            }
        }

        if (goodsItem == null) {
            for (Goods item : warehouseSuperMarket.refrigeratedgoods) {
                if (item.getStockCode() == stockCode) {
                    goodsItem = item;
                    break;
                }
            }
        }

        if (goodsItem == null) {
            for (Goods item : warehouseSuperMarket.roomtemperaturegoods) {
                if (item.getStockCode() == stockCode) {
                    goodsItem = item;
                    break;
                }
            }
        }

        if (goodsItem == null) {
            return null;

        }

        return goodsItem;

    }

    public boolean isStockCodePrimaryKey(String[] goodsData) {
        Goods checkGoodDoesNotExist = findGood(goodsData);

        if (checkGoodDoesNotExist == null) {
            return true;
        }

        return false;
    }

}
