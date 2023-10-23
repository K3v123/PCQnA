/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package try2;

import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author kq635
 */
public class MotherboardView extends BaseView {

    private JLabel typeLabel, sizeLabel;
    private JButton fetchDetailsButton;

    public MotherboardView() {
        super();
        frame.setTitle("Motherboard Details");

        typeLabel = new JLabel("Type: ");
        sizeLabel = new JLabel("Size: ");

        fetchDetailsButton = new JButton("Fetch Details");

        panel.add(typeLabel);
        panel.add(sizeLabel);
        panel.add(fetchDetailsButton);
        panel.add(goBackButton);

        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }

    public void setMotherboardDetails(String type, String size) {
        typeLabel.setText("Type: " + type);
        sizeLabel.setText("Size: " + size);
    }

    public void addFetchDetailsButtonListener(ActionListener listener) {
        fetchDetailsButton.addActionListener(listener);
    }

    public void dispose() {
        frame.dispose();
    }

    public void displayErrorMessage(String errorMessage) {
        JOptionPane.showMessageDialog(frame, errorMessage);
    }
}
