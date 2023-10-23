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

    // constructor for this CPUController class
    public CPUController(CPUModel cpuModel, CPUView cpuView, MainView mainView) {
        this.cpuModel = cpuModel;
        this.cpuView = cpuView;
        this.mainView = mainView;
        this.databaseManager = new DatabaseManager();

        this.cpuView.addGoBackButtonListener(new GoBackListener());
        this.cpuView.addFetchDetailsButtonListener(new FetchDetailsListener());
    }

    // ActionListener to handle going back to the main view.
    class GoBackListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // Hide the CPUView and show the main view.
            cpuView.getFrame().setVisible(false);
            mainView.setVisible(true);
        }
    }


    class FetchDetailsListener implements ActionListener {

//        @Override
//        public void actionPerformed(ActionEvent e) {
//            List<CPUModel> cpuList = databaseManager.fetchCPU();
//            cpuView.setupTable(cpuList);
//
//            // Disable row selection in the table
//            cpuView.getCPUTable().setRowSelectionAllowed(false);
//
//            cpuView.getCPUTable().getSelectionModel().addListSelectionListener(event -> {
//                if (!event.getValueIsAdjusting()) {
//                    int selectedRow = cpuView.getCPUTable().getSelectedRow();
//                    if (selectedRow != -1 && selectedRow < cpuList.size()) {
//                        CPUModel selectedCPUFromTable = cpuList.get(selectedRow);
//                        SelectionStore.getInstance().storeCPUSelection(selectedCPUFromTable);
//                    }
//                }
//            });
//        }
        
        // Fetch CPU data from the database and display it in the CPUView table.
        @Override
        public void actionPerformed(ActionEvent e) {
            List<CPUModel> cpuList = databaseManager.fetchCPU();
            cpuView.setupTable(cpuList);

            // Disable row selection.
            cpuView.getCPUTable().setRowSelectionAllowed(false);
        }

    }

}
