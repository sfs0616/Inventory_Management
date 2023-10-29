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

/**
 * Test class for InventoryModel.
 * It contains various test cases to ensure the proper functionality of InventoryModel's methods.
 */
public class InventoryModelTest {

    private InventoryModel inventoryModel;

    /**
     * Set up method, initializes an InventoryModel object before each test.
     * It's annotated with @BeforeEach, meaning it will be run before each test case.
     */
    @BeforeEach
    public void setUp() throws SQLException {
        inventoryModel = new InventoryModel();
    }

    /**
     * Tests the addition of bin goods with valid data.
     * It asserts that after adding the goods, the relevant collection size increases, indicating success.
     */
    @Test
    public void testAddBinGoodsValidData() {
        // Ensure the inventory model is properly initialized.
        assertNotNull(inventoryModel, "Inventory model is not initialized");

        // Define a valid array of goods information.
        String[] validGoods = {"123", "ProductA", "E", "1", "2", "50.0", "20.0"};

        // Add bin goods using the valid data.
        inventoryModel.addBinGoodsGUI(validGoods);

        // Assert that flammable goods collection has at least one item, indicating that the add was successful.
        assertTrue(inventoryModel.getWarehouseSuperMarket().getFlammablegoods().size() > 0);
    }

    /**
     * Tests the addition of bin goods with invalid data.
     * It asserts that after attempting to add invalid goods, the relevant collection size remains zero, indicating failure.
     */
    @Test
    public void testAddBinGoodsInvalidData() {
        // Define an invalid array of goods information.
        String[] inValidGoods = {"123", "ProductA", "W", "1", "2", "50.0", "20.0"};

        // Attempt to add bin goods using the invalid data.
        inventoryModel.addCartonGoodsGUI(inValidGoods);

        // Assert that flammable goods collection remains empty, indicating that the add was unsuccessful.
        assertTrue(inventoryModel.getWarehouseSuperMarket().getFlammablegoods().size() == 0);
    }

    /**
     * Tests the addition of carton goods with valid data.
     * It asserts that after adding the goods, the relevant collection size increases, indicating success.
     */
    @Test
    public void testAddCartonGoodsValidData() {
        // Define a valid array of carton goods information.
        String[] validGoods = {"456", "ProductB", "E", "3", "4", "100", "5"};

        // Add carton goods using the valid data.
        inventoryModel.addCartonGoodsGUI(validGoods);

        // Assert that flammable goods collection has at least one item, indicating that the add was successful.
        assertTrue(inventoryModel.getWarehouseSuperMarket().getFlammablegoods().size() > 0);
    }

    /**
     * Tests the addition of carton goods with invalid data.
     * It asserts that after attempting to add invalid goods, the relevant collection size remains zero, indicating failure.
     */
    @Test
    public void testAddCartonGoodsInvalidData() {
        // Define an invalid array of carton goods information.
        String[] invalidGoods = {"456", "ProductB", "W", "3", "4", "100", "5"};

        // Attempt to add carton goods using the invalid data.
        inventoryModel.addCartonGoodsGUI(invalidGoods);

        // Assert that flammable goods collection remains empty, indicating that the add was unsuccessful.
        assertTrue(inventoryModel.getWarehouseSuperMarket().getFlammablegoods().size() == 0);
    }

    /**
     * Tests the moving of items from the warehouse to the shelf with valid data.
     * It asserts that after moving the goods, the inventory reflects the changes accurately.
     */
    @Test
    public void moveItemsFromWareHouseToShelfValidData() {
        // Add bin and carton goods with valid data.
        String[] validBinGoods = {"123", "ProductA", "E", "1", "2", "50.0", "20.0"};
        String[] validCartonGoods = {"456", "ProductB", "F", "3", "4", "100", "5"};
        inventoryModel.addBinGoodsGUI(validBinGoods);
        inventoryModel.addCartonGoodsGUI(validCartonGoods);

        // Move bin and carton goods from warehouse to shelf.
        String[] validBinItemsToMove = {"123", "10.50"};
        inventoryModel.moveItemFromWarehouseToShelfGUI(validBinItemsToMove);
        String[] validCartonItemsToMove = {"456", "20"};
        inventoryModel.moveItemFromWarehouseToShelfGUI(validCartonItemsToMove);

        // Find the bin and carton goods after the move.
        String[] bin = {"123", "ProductA"};
        String[] carton = {"456", "ProductB"};
        BinGoodsOnPallet binItem = (BinGoodsOnPallet) inventoryModel.findGood(bin);
        CartonizedGoods cartonItem = (CartonizedGoods) inventoryModel.findGood(carton);

        // Assert that the bin and carton goods reflect the changes accurately.
        assertNotNull(binItem, "Bin item not found");
        assertNotNull(cartonItem, "Carton item not found");
        assertEquals(39.50, binItem.getCurrentKgPerBin());
        assertEquals(30.50, binItem.getCurrentKgOnShelf());
        assertEquals(80, cartonItem.getCurrentGoodsNumber());
        assertEquals(25, cartonItem.getCurrentNumberOfItemsOnShelf());
    }

