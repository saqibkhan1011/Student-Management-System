
package studentmanagementsystem;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class AddCoursePanel extends JPanel {
    private JTextField courseNameField, courseIdField, creditHoursField,courseInstructor;
    private JTextArea outputArea;

    public AddCoursePanel() {
        setLayout(new GridLayout(6, 2, 10, 10));
        courseNameField = new JTextField();
        courseIdField = new JTextField();
        creditHoursField = new JTextField();
        courseInstructor = new JTextField();
        outputArea = new JTextArea();
        outputArea.setEditable(false);

        JButton addCourseButton = new JButton("Add Course");
        addCourseButton.addActionListener(this::handleAddCourse);

        add(new JLabel("Course Name:"));
        add(courseNameField);
        add(new JLabel("Course ID:"));
        add(courseIdField);
        add(new JLabel("Course Credit Hours:"));
        add(creditHoursField);
        add(new JLabel("Instructor Name:"));
        add(courseInstructor);
        add(addCourseButton);
        add(new JScrollPane(outputArea));
    }

    private void handleAddCourse(ActionEvent event) {
        try {
            Course course = new Course( Integer.parseInt(courseIdField.getText()), courseNameField.getText(),
                    Integer.parseInt(creditHoursField.getText()), courseInstructor.getText());
            new CourseDAO().addCourse(course);
            outputArea.setText("Course added successfully!");
        } catch (Exception e) {
            outputArea.setText("Error: " + e.getMessage());
        }
    }
}
