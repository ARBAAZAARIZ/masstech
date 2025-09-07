package model;

public class ComplaintDetails {

	public ComplaintDetails() {
		// TODO Auto-generated constructor stub
	}

	private Long complaintId;
    private Long societyId;
    private Long userId;
    private String userName;
    private Long flatId;
    private String flatNo;
    private String category;
    private String title;
    private String description;
    private String status;
	public ComplaintDetails(Long complaintId, Long societyId, Long userId, String userName, Long flatId, String flatNo,
			String category, String title, String description, String status) {
		super();
		this.complaintId = complaintId;
		this.societyId = societyId;
		this.userId = userId;
		this.userName = userName;
		this.flatId = flatId;
		this.flatNo = flatNo;
		this.category = category;
		this.title = title;
		this.description = description;
		this.status = status;
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
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Long getFlatId() {
		return flatId;
	}
	public void setFlatId(Long flatId) {
		this.flatId = flatId;
	}
	public String getFlatNo() {
		return flatNo;
	}
	public void setFlatNo(String flatNo) {
		this.flatNo = flatNo;
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
    
    
	
}
