package java.dataaccesslayer;

import java.transferobject.OrderDTO;
import java.util.List;

public interface OrderDAO {
    List<OrderDTO> getAllOrders();

    OrderDTO getOrderByOrderId(String Id);

    void addOrder(OrderDTO Order);

    void updateOrder(OrderDTO Order);

    void deleteOrder(OrderDTO Order);

}
