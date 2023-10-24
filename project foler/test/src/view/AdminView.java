/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileWriter;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author kq635
 */
public class AdminView extends BaseView {

    // Declare GUI components and references to other views.
    private JButton applyChangesButton;
    private JButton loginButton, dashboardGoBackButton;
    private JPanel adminDashboardPanel;
    private JCheckBox gpuCheckBox, cpuCheckBox, memoryCheckBox, powerSupplyCheckBox,
            coolingCheckBox, storageCheckBox, tpuCheckBox, motherboardCheckBox;

    private MainView mainView;
    private JTextField usernameField, passwordField;

    // Define a file path for saving/loading component visibility settings.
    private final String VISIBILITY_FILE_PATH = "data/componentVisibility.txt";

    /**
     * Constructor for the `AdminView` class.
     *
     * @param mainView The MainView instance to work with.
     */
    public AdminView(MainView mainView) {
        super();  // Initialize the base view
        this.mainView = mainView;  // Set the MainView instance
        initializeFrame("Admin Login");  // Initialize the frame

        // Create the login panel
        JLabel usernameLabel = new JLabel("Username: ");
        JLabel passwordLabel = new JLabel("Password: ");

        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);
        loginButton = new JButton("Login");

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

        gpuCheckBox = new JCheckBox("GPU");
        cpuCheckBox = new JCheckBox("CPU");
        memoryCheckBox = new JCheckBox("Memory");
        powerSupplyCheckBox = new JCheckBox("PowerSupply");
        coolingCheckBox = new JCheckBox("Cooling");
        storageCheckBox = new JCheckBox("Storage");
        tpuCheckBox = new JCheckBox("TPU");
        motherboardCheckBox = new JCheckBox("Motherboard");

