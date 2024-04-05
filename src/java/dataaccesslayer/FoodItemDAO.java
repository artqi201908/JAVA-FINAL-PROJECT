package dataaccesslayer;


import transferobject.FoodItemDTO;
import java.util.List;

/**
 * Interface for Data Access Object for FoodItems table.
 */
public interface FoodItemDAO {
    List<FoodItemDTO> getAllFoodItems();

    FoodItemDTO getFoodItemById(int id);

    void addFoodItem(FoodItemDTO foodItem);

    void updateFoodItem(FoodItemDTO foodItem);

    void deleteFoodItem(FoodItemDTO foodItem);
}
