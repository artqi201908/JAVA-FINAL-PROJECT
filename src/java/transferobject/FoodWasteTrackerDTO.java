package java.transferobject;

import java.util.Date;

public class FoodWasteTrackerDTO {

    private int wasteId;
    private int itemId;
    private int wastedQuantity;
    private Date dateReported;

    public FoodWasteTrackerDTO() {
    }

    public int getWasteId() {
        return wasteId;
    }

    public void setWasteId(int wasteId) {
        this.wasteId = wasteId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getWastedQuantity() {
        return wastedQuantity;
    }

    public void setWastedQuantity(int wastedQuantity) {
        this.wastedQuantity = wastedQuantity;
    }

    public Date getDateReported() {
        return dateReported;
    }

    public void setDateReported(Date dateReported) {
        this.dateReported = dateReported;
    }

}
