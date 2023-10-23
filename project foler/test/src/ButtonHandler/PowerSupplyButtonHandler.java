/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ButtonHandler;
import Base.DatabaseManager;
import Control.PowerSupplyController;
import view.MainView;
import view.PowerSupplyView;

/**
 *
 * @author kq635
 */
public class PowerSupplyButtonHandler {

    private MainView mainView;
    private DatabaseManager dbManager;

    // Constructor that takes a MainView and a DatabaseManager as parameters
    public PowerSupplyButtonHandler(MainView mainView, DatabaseManager dbManager) {
        this.mainView = mainView;
        this.dbManager = dbManager;
    }

    // A method to handle the power supply-related action
    public void handle() {
        // Create a PowerSupplyController and a PowerSupplyView
        PowerSupplyController powerSupplyController = new PowerSupplyController(new PowerSupplyView(mainView), dbManager);

        // Hide the mainView (presumably, switching to the power supply-related view)
        mainView.setVisible(false);
    }

}
