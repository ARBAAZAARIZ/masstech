package service;

import java.util.List;

import dao.VisitorApprovalDAO;
import model.VisitorLogView;

public class VisitorLogViewService {

	public VisitorLogViewService() {
		// TODO Auto-generated constructor stub
	}
	
	private final VisitorApprovalDAO dao = new VisitorApprovalDAO();

    public List<VisitorLogView> getLogsForSociety(long societyId) {
        return dao.getVisitorLogsBySocietyId(societyId);
    }

}
