
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;
import Model.CPUModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class CPUView extends BaseView {

    private JLabel cpuStatHeader;
    private JLabel speedLabel, coresLabel;
    private JTextArea recommendationText, infoText;
    private JButton fetchDetailsButton, recommendationButton;
    private JDialog recommendationDialog; // Changed to JDialog for consistency
    private JTable cpuTable;
    private final MainView mainView;

    /**
     * Constructor to initialize the CPUView.
     *
     * @param mainView The MainView instance to control navigation.
     */
    public CPUView(MainView mainView) {
        this.mainView = mainView;
        initializeFrame("CPU Details");
        frame.setSize(600, 400);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        cpuStatHeader = new JLabel("CPU stat:");
        goBackButton.setText("<");
        goBackButton.setBounds(10, 10, 45, 30);
        frame.add(goBackButton);

        panel.add(Box.createVerticalStrut(50)); // Create space after the go back button

        // Description
        infoText = new JTextArea("The Central Processing Unit (CPU) is the primary component responsible for executing instructions of a computer program.", 5, 30);
        setupTextArea(infoText);
        panel.add(infoText);

        fetchDetailsButton = new JButton("Fetch CPU Details");
        recommendationButton = new JButton("Show Recommendation");
        recommendationButton.addActionListener(e -> showRecommendation());

        panel.add(cpuStatHeader);
        panel.add(fetchDetailsButton);
        panel.add(recommendationButton);

        frame.add(panel);
        frame.setVisible(true);
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                frame.setVisible(false);
                mainView.setVisible(true);
            }
        });
    }

    /**
     * Sets up the JTextArea component with word wrapping and other properties.
     *
     * @param textArea The JTextArea to configure.
     */
    private void setupTextArea(JTextArea textArea) {
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);
        textArea.setCaretPosition(0);
        textArea.setEditable(false);
        textArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }

    /**
     * Displays the CPU recommendation in a separate dialog.
     */
    private void showRecommendation() {
        frame.setVisible(false); // Hide the CPU frame when showing recommendation

        recommendationDialog = new JDialog(frame, "CPU Recommendation", true);
        recommendationDialog.setSize(400, 200);
        recommendationDialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        recommendationDialog.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                recommendationDialog.dispose();
                frame.setVisible(true); // Show the CPU frame when recommendation dialog is closed
            }
        });

        recommendationText = new JTextArea("For most users, a quad-core or hexa-core processor will suffice. For gaming or heavy multitasking, consider octa-core or higher.", 5, 30);
        setupTextArea(recommendationText);

        JButton goBackFromRecommendation = new JButton("Go Back");
        goBackFromRecommendation.addActionListener(e -> {
            recommendationDialog.dispose();
            frame.setVisible(true);
        });

        JPanel dialogPanel = new JPanel();
        dialogPanel.setLayout(new BoxLayout(dialogPanel, BoxLayout.Y_AXIS));
        dialogPanel.add(recommendationText);
        dialogPanel.add(goBackFromRecommendation);

        recommendationDialog.add(dialogPanel);
        recommendationDialog.setVisible(true);
    }

    /**
     * Adds an ActionListener to the "Fetch CPU Details" button.
     *
     * @param listener The ActionListener to add.
     */
    public void addFetchDetailsButtonListener(ActionListener listener) {
        fetchDetailsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listener.actionPerformed(e);
                fetchDetailsButton.setEnabled(false);  // Disable the button after it's clicked
            }
        });
    }

    /**
     * Sets CPU details to be displayed.
     *
     * @param speed The CPU speed to display.
     * @param cores The number of CPU cores to display.
     */
    public void setCPUDetails(String speed, String cores) {
        speedLabel.setText("Speed: " + speed);
        coresLabel.setText("Cores: " + cores);
    }

    /**
     * Sets up a table to display CPU details.
     *
     * @param cpuList The list of CPUModel objects to display in the table.
     */
    public void setupTable(List<CPUModel> cpuList) {
        String[] columnNames = {"ID", "Name", "Speed", "Overclock"};
        Object[][] data = new Object[cpuList.size()][4];
        for (int i = 0; i < cpuList.size(); i++) {
            data[i][0] = cpuList.get(i).getId();
            data[i][1] = cpuList.get(i).getName();
            data[i][2] = cpuList.get(i).getSpeed();
            data[i][3] = cpuList.get(i).getOverclock();
        }
        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        cpuTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(cpuTable);
        panel.add(scrollPane);
        panel.revalidate();
        panel.repaint();
    }

    /**
     * Retrieves the JTable containing CPU details.
     *
     * @return The JTable containing CPU details.
     */
    public JTable getCPUTable() {
        return cpuTable;
    }

    /**
     * Retrieves the JFrame of the CPUView.
     *
     * @return The JFrame of the CPUView.
     */
    public JFrame getFrame() {
        return frame;
    }

    /**
     * Closes the CPUView window.
     */
    public void closeWindow() {
        frame.dispose();
    }

}
