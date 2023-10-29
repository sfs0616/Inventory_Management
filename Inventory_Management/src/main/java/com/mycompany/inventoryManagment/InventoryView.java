package com.mycompany.inventoryManagment;

import java.awt.CardLayout;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * The InventoryView class represents the main graphical user interface (GUI) of the inventory management system.
 * It extends JFrame, making it a top-level container that can hold other Swing components.
 * The class utilizes a CardLayout to manage different panels, allowing the user to navigate through various sections of the application.
 * Each panel represents a different feature or section of the inventory management system.
 */
public class InventoryView extends JFrame {

    // Panels and layout for the card-based navigation
    private JPanel cardPanel;
    private CardLayout cardLayout;

    // Various panels representing different sections of the application
    private UserPanel userPanel;
    private InventoryPanel inventoryPanel;
    private AddCartonizedGoodsPanel addCartonGoodsPanel;
    private AddBinGoodsPanel addBinGoodsPanel;
    private MoveGoodsPanel moveGoodsPanel;
    private DeleteGoodsPanel deleteGoodsPanel;

    // The model representing the application's data
    private InventoryModel model;

    // Getter methods for accessing the various panels
    public UserPanel getUserPanel() { return userPanel; }
    public InventoryPanel getInventoryPanel() { return inventoryPanel; }
    public JPanel getCardPanel() { return cardPanel; }
    public CardLayout getCardLayout() { return cardLayout; }
    public AddCartonizedGoodsPanel getAddCartonGoodsPanel() { return addCartonGoodsPanel; }
    public AddBinGoodsPanel getAddBinGoodsPanel() { return addBinGoodsPanel; }
    public MoveGoodsPanel getMoveGoodsPanel() { return moveGoodsPanel; }
    public DeleteGoodsPanel getDeleteGoodsPanel() { return deleteGoodsPanel; }

    /**
     * Constructor for InventoryView.
     * Initializes the GUI components, sets up the card layout, and registers the inventory panel as an observer to the model.
     * @param model The InventoryModel representing the application's data.
     */
    public InventoryView(InventoryModel model) {
        this.model = model;

        // Set up the JFrame
        setTitle("Inventory Management System");
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximize the frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Initialize CardLayout and JPanel for card-based navigation
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // Initialize various panels with their respective models or controllers
        userPanel = new UserPanel(model);
        inventoryPanel = new InventoryPanel(model);
        addCartonGoodsPanel = new AddCartonizedGoodsPanel();
        addBinGoodsPanel = new AddBinGoodsPanel();
        moveGoodsPanel = new MoveGoodsPanel(model);
        deleteGoodsPanel = new DeleteGoodsPanel(model);

        // Add panels to cardPanel with corresponding names
        cardPanel.add(userPanel, "Log in:");
        cardPanel.add(inventoryPanel, "InventoryPanel");
        cardPanel.add(addCartonGoodsPanel, "AddCartonizedGoods");
        cardPanel.add(addBinGoodsPanel, "AddBinGoods");
        cardPanel.add(moveGoodsPanel, "Move Goods Panel");
        cardPanel.add(deleteGoodsPanel, "Delete Goods Panel");

        // Show the initial panel (userPanel)
        cardLayout.show(cardPanel, "UserPanel");

        // Add cardPanel to the content pane of the JFrame
        getContentPane().add(cardPanel);

        // Register inventoryPanel as an observer to the model
        model.addObserver(this.inventoryPanel);
    }

    /**
     * Adds an action listener to various components across different panels.
     * This method ensures that user interactions are handled by the provided controller.
     * @param controller The action listener (controller) to handle user interactions.
     */
    public void addController(ActionListener controller) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Registering the controller as an action listener to various buttons and text fields
                userPanel.addloginButtonListener(controller);
                userPanel.addtextFieldActionListener(controller);
                inventoryPanel.addFrozenButtonListener(controller);
                inventoryPanel.addFlammableButtonListener(controller);
                inventoryPanel.addRefrigeratedButtonListener(controller);
                inventoryPanel.addRoomTemperatureButtonListener(controller);
                inventoryPanel.addAddCartonizedGoodsButtonListener(controller);
                inventoryPanel.addMoveGoodsButtonListener(controller);
                inventoryPanel.addAddBinGoodsButtonListener(controller);
                inventoryPanel.addDeleteGoodsButtonActionListener(controller);
                
                addCartonGoodsPanel.addSubmitButtonListener(controller);
                addCartonGoodsPanel.addGoBackButtonActionListener(controller);
                
                addBinGoodsPanel.addSubmitBinGoodsButtonListener(controller);
                addBinGoodsPanel.addGoBackBinButtonActionListener(controller);
                
                moveGoodsPanel.addGoBackButtonActionListener(controller);
                moveGoodsPanel.addMoveGoodsButtonListener(controller);
                
                deleteGoodsPanel.addDeleteGoodsButtonActionListener(controller);
                deleteGoodsPanel.addGoBackButtonActionListener(controller);
            }
        });
    }
}
