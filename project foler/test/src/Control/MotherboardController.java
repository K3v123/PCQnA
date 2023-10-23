/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;
import Base.DatabaseManager;
import Model.MotherboardModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import view.MainView;
import view.MotherboardView;

public class MotherboardController {

    private MotherboardModel motherboardModel;
    private MotherboardView motherboardView;
    private MainView mainView;
    private DatabaseManager databaseManager;

    public MotherboardController(MotherboardModel motherboardModel, MotherboardView motherboardView, MainView mainView) {
        this.motherboardModel = motherboardModel;
        this.motherboardView = motherboardView;
        this.mainView = mainView;

        this.databaseManager = new DatabaseManager();

        // Add action listeners to buttons in the MotherboardView
        this.motherboardView.addGoBackButtonListener(new GoBackListener());
        this.motherboardView.addFetchDetailsButtonListener(new FetchDetailsListener());
    }

    /**
     * ActionListener for the "Go Back" button in the MotherboardView.
     */
    class GoBackListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            // Close the MotherboardView
            motherboardView.dispose();

            // Make the main view visible again
            mainView.setVisible(true);
        }
    }

    /**
     * ActionListener for the "Fetch Details" button in the MotherboardView.
     */
    class FetchDetailsListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            // Fetch motherboard details from the database
            List<MotherboardModel> motherboardList = databaseManager.fetchMotherboard();

            // Populate the motherboard table in the MotherboardView with fetched details
            motherboardView.setupMotherboardTable(motherboardList);

            // After populating the table, disable the fetch button to prevent duplicate fetching
            motherboardView.getFetchDetailsButton().setEnabled(false);
        }
    }

}
