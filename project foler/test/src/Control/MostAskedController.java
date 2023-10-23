/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;
import Base.DatabaseManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import view.MainView;
import view.MostAskedView;

/**
 *
 * @author kq635
 */
public class MostAskedController {

    private MostAskedView mostAskedView;
    private MainView mainView;
    protected DatabaseManager dbManager;

    // Constructor for MostAskedController
    public MostAskedController(MostAskedView mostAskedView, MainView mainView) {
        this.mostAskedView = mostAskedView;
        this.mainView = mainView;
        this.dbManager = new DatabaseManager();

        // Populate the view with data from the database
        populateMostAskedData();

        // Add a listener for the "Go Back" button
        this.mostAskedView.addGoBackButtonListener(new GoBackListener());
    }

    // Populate the view with data from the database
    protected void populateMostAskedData() {
        // Fetch the most asked components from the database
        List<String> mostAskedComponents = dbManager.getMostAskedComponents();
        mostAskedView.displayMostAskedOptions(mostAskedComponents);
    }

    // ActionListener for the "Go Back" button
    private class GoBackListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            mostAskedView.getFrame().setVisible(false); // Hide the MostAskedView
            mainView.getFrame().setVisible(true); // Show the MainView
        }
    }

}
