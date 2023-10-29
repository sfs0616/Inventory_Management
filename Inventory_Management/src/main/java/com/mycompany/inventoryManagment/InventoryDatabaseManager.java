package com.mycompany.inventoryManagment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * The InventoryDatabaseManager class provides functionalities to manage a
 * database connection and perform various database operations.
 *
 * @author Avraam
 */
public class InventoryDatabaseManager {

    // Static initializer block to load the JDBC driver
    static {
        try {
            // Loading the JDBC driver class
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        } catch (ClassNotFoundException e) {
            // Print the stack trace if the driver class is not found
            e.printStackTrace();
        }
    }

    // User object to store the current user's information
    private User user;
    // Database username
    private String USER_NAME;
    // Database password (constant value)
    private static final String PASSWORD = "pdc";
    // Database connection URL
    private String URL;
    // Connection object to manage the database connection
    Connection conn;

    /**
     * Constructor to initialize the InventoryDatabaseManager object.
     *
     * @param user User object containing user's information
     */
    public InventoryDatabaseManager(User user) {
        this.user = user;
        // Setting the database username from the user object
        this.USER_NAME = user.getUserName();
    }

    /**
     * Sets the User object and updates the database username.
     *
     * @param user User object containing the new user's information
     */
    public void setUser(User user) {
        this.user = user;
        this.USER_NAME = user.getUserName();
    }

    /**
     * Obtains a connection to the database. If the connection does not exist or
     * is closed, it establishes a new connection.
     *
     * @return Connection object representing the database connection
     * @throws SQLException If a database access error occurs or the URL is null
     */
    public Connection getConnection() throws SQLException {
        if (this.conn == null || this.conn.isClosed()) {
            establishConnection();
        }
        return this.conn;
    }

    /**
     * Checks if a connection can be established to the database using the
     * current user's credentials and database URL.
     */
    public void checkDbUrlConnection() {
        try {
            // Attempt to establish a connection to the database
            DriverManager.getConnection(this.user.databaseURL, this.user.userName, this.PASSWORD);
            // Close any existing connections
            closeConnections();
            System.out.println("Database Connects ok");
        } catch (SQLException e) {
            if (e.getErrorCode() == 40000) {
                // Handle specific error code for database not found
            } else {
                // Print error message for other SQL exceptions
                System.err.println("Error while checking database connection: " + e.getMessage());
            }
        }
    }

    /**
     * Checks if the database URL exists and is not empty.
     *
     * @return true if the database URL exists and is not empty, false otherwise
     */
    public boolean checkURLexists() {
        return this.user.databaseURL != null && !this.user.databaseURL.trim().isEmpty();
    }

