package model;

public class VisitorLogView {

	public VisitorLogView() {
		// TODO Auto-generated constructor stub
	}
	    private long visitorId; 
	    private String visitorName;
	    public long getVisitorId() {
			return visitorId;
		}
		public void setVisitorId(long visitorId) {
			this.visitorId = visitorId;
		}
		private String purpose;
	    private String flatNo;
	    private String memberFullName;
	    private String photoPath;
	    private String status;
		public VisitorLogView(String visitorName, String purpose, String flatNo, String memberFullName,
				String photoPath, String status) {
			super();
			this.visitorName = visitorName;
			this.purpose = purpose;
			this.flatNo = flatNo;
			this.memberFullName = memberFullName;
			this.photoPath = photoPath;
			this.status = status;
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
		public String getFlatNo() {
			return flatNo;
		}
		public void setFlatNo(String flatNo) {
			this.flatNo = flatNo;
		}
		public String getMemberFullName() {
			return memberFullName;
		}
		public void setMemberFullName(String memberFullName) {
			this.memberFullName = memberFullName;
		}
		public String getPhotoPath() {
			return photoPath;
		}
		public void setPhotoPath(String photoPath) {
			this.photoPath = photoPath;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
	    
	    

}
