package java.transferobject;

import java.math.BigDecimal;
import java.util.Date;

public class OrderDTO {

    private Long id;
    private String title;
    private String description;
    private Long quantity;
    private Date expirationDate;
    private Boolean isDonate;
    private BigDecimal price;
    private Long discount;
    private Boolean isSurplus;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Boolean getDonate() {
        return isDonate;
    }

    public void setDonate(Boolean donate) {
        isDonate = donate;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getDiscount() {
        return discount;
    }

    public void setDiscount(Long discount) {
        this.discount = discount;
    }

    public Boolean getSurplus() {
        return isSurplus;
    }

    public void setSurplus(Boolean surplus) {
        isSurplus = surplus;
    }





}
