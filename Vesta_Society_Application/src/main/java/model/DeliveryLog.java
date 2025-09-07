package model;

import java.sql.Timestamp;

public class DeliveryLog {

	public DeliveryLog() {
		// TODO Auto-generated constructor stub
	}
	
	private Long deliveryLogId;
    private Long societyId;
    private String deliveryPersonName;
    private String mobile;
    private String deliveryType;
    private Long flatId;
    private String flatNo;
    private String buildingName;
    private Timestamp checkIn;
    
	public DeliveryLog(Long deliveryLogId, Long societyId, String deliveryPersonName, String mobile,
			String deliveryType, Long flatId, String flatNo, String buildingName, Timestamp checkIn) {
		super();
		this.deliveryLogId = deliveryLogId;
		this.societyId = societyId;
		this.deliveryPersonName = deliveryPersonName;
		this.mobile = mobile;
		this.deliveryType = deliveryType;
		this.flatId = flatId;
		this.flatNo = flatNo;
		this.buildingName = buildingName;
		this.checkIn = checkIn;
	}

	public Long getDeliveryLogId() {
		return deliveryLogId;
	}

	public void setDeliveryLogId(Long deliveryLogId) {
		this.deliveryLogId = deliveryLogId;
	}

	public Long getSocietyId() {
		return societyId;
	}

	public void setSocietyId(Long societyId) {
		this.societyId = societyId;
	}

	public String getDeliveryPersonName() {
		return deliveryPersonName;
	}

	public void setDeliveryPersonName(String deliveryPersonName) {
		this.deliveryPersonName = deliveryPersonName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getDeliveryType() {
		return deliveryType;
	}

	public void setDeliveryType(String deliveryType) {
		this.deliveryType = deliveryType;
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

	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

	public Timestamp getCheckIn() {
		return checkIn;
	}

	public void setCheckIn(Timestamp checkIn) {
		this.checkIn = checkIn;
	}
	
	
    
    

}
