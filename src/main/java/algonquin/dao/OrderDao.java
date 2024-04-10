package algonquin.dao;

import algonquin.dto.Order;
import algonquin.businesslayer.ValidationException;

import java.util.List;

/**
 * @author 
 * @since 3/30/2024
 */
public interface OrderDao {
    void create(Order order) throws ValidationException;
    void update(Order order) throws ValidationException;
    List<OrderDTO> findAll(Long userId);
    List<OrderDTO> findForRetailer(Long userId);
    Order findById(Long orderId);
}
