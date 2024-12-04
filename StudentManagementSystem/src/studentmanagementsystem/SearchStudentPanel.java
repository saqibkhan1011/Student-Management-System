
package  studentmanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class SearchStudentPanel extends JPanel {
    private JTextField idField;
    private JTextArea outputArea;

    public SearchStudentPanel() {
        setLayout(new GridLayout(3, 2, 10, 10));
        idField = new JTextField();
        outputArea = new JTextArea();
        outputArea.setEditable(false);

        JButton searchButton = new JButton("Search Student");
        searchButton.addActionListener(this::handleSearchStudent);
        
        add(new JLabel("Student ID:"));
        add(idField);
        add(searchButton);
        add(new JScrollPane(outputArea));
    }

    private void handleSearchStudent(ActionEvent event) {
        try {
            int id = Integer.parseInt(idField.getText());
            Student student = new StudentDAO().getStudentById(id);
            if (student != null) {
                outputArea.setText("Student Found:\n" +
                        "Name: " + student.getFirstName() + " " + student.getLastName() + "\n" +
                        "Age: " + student.getAge() + "\n" +
                        "Email: " + student.getEmail());
            } else {
                outputArea.setText("Student not found.");
            }
        } catch (Exception e) {
            outputArea.setText("Error: " + e.getMessage());
        }
    }
}
