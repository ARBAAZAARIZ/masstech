package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Complaint;
import model.ComplaintDetails;
import util.DB_Connection;

public class ComplaintDAO {

	 private Connection conn;
	public ComplaintDAO() {
		this.conn = DB_Connection.getConnection();
	}
	
	 public List<Complaint> getAllComplaints() throws SQLException {
	        String sql = "SELECT * FROM complaints ORDER BY created_at DESC";
	        List<Complaint> list = new ArrayList<Complaint>();

	        try (PreparedStatement ps = conn.prepareStatement(sql);
	             ResultSet rs = ps.executeQuery()) {
	            while (rs.next()) {
	                Complaint c = new Complaint();
	                c.setComplaintId(rs.getLong("complaint_id"));
	                c.setSocietyId(rs.getLong("society_id"));
	                c.setRaisedByUserId(rs.getLong("raised_by_user_id"));
	                c.setFlatId(rs.getLong("flat_id"));
	                c.setCategory(rs.getString("category"));
	                c.setTitle(rs.getString("title"));
	                c.setDescription(rs.getString("description"));
	                c.setStatus(rs.getString("status"));
	                c.setCreatedAt(rs.getTimestamp("created_at"));
	                list.add(c);
	            }
	        }
	        return list;
	    }
	 
	 public void updateStatus(Long complaintId, String newStatus) throws SQLException {
	        String sql = "UPDATE complaints SET status = ? WHERE complaint_id = ?";
	        try (PreparedStatement ps = conn.prepareStatement(sql)) {
	            ps.setString(1, newStatus);
	            ps.setLong(2, complaintId);
	            ps.executeUpdate();
	        }
	    }
	 
	 public Complaint getById(Long complaintId) throws SQLException {
	        String sql = "SELECT * FROM complaints WHERE complaint_id = ?";
	        try (PreparedStatement ps = conn.prepareStatement(sql)) {
	            ps.setLong(1, complaintId);
	            try (ResultSet rs = ps.executeQuery()) {
	                if (rs.next()) {
	                    Complaint c = new Complaint();
	                    c.setComplaintId(rs.getLong("complaint_id"));
	                    c.setSocietyId(rs.getLong("society_id"));
	                    c.setRaisedByUserId(rs.getLong("raised_by_user_id"));
	                    c.setFlatId(rs.getLong("flat_id"));
	                    c.setCategory(rs.getString("category"));
	                    c.setTitle(rs.getString("title"));
	                    c.setDescription(rs.getString("description"));
	                    c.setStatus(rs.getString("status"));
	                    c.setCreatedAt(rs.getTimestamp("created_at"));
	                    return c;
	                }
	            }
	        }
	        return null;
	    }
	 
	 public List<ComplaintDetails> getComplaintsBySocietyId(int societyId) {
		    List<ComplaintDetails> list = new ArrayList<>();
		    String sql = "SELECT c.complaint_id, c.society_id, c.raised_by_user_id, u.username, c.flat_id, f.flat_no, " +
		                 "c.category, c.title, c.description, c.status " +
		                 "FROM complaints c " +
		                 "JOIN users u ON c.raised_by_user_id = u.user_id " +
		                 "JOIN flats f ON c.flat_id = f.flat_id " +
		                 "WHERE c.society_id = ?";

		    try (Connection conn = DB_Connection.getConnection();
		         PreparedStatement ps = conn.prepareStatement(sql)) {

		        ps.setInt(1, societyId);
		        try (ResultSet rs = ps.executeQuery()) {
		            while (rs.next()) {
		                ComplaintDetails cd = new ComplaintDetails();
		                cd.setComplaintId(rs.getLong("complaint_id"));
		                cd.setSocietyId(rs.getLong("society_id"));
		                cd.setUserId(rs.getLong("raised_by_user_id"));
		                cd.setUserName(rs.getString("username"));
		                cd.setFlatId(rs.getLong("flat_id"));
		                cd.setFlatNo(rs.getString("flat_no"));
		                cd.setCategory(rs.getString("category"));
		                cd.setTitle(rs.getString("title"));
		                cd.setDescription(rs.getString("description"));
		                cd.setStatus(rs.getString("status"));
		                list.add(cd);
		            }
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }

		    return list;
		}
	 
	 public boolean updateComplaintStatusBySocietyManager(Long complaintId, String status) {
		    String sql = "UPDATE complaints SET status = ? WHERE complaint_id = ?";

		    try (Connection conn = DB_Connection.getConnection();
		         PreparedStatement ps = conn.prepareStatement(sql)) {

		        ps.setString(1, status);
		        ps.setLong(2, complaintId);
		        return ps.executeUpdate() > 0;

		    } catch (SQLException e) {
		        e.printStackTrace();
		        return false;
		    }
		}



}
