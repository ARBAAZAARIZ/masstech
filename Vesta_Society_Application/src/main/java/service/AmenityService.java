package service;

import dao.AmenityDAO;
import model.Amenity;
import model.AmenityBooking;

import java.sql.SQLException;
import java.util.List;

public class AmenityService {
    private final AmenityDAO dao = new AmenityDAO();

    public List<Amenity> getAllAmenities() throws SQLException {
        return dao.getAllAmenities();
    }

    public void createAmenity(Amenity amenity) throws SQLException {
        dao.createAmenity(amenity);
    }

    public Amenity getAmenityById(Long id) throws SQLException {
        return dao.getById(id);
    }

    public void updateAmenity(Amenity amenity) throws SQLException {
        dao.updateAmenity(amenity);
    }

    public void deleteAmenity(Long id) throws SQLException {
        dao.deleteAmenity(id);
    }
    
    public List<Amenity> getAmenitiesBySocietyId(int societyId) {
        return dao.getAmenitiesBySocietyId((long) societyId);
    }
    
    public List<AmenityBooking> getAmenityBookingsBySocietyId(int societyId) {
        return dao.getAmenityBookingsBySocietyId(societyId);
    }

    public boolean cancelAmenityBooking(Long bookingId) {
        return dao.cancelAmenityBooking(bookingId);
    }
    
    public boolean bookAmenityBooking(long bookingId) {
    	return dao.bookAmenityBooking(bookingId);
    	
    }
    

}
