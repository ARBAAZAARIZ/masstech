package controller.societyManagerController;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.AuthUser;
import model.Building;
import model.Flat;
import model.FlatOccupancy;
import service.BuildingService;
import service.FlatService;
import service.OccupancyService;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

/**
 * Servlet implementation class AssignFlatServlet
 */
@WebServlet("/AssignFlatServlet")
public class AssignFlatServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private BuildingService buildingService;
    private FlatService flatService;
    private OccupancyService occupancyService;
    public AssignFlatServlet() {
    	buildingService = new BuildingService();
        flatService = new FlatService();
        occupancyService = new OccupancyService();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 HttpSession session = request.getSession(false);
	        AuthUser user = (AuthUser) session.getAttribute("authUser");
	        String action = request.getParameter("action");
	        
	        if (action == null || action.equals("view")) {
	            List<FlatOccupancy> occupancyList = occupancyService.getAllOccupanciesBySocietyId(user.getSocietyId());
	            request.setAttribute("occupancyList", occupancyList);
	            request.setAttribute("buildingList", null);
	            request.setAttribute("flatList", null);
	            request.getRequestDispatcher("WEB-INF/views/societyManagerViews/assign_flats.jsp").forward(request, response);

	        }else if (action.equals("init")) {
	            List<Building> buildingList = buildingService.getBuildingsBySocietyID(user.getSocietyId());
	            request.setAttribute("buildingList", buildingList);
	            request.setAttribute("flatList", null);
	            request.setAttribute("occupancyList", null);
	            request.getRequestDispatcher("WEB-INF/views/societyManagerViews/assign_flats.jsp").forward(request, response);

	        }else if (action.equals("selectBuilding")) {
	            Long buildingId = Long.parseLong(request.getParameter("buildingId"));
	            List<Flat> flatList = flatService.getFlatsByBuildingId(buildingId);
	            List<Building> buildingList = buildingService.getBuildingsBySocietyID(user.getSocietyId());

	            request.setAttribute("flatList", flatList);
	            request.setAttribute("buildingList", buildingList);
	            request.setAttribute("selectedBuildingId", buildingId);
	            request.getRequestDispatcher("WEB-INF/views/societyManagerViews/assign_flats.jsp").forward(request, response);
	        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action != null && action.equals("assign")) {
            Long flatId = Long.parseLong(request.getParameter("flatId"));
            Long memberId = Long.parseLong(request.getParameter("memberId"));
            String type = request.getParameter("type");
            Date startDate = Date.valueOf(request.getParameter("startDate"));
            String endDateParam = request.getParameter("endDate");
            Date endDate = (endDateParam != null && !endDateParam.isEmpty()) ? Date.valueOf(endDateParam) : null;

            FlatOccupancy occupancy = new FlatOccupancy();
            occupancy.setFlatId(flatId);
            occupancy.setMemberId(memberId);
            occupancy.setType(type);
            occupancy.setStartDate(startDate);
            occupancy.setEndDate(endDate);

            boolean success = occupancyService.assignFlat(occupancy);

            if (success) {
                request.setAttribute("message", "Flat assigned successfully.");
            } else {
                request.setAttribute("error", "Failed to assign flat.");
            }

            HttpSession session = request.getSession(false);
            AuthUser user = (AuthUser) session.getAttribute("authUser");
            List<FlatOccupancy> occupancyList = occupancyService.getAllOccupanciesBySocietyId(user.getSocietyId());
            request.setAttribute("occupancyList", occupancyList);
            request.getRequestDispatcher("WEB-INF/views/societyManagerViews/assign_flats.jsp").forward(request, response);
        }
    }

}
