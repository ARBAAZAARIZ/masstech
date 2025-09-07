package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Building;
import model.Flat;
import model.Society;
import service.BuildingService;
import service.FlatService;
import service.SocietyService;

import java.io.IOException;
import java.util.List;


@WebServlet("/FlatCrudServlet")
public class FlatCrudServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private FlatService flatService;
	private BuildingService buildingService;
	private SocietyService societyService;
    public FlatCrudServlet() {
    	flatService = new FlatService();
    	buildingService = new BuildingService(); 
    	this.societyService=new SocietyService();
    }

	
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) action = "view";

        switch (action) {
            case "create":
            	
            	List<Society> societyList=societyService.getAllSocieties();
            	
            	request.setAttribute("societyList", societyList);
             	int societyId=0;
            if(request.getParameter("societyId")!=null) {
            	 societyId=Integer.parseInt(request.getParameter("societyId"));
            }
            	
            	
                List<Building> buildings = buildingService.getBuildingsBySocietyID(societyId);
                request.setAttribute("buildingList", buildings);
                request.getRequestDispatcher("/WEB-INF/views/createFlatForm.jsp").forward(request, response);
                break;

            case "delete":
                long flatId = Long.parseLong(request.getParameter("flatId"));
                flatService.deleteFlat(flatId);
                response.sendRedirect("FlatCrudServlet?action=view");
                break;

            case "view":
            default:
                List<Flat> flatList = flatService.getAllFlats();
                request.setAttribute("flatList", flatList);
                request.getRequestDispatcher("/WEB-INF/views/viewFlats.jsp").forward(request, response);
                break;
        }
    }

	
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if ("saveCreate".equals(action)) {
            Long buildingId = Long.parseLong(request.getParameter("buildingId"));
            String flatNo = request.getParameter("flatNo");
            int floorNo = Integer.parseInt(request.getParameter("floorNo"));
            double carpetArea = Double.parseDouble(request.getParameter("carpetAreaSqft"));

            boolean parking = request.getParameter("isParkingAllocated") != null;

            boolean success = flatService.createFlat(buildingId, flatNo, floorNo, carpetArea, parking);

            if (success) {
                response.sendRedirect("FlatCrudServlet?action=view");
            } else {
                request.setAttribute("errorMessage", "Failed to create flat.");
                List<Building> buildings = buildingService.getAllBuildings();
                request.setAttribute("buildingList", buildings);
                request.getRequestDispatcher("/WEB-INF/views/createFlatForm.jsp").forward(request, response);
            }
        }
    }

}
