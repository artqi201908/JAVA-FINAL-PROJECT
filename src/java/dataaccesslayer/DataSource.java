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
public class DataSource {

    private static DataSource instance;
    private static Connection connection;

    private String serverUrl = "jdbc:mysql://localhost:3306/finalproject";
    private String userString = "root";
    private String passwordString = "Mklahoilmj@1";
    private String driverString = "com.mysql.cj.jdbc.Driver";

    // Private constructor to prevent instantiation from outside the class
    private DataSource() {
        try {

            // Load the JDBC driver
            Class.forName(driverString);
            // Create the database connection
            connection = DriverManager.getConnection(serverUrl, userString, passwordString);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            // Handle the exception accordingly (logging, throwing a custom exception, etc.)

        }
    }

    // Method to provide a single instance of DBConnection (Singleton pattern)
    public static synchronized DataSource getInstance() {
        if (instance == null) {
            instance = new DataSource();
        }
        return instance;
    }

    // Method to get the database connection
    public Connection getConnection() {
        return connection;
    }
}
