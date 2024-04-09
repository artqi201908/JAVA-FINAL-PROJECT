package dataaccesslayer;

import transferobject.FoodItemDTO;
import java.util.List;
import org.apache.tomcat.dbcp.dbcp2.SQLExceptionList;

/**
 * Interface for Data Access Object for FoodItems table.
 */
public interface FoodItemDAO {

    List<FoodItemDTO> getAllFoodItemsByUserId(int userId) throws SQLExceptionList;

    FoodItemDTO getFoodItemById(int id);

    void addFoodItem(FoodItemDTO foodItem);

    void updateFoodItem(FoodItemDTO foodItem);

    void deleteFoodItem(FoodItemDTO foodItem);
}
