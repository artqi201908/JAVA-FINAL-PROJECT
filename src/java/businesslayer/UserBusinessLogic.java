
package java.businesslayer;

import java.dataaccesslayer.UserDAOImpl;
import java.transferobject.UserDTO;
import java.util.List;


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

    public void create(UserDTO user) throws ValidateException.ValidationException {
        validateUser(user);
        if (findByUsername(user.getUsername()) != null) {
            throw new ValidateException.ValidationException("Username is exist.");
        }
        if (findByEmail(user.getEmail()) != null) {
            throw new ValidateException.ValidationException("Email is exist.");
        }
        userDao.create(user);
    }

    public void update(UserDTO user) throws ValidateException.ValidationException {
        validateUser(user);
        userDao.update(user);
    }

    private void validateUser(UserDTO user) throws ValidateException.ValidationException {
        ValidateItem.validateString(user.getUsername(), "Username", 30);
        ValidateItem.validateString(user.getPassword(), "Password", 30);
        ValidateItem.validateString(user.getEmail(), "Email", 30);
    }


    public UserDTO findById(Long userId) {
        return userDao.findById(userId);
    }



}


