/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package studentmanagementsystem;
import java.sql.*;
public class AuthenticationManager {
    private static boolean isAuthenticated = false;
    private static String currentUsername;

    public static boolean isAuthenticated() {
        return isAuthenticated;
    }

    public static String getCurrentUsername() {
        return currentUsername;
    }

    public static void authenticate(String username, String password) throws SQLException {
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username);
            stmt.setString(2, password); // Hash this password in real scenarios

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                isAuthenticated = true;
                currentUsername = username;
            } else {
                throw new SQLException("Invalid username or password.");
            }
        }
    }

    public static void signUp(String username, String password, String email) throws SQLException {
        String query = "INSERT INTO users (username, password, email) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username);
            stmt.setString(2, password); // Hash this password in real scenarios
            stmt.setString(3, email);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                isAuthenticated = true;
                currentUsername = username;
            } else {
                throw new SQLException("Failed to create account.");
            }
        }
    }
}
