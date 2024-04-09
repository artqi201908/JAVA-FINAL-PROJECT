package java.transferobject;

public class OrderDTO extends UserValidationResult {

    private Long id;
    private Long itemId;
    private Long quantity;
    private String address;
    private Long statusId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getStatusId() {
        return statusId;
    }

    public void setStatusId(StatusOrder statusId) {
        this.statusId = statusId;
    }
}
