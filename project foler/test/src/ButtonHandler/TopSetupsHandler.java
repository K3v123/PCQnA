/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ButtonHandler;
import Model.CPUModel;
import Model.CoolingModel;
import Model.GPUModel;
import Model.MemoryModel;
import Model.PowerSupplyModel;
import Model.StorageModel;
import javax.swing.JOptionPane;

public class TopSetupsHandler {

    /**
     * Handle the retrieval of top setups and display the information in a
     * dialog box.
     */
    public void handle() {
        // Fetch the top setups for each hardware component
        CPUModel topCPU = new CPUModel().fetchTopCPUById();
        GPUModel topGPU = new GPUModel().fetchTopGPU();
        MemoryModel topMemory = new MemoryModel().fetchTopMemory();
        PowerSupplyModel topPowerSupply = new PowerSupplyModel().fetchTopPowerSupply();
        CoolingModel topCooling = new CoolingModel().fetchTopCooling();
        StorageModel topStorage = new StorageModel().fetchTopStorage();

        // Create a message containing the top setups
        String message = "Top Setups:\n";
        message += "CPU: " + topCPU.getName() + "\n";
        message += "GPU: " + topGPU.getName() + "\n";
        message += "Memory: " + topMemory.getSize() + "\n";
        message += "Power Supply: " + topPowerSupply.getPowerClass() + "\n";
        message += "Cooling: " + topCooling.getType() + "\n";
        message += "Storage: " + topStorage.getType() + " (" + topStorage.getSize() + ")";

        // Display the message in a dialog box
        JOptionPane.showMessageDialog(null, message);
    }
}
