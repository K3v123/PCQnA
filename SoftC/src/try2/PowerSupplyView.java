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

public class PowerSupplyView extends BaseView {

    private JLabel classLabel;
    private JButton fetchDetailsButton; // This button will be used to fetch details

    public PowerSupplyView() {
        super();
        initializeFrame("Power Supply Details");

        classLabel = new JLabel("Class: ");

        fetchDetailsButton = new JButton("Fetch Details"); // Initialize the button

        panel.add(classLabel);
        panel.add(fetchDetailsButton); // Add the button to the panel
        panel.add(goBackButton);

        frame.add(panel);
        frame.setVisible(true);
    }

    public void setPowerSupplyDetails(String classification) {
        classLabel.setText("Class: " + classification);
    }

    public void addFetchDetailsButtonListener(ActionListener listener) {
        fetchDetailsButton.addActionListener(listener);
    }

    // ... [rest of the class remains unchanged]
}
