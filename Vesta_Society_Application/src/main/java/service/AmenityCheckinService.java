package service;

import java.util.List;

import dao.AmenityCheckinDao;
import model.AmenityCheckinLog;

public class AmenityCheckinService {
    private final AmenityCheckinDao amenityCheckinDao = new AmenityCheckinDao();

    public boolean logCheckin(AmenityCheckinLog log) {
        return amenityCheckinDao.insertCheckin(log);
    }
    
    public List<AmenityCheckinLog> getCheckinsBySocietyId(int societyId) {
        return amenityCheckinDao.getCheckinsBySocietyId((long) societyId);
    }
    
    public boolean markCheckout(Long checkinId) {
        return amenityCheckinDao.updateCheckoutTime(checkinId);
    }


}
