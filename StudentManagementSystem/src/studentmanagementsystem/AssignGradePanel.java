/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package studentmanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AssignGradePanel extends JPanel {

    private JTextField studentIdField;
    private JTextField gradeField;
    private JTextArea outputArea;

    public AssignGradePanel() {
        setLayout(new GridLayout(4, 2, 10, 10));

        // Create UI components
        studentIdField = new JTextField();
        gradeField = new JTextField();
        outputArea = new JTextArea();
        outputArea.setEditable(false);

        JButton assignGradeButton = new JButton("Assign Grade");
        assignGradeButton.addActionListener(this::handleAssignGrade);

        // Add components to the panel
        add(new JLabel("Student ID:"));
        add(studentIdField);
        add(new JLabel("Grade:"));
        add(gradeField);
        add(assignGradeButton);
        add(new JScrollPane(outputArea));
    }

    private void handleAssignGrade(ActionEvent event) {
        try {
            int studentId = Integer.parseInt(studentIdField.getText());
            String grade = gradeField.getText().trim();

            // Validate input
            if (grade.isEmpty()) {
                outputArea.setText("Error: Grade cannot be empty.");
                return;
            }

            // Check if student exists and assign grade
            if (assignGradeToStudent(studentId, grade)) {
                outputArea.setText("Grade assigned successfully!");
            } else {
                outputArea.setText("Error: Student ID not found.");
            }
        } catch (NumberFormatException e) {
            outputArea.setText("Error: Invalid ID format. Please enter a valid number.");
        } catch (SQLException e) {
            outputArea.setText("Database Error: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            outputArea.setText("Error: " + e.getMessage());
        }
    }

    private boolean assignGradeToStudent(int studentId, String grade) throws SQLException {
        String query = "UPDATE students SET grade = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, grade);
            stmt.setInt(2, studentId);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0; // Return true if at least one row was updated
        }
    }
}

