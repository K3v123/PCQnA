/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;

import Base.DatabaseManager;
import ButtonHandler.AdminButtonHandler;
import ButtonHandler.CPUButtonHandler;
import ButtonHandler.CoolingButtonHandler;
import ButtonHandler.GPUButtonHandler;
import ButtonHandler.MemoryButtonHandler;
import ButtonHandler.MostAskedButtonHandler;
import ButtonHandler.MotherboardButtonHandler;
import ButtonHandler.PowerSupplyButtonHandler;
import ButtonHandler.StorageButtonHandler;
import ButtonHandler.TPUButtonHandler;
import ButtonHandler.TopSetupsHandler;
import javax.swing.*;
import view.AdminView;
import view.MainView;

public class MainController {

    private MainView mainView;
    private DatabaseManager dbManager;
    private AdminController adminController;
    private AdminView adminView;

    /**
     * Constructor for MainController.
     *
     * @param mainView The main application view.
     */
    public MainController(MainView mainView) {
        this.mainView = mainView;
        this.dbManager = new DatabaseManager();
        this.adminView = new AdminView(this.mainView);
        this.adminView.frame.setVisible(false);
        this.adminController = new AdminController(this.adminView, this.mainView);

        attachEventListeners();

// Apply the loaded visibility states to the MainView components
        this.mainView.setGPUVisibility(this.adminView.getGpuCheckBox().isSelected());
        this.mainView.setCPUVisibility(this.adminView.getCpuCheckBox().isSelected());
        this.mainView.setMemoryVisibility(this.adminView.getMemoryCheckBox().isSelected());
        this.mainView.setPowerSupplyVisibility(this.adminView.getPowerSupplyCheckBox().isSelected());
        this.mainView.setCoolingVisibility(this.adminView.getCoolingCheckBox().isSelected());
        this.mainView.setStorageVisibility(this.adminView.getStorageCheckBox().isSelected());
        this.mainView.setTPUVisibility(this.adminView.getTpuCheckBox().isSelected());
        this.mainView.setMotherboardVisibility(this.adminView.getMotherboardCheckBox().isSelected());

    }

    /**
     * Default constructor for MainController.
     */
    public MainController() {
        this(new MainView());
    }

    /**
     * Attach event listeners to various buttons in the main view.
     */
    public void attachEventListeners() {
        this.mainView.addGPUButtonListener(e -> new GPUButtonHandler(mainView, dbManager).handle());
        this.mainView.addMotherboardButtonListener(e -> new MotherboardButtonHandler(mainView).handle());
        this.mainView.addTPUButtonListener(e -> new TPUButtonHandler(mainView, dbManager).handle());
        this.mainView.addStorageButtonListener(e -> new StorageButtonHandler(mainView, dbManager).handle());
        this.mainView.addCoolingButtonListener(e -> new CoolingButtonHandler(mainView).handle());
        this.mainView.addPowerSupplyButtonListener(e -> new PowerSupplyButtonHandler(mainView, dbManager).handle());
        this.mainView.addMemoryButtonListener(e -> new MemoryButtonHandler(mainView).handle());
        this.mainView.addCPUButtonListener(e -> new CPUButtonHandler(mainView).handle());
        this.mainView.addMostAskedButtonListener(e -> new MostAskedButtonHandler(mainView).handle());
        this.mainView.addAdminButtonListener(e -> new AdminButtonHandler(mainView, adminView).handle());
        this.mainView.addTopSetupsButtonListener(e -> new TopSetupsHandler().handle());
    }

    /**
     * Main method to start the application.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainController());
    }
}
