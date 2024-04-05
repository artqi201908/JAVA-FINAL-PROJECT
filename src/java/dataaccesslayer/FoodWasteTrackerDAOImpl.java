package java.dataaccesslayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import java.transferobject.FoodWasteTrackerDTO;

public class FoodWasteTrackerDAOImpl implements FoodWasteTrackerDAO {

    private final DataSource dataSource;

    public FoodWasteTrackerDAOImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<FoodWasteTrackerDTO> getAllWasteRecords() {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList<FoodWasteTrackerDTO> records = new ArrayList<>();

        try {
            con = dataSource.getConnection();
            String query = "SELECT * FROM FoodWasteTracker ORDER BY WasteID";
            pstmt = con.prepareStatement(query);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                FoodWasteTrackerDTO record = new FoodWasteTrackerDTO();
                record.setWasteId(rs.getInt("WasteID"));
                record.setItemId(rs.getInt("ItemID"));
                record.setWastedQuantity(rs.getInt("WastedQuantity"));
                record.setDateReported(rs.getDate("DateReported"));
                records.add(record);
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
        return records;
    }

    @Override
    public FoodWasteTrackerDTO getWasteRecordById(int recordId) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        FoodWasteTrackerDTO record = null;

        try {
            con = dataSource.getConnection();
            String query = "SELECT * FROM FoodWasteTracker WHERE WasteID = ?";
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, recordId);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                record = new FoodWasteTrackerDTO();
                record.setWasteId(rs.getInt("WasteID"));
                record.setItemId(rs.getInt("ItemID"));
                record.setWastedQuantity(rs.getInt("WastedQuantity"));
                record.setDateReported(rs.getDate("DateReported"));
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
        return record;
    }

    @Override
    public void addWasteRecord(FoodWasteTrackerDTO wasteRecord) {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = dataSource.getConnection();
            String sql = "INSERT INTO FoodWasteTracker (ItemID, WastedQuantity, DateReported) VALUES (?, ?, ?)";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, wasteRecord.getItemId());
            pstmt.setInt(2, wasteRecord.getWastedQuantity());
            pstmt.setDate(3, new java.sql.Date(wasteRecord.getDateReported().getTime()));
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
    public void updateWasteRecord(FoodWasteTrackerDTO wasteRecord) {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = dataSource.getConnection();
            String sql = "UPDATE FoodWasteTracker SET ItemID = ?, WastedQuantity = ?, DateReported = ? WHERE WasteID = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, wasteRecord.getItemId());
            pstmt.setInt(2, wasteRecord.getWastedQuantity());
            pstmt.setDate(3, new java.sql.Date(wasteRecord.getDateReported().getTime()));
            pstmt.setInt(4, wasteRecord.getWasteId());
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
    public void deleteWasteRecord(int recordId) {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = dataSource.getConnection();
            String sql = "DELETE FROM FoodWasteTracker WHERE WasteID = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, recordId);
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
