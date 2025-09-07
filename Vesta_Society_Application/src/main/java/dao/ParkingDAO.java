package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.ParkingAssignmentDetails;
import model.ParkingSlot;
import util.DB_Connection;

public class ParkingDAO {

	public ParkingDAO() {
		// TODO Auto-generated constructor stub
	}
	
	public List<ParkingAssignmentDetails> getParkingAssignmentsBySocietyId(int societyId) {
	    List<ParkingAssignmentDetails> list = new ArrayList<ParkingAssignmentDetails>();

	    String sql = "SELECT pa.vehicle_id, m.full_name, f.flat_no, v.type, s.society_id, ps.identifier, ps.is_covered, pa.start_date, pa.end_date " +
	                 "FROM parking_assignments pa " +
	                 "JOIN vehicles v ON pa.vehicle_id = v.vehicle_id " +
	                 "JOIN members m ON v.member_id = m.member_id " +
	                 "JOIN flats f ON v.flat_id = f.flat_id " +
	                 "JOIN parking_slots ps ON pa.slot_id = ps.slot_id " +
	                 "JOIN societies s ON ps.society_id = s.society_id " +
	                 "WHERE s.society_id = ?";

	    try (Connection conn = DB_Connection.getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql)) {

	        ps.setInt(1, societyId);
	        try (ResultSet rs = ps.executeQuery()) {
	            while (rs.next()) {
	                ParkingAssignmentDetails pad = new ParkingAssignmentDetails();
	                pad.setVehicleId(rs.getLong("vehicle_id"));
	                pad.setMemberFullName(rs.getString("full_name"));
	                pad.setFlatNo(rs.getString("flat_no"));
	                pad.setType(rs.getString("type"));
	                pad.setSocietyId(rs.getLong("society_id"));
	                pad.setIdentifier(rs.getString("identifier"));
	                pad.setCovered(rs.getBoolean("is_covered"));
	                pad.setStartDate(rs.getDate("start_date"));
	                pad.setEndDate(rs.getDate("end_date"));
	                list.add(pad);
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return list;
	}
	
	public boolean deleteAssignmentByVehicleId(Long vehicleId) {
	    String sql = "DELETE FROM parking_assignments WHERE vehicle_id = ?";

	    try (Connection conn = DB_Connection.getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql)) {

	        ps.setLong(1, vehicleId);
	        int rows = ps.executeUpdate();
	        return rows > 0;

	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	
	public boolean assignParking(Long slotId, Long vehicleId, Date startDate, Date endDate) {
	    String sql = "INSERT INTO parking_assignments (slot_id, vehicle_id, start_date, end_date) VALUES (?, ?, ?, ?)";

	    try (Connection conn = DB_Connection.getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql)) {

	        ps.setLong(1, slotId);
	        ps.setLong(2, vehicleId);
	        ps.setDate(3, startDate);

	        if (endDate != null) {
	            ps.setDate(4, endDate);
	        } else {
	            ps.setNull(4, java.sql.Types.DATE);
	        }

	        int rows = ps.executeUpdate();
	        return rows > 0;

	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	
	




}
