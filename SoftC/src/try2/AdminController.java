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

public class AdminController {

    private AdminView adminView;
    private MainView mainView;
    private boolean isAdminLoggedIn = false;
    private int loginAttempts = 0;

    public AdminController(AdminView adminView, MainView mainView) {
        this.adminView = adminView;
        this.mainView = mainView;

        this.adminView.addLoginButtonListener(new LoginListener());
        this.adminView.addGoBackButtonListener(new GoBackListener());
        this.adminView.addDashboardGoBackButtonListener(new GoBackListener());
    }

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
            return "pdc".equals(username) && "pdc".equals(password);
        }
    }

    class GoBackListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            goBackToMainView();
        }
    }

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

    private void goBackToMainView() {
        adminView.dispose();
        mainView.setVisible(true);
    }
}
