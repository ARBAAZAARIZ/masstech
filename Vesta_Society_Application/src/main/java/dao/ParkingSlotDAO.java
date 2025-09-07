
package dao;

import java.sql.*;
import java.util.*;
import model.ParkingSlot;
import util.DB_Connection;

public class ParkingSlotDAO {
    private Connection conn;

    public ParkingSlotDAO() {
        this.conn = DB_Connection.getConnection();
    }

    public void save(ParkingSlot slot) throws SQLException {
        String sql = "INSERT INTO parking_slots (society_id, identifier, is_covered) VALUES (?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, slot.getSocietyId());
            ps.setString(2, slot.getIdentifier());
            ps.setBoolean(3, slot.isCovered());
            ps.executeUpdate();
        }
    }

    public List<ParkingSlot> findAll() throws SQLException {
        List<ParkingSlot> list = new ArrayList<>();
        String sql = "SELECT * FROM parking_slots";
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                ParkingSlot slot = new ParkingSlot();
                slot.setSlotId(rs.getLong("slot_id"));
                slot.setSocietyId(rs.getLong("society_id"));
                slot.setIdentifier(rs.getString("identifier"));
                slot.setCovered(rs.getBoolean("is_covered"));
                list.add(slot);
            }
        }
        return list;
    }

    public ParkingSlot findById(Long slotId) throws SQLException {
        String sql = "SELECT * FROM parking_slots WHERE slot_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, slotId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    ParkingSlot slot = new ParkingSlot();
                    slot.setSlotId(rs.getLong("slot_id"));
                    slot.setSocietyId(rs.getLong("society_id"));
                    slot.setIdentifier(rs.getString("identifier"));
                    slot.setCovered(rs.getBoolean("is_covered"));
                    return slot;
                }
            }
        }
        return null;
    }

    public void update(ParkingSlot slot) throws SQLException {
        String sql = "UPDATE parking_slots SET society_id = ?, identifier = ?, is_covered = ? WHERE slot_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, slot.getSocietyId());
            ps.setString(2, slot.getIdentifier());
            ps.setBoolean(3, slot.isCovered());
            ps.setLong(4, slot.getSlotId());
            ps.executeUpdate();
        }
    }

    public void delete(Long slotId) throws SQLException {
        String sql = "DELETE FROM parking_slots WHERE slot_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, slotId);
            ps.executeUpdate();
        }
    }
    
    
    public List<ParkingSlot> getAvailableSlotsBySocietyId(int societyId) {
        List<ParkingSlot> list = new ArrayList<>();

        String sql = "SELECT ps.slot_id, ps.society_id, ps.identifier, ps.is_covered " +
                     "FROM parking_slots ps " +
                     "WHERE ps.society_id = ? AND ps.slot_id NOT IN (SELECT slot_id FROM parking_assignments)";

        try (Connection conn = DB_Connection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, societyId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ParkingSlot slot = new ParkingSlot();
                    slot.setSlotId(rs.getLong("slot_id"));
                    slot.setSocietyId(rs.getLong("society_id"));
                    slot.setIdentifier(rs.getString("identifier"));
                    slot.setCovered(rs.getBoolean("is_covered"));
                    list.add(slot);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
    
    public boolean markSlotAsCovered(Long slotId) {
        String sql = "UPDATE parking_slots SET is_covered = 1 WHERE slot_id = ?";

        try (Connection conn = DB_Connection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, slotId);
            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public List<ParkingSlot> getAllParkingSlotsBysocietyId(int societyID){	
	 List<ParkingSlot> list = new ArrayList<>();
     String sql = "SELECT * FROM parking_slots where society_id='"+societyID+"'";
     try (PreparedStatement ps = conn.prepareStatement(sql);
          ResultSet rs = ps.executeQuery()) {
         while (rs.next()) {
             ParkingSlot slot = new ParkingSlot();
             slot.setSlotId(rs.getLong("slot_id"));
             slot.setSocietyId(rs.getLong("society_id"));
             slot.setIdentifier(rs.getString("identifier"));
             slot.setCovered(rs.getBoolean("is_covered"));
             list.add(slot);
         }
     }catch(SQLException e) {
    	 System.out.println(e.getMessage());
     }
     return list;
		
	}


}
