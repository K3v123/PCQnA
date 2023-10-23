/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package try2;

/**
 *
 * @author kq635
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainView extends BaseView {

    private JFrame frame;
    private JPanel mainPanel;
    private JButton gpuButton;
    private JButton motherboardButton;
    private JButton tpuButton;
    private JButton storageButton;
    private JButton coolingButton;
    private JButton powerSupplyButton;
    private JButton memoryButton;
    private JButton cpuButton;
    private JButton adminButton;
    private JButton mostAskedButton;
    private JButton topSetupsButton;

    // Returns the frame associated with this MainView.
    public JFrame getFrame() {
        return frame;
    }

    // Initializes the MainView, setting up the main menu with various buttons.
    public MainView() {
        frame = new JFrame("PC Builder Guide");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 600);

        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("Welcome to PC Builder Guide");
        titleLabel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        mainPanel.add(titleLabel);

        gpuButton = createButton("GPU");
        motherboardButton = createButton("Motherboard");
        tpuButton = createButton("TPU");
        storageButton = createButton("Storage");
        coolingButton = createButton("Cooling");
        powerSupplyButton = createButton("Power Supply");
        memoryButton = createButton("Memory");
        cpuButton = createButton("CPU");
        adminButton = createButton("Admin");
        mostAskedButton = createButton("Most Asked Components");
        topSetupsButton = createButton("Top Setups");

        mainPanel.add(gpuButton);
        mainPanel.add(motherboardButton);
        mainPanel.add(tpuButton);
        mainPanel.add(storageButton);
        mainPanel.add(coolingButton);
        mainPanel.add(powerSupplyButton);
        mainPanel.add(memoryButton);
        mainPanel.add(cpuButton);
        mainPanel.add(adminButton);
        mainPanel.add(mostAskedButton);
        mainPanel.add(topSetupsButton);

        frame.add(mainPanel);

        // Adding the action listeners for every button
        cpuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DatabaseManager dbManager = new DatabaseManager();
                dbManager.incrementCountForOption("cpuButton");
            }
        });

        memoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DatabaseManager dbManager = new DatabaseManager();
                dbManager.incrementCountForOption("memoryButton");
            }
        });

        powerSupplyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DatabaseManager dbManager = new DatabaseManager();
                dbManager.incrementCountForOption("powerSupplyButton");
            }
        });

        coolingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DatabaseManager dbManager = new DatabaseManager();
                dbManager.incrementCountForOption("coolingButton");
            }
        });

        storageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DatabaseManager dbManager = new DatabaseManager();
                dbManager.incrementCountForOption("storageButton");
            }
        });

        tpuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DatabaseManager dbManager = new DatabaseManager();
                dbManager.incrementCountForOption("tpuButton");
            }
        });

        motherboardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DatabaseManager dbManager = new DatabaseManager();
                dbManager.incrementCountForOption("motherboardButton");
            }
        });

        gpuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DatabaseManager dbManager = new DatabaseManager();
                dbManager.incrementCountForOption("gpuButton");
            }
        });

        frame.setVisible(true);
    }

    // Creates a JButton with the given text and aligns it in the center.
    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        return button;
    }

    // Updates the visibility of components based on the state of the AdminView.
    public void applyVisibilityFromAdminView(AdminView adminView) {
        gpuButton.setVisible(adminView.isGPUVisible());
        cpuButton.setVisible(adminView.isCPUVisible());
        memoryButton.setVisible(adminView.isMemoryVisible());
        powerSupplyButton.setVisible(adminView.isPowerSupplyVisible());
        coolingButton.setVisible(adminView.isCoolingVisible());
        storageButton.setVisible(adminView.isStorageVisible());
        tpuButton.setVisible(adminView.isTPUVisible());
        motherboardButton.setVisible(adminView.isMotherboardVisible());
    }

    // Adds an action listener to all the button.
    public void addGPUButtonListener(ActionListener listenForGPUButton) {
        gpuButton.addActionListener(listenForGPUButton);
    }

    public void addMotherboardButtonListener(ActionListener listenForMotherboardButton) {
        motherboardButton.addActionListener(listenForMotherboardButton);
    }

    public void addTPUButtonListener(ActionListener listenForTPUButton) {
        tpuButton.addActionListener(listenForTPUButton);
    }

    public void addStorageButtonListener(ActionListener listenForStorageButton) {
        storageButton.addActionListener(listenForStorageButton);
    }

    public void addCoolingButtonListener(ActionListener listenForCoolingButton) {
        coolingButton.addActionListener(listenForCoolingButton);
    }

    public void addPowerSupplyButtonListener(ActionListener listenForPowerSupplyButton) {
        powerSupplyButton.addActionListener(listenForPowerSupplyButton);
    }

    public void addMemoryButtonListener(ActionListener listenForMemoryButton) {
        memoryButton.addActionListener(listenForMemoryButton);
    }

    public void addCPUButtonListener(ActionListener listenForCPUButton) {
        cpuButton.addActionListener(listenForCPUButton);
    }

    public void addAdminButtonListener(ActionListener listenForAdminButton) {
        adminButton.addActionListener(listenForAdminButton);
    }

    public void addMostAskedButtonListener(ActionListener listenForMostAskedButton) {
        mostAskedButton.addActionListener(listenForMostAskedButton);
    }

    // For setting the visibility
    
    public void setVisible(boolean visibility) {
        frame.setVisible(visibility);
    }
    
    public void setGPUVisibility(boolean isVisible) {
        gpuButton.setVisible(isVisible);
    }

    
    public void setCPUVisibility(boolean isVisible) {
        cpuButton.setVisible(isVisible);
    }

    public void setMemoryVisibility(boolean isVisible) {
        memoryButton.setVisible(isVisible);
    }

    public void setPowerSupplyVisibility(boolean isVisible) {
        powerSupplyButton.setVisible(isVisible);
    }

    public void setCoolingVisibility(boolean isVisible) {
        coolingButton.setVisible(isVisible);
    }

    public void setStorageVisibility(boolean isVisible) {
        storageButton.setVisible(isVisible);
    }

    public void setTPUVisibility(boolean isVisible) {
        tpuButton.setVisible(isVisible);
    }

    public void setMotherboardVisibility(boolean isVisible) {
        motherboardButton.setVisible(isVisible);
    }

    // Displays an error message dialog with the provided error message.
    public void displayErrorMessage(String errorMessage) {
        JOptionPane.showMessageDialog(frame, errorMessage);
    }

    // Main method to launch the MainView using SwingUtilities.invokeLater().
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainView());
    }

    // Adds an action listener to the "Top Setups" button.
    public void addTopSetupsButtonListener(ActionListener listenForTopSetupsButton) {
        topSetupsButton.addActionListener(listenForTopSetupsButton);
    }

}
