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
    private SelectionStore selectionStore;
    private AdminController adminController;
    private AdminView adminView;

    public MainController() {
        this.dbManager = new DatabaseManager();
        this.mainView = new MainView();
        this.selectionStore = new SelectionStore();
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
        this.mainView.addFinalSelectionButtonListener(new FinalSelectionButtonListener());
    }

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

    class MotherboardButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            MotherboardController motherboardController = new MotherboardController(new MotherboardModel(), new MotherboardView(), mainView);
            mainView.setVisible(false);
        }
    }

    class TPUButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            TPUController tpuController = new TPUController(new TPUModel(), new TPUView(), mainView);
            mainView.setVisible(false);
        }
    }

    class StorageButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            StorageController storageController = new StorageController(new StorageModel(), new StorageView(), mainView);
            mainView.setVisible(false);
        }
    }

    class CoolingButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            CoolingController coolingController = new CoolingController(new CoolingModel(), new CoolingView(), mainView);
            mainView.setVisible(false);
        }
    }

    class PowerSupplyButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            PowerSupplyController powerSupplyController = new PowerSupplyController(new PowerSupplyModel(), new PowerSupplyView(), mainView, dbManager);
            mainView.setVisible(false);
        }
    }

    class MemoryButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            MemoryController memoryController = new MemoryController(new MemoryModel(), new MemoryView(), mainView);
            mainView.setVisible(false);
        }
    }

    class CPUButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            CPUController cpuController = new CPUController(new CPUModel(), new CPUView(), mainView);
            mainView.setVisible(false);
        }
    }

    class MostAskedButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            MostAskedController mostAskedController = new MostAskedController(new MostAskedView(), mainView);
            mainView.setVisible(false);
        }
    }

    class AdminButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // Since we no longer use DatabaseManager for hide/unhide, we'll adjust the constructor here
            // Using the class-level instances
            MainController.this.adminView.frame.setVisible(true);
            mainView.setVisible(false);
        }
    }

    class FinalSelectionButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            new FinalSelectionView(selectionStore);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainController());
    }
}
