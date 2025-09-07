package service;

import java.util.List;

import dao.BuildingDAO;
import model.Building;

public class BuildingService {
	private BuildingDAO buildingDAO;
	public BuildingService() {
		buildingDAO= new BuildingDAO();
	}
	
	

    public List<Building> getAllBuildings() {
        return buildingDAO.getAllBuildings();
    }
    
    public List<Building> getBuildingsBySocietyID(int societyID){
    	
    	return buildingDAO.getBuildingsBySocietyID(societyID);
    }

    public boolean deleteBuilding(long buildingId) {
        return buildingDAO.deleteBuildingById(buildingId);
    }
    
    public boolean createBuilding(Long societyId, String name, int floors) {
        Long id = buildingDAO.createBuilding(societyId, name, floors);
        return id != null && id > 0;
    }


}
