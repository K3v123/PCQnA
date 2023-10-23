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

// Controller class for managing interactions between the MotherboardModel and MotherboardView.
public class MotherboardController {

    private MotherboardModel motherboardModel;
    private MotherboardView motherboardView;
    private MainView mainView;
    private DatabaseManager databaseManager;

    // Constructor for the MotherboardController.
    public MotherboardController(MotherboardModel motherboardModel, MotherboardView motherboardView, MainView mainView) {
        this.motherboardModel = motherboardModel;
        this.motherboardView = motherboardView;
        this.mainView = mainView;

        // new object created for these
        this.databaseManager = new DatabaseManager();
        this.motherboardView.addGoBackButtonListener(new GoBackListener());
        this.motherboardView.addFetchDetailsButtonListener(new FetchDetailsListener());
    }

    // ActionListener for the "Go Back" button.
    class GoBackListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            motherboardView.dispose();
            mainView.setVisible(true);
        }
    }

    // ActionListener for the "Fetch Details" button.
    class FetchDetailsListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            List<MotherboardModel> motherboardList = databaseManager.fetchMotherboard();
            motherboardView.setupMotherboardTable(motherboardList);

            // After populating the table, disable the fetch button
            motherboardView.getFetchDetailsButton().setEnabled(false);
        }
    }

}
