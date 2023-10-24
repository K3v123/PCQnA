/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

/**
 *
 * @author kq635
 */
import Base.DatabaseManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

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

    /**
     * Get the frame associated with the MainView.
     *
     * @return The frame.
     */
    public JFrame getFrame() {
        return frame;
    }

    /**
     * Constructor for the MainView.
     */
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

        // Adding action listeners for buttons
        // Each button increments the count for a specific option
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

    // create+ add button
    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        return button;
    }

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

    public void setVisible(boolean visibility) {
        frame.setVisible(visibility);
    }

    /**
     * Set the visibility of the GPU button.
     *
     * @param isVisible True if the button should be visible, false otherwise.
     */
    public void setGPUVisibility(boolean isVisible) {
        gpuButton.setVisible(isVisible);
    }

    // same set visbility for others
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

    public void displayErrorMessage(String errorMessage) {
        JOptionPane.showMessageDialog(frame, errorMessage);
    }

    /**
     * Main method to start the MainView.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainView());
    }

    // top selection listener
    public void addTopSetupsButtonListener(ActionListener listenForTopSetupsButton) {
        topSetupsButton.addActionListener(listenForTopSetupsButton);
    }

    // for juint
    protected JButton getGpuButton() {
        return gpuButton;
    }

    protected JButton getCpuButton() {
        return cpuButton;
    }

}
