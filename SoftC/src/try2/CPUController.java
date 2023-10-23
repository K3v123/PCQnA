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
        // Assuming you add a button or action to fetch CPU details in the view
        this.cpuView.addFetchDetailsButtonListener(new FetchDetailsListener());
    }

    class GoBackListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            cpuView.dispose();  // Close the CPUView window
            mainView.setVisible(true);  // Show the main view
        }
    }

    class FetchDetailsListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String name = cpuModel.getName();
                String speed = cpuModel.getSpeed();
                boolean overclock = cpuModel.isOverclock();
                cpuView.setCPUDetails(name, speed, overclock);
            } catch (Exception ex) {
                cpuView.displayErrorMessage("Error fetching CPU details: " + ex.getMessage());
            }
        }
    }

    // ... [Other methods and listeners as needed]
}
