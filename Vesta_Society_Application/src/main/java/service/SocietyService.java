package service;

import java.util.List;

import dao.SocietyDAO;
import model.Society;

public class SocietyService {

	SocietyDAO societyDAO;
	public SocietyService() {
		societyDAO = new  SocietyDAO();
	}
	
	public List<Society> getAllSocieties() {
	    return societyDAO.viewAllSocieties();
	}
	
	public boolean createSociety(String name, String addressLine1, String addressLine2,
            String city, String state, String pincode) {

		Long generatedId = societyDAO.createSociety(name, addressLine1, addressLine2, city, state, pincode);

		return generatedId != null && generatedId > 0;
}
	
	
	
    public Society getSocietyById(long societyId) {
        return societyDAO.getSocietyById(societyId);
    }	
    
    // Update an existing society
    public boolean updateSociety(long societyId, String name, String addressLine1, String addressLine2,
                                 String city, String state, String pincode) {

        return societyDAO.updateSociety(societyId, name, addressLine1, addressLine2, city, state, pincode);
    }
    
    
    public boolean deleteSociety(long societyId) {
        return societyDAO.deleteSocietyById(societyId);
    }




}
