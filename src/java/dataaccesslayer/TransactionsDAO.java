package dataaccesslayer;


import entity.Transaction;
import java.util.List;
import transferobject.TransactionDTO;
/**
 * Interface for Data Access Object for the Transactions table.
 */
public interface TransactionsDAO {
    List<TransactionDTO> getAllTransactions();

    Transaction getTransactionById(int transactionId);

    void addTransaction(Transaction transaction);

    void updateTransaction(Transaction transaction);

   
}
