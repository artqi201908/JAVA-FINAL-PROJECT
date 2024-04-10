package java.dataaccesslayer;

import java.businesslayer.ValidateException;
import java.transferobject.OrderDTO;
import java.util.List;

public interface OrderDAO {

    void create(OrderDTO order) throws ValidateException.ValidationException;
    void update(OrderDTO order) throws ValidateException.ValidationException;
    List<OrderDTO> findAll(Long userId);
    List<OrderDTO> findForRetailer(Long userId);
    OrderDTO findById(Long orderId);

}
