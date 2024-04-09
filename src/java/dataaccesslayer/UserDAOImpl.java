/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package java.dataaccesslayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import transferobject.UserDTO;
import java.util.ArrayList;
import java.util.List;
import transferobject.UserValidationResult;

/**
 *
 * @author phron
 */
public class UserDAOImpl implements UserDAO {

    Connection con = null;
    PreparedStatement pstmt = null;

    public UserDAOImpl() {
    }

    @Override
    public List<UserDTO> getAllUsers() {
        ResultSet rs = null;
        ArrayList<UserDTO> users = null;

        try {
            con = DataSource.getInstance().getConnection();

            String query = "SELECT * FROM Users ORDER BY UserID";
            pstmt = con.prepareStatement(query);

            rs = pstmt.executeQuery();
            users = new ArrayList<>();
            while (rs.next()) {
                UserDTO user = new UserDTO();
                int userId = rs.getInt("UserID");
                user.setUserID(userId);

                String name = rs.getString("Name");
                user.setName(name);

                user.setEmail(rs.getString("Email"));
                user.setUserType(rs.getInt("Usertype"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }

                if (pstmt != null) {
                    pstmt.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return users;
    }

    @Override
    public Integer getUserTypeByUserName(String username) {
        int userType = 0;
        try {
            Connection con = DataSource.getInstance().getConnection();
            PreparedStatement preparedStatement = con.prepareStatement("SELECT UserType FROM User WHERE Name = ?");
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            userType = 4;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close connections and resources
            // Handle exceptions if necessary
        }
        return userType;
    }

    @Override
    public boolean addUser(UserDTO user) {
        Connection con = null;
        PreparedStatement preparedStatement = null;
        boolean success = false;

        try {
            con = DataSource.getInstance().getConnection();
            preparedStatement = con.prepareStatement("INSERT INTO Users (Email, Name, Password, UserType) VALUES (?, ?, ?, ?)");
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setInt(4, user.getUserType());
            int rowsAffected = preparedStatement.executeUpdate();
            success = rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return success;
    }

    @Override
    public void updateUser(UserDTO user) {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = DataSource.getInstance().getConnection();
            pstmt = con.prepareStatement(
                    "UPDATE Users SET Name = ?, "
                    + "Email = ?, Password=?, UserType=? WHERE UserID = ?");
            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getEmail());
            pstmt.setString(3, user.getPassword());
            pstmt.setInt(4, user.getUserID());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
//            try {
//                if (pstmt != null) {
//                    pstmt.close();
//                }
//
//                if (con != null) {
//                    con.close();
//                }
//            } catch (SQLException ex) {
//                System.out.println(ex.getMessage());
//            }
        }
    }

    @Override
    public void deleteUser(UserDTO user) {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = DataSource.getInstance().getConnection();
            pstmt = con.prepareStatement(
                    "DELETE FROM Users WHERE UserID = ?");
            pstmt.setInt(1, user.getUserID());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
//            try {
//                if (pstmt != null) {
//                    pstmt.close();
//                }
//
//                if (con != null) {
//                    con.close();
//                }
//            } catch (SQLException ex) {
//                System.out.println(ex.getMessage());
//            }
        }
    }

    @Override
    public Integer validate(String username, String password) {
        int id = 0;
        try (Connection con = DataSource.getInstance().getConnection(); PreparedStatement pstmt = con.prepareStatement("SELECT UserID FROM Users WHERE Name = ? AND Password = ?")) {
            pstmt.setString(1, username);
            pstmt.setString(2, password); // Consider using hashed passwords
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    id = rs.getInt("UserID"); // Return the user ID
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Log or handle the exception appropriately
        }
        return id; // Return null or a negative value if credentials are invalid
    }

    @Override
    public UserValidationResult validateUserAndGetDetails(String username, String password) {
        UserValidationResult result = null;
        try (Connection con = DataSource.getInstance().getConnection(); PreparedStatement pstmt = con.prepareStatement("SELECT UserID, UserType FROM Users WHERE Name = ? AND Password = ?")) {

            pstmt.setString(1, username);
            pstmt.setString(2, password);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Integer userId = rs.getInt("UserID");
                    Integer userType = rs.getInt("UserType");
                    result = new UserValidationResult(userId, userType);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Consider using a logger
        }
        return result;
    }

}
