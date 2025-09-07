package model;

public class VisitorApprovalLog {

	public VisitorApprovalLog() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	private long visitorId;
    private String visitorName;
    private String purpose;
    private long flatId;
    private long memberId;
    private String visitorProfilePhoto;
    private String status;
	public VisitorApprovalLog(long visitorId, String visitorName, String purpose, long flatId, long memberId,
			String visitorProfilePhoto, String status) {
		super();
		this.visitorId = visitorId;
		this.visitorName = visitorName;
		this.purpose = purpose;
		this.flatId = flatId;
		this.memberId = memberId;
		this.visitorProfilePhoto = visitorProfilePhoto;
		this.status = status;
	}
	public long getVisitorId() {
		return visitorId;
	}
	public void setVisitorId(long visitorId) {
		this.visitorId = visitorId;
	}
	public String getVisitorName() {
		return visitorName;
	}
	public void setVisitorName(String visitorName) {
		this.visitorName = visitorName;
	}
	public String getPurpose() {
		return purpose;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	public long getFlatId() {
		return flatId;
	}
	public void setFlatId(long flatId) {
		this.flatId = flatId;
	}
	public long getMemberId() {
		return memberId;
	}
	public void setMemberId(long memberId) {
		this.memberId = memberId;
	}
	public String getVisitorProfilePhoto() {
		return visitorProfilePhoto;
	}
	public void setVisitorProfilePhoto(String visitorProfilePhoto) {
		this.visitorProfilePhoto = visitorProfilePhoto;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
    
    
    

}
