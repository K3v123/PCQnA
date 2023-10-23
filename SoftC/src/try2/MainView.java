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
import java.awt.event.ActionListener;

public class MainView {

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
        adminButton = createButton("Admin Options");
        mostAskedButton = createButton("Most Asked Components");

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

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        return button;
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


    public void displayErrorMessage(String errorMessage) {
        JOptionPane.showMessageDialog(frame, errorMessage);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainView());  // Proper way to launch a Swing GUI
    }
}
