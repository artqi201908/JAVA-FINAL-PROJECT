package algonquin.businesslayer;

import algonquin.constant.UserType;
import algonquin.dao.OrderDTO;
import algonquin.dao.OrderDao;
import algonquin.dao.OrderDaoImpl;
import algonquin.dto.Order;
import algonquin.dto.User;

import java.util.List;

public class OrderService {
    private OrderDao orderDao = null;

    public OrderService() {
        this.orderDao = new OrderDaoImpl();
    }

    public Order findById(Long orderId) {
        return orderDao.findById(orderId);
    }

    public List<OrderDTO> findAll(User user) {
        if (user.getTypeId() == UserType.RETAILER) {
            return orderDao.findForRetailer(user.getId());
        } else {
            return orderDao.findAll(user.getId());
        }
    }

    public void create(Order order) throws ValidationException {
        validateOrder(order);
        orderDao.create(order);
    }

    public void update(Order order) throws ValidationException {
        validateOrder(order);
        orderDao.update(order);
    }

    private void validateOrder(Order order) throws ValidationException {
        ValidateUtil.validateLong(order.getQuantity(), "Quantity");
    }

}
