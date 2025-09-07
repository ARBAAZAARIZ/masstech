package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import model.Users;
import util.DB_Connection;

public class UserDAO {

    public List<Users> getAllUsers() {
        List<Users> userList = new ArrayList<>();

        String query = "SELECT " +
        	    "u.user_id AS userID, " +
        	    "u.username AS username, " +
        	    "u.role AS role, " +
        	    "u.last_login_at AS lastLogin, " +
        	    "m.member_id AS memberID, " +
        	    "m.email AS email, " +
        	    "m.phone AS phoneNumber, " +
        	    "m.status AS status, " +
        	    "m.profile_photo AS profile_photo, " +  
        	    "s.society_id AS societyId, " +
        	    "s.name AS societyName " +
        	    "FROM users u " +
        	    "INNER JOIN members m ON u.member_id = m.member_id " +
        	    "INNER JOIN societies s ON m.society_id = s.society_id";


        try (Connection conn = DB_Connection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Long userID = rs.getLong("userID");
                String username = rs.getString("username");
                String role = rs.getString("role");
                Timestamp lastLogin = rs.getTimestamp("lastLogin");
                Long memberID = rs.getLong("memberID");
                String email = rs.getString("email");
                String phoneNumber = rs.getString("phoneNumber");
                String status = rs.getString("status");
                int societyId = rs.getInt("societyId");
                String societyName = rs.getString("societyName");
                String profile_photo=rs.getString("profile_photo");

                Users user = new Users(userID, username, role, lastLogin, memberID, email, phoneNumber, status, societyId, societyName,profile_photo);
                userList.add(user);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return userList;
    }
    
    public int insertMember(String fullName, String email, int societyId) {
        String query = "INSERT INTO members (full_name, email, society_id) VALUES (?, ?, ?)";
        try (Connection conn = DB_Connection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, fullName);
            ps.setString(2, email);
            ps.setInt(3, societyId);
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1); // Assuming member_id is the first column
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    
    public int insertUser(String username, String password, int memberId, String role) {
        String query = "INSERT INTO users (username, password_hash, member_id, role) VALUES (?, ?, ?, ?)";
        try (Connection conn = DB_Connection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, username);
            ps.setString(2, password);
            ps.setInt(3, memberId);
            ps.setString(4, role); 

            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public Users fetchUserDetails(long userId) {
        Users user = null;

        String query = "SELECT " +
                "u.user_id AS userID, " +
                "u.username AS username, " +
                "u.role AS role, " +
                "u.last_login_at AS lastLogin, " +
                "m.member_id AS memberID, " +
                "m.email AS email, " +
                "m.phone AS phoneNumber, " +
                "m.status AS status, " +
                "m.profile_photo AS profile_photo, " +
                "s.society_id AS societyId, " +
                "s.name AS societyName " +
                "FROM users u " +
                "INNER JOIN members m ON u.member_id = m.member_id " +
                "INNER JOIN societies s ON m.society_id = s.society_id " +
                "WHERE u.user_id = ?";

        try (Connection conn = DB_Connection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setLong(1, userId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                user = new Users();
                user.setUserID(rs.getLong("userID"));
                user.setUsername(rs.getString("username"));
                user.setRole(rs.getString("role"));
                user.setLastLogin(rs.getTimestamp("lastLogin"));
                user.setMemberID(rs.getLong("memberID"));
                user.setEmail(rs.getString("email"));
                user.setPhoneNumber(rs.getString("phoneNumber"));
                user.setStatus(rs.getString("status"));
                user.setProfile_photo(rs.getString("profile_photo"));
                user.setSocietyId(rs.getInt("societyId"));
                user.setSocietyName(rs.getString("societyName"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }
    
    
    public boolean updateUserDetails(long userId, long memberId, String role, String status, int societyId) {
        String updateUserQuery = "UPDATE users SET role = ? WHERE user_id = ?";
        String updateMemberQuery = "UPDATE members SET status = ?, society_id = ? WHERE member_id = ?";

        try (Connection conn = DB_Connection.getConnection()) {
            conn.setAutoCommit(false); // Start transaction

            try (
                PreparedStatement psUser = conn.prepareStatement(updateUserQuery);
                PreparedStatement psMember = conn.prepareStatement(updateMemberQuery)
            ) {
                // Update users table
                psUser.setString(1, role);
                psUser.setLong(2, userId);
                psUser.executeUpdate();

                // Update members table
                psMember.setString(1, status);
                psMember.setInt(2, societyId);
                psMember.setLong(3, memberId);
                psMember.executeUpdate();

                conn.commit(); // Commit both updates
                return true;

            } catch (Exception e) {
                conn.rollback(); // Rollback on failure
                e.printStackTrace();
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean deleteMemberById(long memberId) {
        String query = "DELETE FROM members WHERE member_id = ?";

        try (Connection conn = DB_Connection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setLong(1, memberId);
            int rowsAffected = ps.executeUpdate();

            return rowsAffected > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    
    public Long getMemberIdByUserId(long userId) {
        String query = "SELECT member_id FROM users WHERE user_id = ?";
        try (Connection conn = DB_Connection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setLong(1, userId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getLong("member_id");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public String getEmailByUsername(String username) throws SQLException {
        String sql = "SELECT m.email FROM users u JOIN members m ON u.member_id = m.member_id WHERE u.username = ?";
        
        try (Connection conn = DB_Connection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, username);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("email");
                }
            }
        }
        return null; // Not found
    }
    
    public Long getMemberIdByUsername(String username) throws SQLException {
        String sql = "SELECT member_id FROM users WHERE username = ?";
        
        try (Connection conn = DB_Connection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, username);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getLong("member_id");
                }
            }
        }
        return null; // Not found
    }
    
    public boolean changePassword(Long userId, String oldPassword, String newPassword) {
     
        String sql = "UPDATE users SET password_hash = ? WHERE user_id = ? AND password_hash = ?";
        try (Connection conn = DB_Connection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, newPassword);
            ps.setLong(2, userId);
            ps.setString(3, oldPassword);

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Error changing password: " + e.getMessage());
            return false;
        }
    }
    
    public List<Users> getUsersBySocietyId(int societyId) {
        List<Users> usersList = new ArrayList<>();

        String sql = "SELECT u.user_id, u.username, u.role, u.last_login_at, " +
                     "m.member_id, m.email, m.phone, m.status, m.society_id, s.name AS society_name, m.profile_photo " +
                     "FROM users u " +
                     "JOIN members m ON u.member_id = m.member_id " +
                     "JOIN societies s ON m.society_id = s.society_id " +
                     "WHERE m.society_id = ?";

        try (Connection conn = DB_Connection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, societyId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Users user = new Users();
                    user.setUserID(rs.getLong("user_id"));
                    user.setUsername(rs.getString("username"));
                    user.setRole(rs.getString("role"));
                    user.setLastLogin(rs.getTimestamp("last_login_at"));
                    user.setMemberID(rs.getLong("member_id"));
                    user.setEmail(rs.getString("email"));
                    user.setPhoneNumber(rs.getString("phone"));
                    user.setStatus(rs.getString("status"));
                    user.setSocietyId(rs.getInt("society_id"));
                    user.setSocietyName(rs.getString("society_name"));
                    user.setProfile_photo(rs.getString("profile_photo"));

                    usersList.add(user);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // You can replace this with proper logging
        }

        return usersList;
    }



    public boolean changePasswordForSocietymanager(long userId, String password) {
    	System.out.println(userId + " from changePasswordForSocietymanager method of user dao");
        String sql = "UPDATE users SET password_hash = ? WHERE user_id = ? ";

        try (Connection conn = DB_Connection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, password);
            ps.setLong(2, userId);

            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    







}
