/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */



import dataaccesslayer.DataSource;
import dataaccesslayer.UserDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import transferobject.UserDTO;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author phron
 */
public class UserDAOImpl implements UserDAO {

    public UserDAOImpl() {
    }

    @Override
    public List<UserDTO> getAllUsers() {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList<UserDTO> users = null;

        try {
            con = DataSource.getConnection();

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
    public UserDTO getUserByUserName(String name) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        UserDTO user = new UserDTO();
        try {
            con = DataSource.getConnection();
            pstmt = con.prepareStatement(
                    "SELECT * FROM Users WHERE Name = ?");
            pstmt.setString(1, name);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                int userId = rs.getInt("UserID");
                String userName = rs.getString("Name");
                String email = rs.getString("Email");
                int role = rs.getInt("UserType");

                user.setUserID(userId);
                user.setName(userName);
                user.setEmail(email);
                user.setUserType(role);

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
//            try {
//                if (rs != null) {
//                    rs.close();
//                }
//
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

        return user;
    }

    @Override
    public boolean addUser(UserDTO user) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        boolean success = false;

        try {
            connection = DataSource.getConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO Users (Email, Name, Password, UserType) VALUES (?, ?, ?, ?)");
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
            con = DataSource.getConnection();
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
            con = DataSource.getConnection();
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
        Connection con = null;
        PreparedStatement pstmt = null;
        boolean userExists = false;
        try {
            con = DataSource.getConnection();
            pstmt = con.prepareStatement("SELECT UserID FROM Users WHERE Username = ? AND Password = ?");
            pstmt.setString(1, username);
            pstmt.setString(2, password); // Consider using hashed passwords
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("UserID"); // Return the user ID
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Log or handle the exception appropriately
        }
        return null;
    }

    @Override
    public boolean emailExists(String email) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = DataSource.getConnection();
            pstmt = con.prepareStatement("SELECT Email FROM Users WHERE Email = ?");
            pstmt.setString(1, email);
            rs = pstmt.executeQuery();
            return rs.next(); // If there's at least one row, the email exists
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(rs);
            close(pstmt);
            close(con);
        }
        return false;
    }

    private void close(AutoCloseable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception e) {
                // Log the exception or handle it
                System.out.println("Failed to close resource: " + e.getMessage());
            }
        }
    }
}
