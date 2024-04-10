package java.dataaccesslayer;

import java.businesslayer.ValidateException;
import java.transferobject.OrderDTO;
import java.util.List;

public interface OrderDAO {

    void create(OrderDTO order) throws ValidateException;
    void update(OrderDTO order) throws ValidateException;
    List<OrderDTO> findAll(Long userId);
    List<OrderDTO> findForRetailer(Long userId);
    OrderDTO findById(Long orderId);

}
