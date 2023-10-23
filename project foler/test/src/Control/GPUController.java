/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;
import Base.DatabaseManager;
import Model.GPUModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import view.GPUView;

public class GPUController {

    private GPUView gpuView;
    private GPUModel gpuModel;
    private DatabaseManager dbManager;

    /**
     * Constructs a GPUController with references to the GPUView and
     * DatabaseManager.
     *
     * @param gpuView The GPUView for displaying GPU information.
     * @param dbManager The DatabaseManager for database operations.
     */
    public GPUController(GPUView gpuView, DatabaseManager dbManager) {
        this.gpuView = gpuView;
        this.dbManager = dbManager;

        // Mock values for GPUModel instantiation (You may fetch real GPU data from the database)
        this.gpuModel = new GPUModel("RTX 4090", "2235-2790 MHz", "Best", "");

        // Attach listeners to GPUView buttons
        this.gpuView.addFetchTypesButtonListener(new FetchTypesListener());
        this.gpuView.addGoBackButtonListener(new GoBackListener());
    }

    /**
     * ActionListener for the "Fetch Types" button in the GPUView.
     */
    class FetchTypesListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // Fetch GPU data from the database
            List<GPUModel> gpuList = dbManager.fetchGPU();

            // Update the GPUView with the fetched GPU data
            gpuView.setupTable(gpuList);
        }
    }

    /**
     * ActionListener for the "Go Back" button in the GPUView.
     */
    class GoBackListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // Close the GPUView window when "Go Back" is clicked
            gpuView.closeWindow();
        }
    }
}
