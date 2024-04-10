package algonquin.dao;

import java.sql.SQLException;

import algonquin.dao.impl.UserDaoImpl;
import algonquin.dto.User;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author 
 * @since 3/30/2024
 */
public class UserDaoTest {
    UserDao userDao = new UserDaoImpl();

    @Test
    public void test_findByUsername() throws SQLException {
        User user = userDao.findByUsername("retailer1");
        Assert.assertNotNull(user);
    }
}
