package algonquin.dao;

import algonquin.dto.Item;
import algonquin.businesslayer.ValidationException;

import java.util.List;

/**
 * @author
 * @since 3/30/2024
 */
public interface ItemDao {
    void create(Item item) throws ValidationException;
    void update(Item item) throws ValidationException;
    void delete(Long itemId) throws ValidationException;
    List<Item> findAll(Long userId);
    List<Item> findSurplus(Long userId);
    List<Item> findAllForConsumerToBuy();
    List<Item> findAllForCharityToClaim();

    Item findById(Long itemId);
    Item findById(Long userId, Long itemId);
}
