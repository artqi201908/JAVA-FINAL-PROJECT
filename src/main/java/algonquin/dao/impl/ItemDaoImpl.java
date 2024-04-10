package algonquin.dao.impl;

import algonquin.dao.DataSource;
import algonquin.dao.ItemDao;
import algonquin.dto.Item;
import algonquin.businesslayer.ValidationException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 
 * @since 3/30/2024
 */
public class ItemDaoImpl implements ItemDao {

    @Override
    public void create(Item item) throws ValidationException {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            DataSource ds = new DataSource();
            con = ds.createConnection();
            pstmt = con.prepareStatement("INSERT INTO Item (title, description, quantity, expirationDate, isDonate, price, discount, isSurplus, createUserId, createDate) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, now())");
            pstmt.setString(1, item.getTitle());
            pstmt.setString(2, item.getDescription());
            pstmt.setLong(3, item.getQuantity());
            pstmt.setTimestamp(4, new Timestamp(item.getExpirationDate().getTime()));
            pstmt.setBoolean(5, item.getIsDonate());
            pstmt.setBigDecimal(6, item.getPrice());
            pstmt.setLong(7, item.getDiscount());
            pstmt.setBoolean(8, item.getIsSurplus());
            pstmt.setLong(9, item.getCreateUserId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ValidationException(e.getMessage());
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
    public void update(Item item) throws ValidationException {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            DataSource ds = new DataSource();
            con = ds.createConnection();
            
            pstmt = con.prepareStatement("UPDATE item " +
                    "SET title=?, description=?, quantity=?, expirationDate=?, isDonate=?, price=?, discount=?, isSurplus=?, modUserId=?, modDate=now() " +
                    "WHERE id=?");
            pstmt.setString(1, item.getTitle());
            pstmt.setString(2, item.getDescription());
            pstmt.setLong(3, item.getQuantity());
            pstmt.setTimestamp(4, new Timestamp(item.getExpirationDate().getTime()));
            pstmt.setBoolean(5, item.getIsDonate());
            pstmt.setBigDecimal(6, item.getPrice());
            pstmt.setLong(7, item.getDiscount());
            pstmt.setBoolean(8, item.getIsSurplus());
            pstmt.setLong(9, item.getModUserId());
            pstmt.setLong(10, item.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ValidationException(e.getMessage());
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
    public void delete(Long itemId) throws ValidationException {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            DataSource ds = new DataSource();
            con = ds.createConnection();
            
            pstmt = con.prepareStatement("DELETE FROM item " +
                    "WHERE id=?");
            pstmt.setLong(1, itemId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ValidationException(e.getMessage());
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
    public List<Item> findAll(Long userId) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Item> items = new ArrayList<>();
        try {
            DataSource ds = new DataSource();
            con = ds.createConnection();
            pstmt = con.prepareStatement(
                    "SELECT * FROM Item where createUserId = ?");
            pstmt.setLong(1, userId);

            rs = pstmt.executeQuery();
            while (rs.next()) {
                Item item = new Item();
                item.setId(rs.getLong("id"));
                item.setTitle(rs.getString("title"));
                item.setDescription(rs.getString("description"));
                item.setQuantity(rs.getLong("quantity"));
                item.setExpirationDate(rs.getDate("expirationDate"));
                item.setIsDonate(rs.getBoolean("isDonate"));
                item.setPrice(rs.getBigDecimal("price"));
                item.setDiscount(rs.getLong("disCount"));
                item.setIsSurplus(rs.getBoolean("isSurplus"));
                item.setCreateUserId(rs.getLong("createUserId"));
                items.add(item);
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
        return items;
    }

    @Override
    public List<Item> findSurplus(Long userId) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Item> items = new ArrayList<>();
        try {
            DataSource ds = new DataSource();
            con = ds.createConnection();
            pstmt = con.prepareStatement(
                    "SELECT * FROM Item where createUserId = ? and isSurplus = 1");
            pstmt.setLong(1, userId);

            rs = pstmt.executeQuery();
            while (rs.next()) {
                Item item = new Item();
                item.setId(rs.getLong("id"));
                item.setTitle(rs.getString("title"));
                item.setDescription(rs.getString("description"));
                item.setQuantity(rs.getLong("quantity"));
                item.setExpirationDate(rs.getDate("expirationDate"));
                item.setIsDonate(rs.getBoolean("isDonate"));
                item.setPrice(rs.getBigDecimal("price"));
                item.setDiscount(rs.getLong("disCount"));
                item.setIsSurplus(rs.getBoolean("isSurplus"));
                item.setCreateUserId(rs.getLong("createUserId"));
                items.add(item);
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
        return items;
    }

    @Override
    public List<Item> findAllForConsumerToBuy() {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Item> items = new ArrayList<>();
        try {
            DataSource ds = new DataSource();
            con = ds.createConnection();
            pstmt = con.prepareStatement(
                    "SELECT * FROM Item where (isDonate != 1 or isDonate is NULL) and isSurplus = 1");

            rs = pstmt.executeQuery();
            while (rs.next()) {
                Item item = new Item();
                item.setId(rs.getLong("id"));
                item.setTitle(rs.getString("title"));
                item.setDescription(rs.getString("description"));
                item.setQuantity(rs.getLong("quantity"));
                item.setExpirationDate(rs.getDate("expirationDate"));
                item.setIsDonate(rs.getBoolean("isDonate"));
                item.setPrice(rs.getBigDecimal("price"));
                item.setDiscount(rs.getLong("disCount"));
                item.setIsSurplus(rs.getBoolean("isSurplus"));
                item.setCreateUserId(rs.getLong("createUserId"));
                items.add(item);
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
        return items;
    }

    @Override
    public List<Item> findAllForCharityToClaim() {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Item> items = new ArrayList<>();
        try {
            DataSource ds = new DataSource();
            con = ds.createConnection();
            pstmt = con.prepareStatement(
                    "SELECT * FROM Item where isDonate = 1 and isSurplus = 1");

            rs = pstmt.executeQuery();
            while (rs.next()) {
                Item item = new Item();
                item.setId(rs.getLong("id"));
                item.setTitle(rs.getString("title"));
                item.setDescription(rs.getString("description"));
                item.setQuantity(rs.getLong("quantity"));
                item.setExpirationDate(rs.getDate("expirationDate"));
                item.setIsDonate(rs.getBoolean("isDonate"));
                item.setPrice(rs.getBigDecimal("price"));
                item.setDiscount(rs.getLong("disCount"));
                item.setIsSurplus(rs.getBoolean("isSurplus"));
                item.setCreateUserId(rs.getLong("createUserId"));
                items.add(item);
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
        return items;
    }

    @Override
    public Item findById(Long itemId) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Item item = null;
        try {
            DataSource ds = new DataSource();
            con = ds.createConnection();
            pstmt = con.prepareStatement(
                    "SELECT * FROM Item where id = ?");
            pstmt.setLong(1, itemId);

            rs = pstmt.executeQuery();
            while (rs.next()) {
                item = new Item();
                item.setId(rs.getLong("id"));
                item.setTitle(rs.getString("title"));
                item.setDescription(rs.getString("description"));
                item.setQuantity(rs.getLong("quantity"));
                item.setExpirationDate(rs.getDate("expirationDate"));
                item.setIsDonate(rs.getBoolean("isDonate"));
                item.setPrice(rs.getBigDecimal("price"));
                item.setDiscount(rs.getLong("disCount"));
                item.setIsSurplus(rs.getBoolean("isSurplus"));
                item.setCreateUserId(rs.getLong("createUserId"));
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
        return item;
    }

    @Override
    public Item findById(Long userId, Long itemId) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Item item = null;
        try {
            DataSource ds = new DataSource();
            con = ds.createConnection();
            pstmt = con.prepareStatement(
                    "SELECT * FROM Item where createUserId = ? and id = ?");
            pstmt.setLong(1, userId);
            pstmt.setLong(2, itemId);

            rs = pstmt.executeQuery();
            while (rs.next()) {
                item = new Item();
                item.setId(rs.getLong("id"));
                item.setTitle(rs.getString("title"));
                item.setDescription(rs.getString("description"));
                item.setQuantity(rs.getLong("quantity"));
                item.setExpirationDate(rs.getDate("expirationDate"));
                item.setIsDonate(rs.getBoolean("isDonate"));
                item.setPrice(rs.getBigDecimal("price"));
                item.setDiscount(rs.getLong("disCount"));
                item.setIsSurplus(rs.getBoolean("isSurplus"));
                item.setCreateUserId(rs.getLong("createUserId"));
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
        return item;
    }
}
