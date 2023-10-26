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
import com.mycompany.inventoryManagment.User;
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
        // Prepare valid input data for adding bin goods
        String[] validGoods = {"123", "ProductA", "E", "1", "2", "50.0", "20.0"};

        // Call the addBinGoodsGUI method
        inventoryModel.addBinGoodsGUI(validGoods);

        // Assert that the goods were added successfully (e.g., check if the list contains the added goods)
        assertTrue(inventoryModel.getWarehouseSuperMarket().getFlammablegoods().size() > 0);
    }

    @Test
    public void testAddBinGoodsInvalidData() {
        // Prepare invalid input data for adding bin goods (e.g., missing required information)
        String[] inValidGoods = {"123", "ProductA", "W", "1", "2", "50.0", "20.0"};
        inventoryModel.addCartonGoodsGUI(inValidGoods);
        assertTrue(inventoryModel.getWarehouseSuperMarket().getFlammablegoods().size() == 0);
        // Call the addBinGoodsGUI method
        // You may want to wrap this call in a try-catch block to catch any expected exceptions
        // Example: assertThrows(SomeException.class, () -> inventoryModel.addBinGoodsGUI(invalidGoods));
        
    }

    @Test
    public void testAddCartonGoodsValidData() {
        // Prepare valid input data for adding carton goods
        String[] validGoods = {"456", "ProductB", "E", "3", "4", "100", "5"};
        
        // Call the addCartonGoodsGUI method
        inventoryModel.addCartonGoodsGUI(validGoods);
        assertTrue(inventoryModel.getWarehouseSuperMarket().getFlammablegoods().size() > 0);
        // Assert that the goods were added successfully (e.g., check if the list contains the added goods)
       
    }

    @Test
    public void testAddCartonGoodsInvalidData() {
        
        // Prepare invalid input data for adding carton goods (e.g., missing required information)
        String[] invalidGoods = {"456", "ProductB", "W", "3", "4", "100", "5"};
        // Call the addCartonGoodsGUI method
        // You may want to wrap this call in a try-catch block to catch any expected exceptions
        // Example: assertThrows(SomeException.class, () -> inventoryModel.addCartonGoodsGUI(invalidGoods));
         inventoryModel.addCartonGoodsGUI(invalidGoods);
         assertTrue(inventoryModel.getWarehouseSuperMarket().getFlammablegoods().size() == 0);
    }

    @Test
    public void moveItemsFromWareHouseToShelfValidData() {
        
        String[] validBinGoods = {"123", "ProductA", "E", "1", "2", "50.0", "20.0"};
        String[] validCartonGoods = {"456", "ProductB", "F", "3", "4", "100", "5"};
         inventoryModel.addBinGoodsGUI(validBinGoods);
         inventoryModel.addCartonGoodsGUI(validCartonGoods);
        // Prepare valid input data for moving items from warehouse to shelf
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

        // Assert that the items were moved successfully (e.g., check if the shelf quantity was updated correctly)
        // You'll need to access the specific item on the shelf and check its quantity
        // Example: assertEquals(expectedQuantity, actualQuantity);
    }

    @Test
    public void moveItemsFromWareHouseToShelfInvalidData() {
        String[] validBinGoods = {"123", "ProductA", "E", "1", "2", "50.0", "20.0"};
        String[] validCartonGoods = {"456", "ProductB", "F", "3", "4", "100", "5"};
        inventoryModel.addBinGoodsGUI(validBinGoods);
        inventoryModel.addCartonGoodsGUI(validCartonGoods);
        // Prepare valid input data for moving items from warehouse to shelf
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
        // Prepare valid input data for deleting goods item that exists
        String[] validItemsToDelete = {"123", "ProductA"};
        
        String[] validBinGoods = {"123", "ProductA", "E", "1", "2", "50.0", "20.0"};
        inventoryModel.addBinGoodsGUI(validBinGoods);
        // Call the deleteGoodsItemGUI method
        boolean itemDeleted = inventoryModel.deleteGoodsItemGUI(validItemsToDelete);
        
        Goods item = inventoryModel.findGood(validItemsToDelete);
        // Assert that the item was deleted successfully (e.g., check if itemDeleted is true)
        assertTrue(itemDeleted);
        assertTrue(item == null);
        // Additional assertion: Ensure that the item is no longer in the goods list
        // Example: assertFalse(inventoryModel.warehouseSuperMarket.flammablegoods.contains(deletedItem));
    }

    @Test
    public void deleteGoodsItemThatDoesNotExist() {
        // Prepare input data for deleting goods item that does not exist
        String[] invalidItemsToDelete = {"789", "NonExistentProduct"};

        // Call the deleteGoodsItemGUI method
        boolean itemDeleted = inventoryModel.deleteGoodsItemGUI(invalidItemsToDelete);

        // Assert that the item was not deleted (e.g., check if itemDeleted is false)
        assertFalse(itemDeleted == true);

        // Additional assertion: Ensure that the goods list remains unchanged
        // Example: assertEquals(initialGoodsCount, inventoryModel.warehouseSuperMarket.flammablegoods.size());
    }

    @Test
    public void deleteGoodsItemWithDuplicateItemCode() {
        // Prepare input data for deleting goods item with duplicate item code
        String[] itemsToDelete = {"123", "ProductA"};

        // Add multiple items with the same item code
        inventoryModel.addBinGoodsGUI(itemsToDelete);
        inventoryModel.addBinGoodsGUI(itemsToDelete);

        // Call the deleteGoodsItemGUI method
        boolean itemDeleted = inventoryModel.deleteGoodsItemGUI(itemsToDelete);

        // Assert that only one of the items was deleted (e.g., check if itemDeleted is true)
        assertTrue(itemDeleted == false);

        // Additional assertion: Ensure that the other item with the same code is still present
        // Example: assertEquals(1, inventoryModel.warehouseSuperMarket.flammablegoods.stream().filter(item -> item.getStockCode() == 123).count());
    }
}
