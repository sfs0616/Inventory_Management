/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.inventoryManagment;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * A class responsible for managing users in the inventory management system.
 * This class provides methods for adding users, checking if a user exists,
 * retrieving users by their usernames, saving user data to a file, and loading user data from a file.
 * It also maintains a collection of users.
 * 
 * The methods in this class allow for user management functionalities such as adding new users,
 * checking for user existence, retrieving user information, and handling user data persistence.
 * 
 * @author Avraa
 */
public class UserManagement{
    
    Scanner keyboard = new Scanner(System.in);
    
    Set<User> users = new LinkedHashSet<>();
    

    public Set<User> getUsers() {
        return users;
    }

    public Scanner getKeyboard() {
        return keyboard;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public void setKeyboard(Scanner keyboard) {
        this.keyboard = keyboard;
    }

    /**
     * Check if a user with the given username exists in the user collection.
     * 
     * @param username The username to check.
     * @return True if the user exists, false otherwise.
     */
    public boolean containsUser(String username) {
        return users.stream().anyMatch(user -> user.getUserName().equals(username));
    }
    
    /**
     * Add a new user to the user collection.
     * 
     * @param user The user to add.
     */
    public void addUser(User user) {
        users.add(user);
    }
    
    /**
     * Get a user by their username from the user collection.
     * 
     * @param username The username of the user to retrieve.
     * @return The user with the specified username, or null if not found.
     */
    public User getUserByUsername(String username) {
        for (User user : users) {
            if (user.getUserName().equals(username)) {
                return user;
            }
        }
        return null;
    }
    
    /**
     * Create a new user and add it to the user collection.
     */
    public void createUser() {
        User user = new User();
        addUser(user);
    }
    
    /**
     * Save the user data to a file.
     * 
     * @param filename The name of the file to save to.
     */
    public void saveToFile(String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(users);
        } catch (IOException e) {
            System.out.println("Error saving user data");
            e.printStackTrace();
        }
    }
    
    /**
     * Load user data from a file.
     * 
     * @param filename The name of the file to load from.
     */
    public void loadFromFile(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            users = (Set<User>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading user data");
            System.out.println("Either input error or user class not found");
            e.printStackTrace();
        }
    }
    
}


