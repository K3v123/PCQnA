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
import java.awt.event.ActionListener;


public class MemoryView extends BaseView {

    private JLabel sizeLabel;
    private JButton fetchDetailsButton;  // Added this line

    public MemoryView() {
        super();
        initializeFrame("Memory Details");

        sizeLabel = new JLabel("Size: ");
        fetchDetailsButton = new JButton("Fetch Details");  // Initialize the button

        panel.add(sizeLabel);
        panel.add(fetchDetailsButton);  // Add the button to the panel
        panel.add(goBackButton);

        frame.add(panel);
        frame.setVisible(true);
    }

    public JFrame getFrame() {
        return frame;
    }

    public void addFetchDetailsButtonListener(ActionListener listener) {
        fetchDetailsButton.addActionListener(listener);
    }

    public void setMemoryDetails(String size) {
        sizeLabel.setText("Size: " + size);
    }
}
