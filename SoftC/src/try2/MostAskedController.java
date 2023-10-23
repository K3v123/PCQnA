/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package try2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author kq635
 */
public class MostAskedController {

    private MostAskedView mostAskedView;
    private MainView mainView;

    public MostAskedController(MostAskedView mostAskedView, MainView mainView) {
        this.mostAskedView = mostAskedView;
        this.mainView = mainView;

        // Populate the view with data (this would typically be fetched from a database)
        populateMostAskedData();

        // Add action listener to the "Go Back" button
        this.mostAskedView.addGoBackButtonListener(new GoBackListener());
    }

    private void populateMostAskedData() {
        // Normally, you'd fetch this data from a database.
        // For the sake of this example, let's use some mock data.
        String data = "1. RTX 4090 - Most popular GPU\n"
                + "2. Intel i9 - Most popular CPU\n"
                + "3. 32GB DDR4 RAM - Most popular Memory\n"
                + "4. 1TB SSD - Most popular Storage";

        mostAskedView.setMostAskedData(data);
    }

    class GoBackListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            mostAskedView.dispose();  // Close the MostAskedView window
            mainView.setVisible(true);  // Show the main view
        }
    }
}
