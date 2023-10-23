/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;
import Base.DatabaseManager;
import Model.CoolingModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import view.CoolingView;
import view.MainView;

public class CoolingController {

    private CoolingModel coolingModel;
    private CoolingView coolingView;
    private MainView mainView;
    private DatabaseManager databaseManager;

    /**
     * Constructor for the `CoolingController` class.
     *
     * @param coolingModel The Cooling Model representing cooling components.
     * @param coolingView The Cooling View responsible for displaying cooling
     * details.
     * @param mainView The main view of the application.
     * @param dbManager The database manager for accessing cooling-related data.
     */
    public CoolingController(CoolingModel coolingModel, CoolingView coolingView, MainView mainView, DatabaseManager dbManager) {
        this.coolingModel = coolingModel;
        this.coolingView = coolingView;
        this.mainView = mainView;
        this.databaseManager = dbManager;  // Initialize the databaseManager

        // Add action listeners to handle user interactions
        this.coolingView.addGoBackButtonListener(new GoBackListener());
        this.coolingView.addFetchDetailsButtonListener(new FetchDetailsListener());
    }

    /**
     * ActionListener for handling the "Go Back" button click event.
     */
    class GoBackListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            coolingView.frame.dispose();  // Close the CoolingView window
            mainView.setVisible(true);  // Show the main view again
        }
    }

    /**
     * ActionListener for handling the "Fetch Details" button click event.
     */
    class FetchDetailsListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            try {
                // Fetch cooling components from the database
                List<CoolingModel> coolingList = databaseManager.fetchCooling();

                // Setup the table in the view with the fetched data
                coolingView.setupCoolingTable(coolingList);

            } catch (Exception ex) {
                coolingView.displayErrorMessage("Error fetching cooling details: " + ex.getMessage());
            }
        }
    }
}
