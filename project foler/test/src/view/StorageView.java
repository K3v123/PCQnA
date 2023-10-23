/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

/**
 *
 * @author kq635
 */
import Model.StorageModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class StorageView extends BaseView {

    private JTextArea infoText;
    private JLabel StorageSizeHeader;
    private JButton fetchDetailsButton, recommendationButton;
    private JFrame recommendationFrame;
    private JTextArea recommendationText;
    private JTable storageTable;
    private boolean fetched = false;

    public StorageView() {
        super();
        initializeFrame("Storage Details");
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Brief Information
        StorageSizeHeader = new JLabel("Storage sizes:");
        infoText = new JTextArea("Storage devices are used to store and retrieve data. They come in different forms like HDD, SSD, etc.", 5, 30);
        infoText.setEditable(false);
        infoText.setWrapStyleWord(true);
        infoText.setLineWrap(true);

        fetchDetailsButton = new JButton("Fetch Storage Details");
        recommendationButton = new JButton("Show Recommendation");

        // ActionListener for the recommendation button
        recommendationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showRecommendation();
            }
        });

        panel.add(Box.createVerticalStrut(40));  // Adding vertical space
        panel.add(infoText);
        panel.add(StorageSizeHeader);
        panel.add(Box.createVerticalGlue());
        panel.add(fetchDetailsButton);
        panel.add(recommendationButton);
        goBackButton.setText("<");
        goBackButton.setBounds(10, 10, 45, 30);
        frame.add(goBackButton);

        frame.add(panel);
        frame.setVisible(true);

        // Window closing listener
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                frame.setVisible(false);
                goBackButton.doClick(); // Simulate a click on the go back button when closing the window
            }
        });
    }

    // ActionListener for the fetch details button
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

    // Show storage recommendation in a separate frame
    private void showRecommendation() {
        recommendationFrame = new JFrame("Storage Recommendation");
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

        recommendationText = new JTextArea("Recommended storage for new users is SSD due to its speed. HDD is cheaper but slower.", 5, 30);
        recommendationText.setEditable(false);
        recommendationText.setWrapStyleWord(true);
        recommendationText.setLineWrap(true);

        JButton goBackFromRecommendation = new JButton("Go Back");
        goBackFromRecommendation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                recommendationFrame.dispose();
                frame.setVisible(true);
            }
        });

        recommendationPanel.add(recommendationText);
        recommendationPanel.add(goBackFromRecommendation);
        recommendationFrame.add(recommendationPanel);
        recommendationFrame.setVisible(true);
        frame.setVisible(false);
    }

    // Populate the storage information table
    public void setupTable(List<StorageModel> storageList) {
        String[] columnNames = {"ID", "Type", "Size", "Speed"};
        Object[][] data = new Object[storageList.size()][4];
        for (int i = 0; i < storageList.size(); i++) {
            data[i][0] = storageList.get(i).getId();
            data[i][1] = storageList.get(i).getType();
            data[i][2] = storageList.get(i).getSize();
            data[i][3] = storageList.get(i).getSpeed();
        }
        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        storageTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(storageTable);
        panel.add(scrollPane);
        panel.revalidate();
        panel.repaint();
    }
}
