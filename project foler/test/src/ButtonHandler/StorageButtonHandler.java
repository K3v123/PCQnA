/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ButtonHandler;
import Base.DatabaseManager;
import Control.StorageController;
import Model.StorageModel;
import view.MainView;
import view.StorageView;

/**
 *
 * @author kq635
 */
public class StorageButtonHandler {

    private MainView mainView;
    private DatabaseManager dbManager;

    // Constructor that takes a MainView and a DatabaseManager as parameters
    public StorageButtonHandler(MainView mainView, DatabaseManager dbManager) {
        this.mainView = mainView;
        this.dbManager = dbManager;
    }

    // Method to handle the storage-related action
    public void handle() {
        // Create a StorageController with a StorageModel, StorageView, mainView, and dbManager
        StorageController storageController = new StorageController(new StorageModel(), new StorageView(), mainView, dbManager);

        // Hide the mainView (presumably, switching to the storage-related view)
        mainView.setVisible(false);
    }
}
