package model;

import java.sql.Timestamp;

public class Users {

    private Long userID;
    private String username;
    private String role;
    private Timestamp lastLogin;
    private Long memberID;
    private String email;
    private String phoneNumber;
    private String status;
    
    private int societyId;
    private String societyName;
    String profile_photo;
    
    public Users(Long userID, String username, String role, Timestamp lastLogin, Long memberID, String email,
			String phoneNumber, String status, int societyId, String societyName,String profile_photo) {
		super();
		this.userID = userID;
		this.username = username;
		this.role = role;
		this.lastLogin = lastLogin;
		this.memberID = memberID;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.status = status;
		this.societyId = societyId;
		this.societyName = societyName;
		this.profile_photo=profile_photo;
	}

	public String getProfile_photo() {
		return profile_photo;
	}

	public void setProfile_photo(String profile_photo) {
		this.profile_photo = profile_photo;
	}

	public int getSocietyId() {
		return societyId;
	}

	public void setSocietyId(int societyId) {
		this.societyId = societyId;
	}

	public String getSocietyName() {
		return societyName;
	}

	public void setSocietyName(String societyName) {
		this.societyName = societyName;
	}

	

    public Users() {
        // Default constructor
    }

    // Getters and Setters
    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
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

    public Timestamp getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Timestamp lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Long getMemberID() {
        return memberID;
    }

    public void setMemberID(Long memberID) {
        this.memberID = memberID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
