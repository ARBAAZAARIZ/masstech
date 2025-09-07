package service;

import dao.ParkingSlotDAO;
import model.ParkingSlot;
import java.sql.SQLException;
import java.util.List;

public class ParkingSlotService {
    private final ParkingSlotDAO dao = new ParkingSlotDAO();

    public void createSlot(ParkingSlot slot) throws SQLException {
        dao.save(slot);
    }

    public List<ParkingSlot> getAllSlots() throws SQLException {
        return dao.findAll();
    }

    public ParkingSlot getSlotById(Long slotId) throws SQLException {
        return dao.findById(slotId);
    }

    public void updateSlot(ParkingSlot slot) throws SQLException {
        dao.update(slot);
    }

    public void deleteSlot(Long slotId) throws SQLException {
        dao.delete(slotId);
    }
    
    public List<ParkingSlot> getAvailableSlotsBySocietyId(int societyId) {
        return dao.getAvailableSlotsBySocietyId(societyId);
    }
    
    public boolean markSlotAsCovered(Long slotId) {
        return dao.markSlotAsCovered(slotId);
    }
    
    public List<ParkingSlot> getAllParkingSlotsBysocietyId(int societyID){
    	return dao.getAllParkingSlotsBysocietyId(societyID);
    }


}
