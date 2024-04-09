package dataaccesslayer;

import entity.Transaction;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class TransactionsDAOImpl implements TransactionsDAO {

   

    public TransactionsDAOImpl() {
       
    }

    @Override
    public List<TransactionDTO> getAllTransactions() {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList<TransactionDTO> transactions = new ArrayList<>();

        try {
          
           con = DataSource.getConnection();
            
            
            String query = "SELECT * FROM Transactions ORDER BY TransactionId";
            pstmt = con.prepareStatement(query);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                TransactionDTO transaction = new TransactionDTO();
                transaction.setTransactionId(rs.getInt("TransactionId"));
                transaction.setItemId(rs.getInt("ItemId"));
                transaction.setUserId(rs.getInt("UserId"));
                transaction.setTransactionType(rs.getString("TransactionType"));
                transaction.setTransactionDate(rs.getTimestamp("TransactionDate"));
                transactions.add(transaction);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return transactions;
    }

    @Override
    public Transaction getTransactionById(int transactionId) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Transaction transaction = null;

        try {
            con = DataSource.getConnection();
            
            String query = "SELECT * FROM Transactions WHERE TransactionId = ?";
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, transactionId);
            rs = pstmt.executeQuery();

            while(rs.next()) {
                transaction = new Transaction();
                transaction.setTransactionId(rs.getInt("TransactionId"));
                transaction.setItemId(rs.getInt("ItemId"));
                transaction.setUserId(rs.getInt("UserId"));
                transaction.setTransactionType(rs.getString("TransactionType"));
                transaction.setTransactionDate(rs.getTimestamp("TransactionDate"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return transaction;
    }

    @Override
    public void addTransaction(Transaction transaction) {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = DataSource.getConnection();
            
            String sql = "INSERT INTO Transactions (ItemId, UserId, TransactionType, TransactionDate) VALUES (?, ?, ?, ?)";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, transaction.getItemId());
            pstmt.setInt(2, transaction.getUserId());
            pstmt.setString(3, transaction.getTransactionType());
            pstmt.setTimestamp(4, transaction.getTransactionDate());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    @Override
    public void updateTransaction(Transaction transaction) {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = DataSource.getConnection();
            
            String sql = "UPDATE Transactions SET ItemId = ?, UserId = ?, TransactionType = ?, TransactionDate = ? WHERE TransactionId = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, transaction.getItemId());
            pstmt.setInt(2, transaction.getUserId());
            pstmt.setString(3, transaction.getTransactionType());
            pstmt.setTimestamp(4, transaction.getTransactionDate());
            pstmt.setInt(5, transaction.getTransactionId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    
}
