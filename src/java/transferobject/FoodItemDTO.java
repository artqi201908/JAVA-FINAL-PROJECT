package java.transferobject;

import java.math.BigDecimal;
import java.util.Date;

public class FoodItemDTO {
    private int itemId;
    private String name;
    private int quantity;
    private Date expirationDate;
    private BigDecimal price;
    private BigDecimal discountRate;
    private boolean isForDonation;
    private int userId;


    public FoodItemDTO() {
    }
    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(BigDecimal discountRate) {
        this.discountRate = discountRate;
    }

    public boolean isForDonation() {
        return isForDonation;
    }

    public void setForDonation(boolean forDonation) {
        isForDonation = forDonation;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }



}
