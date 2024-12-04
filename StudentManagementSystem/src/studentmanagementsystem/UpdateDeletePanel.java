/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package studentmanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class UpdateDeletePanel extends JPanel {
    private JTextField idField, firstNameField, lastNameField, ageField, emailField;
    private JTextArea outputArea;

    public UpdateDeletePanel() {
        setLayout(new GridLayout(7, 2, 10, 10));

        idField = new JTextField();
        firstNameField = new JTextField();
        lastNameField = new JTextField();
        ageField = new JTextField();
        emailField = new JTextField();
        outputArea = new JTextArea();
        outputArea.setEditable(false);

        JButton searchButton = new JButton("Search by ID");
        searchButton.addActionListener(this::handleSearchStudent);

        JButton updateButton = new JButton("Update Student");
        updateButton.addActionListener(this::handleUpdateStudent);

        JButton deleteButton = new JButton("Delete Student");
        deleteButton.addActionListener(this::handleDeleteStudent);

        add(new JLabel("Student ID:"));
        add(idField);
        add(new JLabel("First Name:"));
        add(firstNameField);
        add(new JLabel("Last Name:"));
        add(lastNameField);
        add(new JLabel("Age:"));
        add(ageField);
        add(new JLabel("Email:"));
        add(emailField);
        add(searchButton);
        add(updateButton);
        add(deleteButton);
        add(new JScrollPane(outputArea));
    }

    private void handleSearchStudent(ActionEvent event) {
        try {
            int id = Integer.parseInt(idField.getText());
            Student student = new StudentDAO().getStudentById(id);
            if (student != null) {
                firstNameField.setText(student.getFirstName());
                lastNameField.setText(student.getLastName());
                ageField.setText(String.valueOf(student.getAge()));
                emailField.setText(student.getEmail());
                outputArea.setText("Student Found: " + student.getFirstName() + " " + student.getLastName());
            } else {
                outputArea.setText("Student not found.");
            }
        } catch (Exception e) {
            outputArea.setText("Error: " + e.getMessage());
        }
    }

    private void handleUpdateStudent(ActionEvent event) {
        try {
            int id = Integer.parseInt(idField.getText());
            Student student = new Student(id, firstNameField.getText(), lastNameField.getText(),
                    Integer.parseInt(ageField.getText()), emailField.getText());
            new StudentDAO().updateStudent(student);
            outputArea.setText("Student updated successfully.");
        } catch (Exception e) {
            outputArea.setText("Error: " + e.getMessage());
        }
    }

    private void handleDeleteStudent(ActionEvent event) {
        try {
            int id = Integer.parseInt(idField.getText());
            new StudentDAO().deleteStudent(id);
            outputArea.setText("Student deleted successfully.");
            clearFields();
        } catch (Exception e) {
            outputArea.setText("Error: " + e.getMessage());
        }
    }

    private void clearFields() {
        firstNameField.setText("");
        lastNameField.setText("");
        ageField.setText("");
        emailField.setText("");
    }
}
