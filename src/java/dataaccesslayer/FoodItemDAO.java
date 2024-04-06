package dataaccesslayer;


import java.util.Date;
import transferobject.FoodItemDTO;
import java.util.List;

/**
 * Interface for Data Access Object for FoodItems table.
 */
public interface FoodItemDAO {
    List<FoodItemDTO> getAllFoodItems();

    List<FoodItemDTO> getFoodItemsByPage(int page, int itemsPerPage); // For pagination
    List<FoodItemDTO> searchFoodItems(String keyword); // For searching by name or description
    List<FoodItemDTO> getFoodItemsNearExpiration(Date date); // Items near or past expiration
    FoodItemDTO getFoodItemById(int id);
    void addFoodItem(FoodItemDTO foodItem);
    void addFoodItems(List<FoodItemDTO> foodItems); // Bulk add
    void updateFoodItem(FoodItemDTO foodItem);
    void updateFoodItems(List<FoodItemDTO> foodItems); // Bulk update
    void deleteFoodItem(FoodItemDTO foodItem);
    void deleteFoodItems(List<FoodItemDTO> foodItems); // Bulk delete
    // Statistical methods
    int getTotalNumberOfFoodItems();
    int getTotalQuantityOfFoodItems();
}
