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

public class MemoryController {

    private MemoryModel memoryModel;
    private MemoryView memoryView;
    private MainView mainView;

    public MemoryController(MemoryModel memoryModel, MemoryView memoryView, MainView mainView) {
        this.memoryModel = memoryModel;
        this.memoryView = memoryView;
        this.mainView = mainView;

        this.memoryView.addGoBackButtonListener(new GoBackListener());
        this.memoryView.addFetchDetailsButtonListener(new FetchDetailsListener());
    }

    class GoBackListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            memoryView.dispose();  // Close the MemoryView window
            mainView.setVisible(true);  // Show the main view again
        }
    }

    class FetchDetailsListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            try {
                String size = memoryModel.getSize();
                memoryView.setMemoryDetails(size);
            } catch (Exception ex) {
                memoryView.displayErrorMessage("Error fetching Memory details: " + ex.getMessage());
            }
        }
    }
}
