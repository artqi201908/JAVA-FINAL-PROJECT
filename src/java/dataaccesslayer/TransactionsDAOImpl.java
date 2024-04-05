package java.dataaccesslayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import java.transferobject.TransactionDTO;

public class TransactionsDAOImpl implements TransactionsDAO {

    private final DataSource dataSource;

    public TransactionsDAOImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<TransactionDTO> getAllTransactions() {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList<TransactionDTO> transactions = new ArrayList<>();

        try {
            con = dataSource.getConnection();
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
    public TransactionDTO getTransactionById(int transactionId) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        TransactionDTO transaction = null;

        try {
            con = dataSource.getConnection();
            String query = "SELECT * FROM Transactions WHERE TransactionId = ?";
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, transactionId);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                transaction = new TransactionDTO();
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
    public void addTransaction(TransactionDTO transaction) {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = dataSource.getConnection();
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
    public void updateTransaction(TransactionDTO transaction) {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = dataSource.getConnection();
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

    @Override
    public void deleteTransaction(int transactionId) {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = dataSource.getConnection();
            String sql = "DELETE FROM Transactions WHERE TransactionId = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, transactionId);
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
