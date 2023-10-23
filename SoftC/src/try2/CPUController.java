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

public class CPUController {

    private CPUModel cpuModel;
    private CPUView cpuView;
    private MainView mainView;

    public CPUController(CPUModel cpuModel, CPUView cpuView, MainView mainView) {
        this.cpuModel = cpuModel;
        this.cpuView = cpuView;
        this.mainView = mainView;

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
}
