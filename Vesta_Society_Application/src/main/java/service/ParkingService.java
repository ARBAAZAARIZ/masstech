package service;

import java.sql.Date;
import java.util.List;

import dao.ParkingDAO;
import model.ParkingAssignmentDetails;

public class ParkingService {

	public ParkingService() {
		// TODO Auto-generated constructor stub
	}
	
	ParkingDAO parkingDao=new ParkingDAO();
	
	public List<ParkingAssignmentDetails> getParkingAssignmentsBySocietyId(int societyId) {
	    return parkingDao.getParkingAssignmentsBySocietyId(societyId);
	}
	
	public boolean deleteAssignmentByVehicleId(Long vehicleId) {
	    return parkingDao.deleteAssignmentByVehicleId(vehicleId);
	}
	
	public boolean assignParking(Long slotId, Long vehicleId, Date startDate, Date endDate) {
	    return parkingDao.assignParking(slotId, vehicleId, startDate, endDate);
	}




}
