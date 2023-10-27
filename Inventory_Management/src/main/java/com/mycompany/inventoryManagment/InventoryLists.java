/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.inventoryManagment;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * A class representing the inventory lists for different storage types. This
 * class implements the InventoryManager and InventoryReaderWriter interfaces,
 * providing methods to manage the inventory, including changing the number of
 * pallets and shelves, adding goods, printing the inventory, and more. It also
 * maintains separate lists for different storage types such as frozen,
 * refrigerated, room temperature, and flammable goods.
 *
 * The methods in this class allow for inventory management functionalities such
 * as changing storage capacity, adding goods, and printing the inventory for
 * different storage types. It also handles data persistence using various file
 * formats and interactions with Excel spreadsheets.
 *
 * @author Avraa
 */
//https://docs.oracle.com/javase/tutorial/jndi/objects/serial.html
public class InventoryLists implements InventoryManager, InventoryReaderWriter {

    private User user;

    ArrayList<Goods> frozengoods = new ArrayList<>();
    ProductStorageType frozeninventory = new FrozenType(frozengoods);

    ArrayList<Goods> refrigeratedgoods = new ArrayList<>();
    ProductStorageType refrigeratedinventory = new RefrigeratedType(refrigeratedgoods);

    ArrayList<Goods> flammablegoods = new ArrayList<>();
    ProductStorageType flammableinventory = new FlammableType(flammablegoods);

    ArrayList<Goods> roomtemperaturegoods = new ArrayList<>();
    ProductStorageType roomtemperatureinventory = new RoomTemperatureType(roomtemperaturegoods);

    InventoryDatabaseManager dbManager;

    public ArrayList<Goods> getFrozengoods() {
        return frozengoods;
    }

    public ArrayList<Goods> getRefrigeratedgoods() {
        return refrigeratedgoods;
    }

    public ArrayList<Goods> getFlammablegoods() {
        return flammablegoods;
    }

    public ArrayList<Goods> getRoomtemperaturegoods() {
        return roomtemperaturegoods;
    }

    public InventoryLists() throws SQLException {

    }

    public void establishDatabase() {
        dbManager = new InventoryDatabaseManager(this.user);
    }

    /**
     * Get the current user.
     *
     * @return The current user.
     */
    public User getUser() {
        return user;
    }

    /**
     * Set the current user.
     *
     * @param user The user to set.
     */
    public void setUser(User user) {
        this.user = user;
    }

    Scanner keyboard = new Scanner(System.in);

