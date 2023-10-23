/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package try2;

/**
 *
 * @author kq635
 */
import javax.swing.JLabel;
import javax.swing.*;
import java.awt.event.ActionListener;

public class CPUView extends BaseView {

    private JLabel nameLabel, speedLabel, overclockLabel;
    private JButton fetchDetailsButton; // Add this button

    public CPUView() {
        super();
        frame.setTitle("CPU Details");

        nameLabel = new JLabel("Name: ");
        speedLabel = new JLabel("Speed: ");
        overclockLabel = new JLabel("Overclock: ");
        fetchDetailsButton = new JButton("Fetch Details"); // Initialize the button

        panel.add(nameLabel);
        panel.add(speedLabel);
        panel.add(overclockLabel);
        panel.add(fetchDetailsButton); // Add the button to the panel
        panel.add(goBackButton);

        frame.add(panel);
        frame.setVisible(true);
    }

    public void setCPUDetails(String name, String speed, boolean overclock) {
        nameLabel.setText("Name: " + name);
        speedLabel.setText("Speed: " + speed);
        overclockLabel.setText("Overclock: " + (overclock ? "Yes" : "No"));
    }

    public void addFetchDetailsButtonListener(ActionListener listener) {
        fetchDetailsButton.addActionListener(listener);
    }

    // The rest of the methods from BaseView are inherited and do not need to be redefined here.
}
