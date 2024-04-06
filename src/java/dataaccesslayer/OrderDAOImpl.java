/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataaccesslayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import transferobject.OrderDTO;
/**
 * Implementation of OrderDAO for managing food items history order in memory.
 * 
 * @author artqi
 */
public class OrderDAOImpl implements OrderDAO {
    
    private Connection connection;

    public OrderDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<OrderDTO> getAllOrders() {
        List<OrderDTO> orders = new ArrayList<>();
        String sql = "SELECT * FROM orders";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                orders.add(extractOrderFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    // Implement the rest of the methods in OrderDAO interface similarly
    
    // Helper method to extract order information from ResultSet
    private OrderDTO extractOrderFromResultSet(ResultSet rs) throws SQLException {
        OrderDTO order = new OrderDTO();
        order.setId(rs.getInt("id"));
        order.setItemId(rs.getInt("itemId"));
        // Assuming the item name is also stored in the orders table for simplicity
        order.setItemName(rs.getString("itemName"));
        order.setQuantity(rs.getDouble("quantity"));
        order.setAddress(rs.getString("address"));
        order.setOrderDate(rs.getDate("orderDate"));
        order.setStatus(rs.getString("status"));
        return order;
    }
    
    // Example implementation of addOrder method
    @Override
    public void addOrder(OrderDTO order) {
        String sql = "INSERT INTO orders (itemId, itemName, quantity, address, orderDate, status) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, order.getItemId());
            statement.setString(2, order.getItemName());
            statement.setDouble(3, order.getQuantity());
            statement.setString(4, order.getAddress());
            // Assuming your database supports java.sql.Date for orderDate
            statement.setDate(5, new java.sql.Date(order.getOrderDate().getTime()));
            statement.setString(6, order.getStatus());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // Implement other methods (updateOrder, deleteOrder, getOrdersByStatus, etc.) similarly.
}
