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
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Avraam
 */
public class InventoryPanel extends JPanel implements Observer {

    InventoryModel model;
    private ArrayList<Goods> frozengoods = new ArrayList<>();
    private ArrayList<Goods> refrigeratedgoods = new ArrayList<>();
    private ArrayList<Goods> flammablegoods = new ArrayList<>();
    private ArrayList<Goods> roomtemperaturegoods = new ArrayList<>();
    Dimension preferredSize = new Dimension(300, 25);
    private String[] columnNames = {"STOCK CODE", "PRODUCT DESCRIPTION", "STORAGE_TYPE", "WAREHOUSE BAY NUM", "SUPERMARKET BAY NUM", "CURRENT GOODS TOTAL", "CURRENT CARTONS TOTAL", "CURRENT TOTAL ITEMS SHELF", "CURRENT KG BIN", "CURRENT KG SHELF INT"};
    private DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
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
        addBinGoods = new JButton("Add Bin Goods");
        addBinGoods.setPreferredSize(preferredSize);
        frozenButton = new JButton("Frozen Goods");
        frozenButton.setPreferredSize(preferredSize);
        flammableButton = new JButton("Flammable Goods");
        flammableButton.setPreferredSize(preferredSize);
        refrigeratedButton = new JButton("Refrigerated Goods");
        refrigeratedButton.setPreferredSize(preferredSize);
        roomTemperatureButton = new JButton("roomTemperatureButton");
        roomTemperatureButton.setPreferredSize(preferredSize);
        addCartonizedGoods = new JButton("Add Goods to Inventory");
        addCartonizedGoods.setPreferredSize(preferredSize);
        changePallets = new JButton("Change warehouse area total pallets");
        changePallets.setPreferredSize(preferredSize);
        moveGoods = new JButton("Move goods items from warehouse to shelf");
        moveGoods.setPreferredSize(preferredSize);
        changeSupermarketShelves = new JButton("Change Supermarket Shelves");
        changeSupermarketShelves.setPreferredSize(preferredSize);
        table = new JTable(tableModel);
        scrollPane = new JScrollPane(table);
        add(scrollPane);
        setActionCommands();
        
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Add and format the buttons
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5);  // Margins for the components
        gbc.anchor = GridBagConstraints.WEST;
        add(frozenButton, gbc);

        gbc.gridy++;
        add(flammableButton, gbc);

        gbc.gridy++;
        add(refrigeratedButton, gbc);

        gbc.gridy++;
        add(roomTemperatureButton, gbc);

        gbc.gridy++;
        add(addCartonizedGoods, gbc);
        
        gbc.gridy++;
        add(addBinGoods, gbc);

        gbc.gridy++;
        add(changePallets, gbc);
        
        gbc.gridy++;
        add(changeSupermarketShelves, gbc);

        gbc.gridy++;
        add(moveGoods, gbc);

        

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
        moveGoods.setActionCommand("MoveGoods");
        addBinGoods.setActionCommand("AddBinGoods");
    }

    @Override
    public void update(Observable o, Object arg) {
        
        this.setFrozengoods(model.warehouseSuperMarket.getFrozengoods());
        this.setFlammablegoods(model.warehouseSuperMarket.getFlammablegoods());
        this.setRefrigeratedgoods(model.warehouseSuperMarket.getRefrigeratedgoods());
        this.setRoomtemperaturegoods(model.warehouseSuperMarket.getRoomtemperaturegoods());
    }

    public void updateTableData(ArrayList<Goods> goods) {
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

            }else if(item instanceof BinGoodsOnPallet){
                rowData[8] = ((BinGoodsOnPallet) item).getCurrentKgPerBin();
                rowData[9] = ((BinGoodsOnPallet) item).getCurrentKgOnShelf();
            }
            tableModel.addRow(rowData);
        }
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
    
    public void addAddBinGoodsButtonListener(ActionListener listener){
        addBinGoods.addActionListener(listener);
    }
    
    public void addMoveGoodsButtonListener(ActionListener listener){
        moveGoods.addActionListener(listener);
    }

}
