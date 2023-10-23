package view;
import Model.GPUModel;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class GPUView extends BaseView {

    private JLabel GPUHeader;
    private JTextArea recommendationText, descriptionText;
    private JButton fetchTypesButton, recommendationButton;
    private JDialog recommendationDialog;
    private JButton submitButton;
    private JTable gpuTable;
    private boolean fetched = false;

    /**
     * Constructor for GPUView.
     */
    public GPUView() {
        super();

        frame.setTitle("GPU Details");
        frame.setSize(600, 400);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        GPUHeader = new JLabel("GPU types:");
        goBackButton.setText("<");
        goBackButton.setBounds(10, 10, 45, 30);
        frame.add(goBackButton);
        panel.add(Box.createVerticalStrut(50));

        // Description
        descriptionText = new JTextArea("A GPU, or Graphics Processing Unit, is a specialized electronic circuit designed to accelerate the image output in a frame buffer intended for output to a display.");
        setupTextArea(descriptionText);
        panel.add(descriptionText);

        fetchTypesButton = new JButton("Fetch GPU Types");
        recommendationButton = new JButton("View Recommendation");
        recommendationButton.addActionListener(e -> showRecommendation());

        panel.add(GPUHeader);
        panel.add(fetchTypesButton);
        panel.add(recommendationButton);

        frame.add(panel);
        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                frame.setVisible(false);
                goBackButton.doClick(); // Simulate a click on the go back button when closing the window
            }
        });
    }

    /**
     * Set up the text area for displaying text.
     */
    private void setupTextArea(JTextArea textArea) {
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);
        textArea.setCaretPosition(0);
        textArea.setEditable(false);
        textArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }

    /**
     * Show the GPU recommendation dialog.
     */
    private void showRecommendation() {
        frame.setVisible(false); // Hide the GPU frame when showing recommendation

        recommendationDialog = new JDialog(frame, "Recommendation", true);
        recommendationDialog.setSize(400, 200);
        recommendationDialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        recommendationDialog.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                recommendationDialog.dispose();
                frame.setVisible(true); // Show the GPU frame when recommendation dialog is closed
            }
        });

        recommendationText = new JTextArea("For gaming, we recommend NVIDIA's RTX series or AMD's RX series. For graphic design, NVIDIA's Quadro series or AMD's Radeon Pro series is suitable.");
        setupTextArea(recommendationText);

        JButton closeButton = new JButton("Close");
        closeButton.setPreferredSize(new Dimension(100, 30));
        closeButton.addActionListener(e -> {
            recommendationDialog.dispose();
            frame.setVisible(true); // Show the GPU frame when the "Close" button in recommendation dialog is clicked
        });

        JPanel dialogPanel = new JPanel();
        dialogPanel.setLayout(new BoxLayout(dialogPanel, BoxLayout.Y_AXIS));
        dialogPanel.add(recommendationText);
        dialogPanel.add(closeButton);

        recommendationDialog.add(dialogPanel);
        recommendationDialog.setVisible(true);
    }

    /**
     * Add a listener for the submit button.
     */
    public void addSubmitButtonListener(ActionListener listenForSubmitButton) {
        submitButton.addActionListener(listenForSubmitButton);
    }

    /**
     * Add a listener for the fetch GPU types button.
     */
    public void addFetchTypesButtonListener(ActionListener listenForFetchTypesButton) {
        fetchTypesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!fetched) {
                    listenForFetchTypesButton.actionPerformed(e);
                    fetched = true;
                    fetchTypesButton.setEnabled(false); // Disables the button after fetching
                }
            }
        });
    }

    /**
     * Display an error message dialog.
     */
    public void displayErrorMessage(String errorMessage) {
        JOptionPane.showMessageDialog(frame, errorMessage);
    }

    /**
     * Set up the table to display GPU information.
     */
    public void setupTable(List<GPUModel> gpuList) {
        String[] columnNames = {"ID", "Name", "Speed", "Class"};
        Object[][] data = new Object[gpuList.size()][4];
        for (int i = 0; i < gpuList.size(); i++) {
            data[i][0] = gpuList.get(i).getId();
            data[i][1] = gpuList.get(i).getName();
            data[i][2] = gpuList.get(i).getSpeed();
            data[i][3] = gpuList.get(i).getClassification();
        }
        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        gpuTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(gpuTable);
        panel.add(scrollPane);
        panel.revalidate();
        panel.repaint();
    }

    /**
     * Get the frame of the GPUView.
     */
    public JFrame getFrame() {
        return frame;
    }

    /**
     * Close the GPUView window.
     */
    public void closeWindow() {
        frame.dispose();
    }
}
