/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.inventoryManagment;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Avraam
 */
public class InventoryPanel extends JPanel implements Observer {

    private ArrayList<Goods> frozengoods = new ArrayList<>();
    private ArrayList<Goods> refrigeratedgoods = new ArrayList<>();
    private ArrayList<Goods> flammablegoods = new ArrayList<>();
    private ArrayList<Goods> roomtemperaturegoods = new ArrayList<>();
    private InventoryModel model;
    Dimension preferredSize = new Dimension(300, 25);
    private String[] columnNames = {"STOCK CODE", "PRODUCT DESCRIPTION", "STORAGE_TYPE", "WAREHOUSE BAY NUM", "SUPERMARKET BAY NUM", "CURRENT CARTON ITEMS WAREHOUSE", "CURRENT CARTONS TOTAL", "CURRENT CARTON ITEMS SHELF", "CURRENT KG WAREHOUSE BIN", "CURRENT KG SHELF BIN"};
    private DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    private JTable table;
    private JScrollPane scrollPane = new JScrollPane(table);
    private JButton frozenButton; 
    private JButton flammableButton; 
    private JButton refrigeratedButton;
    private JButton roomTemperatureButton;
    private JButton addCartonizedGoods; 
    private JButton addBinGoods;


    private JButton moveGoods; 
    private JButton deleteGoodsPanelButton;

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

    public void setFrozengoods(ArrayList<Goods> frozengoods) {
        this.frozengoods = frozengoods;
    }

    public void setRefrigeratedgoods(ArrayList<Goods> refrigeratedgoods) {
        this.refrigeratedgoods = refrigeratedgoods;
    }

    public void setFlammablegoods(ArrayList<Goods> flammablegoods) {
        this.flammablegoods = flammablegoods;
    }

    public void setRoomtemperaturegoods(ArrayList<Goods> roomtemperaturegoods) {
        this.roomtemperaturegoods = roomtemperaturegoods;
    }

    public InventoryPanel(InventoryModel model) {
        
        applyDarkMode();
        this.model = model;
        model.addObserver(this);
        deleteGoodsPanelButton = new JButton("DELETE GOODS FROM A LIST");
        deleteGoodsPanelButton.setPreferredSize(preferredSize);
        addBinGoods = new JButton("ADD A NEW BIN GOOD TO A LIST");
        addBinGoods.setPreferredSize(preferredSize);
        frozenButton = new JButton("DISPLAY FROZEN GOODS LIST 'F'");
        frozenButton.setPreferredSize(preferredSize);
        flammableButton = new JButton("DISPLAY FLAMMABLE GOODS LIST 'E'");
        flammableButton.setPreferredSize(preferredSize);
        refrigeratedButton = new JButton("DISPLAY REFRIGERATED GOODS LIST 'C'");
        refrigeratedButton.setPreferredSize(preferredSize);
        roomTemperatureButton = new JButton("DISPLAY ROOM TEMP GOODS LIST 'R'");
        roomTemperatureButton.setPreferredSize(preferredSize);
        addCartonizedGoods = new JButton("ADD A NEW CARTONIZED GOOD TO A LIST");
        addCartonizedGoods.setPreferredSize(preferredSize);
//        changePallets = new JButton("Change warehouse area total pallets");
//        changePallets.setPreferredSize(preferredSize);
        moveGoods = new JButton("MOVE GOODS ITEMS FROM WAREHOUSE TO SHELF");
        moveGoods.setPreferredSize(preferredSize);
//        changeSupermarketShelves = new JButton("Change number of supermarket Shelves");
//        changeSupermarketShelves.setPreferredSize(preferredSize);

        table = new JTable(tableModel);
        scrollPane = new JScrollPane(table);
        table.setBackground(new Color(60, 63, 65));
        table.setForeground(Color.WHITE);
        scrollPane = new JScrollPane(table);
        scrollPane.getViewport().setBackground(new Color(60, 63, 65)); // Set background color of table's viewport
        add(scrollPane);
        setActionCommands();

        setLayout(new GridBagLayout());
        // Set up the GridBagConstraints
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;  // This means next available row
        gbc.insets = new Insets(5, 5, 5, 5);      // Margins for the components
        gbc.anchor = GridBagConstraints.WEST;     // Left-aligned
        gbc.weighty = 1.0;                        // Distribute extra vertical space evenly among buttons
        gbc.fill = GridBagConstraints.NONE;   // Make each button stretch vertically

// Add buttons to the panel
        add(frozenButton, gbc);
        add(flammableButton, gbc);
        add(refrigeratedButton, gbc);
        add(roomTemperatureButton, gbc);
        add(addCartonizedGoods, gbc);
        add(addBinGoods, gbc);
        add(moveGoods, gbc);
        add(deleteGoodsPanelButton, gbc);

        // Add and format the scroll pane
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridheight = GridBagConstraints.REMAINDER;  // Span the remaining rows
        gbc.fill = GridBagConstraints.BOTH;  // Expand in both directions
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        add(scrollPane, gbc);
    }

