package dataaccesslayer;

import java.util.Date;
import transferobject.OrderDTO;
import java.util.List;

public interface OrderDAO {
    List<OrderDTO> getAllOrders();
    
    // New method to get orders by a specific status
    List<OrderDTO> getOrdersByStatus(String status);

    // New method to get orders within a specific date range
    List<OrderDTO> getOrdersByDateRange(Date startDate, Date endDate);

    // New method to get orders for a specific item
    List<OrderDTO> getOrdersByItemId(int itemId);

    OrderDTO getOrderByOrderId(String Id);

    void addOrder(OrderDTO Order);

    void updateOrder(OrderDTO Order);

    void deleteOrder(OrderDTO Order);

}
