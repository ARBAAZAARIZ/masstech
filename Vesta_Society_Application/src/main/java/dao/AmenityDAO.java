package dao;

import model.Amenity;
import model.AmenityBooking;
import util.DB_Connection;

import java.sql.*;
import java.util.*;

public class AmenityDAO {
    private final Connection conn = DB_Connection.getConnection();

    public List<Amenity> getAllAmenities() throws SQLException {
        String sql = "SELECT * FROM amenities ORDER BY amenity_id DESC";
        List<Amenity> list = new ArrayList<>();

        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Amenity a = new Amenity();
                a.setAmenityId(rs.getLong("amenity_id"));
                a.setSocietyId(rs.getLong("society_id"));
                a.setName(rs.getString("name"));
                a.setBookingRequired(rs.getBoolean("booking_required"));
                a.setAmount(rs.getDouble("amount"));
                list.add(a);
            }
        }
        return list;
    }

    public void createAmenity(Amenity amenity) throws SQLException {
        String sql = "INSERT INTO amenities (society_id, name, booking_required, amount) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, amenity.getSocietyId());
            ps.setString(2, amenity.getName());
            ps.setBoolean(3, amenity.isBookingRequired());
            ps.setDouble(4, amenity.getAmount());
            ps.executeUpdate();
        }
    }

    public Amenity getById(Long id) throws SQLException {
        String sql = "SELECT * FROM amenities WHERE amenity_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Amenity a = new Amenity();
                    a.setAmenityId(rs.getLong("amenity_id"));
                    a.setSocietyId(rs.getLong("society_id"));
                    a.setName(rs.getString("name"));
                    a.setBookingRequired(rs.getBoolean("booking_required"));
                    return a;
                }
            }
        }
        return null;
    }

    public void updateAmenity(Amenity amenity) throws SQLException {
        String sql = "UPDATE amenities SET society_id = ?, name = ?, booking_required = ?, amount = ? WHERE amenity_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, amenity.getSocietyId());
            ps.setString(2, amenity.getName());
            ps.setBoolean(3, amenity.isBookingRequired());
            ps.setDouble(4, amenity.getAmount());
            ps.setLong(5, amenity.getAmenityId());
            
            ps.executeUpdate();
        }
    }

    public void deleteAmenity(Long id) throws SQLException {
        String sql = "DELETE FROM amenities WHERE amenity_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        }
    }
    
    public List<Amenity> getAmenitiesBySocietyId(Long societyId) {
        List<Amenity> list = new ArrayList<>();
        String sql = "SELECT * FROM amenities WHERE society_id = ?";

        try (Connection conn = DB_Connection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, societyId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Amenity a = new Amenity();
                    a.setAmenityId(rs.getLong("amenity_id"));
                    a.setSocietyId(rs.getLong("society_id"));
                    a.setAmount(rs.getDouble("amount"));
                    a.setName(rs.getString("name"));
                    a.setBookingRequired(rs.getBoolean("booking_required"));
                    list.add(a);
                }
            }
        } catch (Exception e) {
            System.out.println("Error fetching amenities: " + e.getMessage());
        }
        
        System.out.println(list);

        return list;
    }
    
    
   

    
    public List<AmenityBooking> getAmenityBookingsBySocietyId(int societyId) {
        List<AmenityBooking> list = new ArrayList<>();
        String sql = "SELECT * FROM amenity_bookings WHERE society_id = ? ORDER BY start_time DESC";

        try (Connection conn = DB_Connection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, societyId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    AmenityBooking ab = new AmenityBooking();
                    ab.setBookingId(rs.getLong("booking_id"));
                    ab.setAmenityId(rs.getLong("amenity_id"));
                    ab.setUserId(rs.getLong("user_id"));
                    ab.setAmount(rs.getDouble("amount"));
                    ab.setSocietyId(rs.getLong("society_id"));
                    ab.setAmenityName(rs.getString("amenity_name"));
                    ab.setStartTime(rs.getTimestamp("start_time"));
                    ab.setEndTime(rs.getTimestamp("end_time"));
                    ab.setStatus(rs.getString("status"));
                    list.add(ab);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
    
    public boolean cancelAmenityBooking(long bookingId) {
        String sql = "UPDATE amenity_bookings SET status = 'Cancelled' WHERE booking_id = ?";

        try (Connection conn = DB_Connection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, bookingId);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean bookAmenityBooking(long bookingId) {
    	String sql = "UPDATE amenity_bookings SET status = 'Booked' WHERE booking_id = ?";
    	try (Connection conn = DB_Connection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

               ps.setLong(1, bookingId);
               return ps.executeUpdate() > 0;

           } catch (SQLException e) {
               e.printStackTrace();
               return false;
           }
    }


    

}
