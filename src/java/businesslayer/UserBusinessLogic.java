/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package java.businesslayer;

import java.sql.SQLException;
import java.util.List;
import transferobject.UserDTO;
import dataaccesslayer.*;
import transferobject.UserValidationResult;

/**
 *
 * @author phron
 */
public class UserBusinessLogic {

    private dataaccesslayer.UserDAO usersDao = null;

    public UserBusinessLogic() {
        usersDao = new dataaccesslayer.UserDAOImpl();
    }

    public List<UserDTO> getAllUsers() throws SQLException {
        return usersDao.getAllUsers();
    }

    public Integer validateCredentialsAndGetUserId(String username, String password) {
        return usersDao.validate(username, password);
    }

    public boolean addUser(UserDTO user) {
        return usersDao.addUser(user);
    }

    public Integer getUserTypeByUsername(String username) {
        return usersDao.getUserTypeByUserName(username);
    }

    public UserValidationResult validateUserAndGetDetails(String username, String password) {
        return usersDao.validateUserAndGetDetails(username, password);
    }

}
