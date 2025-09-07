package model;

public class ParkingSlot {

	public ParkingSlot() {
		
	}
	
	 private Long slotId;
	    private Long societyId;
	    private String identifier;
	    private boolean isCovered;
	    
	    
		public ParkingSlot(Long slotId, Long societyId, String identifier, boolean isCovered) {
			super();
			this.slotId = slotId;
			this.societyId = societyId;
			this.identifier = identifier;
			this.isCovered = isCovered;
		}


		public Long getSlotId() {
			return slotId;
		}


		public void setSlotId(Long slotId) {
			this.slotId = slotId;
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
	    
		
		
	    

}
