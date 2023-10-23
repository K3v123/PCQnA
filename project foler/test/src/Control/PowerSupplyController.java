/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;
import Base.DatabaseManager;
import Model.PowerSupplyModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import view.PowerSupplyView;

public class PowerSupplyController {

    private PowerSupplyView powerSupplyView;
    private DatabaseManager dbManager;

    // Constructor that takes a PowerSupplyView and a DatabaseManager as parameters
    public PowerSupplyController(PowerSupplyView powerSupplyView, DatabaseManager dbManager) {
        this.powerSupplyView = powerSupplyView;
        this.dbManager = dbManager;

        // Add listeners to relevant components in the PowerSupplyView
        this.powerSupplyView.addGoBackButtonListener(new GoBackListener());
        this.powerSupplyView.addFetchDetailsButtonListener(new FetchPowerSuppliesListener());
    }

    // ActionListener for fetching power supplies
    class FetchPowerSuppliesListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // Fetch a list of PowerSupplyModel objects from the database
            List<PowerSupplyModel> powerSupplyList = PowerSupplyModel.fetchPowerSupplies(dbManager);

            // Pass the fetched data to the PowerSupplyView to display in a table
            powerSupplyView.setupTable(powerSupplyList);
        }
    }

    // ActionListener for going back
    class GoBackListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            // Close the PowerSupply window (presumably, returning to the previous view)
            powerSupplyView.closeWindow();
        }
    }
}
