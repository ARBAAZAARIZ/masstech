package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Amenity;
import model.AmenityCheckinLog;
import util.DB_Connection;

public class AmenityCheckinDao {
    public boolean insertCheckin(AmenityCheckinLog log) {
    	
    	AmenityDAO amenityDAO=new AmenityDAO();
    
   
    	
        String sql = "INSERT INTO amenity_checkin_log (member_id, amenity_id, checkin_time, guard_id, society_id, status, remarks, amanityName) VALUES (?, ?, ?, ?, ?, ?, ?,?)";

        try (Connection conn = DB_Connection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
        	
         	Amenity amenity=amenityDAO.getById(log.getAmenityId());
         	

            ps.setLong(1, log.getMemberId());
            ps.setLong(2, log.getAmenityId());
            ps.setTimestamp(3, log.getCheckinTime());
            ps.setLong(4, log.getGuardId());
            ps.setLong(5, log.getSocietyId());
            ps.setString(6, log.getStatus());
            ps.setString(7, log.getRemarks());
            ps.setString(8, amenity.getName());

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Error inserting amenity check-in: " + e.getMessage());
            return false;
        }
    }
    
    public List<AmenityCheckinLog> getCheckinsBySocietyId(Long societyId) {
        List<AmenityCheckinLog> list = new ArrayList<AmenityCheckinLog>();
        String sql = "SELECT * FROM amenity_checkin_log WHERE society_id = ? ORDER BY checkin_time DESC";

        try (Connection conn = DB_Connection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, societyId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    AmenityCheckinLog log = new AmenityCheckinLog();
                    log.setCheckinId(rs.getLong("checkin_id"));
                    log.setMemberId(rs.getLong("member_id"));
                    log.setAmenityId(rs.getLong("amenity_id"));
                    log.setCheckinTime(rs.getTimestamp("checkin_time"));
                    log.setCheckoutTime(rs.getTimestamp("checkout_time"));
                    log.setGuardId(rs.getLong("guard_id"));
                    log.setSocietyId(rs.getLong("society_id"));
                    log.setStatus(rs.getString("status"));
                    log.setRemarks(rs.getString("remarks"));
                    log.setCreatedAt(rs.getTimestamp("created_at"));
                    log.setAmanityName(rs.getString("amanityName"));
                    list.add(log);
                }
            }
        } catch (Exception e) {
            System.out.println("Error fetching amenity logs: " + e.getMessage());
        }

        return list;
    }
    
    public boolean updateCheckoutTime(Long checkinId) {
        String sql = "UPDATE amenity_checkin_log SET checkout_time = CURRENT_TIMESTAMP, status = 'OUT' WHERE checkin_id = ?";

        try (Connection conn = DB_Connection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, checkinId);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Error updating checkout: " + e.getMessage());
            return false;
        }
    }


}
