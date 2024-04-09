/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package java.businesslayer;


import java.transferobject.OrderDTO;
import java.transferobject.UserDTO;
import java.transferobject.UserType;
import java.util.List; /**
 *
 * @author Shao Tang
 */
public class OrderBusinessLogic {

    private OrderDAO orderDao = null;

    public OrderBusinessLogic() {
        this.orderDao = new OrderDaoImpl();
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

    public void create(OrderDTO order) throws ValidateException.ValidationException {
        validateOrder(order);
        orderDao.create(order);
    }

    public void update(OrderDTO order) throws ValidateException.ValidationException {
        validateOrder(order);
        orderDao.update(order);
    }

    private void validateOrder(OrderDTO order) throws ValidateException.ValidationException {
        ValidateItem.validateLong(order.getQuantity(), "Quantity");
    }








    public void purchaseItem(int userId, int itemId, int quantity) throws Exception {

        TransactionDTO transaction = new TransactionDTO();
        transaction.setUserId(userId);
        transaction.setItemId(itemId);
        transaction.setTransactionType("PURCHASE");
        transaction.setTransactionDate(new java.sql.Timestamp(new java.util.Date().getTime()));
        transactionsDAO.addTransaction(transaction);

    }

    public List<TransactionDTO> getAllTransactions() {
        return transactionsDAO.getAllTransactions();
    }

    ;

    public Transaction getTransactionById(int transactionId) {
        return transactionsDAO.getTransactionById(transactionId);
    }

    ;

    public void addTransaction(Transaction transaction) {
        transactionsDAO.addTransaction(transaction);
    }

    ;

    public void updateTransaction(Transaction transaction) {
        transactionsDAO.updateTransaction(transaction);

    }
;
}

    private boolean checkInventoryAvailability(int itemId, int quantity) {
    }
