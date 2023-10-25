/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.inventoryManagment;

import java.io.Serializable;

/**
 * Represents a user in the inventory management system.
 * This class stores information about the user, including the username.
 * It implements the Serializable interface to support object serialization.
 * 
 * @author Avraa
 */
public class User implements Serializable {
    String userName;
    String databaseURL;
    

    /**
     * Constructs a new User object.
     */
    public User() {
        // Default constructor
    }

    /**
     * Retrieves the username of the user.
     * 
     * @return The username of the user.
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets the username of the user.
     * 
     * @param userName The username to set.
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    public String getDatabaseURL(){
        return this.databaseURL;
    }

    public void setDatabaseURL(String databaseURL) {
        this.databaseURL = databaseURL;
    }
   @Override 
   public String toString(){
       return userName + " " + databaseURL;
   }
    
    
}


