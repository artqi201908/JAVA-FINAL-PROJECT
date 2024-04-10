package java.dataaccesslayer;

import java.businesslayer.ValidationException;
import java.transferobject.OrderDTO;
import java.util.List;

public interface OrderDAO {

    void create(OrderDTO order) throws ValidationException;
    void update(OrderDTO order) throws ValidationException;
    List<OrderDTO> findAll(Long userId);
    List<OrderDTO> findForRetailer(Long userId);
    OrderDTO findById(Long orderId);

}
