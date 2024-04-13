package algonquin.dto;


import java.util.Date;

/**
 * @author 
 * @since 3/30/2024
 */
public abstract class BaseEntity {
    private Long createUserId;
    private Date createDate = new Date();
    private Long modUserId;
    private Date modDate;

    public Long getCreateUserId() {
        return this.createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public Long getModUserId() {
        return this.modUserId;
    }

    public void setModUserId(Long modUserId) {
        this.modUserId = modUserId;
    }

    public Date getCreateDate() {
        return this.createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModDate() {
        return this.modDate;
    }

    public void setModDate(Date modDate) {
        this.modDate = modDate;
    }
}
