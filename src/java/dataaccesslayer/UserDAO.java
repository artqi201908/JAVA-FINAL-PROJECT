/* Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
        * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
        */
        package java.dataaccesslayer;

import java.sql.SQLException;
import java.util.List;
import java.transferobject.UserValidationResult;
import java.transferobject.UserDTO;
import java.businesslayer.ValidateException;


/**
 * @author fengqi
 * @since 3/30/2024
 */

public interface UserDAO {
    UserDTO findById(Long id);
    UserDTO findByUsername(String username);
    UserDTO findByEmail(String email);
    List<UserDTO> findSubscribedUsers();
    void create(UserDTO user) throws  ValidateException.ValidationException;
    void update(UserDTO user) throws  ValidateException.ValidationException;

}