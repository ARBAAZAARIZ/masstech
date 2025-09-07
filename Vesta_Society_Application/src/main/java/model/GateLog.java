package model;

import java.sql.Timestamp;

public class GateLog {
    private Long gateLogId;
    private Long societyId;
    private String visitorName;
    private String vehicleNo;
    private String purpose;
    private Long flatId;
    private String flatNo;
    private String buildingName;
    private Timestamp checkIn;
    private Timestamp checkOut;

    public GateLog() {}

    public GateLog(Long gateLogId, Long societyId, String visitorName, String vehicleNo, String purpose,
                   Long flatId, String flatNo, String buildingName, Timestamp checkIn, Timestamp checkOut) {
        this.gateLogId = gateLogId;
        this.societyId = societyId;
        this.visitorName = visitorName;
        this.vehicleNo = vehicleNo;
        this.purpose = purpose;
        this.flatId = flatId;
        this.flatNo = flatNo;
        this.buildingName = buildingName;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    // Getters and Setters

    public Long getGateLogId() {
        return gateLogId;
    }

    public void setGateLogId(Long gateLogId) {
        this.gateLogId = gateLogId;
    }

    public Long getSocietyId() {
        return societyId;
    }

    public void setSocietyId(Long societyId) {
        this.societyId = societyId;
    }

    public String getVisitorName() {
        return visitorName;
    }

    public void setVisitorName(String visitorName) {
        this.visitorName = visitorName;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
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

    public Timestamp getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(Timestamp checkOut) {
        this.checkOut = checkOut;
    }
}
