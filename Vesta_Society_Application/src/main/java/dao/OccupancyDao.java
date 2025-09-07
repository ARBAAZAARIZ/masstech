package dao;

import model.FlatOccupancy;
import util.DB_Connection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OccupancyDao {

    public List<FlatOccupancy> getAllOccupanciesBySocietyId(int societyId) {
        List<FlatOccupancy> list = new ArrayList<>();

        String sql = "SELECT fo.occupancy_id, fo.flat_id, fo.member_id, fo.type, fo.start_date, fo.end_date " +
                     "FROM flat_occupancies fo " +
                     "JOIN flats f ON fo.flat_id = f.flat_id " +
                     "JOIN buildings b ON f.building_id = b.building_id " +
                     "WHERE b.society_id = ? ORDER BY fo.start_date DESC";

        try (Connection conn = DB_Connection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, societyId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    FlatOccupancy o = new FlatOccupancy();
                    o.setOccupancyId(rs.getLong("occupancy_id"));
                    o.setFlatId(rs.getLong("flat_id"));
                    o.setMemberId(rs.getLong("member_id"));
                    o.setType(rs.getString("type"));
                    o.setStartDate(rs.getDate("start_date"));
                    o.setEndDate(rs.getDate("end_date"));
                    list.add(o);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public boolean assignFlat(FlatOccupancy occupancy) {
        String sql = "INSERT INTO flat_occupancies (flat_id, member_id, type, start_date, end_date) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DB_Connection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, occupancy.getFlatId());
            ps.setLong(2, occupancy.getMemberId());
            ps.setString(3, occupancy.getType());
            ps.setDate(4, occupancy.getStartDate());

            if (occupancy.getEndDate() != null) {
                ps.setDate(5, occupancy.getEndDate());
            } else {
                ps.setNull(5, Types.DATE);
            }

            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
