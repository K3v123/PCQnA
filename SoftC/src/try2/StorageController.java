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

public class StorageController {

    private StorageModel storageModel;
    private StorageView storageView;
    private MainView mainView;

    public StorageController(StorageModel storageModel, StorageView storageView, MainView mainView) {
        this.storageModel = storageModel;
        this.storageView = storageView;
        this.mainView = mainView;

        this.storageView.addGoBackButtonListener(new GoBackListener());
        // Assuming you add a button or action to fetch Storage details in the view
        this.storageView.addFetchDetailsButtonListener(new FetchDetailsListener());
    }

    class GoBackListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            storageView.dispose();  // Close the StorageView window
            mainView.setVisible(true);  // Show the main view
        }
    }

    class FetchDetailsListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String type = storageModel.getType();
                String size = storageModel.getSize();
                String speed = String.valueOf(storageModel.getSpeed());
                storageView.setStorageDetails(type, size, speed);
            } catch (Exception ex) {
                storageView.displayErrorMessage("Error fetching storage details: " + ex.getMessage());
            }
        }
    }

    // ... [Other methods and listeners as needed]
}
