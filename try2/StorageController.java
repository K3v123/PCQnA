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

public class StorageController {

    private StorageModel storageModel;
    private StorageView storageView;
    private MainView mainView;
    private DatabaseManager dbManager;

    // Constructor for this StorageController class
    // Constructs a StorageController with references to the StorageModel, StorageView, MainView, and DatabaseManager.
    public StorageController(StorageModel storageModel, StorageView storageView, MainView mainView, DatabaseManager dbManager) {
        this.storageModel = storageModel;
        this.storageView = storageView;
        this.mainView = mainView;
        this.dbManager = dbManager;

        this.storageView.addGoBackButtonListener(new GoBackListener());
        this.storageView.addFetchDetailsButtonListener(new FetchDetailsListener());
    }

    // ActionListener for handling the "Go Back" action.
    class GoBackListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            storageView.dispose();  // Close the StorageView window
            mainView.setVisible(true);  // Show the main view
        }
    }

    // ActionListener for handling the "Fetch Details" action.
    class FetchDetailsListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            List<StorageModel> storageList = dbManager.fetchStorage();
            storageView.setupTable(storageList);
        }
    }

}
