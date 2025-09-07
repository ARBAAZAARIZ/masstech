package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Vehicle;
import util.DB_Connection;

public class VehicleDAO {

	private Connection conn;

    public VehicleDAO() {
        this.conn = DB_Connection.getConnection();
    }
    
    public Long save(Vehicle v) throws SQLException {
        String sql = "INSERT INTO vehicles (member_id, flat_id, registration_no, type) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)) {
            ps.setLong(1, v.getMemberId());
            ps.setLong(2, v.getFlatId());
            ps.setString(3, v.getRegistrationNo());
            ps.setString(4, v.getType());
            int rows = ps.executeUpdate();
            if (rows > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) return rs.getLong(1);
            }
            return null;
        }
        
    }
    
    public List<Vehicle> getAllVehicles() throws SQLException {
        String sql = "SELECT * FROM vehicles";
        List<Vehicle> list = new ArrayList<Vehicle>();

        try (Connection conn = DB_Connection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Vehicle v = new Vehicle();
                v.setVehicleId(rs.getLong("vehicle_id"));
                v.setMemberId(rs.getLong("member_id"));
                v.setFlatId(rs.getLong("flat_id"));
                v.setRegistrationNo(rs.getString("registration_no"));
                v.setType(rs.getString("type"));
                list.add(v);
            }
        }
        return list;
    }
    
    public List<Vehicle> getUnassignedVehiclesBySocietyId(int societyId) {
        List<Vehicle> list = new ArrayList<>();

        String sql = "SELECT v.vehicle_id, v.member_id, v.flat_id, v.registration_no, v.type " +
                     "FROM vehicles v " +
                     "JOIN members m ON v.member_id = m.member_id " +
                     "WHERE m.society_id = ? AND v.vehicle_id NOT IN (SELECT vehicle_id FROM parking_assignments)";

        try (Connection conn = DB_Connection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, societyId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Vehicle v = new Vehicle();
                    v.setVehicleId(rs.getLong("vehicle_id"));
                    v.setMemberId(rs.getLong("member_id"));
                    v.setFlatId(rs.getLong("flat_id"));
                    v.setRegistrationNo(rs.getString("registration_no"));
                    v.setType(rs.getString("type"));
                    list.add(v);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }


    
 

}
