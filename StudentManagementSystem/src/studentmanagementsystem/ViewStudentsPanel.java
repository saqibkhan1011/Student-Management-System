/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package studentmanagementsystem;


import java.awt.BorderLayout;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ViewStudentsPanel extends JPanel {
    private JTable studentTable;
    private JTextArea outputArea;

    public ViewStudentsPanel() {
        setLayout(new BorderLayout());
        studentTable = new JTable();
        outputArea = new JTextArea();
        outputArea.setEditable(false);

        JButton refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(e -> loadStudents());

        add(new JScrollPane(studentTable), BorderLayout.CENTER);
        add(refreshButton, BorderLayout.NORTH);
        add(new JScrollPane(outputArea), BorderLayout.SOUTH);
    }

    private void loadStudents() {
        try {
            List<Student> students = new StudentDAO().getAllStudents();
            DefaultTableModel model = new DefaultTableModel(
                new String[]{"ID", "First Name", "Last Name", "Age", "Email"}, 0);
            for (Student student : students) {
                model.addRow(new Object[]{
                    student.getId(), student.getFirstName(), student.getLastName(),
                    student.getAge(), student.getEmail()
                });
            }
            studentTable.setModel(model);
            outputArea.setText("Students loaded successfully.");
        } catch (Exception e) {
            outputArea.setText("Error: " + e.getMessage());
        }
    }
}
