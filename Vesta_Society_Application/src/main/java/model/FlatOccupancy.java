package model;

import java.sql.Date;

public class FlatOccupancy {

	public FlatOccupancy() {
		// TODO Auto-generated constructor stub
	}
	
	private Long occupancyId;
    private Long flatId;
    private Long memberId;
    private String type; // "Owner" or "Tenant"
    private Date startDate;
    private Date endDate;
	
	 public FlatOccupancy(Long occupancyId, Long flatId, Long memberId, String type, Date startDate, Date endDate) {
	        this.occupancyId = occupancyId;
	        this.flatId = flatId;
	        this.memberId = memberId;
	        this.type = type;
	        this.startDate = startDate;
	        this.endDate = endDate;
	    }

	 public Long getOccupancyId() {
		 return occupancyId;
	 }

	 public void setOccupancyId(Long occupancyId) {
		 this.occupancyId = occupancyId;
	 }

	 public Long getFlatId() {
		 return flatId;
	 }

	 public void setFlatId(Long flatId) {
		 this.flatId = flatId;
	 }

	 public Long getMemberId() {
		 return memberId;
	 }

	 public void setMemberId(Long memberId) {
		 this.memberId = memberId;
	 }

	 public String getType() {
		 return type;
	 }

	 public void setType(String type) {
		 this.type = type;
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
