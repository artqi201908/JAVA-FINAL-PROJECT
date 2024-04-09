/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package java.dataaccesslayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author phron
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSource {

    private Connection connection = null;
    private String url = "jdbc:mysql://localhost:3306/fwrp?useSSL=false"; // &allowPublicKeyRetrieval=true
    private String username = "root";
    private String password = "Mklahoilmj@1";

    public DataSource() {

    }

    /*
     * Only use one connection for this application, prevent memory leaks.
     */
    public Connection createConnection() throws SQLException {

        try {
            if (connection != null) {
                System.out.println("Cannot create new connection, one exists already");
            } else {
                // manually register mysql driver
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                connection = DriverManager.getConnection(url, username, password);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
        return connection;
    }
}