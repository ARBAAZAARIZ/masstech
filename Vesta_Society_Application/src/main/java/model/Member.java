package model;

import java.sql.Date;

public class Member {

	public Member() {
		
	}
	
	private Long memberId;
    private Long societyId;
    private String fullName;
    private String email;
    private String phone;
    private String status;
    private Date createdAt;
    private String pofile_photo;


	public Member(Long memberId, Long societyId, String fullName, String email, String phone, String status,
			Date createdAt) {
		super();
		this.memberId = memberId;
		this.societyId = societyId;
		this.fullName = fullName;
		this.email = email;
		this.phone = phone;
		this.status = status;
		this.createdAt = createdAt;
		
	}

	public String getPofile_photo() {
		return pofile_photo;
	}

	public void setPofile_photo(String pofile_photo) {
		this.pofile_photo = pofile_photo;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public Long getSocietyId() {
		return societyId;
	}

	public void setSocietyId(Long societyId) {
		this.societyId = societyId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	
    
    
    

}
