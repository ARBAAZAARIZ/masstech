package model;

import java.sql.Timestamp;

public class AmenityBooking {

	public AmenityBooking() {
		// TODO Auto-generated constructor stub
	}
	private Long bookingId;
    private Long amenityId;
    private Long userId;
    private double amount;
    private Long societyId;
    private String amenityName;
    private Timestamp startTime;
    private Timestamp endTime;
    private String status;
	public AmenityBooking(Long bookingId, Long amenityId, Long userId, double amount, Long societyId,
			String amenityName, Timestamp startTime, Timestamp endTime, String status) {
		super();
		this.bookingId = bookingId;
		this.amenityId = amenityId;
		this.userId = userId;
		this.amount = amount;
		this.societyId = societyId;
		this.amenityName = amenityName;
		this.startTime = startTime;
		this.endTime = endTime;
		this.status = status;
	}
	public Long getBookingId() {
		return bookingId;
	}
	public void setBookingId(Long bookingId) {
		this.bookingId = bookingId;
	}
	public Long getAmenityId() {
		return amenityId;
	}
	public void setAmenityId(Long amenityId) {
		this.amenityId = amenityId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public Long getSocietyId() {
		return societyId;
	}
	public void setSocietyId(Long societyId) {
		this.societyId = societyId;
	}
	public String getAmenityName() {
		return amenityName;
	}
	public void setAmenityName(String amenityName) {
		this.amenityName = amenityName;
	}
	public Timestamp getStartTime() {
		return startTime;
	}
	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}
	public Timestamp getEndTime() {
		return endTime;
	}
	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
    
    

}
