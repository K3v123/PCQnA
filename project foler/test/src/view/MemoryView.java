/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;
import Model.MemoryModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class MemoryView extends BaseView {

    private JTable memoryTable = new JTable(); // Create a JTable to display memory data

    private JLabel MemSizeHeader;
    private JButton fetchDetailsButton, recommendationButton;
    private JFrame recommendationFrame;
    private JTextArea recommendationText, infoText;
    private boolean fetched = false;

    // Constructor for MemoryView
    public MemoryView() {
        super(); // Call the constructor of the base class (BaseView)
        initializeFrame("Memory Details"); // Initialize the frame with a title
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        MemSizeHeader = new JLabel("Size: ");
        infoText = new JTextArea("Memory (RAM) is the primary storage used for a computer's immediate tasks.", 5, 30);
        infoText.setEditable(false);
        fetchDetailsButton = new JButton("Fetch Memory Details");
        recommendationButton = new JButton("Show Recommendation");

        recommendationButton.addActionListener(e -> showRecommendation()); // Add an action listener to the recommendation button

        panel.add(Box.createVerticalStrut(40));  // Adding vertical space
        panel.add(infoText);
        panel.add(MemSizeHeader);
        panel.add(fetchDetailsButton);
        panel.add(recommendationButton);
        goBackButton.setText("<");
        goBackButton.setBounds(10, 10, 45, 30);
        frame.add(goBackButton);

        frame.add(panel);
        frame.setVisible(true);

        // Add a window listener to handle window closing
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                frame.setVisible(false);
                goBackButton.doClick(); // Simulate a click on the go back button when closing the window
            }
        });

    }

    // Add an action listener for the "Fetch Memory Details" button
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

    // Set the memory details in the UI
    public void setMemoryDetails(String size) {
        MemSizeHeader.setText("Size: " + size);
    }

    // Show the memory recommendation
    private void showRecommendation() {
        if (recommendationFrame == null) { // Check if recommendationFrame is already created
            recommendationFrame = new JFrame("Memory Recommendation");
            recommendationFrame.setSize(300, 200);
            recommendationFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

            // Add a window listener to handle window closing
            recommendationFrame.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent e) {
                    recommendationFrame.dispose(); // Dispose the recommendationFrame
                    frame.setVisible(true);
                }
            });

            JPanel recommendationPanel = new JPanel();
            recommendationPanel.setLayout(new BoxLayout(recommendationPanel, BoxLayout.Y_AXIS));

            recommendationText = new JTextArea("8GB is the minimum for most tasks, 16GB is recommended for gaming & professional work.", 5, 30);
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
        }

        recommendationFrame.setVisible(true); // Show the recommendationFrame
        frame.setVisible(false); // Hide the main frame
    }

    // Set up the JTable to display memory details
    public void setupTable(List<MemoryModel> memoryList) {
        String[] columnNames = {"ID", "Size"};
        Object[][] data = new Object[memoryList.size()][2];
        for (int i = 0; i < memoryList.size(); i++) {
            data[i][0] = memoryList.get(i).getId();
            data[i][1] = memoryList.get(i).getSize();
        }
        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        memoryTable.setModel(tableModel);
        JScrollPane scrollPane = new JScrollPane(memoryTable);
        panel.add(scrollPane);
        panel.revalidate();
        panel.repaint();
    }
}
