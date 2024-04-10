package algonquin.businesslayer;

import algonquin.constant.UserType;
import algonquin.dao.ItemDao;
import algonquin.dao.impl.ItemDaoImpl;
import algonquin.dto.Item;
import algonquin.dto.User;

import java.util.ArrayList;
import java.util.List;

public class ItemService {

    private ItemDao itemDao = null;

    public ItemService() {
        this.itemDao = new ItemDaoImpl();
    }


    public Item findById(Long itemId) {
        return itemDao.findById(itemId);
    }

    public Item findById(Long userId, Long itemId) {
        return itemDao.findById(userId, itemId);
    }

    public List<Item> findAll(Long userId) {
        return itemDao.findAll(userId);
    }

    public List<Item> findSurplus(Long userId) {
        return itemDao.findSurplus(userId);
    }

    public List<Item> findAllForConsumerToBuy() {
        return itemDao.findAllForConsumerToBuy();
    }

    public List<Item> findAllForCharityToClaim() {
        return itemDao.findAllForCharityToClaim();
    }

    public List<Item> findByUser(User user) {
        List<Item> items = new ArrayList<>();
        if (user.getTypeId() == UserType.RETAILER) {
            items = findAll(user.getId());
        } else if (user.getTypeId() == UserType.CONSUMER) {
            items = findAllForConsumerToBuy();
        } else if (user.getTypeId() == UserType.CHARITABLE_ORGANIZATION) {
            items = findAllForCharityToClaim();
        }
        return items;
    }

    public void create(Item item) throws ValidationException {
        validateItem(item);
        itemDao.create(item);
    }

    public void update(Item item) throws ValidationException {
        validateItem(item);
        itemDao.update(item);
    }

    public void delete(Long itemId) throws ValidationException {
        itemDao.delete(itemId);
    }

    private void validateItem(Item item) throws ValidationException {
        ValidateUtil.validateString(item.getTitle(), "Title", 255);
        ValidateUtil.validateLong(item.getQuantity(), "Quantity");
        ValidateUtil.validateDate(item.getExpirationDate(), "ExpirationDate");
    }

}
