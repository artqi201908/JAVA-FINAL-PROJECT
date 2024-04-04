package java.dataaccesslayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import java.transferobject.FoodItemDTO;

public class FoodItemDAOImpl implements FoodItemDAO {

    private DataSource dataSource;

    public FoodItemDAOImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<FoodItemDTO> getAllFoodItems() {
        List<FoodItemDTO> foodItems = new ArrayList<>();
        String sql = "SELECT * FROM FoodItems";

        try (Connection con = dataSource.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                FoodItemDTO foodItem = new FoodItemDTO();
                foodItem.setItemId(rs.getInt("ItemId"));
                foodItem.setName(rs.getString("Name"));
                foodItem.setQuantity(rs.getInt("Quantity"));
                foodItem.setExpirationDate(rs.getDate("ExpirationDate"));
                foodItem.setPrice(rs.getDouble("Price"));
                foodItem.setDiscountRate(rs.getDouble("DiscountRate"));
                foodItem.setForDonation(rs.getBoolean("IsForDonation"));
                foodItem.setUserId(rs.getInt("UserId"));
                foodItems.add(foodItem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return foodItems;
    }

    @Override
    public FoodItemDTO getFoodItemById(int id) {
        FoodItemDTO foodItem = null;
        String sql = "SELECT * FROM FoodItems WHERE ItemId = ?";

        try (Connection con = dataSource.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    foodItem = new FoodItemDTO();
                    foodItem.setItemId(rs.getInt("ItemId"));
                    foodItem.setName(rs.getString("Name"));
                    foodItem.setQuantity(rs.getInt("Quantity"));
                    foodItem.setExpirationDate(rs.getDate("ExpirationDate"));
                    foodItem.setPrice(rs.getDouble("Price"));
                    foodItem.setDiscountRate(rs.getDouble("DiscountRate"));
                    foodItem.setForDonation(rs.getBoolean("IsForDonation"));
                    foodItem.setUserId(rs.getInt("UserId"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return foodItem;
    }

    @Override
    public void addFoodItem(FoodItemDTO foodItem) {
        String sql = "INSERT INTO FoodItems (Name, Quantity, ExpirationDate, Price, DiscountRate, IsForDonation, UserId) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = dataSource.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, foodItem.getName());
            pstmt.setInt(2, foodItem.getQuantity());
            pstmt.setDate(3, new java.sql.Date(foodItem.getExpirationDate().getTime()));
            pstmt.setDouble(4, foodItem.getPrice());
            pstmt.setDouble(5, foodItem.getDiscountRate());
            pstmt.setBoolean(6, foodItem.isForDonation());
            pstmt.setInt(7, foodItem.getUserId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateFoodItem(FoodItemDTO foodItem) {
        String sql = "UPDATE FoodItems SET Name = ?, Quantity = ?, ExpirationDate = ?, Price = ?, DiscountRate = ?, IsForDonation = ?, UserId = ? WHERE ItemId = ?";

        try (Connection con = dataSource.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, foodItem.getName());
            pstmt.setInt(2, foodItem.getQuantity());
            pstmt.setDate(3, new java.sql.Date(foodItem.getExpirationDate().getTime()));
            pstmt.setDouble(4, foodItem.getPrice());
            pstmt.setDouble(5, foodItem.getDiscountRate());
            pstmt.setBoolean(6, foodItem.isForDonation());
            pstmt.setInt(7, foodItem.getUserId());
            pstmt.setInt(8, foodItem.getItemId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteFoodItem(FoodItemDTO foodItem) {
        String sql = "DELETE FROM FoodItems WHERE ItemId = ?";

        try (Connection con = dataSource.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setInt(1, foodItem.getItemId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
