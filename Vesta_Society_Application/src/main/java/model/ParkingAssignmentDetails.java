package model;
import java.sql.Date;
public class ParkingAssignmentDetails {

	public ParkingAssignmentDetails() {
		// TODO Auto-generated constructor stub
	}
	
	 private Long vehicleId;
	    private String memberFullName;
	    private String flatNo;
	    private String type;
	    private Long societyId;
	    private String identifier;
	    private boolean isCovered;
	    private Date startDate;
	    private Date endDate;
		public ParkingAssignmentDetails(Long vehicleId, String memberFullName, String flatNo, String type,
				Long societyId, String identifier, boolean isCovered, Date startDate, Date endDate) {
			super();
			this.vehicleId = vehicleId;
			this.memberFullName = memberFullName;
			this.flatNo = flatNo;
			this.type = type;
			this.societyId = societyId;
			this.identifier = identifier;
			this.isCovered = isCovered;
			this.startDate = startDate;
			this.endDate = endDate;
		}
		public Long getVehicleId() {
			return vehicleId;
		}
		public void setVehicleId(Long vehicleId) {
			this.vehicleId = vehicleId;
		}
		public String getMemberFullName() {
			return memberFullName;
		}
		public void setMemberFullName(String memberFullName) {
			this.memberFullName = memberFullName;
		}
		public String getFlatNo() {
			return flatNo;
		}
		public void setFlatNo(String flatNo) {
			this.flatNo = flatNo;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public Long getSocietyId() {
			return societyId;
		}
		public void setSocietyId(Long societyId) {
			this.societyId = societyId;
		}
		public String getIdentifier() {
			return identifier;
		}
		public void setIdentifier(String identifier) {
			this.identifier = identifier;
		}
		public boolean isCovered() {
			return isCovered;
		}
		public void setCovered(boolean isCovered) {
			this.isCovered = isCovered;
		}
		public Date getStartDate() {
			return startDate;
		}
		public void setStartDate(Date startDate) {
			this.startDate = startDate;
		}
		public Date getEndDate() {
			return endDate;
		}
		public void setEndDate(Date endDate) {
			this.endDate = endDate;
		}
	    
	    
	    

}
