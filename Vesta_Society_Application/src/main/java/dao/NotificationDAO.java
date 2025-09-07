package dao;

import model.Notification;
import model.NotificationDetails;
import util.DB_Connection;

import java.sql.*;
import java.util.*;

public class NotificationDAO {
    private final Connection conn = DB_Connection.getConnection();

    public List<Notification> getAllNotifications() throws SQLException {
        String sql = "SELECT * FROM notifications ORDER BY created_at DESC";
        List<Notification> list = new ArrayList<>();

        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Notification n = new Notification();
                n.setNotificationId(rs.getLong("notification_id"));
                n.setUserId(rs.getLong("user_id"));
                n.setMessage(rs.getString("message"));
                n.setReadStatus(rs.getString("read_status"));
                n.setCreatedAt(rs.getTimestamp("created_at"));
                list.add(n);
            }
        }
        return list;
    }

    public void updateReadStatus(Long notificationId, String status) throws SQLException {
        String sql = "UPDATE notifications SET read_status = ? WHERE notification_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, status);
            ps.setLong(2, notificationId);
            ps.executeUpdate();
        }
    }
    
    public int getUnreadCount() throws SQLException {
        String sql = "SELECT COUNT(*) FROM notifications WHERE read_status = 'Unread'";
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        return 0;
    }
    
    public boolean insertNotification(Long userId, String message) {
        String sql = "INSERT INTO notifications (user_id, message) VALUES (?, ?)";

        try (Connection conn = DB_Connection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, userId);
            ps.setString(2, message);

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Error inserting notification: " + e.getMessage());
            return false;
        }
    }
    
    public List<Notification> getNotificationsByUserId(Long userId) {
        List<Notification> list = new ArrayList<>();
        String sql = "SELECT * FROM notifications WHERE user_id = ? ORDER BY created_at DESC";

        try (Connection conn = DB_Connection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, userId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Notification n = new Notification();
                    n.setNotificationId(rs.getLong("notification_id"));
                    n.setUserId(rs.getLong("user_id"));
                    n.setMessage(rs.getString("message"));
                    n.setReadStatus(rs.getString("read_status"));
                    n.setCreatedAt(rs.getTimestamp("created_at"));
                    list.add(n);
                }
            }
        } catch (Exception e) {
            System.out.println("Error fetching notifications: " + e.getMessage());
        }

        return list;
    }
    
    public int getUnreadNotificationCountBySocietyId(int societyId) {
        int count = 0;
        String sql = "SELECT COUNT(*) FROM notifications n " +
                     "inner join users u on n.user_id = u.user_id  " +
                     "inner join members m on u.member_id = m.member_id " +
                     "where m.society_id = ? and n.read_status='Unread'";

        try (Connection conn = DB_Connection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, societyId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    count = rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // or log it properly
        }

        return count;
    }
    
    
    
    public List<NotificationDetails> getNotificationsBySocietyId(int societyId) {
        List<NotificationDetails> list = new ArrayList<>();
        String sql = "SELECT n.notification_id, n.user_id, n.message, n.read_status, n.created_at, " +
                     "m.full_name, m.profile_photo, m.phone, u.username, u.role, m.society_id " +
                     "FROM notifications n " +
                     "JOIN users u ON n.user_id = u.user_id " +
                     "JOIN members m ON u.member_id = m.member_id " +
                     "WHERE m.society_id = ? ORDER BY n.created_at DESC";

        try (Connection conn = DB_Connection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, societyId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    NotificationDetails nd = new NotificationDetails();
                    nd.setNotificationId(rs.getLong("notification_id"));
                    nd.setUserId(rs.getLong("user_id"));
                    nd.setMessage(rs.getString("message"));
                    nd.setReadStatus(rs.getString("read_status"));
                    nd.setCreatedAt(rs.getTimestamp("created_at"));
                    nd.setFullName(rs.getString("full_name"));
                    nd.setProfilePhoto(rs.getString("profile_photo"));
                    nd.setPhone(rs.getString("phone"));
                    nd.setUsername(rs.getString("username"));
                    nd.setRole(rs.getString("role"));
                    nd.setSocietyId(rs.getLong("society_id"));
                    list.add(nd);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
    
    
    public boolean markNotificationAsRead(Long notificationId) {
        String sql = "UPDATE notifications SET read_status = 'Read' WHERE notification_id = ?";

        try (Connection conn = DB_Connection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, notificationId);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    
    




    
}
