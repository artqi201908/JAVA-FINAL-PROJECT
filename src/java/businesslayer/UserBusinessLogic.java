package java.businesslayer;

import javax.xml.registry.infomodel.User;
import java.util.List;

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