    /**
     * Tests the moving of items from the warehouse to the shelf with invalid data.
     * It asserts that after attempting to move nonexistent goods, the inventory remains unchanged.
     */
    @Test
    public void moveItemsFromWareHouseToShelfInvalidData() {
        // Add bin and carton goods with valid data.
        String[] validBinGoods = {"123", "ProductA", "E", "1", "2", "50.0", "20.0"};
        String[] validCartonGoods = {"456", "ProductB", "F", "3", "4", "100", "5"};
        inventoryModel.addBinGoodsGUI(validBinGoods);
        inventoryModel.addCartonGoodsGUI(validCartonGoods);

        // Attempt to move nonexistent bin and carton goods.
        String[] invalidBinItemsToMove = {"555", "10.50"};
        inventoryModel.moveItemFromWarehouseToShelfGUI(invalidBinItemsToMove);
        String[] invalidCartonItemsToMove = {"666", "20"};
        inventoryModel.moveItemFromWarehouseToShelfGUI(invalidCartonItemsToMove);

        // Find the bin and carton goods after the attempted move.
        String[] bin = {"123", "ProductA"};
        String[] carton = {"456", "ProductB"};
        BinGoodsOnPallet binItem = (BinGoodsOnPallet) inventoryModel.findGood(bin);
        CartonizedGoods cartonItem = (CartonizedGoods) inventoryModel.findGood(carton);

        // Assert that the bin and carton goods did not change.
        assertNotNull(binItem, "Bin item not found");
        assertNotNull(cartonItem, "Carton item not found");
        assertNotEquals(39.50, binItem.getCurrentKgPerBin());
        assertNotEquals(30.50, binItem.getCurrentKgOnShelf());
        assertNotEquals(80, cartonItem.getCurrentGoodsNumber());
        assertNotEquals(25, cartonItem.getCurrentNumberOfItemsOnShelf());
    }

    /**
     * Tests the deletion of a goods item that exists in the inventory.
     * It asserts that after deletion, the goods item is no longer found and the operation returns true.
     */
    @Test
    public void deleteGoodsItemThatExists() {
        // Add bin goods with valid data.
        String[] validBinGoods = {"123", "ProductA", "E", "1", "2", "50.0", "20.0"};
        inventoryModel.addBinGoodsGUI(validBinGoods);

        // Define the goods item to delete.
        String[] validItemsToDelete = {"123", "ProductA"};

        // Delete the goods item and assert that the operation was successful.
        boolean itemDeleted = inventoryModel.deleteGoodsItemGUI(validItemsToDelete);
        assertTrue(itemDeleted);

        // Try to find the goods item after deletion and assert that it is no longer found.
        Goods item = inventoryModel.findGood(validItemsToDelete);
        assertNull(item, "Goods item still found after deletion");
    }

    /**
     * Tests the deletion of a nonexistent goods item.
     * It asserts that attempting to delete a nonexistent item returns false.
     */
    @Test
    public void deleteGoodsItemThatDoesNotExist() {
        // Define a nonexistent goods item.
        String[] invalidItemsToDelete = {"789", "NonExistentProduct"};

        // Attempt to delete the nonexistent goods item and assert that the operation was unsuccessful.
        boolean itemDeleted = inventoryModel.deleteGoodsItemGUI(invalidItemsToDelete);
        assertFalse(itemDeleted, "Nonexistent goods item reportedly deleted");
    }

    /**THIS TEST IS NOW INVALID AS ITS IMPOSSIBLE FOR GOODS ITEMS TO EXIST WITH SAME PRIMARY KEY IN INVENTORY:
     * Tests the deletion of a goods item with a duplicate item code.
     * It asserts that attempting to delete a goods item when multiple items have the same item code returns false.
     */
//    @Test
//    public void deleteGoodsItemWithDuplicateItemCode() {
//        // Define a goods item to add twice, creating a duplicate item code.
//        String[] itemsToDelete = {"123", "ProductA"};
//        inventoryModel.addBinGoodsGUI(itemsToDelete);
//        inventoryModel.addBinGoodsGUI(itemsToDelete);
//
//        // Attempt to delete the goods item and assert that the operation was unsuccessful.
//        boolean itemDeleted = inventoryModel.deleteGoodsItemGUI(itemsToDelete);
//        assertFalse(itemDeleted, "Goods item reportedly deleted despite duplicate item code");
//    }
}