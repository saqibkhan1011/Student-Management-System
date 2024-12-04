/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package studentmanagementsystem;


import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class MainUI {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Show the authentication window first
            if (!AuthenticationManager.isAuthenticated()) {
                showAuthWindow();
            }

            // Only show the main application window if authenticated
            if (AuthenticationManager.isAuthenticated()) {
                showMainAppWindow();
            }
        });
    }

    private static void showAuthWindow() {
    // Create a JFrame for the authentication window
    JFrame authFrame = new JFrame("Login / Sign Up");
    authFrame.setSize(400, 300);
    authFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    authFrame.setLayout(new BorderLayout());

    JPanel authPanel = new JPanel();
    authPanel.setLayout(new GridLayout(5, 2, 10, 10)); // Adjust the layout as needed

    // Create components
    JLabel usernameLabel = new JLabel("Username:");
    JTextField usernameField = new JTextField();
    JLabel passwordLabel = new JLabel("Password:");
    JPasswordField passwordField = new JPasswordField();
    JLabel emailLabel = new JLabel("Email (Sign Up only):");
    JTextField emailField = new JTextField();
    JButton loginButton = new JButton("Login");
    JButton signUpButton = new JButton("Sign Up");

    // Add components to the panel
    authPanel.add(usernameLabel);
    authPanel.add(usernameField);
    authPanel.add(passwordLabel);
    authPanel.add(passwordField);
    authPanel.add(emailLabel);
    authPanel.add(emailField);
    authPanel.add(loginButton);
    authPanel.add(signUpButton);

    // Add action listeners to buttons
    loginButton.addActionListener(e -> {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        // Check if the fields are filled
        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(authFrame, "Please fill in both username and password.", "Input Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            AuthenticationManager.authenticate(username, password);
            JOptionPane.showMessageDialog(authFrame, "Login successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
            authFrame.dispose(); // Close the login window
            showMainAppWindow(); // Show the main application window
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(authFrame, "Login Failed: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    });

    signUpButton.addActionListener(e -> {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        String email = emailField.getText();

        // Check if the fields are filled
        if (username.isEmpty() || password.isEmpty() || email.isEmpty()) {
            JOptionPane.showMessageDialog(authFrame, "Please fill in all fields for sign up.", "Input Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            AuthenticationManager.signUp(username, password, email);
            JOptionPane.showMessageDialog(authFrame, "Sign Up successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
            authFrame.dispose(); // Close the sign-up window
            showMainAppWindow(); // Show the main application window
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(authFrame, "Sign Up Failed: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    });

    // Add the panel to the frame and set frame properties
    authFrame.add(authPanel, BorderLayout.CENTER);
    authFrame.setVisible(true);
}


    private static void showMainAppWindow() {
        JFrame frame = new JFrame("Student Database Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.add(new MainPanel());
        frame.setVisible(true);
    }
}

