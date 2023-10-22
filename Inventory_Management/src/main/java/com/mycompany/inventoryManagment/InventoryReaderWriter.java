/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.inventoryManagment;

import java.io.IOException;
import java.util.ArrayList;
//Chat gpt assisted with making serialized file methods and excel file  read and write methods. 
/**
 * An interface defining methods for reading and writing inventory data.
 * These methods provide functionality for serializing and deserializing inventory data
 * to and from various formats such as text files and Excel spreadsheets.
 * 
 * The interface outlines methods for different operations related to inventory data management.
 * Implementing classes are expected to provide concrete implementations for these methods.
 * 
 * @author Avraa
 */
public interface InventoryReaderWriter {
    
    /**
     * Write a list of goods to a serialized text file.
     * 
     * @param writegoods The list of goods to be written.
     * @param listName The name of the list.
     * @param userToWrite The user object associated with the goods.
     */
    public void writeObjectsToSerializedText(ArrayList<Goods> writegoods, String listName, User userToWrite);
    
    /**
     * Read a list of goods from a serialized text file.
     * 
     * @param readgoods The list of goods to be populated from the file.
     * @param filePath The path to the serialized text file.
     * @throws IOException If an I/O error occurs while reading the file.
     * @throws ClassNotFoundException If the class of the serialized object cannot be found.
     */
    public void readObjectsFromSerializedText(ArrayList<Goods> readgoods, String filePath)
            throws IOException, ClassNotFoundException;
    
    /**
     * Write a list of goods to a text file.
     * 
     * @param goodsList The list of goods to be written.
     * @param fileName The name of the file.
     * @param user The user object associated with the goods.
     */
    public void writeObjectsToTextFile(ArrayList<Goods> goodsList, String fileName, User user);
    
    /**
     * Read a list of goods from a text file.
     * 
     * @param goodsList The list of goods to be populated from the file.
     * @param fileName The name of the file.
     * @throws IOException If an I/O error occurs while reading the file.
     */
    public void readObjectsFromTextFile(ArrayList<Goods> goodsList, String fileName) throws IOException;
    
    /**
     * Write a list of goods to an Excel spreadsheet.
     * 
     * @param goodsList The list of goods to be written.
     * @param fileName The name of the Excel spreadsheet file.
     * @param user The user object associated with the goods.
     * @throws IOException If an I/O error occurs while writing the spreadsheet.
     */
    public void writeObjectsToExcel(ArrayList<Goods> goodsList, String fileName, User user) throws IOException;
    
    /**
     * Read a list of goods from an Excel spreadsheet.
     * 
     * @param readgoods The list of goods to be populated from the spreadsheet.
     * @param filePath The path to the Excel spreadsheet file.
     */
    public void readObjectsFromExcel(ArrayList<Goods> readgoods, String filePath);
}

