/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package studentmanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class ViewEnrollmentsPanel extends JPanel {
    private JTextArea outputArea;

    public ViewEnrollmentsPanel() {
        setLayout(new BorderLayout()); // Use BorderLayout to position components

        // Create the output area and make it non-editable
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setLineWrap(true);
        outputArea.setWrapStyleWord(true);

        // Create the refresh button and add an action listener
        JButton refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(e -> loadEnrollments());

        // Add components to the panel
        add(new JScrollPane(outputArea), BorderLayout.CENTER);
        add(refreshButton, BorderLayout.SOUTH);

        // Load enrollments on initialization
        loadEnrollments();
    }

    private void loadEnrollments() {
        try {
            // Get the enrolled students and courses from the DAO
            List<String> enrollments = new EnrollmentDAO().getEnrolledStudentsWithCourses();
            StringBuilder sb = new StringBuilder();

            // Check if the list is empty
            if (enrollments.isEmpty()) {
                sb.append("No enrollments found.");
            } else {
                for (String record : enrollments) {
                    sb.append(record).append("\n");
                }
            }

            // Update the output area with the formatted enrollments list
            outputArea.setText(sb.toString());
        } catch (SQLException e) {
            // Handle any SQL errors
            outputArea.setText("Database Error: " + e.getMessage());
            e.printStackTrace(); // For debugging purposes
        }
    }
}


