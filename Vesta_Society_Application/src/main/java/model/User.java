package model;

import java.sql.Date;

public class User {

	public User() {
		
	}
	
	public User(Long userId, Long memberId, String username, String passwordHash, boolean isActive, Date lastLoginAt) {
		super();
		this.userId = userId;
		this.memberId = memberId;
		this.username = username;
		this.passwordHash = passwordHash;
		this.isActive = isActive;
		this.lastLoginAt = lastLoginAt;
	}
	
	
	
	
	private Long userId;
    private Long memberId;
    private String username;
    private String passwordHash;
    private boolean isActive;
    private Date lastLoginAt;
    
    
    public Long getUserId() {
		return userId;
	}


	public void setUserId(Long userId) {
		this.userId = userId;
	}


	public Long getMemberId() {
		return memberId;
	}


	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPasswordHash() {
		return passwordHash;
	}


	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}


	public boolean isActive() {
		return isActive;
	}


	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}


	public Date getLastLoginAt() {
		return lastLoginAt;
	}


	public void setLastLoginAt(Date lastLoginAt) {
		this.lastLoginAt = lastLoginAt;
	}


	

	

   

}
