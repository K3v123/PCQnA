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

public class PowerSupplyController {

    private PowerSupplyModel powerSupplyModel;
    private PowerSupplyView powerSupplyView;
    private MainView mainView;
    private DatabaseManager dbManager;

    public PowerSupplyController(PowerSupplyModel powerSupplyModel, PowerSupplyView powerSupplyView, MainView mainView, DatabaseManager dbManager) {
        this.powerSupplyModel = powerSupplyModel;
        this.powerSupplyView = powerSupplyView;
        this.mainView = mainView;
        this.dbManager = dbManager;

        this.powerSupplyView.addGoBackButtonListener(new GoBackListener());
        this.powerSupplyView.addFetchDetailsButtonListener(new FetchDetailsListener());
    }

    class GoBackListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            powerSupplyView.dispose();  // Close the PowerSupplyView window
            mainView.setVisible(true);  // Show the main view again
        }
    }

    class FetchDetailsListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            try {
                String classification = powerSupplyModel.getClassification();  // Get classification
                powerSupplyView.setPowerSupplyDetails(classification);
            } catch (Exception ex) {
                powerSupplyView.displayErrorMessage("Error fetching Power Supply details: " + ex.getMessage());
            }
        }
    }
}
