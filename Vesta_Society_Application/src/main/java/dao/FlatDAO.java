package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Flat;
import util.DB_Connection;

public class FlatDAO {

	public FlatDAO() {
		
	}
	
	public List<Flat> getAllFlats() {
	    List<Flat> list = new ArrayList<Flat>();
	    String query = "SELECT * FROM flats ORDER BY flat_no";

	    try (Connection conn = DB_Connection.getConnection();
	         PreparedStatement ps = conn.prepareStatement(query);
	         ResultSet rs = ps.executeQuery()) {

	        while (rs.next()) {
	            Flat f = new Flat();
	            f.setFlatId(rs.getLong("flat_id"));
	            f.setBuildingId(rs.getLong("building_id"));
	            f.setFlatNo(rs.getString("flat_no"));
	            f.setFloorNo(rs.getInt("floor_no"));
	            f.setCarpetAreaSqft(rs.getDouble("carpet_area_sqft"));
	            f.setParkingAllocated(rs.getBoolean("is_parking_allocated"));
	            list.add(f);
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return list;
	}

	public Long createFlat(Long buildingId, String flatNo, int floorNo, double carpetArea, boolean parking) {
	    String query = "INSERT INTO flats (building_id, flat_no, floor_no, carpet_area_sqft, is_parking_allocated) VALUES (?, ?, ?, ?, ?)";

	    try (Connection conn = DB_Connection.getConnection();
	         PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

	        ps.setLong(1, buildingId);
	        ps.setString(2, flatNo);
	        ps.setInt(3, floorNo);
	        ps.setDouble(4, carpetArea);
	        ps.setBoolean(5, parking);

	        int rows = ps.executeUpdate();
	        if (rows > 0) {
	            ResultSet rs = ps.getGeneratedKeys();
	            if (rs.next()) return rs.getLong(1);
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return null;
	}

	public boolean deleteFlatById(long flatId) {
	    String query = "DELETE FROM flats WHERE flat_id = ?";
	    try (Connection conn = DB_Connection.getConnection();
	         PreparedStatement ps = conn.prepareStatement(query)) {

	        ps.setLong(1, flatId);
	        return ps.executeUpdate() > 0;

	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	
	public Long getFlatIdByBuildingAndFlatNo(Long buildingId, String flatNo) throws SQLException {
	    String sql = "SELECT flat_id FROM flats WHERE building_id = ? AND flat_no = ?";
	    try (Connection conn = DB_Connection.getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql)) {

	        ps.setLong(1, buildingId);
	        ps.setString(2, flatNo);

	        try (ResultSet rs = ps.executeQuery()) {
	            if (rs.next()) {
//	            	System.out.println(rs.getLong("flat_id") + "flat dao");
	                return rs.getLong("flat_id");
	            	
	            }
	        }
	    }
	    return null; // Not found
	}
	
	public List<Flat> getFlatsByBuildingId(Long buildingId) {
	    List<Flat> list = new ArrayList<>();
	    String query = "SELECT * FROM flats WHERE building_id = ? ORDER BY flat_no";

	    try (Connection conn = DB_Connection.getConnection();
	         PreparedStatement ps = conn.prepareStatement(query)) {

	        ps.setLong(1, buildingId);
	        try (ResultSet rs = ps.executeQuery()) {
	            while (rs.next()) {
	                Flat f = new Flat(
	                    rs.getLong("flat_id"),
	                    rs.getLong("building_id"),
	                    rs.getString("flat_no"),
	                    rs.getInt("floor_no"),
	                    rs.getDouble("carpet_area_sqft"),
	                    rs.getBoolean("is_parking_allocated")
	                );
	                list.add(f);
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return list;
	}




}
