/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package try2;

/**
 *
 * @author kq635
 */

/*
 * This is a Java class for the AdminController, which controls interactions with the admin view and main view of an application.
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class AdminController {

    private AdminView adminView;   // Reference to the admin view
    private MainView mainView;     // Reference to the main view
    private boolean isAdminLoggedIn = false; // Flag to track admin login status
    private int loginAttempts = 0;  // Count login attempts

    public AdminController(AdminView adminView, MainView mainView) {
        this.adminView = adminView;
        this.mainView = mainView;

        // Add action listeners to various buttons
        this.adminView.addLoginButtonListener(new LoginListener());
        this.adminView.addGoBackButtonListener(new GoBackListener());
        this.adminView.addDashboardGoBackButtonListener(new GoBackListener());
    }

    // ActionListener for the login button
    class LoginListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (isAdminLoggedIn) {
                adminView.displaySuccessMessage("You're already logged in!");
                return;
            }

            // Get username and password from the admin view
            String username = adminView.getUsername();
            String password = adminView.getPassword();

            if (isValidAdminCredentials(username, password)) {
                isAdminLoggedIn = true;
                adminView.displaySuccessMessage("Logged in successfully!");
                adminView.switchToDashboard();

                // After successful login, apply visibility settings to the main view
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

        // Check if provided credentials are valid
        private boolean isValidAdminCredentials(String username, String password) {
            return "pdc".equals(username) && "pdc".equals(password);
        }
    }

    // ActionListener for "Go Back" button
    class GoBackListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            goBackToMainView();
        }
    }

    // Apply visibility settings from the admin view to the main view
    private void applyComponentVisibility() {
        mainView.setGPUVisibility(adminView.isGPUVisible());
        mainView.setCPUVisibility(adminView.isCPUVisible());
        mainView.setMemoryVisibility(adminView.isMemoryVisible());
        mainView.setPowerSupplyVisibility(adminView.isPowerSupplyVisible());
        mainView.setCoolingVisibility(adminView.isCoolingVisible());
        mainView.setStorageVisibility(adminView.isStorageVisible());
        mainView.setTPUVisibility(adminView.isTPUVisible());
        mainView.setMotherboardVisibility(adminView.isMotherboardVisible());
    }

    // Switch back to the main view
    private void goBackToMainView() {
        adminView.dispose();
        mainView.setVisible(true);
    }
}