        dashboardGoBackButton = new JButton("Go Back");
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
        frame.add(panel);
        frame.setVisible(true);
        frame.add(panel);
        frame.setVisible(true);
        frame.setPreferredSize(new Dimension(250, 400));  // Setting a preferred size for the frame

        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                frame.dispose();  // Close the AdminView
                mainView.setVisible(true);  // Show the MainView
            }
        });

        loadVisibilityState();

        ActionListener saveVisibilityListener = e -> saveVisibilityState();
        gpuCheckBox.addActionListener(saveVisibilityListener);
        cpuCheckBox.addActionListener(saveVisibilityListener);
        memoryCheckBox.addActionListener(saveVisibilityListener);
        powerSupplyCheckBox.addActionListener(saveVisibilityListener);
        coolingCheckBox.addActionListener(saveVisibilityListener);
        storageCheckBox.addActionListener(saveVisibilityListener);
        tpuCheckBox.addActionListener(saveVisibilityListener);
        motherboardCheckBox.addActionListener(saveVisibilityListener);

        applyChangesButton = new JButton("Apply Changes");
        applyChangesButton.addActionListener(e -> {
            saveVisibilityState();
            mainView.applyVisibilityFromAdminView(this);
        });
        adminDashboardPanel.add(applyChangesButton);
    }

    // Get the username entered in the UI.
    public String getUsername() {
        return usernameField.getText();
    }

    // Get the password entered in the UI.
    public String getPassword() {
        return passwordField.getText();
    }

    public JCheckBox getGpuCheckBox() {
        return gpuCheckBox;
    }

    public JCheckBox getCpuCheckBox() {
        return cpuCheckBox;
    }

    public JCheckBox getMemoryCheckBox() {
        return memoryCheckBox;
    }

    public JCheckBox getPowerSupplyCheckBox() {
        return powerSupplyCheckBox;
    }

    public JCheckBox getCoolingCheckBox() {
        return coolingCheckBox;
    }

    public JCheckBox getStorageCheckBox() {
        return storageCheckBox;
    }

    public JCheckBox getTpuCheckBox() {
        return tpuCheckBox;
    }

    // setters
    public JCheckBox getMotherboardCheckBox() {
        return motherboardCheckBox;
    }

    public void setGpuCheckBox(JCheckBox gpuCheckBox) {
        this.gpuCheckBox = gpuCheckBox;
    }

    public void setCpuCheckBox(JCheckBox cpuCheckBox) {
        this.cpuCheckBox = cpuCheckBox;
    }

    public void setMemoryCheckBox(JCheckBox memoryCheckBox) {
        this.memoryCheckBox = memoryCheckBox;
    }

    public void setPowerSupplyCheckBox(JCheckBox powerSupplyCheckBox) {
        this.powerSupplyCheckBox = powerSupplyCheckBox;
    }

    public void setCoolingCheckBox(JCheckBox coolingCheckBox) {
        this.coolingCheckBox = coolingCheckBox;
    }

    public void setStorageCheckBox(JCheckBox storageCheckBox) {
        this.storageCheckBox = storageCheckBox;
    }

    public void setTpuCheckBox(JCheckBox tpuCheckBox) {
        this.tpuCheckBox = tpuCheckBox;
    }

    public void setMotherboardCheckBox(JCheckBox motherboardCheckBox) {
        this.motherboardCheckBox = motherboardCheckBox;
    }

    // Check if GPU component is visible in the UI.
    public boolean isGPUVisible() {
        return gpuCheckBox.isSelected();
    }

    // Check if CPU component is visible in the UI.
    public boolean isCPUVisible() {
        return cpuCheckBox.isSelected();
    }

    // Check if Memory component is visible in the UI.
    public boolean isMemoryVisible() {
        return memoryCheckBox.isSelected();
    }

    // Check if PowerSupply component is visible in the UI.
    public boolean isPowerSupplyVisible() {
        return powerSupplyCheckBox.isSelected();
    }

    // Check if Cooling component is visible in the UI.
    public boolean isCoolingVisible() {
        return coolingCheckBox.isSelected();
    }

    // Check if Storage component is visible in the UI.
    public boolean isStorageVisible() {
        return storageCheckBox.isSelected();
    }

    // Check if TPU component is visible in the UI.
    public boolean isTPUVisible() {
        return tpuCheckBox.isSelected();
    }

    // Check if Motherboard component is visible in the UI.
    public boolean isMotherboardVisible() {
        return motherboardCheckBox.isSelected();
    }

    // Switch to the admin dashboard view.
    public void switchToDashboard() {
        panel.setVisible(false);
        frame.add(adminDashboardPanel);
        adminDashboardPanel.setVisible(true);
        frame.pack();
    }

    // Display a success message dialog.
    public void displaySuccessMessage(String successMessage) {
        JOptionPane.showMessageDialog(frame, successMessage, "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    // Display an error message dialog.
    public void displayErrorMessage(String errorMessage) {
        JOptionPane.showMessageDialog(frame, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
    }

    // Add an action listener for the login button.
    public void addLoginButtonListener(ActionListener listenForLoginButton) {
        loginButton.addActionListener(listenForLoginButton);
    }

    // Add an action listener for the "Go Back" button on the dashboard.
    public void addDashboardGoBackButtonListener(ActionListener listenForGoBackButton) {
        dashboardGoBackButton.addActionListener(listenForGoBackButton);
    }

    /**
     * Set the password field with a specified value.
     *
     * @param password The password to set.
     */
    public void setPassword(String password) {
        passwordField.setText(password);
    }

    // Save the component visibility state to a file.
    public void saveVisibilityState() {
        try (FileWriter writer = new FileWriter(VISIBILITY_FILE_PATH)) {
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

    // Load the component visibility state from a file.
    public void loadVisibilityState() {
        File file = new File(VISIBILITY_FILE_PATH);
        if (!file.exists()) {
            return;  // If file doesn't exist, skip loading.
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length != 2) {
                    continue;  // Skip if line format is invalid.
                }

                String componentName = parts[0].trim();
                boolean isVisible = Boolean.parseBoolean(parts[1].trim());

                switch (componentName) {
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
                    default:
                        // Handle unknown components if necessary.
                        break;
                }
            }
        } catch (IOException e) {
            displayErrorMessage("Error loading visibility state.");
        }
    }

    // for juint test
    public JTextField getUsernameField() {
        return usernameField;
    }

//    public JPasswordField getPasswordField() {
//        return passwordField;
//    }
}
