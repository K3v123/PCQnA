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

public class AdminView extends BaseView {

    private JTextField usernameField, passwordField;
    private JButton loginButton;

    public AdminView() {
        super();
        initializeFrame("Admin Login");

        JLabel usernameLabel = new JLabel("Username: ");
        JLabel passwordLabel = new JLabel("Password: ");

        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);
        loginButton = new JButton("Login");

        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(loginButton);
        panel.add(goBackButton);

        frame.add(panel);
        frame.setVisible(true);
    }

    public String getUsername() {
        return usernameField.getText();
    }

    public String getPassword() {
        return passwordField.getText();
    }

    public void displaySuccessMessage(String successMessage) {
        JOptionPane.showMessageDialog(frame, successMessage, "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    public void displayErrorMessage(String errorMessage) {
        JOptionPane.showMessageDialog(frame, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void addLoginButtonListener(ActionListener listenForLoginButton) {
        loginButton.addActionListener(listenForLoginButton);
    }
}
