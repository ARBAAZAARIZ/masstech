package model;

import java.sql.Timestamp;

public class Notification {

	public Notification() {
		
	}
	
	 private Long notificationId;
	    private Long userId;
	    private String message;
	    private String readStatus;
	    private Timestamp createdAt;
		public Notification(Long notificationId, Long userId, String message, String readStatus, Timestamp createdAt) {
			super();
			this.notificationId = notificationId;
			this.userId = userId;
			this.message = message;
			this.readStatus = readStatus;
			this.createdAt = createdAt;
		}
		public Long getNotificationId() {
			return notificationId;
		}
		public void setNotificationId(Long notificationId) {
			this.notificationId = notificationId;
		}
		public Long getUserId() {
			return userId;
		}
		public void setUserId(Long userId) {
			this.userId = userId;
		}
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
		public String getReadStatus() {
			return readStatus;
		}
		public void setReadStatus(String readStatus) {
			this.readStatus = readStatus;
		}
		public Timestamp getCreatedAt() {
			return createdAt;
		}
		public void setCreatedAt(Timestamp createdAt) {
			this.createdAt = createdAt;
		}
		
		
	    
	    

}
