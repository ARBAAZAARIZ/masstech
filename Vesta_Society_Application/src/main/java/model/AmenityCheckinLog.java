package model;

import java.sql.Timestamp;

public class AmenityCheckinLog {

	public AmenityCheckinLog() {
		// TODO Auto-generated constructor stub
	}
	private Long checkinId;
    private Long memberId;
    private Long amenityId;
    private Timestamp checkinTime;
    private Timestamp checkoutTime;
    private Long guardId;
    private Long societyId;
    private String status;
    private String remarks;
    private Timestamp createdAt;
    String amanityName;
	public AmenityCheckinLog(Long checkinId, Long memberId, Long amenityId, Timestamp checkinTime,
			Timestamp checkoutTime, Long guardId, Long societyId, String status, String remarks, Timestamp createdAt,String amanityName) {
		super();
		this.checkinId = checkinId;
		this.memberId = memberId;
		this.amenityId = amenityId;
		this.amanityName=amanityName;
		this.checkinTime = checkinTime;
		this.checkoutTime = checkoutTime;
		this.guardId = guardId;
		this.societyId = societyId;
		this.status = status;
		this.remarks = remarks;
		this.createdAt = createdAt;
	}
	public Long getCheckinId() {
		return checkinId;
	}
	public void setCheckinId(Long checkinId) {
		this.checkinId = checkinId;
	}
	public Long getMemberId() {
		return memberId;
	}
	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}
	public Long getAmenityId() {
		return amenityId;
	}
	public void setAmenityId(Long amenityId) {
		this.amenityId = amenityId;
	}
	public Timestamp getCheckinTime() {
		return checkinTime;
	}
	public void setCheckinTime(Timestamp checkinTime) {
		this.checkinTime = checkinTime;
	}
	public Timestamp getCheckoutTime() {
		return checkoutTime;
	}
	public void setCheckoutTime(Timestamp checkoutTime) {
		this.checkoutTime = checkoutTime;
	}
	public Long getGuardId() {
		return guardId;
	}
	public void setGuardId(Long guardId) {
		this.guardId = guardId;
	}
	public Long getSocietyId() {
		return societyId;
	}
	public void setSocietyId(Long societyId) {
		this.societyId = societyId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Timestamp getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
	public String getAmanityName() {
		return amanityName;
	}
	public void setAmanityName(String amanityName) {
		this.amanityName = amanityName;
	}
    
    
}
