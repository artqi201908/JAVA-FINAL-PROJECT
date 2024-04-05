/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataaccesslayer;

import transferobject.UserDTO;
import java.util.List;


/**
 *
 * @author phron
 */
public interface UserDAO {
    List<UserDTO> getAllUsers();
    

    UserDTO getUserByUserName(String name);

    void addUser(UserDTO User);

    void updateUser(UserDTO user);

    void deleteUser(UserDTO user);
    public boolean validate(String username, String password);
}
