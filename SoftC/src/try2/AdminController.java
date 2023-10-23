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

public class AdminController implements DatabaseOperations {

    private AdminView adminView;
    private MainView mainView;
    private boolean isAdminLoggedIn = false; // to check if admin is already logged in
    private int loginAttempts = 0; // count login attempts
    private DatabaseManager dbManager;

    public AdminController(AdminView adminView, MainView mainView) {
        this.adminView = adminView;
        this.mainView = mainView;

        // Add action listeners to the buttons in the AdminView
        this.adminView.addLoginButtonListener(new LoginListener());
        this.adminView.addGoBackButtonListener(new GoBackListener());
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
                isAdminLoggedIn = true;  // Set the login flag to true
                adminView.displaySuccessMessage("Logged in successfully!");
                // You can add code here to open the admin dashboard if needed
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
            // For this example, let's say the admin username is "admin" and password is "password123"
            // In a real-world scenario, you'd probably fetch these values from a secure database.
            return "admin".equals(username) && "password123".equals(password);
        }
    }

    class GoBackListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            goBackToMainView();
        }
    }

    @Override
    public void addToDatabase(Component component) {
        String query = "INSERT INTO components (id, name) VALUES ('"
                + component.getId() + "', '"
                + component.getName() + "')";
        int rowsAffected = dbManager.executeUpdate(query);
        if (rowsAffected > 0) {
            adminView.displaySuccessMessage("Component added successfully!");
        } else {
            adminView.displayErrorMessage("Error adding component.");
        }
    }

    @Override
    public void editInDatabase(String componentId, Component updatedComponent) {
        String query = "UPDATE components SET name = '"
                + updatedComponent.getName() + "' WHERE id = '"
                + componentId + "'";
        int rowsAffected = dbManager.executeUpdate(query);
        if (rowsAffected > 0) {
            adminView.displaySuccessMessage("Component updated successfully!");
        } else {
            adminView.displayErrorMessage("Error updating component.");
        }
    }

    @Override
    public void deleteFromDatabase(String componentId) {
        String query = "DELETE FROM components WHERE id = '" + componentId + "'";
        int rowsAffected = dbManager.executeUpdate(query);
        if (rowsAffected > 0) {
            adminView.displaySuccessMessage("Component deleted successfully!");
        } else {
            adminView.displayErrorMessage("Error deleting component.");
        }
    }

    private void goBackToMainView() {
        adminView.dispose();  // Close the AdminView window
        mainView.setVisible(true);  // Show the main view
    }
}
