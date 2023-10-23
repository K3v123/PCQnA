/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import view.AdminView;
import view.MainView;

public class AdminController {

    // Declare private member variables to store references to AdminView, MainView, and control variables.
    private AdminView adminView;
    private MainView mainView;
    private boolean isAdminLoggedIn = false;
    private int loginAttempts = 0;

    /**
     * Constructor for the `AdminController` class.
     *
     * @param adminView The AdminView instance to work with.
     * @param mainView The MainView instance to work with.
     */
    public AdminController(AdminView adminView, MainView mainView) {
        this.adminView = adminView;
        this.mainView = mainView;

        // Register action listeners for buttons in the AdminView.
        this.adminView.addLoginButtonListener(new LoginListener());
        this.adminView.addGoBackButtonListener(new GoBackListener());
        this.adminView.addDashboardGoBackButtonListener(new GoBackListener());
    }

    /**
     * ActionListener for the login button in the AdminView.
     */
    class LoginListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (isAdminLoggedIn) {
                adminView.displaySuccessMessage("You're already logged in!");
                return;
            }

            String username = adminView.getUsername();
            String password = adminView.getPassword();

            if (isValidAdminCredentials(username, password)) {
                isAdminLoggedIn = true;
                adminView.displaySuccessMessage("Logged in successfully!");
                adminView.switchToDashboard();
                // After successful login, apply visibility settings
                applyComponentVisibility();
            } else {
                loginAttempts++;
                if (loginAttempts >= 3) {
                    adminView.displayErrorMessage("Exceeded login attempts. Returning to main menu.");
                    goBackToMainView();
                } else {
                    adminView.displayErrorMessage("Invalid username or password. Attempts left: " + (3 - loginAttempts));
                }
            }
        }

        private boolean isValidAdminCredentials(String username, String password) {
            return "pdc".equals(username) && "pdc".equals(password); // Replace with your actual validation logic.
        }
    }

    /**
     * ActionListener for "Go Back" buttons in the AdminView.
     */
    class GoBackListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            goBackToMainView();
        }
    }

    /**
     * Apply visibility settings from AdminView to MainView.
     */
    private void applyComponentVisibility() {
        // Set visibility for various components in MainView based on AdminView settings.
        mainView.setGPUVisibility(adminView.isGPUVisible());
        mainView.setCPUVisibility(adminView.isCPUVisible());
        mainView.setMemoryVisibility(adminView.isMemoryVisible());
        mainView.setPowerSupplyVisibility(adminView.isPowerSupplyVisible());
        mainView.setCoolingVisibility(adminView.isCoolingVisible());
        mainView.setStorageVisibility(adminView.isStorageVisible());
        mainView.setTPUVisibility(adminView.isTPUVisible());
        mainView.setMotherboardVisibility(adminView.isMotherboardVisible());
    }

    /**
     * Go back to the MainView and dispose of the AdminView frame.
     */
    private void goBackToMainView() {
        adminView.frame.dispose();  // Call dispose on the JFrame object
        mainView.setVisible(true);
    }

    // Methods for testing purposes (JUnit).
    public boolean isAdminLoggedIn() {
        return isAdminLoggedIn;
    }

    public int getLoginAttempts() {
        return loginAttempts;
    }
}
