package service;

import java.util.List;

import dao.FlatDAO;
import model.Flat;

public class FlatService {
	
	private FlatDAO flatDAO;
	
	public FlatService() {
		flatDAO = new FlatDAO();
	}
	
	public List<Flat> getAllFlats() {
        return flatDAO.getAllFlats();
    }
	
	public boolean createFlat(Long buildingId, String flatNo, int floorNo, double carpetArea, boolean parking) {
        Long id = flatDAO.createFlat(buildingId, flatNo, floorNo, carpetArea, parking);
        return id != null && id > 0;
    }
	
	public boolean deleteFlat(long flatId) {
        return flatDAO.deleteFlatById(flatId);
    }
	
	public List<Flat> getFlatsByBuildingId(Long buildingId) {
	    return flatDAO.getFlatsByBuildingId(buildingId);
	}

	

}
