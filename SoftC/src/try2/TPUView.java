/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package try2;

/**
 *
 * @author kq635
 */
import java.awt.event.ActionListener;
import javax.swing.*;

public class TPUView extends BaseView {

    private JLabel speedLabel;
    private JButton fetchDetailsButton;  // Add this line

    public TPUView() {
        super();
        frame.setTitle("TPU Details");

        speedLabel = new JLabel("Speed: ");
        fetchDetailsButton = new JButton("Fetch TPU Details");  // Initialize the button

        panel.add(speedLabel);
        panel.add(fetchDetailsButton);  // Add the button to the panel
        panel.add(goBackButton);

        frame.add(panel);
        frame.setVisible(true);
    }

    public void setTPUDetails(String speed) {
        speedLabel.setText("Speed: " + speed);
    }

    public void addFetchDetailsButtonListener(ActionListener listener) {
        fetchDetailsButton.addActionListener(listener);
    }

    public void displayErrorMessage(String errorMessage) {
        JOptionPane.showMessageDialog(frame, errorMessage);
    }

    public void dispose() {
        frame.dispose();
    }

}
