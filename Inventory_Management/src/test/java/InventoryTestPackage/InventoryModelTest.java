/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package InventoryTestPackage;

import com.mycompany.inventoryManagment.BinGoodsOnPallet;
import com.mycompany.inventoryManagment.CartonizedGoods;
import com.mycompany.inventoryManagment.Goods;
import com.mycompany.inventoryManagment.InventoryModel;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.sql.SQLException;

public class InventoryModelTest {

    private InventoryModel inventoryModel;

    @BeforeEach
    public void setUp() throws SQLException {
        inventoryModel = new InventoryModel();
        
        
    }
    
    

    @Test
    public void testAddBinGoodsValidData() {
        if (inventoryModel == null) {
    throw new IllegalStateException("inventoryModel is not initialized");
}
       
        String[] validGoods = {"123", "ProductA", "E", "1", "2", "50.0", "20.0"};

    
        inventoryModel.addBinGoodsGUI(validGoods);

        
        assertTrue(inventoryModel.getWarehouseSuperMarket().getFlammablegoods().size() > 0);
    }

    @Test
    public void testAddBinGoodsInvalidData() {
      
        String[] inValidGoods = {"123", "ProductA", "W", "1", "2", "50.0", "20.0"};
        inventoryModel.addCartonGoodsGUI(inValidGoods);
        assertTrue(inventoryModel.getWarehouseSuperMarket().getFlammablegoods().size() == 0);
       
     
        
    }

    @Test
    public void testAddCartonGoodsValidData() {
        
        String[] validGoods = {"456", "ProductB", "E", "3", "4", "100", "5"};
        
       
        inventoryModel.addCartonGoodsGUI(validGoods);
        assertTrue(inventoryModel.getWarehouseSuperMarket().getFlammablegoods().size() > 0);
        
       
    }

    @Test
    public void testAddCartonGoodsInvalidData() {
        
        
        String[] invalidGoods = {"456", "ProductB", "W", "3", "4", "100", "5"};
        
       
        
         inventoryModel.addCartonGoodsGUI(invalidGoods);
         assertTrue(inventoryModel.getWarehouseSuperMarket().getFlammablegoods().size() == 0);
    }

    @Test
    public void moveItemsFromWareHouseToShelfValidData() {
        
        String[] validBinGoods = {"123", "ProductA", "E", "1", "2", "50.0", "20.0"};
        String[] validCartonGoods = {"456", "ProductB", "F", "3", "4", "100", "5"};
         inventoryModel.addBinGoodsGUI(validBinGoods);
         inventoryModel.addCartonGoodsGUI(validCartonGoods);
      
        String[] validBinItemsToMove = {"123", "10.50"};
        inventoryModel.moveItemFromWarehouseToShelfGUI(validBinItemsToMove);
        String[] validCartonItemsToMove = {"456", "20"};
        inventoryModel.moveItemFromWarehouseToShelfGUI(validCartonItemsToMove);
        String[] bin = {"123", "ProductA"};
        String[] carton = {"456", "ProductB"};
        
        BinGoodsOnPallet  binItem = (BinGoodsOnPallet) inventoryModel.findGood(bin);
        CartonizedGoods  cartonItem = (CartonizedGoods) inventoryModel.findGood(carton);
        
        assertTrue(binItem.getCurrentKgPerBin() == 39.50);
        assertTrue(binItem.getCurrentKgOnShelf() == 30.50);
        assertTrue(cartonItem.getCurrentGoodsNumber() == 80);
        assertTrue(cartonItem.getCurrentNumberOfItemsOnShelf() == 25);

       
    }

    @Test
    public void moveItemsFromWareHouseToShelfInvalidData() {
        String[] validBinGoods = {"123", "ProductA", "E", "1", "2", "50.0", "20.0"};
        String[] validCartonGoods = {"456", "ProductB", "F", "3", "4", "100", "5"};
        inventoryModel.addBinGoodsGUI(validBinGoods);
        inventoryModel.addCartonGoodsGUI(validCartonGoods);
        
        String[] validBinItemsToMove = {"555", "10.50"};
        inventoryModel.moveItemFromWarehouseToShelfGUI(validBinItemsToMove);
        String[] validCartonItemsToMove = {"666", "20"};
        inventoryModel.moveItemFromWarehouseToShelfGUI(validBinItemsToMove);
        String[] bin = {"123", "ProductA"};
        String[] carton = {"456", "ProductB"};
        
        BinGoodsOnPallet  binItem = (BinGoodsOnPallet) inventoryModel.findGood(bin);
        CartonizedGoods  cartonItem = (CartonizedGoods) inventoryModel.findGood(carton);
        
        assertTrue(binItem.getCurrentKgPerBin() != 39.50);
        assertTrue(binItem.getCurrentKgOnShelf() != 30.50);
        assertTrue(cartonItem.getCurrentCartonsNumber() != 80);
        assertTrue(cartonItem.getCurrentNumberOfItemsOnShelf() != 25);

        
        
        
    }

    @Test
    public void deleteGoodsItemThatExists() {
        
        String[] validItemsToDelete = {"123", "ProductA"};
        
        String[] validBinGoods = {"123", "ProductA", "E", "1", "2", "50.0", "20.0"};
        inventoryModel.addBinGoodsGUI(validBinGoods);
        
        boolean itemDeleted = inventoryModel.deleteGoodsItemGUI(validItemsToDelete);
        
        Goods item = inventoryModel.findGood(validItemsToDelete);
        
        assertTrue(itemDeleted);
        assertTrue(item == null);
        
    }

    @Test
    public void deleteGoodsItemThatDoesNotExist() {
        
        String[] invalidItemsToDelete = {"789", "NonExistentProduct"};

        
        boolean itemDeleted = inventoryModel.deleteGoodsItemGUI(invalidItemsToDelete);

        
        assertFalse(itemDeleted == true);

        
    }

    @Test
    public void deleteGoodsItemWithDuplicateItemCode() {
       
        String[] itemsToDelete = {"123", "ProductA"};

        
        inventoryModel.addBinGoodsGUI(itemsToDelete);
        inventoryModel.addBinGoodsGUI(itemsToDelete);

        
        boolean itemDeleted = inventoryModel.deleteGoodsItemGUI(itemsToDelete);

        
        assertTrue(itemDeleted == false);

        
    }
}
