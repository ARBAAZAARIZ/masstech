package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.VisitorApprovalLog;
import model.VisitorLogView;
import util.DB_Connection;

public class VisitorApprovalDAO {

	public VisitorApprovalDAO() {
		// TODO Auto-generated constructor stub
	}
	
	public Long getMemberIdByFlatId(long flatId) {
        String sql = "SELECT member_id FROM flat_occupancies WHERE flat_id = ? LIMIT 1";

        try (Connection conn = DB_Connection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, flatId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getLong("member_id");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
	
	public boolean insertVisitorLog(VisitorApprovalLog log) {
        String sql = "INSERT INTO visitor_approval_log (visitor_name, purpose, flat_id, member_id, visitor_profile_photo, status) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DB_Connection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, log.getVisitorName());
            ps.setString(2, log.getPurpose());
            ps.setLong(3, log.getFlatId());
            ps.setLong(4, log.getMemberId());
            ps.setString(5, log.getVisitorProfilePhoto());
            ps.setString(6, log.getStatus());

            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
	
	public List<VisitorLogView> getVisitorLogsBySocietyId(long societyId) {
	    List<VisitorLogView> logs = new ArrayList<VisitorLogView>();

	    String sql = """
	        SELECT 
	        v.visitor_id,
	            v.visitor_name,
	            v.purpose,
	            v.visitor_profile_photo,
	            v.status,
	            f.flat_no,
	            m.full_name
	        FROM visitor_approval_log v
	        JOIN flats f ON v.flat_id = f.flat_id
	        JOIN flat_occupancies fo ON f.flat_id = fo.flat_id AND fo.end_date IS NULL
	        JOIN members m ON fo.member_id = m.member_id
	        JOIN buildings b ON f.building_id = b.building_id
	        WHERE b.society_id = ?
	        ORDER BY v.visitor_id DESC
	    """;

	    try (Connection conn = DB_Connection.getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql)) {

	        ps.setLong(1, societyId);
	        ResultSet rs = ps.executeQuery();

	        while (rs.next()) {
	            VisitorLogView view = new VisitorLogView();
	            view.setVisitorId(rs.getLong("visitor_id"));
	            view.setVisitorName(rs.getString("visitor_name"));
	            view.setPurpose(rs.getString("purpose"));
	            view.setPhotoPath(rs.getString("visitor_profile_photo"));
	            view.setStatus(rs.getString("status"));
	            view.setFlatNo(rs.getString("flat_no"));
	            view.setMemberFullName(rs.getString("full_name"));
	            logs.add(view);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return logs;
	}
	
	public boolean updateVisitorStatus(long visitorId, String newStatus) {
	    String sql = "UPDATE visitor_approval_log SET status = ? WHERE visitor_id = ?";

	    try (Connection conn = DB_Connection.getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql)) {

	        ps.setString(1, newStatus);
	        ps.setLong(2, visitorId);

	        int rows = ps.executeUpdate();
	        return rows > 0;

	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}



}
