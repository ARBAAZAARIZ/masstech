package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.DeliveryLog;
import model.GateLog;
import util.DB_Connection;

public class SecurityDao {

    public SecurityDao() {}

    public boolean insertVisitorGateLog(Long societyId, String visitorName, String vehicleNo,
                                        String purpose, String flatNo, String buildingName) {
        boolean success = false;

        String sql = "{CALL insert_visitor_gatelog(?, ?, ?, ?, ?, ?)}";

        try (Connection conn = DB_Connection.getConnection();
             CallableStatement cs = conn.prepareCall(sql)) {

            cs.setLong(1, societyId);
            cs.setString(2, visitorName);
            cs.setString(3, vehicleNo);
            cs.setString(4, purpose);
            cs.setString(5, flatNo);
            cs.setString(6, buildingName);

            try (ResultSet rs = cs.executeQuery()) {
                if (rs.next()) {
                    success = rs.getBoolean("success");
                }
            }

        } catch (Exception e) {
            System.out.println("Error calling insert_visitor_gatelog: " + e.getMessage());
        }

        return success;
    }
    
    public List<GateLog> getVisitorLogsBySocietyId(Long societyId) {
        List<GateLog> logs = new ArrayList<GateLog>();
        String sql = "SELECT * FROM gate_logs WHERE society_id = ? ORDER BY check_in DESC";

        try (Connection conn = DB_Connection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, societyId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    GateLog log = new GateLog();
                    log.setGateLogId(rs.getLong("gate_log_id"));
                    log.setSocietyId(rs.getLong("society_id"));
                    log.setVisitorName(rs.getString("visitor_name"));
                    log.setVehicleNo(rs.getString("vehicle_no"));
                    log.setPurpose(rs.getString("purpose"));
                    log.setFlatId(rs.getLong("flat_id"));
                    log.setFlatNo(rs.getString("flat_no"));
                    log.setBuildingName(rs.getString("building_name"));
                    log.setCheckIn(rs.getTimestamp("check_in"));
                    log.setCheckOut(rs.getTimestamp("check_out"));
                    logs.add(log);
                }
            }
        } catch (Exception e) {
            System.out.println("Error fetching visitor logs: " + e.getMessage());
        }

        return logs;
    }
    
    public boolean insertDeliveryLog(Long societyId, String name, String mobile, String type,
            String flatNo, String buildingName) {
    			boolean success = false;
    			String sql = "{CALL insert_delivery_log(?, ?, ?, ?, ?, ?)}";

    			try (Connection conn = DB_Connection.getConnection();
    					CallableStatement cs = conn.prepareCall(sql)) {
    				cs.setLong(1, societyId);
    				cs.setString(2, name);
    				cs.setString(3, mobile);
    				cs.setString(4, type);
    				cs.setString(5, flatNo);
    				cs.setString(6, buildingName);

    				try (ResultSet rs = cs.executeQuery()) {
    					if (rs.next()) {
    						success = rs.getBoolean("success");
    					}
    				}
    			} catch (Exception e) {
    				System.out.println("Error inserting delivery log: " + e.getMessage());
    			}

    			return success;
    }
    
    public List<DeliveryLog> getDeliveryLogsBySocietyId(Long societyId) {
        List<DeliveryLog> logs = new ArrayList<>();
        String sql = "SELECT * FROM delivery_logs WHERE society_id = ? ORDER BY check_in DESC";

        try (Connection conn = DB_Connection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, societyId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    DeliveryLog log = new DeliveryLog();
                    log.setDeliveryLogId(rs.getLong("delivery_log_id"));
                    log.setSocietyId(rs.getLong("society_id"));
                    log.setDeliveryPersonName(rs.getString("delivery_person_name"));
                    log.setMobile(rs.getString("mobile"));
                    log.setDeliveryType(rs.getString("delivery_type"));
                    log.setFlatId(rs.getLong("flat_id"));
                    log.setFlatNo(rs.getString("flat_no"));
                    log.setBuildingName(rs.getString("building_name"));
                    log.setCheckIn(rs.getTimestamp("check_in"));
                    logs.add(log);
                }
            }
        } catch (Exception e) { 
            System.out.println("Error fetching delivery logs: " + e.getMessage());
        }

        return logs;
    }
    
    public boolean updateVisitorCheckout(Long logId) {
        String sql = "UPDATE gate_logs SET check_out = NOW() WHERE gate_log_id = ?";
        System.out.println("log id from dao");
        try (Connection conn = DB_Connection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, logId);
            int success=ps.executeUpdate();
            System.out.println(success);
            if(success>0) {
            	return true;
            }else {
            	return false;
            }
        } catch (Exception e) {
            System.out.println("Error updating check-out: " + e.getMessage());
            return false;
        }
    }




}
