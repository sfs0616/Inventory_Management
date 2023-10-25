/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.inventoryManagment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Avraam
 */
public class InventoryDatabaseManager {
     static {
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    private User user;
    private String USER_NAME; //your DB username
    private static final String PASSWORD = "pdc"; //your DB password
    private String URL;  //url of the DB host

    Connection conn;

    public InventoryDatabaseManager(User user) {
        this.user = user;
        this.USER_NAME = user.getUserName();
        this.URL = user.getDatabaseURL();
    }

     
     
     public void setUser(User user){
         this.user = user;
     }

//    public static void main(String[] args) throws SQLException {
//        InventoryDatabaseManager dbManager = new InventoryDatabaseManager();
//        
//        System.out.println(dbManager.getConnection());
//        
//        dbManager.closeConnections();
//        //System.out.println(dbManager.closeConnection());
//       
//    }

    public Connection getConnection() throws SQLException {
    if (this.conn == null || this.conn.isClosed()) {
        establishConnection();
    }
    return this.conn;
}
    
    public boolean checkUserDataBaseExists() {
    boolean exists = false;
    if (URL != null && !URL.trim().isEmpty()) {
        try {
            DriverManager.getConnection(URL, USER_NAME, PASSWORD);
            closeConnections();
            exists = true;
        } catch (SQLException e) {
            if (e.getErrorCode() == 40000) { // Specific error code for database not found
                exists = false;
            } else {
                System.err.println("Error while checking database existence: " + e.getMessage());
            }
        }
    }
    if (!exists) {
       createDataBaseForNewUser();
    }
    return exists;
}
    //Used chat gpt to figure out how to do this.
    public void createDataBaseForNewUser() {
    String newDBURL = "jdbc:derby:" + "userdb_" + USER_NAME + "Edb" + ";create=true";
   
    try {
        // Create the new database
        Connection newDBConnection = DriverManager.getConnection(newDBURL, USER_NAME, PASSWORD);

        // Initialize tables in the new database
        Statement statement = newDBConnection.createStatement();
        
        String[] createTableCommands = {
            "CREATE TABLE FROZEN_GOODS (STOCK_CODE INT, PRODUCT_DESCRIPTION VARCHAR(100), STORAGE_TYPE VARCHAR(1), WAREHOUSE_BAY_NUM INT, SUPERMARKET_BAY_NUM INT, CURRENT_GOODS_TOTAL INT, CURRENT_CARTONS_TOTAL INT, CURRENT_TOTAL_ITEMS_SHELF INT, CURRENT_KG_BIN INT, CURRENT_KG_SHELF INT)",
            "CREATE TABLE REFRIGERATED_GOODS (STOCK_CODE INT, PRODUCT_DESCRIPTION VARCHAR(100), STORAGE_TYPE VARCHAR(1), WAREHOUSE_BAY_NUM INT, SUPERMARKET_BAY_NUM INT, CURRENT_GOODS_TOTAL INT, CURRENT_CARTONS_TOTAL INT, CURRENT_TOTAL_ITEMS_SHELF INT, CURRENT_KG_BIN INT, CURRENT_KG_SHELF INT)",
            "CREATE TABLE ROOM_TEMP_GOODS (STOCK_CODE INT, PRODUCT_DESCRIPTION VARCHAR(100), STORAGE_TYPE VARCHAR(1), WAREHOUSE_BAY_NUM INT, SUPERMARKET_BAY_NUM INT, CURRENT_GOODS_TOTAL INT, CURRENT_CARTONS_TOTAL INT, CURRENT_TOTAL_ITEMS_SHELF INT, CURRENT_KG_BIN INT, CURRENT_KG_SHELF INT)",
            "CREATE TABLE FLAMMABLE_GOODS (STOCK_CODE INT, PRODUCT_DESCRIPTION VARCHAR(100), STORAGE_TYPE VARCHAR(1), WAREHOUSE_BAY_NUM INT, SUPERMARKET_BAY_NUM INT, CURRENT_GOODS_TOTAL INT, CURRENT_CARTONS_TOTAL INT, CURRENT_TOTAL_ITEMS_SHELF INT, CURRENT_KG_BIN INT, CURRENT_KG_SHELF INT)"
        };
        
        for (String command : createTableCommands) {
            statement.execute(command);
            System.out.println("Table created: " + command.split(" ")[2]);
        }
        statement.close();
        newDBConnection.close();

       
        // Update the class variable URL
         user.setDatabaseURL("jdbc:derby:" + "userdb_" + USER_NAME + "Edb");
         
        System.out.println("New database and tables created successfully for user: " + USER_NAME);
        
        
    } catch (SQLException e) {
        System.err.println("Error while creating new database: " + e.getMessage());
        
    }
    
}

    //Establish connection
     public void establishConnection() {
    try {
        // Establish a connection to Database
        this.conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
        System.out.println("Connection established successfully!");
        System.out.println(user.getDatabaseURL());
    } catch (SQLException e) {
        System.err.println("Error while establishing a connection: " + e.getMessage());
        // You may want to log the exception or take appropriate error-handling actions.
    }
}
    


    public void closeConnections() {
        if (conn != null) {
            try {
                conn.close();
                System.out.println("Connections closed");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public ResultSet queryDB(String sql) {

        Connection connection = this.conn;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return resultSet;
    }

    public void updateDB(String sql) {
        
        

        Connection connection = this.conn;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.createStatement();
            statement.executeUpdate(sql);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
}
