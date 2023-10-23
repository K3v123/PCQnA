/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;
import Base.DatabaseManager;
import Model.MemoryModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import view.MainView;
import view.MemoryView;

public class MemoryController {

    private MemoryModel memoryModel;
    private MemoryView memoryView;
    private MainView mainView;
    private DatabaseManager dbManager;

    // Constructor to initialize the MemoryController
    public MemoryController(MemoryModel memoryModel, MemoryView memoryView, MainView mainView) {
        this.memoryModel = memoryModel;
        this.memoryView = memoryView;
        this.mainView = mainView;

        // Add a listener to the "Go Back" button in the MemoryView
        this.memoryView.addGoBackButtonListener(new GoBackListener());

        // Initialize the database manager
        this.dbManager = new DatabaseManager();

        // Add a listener to the "Fetch Details" button in the MemoryView
        this.memoryView.addFetchDetailsButtonListener(new FetchDetailsListener());
    }

    // ActionListener for the "Go Back" button
    class GoBackListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            memoryView.frame.dispose();  // Close the MemoryView window
            mainView.setVisible(true);  // Show the main view again
        }
    }

    // ActionListener for the "Fetch Details" button
    class FetchDetailsListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            List<MemoryModel> memoryList = dbManager.fetchMemory();  // Fetch memory details from the database
            memoryView.setupTable(memoryList);  // Display memory details in a table in the MemoryView
        }
    }
}
