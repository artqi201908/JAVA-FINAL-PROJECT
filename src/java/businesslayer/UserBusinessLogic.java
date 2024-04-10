/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package java.businesslayer;

import java.dataaccesslayer.UserDAOImpl;
import java.transferobject.UserDTO;
import java.util.List;
import transferobject.UserDTO;
import dataaccesslayer.*;
import transferobject.UserValidationResult;

/**
 *
 * @author phron
 */
public class UserBusinessLogic {

    private UserDAOImpl userDao = null;

    public UserBusinessLogic() {
        this.userDao = new UserDAOImpl();
    }

    public UserDTO findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    public UserDTO findByEmail(String email) {
        return userDao.findByEmail(email);
    }

    public void create(UserDTO user) throws ValidationException.ValidationException {
        validateUser(user);
        if (findByUsername(user.getUsername()) != null) {
            throw new ValidationException.ValidationException("Username is exist.");
        }
        if (findByEmail(user.getEmail()) != null) {
            throw new ValidationException.ValidationException("Email is exist.");
        }
        userDao.create(user);
    }

    public void update(UserDTO user) throws ValidationException.ValidationException {
        validateUser(user);
        userDao.update(user);
    }

    private void validateUser(UserDTO user) throws ValidationException.ValidationException {
        ValidateItem.validateString(user.getUsername(), "Username", 30);
        ValidateItem.validateString(user.getPassword(), "Password", 30);
        ValidateItem.validateString(user.getEmail(), "Email", 30);
    }


    public UserDTO findById(Long userId) {
        return userDao.findById(userId);
    }

    public List<UserDTO> findSubscribedUsers() {
        return userDao.findSubscribedUsers();
    }
}

}
