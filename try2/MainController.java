/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package try2;

/**
 *
 * @author kq635
 */
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainController {

    private MainView mainView;
    private DatabaseManager dbManager;
    private AdminController adminController;
    private AdminView adminView;

    // Initializes the MainController, setting up the database manager and various views/controllers.
    // This constructor creates the main menu view and sets up action listeners for different components.
    public MainController() {
        this.dbManager = new DatabaseManager();
        this.mainView = new MainView();
        this.adminView = new AdminView(mainView);
        this.adminView.frame.setVisible(false);  // Ensure AdminView is initially hidden
        this.adminController = new AdminController(this.adminView, mainView);

        this.mainView.addGPUButtonListener(new GPUButtonListener());
        this.mainView.addMotherboardButtonListener(new MotherboardButtonListener());
        this.mainView.addTPUButtonListener(new TPUButtonListener());
        this.mainView.addStorageButtonListener(new StorageButtonListener());
        this.mainView.addCoolingButtonListener(new CoolingButtonListener());
        this.mainView.addPowerSupplyButtonListener(new PowerSupplyButtonListener());
        this.mainView.addMemoryButtonListener(new MemoryButtonListener());
        this.mainView.addCPUButtonListener(new CPUButtonListener());
        this.mainView.addAdminButtonListener(new AdminButtonListener());
        this.mainView.addMostAskedButtonListener(new MostAskedButtonListener());

        // Add the listener for the "Top Setups" button
        this.mainView.addTopSetupsButtonListener(new TopSetupsListener());
    }

    // ActionListener for the "GPU" button in the main menu.
    // It handles showing the GPUView and controlling its visibility.
    class GPUButtonListener implements ActionListener {

        private GPUController gpuController;
        private GPUView gpuView;

        @Override
        public void actionPerformed(ActionEvent e) {
            if (gpuView == null) {
                gpuView = new GPUView();
                gpuController = new GPUController(gpuView, dbManager);
                gpuView.addGoBackButtonListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        gpuView.getFrame().setVisible(false); // Hide the GPUView
                        mainView.setVisible(true); // Show the mainView (main menu)
                    }
                });
            }
            mainView.setVisible(false); // Hide the mainView
            gpuView.getFrame().setVisible(true); // Show the GPUView
        }
    }

    // ActionListener for the "Motherboard" button in the main menu.
    // It handles showing the MotherboardView and controlling visibility.
    class MotherboardButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            MotherboardController motherboardController = new MotherboardController(new MotherboardModel(), new MotherboardView(), mainView);
            mainView.setVisible(false);
        }
    }

    // ActionListener for the "TPU" button in the main menu.
    // It handles showing the TPUView and controlling visibility.
    class TPUButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            TPUController tpuController = new TPUController(new TPUModel(), new TPUView(), mainView, dbManager);
            mainView.setVisible(false);
        }
    }

    // ActionListener for the "Storage" button in the main menu.
    // It handles showing the StorageView and controlling visibility.
    class StorageButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            StorageController storageController = new StorageController(new StorageModel(), new StorageView(), mainView, dbManager);
            mainView.setVisible(false);
        }
    }

    // ActionListener for the "Cooling" button in the main menu.
    // It handles showing the CoolingView and controlling visibility.
    class CoolingButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            DatabaseManager dbManager = new DatabaseManager();
            CoolingController coolingController = new CoolingController(new CoolingModel(), new CoolingView(), mainView, dbManager);
            mainView.setVisible(false);
        }
    }
    
    // ActionListener for the "Power Supply" button in the main menu.
    // It handles showing the PowerSupplyView and controlling visibility.
    class PowerSupplyButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            PowerSupplyController powerSupplyController = new PowerSupplyController(new PowerSupplyView(mainView), dbManager);
            mainView.setVisible(false);
        }
    }

    // ActionListener for the "Memory" button in the main menu.
    // It handles showing the MemoryView and controlling visibility.
    class MemoryButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            MemoryController memoryController = new MemoryController(new MemoryModel(), new MemoryView(), mainView);
            mainView.setVisible(false);
        }
    }

    // ActionListener for the "CPU" button in the main menu.
    // It handles showing the CPUView and controlling visibility.
    class CPUButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            var cpuController = new CPUController(new CPUModel(), new CPUView(mainView), mainView);
            mainView.setVisible(false);
        }
    }

    // ActionListener for the "Most Asked" button in the main menu.
    // It handles showing the MostAskedView and controlling visibility.
    class MostAskedButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            MostAskedController mostAskedController = new MostAskedController(new MostAskedView(), mainView);
            mainView.setVisible(false);
        }
    }

    // ActionListener for the "Admin" button in the main menu.
    // It handles showing the AdminView and controlling visibility.
    class AdminButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // Since we no longer use DatabaseManager for hide/unhide, we'll adjust the constructor here
            // Using the class-level instances
            MainController.this.adminView.frame.setVisible(true);
            mainView.setVisible(false);
        }
    }

    // Entry point for the application. Initializes the MainController.
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainController());
    }

    // ActionListener for the "Top Setups" button in the main menu.
    // It fetches top components and displays a message.
    class TopSetupsListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            CPUModel topCPU = new CPUModel().fetchTopCPUById();
            GPUModel topGPU = new GPUModel().fetchTopGPU();
            MemoryModel topMemory = new MemoryModel().fetchTopMemory();
            PowerSupplyModel topPowerSupply = new PowerSupplyModel().fetchTopPowerSupply();
            CoolingModel topCooling = new CoolingModel().fetchTopCooling();
            StorageModel topStorage = new StorageModel().fetchTopStorage();

            String message = "Top Setups:\n";
            message += "CPU: " + topCPU.getName() + "\n";
            message += "GPU: " + topGPU.getName() + "\n";
            message += "Memory: " + topMemory.getSize() + "\n";
            message += "Power Supply: " + topPowerSupply.getPowerClass() + "\n";
            message += "Cooling: " + topCooling.getType() + "\n";
            message += "Storage: " + topStorage.getType() + " (" + topStorage.getSize() + ")";

            JOptionPane.showMessageDialog(null, message);
        }
    }
}
