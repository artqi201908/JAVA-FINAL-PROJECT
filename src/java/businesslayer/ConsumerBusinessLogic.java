/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package businesslayer;

import dataaccesslayer.FoodItemDAO;
import dataaccesslayer.FoodItemDAOImpl;
import dataaccesslayer.TransactionsDAO;
import dataaccesslayer.TransactionsDAOImpl;


/**
 *
 * @author Shao Tang
 */
public class ConsumerBusinessLogic {
    private TransactionsDAO transactionsDAO;
    private FoodItemDAO itemDAO;
  
   
    public ConsumerBusinessLogic() {
        this.transactionsDAO = new TransactionsDAOImpl();
        this.itemDAO = new FoodItemDAOImpl();
    }
    
   public void purchaseItem(int userId, int itemId, int quantity) throws Exception {
        
        TransactionDTO transaction = new TransactionDTO();
        transaction.setUserId(userId);
        transaction.setItemId(itemId);
        transaction.setTransactionType("PURCHASE");
        transaction.setTransactionDate(new java.sql.Timestamp(new java.util.Date().getTime())); 
        transactionsDAO.addTransaction(transaction);

       
        itemDAO.updateFoodItem(itemId, -quantity); 
    }

   
    public List<TransactionDTO> getAllTransactions(){
         return transactionsDAO.getAllTransactions();
    };

    public Transaction getTransactionById(int transactionId){
        return transactionsDAO.getTransactionById(transactionId);
    };

    public void addTransaction(Transaction transaction){
        transactionsDAO.addTransaction(transaction);
    };

    public void updateTransaction(Transaction transaction){
        transactionsDAO.updateTransaction(transaction);

    };
}
