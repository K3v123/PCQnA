/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;
/**
 *
 * @author kq635
 */
import Model.PowerSupplyModel;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.util.List;

public class PowerSupplyView extends BaseView {

    private JLabel powerClassHeader;
    private JButton fetchDetailsButton, recommendationButton;
    private JFrame recommendationFrame;
    private JTextArea recommendationText, infoText;
    private JTable powerSupplyTable;
    private boolean fetched = false;
    private MainView mainView;  // Assuming your main view is named "MainView"

    // Constructor that takes a reference to the main view
    public PowerSupplyView(MainView mainView) {
        super();

        this.mainView = mainView;  // Initialize the main view reference

        initializeFrame("Power Supply Details");
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        powerClassHeader = new JLabel("Power Class: ");
        infoText = new JTextArea("A power supply unit converts power from the wall outlet to the type that the computer components need.", 5, 30);
        infoText.setEditable(false);
        fetchDetailsButton = new JButton("Fetch Power Supply Details");
        recommendationButton = new JButton("Show Recommendation");

        // Add an ActionListener to the recommendationButton
        recommendationButton.addActionListener(e -> showRecommendation());

        panel.add(Box.createVerticalStrut(40));  // Adding vertical space
        panel.add(infoText);
        panel.add(powerClassHeader);
        panel.add(fetchDetailsButton);
        panel.add(recommendationButton);
        goBackButton.setText("<");
        goBackButton.setBounds(10, 10, 45, 30);
        frame.add(goBackButton);

        frame.add(panel);
        frame.setVisible(true);

        // Modify the goBackButton action to show the main view
        goBackButton.addActionListener(e -> {
            frame.setVisible(false); // Hide the current frame
            mainView.setVisible(true);  // Show the main view
        });

        // Add a WindowListener to handle window closing
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                frame.setVisible(false);
                mainView.setVisible(true);  // Show the main view
            }
        });
    }

    // Method to add a listener to the fetchDetailsButton
    public void addFetchDetailsButtonListener(ActionListener listener) {
        fetchDetailsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!fetched) { // Check if the button hasn't been pressed before
                    listener.actionPerformed(e); // Execute the listener
                    fetched = true; // Update the fetched state
                    fetchDetailsButton.setEnabled(false); // Disable the button
                }
            }
        });
    }

    // Method to set power supply details
    public void setPowerSupplyDetails(String powerRating) {
        powerClassHeader.setText("Power Rating: " + powerRating);
    }

    // Method to show a recommendation dialog
    private void showRecommendation() {
        recommendationFrame = new JFrame("Power Supply Recommendation");
        recommendationFrame.setSize(300, 200);
        recommendationFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        recommendationFrame.addWindowListener(new java.awt.event.WindowAdapter() {

            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                recommendationFrame.dispose();
                frame.setVisible(true);
            }
        });

        JPanel recommendationPanel = new JPanel();
        recommendationPanel.setLayout(new BoxLayout(recommendationPanel, BoxLayout.Y_AXIS));

        recommendationText = new JTextArea("We recommend at least 500W for a basic setup and higher for gaming or workstation setups.", 5, 30);
        recommendationText.setEditable(false);
        recommendationText.setWrapStyleWord(true);
        recommendationText.setLineWrap(true);

        JButton goBackFromRecommendation = new JButton("Go Back");
        goBackFromRecommendation.addActionListener(e -> {
            recommendationFrame.dispose();
            frame.setVisible(true);
        });

        recommendationPanel.add(recommendationText);
        recommendationPanel.add(goBackFromRecommendation);
        recommendationFrame.add(recommendationPanel);
        recommendationFrame.setVisible(true);
        frame.setVisible(false);
    }

    // Method to set up a table with power supply data
    public void setupTable(List<PowerSupplyModel> powerSupplyList) {
        String[] columnNames = {"ID", "Class"};
        Object[][] data = new Object[powerSupplyList.size()][2];
        for (int i = 0; i < powerSupplyList.size(); i++) {
            data[i][0] = powerSupplyList.get(i).getId();
            data[i][1] = powerSupplyList.get(i).getPowerClass();
        }
        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        powerSupplyTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(powerSupplyTable);
        panel.add(scrollPane);
        panel.revalidate();
        panel.repaint();
    }

    // Method to close the window
    public void closeWindow() {
        frame.dispose();
    }
}
