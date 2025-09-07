package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Building;
import model.Flat;
import model.Vehicle;
import service.BuildingService;
import service.FlatService;
import service.VehicleService;

import java.io.IOException;
import java.util.List;


@WebServlet("/VehicleServlet")
public class VehicleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    private VehicleService vehicleService;
    private BuildingService buildingService;
    private FlatService flatService;
    public VehicleServlet() {
    	vehicleService=new VehicleService();
    	buildingService=new BuildingService();
    	flatService=new FlatService();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 String action = request.getParameter("action");
		 
		
		
		if("register".equals(action)) {
			
			List<Building> buildingList = buildingService.getAllBuildings();
            request.setAttribute("buildingList", buildingList);
            
            List<Flat> flatList = flatService.getAllFlats();
            request.setAttribute("flatList", flatList);
            request.getRequestDispatcher("WEB-INF/views/register_vehicle.jsp").forward(request, response);
            
			
		}else {
			List<Vehicle> vehicleList = vehicleService.getAllRegisteredVehicles();
			request.setAttribute("vehicleList", vehicleList);
			request.getRequestDispatcher("WEB-INF/views/view_vehicles.jsp").forward(request, response);
		}

		 
	}

	 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String action = request.getParameter("action");

	    switch (action) {
	        case "register":
	            String buildingName = request.getParameter("buildingName");
	            String flatNo = request.getParameter("flatNo");    
	            String username = request.getParameter("username");
	            String registrationNo = request.getParameter("registrationNo");
	            String type = request.getParameter("type");

	            boolean success = false;
	            try {
	                success = vehicleService.registerVehicle(buildingName, flatNo, username, registrationNo, type);
	            } catch (Exception e) {
	                e.printStackTrace();
	                request.setAttribute("error", "Something went wrong during registration.");
	            }

	            if (success) {
	                request.setAttribute("message", "Vehicle registered successfully. Confirmation email sent.");
	            } else {
	                request.setAttribute("error", "Invalid building, flat, or username. Please check and try again.");
	            }

	            request.getRequestDispatcher("WEB-INF/views/register_vehicle.jsp").forward(request, response);
	            return;
	    }

	    doGet(request, response);
	}


}
