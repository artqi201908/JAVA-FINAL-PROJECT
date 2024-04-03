/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataaccesslayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import transferobject.UserDTO;

/**
 *
 * @author phron
 */
public class UserDAOImpl implements UserDAO {
    
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
            users = new ArrayList<UserDTO>();
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
                int role =rs.getInt("UserType");

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
    public void addUser(UserDTO user) {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = DataSource.getConnection();

            pstmt = con.prepareStatement(
                    "INSERT INTO Users (Name,Email,Password,UserType) "
                    + "VALUES(?, ?,?,?)");
            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getEmail());
            pstmt.setString(3, user.getPassword());
            pstmt.setInt(4, user.getUserType());
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
            pstmt.setInt(4, user.getUserType());
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
     public boolean validate(String username, String password) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        boolean userExists = false;
    try {
        con = DataSource.getConnection();
        String query = "SELECT COUNT(*) FROM Users WHERE Username = ? AND Password = ?";
        pstmt = con.prepareStatement(query);
        
        pstmt.setString(1, username);
        pstmt.setString(2, password);

        rs = pstmt.executeQuery();

        if (rs.next() && rs.getInt(1) > 0) {
            userExists = true;
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        // Ensure resources are closed in finally block to avoid resource leaks
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
            ex.printStackTrace();
        }
    }
    return userExists;

     }

}
