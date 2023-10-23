/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package try2;

/**
 *
 * @author kq635
 */

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 *
 * @author kq635
 */
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
public class AdminView extends BaseView {

    // Fields for UI components
    private JTextField usernameField, passwordField;
    private JButton applyChangesButton;
    private JButton loginButton, dashboardGoBackButton;
    private JPanel adminDashboardPanel;
    private JCheckBox gpuCheckBox, cpuCheckBox, memoryCheckBox, powerSupplyCheckBox,
            coolingCheckBox, storageCheckBox, tpuCheckBox, motherboardCheckBox;
    private MainView mainView;

    // File path for saving and loading visibility settings
    private final String VISIBILITY_FILE_PATH = "data/componentVisibility.txt";

    public AdminView(MainView mainView) {
        super();
        this.mainView = mainView;
        initializeFrame("Admin Login");

        // Create the login panel
        JLabel usernameLabel = new JLabel("Username: ");
        JLabel passwordLabel = new JLabel("Password: ");

        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);
        loginButton = new JButton("Login");

        // Add UI components to the login panel
        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(loginButton);
        panel.add(goBackButton);

        // Create the admin dashboard panel
        adminDashboardPanel = new JPanel();
        adminDashboardPanel.setLayout(new BoxLayout(adminDashboardPanel, BoxLayout.Y_AXIS));
        JLabel visibilityLabel = new JLabel("Component Visibility: ");

        // Create checkboxes for each component
        gpuCheckBox = new JCheckBox("GPU");
        cpuCheckBox = new JCheckBox("CPU");
        memoryCheckBox = new JCheckBox("Memory");
        powerSupplyCheckBox = new JCheckBox("PowerSupply");
        coolingCheckBox = new JCheckBox("Cooling");
        storageCheckBox = new JCheckBox("Storage");
        tpuCheckBox = new JCheckBox("TPU");
        motherboardCheckBox = new JCheckBox("Motherboard");

        dashboardGoBackButton = new JButton("Go Back");

        // Add UI components to the admin dashboard panel
        adminDashboardPanel.add(visibilityLabel);
        adminDashboardPanel.add(gpuCheckBox);
        adminDashboardPanel.add(cpuCheckBox);
        adminDashboardPanel.add(memoryCheckBox);
        adminDashboardPanel.add(powerSupplyCheckBox);
        adminDashboardPanel.add(coolingCheckBox);
        adminDashboardPanel.add(storageCheckBox);
        adminDashboardPanel.add(tpuCheckBox);
        adminDashboardPanel.add(motherboardCheckBox);
        adminDashboardPanel.add(dashboardGoBackButton);

        // Add panels to the frame and set its properties
        frame.add(panel);
        frame.setVisible(true);
        frame.add(panel);
        frame.setVisible(true);

        // Set a preferred size for the frame
        frame.setPreferredSize(new Dimension(250, 400));