    @Override
    public void changeNumberOfPallets() {
        System.out.println("Which warehouse zone would you like to change number of pallets?");
        System.out.println("0: Exit.");
        System.out.println("1: Flammable");
        System.out.println("2: Frozen");
        System.out.println("3: Refrigerated");
        System.out.println("4: Room temperature");

        int case1Input = -1;

        try {
            case1Input = keyboard.nextInt();

            switch (case1Input) {
                case 0: {
                    break;
                }
                case 1: {
                    this.flammableinventory.ChangePalletsNumber();
                    break;
                }
                case 2: {
                    this.frozeninventory.ChangePalletsNumber();
                    break;
                }
                case 3: {
                    this.refrigeratedinventory.ChangePalletsNumber();
                    break;
                }
                case 4: {
                    this.roomtemperatureinventory.ChangePalletsNumber();
                    break;
                }
                default: {
                    System.out.println("Invalid input. Please enter a number from 0 to 4.");
                    keyboard.nextLine();
                    break;
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid number.");
            keyboard.nextLine();
        }
    }

    @Override
    public void changeNumberOfShelves() {
        System.out.println("Which supermarket zone would you like to change number of shelves?");
        System.out.println("0: Exit.");
        System.out.println("1: Flammable");
        System.out.println("2: Frozen");
        System.out.println("3: Refrigerated");
        System.out.println("4: Room temperature");

        int case2Input = -1;

        try {
            case2Input = keyboard.nextInt();

            switch (case2Input) {
                case 0: {
                    break;
                }
                case 1: {
                    this.flammableinventory.ChangeShelvesNumber();
                    break;
                }
                case 2: {
                    this.frozeninventory.ChangeShelvesNumber();
                    break;
                }
                case 3: {
                    this.refrigeratedinventory.ChangeShelvesNumber();
                    break;
                }
                case 4: {
                    this.roomtemperatureinventory.ChangeShelvesNumber();
                    break;
                }
                default: {
                    System.out.println("Invalid input. Please enter a number from 0 to 4.");
                    break;
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid number.");
            keyboard.nextLine();
        }
    }

//    @Override
//    public void addGoods() {
//        System.out.println("Would you like to add a (1) palletized good or (2) a bin pallet type good (i.e fruit and veggies): ");
//
//        int goodstype = -1;
//
//        try {
//            goodstype = keyboard.nextInt();
//
//            if (goodstype == 1) {
//                this.addPalletGoods();
//            } else if (goodstype == 2) {
//                this.addBinGoods();
//            } else {
//                System.out.println("Invalid input. Please enter 1 or 2.");
//            }
//        } catch (InputMismatchException e) {
//            System.out.println("Invalid input. Please enter a valid number.");
//            keyboard.nextLine();
//        }
//    }
    @Override
    public void printInventory() {
        System.out.println("Frozen Goods:");
        for (Goods goods : this.frozengoods) {
            System.out.println(goods.toString());
        }

        System.out.println("Refrigerated goods:");
        for (Goods goods : this.refrigeratedgoods) {
            System.out.println(goods.toString());
        }

        System.out.println("Room temperature goods:");
        for (Goods goods : this.roomtemperaturegoods) {
            System.out.println(goods.toString());
        }

        System.out.println("Flammable Goods:");
        for (Goods goods : this.flammablegoods) {
            System.out.println(goods.toString());
        }
    }

    /**
     * Adds palletized goods to the inventory. This method prompts the user to
     * input details about the goods, including stock code, description, storage
     * type, maximum number of cartons per pallet, maximum number of goods per
     * carton, current number of goods, and shelf information. The method then
     * creates a new CartonizedGoods object based on the input and adds it to
     * the appropriate storage type list.
     */
//    public void addPalletGoods() {
//        Scanner keyboard = new Scanner(System.in);
//
//        try {
//            System.out.println("Please enter a new stock code: ");
//            int stockCode = keyboard.nextInt();
//            keyboard.nextLine();
//            System.out.println("Please enter a brief product description: ");
//            String description = keyboard.nextLine();
//            char storageType = ' ';
//
//            do {
//                System.out.println("Please enter a storage type from the list below: ");
//                System.out.println("'E': Flammable");
//                System.out.println("'F': Frozen");
//                System.out.println("'C': Refrigerated");
//                System.out.println("'R': Room temperature");
//
//                String input = keyboard.next();
//                try {
//                    if (input.length() == 1) {
//                        storageType = input.charAt(0);
//                        storageType = Character.toUpperCase(storageType);
//                    } else {
//                        throw new IllegalArgumentException("Invalid input. Please enter a single character.");
//                    }
//
//                    if (storageType != 'E' && storageType != 'F' && storageType != 'C' && storageType != 'R') {
//                        throw new IllegalArgumentException("Invalid storage type. Please enter 'E', 'F', 'C', or 'R'.");
//                    }
//                } catch (IllegalArgumentException e) {
//                    System.out.println(e.getMessage());
//                }
//            } while (storageType != 'E' && storageType != 'F' && storageType != 'C' && storageType != 'R');
//
//            System.out.println("Please enter the maximum number of cartons per pallet: ");
//            int MAX_NUMBER_CARTONS_PER_PALLET = keyboard.nextInt();
//            System.out.println("Please enter the maximum number of goods per carton: ");
//            int MAX_NUMBER_GOODS_PER_CARTON = keyboard.nextInt();
//            System.out.println("Please enter the current number of goods to be added to pallet: ");
//            int currentGoodsNumber = keyboard.nextInt();
//            System.out.println("Please enter the maximum number of individual goods items that can fit on shelf: ");
//            int maxNumberOfItemsShelf = keyboard.nextInt();
//            System.out.println("How many items would you initially like to put on the supermarket shelf: ");
//            int currentNumberOfItemsOnShelf = keyboard.nextInt();
//            System.out.println("Note: The supermarket shelf bay number will be the same as the warehouse bay number to avoid confusion.");
//            int warehouseBayNumber;
//
//            if (storageType == 'E') {
//                System.out.println("What warehouse bay number in the flammable section would you like to add the new goods to: ");
//                warehouseBayNumber = keyboard.nextInt();
//                if (flammablegoods.size() <= flammableinventory.getMaxNumberPalletBays()) {
//                    flammablegoods.add(new CartonizedGoods(MAX_NUMBER_CARTONS_PER_PALLET, MAX_NUMBER_GOODS_PER_CARTON, currentGoodsNumber, maxNumberOfItemsShelf, currentNumberOfItemsOnShelf, stockCode, description, storageType, warehouseBayNumber, ));
//                } else {
//                    System.out.println("Not enough space in this warehouse for new pallet");
//                }
//            } else if (storageType == 'F') {
//                System.out.println("What warehouse bay number in the frozen section would you like to add the new goods to: ");
//                warehouseBayNumber = keyboard.nextInt();
//                if (frozengoods.size() <= frozeninventory.getMaxNumberPalletBays()) {
//                    frozengoods.add(new CartonizedGoods(MAX_NUMBER_CARTONS_PER_PALLET, MAX_NUMBER_GOODS_PER_CARTON, currentGoodsNumber, maxNumberOfItemsShelf, currentNumberOfItemsOnShelf, stockCode, description, storageType, warehouseBayNumber));
//                } else {
//                    System.out.println("Not enough space in this warehouse for new pallet");
//                }
//            } else if (storageType == 'C') {
//                System.out.println("What warehouse bay number in the refrigeration section would you like to add the new goods to: ");
//                warehouseBayNumber = keyboard.nextInt();
//                if (refrigeratedgoods.size() <= refrigeratedinventory.getMaxNumberPalletBays()) {
//                    refrigeratedgoods.add(new CartonizedGoods(MAX_NUMBER_CARTONS_PER_PALLET, MAX_NUMBER_GOODS_PER_CARTON, currentGoodsNumber, maxNumberOfItemsShelf, currentNumberOfItemsOnShelf, stockCode, description, storageType, warehouseBayNumber));
//                } else {
//                    System.out.println("Not enough space in this warehouse for new pallet");
//                }
//            } else if (storageType == 'R') {
//                System.out.println("What warehouse bay number in the room temperature section would you like to add the new goods to: ");
//                warehouseBayNumber = keyboard.nextInt();
//                if (roomtemperaturegoods.size() <= roomtemperatureinventory.getMaxNumberPalletBays()) {
//                    roomtemperaturegoods.add(new CartonizedGoods(MAX_NUMBER_CARTONS_PER_PALLET, MAX_NUMBER_GOODS_PER_CARTON, currentGoodsNumber, maxNumberOfItemsShelf, currentNumberOfItemsOnShelf, stockCode, description, storageType, warehouseBayNumber));
//                } else {
//                    System.out.println("Not enough space in this warehouse for new pallet.");
//                }
//            } else {
//                System.out.println("Invalid storage type. Please enter 'E', 'F', 'C', or 'R'.");
//            }
//        } catch (InputMismatchException e) {
//            System.out.println("Invalid input. Please enter a valid number.");
//            keyboard.nextLine();
//        }
//    }
    /**
     * Adds bin pallet type goods to the inventory. This method prompts the user
     * to input details about the goods, including stock code, description,
     * storage type, maximum number of kg per bin pallet, current number of kg,
     * and shelf information. The method then creates a new BinGoodsOnPallet
     * object based on the input and adds it to the appropriate storage type
     * list.
     */
//    public void addBinGoods() {
//        Scanner keyboard = new Scanner(System.in);
//
//        try {
//            System.out.println("Please enter a new stock code: ");
//            int stockCode = keyboard.nextInt();
//            keyboard.nextLine();
//            System.out.println("Please enter a brief product description: ");
//            String description = keyboard.nextLine();
//            char storageType = ' ';
//
//            do {
//                System.out.println("Please enter a storage type from the list below: ");
//                System.out.println("'F': Frozen");
//                System.out.println("'C': Refrigerated");
//                System.out.println("'R': Room temperature");
//
//                String input = keyboard.next();
//                try {
//                    if (input.length() == 1) {
//                        storageType = input.charAt(0);
//                        storageType = Character.toUpperCase(storageType);
//                    } else {
//                        throw new IllegalArgumentException("Invalid input. Please enter a single character.");
//                    }
//
//                    if (storageType != 'F' && storageType != 'C' && storageType != 'R') {
//                        throw new IllegalArgumentException("Invalid storage type. Please enter 'F', 'C', or 'R'.");
//                    }
//                } catch (IllegalArgumentException e) {
//                    System.out.println(e.getMessage());
//                }
//            } while (storageType != 'F' && storageType != 'C' && storageType != 'R');
//
//            System.out.println("Please enter the maximum number of kg per bin pallet: ");
//            int maxKgPerBin = keyboard.nextInt();
//            System.out.println("Please enter the current new kg in the bin: ");
//            int currentKgPerBin = keyboard.nextInt();
//            System.out.println("Please enter the max kg that can fit on the supermarket shelf: ");
//            int maxKgOnShelf = keyboard.nextInt();
//            System.out.println("Please enter the initial kg you would like to add to shelf: ");
//            int currentKgOnShelf = keyboard.nextInt();
//
//            if (currentKgOnShelf > maxKgOnShelf) {
//                System.out.println("That is too many kg for the shelf.");
//                return;
//            }
//
//            int warehouseBayNumber;
//
//            if (storageType == 'F') {
//                System.out.println("What warehouse bay number in the frozen section would you like to add the new goods to: ");
//                warehouseBayNumber = keyboard.nextInt();
//                if (frozengoods.size() < frozeninventory.getMaxNumberPalletBays()) {
//                    frozengoods.add(new BinGoodsOnPallet(currentKgPerBin, maxKgPerBin, maxKgOnShelf, currentKgOnShelf, stockCode, description, storageType, warehouseBayNumber));
//                } else {
//                    System.out.println("Not enough space in this warehouse for a new bin pallet.");
//                }
//            } else if (storageType == 'C') {
//                System.out.println("What warehouse bay number in the refrigeration section would you like to add the new goods to: ");
//                warehouseBayNumber = keyboard.nextInt();
//                if (refrigeratedgoods.size() < refrigeratedinventory.getMaxNumberPalletBays()) {
//                    refrigeratedgoods.add(new BinGoodsOnPallet(currentKgPerBin, maxKgPerBin, maxKgOnShelf, currentKgOnShelf, stockCode, description, storageType, warehouseBayNumber));
//                } else {
//                    System.out.println("Not enough space in this warehouse for a new bin pallet.");
//                }
//            } else if (storageType == 'R') {
//                System.out.println("What warehouse bay number in the room temperature section would you like to add the new goods to: ");
//                warehouseBayNumber = keyboard.nextInt();
//                if (roomtemperaturegoods.size() < roomtemperatureinventory.getMaxNumberPalletBays()) {
//                    roomtemperaturegoods.add(new BinGoodsOnPallet(currentKgPerBin, maxKgPerBin, maxKgOnShelf, currentKgOnShelf, stockCode, description, storageType, warehouseBayNumber));
//                } else {
//                    System.out.println("Not enough space in this warehouse for a new bin pallet.");
//                }
//            } else {
//                System.out.println("Invalid storage type. Please enter 'F', 'C', or 'R'.");
//            }
//        } catch (InputMismatchException e) {
//            System.out.println("Invalid input. Please enter a valid number.");
//            keyboard.nextLine();
//        }
//    }
    /**
     * Moves a specified amount of kg from a bin pallet type good in the
     * warehouse to the supermarket shelf.
     *
     * @param good The BinGoodsOnPallet object to move units from.
     * @param amountToMove The amount of kg to move.
     */
    public void binGoodsmoveUnits(BinGoodsOnPallet good, double amountToMove) {

        good.setCurrentKgPerBin(good.getCurrentKgPerBin() - amountToMove);

        good.setCurrentKgOnShelf(good.getCurrentKgOnShelf() + amountToMove);
    }

    /**
     * Moves a specified number of units from a cartonized goods item in the
     * warehouse to the supermarket shelf.
     *
     * @param good The CartonizedGoods object to move units from.
     * @param amountToMove The number of units to move.
     */
    public void CartonizedMoveUnits(CartonizedGoods good, int amountToMove) {

        good.setCurrentGoodsNumber(good.getCurrentGoodsNumber() - amountToMove);

        good.setCurrentNumberOfItemsOnShelf(good.getCurrentNumberOfItemsOnShelf() + amountToMove);
    }

    /**
     *
     * Searches goods arrays for goods matching stock code, returns goods data
     * to user and then asks them to input what quantity they want to move.
     *
     *
     */
    @Override
    public void moveItemFromWarehouseToShelf() {
        Scanner keyboard = new Scanner(System.in);

        try {
            System.out.println("Please enter a stock code: ");
            int stockCode = keyboard.nextInt();
            keyboard.nextLine();

            Goods foundGood = null;
            int returnStockCode = 0;

            for (Goods goods : frozengoods) {
                if (goods.getStockCode() == stockCode) {
                    foundGood = goods;
                    break;
                }
            }

            if (foundGood == null) {
                for (Goods goods : refrigeratedgoods) {
                    if (goods.getStockCode() == stockCode) {
                        foundGood = goods;
                        break;
                    }
                }
            }

            if (foundGood == null) {
                for (Goods goods : roomtemperaturegoods) {
                    if (goods.getStockCode() == stockCode) {
                        foundGood = goods;
                        break;
                    }
                }
            }

            if (foundGood == null) {
                for (Goods goods : flammablegoods) {
                    if (goods.getStockCode() == stockCode) {
                        foundGood = goods;
                        break;
                    }
                }
            }

            if (foundGood != null) {
                System.out.println("Here is the product that you requested: ");
                System.out.println(foundGood.toString());
                if (foundGood instanceof CartonizedGoods) {
                    System.out.println("How many units would you like to take from the warehouse and add to the supermarket bay?");
                    int units = keyboard.nextInt();
                    CartonizedGoods cartonizedGoods = (CartonizedGoods) foundGood;
                    CartonizedMoveUnits(cartonizedGoods, units);
                } else if (foundGood instanceof BinGoodsOnPallet) {
                    System.out.println("How many kg would you like to take from the warehouse and add to the supermarket bay?");
                    double kg = keyboard.nextDouble();
                    BinGoodsOnPallet bingoods = (BinGoodsOnPallet) foundGood;
                    binGoodsmoveUnits(bingoods, kg);
                }
                returnStockCode = foundGood.getStockCode();
            } else {
                System.out.println("Could not find a product with that StockCode.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid number.");
            keyboard.nextLine();
        }
    }

    ///https://mkyong.com/java/how-to-read-and-write-java-object-to-a-file/ (Source for serialized files work);
    @Override
    public void readObjectsFromSerializedText(ArrayList<Goods> readgoods, String filePath)
            throws IOException, ClassNotFoundException {
        try {
            FileInputStream fi = new FileInputStream(new File(filePath));
            ObjectInputStream oi = new ObjectInputStream(fi);

            while (true) {
                Goods good = (Goods) oi.readObject();
                readgoods.add(good);
            }
        } catch (EOFException e) {

        } catch (FileNotFoundException e) {

            System.out.println("File not found: " + e.getMessage());
        }
    }

    /**
     *
     * Helps load user data from serialized objects within text files within the
     * user directory.
     *
     *
     */
    public void loadSerializedDataForUser(User user) {
        String userDirectoryPath = System.getProperty("user.dir") + File.separator + user.getUserName();
        File userDirectory = new File(userDirectoryPath);

        try {
            if (userDirectory.exists() && userDirectory.isDirectory()) {
                File[] serializedFiles = userDirectory.listFiles((dir, name) -> name.startsWith("serialized") && name.endsWith(".txt"));
                if (serializedFiles != null) {
                    for (File serializedFile : serializedFiles) {
                        String fileName = serializedFile.getName().replace("serialized", "").replace(".txt", "");
                        ArrayList<Goods> goodsList = getGoodsListByFileName(fileName);
                        String filePath = userDirectoryPath + File.separator + "serialized" + fileName + ".txt";
                        readObjectsFromSerializedText(goodsList, filePath);
                    }
                } else {
                    System.out.println("No serialized files found for the user.");
                }
            } else {
                System.out.println("User directory does not exist.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while accessing files: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Error: Class not found while deserializing objects.");
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }

// helps determine which arrrays to load objects into
    public ArrayList<Goods> getGoodsListByFileName(String fileName) {
        try {
            switch (fileName) {
                case "Flammable":
                    return flammablegoods;
                case "Frozen":
                    return frozengoods;
                case "Refrigerated":
                    return refrigeratedgoods;
                case "RoomTemperature":
                    return roomtemperaturegoods;

                default:
                    throw new IllegalArgumentException("Unsupported file name: " + fileName);
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            return null;
        }
    }

    ///https://mkyong.com/java/how-to-read-and-write-java-object-to-a-file/
    @Override
    public void writeObjectsToSerializedText(ArrayList<Goods> writegoods, String listName, User userToWrite) {
        try {
            String userDirectoryPath = System.getProperty("user.dir") + File.separator + userToWrite.getUserName();
            String filePath = userDirectoryPath + File.separator + "serialized" + listName + ".txt";

            try ( FileOutputStream f = new FileOutputStream(new File(filePath));  ObjectOutputStream o = new ObjectOutputStream(f)) {

                for (Goods good : writegoods) {
                    o.writeObject(good);
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while writing serialized objects: " + e.getMessage());
        }
    }

    @Override
    public void writeObjectsToTextFile(ArrayList<Goods> goodsList, String fileName, User user) {
        try {
            String userDirectoryPath = System.getProperty("user.dir") + File.separator + user.getUserName();
            try ( FileWriter writer = new FileWriter(new File(userDirectoryPath, "savedInventory_" + fileName + ".txt"))) {
                for (Goods goods : goodsList) {
                    writer.write(goods.toString() + System.lineSeparator());
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while writing objects to text file: " + e.getMessage());
        }
    }

//unimplemented below as serves no purpose in current project scope
    public void formattedWriteObjectsToTextFile(ArrayList<Goods> goodsList, String fileName, User user) throws IOException {
        String userDirectoryPath = System.getProperty("user.dir") + File.separator + user.getUserName();
        try ( FileWriter writer = new FileWriter(new File(userDirectoryPath, "savedInventory_" + fileName + ".txt"))) {

            writer.write("Description\tStock Code\tStorage Type\tWarehouse Bay Number\tSupermarket Bay Number\t"
                    + "Current Kg In Warehouse Bin\tCurrent Kg on SuperMarket Shelf\tMax Kg per Bin\tMax Kg on shelf\t"
                    + "Max number of cartons per pallet\tMax number of goods per carton\tCurrent total goods in warehouse\t"
                    + "Current total cartons left in warehouse\tMax number of goods that can fit on supermarket shelf\t"
                    + "Current number of goods on supermarket shelf" + System.lineSeparator());

            for (Goods goods : goodsList) {
                if (goods instanceof CartonizedGoods) {
                    CartonizedGoods cartonizedGoods = (CartonizedGoods) goods;
                    writer.write(cartonizedGoods.getDescription() + "\t"
                            + cartonizedGoods.getStockCode() + "\t"
                            + cartonizedGoods.getStorageType() + "\t"
                            + cartonizedGoods.getWarehouseBayNumber() + "\t"
                            + cartonizedGoods.getSupermarketBayNumber() + "\t"
                            + "\t" + "\t" + "\t" + "\t" + "\t"
                            + cartonizedGoods.getMAX_NUMBER_CARTONS_PER_PALLET() + "\t"
                            + cartonizedGoods.getMAX_NUMBER_GOODS_PER_CARTON() + "\t"
                            + cartonizedGoods.getCurrentGoodsNumber() + "\t"
                            + cartonizedGoods.getCurrentCartonsNumber() + "\t"
                            + cartonizedGoods.getMaxNumberOfItemsShelf() + "\t"
                            + cartonizedGoods.getCurrentNumberOfItemsOnShelf() + System.lineSeparator());
                } else if (goods instanceof BinGoodsOnPallet) {
                    BinGoodsOnPallet binGoods = (BinGoodsOnPallet) goods;
                    writer.write(binGoods.getDescription() + "\t"
                            + binGoods.getStockCode() + "\t"
                            + binGoods.getStorageType() + "\t"
                            + binGoods.getWarehouseBayNumber() + "\t"
                            + binGoods.getSupermarketBayNumber() + "\t"
                            + binGoods.getCurrentKgPerBin() + "\t"
                            + binGoods.getCurrentKgOnShelf() + "\t"
                            + binGoods.getMaxKgPerBin() + "\t"
                            + binGoods.getMaxKgOnShelf() + "\t"
                            + "\t" + "\t" + "\t" + "\t" + "\t" + "\t" + System.lineSeparator());
                }
            }
        }
    }
// unimplemented method below as serves so purpose for application

    public void readObjectsFromTextFile(ArrayList<Goods> goodsList, String fileName) throws IOException {
        try ( BufferedReader reader = new BufferedReader(new FileReader("savedInventory_" + fileName + ".txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {

            }
        }
    }

    //Writes objects to excel spreadsheet, works in similar way to how serialized text files are written however method body is different.
    /**
     *
     *
     * Chat gpt assisted with developing and formatting this method.
     *
     */
    @Override
    public void writeObjectsToExcel(ArrayList<Goods> goodsList, String fileName, User user) throws IOException {
        String userDirectoryPath = System.getProperty("user.dir") + File.separator + user.getUserName();
        String excelFilePath = userDirectoryPath + File.separator + "savedInventory_" + fileName + ".xlsx";

        try ( Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet(fileName);

            Row headerRow = sheet.createRow(0);
            String[] columns = {
                "Description", "Stock Code", "Storage Type", "Warehouse Bay Number", "Supermarket Bay Number",
                "Current Kg In Warehouse Bin", "Current Kg on SuperMarket Shelf", "Max Kg per Bin", "Max Kg on shelf",
                "MAX_NUMBER_CARTONS_PER_PALLET", "MAX_NUMBER_GOODS_PER_CARTON", "currentGoodsNumber", "currentCartonsNumber",
                "maxNumberOfItemsShelf", "currentNumberOfItemsOnShelf"
            };
            for (int i = 0; i < columns.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(columns[i]);
            }

            int rowIndex = 1;
            for (Goods goods : goodsList) {
                Row dataRow = sheet.createRow(rowIndex++);
                dataRow.createCell(0).setCellValue(goods.getDescription());
                dataRow.createCell(1).setCellValue(goods.getStockCode());
                dataRow.createCell(2).setCellValue(String.valueOf(goods.getStorageType()));
                dataRow.createCell(3).setCellValue(goods.getWarehouseBayNumber());
                dataRow.createCell(4).setCellValue(goods.getSupermarketBayNumber());
                if (goods instanceof BinGoodsOnPallet) {
                    BinGoodsOnPallet bingoods = (BinGoodsOnPallet) goods;
                    dataRow.createCell(5).setCellValue(bingoods.getCurrentKgPerBin());
                    dataRow.createCell(6).setCellValue(bingoods.getCurrentKgOnShelf());
                    dataRow.createCell(7).setCellValue(bingoods.getMaxKgPerBin());
                    dataRow.createCell(8).setCellValue(bingoods.getMaxKgOnShelf());

                } else if (goods instanceof CartonizedGoods) {
                    CartonizedGoods cartongoods = (CartonizedGoods) goods;
                    dataRow.createCell(9).setCellValue(cartongoods.getMAX_NUMBER_CARTONS_PER_PALLET());
                    dataRow.createCell(10).setCellValue(cartongoods.getMAX_NUMBER_GOODS_PER_CARTON());
                    dataRow.createCell(11).setCellValue(cartongoods.getCurrentGoodsNumber());
                    dataRow.createCell(12).setCellValue(cartongoods.getCurrentCartonsNumber());
                    dataRow.createCell(13).setCellValue(cartongoods.getMaxNumberOfItemsShelf());
                    dataRow.createCell(14).setCellValue(cartongoods.getCurrentNumberOfItemsOnShelf());
                }

            }

            for (int i = 0; i < columns.length; i++) {
                sheet.autoSizeColumn(i);
            }

            try ( FileOutputStream fos = new FileOutputStream(excelFilePath)) {
                workbook.write(fos);
            } catch (IOException e) {
                System.out.println("An error occurred while writing objects to Excel file: " + e.getMessage());
            }
        }
    }

    // Chat gpt assisted with formatting this method.
    @Override
    public void readObjectsFromExcel(ArrayList<Goods> readgoods, String filePath) {
        try ( Workbook workbook = new XSSFWorkbook(new FileInputStream(filePath))) {
            Sheet sheet = workbook.getSheetAt(0);

            int rowIndex = 1;

            while (rowIndex <= sheet.getLastRowNum()) {
                Row row = sheet.getRow(rowIndex);

                try {
                    String description = getStringCellValue(row.getCell(0));
                    int stockCode = (int) getNumericCellValue(row.getCell(1));
                    String storageTypeString = row.getCell(2).getStringCellValue();
                    char storageType = storageTypeString.isEmpty() ? ' ' : storageTypeString.charAt(0);
                    int warehouseBayNumber = (int) getNumericCellValue(row.getCell(3));
                    int superMarketBayNumber = (int) getNumericCellValue(row.getCell(4));

                    Cell cellBin = row.getCell(5);
                    Cell cellCarton = row.getCell(9);

                    if (cellBin != null) {
                        double currentKgPerBin = getNumericCellValue(cellBin);
                        double currentKgOnShelf = getNumericCellValue(row.getCell(6));
                        double maxKgPerBin = getNumericCellValue(row.getCell(7));
                        double maxKgOnShelf = getNumericCellValue(row.getCell(8));
                        BinGoodsOnPallet goods = new BinGoodsOnPallet(currentKgPerBin, maxKgPerBin, maxKgOnShelf, currentKgOnShelf, stockCode, description, storageType, warehouseBayNumber, superMarketBayNumber);
                        readgoods.add(goods);
                    }

                    if (cellCarton != null) {
                        int MAX_NUMBER_CARTONS_PER_PALLET = (int) getNumericCellValue(cellCarton);
                        int MAX_NUMBER_GOODS_PER_CARTON = (int) getNumericCellValue(row.getCell(10));
                        int currentGoodsNumber = (int) getNumericCellValue(row.getCell(11));
                        int maxNumberOfItemsShelf = (int) getNumericCellValue(row.getCell(13));
                        int currentNumberOfItemsOnShelf = (int) getNumericCellValue(row.getCell(14));
                        CartonizedGoods goods = new CartonizedGoods(MAX_NUMBER_CARTONS_PER_PALLET, MAX_NUMBER_GOODS_PER_CARTON, currentGoodsNumber, maxNumberOfItemsShelf, currentNumberOfItemsOnShelf, stockCode, description, storageType, warehouseBayNumber, superMarketBayNumber);
                        readgoods.add(goods);
                    }
                } catch (Exception e) {
                    System.out.println("Error reading row at index " + rowIndex + ": " + e.getMessage());
                }

                rowIndex++;
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading objects from Excel file: " + e.getMessage());
        }
        System.out.println("Goods saved to database");

    }

// Suggested by chat gpt currently unimplemented but will use later to increase reliability of excel imports/exports.
    private String getStringCellValue(Cell cell) {
        if (cell == null) {
            return "";
        }

        if (cell.getCellType() == CellType.STRING) {
            return cell.getStringCellValue();
        } else {
            return "";
        }
    }

// Suggested by chat gpt currently unimplemented but will use later to increase reliability of excel imports/exports.
    private double getNumericCellValue(Cell cell) {
        if (cell == null) {
            return 0.0;
        }

        if (cell.getCellType() == CellType.NUMERIC) {
            return cell.getNumericCellValue();
        } else {
            return 0.0;
        }
    }

    /**
     *
     * Reads in Excel spreadsheet data to inventory object arrays for each
     * category of storage type. Similar formatting to existing loadData method.
     *
     *
     */
    public void loadExcelDataForUser(User user) {
        String userDirectoryPath = System.getProperty("user.dir") + File.separator + user.getUserName();
        File userDirectory = new File(userDirectoryPath);

        try {
            if (userDirectory.exists() && userDirectory.isDirectory()) {

                File[] excelFiles = userDirectory.listFiles((dir, name) -> name.startsWith("savedInventory_") && name.endsWith(".xlsx"));
                if (excelFiles != null) {
                    for (File excelFile : excelFiles) {
                        String fileName = excelFile.getName().replace("savedInventory_", "").replace(".xlsx", "");
                        ArrayList<Goods> goodsList = getGoodsListByFileName(fileName); // Implement this method
                        String filePath = userDirectoryPath + File.separator + "savedInventory_" + fileName + ".xlsx";

                        readObjectsFromExcel(goodsList, filePath);

                    }
                } else {
                    System.out.println("No Excel files found for the user.");
                }
            } else {
                System.out.println("User directory does not exist.");
            }
        } catch (Exception e) {
            System.out.println("An error occurred while loading Excel data for the user: " + e.getMessage());
        }

    }

    public void upateUserExcelSpreadSheets() throws IOException {
        try {
            this.writeObjectsToExcel(this.flammablegoods, "Flammable", user);
            this.writeObjectsToExcel(this.frozengoods, "Frozen", user);
            this.writeObjectsToExcel(this.refrigeratedgoods, "Refrigerated", user);
            this.writeObjectsToExcel(this.roomtemperaturegoods, "RoomTemperature", user);
        } catch (IOException e) {
            System.err.println("Error updating user Excel spreads: " + e.getMessage());

        }
    }//Saves serialized objects to text file

    public void saveToFile(User userToWrite) {
        this.writeObjectsToSerializedText(this.flammablegoods, "Flammable", userToWrite);
        this.writeObjectsToSerializedText(this.frozengoods, "Frozen", userToWrite);
        this.writeObjectsToSerializedText(this.refrigeratedgoods, "Refrigerated", userToWrite);
        this.writeObjectsToSerializedText(this.roomtemperaturegoods, "RoomTemperature", userToWrite);
    }
//Saves serialized objects to text file and saves object data to excel spreadsheets too. 

    public void saveToFileAndUpdateExcel(User userToWrite) throws IOException {
        this.writeObjectsToSerializedText(this.flammablegoods, "Flammable", userToWrite);
        this.writeObjectsToSerializedText(this.frozengoods, "Frozen", userToWrite);
        this.writeObjectsToSerializedText(this.refrigeratedgoods, "Refrigerated", userToWrite);
        this.writeObjectsToSerializedText(this.roomtemperaturegoods, "RoomTemperature", userToWrite);

        this.writeObjectsToExcel(this.flammablegoods, "Flammable", userToWrite);
        this.writeObjectsToExcel(this.frozengoods, "Frozen", userToWrite);
        this.writeObjectsToExcel(this.refrigeratedgoods, "Refrigerated", userToWrite);
        this.writeObjectsToExcel(this.roomtemperaturegoods, "RoomTemperature", userToWrite);

    }
//Saves user inventory data to readable text files

    public void saveFilesToText(User userToWrite) {
        this.writeObjectsToTextFile(this.flammablegoods, "Flammable", userToWrite);
        this.writeObjectsToTextFile(this.frozengoods, "Frozen", userToWrite);
        this.writeObjectsToTextFile(this.refrigeratedgoods, "Refrigerated", userToWrite);
        this.writeObjectsToTextFile(this.roomtemperaturegoods, "RoomTemperature", userToWrite);

    }

    //sorts each goods item in alphabetical order inside each list, is usually implemented before saving in case user has added any extra inventory
    @Override
    public void goodsSorter() {
        Collections.sort(this.flammablegoods);
        Collections.sort(this.frozengoods);
        Collections.sort(this.refrigeratedgoods);
        Collections.sort(this.roomtemperaturegoods);
    }

    public void dbAddCartonizedGoods(Goods goods, String tableName) throws SQLException {

        PreparedStatement insertStmt;
        String insertSQL = "INSERT INTO " + tableName + " (STOCK_CODE, PRODUCT_DESCRIPTION, STORAGE_TYPE, WAREHOUSE_BAY_NUM, SUPERMARKET_BAY_NUM, CURRENT_GOODS_WAREHOUSE_CARTONIZED, CURRENT_CARTONS_TOTAL, CURRENT_TOTAL_ITEMS_SHELF_CARTONIZED) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        String productStorage = String.valueOf(goods.getStorageType());
        insertStmt = dbManager.conn.prepareStatement(insertSQL);
        insertStmt.setInt(1, goods.getStockCode());
        insertStmt.setString(2, goods.getDescription());
        insertStmt.setString(3, productStorage);
        insertStmt.setInt(4, goods.getWarehouseBayNumber());
        insertStmt.setInt(5, goods.getSupermarketBayNumber());
        insertStmt.setInt(6, ((CartonizedGoods) goods).getCurrentGoodsNumber());
        insertStmt.setInt(7, ((CartonizedGoods) goods).getCurrentCartonsNumber());
        insertStmt.setInt(8, ((CartonizedGoods) goods).getCurrentNumberOfItemsOnShelf());
        insertStmt.executeUpdate();
    }

    public void dbAddBinGoods(Goods goods, String tableName) throws SQLException {

        PreparedStatement insertStmt;
        String insertSQL = "INSERT INTO " + tableName + " (STOCK_CODE, PRODUCT_DESCRIPTION, STORAGE_TYPE, WAREHOUSE_BAY_NUM, SUPERMARKET_BAY_NUM, CURRENT_KG_WAREHOUSE_BIN, CURRENT_KG_SHELF_BIN) VALUES (?, ?, ?, ?, ?, ?, ?)";
        String productStorage = String.valueOf(goods.getStorageType());
        insertStmt = dbManager.conn.prepareStatement(insertSQL);
        insertStmt.setInt(1, goods.getStockCode());
        insertStmt.setString(2, goods.getDescription());
        insertStmt.setString(3, productStorage);
        insertStmt.setInt(4, goods.getWarehouseBayNumber());
        insertStmt.setInt(5, goods.getSupermarketBayNumber());
        insertStmt.setDouble(6, ((BinGoodsOnPallet) goods).getCurrentKgPerBin());
        insertStmt.setDouble(7, ((BinGoodsOnPallet) goods).getCurrentKgOnShelf());
        insertStmt.executeUpdate();
    }

    public void checkDbGoodsValuesCartonized(ResultSet rs, Goods good, String tableName) throws SQLException {
        if (rs.getInt("CURRENT_GOODS_WAREHOUSE_CARTONIZED") != ((CartonizedGoods) good).getCurrentGoodsNumber()
                || rs.getInt("CURRENT_CARTONS_TOTAL") != ((CartonizedGoods) good).getCurrentCartonsNumber()
                || rs.getInt("CURRENT_TOTAL_ITEMS_SHELF_CARTONIZED") != ((CartonizedGoods) good).getCurrentNumberOfItemsOnShelf()) {

            String updateSQL = "UPDATE " + tableName + " SET CURRENT_GOODS_WAREHOUSE_CARTONIZED = ?, CURRENT_CARTONS_TOTAL = ?, CURRENT_TOTAL_ITEMS_SHELF_CARTONIZED = ? WHERE STOCK_CODE = ?";
            try ( PreparedStatement updateStmt = dbManager.conn.prepareStatement(updateSQL)) {
                updateStmt.setInt(1, ((CartonizedGoods) good).getCurrentGoodsNumber());
                updateStmt.setInt(2, ((CartonizedGoods) good).getCurrentCartonsNumber());
                updateStmt.setInt(3, ((CartonizedGoods) good).getCurrentNumberOfItemsOnShelf());
                updateStmt.setInt(4, good.getStockCode());

                updateStmt.executeUpdate();
            }
        }

    }

    public void checkDbGoodsValuesBin(ResultSet rs, Goods good, String tableName) throws SQLException {
        if (rs.getDouble("CURRENT_KG_WAREHOUSE_BIN") != ((BinGoodsOnPallet) good).getCurrentKgPerBin()
                || rs.getDouble("CURRENT_KG_SHELF_BIN") != ((BinGoodsOnPallet) good).getCurrentKgOnShelf()) {

            String updateSQL = "UPDATE " + tableName + " SET CURRENT_KG_WAREHOUSE_BIN = ?, CURRENT_KG_SHELF_BIN = ? WHERE STOCK_CODE = ?";
            try ( PreparedStatement updateStmt = dbManager.conn.prepareStatement(updateSQL)) {
                updateStmt.setDouble(1, ((BinGoodsOnPallet) good).getCurrentKgPerBin());
                updateStmt.setDouble(2, ((BinGoodsOnPallet) good).getCurrentKgOnShelf());
                updateStmt.setInt(3, good.getStockCode());
                updateStmt.executeUpdate();
            }
        }

    }

    public void syncDatabase() {
        syncObjectsToDB(flammablegoods, "FLAMMABLE_GOODS");
        syncObjectsToDB(frozengoods, "FROZEN_GOODS");
        syncObjectsToDB(refrigeratedgoods, "REFRIGERATED_GOODS");
        syncObjectsToDB(roomtemperaturegoods, "ROOM_TEMP_GOODS");
    }

    public void syncObjectsToDB(ArrayList<Goods> goodsArray, String tableName) {
        //dbManager.checkUserDataBaseExists()

        try {
            for (Goods goods : goodsArray) {
                String checkSQL = "SELECT * FROM " + tableName + " WHERE STOCK_CODE = ?";

                try ( PreparedStatement checkStmt = dbManager.conn.prepareStatement(checkSQL)) {
                    checkStmt.setInt(1, goods.getStockCode());

                    try ( ResultSet rs = checkStmt.executeQuery()) {
                        if (!rs.next()) {
                            // Insert new row

                            if (goods instanceof CartonizedGoods) {
                                dbAddCartonizedGoods(goods, tableName);

                            } else if (goods instanceof BinGoodsOnPallet) {
                                dbAddBinGoods(goods, tableName);
                            }
                        } else {
                            if (goods instanceof CartonizedGoods) {
                                checkDbGoodsValuesCartonized(rs, goods, tableName);
                            } else if (goods instanceof BinGoodsOnPallet) {
                                checkDbGoodsValuesBin(rs, goods, tableName);
                            }
                        }
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Error syncing objects to DB: " + e.getMessage());
        }
    }

    public void loadDbTablesIntoInventory() {
        readInDbInventoryLists();
    }

    public void readInDbInventoryLists() {
        this.readDbGoods(frozengoods, "FROZEN_GOODS");
        this.readDbGoods(flammablegoods, "FLAMMABLE_GOODS");
        this.readDbGoods(refrigeratedgoods, "REFRIGERATED_GOODS");
        this.readDbGoods(roomtemperaturegoods, "ROOM_TEMP_GOODS");
    }

    public void readDbGoods(ArrayList<Goods> goods, String tableName) {

        try (
                 PreparedStatement pstmt = dbManager.conn.prepareStatement("SELECT * FROM " + tableName)) {

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int stockCode = rs.getInt("STOCK_CODE");
                String description = rs.getString("PRODUCT_DESCRIPTION");
                String storageTypeString = rs.getString("STORAGE_TYPE");
                if (storageTypeString == null || storageTypeString.isEmpty()) {
                    throw new IllegalStateException("Storage type is null or empty");
                }
                char storageType = storageTypeString.charAt(0);
                int wareHouseBayNum = rs.getInt("WAREHOUSE_BAY_NUM");
                int superMarketBayNum = rs.getInt("SUPERMARKET_BAY_NUM");
                //For Carton goods. 
                int currentGoodsWarehouse = rs.getInt("CURRENT_GOODS_WAREHOUSE_CARTONIZED");
                if (rs.wasNull()) {
                    double currentKgInWareHouse = rs.getDouble("CURRENT_KG_WAREHOUSE_BIN");
                    double currentKgOnShelf = rs.getDouble("CURRENT_KG_SHELF_BIN");
                    BinGoodsOnPallet binItem = new BinGoodsOnPallet(currentKgInWareHouse, 1000, 100, currentKgOnShelf, stockCode, description, storageType, wareHouseBayNum, superMarketBayNum);
                    goods.add(binItem);
                } else {

                    int currentShelfItems = rs.getInt("CURRENT_TOTAL_ITEMS_SHELF_CARTONIZED");
                    CartonizedGoods cartonItem = new CartonizedGoods(1000, 10, currentGoodsWarehouse, 100, currentShelfItems, stockCode, description, storageType, wareHouseBayNum, superMarketBayNum);
                    goods.add(cartonItem);
                }

            }
            System.out.println("Table loaded: " + tableName);
        } catch (Exception e) {
            System.err.println("Error occurred while reading from the database: " + e.getMessage());
            e.printStackTrace();
        }

    }

    @Override
    public void addGoods() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
