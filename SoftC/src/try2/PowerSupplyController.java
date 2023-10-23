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

        @Override
        public void actionPerformed(ActionEvent e) {
            powerSupplyView.dispose();
            mainView.setVisible(true);
        }
    }

    class FetchDetailsListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                // Here, you'd typically fetch the details from the database or the model
                // For demonstration purposes, I'm using the model's getter directly
                String classification = powerSupplyModel.getClassification();
                powerSupplyView.setPowerSupplyDetails(classification);
            } catch (Exception ex) {
                // Assuming you have an error message display method in your view
                powerSupplyView.displayErrorMessage("Error fetching power supply details: " + ex.getMessage());
            }
        }
    }

    // ... [Other methods and listeners as needed]
}
