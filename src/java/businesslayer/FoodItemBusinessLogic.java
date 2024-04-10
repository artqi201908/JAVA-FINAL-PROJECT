/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package java.businesslayer;


import java.dataaccesslayer.FoodItemDAO;
import java.dataaccesslayer.FoodItemDAOImpl;
import java.transferobject.FoodItemDTO;
import java.transferobject.UserDTO;
import java.util.ArrayList;
import java.util.List;
import org.apache.tomcat.dbcp.dbcp2.SQLExceptionList;

/**
 *
 * @author phron
 */
public class FoodItemBusinessLogic {

    private FoodItemDAO itemDao = null;

    public FoodItemBusinessLogic() {
        this.itemDao = new FoodItemDAOImpl();
    }


    public FoodItemDTO findById(Long itemId) {
        return itemDao.findById(itemId);
    }

    public FoodItemDTO findById(Long userId, Long itemId) {
        return itemDao.findById(userId, itemId);
    }

    public List<FoodItemDTO> findAll(Long userId) {
        return itemDao.findAll(userId);
    }

    public List<FoodItemDTO> findSurplus(Long userId) {
        return itemDao.findSurplus(userId);
    }

    public List<FoodItemDTO> findAllForConsumerToBuy() {
        return itemDao.findAllForConsumerToBuy();
    }

    public List<FoodItemDTO> findAllForCharityToClaim() {
        return itemDao.findAllForCharityToClaim();
    }

    public List<FoodItemDTO> findByUser(UserDTO user) {
        List<FoodItemDTO> items = new ArrayList<>();
        if (user.getTypeId() == UserType.RETAILER) {
            items = findAll(user.getId());
        } else if (user.getTypeId() == UserType.CONSUMER) {
            items = findAllForConsumerToBuy();
        } else if (user.getTypeId() == UserType.CHARITABLE_ORGANIZATION) {
            items = findAllForCharityToClaim();
        }
        return items;
    }

    public void create(FoodItemDTO item) throws ValidateException.ValidationException {
        validateItem(item);
        itemDao.addFood(item);
    }

    public void update(FoodItemDTO item) throws ValidateException.ValidationException {
        validateItem(item);
        itemDao.update(item);
    }

    public void delete(Long itemId) throws ValidateException.ValidationException {
        itemDao.delete(itemId);
    }

    private void validateItem(FoodItemDTO item) throws ValidateException.ValidationException {
        ValidateItem.validateString(item.getTitle(), "Title", 255);
        ValidateItem.validateLong(item.getQuantity(), "Quantity");
        ValidateItem.validateDate(item.getExpirationDate(), "ExpirationDate");
    }
}
