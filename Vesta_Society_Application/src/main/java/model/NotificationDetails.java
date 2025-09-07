package model;

import java.sql.Timestamp;

public class NotificationDetails {

	public NotificationDetails() {
		// TODO Auto-generated constructor stub
	}
	
	private Long notificationId;
    private Long userId;
    private String message;
    private String readStatus;
    private Timestamp createdAt;
    private String fullName;
    private String profilePhoto;
    private String phone;
    private String username;
    private String role;
    private Long societyId;
	public NotificationDetails(Long notificationId, Long userId, String message, String readStatus, Timestamp createdAt,
			String fullName, String profilePhoto, String phone, String username, String role, Long societyId) {
		super();
		this.notificationId = notificationId;
		this.userId = userId;
		this.message = message;
		this.readStatus = readStatus;
		this.createdAt = createdAt;
		this.fullName = fullName;
		this.profilePhoto = profilePhoto;
		this.phone = phone;
		this.username = username;
		this.role = role;
		this.societyId = societyId;
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
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getProfilePhoto() {
		return profilePhoto;
	}
	public void setProfilePhoto(String profilePhoto) {
		this.profilePhoto = profilePhoto;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public Long getSocietyId() {
		return societyId;
	}
	public void setSocietyId(Long societyId) {
		this.societyId = societyId;
	}
    
    

}
