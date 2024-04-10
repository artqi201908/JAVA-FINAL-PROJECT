package java.businesslayer;

import java.dataaccesslayer.OrderDAO;
import java.dataaccesslayer.OrderDAOImpl;
import java.transferobject.OrderDTO;
import java.transferobject.UserDTO;
import java.transferobject.UserType;
import java.util.List;

/**
 * @author Wenqi Tang
 */
public class OrderBusinessLogic {
    private OrderDAO orderDao = null;

    public OrderBusinessLogic() {
        this.orderDao = new OrderDAOImpl();
    }

    public OrderDTO findById(Long orderId) {
        return orderDao.findById(orderId);
    }

    public List<OrderDTO> findAll(UserDTO user) {
        if (user.getTypeId() == UserType.RETAILER) {
            return orderDao.findForRetailer(user.getId());
        } else {
            return orderDao.findAll(user.getId());
        }
    }

    public void create(OrderDTO order) throws ValidationException {
        validateOrder(order);
        orderDao.create(order);
    }

    public void update(OrderDTO order) throws ValidationException {
        validateOrder(order);
        orderDao.update(order);
    }

    private void validateOrder(OrderDTO order) throws ValidationException {
        ValidateItem.validateLong(order.getQuantity(), "Quantity");
    }
}
