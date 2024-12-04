/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package studentmanagementsystem;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class FilterPanel extends JPanel {
    private JTextField nameField, ageField;
    private JTable studentTable;
    private JTextArea outputArea;

    public FilterPanel() {
        setLayout(new BorderLayout());
        JPanel filterPanel = new JPanel(new GridLayout(1, 5, 10, 10));

        nameField = new JTextField();
        ageField = new JTextField();
        JButton filterButton = new JButton("Filter Students");
        filterButton.addActionListener(this::handleFilterStudents);

        filterPanel.add(new JLabel("Name:"));
        filterPanel.add(nameField);
        filterPanel.add(new JLabel("Age:"));
        filterPanel.add(ageField);
        filterPanel.add(filterButton);

        studentTable = new JTable();
        outputArea = new JTextArea();
        outputArea.setEditable(false);

        add(filterPanel, BorderLayout.NORTH);
        add(new JScrollPane(studentTable), BorderLayout.CENTER);
        add(new JScrollPane(outputArea), BorderLayout.SOUTH);
    }

    private void handleFilterStudents(ActionEvent event) {
        try {
            String name = nameField.getText();
            int age = ageField.getText().isEmpty() ? -1 : Integer.parseInt(ageField.getText());
            List<Student> students = new StudentDAO().filterStudents(name, age);

            DefaultTableModel model = new DefaultTableModel(
                new String[]{"ID", "First Name", "Last Name", "Age", "Email"}, 0);
            for (Student student : students) {
                model.addRow(new Object[]{
                        student.getId(),
                        student.getFirstName(),
                        student.getLastName(),
                        student.getAge(),
                        student.getEmail()
                });
            }
            studentTable.setModel(model);
            outputArea.setText(students.size() + " student(s) found.");
        } catch (Exception e) {
            outputArea.setText("Error: " + e.getMessage());
        }
    }
}
