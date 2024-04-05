/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package businesslayer;


import java.dataaccesslayer.UserDAOImpl;
import java.sql.SQLException;
import java.transferobject.UserDTO;
import java.util.List;


/**
 *
 * @author phron
 */
public class UserBusinessLogic {
     private UserDAOImpl usersDao = null;

    public UserBusinessLogic() {
        usersDao = new UserDAOImpl();
    }

    public List<UserDTO> getAllUsers() throws SQLException {
        return usersDao.getAllUsers();
    }

    public void addUser(UserDTO user) {
        usersDao.addUser(user);
    }
    public boolean validateCredentials(String username, String password) {
        return usersDao.validate(username, password);
    }
}
