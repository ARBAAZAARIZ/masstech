package model;

public class Flat {

	public Flat() {
		
	}
	
	 private Long flatId;
	    private Long buildingId;
	    private String flatNo;
	    private int floorNo;
	    private double carpetAreaSqft;
	    private boolean isParkingAllocated;
	    
	    
		public Flat(Long flatId, Long buildingId, String flatNo, int floorNo, double carpetAreaSqft,
				boolean isParkingAllocated) {
			super();
			this.flatId = flatId;
			this.buildingId = buildingId;
			this.flatNo = flatNo;
			this.floorNo = floorNo;
			this.carpetAreaSqft = carpetAreaSqft;
			this.isParkingAllocated = isParkingAllocated;
		}


		public Long getFlatId() {
			return flatId;
		}


		public void setFlatId(Long flatId) {
			this.flatId = flatId;
		}


		public Long getBuildingId() {
			return buildingId;
		}


		public void setBuildingId(Long buildingId) {
			this.buildingId = buildingId;
		}


		public String getFlatNo() {
			return flatNo;
		}


		public void setFlatNo(String flatNo) {
			this.flatNo = flatNo;
		}


		public int getFloorNo() {
			return floorNo;
		}


		public void setFloorNo(int floorNo) {
			this.floorNo = floorNo;
		}


		public double getCarpetAreaSqft() {
			return carpetAreaSqft;
		}


		public void setCarpetAreaSqft(double carpetAreaSqft) {
			this.carpetAreaSqft = carpetAreaSqft;
		}


		public boolean isParkingAllocated() {
			return isParkingAllocated;
		}


		public void setParkingAllocated(boolean isParkingAllocated) {
			this.isParkingAllocated = isParkingAllocated;
		}
		
		
	    
	    

}
