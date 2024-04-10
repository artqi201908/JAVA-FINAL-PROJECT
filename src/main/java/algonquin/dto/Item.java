package algonquin.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author 
 * @since 3/30/2024
 */
public class Item extends BaseEntity{
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
        return this.id;
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

    public Boolean getIsDonate() {
        return isDonate;
    }

    public void setIsDonate(Boolean denote) {
        isDonate = denote;
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

    public void setIsSurplus(Boolean isSurplus) {
        this.isSurplus = isSurplus;
    }

    public Boolean getIsSurplus() {
        return isSurplus;
    }
}