    /**
     * Creates a new database for the new user along with the required tables.
     */
    public void createDataBaseForNewUser() {
        // Constructing the database URL for the new user
        String newDBURL = "jdbc:derby:" + "userdb_" + USER_NAME + "Edb" + ";create=true";
        System.out.println("Create new database called");
        // Setting the database URL for the user
        this.user.setDatabaseURL("jdbc:derby:" + "userdb_" + USER_NAME + "Edb");

        try {
            // Establishing a connection to the new database
            Connection newDBConnection = DriverManager.getConnection(newDBURL, USER_NAME, PASSWORD);

            // Initializing a statement for executing SQL commands
            Statement statement = newDBConnection.createStatement();

            // Array of SQL commands to create tables
            String[] createTableCommands = {
                "CREATE TABLE FROZEN_GOODS (\n"
                + "STOCK_CODE INT PRIMARY KEY NOT NULL,\n"
                + "PRODUCT_DESCRIPTION VARCHAR(100) NOT NULL,\n"
                + "STORAGE_TYPE VARCHAR(1) NOT NULL,\n"
                + "WAREHOUSE_BAY_NUM INT NOT NULL,\n"
                + "SUPERMARKET_BAY_NUM INT NOT NULL,\n"
                + "CURRENT_GOODS_WAREHOUSE_CARTONIZED INT,\n"
                + "CURRENT_CARTONS_TOTAL INT,\n"
                + "CURRENT_TOTAL_ITEMS_SHELF_CARTONIZED INT,\n"
                + "CURRENT_KG_WAREHOUSE_BIN DECIMAL(10, 2),\n"
                + "CURRENT_KG_SHELF_BIN DECIMAL(10, 2))\n",
                "CREATE TABLE FLAMMABLE_GOODS (\n"
                + "STOCK_CODE INT PRIMARY KEY NOT NULL,\n"
                + "PRODUCT_DESCRIPTION VARCHAR(100) NOT NULL,\n"
                + "STORAGE_TYPE VARCHAR(1) NOT NULL,\n"
                + "WAREHOUSE_BAY_NUM INT NOT NULL,\n"
                + "SUPERMARKET_BAY_NUM INT NOT NULL,\n"
                + "CURRENT_GOODS_WAREHOUSE_CARTONIZED INT,\n"
                + "CURRENT_CARTONS_TOTAL INT,\n"
                + "CURRENT_TOTAL_ITEMS_SHELF_CARTONIZED INT,\n"
                + "CURRENT_KG_WAREHOUSE_BIN DECIMAL(10, 2),\n"
                + "CURRENT_KG_SHELF_BIN DECIMAL(10, 2))\n",
                "CREATE TABLE REFRIGERATED_GOODS (\n"
                + "STOCK_CODE INT PRIMARY KEY NOT NULL,\n"
                + "PRODUCT_DESCRIPTION VARCHAR(100) NOT NULL,\n"
                + "STORAGE_TYPE VARCHAR(1) NOT NULL,\n"
                + "WAREHOUSE_BAY_NUM INT NOT NULL,\n"
                + "SUPERMARKET_BAY_NUM INT NOT NULL,\n"
                + "CURRENT_GOODS_WAREHOUSE_CARTONIZED INT,\n"
                + "CURRENT_CARTONS_TOTAL INT,\n"
                + "CURRENT_TOTAL_ITEMS_SHELF_CARTONIZED INT,\n"
                + "CURRENT_KG_WAREHOUSE_BIN DECIMAL(10, 2),\n"
                + "CURRENT_KG_SHELF_BIN DECIMAL(10, 2))\n",
                "CREATE TABLE ROOM_TEMP_GOODS (\n"
                + "STOCK_CODE INT PRIMARY KEY NOT NULL,\n"
                + "PRODUCT_DESCRIPTION VARCHAR(100) NOT NULL,\n"
                + "STORAGE_TYPE VARCHAR(1) NOT NULL,\n"
                + "WAREHOUSE_BAY_NUM INT NOT NULL,\n"
                + "SUPERMARKET_BAY_NUM INT NOT NULL,\n"
                + "CURRENT_GOODS_WAREHOUSE_CARTONIZED INT,\n"
                + "CURRENT_CARTONS_TOTAL INT,\n"
                + "CURRENT_TOTAL_ITEMS_SHELF_CARTONIZED INT,\n"
                + "CURRENT_KG_WAREHOUSE_BIN DECIMAL(10, 2),\n"
                + "CURRENT_KG_SHELF_BIN DECIMAL(10, 2))\n"
            };

            // Executing each table creation command
            for (String command : createTableCommands) {
                statement.execute(command);
                System.out.println("Table created: " + command.split(" ")[2]);
            }
            // Closing the statement and connection
            statement.close();
            newDBConnection.close();
            System.out.println("New database and tables created successfully for user: " + USER_NAME);
        } catch (SQLException e) {
            System.err.println("Error while creating new database: " + e.getMessage());
            e.printStackTrace();
        }
    }

  /**
     * Establishes a connection to the database.
     */
    public void establishConnection() {
        try {
            // Establishing a connection to the database
            this.conn = DriverManager.getConnection(user.databaseURL, USER_NAME, PASSWORD);
            System.out.println("Connection established successfully!");
            System.out.println(user.getDatabaseURL());
        } catch (SQLException e) {
            // Printing an error message if a connection cannot be established
            System.err.println("Error while establishing a connection: " + e.getMessage());
            
        }
    }

    /**
     * Closes the database connection.
     */
    public void closeConnections() {
        if (conn != null) {
            try {
                // Closing the connection
                conn.close();
                System.out.println("Connections closed");
            } catch (SQLException ex) {
                // Printing an error message if the connection cannot be closed
                System.out.println(ex.getMessage());
            }
        }
    }

    /**
     * Executes a SELECT query on the database.
     *
     * @param sql The SQL query string
     * @return ResultSet object containing the results of the query
     */
    public ResultSet queryDB(String sql) {
        Connection connection = this.conn;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            // Creating a statement and executing the query
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
        } catch (SQLException ex) {
            // Printing an error message if the query execution fails
            System.out.println(ex.getMessage());
        }
        return resultSet;
    }

    /**
     * Executes an UPDATE, INSERT, or DELETE query on the database.
     *
     * @param sql The SQL query string
     */
    public void updateDB(String sql) {
        Connection connection = this.conn;
        Statement statement = null;

        try {
            // Creating a statement and executing the update query
            statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException ex) {
            // Printing an error message if the update query execution fails
            System.out.println(ex.getMessage());
        } finally {
            if (statement != null) {
                try {
                    // Closing the statement
                    statement.close();
                } catch (SQLException ex) {
                    // Printing an error message if the statement cannot be closed
                    System.out.println(ex.getMessage());
                }
            }
        }
    }
}
