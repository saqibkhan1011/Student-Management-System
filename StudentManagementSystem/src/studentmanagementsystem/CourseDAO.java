/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package studentmanagementsystem;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseDAO {
    public void addCourse(Course course) throws SQLException {
        String query = "INSERT INTO courses (courseId, courseName, creditHours, courseInstructor) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, course.getCourseId());
            stmt.setString(2, course.getCourseName());
            stmt.setInt(3, course.getCreditHours());
            stmt.setString(4, course.getCourseInstructor());
            stmt.executeUpdate();
        }
    }

    public List<Course> getAllCourses() throws SQLException {
        String query = "SELECT * FROM courses";
        List<Course> courses = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                courses.add(new Course(
                    rs.getInt("courseId"),
                    rs.getString("courseName"),
                    rs.getInt("creditHours"),
                    rs.getString("courseInstructor")
                ));
            }
        }
        return courses;
    }
}

    
 
 