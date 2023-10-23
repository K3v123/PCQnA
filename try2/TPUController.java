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

public class TPUController {

    private TPUModel tpuModel;
    private TPUView tpuView;
    private MainView mainView;

    private DatabaseManager dbManager;

    public TPUController(TPUModel tpuModel, TPUView tpuView, MainView mainView, DatabaseManager dbManager) {
        this.tpuModel = tpuModel;
        this.tpuView = tpuView;
        this.mainView = mainView;
        this.dbManager = dbManager;

        // Add a listener to the "Go Back" button in TPUView
        this.tpuView.addGoBackButtonListener(new GoBackListener());
        
        // Add a listener to the "Fetch Details" button in TPUView
        this.tpuView.addFetchDetailsButtonListener(new FetchDetailsListener());

    }

    // ActionListener to handle "Go Back" button clicks
    class GoBackListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            tpuView.dispose();  // Close the TPUView window
            mainView.setVisible(true);  // Show the main view again
        }
    }

    // ActionListener to handle "Fetch Details" button clicks
    class FetchDetailsListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            List<TPUModel> tpuList = dbManager.fetchTPU();
            tpuView.setupTable(tpuList);
        }
    }

}
