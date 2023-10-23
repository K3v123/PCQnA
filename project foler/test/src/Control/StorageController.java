/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;
import Base.DatabaseManager;
import Model.StorageModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import view.MainView;
import view.StorageView;

public class StorageController {

    private StorageModel storageModel;
    private StorageView storageView;
    private MainView mainView;
    private DatabaseManager dbManager;

    // Constructor that takes a StorageModel, StorageView, MainView, and DatabaseManager as parameters
    public StorageController(StorageModel storageModel, StorageView storageView, MainView mainView, DatabaseManager dbManager) {
        this.storageModel = storageModel;
        this.storageView = storageView;
        this.mainView = mainView;
        this.dbManager = dbManager;

        // Add listeners to relevant components in the StorageView
        this.storageView.addGoBackButtonListener(new GoBackListener());
        this.storageView.addFetchDetailsButtonListener(new FetchDetailsListener());
    }

    // ActionListener for going back
    class GoBackListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            storageView.frame.dispose();  // Dispose of the JFrame object
            mainView.setVisible(true);  // Show the main view
        }
    }

    // ActionListener for fetching storage details
    class FetchDetailsListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // Fetch a list of StorageModel objects from the database using dbManager
            List<StorageModel> storageList = dbManager.fetchStorage();

            // Pass the fetched data to the StorageView to display in a table
            storageView.setupTable(storageList);
        }
    }
}
