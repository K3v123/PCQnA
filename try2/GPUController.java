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
import java.util.List;

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


    }

    // ActionListener implementation for fetching GPU types and populating a table.
    class FetchTypesListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            List<GPUModel> gpuList = dbManager.fetchGPU();
            gpuView.setupTable(gpuList);
        }
    }

    // ActionListener implementation for going back from the GPU window.
    class GoBackListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            // Close the GPU window
            gpuView.closeWindow();
        }
    }


}
