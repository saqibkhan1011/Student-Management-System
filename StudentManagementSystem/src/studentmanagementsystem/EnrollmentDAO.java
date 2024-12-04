/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package studentmanagementsystem;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EnrollmentDAO {

     public void enrollStudent(int studentId, int courseId) throws SQLException {
        String query = "INSERT INTO enrollments (studentId, courseId) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, studentId);
            stmt.setInt(2, courseId);
            stmt.executeUpdate();
        }
    }
    public List<String> getEnrolledStudentsWithCourses() throws SQLException {
        String query = """
    SELECT s.first_name, s.last_name, s.email, c.courseName AS course_name
    FROM enrollments e
    JOIN students s ON e.studentId = s.id
    JOIN courses c ON e.courseId = c.courseId
""";


        List<String> result = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                String record = String.format(
                    "Student: %s %s, Email: %s, Course: %s",
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getString("email"),
                    rs.getString("course_name")
                );
                result.add(record);
            }
        }
        return result;
    }
}



