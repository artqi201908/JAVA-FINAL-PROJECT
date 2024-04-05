/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataaccesslayer;

import java.sql.SQLException;
import java.util.List;
import transferobject.UserDTO;

/**
 *
 * @author phron
 */
public interface UserDAO {
    List<UserDTO> getAllUsers();

    UserDTO getUserByUserName(String name);

    boolean addUser(UserDTO User);

    void updateUser(UserDTO user);

    void deleteUser(UserDTO user);
    
    public Integer validate(String username, String password);
    
    public boolean emailExists(String email);
}
