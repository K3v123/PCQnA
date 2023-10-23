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
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class CPUView extends BaseView {

    private JLabel speedLabel, coresLabel;
    private JButton fetchDetailsButton, recommendationButton;
    private JFrame recommendationFrame;
    private JTextArea recommendationText, infoText;
    private JTable cpuTable;

    public CPUView() {
        super();
        initializeFrame("CPU Details");
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        speedLabel = new JLabel("Speed: ");
        coresLabel = new JLabel("Cores: ");
        infoText = new JTextArea("The Central Processing Unit (CPU) is the primary component responsible for executing instructions of a computer program.", 5, 30);
        infoText.setEditable(false);
        fetchDetailsButton = new JButton("Fetch CPU Details");
        recommendationButton = new JButton("Show Recommendation");

        recommendationButton.addActionListener(e -> showRecommendation());

        panel.add(infoText);
        panel.add(speedLabel);
        panel.add(coresLabel);
        panel.add(fetchDetailsButton);
        panel.add(recommendationButton);
        panel.add(goBackButton);

        frame.add(panel);
        frame.setSize(650, 450);
        frame.setVisible(true);
    }

    public void addFetchDetailsButtonListener(ActionListener listener) {
        fetchDetailsButton.addActionListener(listener);
    }

    public void setCPUDetails(String speed, String cores) {
        speedLabel.setText("Speed: " + speed);
        coresLabel.setText("Cores: " + cores);
    }

    private void showRecommendation() {
        recommendationFrame = new JFrame("CPU Recommendation");
        recommendationFrame.setSize(300, 200);
        recommendationFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel recommendationPanel = new JPanel();
        recommendationPanel.setLayout(new BoxLayout(recommendationPanel, BoxLayout.Y_AXIS));

        recommendationText = new JTextArea("For most users, a quad-core or hexa-core processor will suffice. For gaming or heavy multitasking, consider octa-core or higher.", 5, 30);
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

    public void setupTable(List<CPUModel> cpuList) {
        // Define table column names
        String[] columnNames = {"ID", "Name", "Speed", "Overclock"};

        // Convert CPUModel list to table data
        Object[][] data = new Object[cpuList.size()][4];
        for (int i = 0; i < cpuList.size(); i++) {
            data[i][0] = cpuList.get(i).getId();
            data[i][1] = cpuList.get(i).getName();
            data[i][2] = cpuList.get(i).getSpeed();
            data[i][3] = cpuList.get(i).getOverclock();
        }

        cpuTable = new JTable(data, columnNames);
        fetchDetailsButton.setEnabled(false);

        JScrollPane scrollPane = new JScrollPane(cpuTable);
        panel.add(scrollPane);
        panel.revalidate();
        panel.repaint();
    }

    public JTable getCPUTable() {
        return cpuTable;
    }
}
