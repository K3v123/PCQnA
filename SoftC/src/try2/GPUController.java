/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package try2;

/**
 *
 * @author kq635
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GPUController {

    private GPUView gpuView;
    private GPUModel gpuModel;
    private DatabaseManager dbManager;

    public GPUController(GPUView gpuView, DatabaseManager dbManager) {
        this.gpuView = gpuView;
        this.dbManager = dbManager;

        // Mock values for GPUModel instantiation
        this.gpuModel = new GPUModel("RTX 4090", "2235-2790 MHz", "Best", "");

        // Attach listeners
        this.gpuView.addFetchTypesButtonListener(new FetchTypesListener());
        this.gpuView.addGoBackButtonListener(new GoBackListener());

        // Display initial GPU details
        displayGPUDetails();
    }

    class FetchTypesListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            // For this example, let's just display the details
            // In a real-world scenario, you would fetch and update details dynamically
            displayGPUDetails();
        }
    }

    class GoBackListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            // Close the GPU window
            gpuView.closeWindow();
        }
    }

    private void displayGPUDetails() {
        // Fetch details from the model
        String name = gpuModel.getName();
        String speed = gpuModel.getSpeed();
        String classification = gpuModel.getClassification();  // Ensure getClassification is defined in GPUModel
        String recommendation = dbManager.fetchGPURecommendation(name);  // Ensure fetchGPURecommendation is defined in DatabaseManager

        // Update the view
        gpuView.setGPUDetails(name, speed, classification);
        gpuView.setRecommendation(recommendation);
    }
}
