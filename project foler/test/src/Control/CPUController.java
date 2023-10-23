/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;
import Base.DatabaseManager;
import Model.CPUModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import view.CPUView;
import view.MainView;

public class CPUController {

    private CPUModel cpuModel;
    private CPUView cpuView;
    private MainView mainView;
    private DatabaseManager databaseManager;

    /**
     * Constructor for the `CPUController` class.
     *
     * @param cpuModel The CPU model associated with this controller.
     * @param cpuView The CPU view associated with this controller.
     * @param mainView The main view of the application.
     */
    public CPUController(CPUModel cpuModel, CPUView cpuView, MainView mainView) {
        this.cpuModel = cpuModel;
        this.cpuView = cpuView;
        this.mainView = mainView;
        this.databaseManager = new DatabaseManager(); // Initialize the database manager

        // Add action listeners for the "Go Back" and "Fetch Details" buttons in the CPU view
        this.cpuView.addGoBackButtonListener(new GoBackListener());
        this.cpuView.addFetchDetailsButtonListener(new FetchDetailsListener());
    }

    /**
     * ActionListener for the "Go Back" button in the CPU view. It hides the CPU
     * view and shows the main view.
     */
    class GoBackListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            cpuView.getFrame().setVisible(false);
            mainView.setVisible(true);
        }
    }

    /**
     * ActionListener for the "Fetch Details" button in the CPU view. It fetches
     * CPU details from the database, sets up a table in the CPU view, and
     * disables row selection in the table.
     */
    class FetchDetailsListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            List<CPUModel> cpuList = databaseManager.fetchCPU();
            cpuView.setupTable(cpuList);

            // Disable row selection in the table
            cpuView.getCPUTable().setRowSelectionAllowed(false);
        }
    }

    /**
     * Setter for the database manager. Allows setting a custom database manager
     * for testing purposes.
     *
     * @param databaseManager The database manager to set.
     */
    public void setDatabaseManager(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

}
