package java.dataaccesslayer;

import java.businesslayer.ValidateException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.transferobject.OrderDTO;
import java.transferobject.StatusOrder;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOImpl extends OrderDAO {
    @Override
    public void create(OrderDTO order) throws ValidateException.ValidationException {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            DataSource ds = new DataSource();
            con = ds.createConnection();

            pstmt = con.prepareStatement("INSERT INTO Orders (itemId, quantity, address, statusId, createUserId, createDate) VALUES(?, ?, ?, ?, ?, now())");
            pstmt.setLong(1, order.getItemId());
            pstmt.setLong(2, order.getQuantity());
            pstmt.setString(3, order.getAddress());
            pstmt.setLong(4, order.getStatusId());
            pstmt.setLong(5, order.getCreateUserId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ValidateException.ValidationException(e.getMessage());
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    @Override
    public void update(OrderDTO order) throws ValidateException.ValidationException {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            DataSource ds = new DataSource();
            con = ds.createConnection();

            pstmt = con.prepareStatement("UPDATE orders " +
                    "SET itemId=?, quantity=?, address=?, statusId=?, modUserId=-1, modDate=now() " +
                    "WHERE id=?");
            pstmt.setLong(1, order.getItemId());
            pstmt.setLong(2, order.getQuantity());
            pstmt.setString(3, order.getAddress());
            pstmt.setLong(4, order.getStatusId());
            pstmt.setLong(5, order.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ValidateException.ValidationException(e.getMessage());
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    @Override
    public List<OrderDTO> findAll(Long userId) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<OrderDTO> orders = new ArrayList<>();
        try {
            DataSource ds = new DataSource();
            con = ds.createConnection();
            pstmt = con.prepareStatement(
                    "select o.*, i.title as itemTitle from orders o, item i where o.itemId = i.id and o.createUserId = ?");
            pstmt.setLong(1, userId);

            rs = pstmt.executeQuery();
            while (rs.next()) {
                OrderDTO order = new OrderDTO();
                order.setId(rs.getLong("id"));
                order.setItemId(rs.getLong("itemId"));
                order.setItemTitle(rs.getString("itemTitle"));
                order.setQuantity(rs.getLong("quantity"));
                order.setAddress(rs.getString("address"));
                order.setStatusId(rs.getLong("statusId"));
                order.setCreateUserId(rs.getLong("createUserId"));
                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return orders;
    }

    @Override
    public List<OrderDTO> findForRetailer(Long userId) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<OrderDTO> orders = new ArrayList<>();
        try {
            DataSource ds = new DataSource();
            con = ds.createConnection();
            pstmt = con.prepareStatement(
                    "select o.*, i.title as itemTitle from orders o, item i where o.itemId = i.id and o.statusId = ?");
            pstmt.setLong(1, StatusOrder.PENDING_APPROVE);

            rs = pstmt.executeQuery();
            while (rs.next()) {
                OrderDTO order = new OrderDTO();
                order.setId(rs.getLong("id"));
                order.setItemId(rs.getLong("itemId"));
                order.setItemTitle(rs.getString("itemTitle"));
                order.setQuantity(rs.getLong("quantity"));
                order.setAddress(rs.getString("address"));
                order.setStatusId(rs.getLong("statusId"));
                order.setCreateUserId(rs.getLong("createUserId"));
                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return orders;
    }

    @Override
    public OrderDTO findById(Long orderId) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        OrderDTO order = null;
        try {
            DataSource ds = new DataSource();
            con = ds.createConnection();
            pstmt = con.prepareStatement(
                    "select o.* from orders o where o.id = ?");
            pstmt.setLong(1, orderId);

            rs = pstmt.executeQuery();
            while (rs.next()) {
                order = new OrderDTO();
                order.setId(rs.getLong("id"));
                order.setItemId(rs.getLong("itemId"));
                order.setQuantity(rs.getLong("quantity"));
                order.setAddress(rs.getString("address"));
                order.setStatusId(rs.getLong("statusId"));
                order.setCreateUserId(rs.getLong("createUserId"));
            }
        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return order;
    }

}