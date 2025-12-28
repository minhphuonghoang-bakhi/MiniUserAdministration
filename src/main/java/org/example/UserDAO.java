package org.example;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    //CREATE
    public boolean addUser(User user) {
        String sql = "INSERT INTO users (username, email, password) VALUES (?, ?, ?)";

        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getEmail());
            pstmt.setString(3, user.getPassword());

            int rowsAffected = pstmt.executeUpdate();
            return (rowsAffected > 0);
        }
        catch (SQLException e) {
            System.err.println("Error creating user: " + e.getMessage());
            return false;
        }
    }

    //READ - retrieve all inputted users
    public List<User> getAllUsers() {
        String sql = "SELECT * FROM users ORDER BY id";
        List<User> users = new ArrayList<>();

        try {
            Connection conn = DatabaseConnection.getConnection();
            Statement stmt = conn.createStatement();  //statement to execute sql
            ResultSet rs = stmt.executeQuery(sql);
            //ResultSet acts like a box or window holding query results,
            //and rs.getXXX() lets Java fetch each column of each row one by one from that box.

            while (rs.next()) {
                User user = new User(
                        rs.getInt("id"),  //from Integer in DB into int in Java
                        rs.getString("username"),   //from VARCHAR in DB into String in Java to pass the constructor parameter
                        rs.getString("email"),
                        rs.getString("password")
                );
                users.add(user);  //add the created object user to users Arraylist
            }
        }
        catch (SQLException e) {
        System.err.println("Error retrieving users: " + e.getMessage());
        }
        return users;  //return the list
    }

    //Read - get users by id
    //prepared statement: contains placeholder
    //statement: does not, used for sql not holding ?
    public User getUserByID(int id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                User user = new User(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("password")
                );
                return user;  //just return one user so here is fine
            }
        }
        catch (SQLException e) {
            System.err.println("Error retrieving users: " + e.getMessage());
        }
        return null;  // if (while) return user, else return null
    }

    // Update
    public boolean updateUser(User user) {
        String sql = "UPDATE users SET username=?, email=?, password=? WHERE id=?";

        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getEmail());
            pstmt.setString(3, user.getPassword());
            pstmt.setInt(4, user.getId());

            int rowsAffected = pstmt.executeUpdate();
            return (rowsAffected > 0);

    }
        catch (SQLException e) {
            System.err.println("Error updating user: " + e.getMessage());
        }
        return false;
    }

    // DELETE
    public boolean deleteUser(int id) {
        String sql = "DELETE FROM users WHERE id = ?";

        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, id);
            int rowsAffected = pstmt.executeUpdate();
            return (rowsAffected > 0);
        } catch (SQLException e) {
            System.err.println("Error deleting user: " + e.getMessage());
        }
        return false;
    }
}
