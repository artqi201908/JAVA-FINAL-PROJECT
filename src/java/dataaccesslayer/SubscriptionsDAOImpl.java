package dataaccesslayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import transferobject.SubscriptionDTO;

public class SubscriptionsDAOImpl implements SubscriptionsDAO {

    private final DataSource dataSource;

    public SubscriptionsDAOImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<SubscriptionDTO> getAllSubscriptions() {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList<SubscriptionDTO> subscriptions = new ArrayList<>();

        try {
            con = dataSource.getConnection();
            String query = "SELECT * FROM Subscriptions ORDER BY SubscriptionId";
            pstmt = con.prepareStatement(query);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                SubscriptionDTO subscription = new SubscriptionDTO();
                subscription.setSubscriptionId(rs.getInt("SubscriptionId"));
                subscription.setUserId(rs.getInt("UserId"));
                subscription.setLocation(rs.getString("Location"));
                subscription.setCommunicationMethod(rs.getString("CommunicationMethod"));
                subscriptions.add(subscription);
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
        return subscriptions;
    }

    @Override
    public SubscriptionDTO getSubscriptionById(int subscriptionId) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        SubscriptionDTO subscription = null;

        try {
            con = dataSource.getConnection();
            String query = "SELECT * FROM Subscriptions WHERE SubscriptionId = ?";
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, subscriptionId);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                subscription = new SubscriptionDTO();
                subscription.setSubscriptionId(rs.getInt("SubscriptionId"));
                subscription.setUserId(rs.getInt("UserId"));
                subscription.setLocation(rs.getString("Location"));
                subscription.setCommunicationMethod(rs.getString("CommunicationMethod"));
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
        return subscription;
    }

    @Override
    public void addSubscription(SubscriptionDTO subscription) {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = dataSource.getConnection();
            String sql = "INSERT INTO Subscriptions (UserId, Location, CommunicationMethod) VALUES (?, ?, ?)";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, subscription.getUserId());
            pstmt.setString(2, subscription.getLocation());
            pstmt.setString(3, subscription.getCommunicationMethod());
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
    public void updateSubscription(SubscriptionDTO subscription) {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = dataSource.getConnection();
            String sql = "UPDATE Subscriptions SET UserId = ?, Location = ?, CommunicationMethod = ? WHERE SubscriptionId = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, subscription.getUserId());
            pstmt.setString(2, subscription.getLocation());
            pstmt.setString(3, subscription.getCommunicationMethod());
            pstmt.setInt(4, subscription.getSubscriptionId());
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
    public void deleteSubscription(int subscriptionId) {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = dataSource.getConnection();
            String sql = "DELETE FROM Subscriptions WHERE SubscriptionId = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, subscriptionId);
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
