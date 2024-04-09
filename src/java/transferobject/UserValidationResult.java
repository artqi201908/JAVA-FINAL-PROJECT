/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package transferobject;

/**
 *
 * @author phron
 */
public class UserValidationResult {

    private Integer userId;
    private Integer userType;

    public UserValidationResult(Integer userId, Integer userType) {
        this.userId = userId;
        this.userType = userType;
    }

    // Getters
    public Integer getUserId() {
        return userId;
    }

    public Integer getUserType() {
        return userType;
    }
}
