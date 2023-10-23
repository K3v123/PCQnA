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

public class MotherboardController {

    private MotherboardModel motherboardModel;
    private MotherboardView motherboardView;
    private MainView mainView;

    public MotherboardController(MotherboardModel motherboardModel, MotherboardView motherboardView, MainView mainView) {
        this.motherboardModel = motherboardModel;
        this.motherboardView = motherboardView;
        this.mainView = mainView;

        this.motherboardView.addGoBackButtonListener(new GoBackListener());
        this.motherboardView.addFetchDetailsButtonListener(new FetchDetailsListener());
    }

    class GoBackListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            motherboardView.dispose();
            mainView.setVisible(true);
        }
    }

    class FetchDetailsListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                // For simplicity, let's fetch the first motherboard details (you can modify this to fetch specific details or provide options in the view)
                String type = motherboardModel.getType();
                String size = motherboardModel.getSize();
                motherboardView.setMotherboardDetails(type, size);
            } catch (Exception ex) {
                motherboardView.displayErrorMessage("Error fetching motherboard details.");
            }
        }
    }
}
