package java.businesslayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataFormat {

    private Connection connection = null;
    private String url = "jdbc:mysql://localhost:3306/fwrp?useSSL=false"; // &allowPublicKeyRetrieval=true
    private String username = "root";
    private String password = "123456";

    public DataFormat() {
    }


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
