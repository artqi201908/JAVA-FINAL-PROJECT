package algonquin.dao;

import java.util.List;

import algonquin.dto.User;
import algonquin.businesslayer.ValidationException;

/**
 * @author 
 * @since 3/30/2024
 */
public interface UserDao {
    User findById(Long id);
    User findByUsername(String username);
    User findByEmail(String email);
    List<User> findSubscribedUsers();
    void create(User user) throws ValidationException;
    void update(User user) throws ValidationException;

}
