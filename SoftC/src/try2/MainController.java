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


public class MainController {

    private MainView mainView;

    public MainController() {
        this.mainView = new MainView();

        // Create instances of models and views
        GPUModel gpuModel = new GPUModel("someId", "someName", "someSpeed", "someClassification");
        GPUView gpuView = new GPUView();

        MotherboardModel motherboardModel = new MotherboardModel("someId", "someName", "someType", "someSize");
        MotherboardView motherboardView = new MotherboardView();

        TPUModel tpuModel = new TPUModel("someId", "someName", "someSpeed");
        TPUView tpuView = new TPUView();

        StorageModel storageModel = new StorageModel("someId", "someName", "someType", "someSize", 123.45);
        StorageView storageView = new StorageView();

        CoolingModel coolingModel = new CoolingModel("someId", "someName", "someType");
        CoolingView coolingView = new CoolingView();

        PowerSupplyModel powerSupplyModel = new PowerSupplyModel("someId", "someName", "someClassification");
        PowerSupplyView powerSupplyView = new PowerSupplyView();

        MemoryModel memoryModel = new MemoryModel("someId", "someName", "someSize");
        MemoryView memoryView = new MemoryView();

        CPUModel cpuModel = new CPUModel("someId", "someName", "someSpeed", false);
        CPUView cpuView = new CPUView();

        DatabaseManager dbManager = new DatabaseManager();

        // Add action listeners with controllers initialized with models and views
        this.mainView.addGPUButtonListener(e -> new GPUController(gpuView, dbManager));
        this.mainView.addPowerSupplyButtonListener(e -> new PowerSupplyController(powerSupplyModel, powerSupplyView, mainView, dbManager));
        this.mainView.addMotherboardButtonListener(e -> new MotherboardController(motherboardModel, motherboardView, mainView));
        this.mainView.addTPUButtonListener(e -> new TPUController(tpuModel, tpuView, mainView));
        this.mainView.addStorageButtonListener(e -> new StorageController(storageModel, storageView, mainView));
        this.mainView.addCoolingButtonListener(e -> new CoolingController(coolingModel, coolingView, mainView));
        this.mainView.addMemoryButtonListener(e -> new MemoryController(memoryModel, memoryView, mainView));
        this.mainView.addCPUButtonListener(e -> new CPUController(cpuModel, cpuView, mainView));
        this.mainView.addAdminButtonListener(e -> new AdminController(new AdminView(), mainView));
        this.mainView.addMostAskedButtonListener(e -> new MostAskedController(new MostAskedView(), mainView));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainController());
    }
}
