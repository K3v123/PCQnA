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

public class CoolingView extends BaseView {

    private JLabel typeLabel;
    private JButton fetchDetailsButton;  // New button for fetching details

    public CoolingView() {
        super();
        initializeFrame("Cooling Details");

        typeLabel = new JLabel("Type: ");
        fetchDetailsButton = new JButton("Fetch Details");  // Initialize the new button

        panel.add(typeLabel);
        panel.add(fetchDetailsButton);  // Add the new button to the panel
        panel.add(goBackButton);

        frame.add(panel);
        frame.setVisible(true);
    }

    public void setCoolingDetails(String type) {
        typeLabel.setText("Type: " + type);
    }

    // New method to attach a listener to the fetchDetailsButton
    public void addFetchDetailsButtonListener(ActionListener listener) {
        fetchDetailsButton.addActionListener(listener);
    }
}
