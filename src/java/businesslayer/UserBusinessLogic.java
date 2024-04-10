package java.businesslayer;


import java.transferobject.UserDTO;
import java.util.List;
import java.transferobject.UserDTO;
import java.dataaccesslayer.*;
import java.transferobject.UserValidationResult;


public class UserBusinessLogic {
    public UserBusinessLogic() {
    }

    public User findByUsername(String username) {
        return null;
    }

    public User findByEmail(String email) {
        return null;
    }

    public void create(User user) throws ValidateException.ValidationException {
    }

    public void update(User user) throws ValidateException.ValidationException {
    }

    private void validateUser(User user) throws ValidateException.ValidationException {
    }

    public User findById(Long userId) {
        return null;
    }

    public List<User> findSubscribedUsers() {
        return null;
    }
}
