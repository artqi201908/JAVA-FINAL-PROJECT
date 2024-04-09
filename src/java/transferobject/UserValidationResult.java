<<<<<<< HEAD
package java.transferobject;

import java.util.Date;
=======
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package transferobject;
>>>>>>> origin/main

/**
 *
 * @author phron
 */
public class UserValidationResult {

<<<<<<< HEAD
    private Long createUserId;
    private Date createDate = new Date();
    private Long modUserId;
    private Date modDate;

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Long getModUserId() {
        return modUserId;
    }

    public void setModUserId(Long modUserId) {
        this.modUserId = modUserId;
    }

    public Date getModDate() {
        return modDate;
    }

    public void setModDate(Date modDate) {
        this.modDate = modDate;
    }






=======
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
>>>>>>> origin/main
}
