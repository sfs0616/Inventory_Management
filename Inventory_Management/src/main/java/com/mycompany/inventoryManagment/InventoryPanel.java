/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.inventoryManagment;

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
    private String[] columnNames = {"STOCK CODE", "PRODUCT DESCRIPTION", "STORAGE_TYPE", "WAREHOUSE BAY NUM", "SUPERMARKET BAY NUM", "CURRENT GOODS TOTAL", "CURRENT CARTONS TOTAL", "CURRENT TOTAL ITEMS SHELF", "CURRENT KG BIN", "CURRENT KG SHELF INT"};
    private DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0) {
    @Override
    public boolean isCellEditable(int row, int column) {
        return false;  
    }
};
    private JTable table;
    private JScrollPane scrollPane = new JScrollPane(table);
    private JButton frozenButton; //When this button is clicked the inventory for frozen goods is displayed in the Jtable
    private JButton flammableButton; //When this button is clicked the inventory for flammable goods is displayed in the Jtable
    private JButton refrigeratedButton;//When this button is clicked the inventory for refrigerated goods is displayed in the Jtable
    private JButton roomTemperatureButton;//When this button is clicked the inventory for room temperature goods is displayed in the Jtable
    private JButton addCartonizedGoods; //When this button is clicked it triggers the addCartonizedGoods method in the model;
    private JButton addBinGoods;
    private JButton changePallets; //When this button is good it triggers the changePallets method in the model;
    private JButton changeSupermarketShelves; //When this button is clicked it triggers the change number of supermarket shelves method in the model 
    private JButton moveGoods; // When this button is clicked the movegoods method is triggered
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
        this.model = model;
        model.addObserver(this);
        deleteGoodsPanelButton = new JButton("Delete goods from list");
        addBinGoods = new JButton("Add Bin Goods to a list");
        addBinGoods.setPreferredSize(preferredSize);
        frozenButton = new JButton("Diaplay frozen goods list");
        frozenButton.setPreferredSize(preferredSize);
        flammableButton = new JButton("Display flammable goods list");
        flammableButton.setPreferredSize(preferredSize);
        refrigeratedButton = new JButton("Display refrigerated goods list");
        refrigeratedButton.setPreferredSize(preferredSize);
        roomTemperatureButton = new JButton("Displat room temp goods list");
        roomTemperatureButton.setPreferredSize(preferredSize);
        addCartonizedGoods = new JButton("Add new cartonized product");
        addCartonizedGoods.setPreferredSize(preferredSize);
        changePallets = new JButton("Change warehouse area total pallets");
        changePallets.setPreferredSize(preferredSize);
        moveGoods = new JButton("Move goods from warehouse to shelf");
        moveGoods.setPreferredSize(preferredSize);
        changeSupermarketShelves = new JButton("Change number of supermarket Shelves");
        changeSupermarketShelves.setPreferredSize(preferredSize);
        
        

        table = new JTable(tableModel);
        scrollPane = new JScrollPane(table);
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
        add(moveGoods, gbc);
        add(addCartonizedGoods, gbc);
        add(addBinGoods, gbc);
        add(changePallets, gbc);
        add(changeSupermarketShelves, gbc);
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
        changePallets.setActionCommand("ChangePallets");
        changeSupermarketShelves.setActionCommand("ChangeSupermarketShelves");
        moveGoods.setActionCommand("GetMoveGoodsPanel");
        addBinGoods.setActionCommand("AddBinGoods");
        deleteGoodsPanelButton.setActionCommand("Show Delete Goods Panel");
        
    }

    @Override
    public void update(Observable o, Object arg) {

        this.setFrozengoods(model.getWarehouseSuperMarket().getFrozengoods());
        this.setFlammablegoods(model.getWarehouseSuperMarket().getFlammablegoods());
        this.setRefrigeratedgoods(model.getWarehouseSuperMarket().getRefrigeratedgoods());
        this.setRoomtemperaturegoods(model.getWarehouseSuperMarket().getRoomtemperaturegoods());
        repaintTables();
    }
    
    public void repaintTables(){
        updateTableData(frozengoods);
        updateTableData(flammablegoods);
        updateTableData(refrigeratedgoods);
        updateTableData(flammablegoods);
        repaint();
    }

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

    public void addChangePalletsButtonListener(ActionListener listener) {
        changePallets.addActionListener(listener);
    }

    public void addChangeSupermarketShelvesButtonListener(ActionListener listener) {
        changeSupermarketShelves.addActionListener(listener);
    }

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
