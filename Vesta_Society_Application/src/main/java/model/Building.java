package model;

import java.sql.Timestamp;


public class Building {

	public Building() {
		
	}
	
	private Long buildingId;
	private Long societyId;
    private String name;
    private int floors;
    private Timestamp createdAt;
    
    
	


	public Building(Long buildingId, Long societyId, String name, int floors, Timestamp createdAt) {
		
		this.buildingId = buildingId;
		this.societyId = societyId;
		this.name = name;
		this.floors = floors;
		this.createdAt = createdAt;
	}


	public Long getBuildingId() {
		return buildingId;
	}


	public void setBuildingId(Long buildingId) {
		this.buildingId = buildingId;
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


	public int getFloors() {
		return floors;
	}


	public void setFloors(int floors) {
		this.floors = floors;
	}


	public Timestamp getCreatedAt() {
		return createdAt;
	}


	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
	
	
    
    

}
