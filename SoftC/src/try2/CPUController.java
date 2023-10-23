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

public class CPUController {

    private CPUModel cpuModel;
    private CPUView cpuView;
    private MainView mainView;
    private DatabaseManager databaseManager;

    public CPUController(CPUModel cpuModel, CPUView cpuView, MainView mainView) {
        this.cpuModel = cpuModel;
        this.cpuView = cpuView;
        this.mainView = mainView;
        this.databaseManager = new DatabaseManager();

        this.cpuView.addGoBackButtonListener(new GoBackListener());
        this.cpuView.addFetchDetailsButtonListener(new FetchDetailsListener());
    }

    class GoBackListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            cpuView.dispose();  // Close the CPUView window
            mainView.setVisible(true);  // Show the main view again
        }
    }

    class FetchDetailsListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            List<CPUModel> cpuList = databaseManager.fetchCPU();  // This method needs to be updated in DatabaseManager
            cpuView.setupTable(cpuList);  // This method needs to be added to CPUView
            cpuView.getCPUTable().getSelectionModel().addListSelectionListener(event -> {
                if (!event.getValueIsAdjusting()) {
                    int selectedRow = cpuView.getCPUTable().getSelectedRow();
                    if (selectedRow != -1) {
                        CPUModel selectedCPU = cpuList.get(selectedRow);
                        SelectionStore.getInstance().storeCPUSelection(selectedCPU);  // This method needs to be added to SelectionStore
                    }
                }
            });
        }
    }

    public void actionPerformed(ActionEvent e) {
        try {
            cpuModel.loadFromDatabase();  // Loading the CPU details from the database
            String speed = cpuModel.getSpeed();
            String overclock = cpuModel.getOverclock();
            cpuView.setCPUDetails(speed, overclock); // Setting the details in the view
        } catch (Exception ex) {
            cpuView.displayErrorMessage("Error fetching CPU details: " + ex.getMessage());
        }
    }
}
