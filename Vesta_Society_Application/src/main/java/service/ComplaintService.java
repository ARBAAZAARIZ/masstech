package service;

import java.sql.SQLException;
import java.util.List;

import dao.ComplaintDAO;
import model.Complaint;
import model.ComplaintDetails;

public class ComplaintService {

	private final ComplaintDAO complaintDAO;
	
	public ComplaintService() {
		complaintDAO = new ComplaintDAO();
	}
	public List<Complaint> getAllComplaints() throws SQLException {
        return complaintDAO.getAllComplaints();
    }

    public Complaint getComplaintById(Long id) throws SQLException {
        return complaintDAO.getById(id);
    }

    public void updateComplaintStatus(Long id, String status) throws SQLException {
    	complaintDAO.updateStatus(id, status);
    }
    
    public List<ComplaintDetails> getComplaintsBySocietyId(int societyId) {
        return complaintDAO.getComplaintsBySocietyId(societyId);
    }

    public boolean updateComplaintStatusBySocietyManager(Long complaintId, String status) {
        return complaintDAO.updateComplaintStatusBySocietyManager(complaintId, status);
    }


}
