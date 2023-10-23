/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package try2;

/**
 *
 * @author kq635
 */
public class SelectionStore {

    private String gpuSelection;
    private String motherboardSelection;
    private String tpuSelection;
    private String storageSelection;
    private String coolingSelection;
    private String powerSupplySelection;
    private String memorySelection;
    private String cpuSelection;
    private static SelectionStore instance = null;

    public SelectionStore() {
        // Initialize all selections to "Not Selected" by default
        this.gpuSelection = "Not Selected";
        this.motherboardSelection = "Not Selected";
        this.tpuSelection = "Not Selected";
        this.storageSelection = "Not Selected";
        this.coolingSelection = "Not Selected";
        this.powerSupplySelection = "Not Selected";
        this.memorySelection = "Not Selected";
        this.cpuSelection = "Not Selected";
    }

    public static SelectionStore getInstance() {
        if (instance == null) {
            instance = new SelectionStore();
        }
        return instance;
    }

    public void storeCPUSelection(CPUModel selectedCPU) {
        this.cpuSelection = selectedCPU.getName();  // Or any other attribute of the CPUModel you want to store
    }

    // Getters and Setters for each component
    public String getGpuSelection() {
        return gpuSelection;
    }

    public void setGpuSelection(String gpuSelection) {
        this.gpuSelection = gpuSelection;
    }

    public String getMotherboardSelection() {
        return motherboardSelection;
    }

    public void setMotherboardSelection(String motherboardSelection) {
        this.motherboardSelection = motherboardSelection;
    }

    public String getTpuSelection() {
        return tpuSelection;
    }

    public void setTpuSelection(String tpuSelection) {
        this.tpuSelection = tpuSelection;
    }

    public String getStorageSelection() {
        return storageSelection;
    }

    public void setStorageSelection(String storageSelection) {
        this.storageSelection = storageSelection;
    }

    public String getCoolingSelection() {
        return coolingSelection;
    }

    public void setCoolingSelection(String coolingSelection) {
        this.coolingSelection = coolingSelection;
    }

    public String getPowerSupplySelection() {
        return powerSupplySelection;
    }

    public void setPowerSupplySelection(String powerSupplySelection) {
        this.powerSupplySelection = powerSupplySelection;
    }

    public String getMemorySelection() {
        return memorySelection;
    }

    public void setMemorySelection(String memorySelection) {
        this.memorySelection = memorySelection;
    }

    public String getCpuSelection() {
        return cpuSelection;
    }

    public void setCpuSelection(String cpuSelection) {
        this.cpuSelection = cpuSelection;
    }
}
