package model;

import java.sql.Timestamp;

public class Complaint {

	public Complaint() {
		
	}
	
	private Long complaintId;
    private Long societyId;
    private Long raisedByUserId;
    private Long flatId;
    private String category;
    private String title;
    private String description;
    private String status;
    private Timestamp createdAt;
    
    
	public Complaint(Long complaintId, Long societyId, Long raisedByUserId, Long flatId, String category, String title,
			String description, String status, Timestamp createdAt) {
		super();
		this.complaintId = complaintId;
		this.societyId = societyId;
		this.raisedByUserId = raisedByUserId;
		this.flatId = flatId;
		this.category = category;
		this.title = title;
		this.description = description;
		this.status = status;
		this.createdAt = createdAt;
	}


	public Long getComplaintId() {
		return complaintId;
	}


	public void setComplaintId(Long complaintId) {
		this.complaintId = complaintId;
	}


	public Long getSocietyId() {
		return societyId;
	}


	public void setSocietyId(Long societyId) {
		this.societyId = societyId;
	}


	public Long getRaisedByUserId() {
		return raisedByUserId;
	}


	public void setRaisedByUserId(Long raisedByUserId) {
		this.raisedByUserId = raisedByUserId;
	}


	public Long getFlatId() {
		return flatId;
	}


	public void setFlatId(Long flatId) {
		this.flatId = flatId;
	}


	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public Timestamp getCreatedAt() {
		return createdAt;
	}


	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
	
	
	
	

}
