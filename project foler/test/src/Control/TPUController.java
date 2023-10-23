/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;
import Base.DatabaseManager;
import Model.TPUModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import view.MainView;
import view.TPUView;

public class TPUController {

    private TPUModel tpuModel;
    private TPUView tpuView;
    private MainView mainView;
    private DatabaseManager dbManager;

    /**
     * Constructor for the TPUController class.
     *
     * @param tpuModel The TPU model representing TPU data.
     * @param tpuView The TPU view for displaying TPU-related information.
     * @param mainView The main view of the application.
     * @param dbManager The database manager for accessing TPU data.
     */
    public TPUController(TPUModel tpuModel, TPUView tpuView, MainView mainView, DatabaseManager dbManager) {
        this.tpuModel = tpuModel;
        this.tpuView = tpuView;
        this.mainView = mainView;
        this.dbManager = dbManager;

        // Add action listeners to TPUView components
        this.tpuView.addGoBackButtonListener(new GoBackListener());
        this.tpuView.addFetchDetailsButtonListener(new FetchDetailsListener());
    }

    /**
     * ActionListener for the "Go Back" button in the TPUView. It disposes of
     * the TPUView frame and sets the main view to be visible.
     */
    class GoBackListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            tpuView.frame.dispose();  // Call dispose on the JFrame object
            mainView.setVisible(true);
        }
    }

    /**
     * ActionListener for the "Fetch Details" button in the TPUView. It fetches
     * TPU data from the database and sets up the TPUView to display the data.
     */
    class FetchDetailsListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            List<TPUModel> tpuList = dbManager.fetchTPU();  // Fetch TPU data
            tpuView.setupTable(tpuList);  // Set up the TPUView to display the data
        }
    }
}
