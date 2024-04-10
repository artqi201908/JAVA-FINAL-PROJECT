package java.dataaccesslayer;


import java.businesslayer.ValidationException;
import java.transferobject.FoodItemDTO;
import java.util.List;

/**
 * Interface for Data Access Object for FoodItems table.
 */
public interface FoodItemDAO {

    void addFood(FoodItemDTO item) throws ValidationException.ValidationException;
    void update(FoodItemDTO item) throws ValidationException.ValidationException;
    void delete(Long itemId) throws ValidationException.ValidationException;
    List<FoodItemDTO> findAll(Long userId);
    List<FoodItemDTO> findSurplus(Long userId);
    List<FoodItemDTO> findAllForConsumerToBuy();
    List<FoodItemDTO> findAllForCharityToClaim();

    FoodItemDTO findById(Long itemId);
    FoodItemDTO findById(Long userId, Long itemId);
}
