/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package studentmanagementsystem;

import javax.swing.*;
import java.awt.*;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {

    public MainPanel() {
        setLayout(new BorderLayout());
        JTabbedPane tabbedPane = new JTabbedPane();

        tabbedPane.addTab("Add Student", new AddStudentPanel());
        tabbedPane.addTab("View Students", new ViewStudentsPanel());
        tabbedPane.addTab("Search Student", new SearchStudentPanel());
        tabbedPane.addTab("Update/Delete Student", new UpdateDeletePanel());
        tabbedPane.addTab("Filter Students", new FilterPanel());
        tabbedPane.addTab("Add Course", new AddCoursePanel());
        tabbedPane.addTab("Enroll Student", new EnrollStudentPanel());
        tabbedPane.addTab("View Enrollments", new ViewEnrollmentsPanel());
        tabbedPane.addTab("Assign Grade", new AssignGradePanel());
      

        add(tabbedPane, BorderLayout.CENTER);
    }
}

