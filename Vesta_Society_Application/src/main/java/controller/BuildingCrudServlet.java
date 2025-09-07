package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Building;
import model.Society;
import service.BuildingService;
import service.SocietyService;

import java.io.IOException;
import java.util.List;


@WebServlet("/BuildingCrudServlet")
public class BuildingCrudServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BuildingService buildingService;
	private SocietyService societyService;
	
	
    public BuildingCrudServlet() {
    	new BuildingService();
    	buildingService=new BuildingService();
    	societyService=new SocietyService();
    }

	
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) action = "view";

        switch (action) {
            case "delete":
                long buildingId = Long.parseLong(request.getParameter("buildingId"));
                boolean deleted = buildingService.deleteBuilding(buildingId);
                response.sendRedirect("BuildingCrudServlet?action=view");
                break;
                
            case "create":
                List<Society> societies = societyService.getAllSocieties();
                request.setAttribute("societyList", societies);
                request.getRequestDispatcher("/WEB-INF/views/createBuildingForm.jsp").forward(request, response);
                break;


            case "view":
            default:
                List<Building> buildingList = buildingService.getAllBuildings();
                request.setAttribute("buildingList", buildingList);
                request.getRequestDispatcher("/WEB-INF/views/viewBuildings.jsp").forward(request, response);
                break;
        }
    }

	
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if ("saveCreate".equals(action)) {
            Long societyId = Long.parseLong(request.getParameter("societyId"));
            String name = request.getParameter("name");
            int floors = Integer.parseInt(request.getParameter("floors"));

            boolean success = buildingService.createBuilding(societyId, name, floors);

            if (success) {
                response.sendRedirect("BuildingCrudServlet?action=view");
            } else {
                request.setAttribute("errorMessage", "Failed to create building. Name might already exist.");
                List<Society> societies = societyService.getAllSocieties();
                request.setAttribute("societyList", societies);
                request.getRequestDispatcher("/WEB-INF/views/createBuildingForm.jsp").forward(request, response);
            }
        }
    }


}
