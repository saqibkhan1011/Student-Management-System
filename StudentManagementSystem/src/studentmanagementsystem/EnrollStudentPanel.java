/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package studentmanagementsystem;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.*;
import java.sql.SQLException;


public class EnrollStudentPanel extends JPanel {
    private JTextField studentIdField, courseIdField;
    private JTextArea outputArea;

    public EnrollStudentPanel() {
        setLayout(new GridLayout(4, 2, 10, 10));

        studentIdField = new JTextField();
        courseIdField = new JTextField();
        outputArea = new JTextArea();
        outputArea.setEditable(false);

        JButton enrollButton = new JButton("Enroll Student");
        enrollButton.addActionListener(this::handleEnrollment);

        add(new JLabel("Student ID:"));
        add(studentIdField);
        add(new JLabel("Course ID:"));
        add(courseIdField);
        add(enrollButton);
        add(new JScrollPane(outputArea));
    }

   private void handleEnrollment(ActionEvent event) {
    try {
        int studentId = Integer.parseInt(studentIdField.getText());
        int courseId = Integer.parseInt(courseIdField.getText());
        new EnrollmentDAO().enrollStudent(studentId, courseId);
        outputArea.setText("Student enrolled successfully!");
    } catch (NumberFormatException e) {
        outputArea.setText("Error: Invalid ID format. Please enter valid numbers.");
    } catch (SQLException e) {
    outputArea.setText("Database Error: " + e.getMessage());
    e.printStackTrace(); // For debugging purposes
}
 catch (Exception e) {
        outputArea.setText("Error: " + e.getMessage());
    }
}

}

