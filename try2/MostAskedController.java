/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package try2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 *
 * @author kq635
 */

public class MostAskedController {

    private MostAskedView mostAskedView;
    private MainView mainView;
    private DatabaseManager dbManager;

    // Initializes the MostAskedController with a MostAskedView, MainView, and DatabaseManager.
    public MostAskedController(MostAskedView mostAskedView, MainView mainView) {
        this.mostAskedView = mostAskedView;
        this.mainView = mainView;
        this.dbManager = new DatabaseManager();

        // Populate the view with data from the database
        populateMostAskedData();

        this.mostAskedView.addGoBackButtonListener(new GoBackListener());
    }

    // Populates the MostAskedView with data from the database.
    private void populateMostAskedData() {
        // Fetch the most asked components from the database
        List<String> mostAskedComponents = dbManager.getMostAskedComponents();
        mostAskedView.displayMostAskedOptions(mostAskedComponents);
    }

    // ActionListener for the "Go Back" button in the MostAskedView.
    private class GoBackListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            mostAskedView.getFrame().setVisible(false);
            mainView.getFrame().setVisible(true);
        }
    }

}
