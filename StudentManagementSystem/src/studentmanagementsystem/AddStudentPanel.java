/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package studentmanagementsystem;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class AddStudentPanel extends JPanel {
    private JTextField firstNameField, lastNameField, ageField, emailField;
    private JTextArea outputArea;

    public AddStudentPanel() {
        setLayout(new GridLayout(6, 2, 10, 10));
        firstNameField = new JTextField();
        lastNameField = new JTextField();
        ageField = new JTextField();
        emailField = new JTextField();
        outputArea = new JTextArea();
        outputArea.setEditable(false);

        JButton addButton = new JButton("Add Student");
        addButton.addActionListener(this::handleAddStudent);

        add(new JLabel("First Name:"));
        add(firstNameField);
        add(new JLabel("Last Name:"));
        add(lastNameField);
        add(new JLabel("Age:"));
        add(ageField);
        add(new JLabel("Email:"));
        add(emailField);
        add(addButton);
        add(new JScrollPane(outputArea));
    }

    private void handleAddStudent(ActionEvent event) {
        try {
            Student student = new Student(0, firstNameField.getText(), lastNameField.getText(),
                    Integer.parseInt(ageField.getText()), emailField.getText());
            new StudentDAO().addStudent(student);
            outputArea.setText("Student added successfullly!");
        } catch (Exception e) {
            outputArea.setText("Error: " + e.getMessage());
        }
    }
}
