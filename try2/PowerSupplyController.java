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

public class PowerSupplyController {

    private PowerSupplyView powerSupplyView;
    private DatabaseManager dbManager;

    public PowerSupplyController(PowerSupplyView powerSupplyView, DatabaseManager dbManager) {
        this.powerSupplyView = powerSupplyView;
        this.dbManager = dbManager;

        this.powerSupplyView.addGoBackButtonListener(new GoBackListener());
        this.powerSupplyView.addFetchDetailsButtonListener(new FetchPowerSuppliesListener());
    }

    // Action Lstener for FetchPowerSuppliesListener
    class FetchPowerSuppliesListener implements ActionListener {

        // Overrides the action for actionPerformed method
        @Override
        public void actionPerformed(ActionEvent e) {
            List<PowerSupplyModel> powerSupplyList = PowerSupplyModel.fetchPowerSupplies(dbManager);
            powerSupplyView.setupTable(powerSupplyList);
        }
    }

    // Action Listener for GoBackListener
    class GoBackListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Close the PowerSupply window
            powerSupplyView.closeWindow();
        }
    }
}
