package model;

public class Amenity {

	private Long amenityId;
    private Long societyId;
    private String name;
    private double amount;
    private boolean bookingRequired;
    
	public Amenity() {
		
	}

	public Amenity(Long amenityId, Long societyId, String name, boolean bookingRequired,double amount) {
		super();
		this.amenityId = amenityId;
		this.societyId = societyId;
		this.name = name;
		this.amount=amount;
		this.bookingRequired = bookingRequired;
	}

	public Long getAmenityId() {
		return amenityId;
	}

	public void setAmenityId(Long amenityId) {
		this.amenityId = amenityId;
	}

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

	public boolean isBookingRequired() {
		return bookingRequired;
	}

	public void setBookingRequired(boolean bookingRequired) {
		this.bookingRequired = bookingRequired;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	
	
	

}