        // Handle window closing event
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                frame.dispose();  // Close the AdminView
                mainView.setVisible(true);  // Show the MainView
            }
        });

        // Load visibility settings from a file
        loadVisibilityState();

        // Action listeners for saving visibility settings and applying changes
        ActionListener saveVisibilityListener = e -> saveVisibilityState();
        gpuCheckBox.addActionListener(saveVisibilityListener);
        cpuCheckBox.addActionListener(saveVisibilityListener);
        memoryCheckBox.addActionListener(saveVisibilityListener);
        powerSupplyCheckBox.addActionListener(saveVisibilityListener);
        coolingCheckBox.addActionListener(saveVisibilityListener);
        storageCheckBox.addActionListener(saveVisibilityListener);
        tpuCheckBox.addActionListener(saveVisibilityListener);
        motherboardCheckBox.addActionListener(saveVisibilityListener);

        // For apply changes button
        applyChangesButton = new JButton("Apply Changes");
        applyChangesButton.addActionListener(e -> {
            saveVisibilityState(); // to check the state of visibility (if visible or not)
            mainView.applyVisibilityFromAdminView(this);
        });
        adminDashboardPanel.add(applyChangesButton); //adds it to the main dashboard panel
    }

    // Apply the visibility state to the MainView based on the checkbox settings
    private void applyVisibilityStateToMainView() { 
        if (mainView != null) {
            mainView.setGPUVisibility(isGPUVisible());
            mainView.setCPUVisibility(isCPUVisible());
            mainView.setMemoryVisibility(isMemoryVisible());
            mainView.setPowerSupplyVisibility(isPowerSupplyVisible());
            mainView.setCoolingVisibility(isCoolingVisible());
            mainView.setStorageVisibility(isStorageVisible());
            mainView.setTPUVisibility(isTPUVisible());
            mainView.setMotherboardVisibility(isMotherboardVisible());
        }
    }

    // Get the entered username
    public String getUsername() {
        return usernameField.getText();
    }

    // Get the entered password
    public String getPassword() {
        return passwordField.getText();
    }

    // Check if GPU component is visible
    public boolean isGPUVisible() {
        return gpuCheckBox.isSelected();
    }

    // Check if CPU component is visible
    public boolean isCPUVisible() {
        return cpuCheckBox.isSelected();
    }

    // Check if memory component is visible
    public boolean isMemoryVisible() {
        return memoryCheckBox.isSelected();
    }

     // Check if power supply component is visible
    public boolean isPowerSupplyVisible() {
        return powerSupplyCheckBox.isSelected();
    }

     // Check if cooling component is visible
    public boolean isCoolingVisible() {
        return coolingCheckBox.isSelected();
    }

     // Check if storage component is visible
    public boolean isStorageVisible() {
        return storageCheckBox.isSelected();
    }

     // Check if TPU component is visible
    public boolean isTPUVisible() {
        return tpuCheckBox.isSelected();
    }

     // Check if Motherboard component is visible
    public boolean isMotherboardVisible() {
        return motherboardCheckBox.isSelected();
    }

    
    // Switch the view to the admin dashboard by hiding the login panel and displaying the admin dashboard panel
    public void switchToDashboard() {
        panel.setVisible(false); // Hide the login panel
        frame.add(adminDashboardPanel); // Add the admin dashboard panel to the frame
        adminDashboardPanel.setVisible(true); // Display the admin dashboard panel
        frame.pack(); // Adjust the frame size to fit the new panel
    }

    // Display a success message in a dialog
    public void displaySuccessMessage(String successMessage) {
        JOptionPane.showMessageDialog(frame, successMessage, "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    // Display an error message in a dialog
    @Override
    public void displayErrorMessage(String errorMessage) {
        JOptionPane.showMessageDialog(frame, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
    }

    // Add an ActionListener to the login button
    public void addLoginButtonListener(ActionListener listenForLoginButton) {
        loginButton.addActionListener(listenForLoginButton);
    }

    // Add an ActionListener to the dashboard go back button
    public void addDashboardGoBackButtonListener(ActionListener listenForGoBackButton) {
        dashboardGoBackButton.addActionListener(listenForGoBackButton);
    }

    // Save the visibility state of components to a file
    public void saveVisibilityState() {
        try (FileWriter writer = new FileWriter(VISIBILITY_FILE_PATH)) {
            // Write the visibility state of each component to the file
            writer.write("GPU:" + gpuCheckBox.isSelected() + "\n");
            writer.write("CPU:" + cpuCheckBox.isSelected() + "\n");
            writer.write("Memory:" + memoryCheckBox.isSelected() + "\n");
            writer.write("PowerSupply:" + powerSupplyCheckBox.isSelected() + "\n");
            writer.write("Cooling:" + coolingCheckBox.isSelected() + "\n");
            writer.write("Storage:" + storageCheckBox.isSelected() + "\n");
            writer.write("TPU:" + tpuCheckBox.isSelected() + "\n");
            writer.write("Motherboard:" + motherboardCheckBox.isSelected() + "\n");
        } catch (IOException e) {
            displayErrorMessage("Error saving visibility state.");
        }
    }

    // Load the visibility state of components from a file
    public void loadVisibilityState() {
        File file = new File(VISIBILITY_FILE_PATH);
        if (!file.exists()) {
            return;  // If the file doesn't exist, skip loading.
        }
        // Read all lines from the visibility state file and process them
        try {
            List<String> lines = Files.readAllLines(Paths.get(VISIBILITY_FILE_PATH));
            // Iterate through each line in the file
            for (String line : lines) {
                String[] parts = line.split(":");
                if (parts.length != 2) {
                    continue;
                }

                String component = parts[0].trim();
                boolean isVisible = Boolean.parseBoolean(parts[1].trim());

                // Set the visibility of components based on the loaded data
                switch (component) {
                    case "GPU":
                        gpuCheckBox.setSelected(isVisible);
                        break;
                    case "CPU":
                        cpuCheckBox.setSelected(isVisible);
                        break;
                    case "Memory":
                        memoryCheckBox.setSelected(isVisible);
                        break;
                    case "PowerSupply":
                        powerSupplyCheckBox.setSelected(isVisible);
                        break;
                    case "Cooling":
                        coolingCheckBox.setSelected(isVisible);
                        break;
                    case "Storage":
                        storageCheckBox.setSelected(isVisible);
                        break;
                    case "TPU":
                        tpuCheckBox.setSelected(isVisible);
                        break;
                    case "Motherboard":
                        motherboardCheckBox.setSelected(isVisible);
                        break;
                }
            }
        } 
        // catch exception 
        catch (IOException e) {
            displayErrorMessage("Error loading visibility state.");
        }
    }
}

