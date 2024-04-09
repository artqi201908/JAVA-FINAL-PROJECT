package java.transferobject;

import java.util.Date;

/**
 *
 * @author phron
 */
public class UserValidationResult {

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






}
