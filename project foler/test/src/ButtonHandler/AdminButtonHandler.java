/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ButtonHandler;
import view.AdminView;
import view.MainView;

/**
 * This is the `AdminButtonHandler` class. It is responsible for handling button
 * clicks related to administration tasks.
 *
 * @author kq635
 */
public class AdminButtonHandler {
    // Declare private member variables to store references to MainView and AdminView.

    private MainView mainView;
    private AdminView adminView;

    /**
     * Constructor for the `AdminButtonHandler` class.
     *
     * @param mainView The MainView instance to work with.
     * @param adminView The AdminView instance to work with.
     */
    public AdminButtonHandler(MainView mainView, AdminView adminView) {
        this.mainView = mainView;
        this.adminView = adminView;
    }

    /**
     * This method is called when an administrative button is clicked. It makes
     * the adminView frame visible and hides the mainView.
     */
    public void handle() {
        adminView.frame.setVisible(true); // Show the adminView frame.
        mainView.setVisible(false);       // Hide the mainView.
    }
}
