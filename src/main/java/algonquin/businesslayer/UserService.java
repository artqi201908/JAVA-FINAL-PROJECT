package algonquin.businesslayer;

import algonquin.dao.UserDao;
import algonquin.dao.impl.UserDaoImpl;
import algonquin.dto.User;

import java.util.List;

public class UserService {

    private UserDao userDao = null;

    public UserService() {
        this.userDao = new UserDaoImpl();
    }

    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    public User findByEmail(String email) {
        return userDao.findByEmail(email);
    }

    public void create(User user) throws ValidationException {
        validateUser(user);
        if (findByUsername(user.getUsername()) != null) {
            throw new ValidationException("Username is exist.");
        }
        if (findByEmail(user.getEmail()) != null) {
            throw new ValidationException("Email is exist.");
        }
        userDao.create(user);
    }

    public void update(User user) throws ValidationException {
        validateUser(user);
        userDao.update(user);
    }

    private void validateUser(User user) throws ValidationException {
        ValidateUtil.validateString(user.getUsername(), "Username", 30);
        ValidateUtil.validateString(user.getPassword(), "Password", 30);
        ValidateUtil.validateString(user.getEmail(), "Email", 30);
    }


    public User findById(Long userId) {
        return userDao.findById(userId);
    }

    public List<User> findSubscribedUsers() {
        return userDao.findSubscribedUsers();
    }
}
