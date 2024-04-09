/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package businesslayer;

import dataaccesslayer.FoodItemDAO;
import dataaccesslayer.TransactionsDAO;
import java.transferobject.TransactionDTO;

/**
 *
 * @author Shao Tang
 */
public class ConsumerBusinessLogic {

    private TransactionsDAO transactionsDAO;
    private FoodItemDAO itemDAO;

    public ConsumerBusinessLogic(TransactionsDAO transactionsDAO, FoodItemDAO itemDAO) {
        this.transactionsDAO = transactionsDAO;
        this.itemDAO = itemDAO;
    }

    public void purchaseItem(int userId, int itemId, int quantity) throws Exception {

        boolean isAvailable = checkInventoryAvailability(itemId, quantity);
        if (!isAvailable) {
            throw new Exception("Item not available in sufficient quantity.");
        }

        TransactionDTO transaction = new TransactionDTO();
        transaction.setUserId(userId);
        transaction.setItemId(itemId);
        transaction.setTransactionType("PURCHASE");
        transaction.setTransactionDate(new java.sql.Timestamp(new java.util.Date().getTime()));
        transactionsDAO.addTransaction(transaction);

    }

    private boolean checkInventoryAvailability(int itemId, int quantity) {
        //
        return true;
    }
}
