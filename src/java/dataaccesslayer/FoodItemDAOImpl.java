package dataaccesslayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import transferobject.FoodItemDTO;
import dataaccesslayer.FoodItemDAO;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.tomcat.dbcp.dbcp2.SQLExceptionList;

public class FoodItemDAOImpl implements FoodItemDAO {

    public FoodItemDAOImpl() {
    }

    @Override
    public List<FoodItemDTO> getAllFoodItemsByUserId(int userId) throws SQLExceptionList {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<FoodItemDTO> foodItems = new ArrayList<>();
        String sql = "SELECT ItemID, Name, Quantity, ExpirationDate, Price, DiscountRate, IsForDonation, UserID FROM FoodItems WHERE UserID = ? ORDER BY ItemID";

        try {
            con = DataSource.getInstance().getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, userId);
            rs = pstmt.executeQuery(); // Execute the query

            while (rs.next()) {
                FoodItemDTO item = new FoodItemDTO();
                item.setItemId(rs.getInt("ItemID"));
                item.setName(rs.getString("Name"));
                item.setQuantity(rs.getInt("Quantity"));
                item.setExpirationDate(rs.getDate("ExpirationDate"));
                item.setPrice(rs.getDouble("Price"));
                item.setDiscountRate(rs.getDouble("DiscountRate"));
                item.setForDonation(rs.getBoolean("IsForDonation"));
                item.setUserId(rs.getInt("UserID"));

                foodItems.add(item); // Add the food item to the list
            }
            rs.close(); // Explicitly close ResultSet
        } catch (SQLException ex) {
            Logger.getLogger(FoodItemDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return foodItems;
    }

    @Override
    public FoodItemDTO getFoodItemById(int id) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        FoodItemDTO foodItem = null;

        try {
            con = DataSource.getInstance().getConnection();
            String query = "SELECT * FROM FoodItems WHERE ItemId = ?";
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();

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
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return foodItem;
    }

    @Override
    public void addFoodItem(FoodItemDTO foodItem) {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = DataSource.getInstance().getConnection();
            String sql = "INSERT INTO FoodItems (Name, Quantity, ExpirationDate, Price, DiscountRate, IsForDonation, UserId) VALUES (?, ?, ?, ?, ?, ?, ?)";
            pstmt = con.prepareStatement(sql);
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
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    @Override
    public void updateFoodItem(FoodItemDTO foodItem) {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = DataSource.getInstance().getConnection();
            String sql = "UPDATE FoodItems SET Name = ?, Quantity = ?, ExpirationDate = ?, Price = ?, DiscountRate = ?, IsForDonation = ?, UserId = ? WHERE ItemId = ?";
            pstmt = con.prepareStatement(sql);
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
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    @Override
    public void deleteFoodItem(FoodItemDTO foodItem) {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = DataSource.getInstance().getConnection();
            String sql = "DELETE FROM FoodItems WHERE ItemId = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, foodItem.getItemId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
