///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package com.mycompany.inventoryManagment;
//import java.util.InputMismatchException;
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.sql.SQLException;
//import java.util.Scanner;
//
///**
// *
// * @author Avraam Ardelean, SID 14883856
// */
//
//
//public class InventoryApplicationCUI {
//
//    public static void main(String[] args) throws IOException, FileNotFoundException, ClassNotFoundException, SQLException {
//        // Initialize exit flag and scanner for user input
//        boolean exitProgram = false;
//        Scanner keyboard = new Scanner(System.in);
//        InventoryLists warehousesupermarket = new InventoryLists();
//        // Initialize user management, user, and inventory objects
//        UserManagement users = new UserManagement();
//        User userToWrite = new User();
//        
//        // Load existing user data from file
//        users.loadFromFile("users.dat");
//
//        System.out.println("Enter your username:");
//        String username = keyboard.nextLine();
//        // Check if the user already exists
//        boolean ifUserExists = false;
//        for (User user : users.users) {
//            if (user.getUserName().equals(username)) {
//                ifUserExists = true;
//                break; 
//            }
//        }
//        
//        if (ifUserExists) {
//            System.out.println("User exists");
//            // Load matched user's data
//            User matchedUser = users.getUserByUsername(username);
//            if (matchedUser != null) {
//
//                userToWrite = matchedUser;
//                
//                warehousesupermarket.setUser(userToWrite);
//                int dataOrExcel = -1; 
//        // Loop until valid dataOrExcel choice is made
//                do {
//                    try {
//                        System.out.println("Do you want to load inventory data from saved data or Excel in directory?");
//                        System.out.println("1: Saved data");
//                        System.out.println("2: Excel");
//                        dataOrExcel = keyboard.nextInt();
//
//                        if (dataOrExcel == 1) {
//                            warehousesupermarket.loadSerializedDataForUser(userToWrite);
//                        } else if (dataOrExcel == 2) {
//                            warehousesupermarket.loadExcelDataForUser(userToWrite);
//                        } else {
//                            System.out.println("Invalid input. Please enter 1 for saved data or 2 for Excel.");
//                        }
//                    } catch (InputMismatchException e) {
//                        System.out.println("Invalid input. Please enter a valid number.");
//                        keyboard.next(); 
//                    }
//                } while (dataOrExcel < 1 || dataOrExcel > 2); 
//
//            }
//        } else {
//
//            // Create a new user
//            User newUser = new User();
//            newUser.setUserName(username);
//            userToWrite = newUser;
//            users.addUser(newUser);
//            warehousesupermarket.setUser(userToWrite);
//
//            // Create a directory for the new user
//            String userDirectoryPath = System.getProperty("user.dir") + File.separator + username;
//            File userDirectory = new File(userDirectoryPath);
//            boolean directoryCreated = userDirectory.mkdir();
//
//            if (directoryCreated) {
//                System.out.println("User directory created.");
//            } else {
//                System.out.println("Failed to create user directory.");
//            }
//        }
//        warehousesupermarket.establishDatabaseManager();
//        Boolean userDat = warehousesupermarket.dbManager.checkURLexists();
//        
//            warehousesupermarket.dbManager.establishConnection();
//        
//
//        System.out.println("Welcome to the Inventory Application.");
//
//        while (!exitProgram) {
//            System.out.println("What would you like to do: input number and press enter ");
//            System.out.println("0: Input 0 to save inventory and exit programme.");//
//            System.out.println("1: Change max number of warehouse pallet/bin locations.");//
//            System.out.println("2: Change max number of supermarket shelf locations.");//
//            System.out.println("3: Add new types of goods to the inventory.");
//            System.out.println("4: Move goods from warehouse to supermarket shelves");
//            System.out.println("5: Print current inventory.");
//
//            int mainMenuInput = -1; 
//             // Loop until valid mainMenuInput choice is made
//            do {
//                try {
//                    mainMenuInput = keyboard.nextInt();
//
//                    switch (mainMenuInput) {
//                        case 0: {
//                            exitProgram = true;
//                            break;
//                        }
//
//                        case 1: {
//                            warehousesupermarket.changeNumberOfPallets();
//                            break;
//                        }
//
//                        case 2: {
//                            warehousesupermarket.changeNumberOfShelves();
//                            break;
//                        }
//
//                        case 3: {
//                            warehousesupermarket.addGoods();
//                            break;
//                        }
//
//                        case 4: {
//                            warehousesupermarket.moveItemFromWarehouseToShelf();
//                            break;
//                        }
//
//                        case 5: {
//                            warehousesupermarket.printInventory();
//                            break;
//                        }
//                        default:
//                            System.out.println("Invalid input. Please enter a valid option.");
//                    }
//                } catch (InputMismatchException e) {
//                    System.out.println("Invalid input. Please enter a valid number.");
//                    keyboard.next();
//                }
//            } while (mainMenuInput < 0);
//        }
//        // Save user data to file
//        users.saveToFile("users.dat");
//
//        System.out.println("Saved user data.");
//
//        warehousesupermarket.goodsSorter();
//
//        int filesToSave = 0; 
//        // Loop until valid filesToSave choice is made
//        do {
//            System.out.println("Do you want to update Excel spreadsheet as well or just save Inventory?");
//            System.out.println("1: Just save inventory");
//            System.out.println("2: Save inventory and update Excel spreadsheet");
//
//            try {
//                filesToSave = keyboard.nextInt();
//                // Based on filesToSave choice, save inventory and update Excel spreadsheet
//                if (filesToSave != 1 && filesToSave != 2) {
//                    throw new IllegalArgumentException("Invalid input. Please enter 1 or 2.");
//                }
//            } catch (InputMismatchException e) {
//                System.out.println("Invalid input. Please enter a valid number.");
//                keyboard.nextLine(); 
//            } catch (IllegalArgumentException e) {
//                System.out.println(e.getMessage());
//            }
//        } while (filesToSave != 1 && filesToSave != 2);
//
//        if (filesToSave == 1) {
//            warehousesupermarket.saveToFile(userToWrite);
//        } else {
//            warehousesupermarket.saveToFileAndUpdateExcel(userToWrite);
//        }
//        // Save inventory data to text files
//        warehousesupermarket.saveFilesToText(userToWrite);
//        System.out.println("Saved files");
//        System.out.println("Exiting the Inventory Application.");
//        
//        warehousesupermarket.syncObjectsToDB(warehousesupermarket.frozengoods, "FROZEN_GOODS");
//        warehousesupermarket.syncObjectsToDB(warehousesupermarket.flammablegoods, "FLAMMABLE_GOODS ");
//        warehousesupermarket.syncObjectsToDB(warehousesupermarket.roomtemperaturegoods, "ROOM_TEMP_GOODS ");
//        warehousesupermarket.syncObjectsToDB(warehousesupermarket.refrigeratedgoods, "REFRIGERATED_GOODS ");
//        
//        warehousesupermarket.dbManager.closeConnections();
//    }
//
//}
