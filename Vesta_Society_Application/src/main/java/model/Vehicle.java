package model;

public class Vehicle {

	public Vehicle() {
		
	}
	
	private Long vehicleId;
    private Long memberId;
    private Long flatId;
    private String registrationNo;
    private String type; // TwoWheeler, FourWheeler, Other
    
	public Vehicle(Long vehicleId, Long memberId, Long flatId, String registrationNo, String type) {
		super();
		this.vehicleId = vehicleId;
		this.memberId = memberId;
		this.flatId = flatId;
		this.registrationNo = registrationNo;
		this.type = type;
	}

	public Long getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(Long vehicleId) {
		this.vehicleId = vehicleId;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public Long getFlatId() {
		return flatId;
	}

	public void setFlatId(Long flatId) {
		this.flatId = flatId;
	}

	public String getRegistrationNo() {
		return registrationNo;
	}

	public void setRegistrationNo(String registrationNo) {
		this.registrationNo = registrationNo;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	

}
