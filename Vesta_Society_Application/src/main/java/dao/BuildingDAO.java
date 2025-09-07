package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Building;
import util.DB_Connection;

public class BuildingDAO {

	public BuildingDAO() {
		// TODO Auto-generated constructor stub
	}
	
	public List<Building> getAllBuildings() {
	    List<Building> list = new ArrayList<Building>();
	    String query = "SELECT * FROM buildings ORDER BY created_at DESC";

	    try (Connection conn = DB_Connection.getConnection();
	         PreparedStatement ps = conn.prepareStatement(query);
	         ResultSet rs = ps.executeQuery()) {

	        while (rs.next()) {
	            Building b = new Building(
	                rs.getLong("building_id"),
	                rs.getLong("society_id"),
	                rs.getString("name"),
	                rs.getInt("floors"),
	                rs.getTimestamp("created_at")
	            );
	            list.add(b);
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return list;
	}
	
	public List<Building> getBuildingsBySocietyID(int societyID){
		List<Building> list = new ArrayList<Building>();
		String query = "SELECT * FROM buildings where society_id = '"+societyID+"' ORDER BY created_at DESC";
		try (Connection conn = DB_Connection.getConnection();
		         PreparedStatement ps = conn.prepareStatement(query);
		         ResultSet rs = ps.executeQuery()) {
			 while (rs.next()) {
		            Building b = new Building(
		                rs.getLong("building_id"),
		                rs.getLong("society_id"),
		                rs.getString("name"),
		                rs.getInt("floors"),
		                rs.getTimestamp("created_at")
		            );
		            list.add(b);
		        }

		    }catch (Exception e) {
		        e.printStackTrace();
		    }
		return list;
		}
	

	public boolean deleteBuildingById(long buildingId) {
	    String query = "DELETE FROM buildings WHERE building_id = ?";
	    try (Connection conn = DB_Connection.getConnection();
	         PreparedStatement ps = conn.prepareStatement(query)) {

	        ps.setLong(1, buildingId);
	        return ps.executeUpdate() > 0;

	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	
	public Long createBuilding(Long societyId, String name, int floors) {
	    String query = "INSERT INTO buildings (society_id, name, floors) VALUES (?, ?, ?)";

	    try (Connection conn = DB_Connection.getConnection();
	         PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

	        ps.setLong(1, societyId);
	        ps.setString(2, name);
	        ps.setInt(3, floors);

	        int rows = ps.executeUpdate();
	        if (rows > 0) {
	            ResultSet rs = ps.getGeneratedKeys();
	            if (rs.next()) return rs.getLong(1);
	        }

	    } catch (SQLIntegrityConstraintViolationException e) {
	        System.out.println("Duplicate building name in this society.");
	        return 0L;
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return null;
	}

	public Long getBuildingIdByName(String buildingName) throws SQLException {
	    String sql = "SELECT building_id FROM buildings WHERE name = ?";
	    try (Connection conn = DB_Connection.getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql)) {

	        ps.setString(1, buildingName);

	        try (ResultSet rs = ps.executeQuery()) {
	            if (rs.next()) {
	                return rs.getLong("building_id");
	            }
	        }
	    }
	    return null; // Not found
	}
	
	


}
