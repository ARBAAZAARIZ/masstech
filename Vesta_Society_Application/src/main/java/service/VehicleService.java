package service;

import java.sql.SQLException;
import java.util.List;

import dao.BuildingDAO;
import dao.FlatDAO;
import dao.UserDAO;
import dao.VehicleDAO;
import model.Vehicle;
import util.EmailUtil;

public class VehicleService {

	 private final VehicleDAO vehicleDAO;
	 private final FlatDAO flatDAO;
	 private final BuildingDAO buildingDAO;
	 private final UserDAO userDAO;
	 
	public VehicleService() {
		this.vehicleDAO = new VehicleDAO();
		this.flatDAO=new FlatDAO();
		this.buildingDAO=new BuildingDAO();
		this.userDAO= new UserDAO();
	}
	
	 public boolean registerVehicle(String buildingName,String flatNo,String username,String registrationNo,
			 String type )  {
		 
		 try {
			 long buildingId=buildingDAO.getBuildingIdByName(buildingName);
			 System.out.println(flatNo);
			 
			 long flatId=flatDAO.getFlatIdByBuildingAndFlatNo(buildingId, flatNo);
			 
			 Vehicle v=new Vehicle();
			 long memberId=userDAO.getMemberIdByUsername(username);
			 
			 v.setMemberId(memberId);
			 v.setFlatId(flatId);
			 v.setRegistrationNo(registrationNo);
			 v.setType(type);
			 
			 
			 long success=vehicleDAO.save(v);
			 if(success>0) {
				 String email=userDAO.getEmailByUsername(username);
				 EmailUtil.sendVehicleConfirmation(email, username, v,flatNo,buildingName);
				 return true;
			 }
			 return false;
		 }catch(SQLException e) {
			 System.out.println(e.getMessage());
			 return false;
		 }

	    }
	 
	 public List<Vehicle> getAllRegisteredVehicles()  {
		 
		 try {
			 return vehicleDAO.getAllVehicles();
		 }catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}
		   
		}
	 
	 public List<Vehicle> getUnassignedVehiclesBySocietyId(int societyId) {
		    return vehicleDAO.getUnassignedVehiclesBySocietyId(societyId);
		}



}
