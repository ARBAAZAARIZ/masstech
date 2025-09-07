package model;

import java.sql.Timestamp;

public class AuthUser {

    private Long userId;
    private String username;
    private String email;
    private String role;
    private String fullName;
    private String phone;
    private String status;
    private Timestamp lastLoginAt;
    private String profile_photo;
    private int societyId;
    private int memberId;

    public AuthUser() {
    }

    public AuthUser(Long userId, String username, String email, String role,
                    String fullName, String phone, String status, Timestamp lastLoginAt, String profile_photo,int societyId,int memberId) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.role = role;
        this.fullName = fullName;
        this.phone = phone;
        this.status = status;
        this.lastLoginAt = lastLoginAt;
        this.profile_photo=profile_photo;	
        this.societyId=societyId;
        this.memberId=memberId;
        
    }

    public int getSocietyId() {
		return societyId;
	}

	public void setSocietyId(int societyId) {
		this.societyId = societyId;
	}

	public String getProfile_photo() {
		return profile_photo;
	}

	public void setProfile_photo(String profile_photo) {
		this.profile_photo = profile_photo;
	}

	public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }

    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getLastLoginAt() {
        return lastLoginAt;
    }
    public void setLastLoginAt(Timestamp lastLoginAt) {
        this.lastLoginAt = lastLoginAt;
    }

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
}
