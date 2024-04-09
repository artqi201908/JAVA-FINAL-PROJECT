/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package java.dataaccesslayer;

import java.util.List;
import transferobject.UserDTO;

/**
 *
 * @author phron
 */
public interface UserDAO {

    List<UserDTO> getAllUsers();

    Integer getUserTypeByUserID(int userID);

    boolean addUser(UserDTO User);

    void updateUser(UserDTO user);

    void deleteUser(UserDTO user);

    public Integer validate(String username, String password);
}
