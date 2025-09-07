	package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Member;
import util.DB_Connection;

public class MemberDAO {

	public MemberDAO() {
		
	}
	
	public Member getMemberById(int memberId) {
	    String sql = "SELECT * FROM members WHERE member_id = ?";
System.out.println(memberId +" from dao");
	    try (Connection conn = DB_Connection.getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql)) {

	        ps.setInt(1, memberId);

	        try (ResultSet rs = ps.executeQuery()) {
	            if (rs.next()) {
	                Member member = new Member();
	                member.setMemberId(rs.getLong("member_id"));
	                member.setSocietyId(rs.getLong("society_id"));
	                member.setFullName(rs.getString("full_name"));
	                member.setEmail(rs.getString("email"));
	                member.setPhone(rs.getString("phone"));
	                member.setStatus(rs.getString("status"));
	                member.setCreatedAt(rs.getDate("created_at"));
	                member.setPofile_photo(rs.getString("profile_photo")); // typo preserved as per your field name
	                System.out.println(member.getMemberId());
	                return member;
	            }
	        }
	    } catch (Exception e) {
	        System.out.println("Error fetching member: " + e.getMessage());
	    }

	    return null;
	}
	
	public boolean updateMember(int memberId, String fullName, String email, String phone, String photoPath) {
        String sql = "UPDATE members SET full_name = ?, email = ?, phone = ?, profile_photo = ? WHERE member_id = ?";

        try (Connection conn = DB_Connection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, fullName);
            ps.setString(2, email);
            ps.setString(3, phone);
            ps.setString(4, photoPath);
            ps.setLong(5, memberId);

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Error updating member: " + e.getMessage());
            return false;
        }
    }
	
	public List<Member> getMembersBySocietyId(int societyId) {
	    List<Member> memberList = new ArrayList<Member>();

	    String sql = "SELECT member_id, society_id, full_name, email, phone, status, created_at, profile_photo " +
	                 "FROM members WHERE society_id = ? ORDER BY created_at DESC";

	    try (Connection conn = DB_Connection.getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql)) {

	        ps.setLong(1, societyId);

	        try (ResultSet rs = ps.executeQuery()) {
	            while (rs.next()) {
	                Member m = new Member();
	                m.setMemberId(rs.getLong("member_id"));
	                m.setSocietyId(rs.getLong("society_id"));
	                m.setFullName(rs.getString("full_name"));
	                m.setEmail(rs.getString("email"));
	                m.setPhone(rs.getString("phone"));
	                m.setStatus(rs.getString("status"));
	                m.setCreatedAt(rs.getDate("created_at"));
	                m.setPofile_photo(rs.getString("profile_photo"));

	                memberList.add(m);
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace(); // Replace with proper logging if needed
	    }

	    return memberList;
	}
	
	public boolean updateMember(Member member) {
	    String sql = "UPDATE members SET full_name = ?, email = ?, phone = ?, status = ? WHERE member_id = ?";

	    try (Connection conn = DB_Connection.getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql)) {

	        ps.setString(1, member.getFullName());
	        ps.setString(2, member.getEmail());
	        ps.setString(3, member.getPhone());
	        ps.setString(4, member.getStatus());
	        ps.setLong(5, member.getMemberId());

	        int rowsAffected = ps.executeUpdate();
	        return rowsAffected > 0;

	    } catch (SQLException e) {
	        e.printStackTrace(); // Replace with proper logging if needed
	        return false;
	    }
	}
	
	public List<Member> getMembersByNameFragment(long societyId, String nameFragment) {
	    List<Member> members = new ArrayList<>();

	    String sql = """
	    		   SELECT * FROM members 
	    		   WHERE society_id = ? 
	    		   AND full_name LIKE ? 
	    		   ORDER BY full_name ASC
	    		""";


	    try (Connection conn = DB_Connection.getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql)) {

	        ps.setLong(1, societyId);
	        ps.setString(2, "%" + nameFragment + "%");

	        ResultSet rs = ps.executeQuery();
	        while (rs.next()) {
	            Member m = new Member();
	            m.setMemberId(rs.getLong("member_id"));
	            m.setSocietyId(rs.getLong("society_id"));
	            m.setFullName(rs.getString("full_name"));
	            m.setEmail(rs.getString("email"));
	            m.setPhone(rs.getString("phone"));
	            m.setStatus(rs.getString("status"));
	            m.setCreatedAt(new Date(rs.getTimestamp("created_at").getTime()));
	            m.setPofile_photo(rs.getString("profile_photo")); // typo fix if needed
	            members.add(m);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return members;
	}






}
