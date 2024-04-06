/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataaccesslayer;

import dataaccesslayer.FoodItemDAO;
import transferobject.FoodItemDTO;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of FoodItemDAO for managing food items in memory.
 * 
 * @author artqi
 */
public class FoodItemDAOImpl implements FoodItemDAO {
    
    // Example in-memory store
    private List<FoodItemDTO> foodItems = new ArrayList<>();

    public FoodItemDAOImpl() {
        // Initialize with some dummy data
        foodItems.add(new FoodItemDTO(1, "Rice", 100, null, 1.0, 0, true, "Grains", "Premium quality rice"));
        // Add more items as needed
    }

    @Override
    public List<FoodItemDTO> getAllFoodItems() {
        return new ArrayList<>(foodItems); // Return a copy of the list to avoid external modifications
    }

    @Override
    public FoodItemDTO getFoodItemById(int id) {
        return foodItems.stream()
                .filter(item -> item.getItemId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public void addFoodItem(FoodItemDTO foodItem) {
        foodItems.add(foodItem);
    }

    @Override
    public void updateFoodItem(FoodItemDTO foodItem) {
        // Find index of the item to be updated
        int index = -1;
        for (int i = 0; i < foodItems.size(); i++) {
            if (foodItems.get(i).getItemId() == foodItem.getItemId()) {
                index = i;
                break;
            }
        }
        // Replace item at the found index
        if (index != -1) {
            foodItems.set(index, foodItem);
        }
    }

    @Override
    public void deleteFoodItem(FoodItemDTO foodItem) {
        foodItems.removeIf(item -> item.getItemId() == foodItem.getItemId());
    }
    
}

