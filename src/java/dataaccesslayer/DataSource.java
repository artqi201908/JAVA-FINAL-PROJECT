/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataaccesslayer;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author phron
 */
public class DataSource {

    private static Connection connection = null;
    private static String url = "jdbc:mysql://localhost:3306/finalproject?useSSL=false&allowPublicKeyRetrieval=true";
    private static String username = "root";
    private static String password = "Mklahoilmj@1";

    public DataSource() {
    }

    /*
 * Only use one connection for this application, prevent memory leaks.
     */
    public static Connection getConnection() throws SQLException {
        try {
            if (connection != null) {
                System.out.println("Cannot create new connection, one exists already");
            } else {
                connection = DriverManager.getConnection(url, username, password);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
        return connection;
    }
}

