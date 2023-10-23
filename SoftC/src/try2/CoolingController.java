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

public class CoolingController {

    private CoolingModel coolingModel;
    private CoolingView coolingView;
    private MainView mainView;

    public CoolingController(CoolingModel coolingModel, CoolingView coolingView, MainView mainView) {
        this.coolingModel = coolingModel;
        this.coolingView = coolingView;
        this.mainView = mainView;

        this.coolingView.addGoBackButtonListener(new GoBackListener());
        // Assuming you add a button or action to fetch Cooling details in the view
        this.coolingView.addFetchDetailsButtonListener(new FetchDetailsListener());
    }

    class GoBackListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            coolingView.dispose();  // Close the CoolingView window
            mainView.setVisible(true);  // Show the main view
        }
    }

    class FetchDetailsListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String type = coolingModel.getType();
                coolingView.setCoolingDetails(type);
            } catch (Exception ex) {
                // Assuming you have a method to display errors in CoolingView
                coolingView.displayErrorMessage("Error fetching Cooling details: " + ex.getMessage());
            }
        }
    }

    // ... [Other methods and listeners as needed]
}
