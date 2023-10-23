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

public class TPUController {

    private TPUModel tpuModel;
    private TPUView tpuView;
    private MainView mainView;

    public TPUController(TPUModel tpuModel, TPUView tpuView, MainView mainView) {
        this.tpuModel = tpuModel;
        this.tpuView = tpuView;
        this.mainView = mainView;

        this.tpuView.addGoBackButtonListener(new GoBackListener());
        this.tpuView.addFetchDetailsButtonListener(new FetchDetailsListener());
    }

    class GoBackListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            tpuView.dispose();  // Close the TPUView window
            mainView.setVisible(true);  // Show the main view again
        }
    }

    class FetchDetailsListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            try {
                String speed = tpuModel.getSpeed();
                tpuView.setTPUDetails(speed);
            } catch (Exception ex) {
                tpuView.displayErrorMessage("Error fetching TPU details: " + ex.getMessage());
            }
        }
    }

    // ... [Other methods and listeners as needed]
}
