/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.inventoryManagment;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;

/**
 *
 * @author Avraam
 */
public class InventoryController implements ActionListener {

    private InventoryModel model;

    private InventoryView view;

    public InventoryController(InventoryModel model, InventoryView view) {
        this.model = model;
        this.view = view;
        view.setVisible(true);

        view.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    handleWindowClosing();
                } catch (IOException ex) {
                    Logger.getLogger(InventoryController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

    }

    private void handleWindowClosing() throws IOException {
        if(model.getWarehouseSuperMarket().getUser() != null)
        {
            model.saveGoodsDataGUI();
        }
        
        System.exit(0); // Optionally, exit the application
    }

    public void initializeController() {
        view.addController(this);
        view.setVisible(true);
    }

    public void addModel(InventoryModel model) {
        this.model = model;
    }

    public InventoryModel getModel() {
        return model;
    }

    public InventoryView getView() {
        return view;
    }

    public void setModel(InventoryModel model) {
        this.model = model;
    }

    public void setView(InventoryView view) {
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand(); // Get the action command of the clicked button
        SwingUtilities.invokeLater(() -> {
            switch (command) {
                case "Login":
                    String username = view.getUserPanel().getUsername();
                    if (username.trim().isEmpty() || username.equalsIgnoreCase("Enter UserName Here")) {
                        view.getUserPanel().errorUserNameMessage();
                    } else {
                        System.out.println("Login Button Clicked findUser is called");
                        model.findUserGUI(username);
                        
                        view.getCardLayout().show(view.getCardPanel(), "InventoryPanel");
                    }

                    break;
                case "Frozen":
                    view.getInventoryPanel().updateTableData(view.getInventoryPanel().getFrozengoods());
                    break;
                case "Flammable":
                    view.getInventoryPanel().updateTableData(view.getInventoryPanel().getFlammablegoods());
                    break;
                case "Refrigerated":
                    view.getInventoryPanel().updateTableData(view.getInventoryPanel().getRefrigeratedgoods());
                    break;
                case "RoomTemperature":
                    view.getInventoryPanel().updateTableData(view.getInventoryPanel().getRoomtemperaturegoods());
                    break;
                case "AddBinGoods":
                    view.getCardLayout().show(view.getCardPanel(), "AddBinGoods");
                    break;
                case "SubmitBinGoods":
                    view.getAddBinGoodsPanel().submitData();
                    String[] typeCheckBin = view.getAddBinGoodsPanel().getData();
                    Boolean checkPrimaryKeyBin = model.isStockCodePrimaryKey(typeCheckBin);
                    if (checkPrimaryKeyBin == false) {
                        view.getAddBinGoodsPanel().primaryKeyAlreadyExists();
                        break;
                    }
                    Boolean validBinFields = true;
                    for (int i = 0; i < typeCheckBin.length; i++) {
                        if (typeCheckBin[i] == null || typeCheckBin[i].trim().isEmpty()) {
                            validBinFields = false;
                            System.out.println("Field Checked");
                        }
                    }
                    if (validBinFields == true) {
                        if (typeCheckBin[2].equals("F") || typeCheckBin[2].equals("C") || typeCheckBin[2].equals("R") || typeCheckBin[2].equals("E")) {
                            if (model.addBinGoodsGUI(view.getAddBinGoodsPanel().getData()) == true) {
                                view.getAddBinGoodsPanel().goodsAddedMessage();
                            } else {
                                view.getAddCartonGoodsPanel().invalidFields();
                            }

                        } else {
                            view.getAddBinGoodsPanel().invalidStorageMessage();
                        }
                    } else {
                        view.getAddBinGoodsPanel().invalidFields();
                    }

                    break;
                case "SubmitCartonGoods":
                    view.getAddCartonGoodsPanel().submitData();
                    String[] typeCheckCarton = view.getAddCartonGoodsPanel().getData();
                    Boolean checkPrimaryKeyCarton = model.isStockCodePrimaryKey(typeCheckCarton);
                    if (checkPrimaryKeyCarton == false) {
                        view.getAddCartonGoodsPanel().primaryKeyAlreadyExists();
                        break;
                    }
                    Boolean validCartonFields = true;
                    for (int i = 0; i < typeCheckCarton.length; i++) {
                        if (typeCheckCarton[i] == null || typeCheckCarton[i].trim().isEmpty()) {
                            validCartonFields = false;
                            System.out.println("Field Checked");

                        }
                    }
                    if (validCartonFields == true) {
                        if (typeCheckCarton[2].equals("F") || typeCheckCarton[2].equals("C") || typeCheckCarton[2].equals("R") || typeCheckCarton[2].equals("E")) {

                            if (model.addCartonGoodsGUI(view.getAddCartonGoodsPanel().getData()) == true) {
                                view.getAddCartonGoodsPanel().cartonGoodsAddedMessage();
                            } else {
                                view.getAddCartonGoodsPanel().invalidFields();
                            }

                        } else {
                            view.getAddCartonGoodsPanel().invalidStorageCartonMessage();
                        }
                    } else {
                        view.getAddCartonGoodsPanel().invalidFields();
                    }
                    break;

                case "Add Cartonized Goods":
                    view.getCardLayout().show(view.getCardPanel(), "AddCartonizedGoods");
                    break;
                case "GoBack":
                    view.getCardLayout().show(view.getCardPanel(), "InventoryPanel");
                    break;

                case "GetMoveGoodsPanel":
                    view.getCardLayout().show(view.getCardPanel(), "Move Goods Panel");
                    break;

                case "Move Goods":
                    Boolean validFields = true;
                    view.getMoveGoodsPanel().submitData();
                    String[] moveGoodsData = view.getMoveGoodsPanel().getData();
                    for (int i = 0; i < moveGoodsData.length; i++) {
                        if (moveGoodsData[i] == null || moveGoodsData[i].trim().isEmpty()) {
                            validFields = false;
                            System.out.println("Field Checked");

                        }
                    }
                    if (validFields == false) {
                        view.getMoveGoodsPanel().errorInvalidFields();
                    } else {
                        Boolean validTransaction = model.moveItemFromWarehouseToShelfGUI(moveGoodsData);
                        if (validTransaction == true) {
                            view.getMoveGoodsPanel().goodsMovedMessage();
                        } else {
                            view.getMoveGoodsPanel().errorCheckQuantitiesInExistingInventory();
                        }
                    }

                    break;

                case "Show Delete Goods Panel":
                    view.getCardLayout().show(view.getCardPanel(), "Delete Goods Panel");

                    break;
                case "Delete Goods":
                    Boolean validDeleteFields = true;
                    view.getDeleteGoodsPanel().submitData();
                    String[] deleteData = view.getDeleteGoodsPanel().getData();
                    for (int i = 0; i < deleteData.length; i++) {
                        if (deleteData[i] == null || deleteData[i].trim().isEmpty()) {
                            validDeleteFields = false;
                            System.out.println("Fields Checked");

                        }
                    }

                    if (validDeleteFields == false) {
                        view.getDeleteGoodsPanel().errorInvalidFields();
                    } else {
                        Boolean validTransaction = model.deleteGoodsItemGUI(deleteData);
                        if (validTransaction == true) {
                            view.getDeleteGoodsPanel().goodsDeletedMessage();
                        } else {
                            view.getDeleteGoodsPanel().errorCheckQuantitiesInExistingInventory();
                        }
                    }

                    break;

            }
        });

    }

}
