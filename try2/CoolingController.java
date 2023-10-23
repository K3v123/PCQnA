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

public class CoolingController {

    private CoolingModel coolingModel;
    private CoolingView coolingView;
    private MainView mainView;
    private DatabaseManager databaseManager;

    // Constructor for this CoolingController class
    public CoolingController(CoolingModel coolingModel, CoolingView coolingView, MainView mainView, DatabaseManager dbManager) {
        this.coolingModel = coolingModel;
        this.coolingView = coolingView;
        this.mainView = mainView;
        this.databaseManager = dbManager;  // Initialize the databaseManager

        this.coolingView.addGoBackButtonListener(new GoBackListener());
        this.coolingView.addFetchDetailsButtonListener(new FetchDetailsListener());
    }

    class GoBackListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            coolingView.dispose();  // Close the CoolingView window
            mainView.setVisible(true);  // Show the main view again
        }
    }

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
