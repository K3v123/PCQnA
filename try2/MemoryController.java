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

public class MemoryController {

    private MemoryModel memoryModel;
    private MemoryView memoryView;
    private MainView mainView;
    private DatabaseManager dbManager;

    // Constructor for this class
    public MemoryController(MemoryModel memoryModel, MemoryView memoryView, MainView mainView) {
        this.memoryModel = memoryModel;
        this.memoryView = memoryView;
        this.mainView = mainView;

        // new Objects created here
        this.memoryView.addGoBackButtonListener(new GoBackListener());
        this.dbManager = new DatabaseManager();
        this.memoryView.addFetchDetailsButtonListener(new FetchDetailsListener());
    }

    class GoBackListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            memoryView.dispose();  // Close the MemoryView window
            mainView.setVisible(true);  // Show the main view again
        }
    }

    class FetchDetailsListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            List<MemoryModel> memoryList = dbManager.fetchMemory();  // Fetch memory details from database
            memoryView.setupTable(memoryList);  // Display memory details in table
        }
    }
}
