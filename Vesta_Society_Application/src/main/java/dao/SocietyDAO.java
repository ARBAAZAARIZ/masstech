package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Society;
import util.DB_Connection;

public class SocietyDAO {

	public SocietyDAO() {
		// TODO Auto-generated constructor stub
	}
	
	public List<Society> getAllSocieties() {
	    List<Society> list = new ArrayList<Society>();
	    String query = "SELECT DISTINCT society_id, name FROM societies";

	    try (Connection conn = DB_Connection.getConnection();
	         PreparedStatement ps = conn.prepareStatement(query);
	         ResultSet rs = ps.executeQuery()) {

	        while (rs.next()) {
	            Society s = new Society();
	            s.setSocietyId(rs.getLong("society_id"));
	            s.setName(rs.getString("name"));
	            list.add(s);
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return list;
	}
	
	public List<Society> viewAllSocieties() {
	    List<Society> list = new ArrayList<>();
	    String query = "SELECT * FROM societies ORDER BY created_at DESC";

	    try (Connection conn = DB_Connection.getConnection();
	         PreparedStatement ps = conn.prepareStatement(query);
	         ResultSet rs = ps.executeQuery()) {

	        while (rs.next()) {
	            Society s = new Society();
	            s.setSocietyId(rs.getLong("society_id"));
	            s.setName(rs.getString("name"));
	            s.setAddressLine1(rs.getString("address_line1"));
	            s.setAddressLine2(rs.getString("address_line2"));
	            s.setCity(rs.getString("city"));
	            s.setState(rs.getString("state"));
	            s.setPincode(rs.getString("pincode"));
	            s.setCreatedAt(rs.getDate("created_at"));
	            s.setUpdatedAt(rs.getDate("updated_at"));
	            list.add(s);
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return list;
	}
	
	public Long createSociety(String name, String addressLine1, String addressLine2,
            String city, String state, String pincode) {

		String query = "INSERT INTO societies (name, address_line1, address_line2, city, state, pincode) " +
				"VALUES (?, ?, ?, ?, ?, ?)";

		try (Connection conn = DB_Connection.getConnection();
				PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

			ps.setString(1, name);
			ps.setString(2, addressLine1);
			ps.setString(3, addressLine2);
			ps.setString(4, city);
			ps.setString(5, state);
			ps.setString(6, pincode);

			int affectedRows = ps.executeUpdate();

			if (affectedRows > 0) {
				try (ResultSet rs = ps.getGeneratedKeys()) {
					if (rs.next()) {
      return rs.getLong(1);
					}
				}
			}

		} 	catch (SQLIntegrityConstraintViolationException e) {
			System.out.println("Duplicate society name: " + name);
			return 0L; // Signal duplicate
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	public Society getSocietyById(long societyId) {
	    String query = "SELECT * FROM societies WHERE society_id = ?";
	    try (Connection conn = DB_Connection.getConnection();
	         PreparedStatement ps = conn.prepareStatement(query)) {

	        ps.setLong(1, societyId);
	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {
	            return new Society(
	                rs.getLong("society_id"),
	                rs.getString("name"),
	                rs.getString("address_line1"),
	                rs.getString("address_line2"),
	                rs.getString("city"),
	                rs.getString("state"),
	                rs.getString("pincode"),
	                rs.getDate("created_at"),
	                rs.getDate("updated_at")
	            );
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return null;
	}
	
	
	public boolean updateSociety(long societyId, String name, String addressLine1, String addressLine2,
            String city, String state, String pincode) {

		String query = "UPDATE societies SET name = ?, address_line1 = ?, address_line2 = ?, city = ?, state = ?, pincode = ?, updated_at = NOW() WHERE society_id = ?";

		try (Connection conn = DB_Connection.getConnection();
				PreparedStatement ps = conn.prepareStatement(query)) {

			ps.setString(1, name);
			ps.setString(2, addressLine1);
			ps.setString(3, addressLine2);
			ps.setString(4, city);
			ps.setString(5, state);
			ps.setString(6, pincode);
			ps.setLong(7, societyId);

			return ps.executeUpdate() > 0;

		}	 catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}



	public boolean deleteSocietyById(long societyId) {
	    String query = "DELETE FROM societies WHERE society_id = ?";
	    try (Connection conn = DB_Connection.getConnection();
	         PreparedStatement ps = conn.prepareStatement(query)) {

	        ps.setLong(1, societyId);
	        return ps.executeUpdate() > 0;

	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;
	    }
	}





	}
