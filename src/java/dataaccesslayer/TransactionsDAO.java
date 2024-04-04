package java.dataaccesslayer;

import java.transferobject.TransactionDTO;
import java.util.List;

/**
 * Interface for Data Access Object for the Transactions table.
 */
public interface TransactionsDAO {
    List<TransactionDTO> getAllTransactions();

    TransactionDTO getTransactionById(int transactionId);

    void addTransaction(TransactionDTO transaction);

    void updateTransaction(TransactionDTO transaction);

    void deleteTransaction(int transactionId);
}
