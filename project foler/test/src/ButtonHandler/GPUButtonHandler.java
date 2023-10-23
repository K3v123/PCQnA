/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ButtonHandler;
import Base.DatabaseManager;
import Control.GPUController;
import view.GPUView;
import view.MainView;

public class GPUButtonHandler {

    private MainView mainView;
    private DatabaseManager dbManager;

    /**
     * Constructs a GPUButtonHandler with references to the main view and the
     * database manager.
     *
     * @param mainView The main view of the application.
     * @param dbManager The database manager used for database operations.
     */
    public GPUButtonHandler(MainView mainView, DatabaseManager dbManager) {
        this.mainView = mainView;
        this.dbManager = dbManager;
    }

    /**
     * Handles the action related to GPU components.
     */
    public void handle() {
        // Create a GPUView for displaying GPU-related information
        GPUView gpuView = new GPUView();

        // Create a GPUController to handle GPU-related actions
        GPUController gpuController = new GPUController(gpuView, dbManager);

        // Add an ActionListener to the "Go Back" button in the GPUView
        gpuView.addGoBackButtonListener(e -> {
            gpuView.getFrame().setVisible(false); // Hide the GPUView frame
            mainView.setVisible(true); // Show the main view
        });

        // Hide the main view and show the GPUView
        mainView.setVisible(false);
        gpuView.getFrame().setVisible(true);
    }
}
