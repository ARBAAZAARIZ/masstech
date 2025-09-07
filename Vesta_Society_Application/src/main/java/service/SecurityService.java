package service;

import java.util.List;

import dao.SecurityDao;
import model.DeliveryLog;
import model.GateLog;

public class SecurityService {

    private final SecurityDao securityDao;

    public SecurityService() {
        this.securityDao = new SecurityDao();
    }

    public boolean logVisitorEntry(Long societyId, String visitorName, String vehicleNo,
                                   String purpose, String flatNo, String buildingName) {
        return securityDao.insertVisitorGateLog(societyId, visitorName, vehicleNo, purpose, flatNo, buildingName);
    }
    
    public List<GateLog> getVisitorLogsForSociety(Long societyId) {
        return securityDao.getVisitorLogsBySocietyId(societyId);
    }
    
    public boolean logDeliveryEntry(Long societyId, String name, String mobile,
            String type, String flatNo, String buildingName) {
    	return securityDao.insertDeliveryLog(societyId, name, mobile, type, flatNo, buildingName);
    }
    
    public List<DeliveryLog> getDeliveryLogsForSociety(Long societyId) {
        return securityDao.getDeliveryLogsBySocietyId(societyId);
    }
    
    public boolean updateVisitorCheckout(Long logId) {
    	System.out.println("Log id from service class "+logId);
    	return securityDao.updateVisitorCheckout(logId);
    	
    }



}
