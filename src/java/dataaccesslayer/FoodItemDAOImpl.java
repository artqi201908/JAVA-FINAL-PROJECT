package dataaccesslayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.transferobject.FoodItemDTO;

public class FoodItemDAOImpl implements FoodItemDAO {


    public FoodItemDAOImpl() {
    }

    @Override
    public List<FoodItemDTO> getAllFoodItems() {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<FoodItemDTO> foodItems = new ArrayList<>();

        try {
            con = dataaccesslayer.DataSource.getConnection();
            String query = "SELECT * FROM FoodItems";
            pstmt = con.prepareStatement(query);
            rs = pstmt.executeQuery();

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
        return foodItems;
    }

    @Override
    public FoodItemDTO getFoodItemById(int id) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        FoodItemDTO foodItem = null;

        try {
            con = DataSource.getConnection();
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
            con = DataSource.getConnection();
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
            con = DataSource.getConnection();
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
            con = DataSource.getConnection();
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
