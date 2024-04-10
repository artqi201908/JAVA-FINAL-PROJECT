/* File: SwingMVCDemo.java
 * Author: Stanley Pieda
 * Date: 2015
 * Description: Demonstration of DAO Design Pattern, MVC Design Pattern
 * References:
 * Ram N. (2013).  Data Access Object Design Pattern or DAO Pattern [blog] Retrieved from
 * http://ramj2ee.blogspot.in/2013/08/data-access-object-design-pattern-or.html
 */
package algonquin.dao;

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
