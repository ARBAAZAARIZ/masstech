package service;

import dao.NotificationDAO;
import model.Notification;
import model.NotificationDetails;

import java.sql.SQLException;
import java.util.List;

public class NotificationService {
    private final NotificationDAO dao = new NotificationDAO();

    public List<Notification> getAllNotifications() throws SQLException {
        return dao.getAllNotifications();
    }

    public void markAsRead(Long notificationId) throws SQLException {
        dao.updateReadStatus(notificationId, "Read");
    }
    
    public int getUnreadCount() throws SQLException {
        return dao.getUnreadCount();
    }
    
    public boolean sendNotification(Long userId, String message) {
        return dao.insertNotification(userId, message);
    }
    
    public List<Notification> getNotificationsByUserId(Long userId) {
        return dao.getNotificationsByUserId(userId);
    }
    
    public int getUnreadNotificationCountBySocietyId(int societyId) {
    	return dao.getUnreadNotificationCountBySocietyId(societyId);
    }
    
    public List<NotificationDetails> getNotificationsBySocietyId(int societyId) {
        return dao.getNotificationsBySocietyId(societyId);
    }

    public boolean markNotificationAsRead(Long notificationId) {
        return dao.markNotificationAsRead(notificationId);
    }




}
