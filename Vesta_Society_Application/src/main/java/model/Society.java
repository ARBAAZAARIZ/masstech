package model;

import java.sql.Date;

public class Society {

	public Society() {
		
	}
	
	public Society(Long societyId, String name, String addressLine1, String addressLine2, String city, String state,
			String pincode, Date createdAt, Date updatedAt) {
		super();
		this.societyId = societyId;
		this.name = name;
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.city = city;
		this.state = state;
		this.pincode = pincode;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
	
	
	 private Long societyId;
	    private String name;
	    private String addressLine1;
	    private String addressLine2;
	    private String city;
	    private String state;
	    private String pincode;
	    private Date createdAt;
	    private Date updatedAt;
		public Long getSocietyId() {
			return societyId;
		}
		public void setSocietyId(Long societyId) {
			this.societyId = societyId;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getAddressLine1() {
			return addressLine1;
		}
		public void setAddressLine1(String addressLine1) {
			this.addressLine1 = addressLine1;
		}
		public String getAddressLine2() {
			return addressLine2;
		}
		public void setAddressLine2(String addressLine2) {
			this.addressLine2 = addressLine2;
		}
		public String getCity() {
			return city;
		}
		public void setCity(String city) {
			this.city = city;
		}
		public String getState() {
			return state;
		}
		public void setState(String state) {
			this.state = state;
		}
		public String getPincode() {
			return pincode;
		}
		public void setPincode(String pincode) {
			this.pincode = pincode;
		}
		public Date getCreatedAt() {
			return createdAt;
		}
		public void setCreatedAt(Date createdAt) {
			this.createdAt = createdAt;
		}
		public Date getUpdatedAt() {
			return updatedAt;
		}
		public void setUpdatedAt(Date updatedAt) {
			this.updatedAt = updatedAt;
		}
		
	    
	    

}