    public void setActionCommands() {
        frozenButton.setActionCommand("Frozen");
        flammableButton.setActionCommand("Flammable");
        refrigeratedButton.setActionCommand("Refrigerated");
        roomTemperatureButton.setActionCommand("RoomTemperature");
        addCartonizedGoods.setActionCommand("Add Cartonized Goods");
//        changePallets.setActionCommand("ChangePallets");
//        changeSupermarketShelves.setActionCommand("ChangeSupermarketShelves");
        moveGoods.setActionCommand("GetMoveGoodsPanel");
        addBinGoods.setActionCommand("AddBinGoods");
        deleteGoodsPanelButton.setActionCommand("Show Delete Goods Panel");

    }
    
    //Apply paint components to the UI manager. 
     private void applyDarkMode() {
        setBackground(new Color(60, 63, 65));
        setForeground(Color.WHITE);

        UIManager.put("Panel.background", new Color(60, 63, 65));
        UIManager.put("Panel.foreground", Color.WHITE);
        UIManager.put("Button.background", new Color(77, 77, 77));
        UIManager.put("Button.foreground", Color.WHITE);
        UIManager.put("Table.background", new Color(60, 63, 65));
        UIManager.put("Table.foreground", Color.WHITE);
        UIManager.put("Table.gridColor", new Color(90, 90, 90));
        UIManager.put("ScrollPane.background", new Color(60, 63, 65));
        UIManager.put("ScrollPane.foreground", Color.WHITE);
        UIManager.put("Viewport.background", new Color(60, 63, 65));
        UIManager.put("Viewport.foreground", Color.WHITE);
    }

     
    //Update arrays in the InventoryPanel class so that they can be dispayed in the inventory lists when changes happen to the model inventory lists. 
    @Override
    public void update(Observable o, Object arg) {

        this.setFrozengoods(model.getWarehouseSuperMarket().getFrozengoods());
        this.setFlammablegoods(model.getWarehouseSuperMarket().getFlammablegoods());
        this.setRefrigeratedgoods(model.getWarehouseSuperMarket().getRefrigeratedgoods());
        this.setRoomtemperaturegoods(model.getWarehouseSuperMarket().getRoomtemperaturegoods());
        repaintTables();
    }
//repaind the tables when an update occurs. 
    public void repaintTables() {
        updateTableData(frozengoods);
        updateTableData(flammablegoods);
        updateTableData(refrigeratedgoods);
        updateTableData(flammablegoods);
        repaint();
    }
//Update the text data to be displayed on screen for the arrays. 
    public void updateTableData(ArrayList<Goods> goods) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
                tableModel.setRowCount(0);

                for (Goods item : goods) {
                    Object[] rowData = new Object[10];
                    rowData[0] = item.getStockCode();
                    rowData[1] = item.getDescription();
                    rowData[2] = item.getStorageType();
                    rowData[3] = item.getWarehouseBayNumber();
                    rowData[4] = item.getSupermarketBayNumber();
                    if (item instanceof CartonizedGoods) {
                        rowData[5] = ((CartonizedGoods) item).getCurrentGoodsNumber();
                        rowData[6] = ((CartonizedGoods) item).getCurrentCartonsNumber();
                        rowData[7] = ((CartonizedGoods) item).getCurrentNumberOfItemsOnShelf();
                    } else if (item instanceof BinGoodsOnPallet) {
                        rowData[8] = ((BinGoodsOnPallet) item).getCurrentKgPerBin();
                        rowData[9] = ((BinGoodsOnPallet) item).getCurrentKgOnShelf();
                    }
                    tableModel.addRow(rowData);
                }
            }
        });
    }
//Methods to add actionlisteners to the buttons.
    public void addFrozenButtonListener(ActionListener listener) {
        frozenButton.addActionListener(listener);
    }

    public void addFlammableButtonListener(ActionListener listener) {
        flammableButton.addActionListener(listener);
    }

    public void addRefrigeratedButtonListener(ActionListener listener) {
        refrigeratedButton.addActionListener(listener);
    }

    public void addRoomTemperatureButtonListener(ActionListener listener) {
        roomTemperatureButton.addActionListener(listener);
    }

//    public void addChangePalletsButtonListener(ActionListener listener) {
//        changePallets.addActionListener(listener);
//    }
//    public void addChangeSupermarketShelvesButtonListener(ActionListener listener) {
//        changeSupermarketShelves.addActionListener(listener);
//    }
    public void addAddCartonizedGoodsButtonListener(ActionListener listener) {
        addCartonizedGoods.addActionListener(listener);
    }

    public void addAddBinGoodsButtonListener(ActionListener listener) {
        addBinGoods.addActionListener(listener);
    }

    public void addMoveGoodsButtonListener(ActionListener listener) {
        moveGoods.addActionListener(listener);
    }

    public void addDeleteGoodsButtonActionListener(ActionListener listener) {
        deleteGoodsPanelButton.addActionListener(listener);
    }

}
