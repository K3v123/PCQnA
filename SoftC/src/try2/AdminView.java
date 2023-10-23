/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package try2;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

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
import javax.swing.JOptionPane;

public class AdminView extends BaseView {

    private JTextField usernameField, passwordField;
    private JButton applyChangesButton;
    private JButton loginButton, dashboardGoBackButton;
    private JPanel adminDashboardPanel;
    private JCheckBox gpuCheckBox, cpuCheckBox, memoryCheckBox, powerSupplyCheckBox,
            coolingCheckBox, storageCheckBox, tpuCheckBox, motherboardCheckBox;
    private MainView mainView;

    private final String VISIBILITY_FILE_PATH = "data/componentVisibility.txt";

    public AdminView(MainView mainView) {  // Added MainView parameter
        super();
        this.mainView = mainView;  // Set the MainView instance
        initializeFrame("Admin Login");

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
        frame.setPreferredSize(new Dimension(250, 400));  // Setting a preferred size for the frame

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

    public String getUsername() {
        return usernameField.getText();
    }

    public String getPassword() {
        return passwordField.getText();
    }

    public boolean isGPUVisible() {
        return gpuCheckBox.isSelected();
    }

    public boolean isCPUVisible() {
        return cpuCheckBox.isSelected();
    }

    public boolean isMemoryVisible() {
        return memoryCheckBox.isSelected();
    }

    public boolean isPowerSupplyVisible() {
        return powerSupplyCheckBox.isSelected();
    }

    public boolean isCoolingVisible() {
        return coolingCheckBox.isSelected();
    }

    public boolean isStorageVisible() {
        return storageCheckBox.isSelected();
    }

    public boolean isTPUVisible() {
        return tpuCheckBox.isSelected();
    }

    public boolean isMotherboardVisible() {
        return motherboardCheckBox.isSelected();
    }

    public void switchToDashboard() {
        panel.setVisible(false);
        frame.add(adminDashboardPanel);
        adminDashboardPanel.setVisible(true);
        frame.pack();
    }

    public void displaySuccessMessage(String successMessage) {
        JOptionPane.showMessageDialog(frame, successMessage, "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    public void displayErrorMessage(String errorMessage) {
        JOptionPane.showMessageDialog(frame, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void addLoginButtonListener(ActionListener listenForLoginButton) {
        loginButton.addActionListener(listenForLoginButton);
    }

    public void addDashboardGoBackButtonListener(ActionListener listenForGoBackButton) {
        dashboardGoBackButton.addActionListener(listenForGoBackButton);
    }

    // ... [Other methods from your provided code] ...
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

    public void loadVisibilityState() {
        File file = new File(VISIBILITY_FILE_PATH);
        if (!file.exists()) {
            return;  // If file doesn't exist, skip loading.
        }
        try {
            List<String> lines = Files.readAllLines(Paths.get(VISIBILITY_FILE_PATH));
            for (String line : lines) {
                String[] parts = line.split(":");
                if (parts.length != 2) {
                    continue;
                }

                String component = parts[0].trim();
                boolean isVisible = Boolean.parseBoolean(parts[1].trim());

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
        } catch (IOException e) {
            displayErrorMessage("Error loading visibility state.");
        }
    }
}
