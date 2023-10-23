/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package try2;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 *
 * @author kq635
 */
import javax.swing.*;

public class FinalSelectionView {

    private JFrame frame;
    private JPanel panel;
    private JTextArea selectedComponentsText;
    private JButton closeButton;

    public FinalSelectionView(SelectionStore selectionStore) {
        frame = new JFrame("Your Setup");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 300);

        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        selectedComponentsText = new JTextArea();
        setupTextArea(selectedComponentsText);

        // Set the text from SelectionStore
        StringBuilder components = new StringBuilder();
        components.append(selectionStore.getGpuSelection()).append("\n");
        components.append(selectionStore.getMotherboardSelection()).append("\n");
        components.append(selectionStore.getTpuSelection()).append("\n");
        components.append(selectionStore.getStorageSelection()).append("\n");
        components.append(selectionStore.getCoolingSelection()).append("\n");
        components.append(selectionStore.getPowerSupplySelection()).append("\n");
        components.append(selectionStore.getMemorySelection()).append("\n");
        components.append(selectionStore.getCpuSelection()).append("\n");
        selectedComponentsText.setText(components.toString());

        closeButton = new JButton("Close");
        closeButton.addActionListener(e -> frame.dispose());  // Close the current window

        panel.add(selectedComponentsText);
        panel.add(closeButton);

        frame.add(panel);
        frame.setVisible(true);
    }

    private void setupTextArea(JTextArea textArea) {
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);
        textArea.setCaretPosition(0);
        textArea.setEditable(false);
        textArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }
}
