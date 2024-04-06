/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package businesslayer;



import java.sql.SQLException;

import java.util.List;
import transferobject.UserDTO;


/**
 *
 * @author phron
 */
public class UserBusinessLogic {

    //private UserDAOImpl usersDao = null;

    public UserBusinessLogic() {
        usersDao = new UserDAOImpl();
    }

    public List<UserDTO> getAllUsers() throws SQLException {
        return usersDao.getAllUsers();
    }

    public Integer validateCredentials(String username, String password) {
        return usersDao.validate(username, password);
    }

    public boolean addUser(UserDTO user) {
           return usersDao.addUser(user);   
}}
