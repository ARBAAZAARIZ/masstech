package service;

import dao.OccupancyDao;
import model.FlatOccupancy;

import java.util.List;

public class OccupancyService {

    private OccupancyDao occupancyDao;

    public OccupancyService() {
        occupancyDao = new OccupancyDao();
    }

    public List<FlatOccupancy> getAllOccupanciesBySocietyId(int societyId) {
        return occupancyDao.getAllOccupanciesBySocietyId(societyId);
    }

    public boolean assignFlat(FlatOccupancy occupancy) {
        return occupancyDao.assignFlat(occupancy);
    }
}
