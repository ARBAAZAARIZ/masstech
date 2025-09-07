package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.AuthUser;
import model.Building;
import model.Flat;
import model.Society;
import service.BuildingService;
import service.FlatService;
import service.SecurityService;
import service.SocietyService;

import java.io.IOException;
import java.util.List;


@WebServlet("/VisitorEntryServlet")
public class VisitorEntryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   private SocietyService societyService;
   private BuildingService buildingService;
   private FlatService flatService;
    public VisitorEntryServlet() {
        
      this.societyService=new SocietyService();
      this.buildingService =new BuildingService();
      this.flatService = new FlatService();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AuthUser user = (AuthUser) request.getSession(false).getAttribute("authUser");
		int societyId=user.getSocietyId();
		Society society=societyService.getSocietyById(societyId);
		
		List<Building> buildingList=buildingService.getAllBuildings();
		List<Flat> flatList=flatService.getAllFlats();
		
		request.setAttribute("society", society);
		request.setAttribute("buildingList", buildingList);
        request.setAttribute("flatList", flatList);
        
        request.getRequestDispatcher("WEB-INF/views/securityViews/visitorEntry.jsp").forward(request, response);
		
	}

	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    try {
	        // Get form data
	        Long societyId = Long.parseLong(request.getParameter("societyId"));
	        String visitorName = request.getParameter("visitorName");
	        String vehicleNo = request.getParameter("vehicleNo");
	        String purpose = request.getParameter("purpose");
	        String buildingName = request.getParameter("buildingId"); // dropdown value is building name
	        String flatNo = request.getParameter("flatId"); // dropdown value is flat number

	        // Call service
	        SecurityService securityService = new SecurityService();
	        boolean success = securityService.logVisitorEntry(societyId, visitorName, vehicleNo, purpose, flatNo, buildingName);

	        // Set feedback message
	        if (success) {
	            request.setAttribute("message", "Visitor entry logged successfully.");
	        } else {
	            request.setAttribute("error", "Failed to log visitor entry. Please check flat/building details.");
	        }

	        // Reload form with fresh data
	        AuthUser user = (AuthUser) request.getSession(false).getAttribute("authUser");
	        int sid = user.getSocietyId();

	        Society society = new SocietyService().getSocietyById(sid);
	        List<Building> buildingList = new BuildingService().getAllBuildings();
	        List<Flat> flatList = new FlatService().getAllFlats();

	        request.setAttribute("society", society);
	        request.setAttribute("buildingList", buildingList);
	        request.setAttribute("flatList", flatList);

	        request.getRequestDispatcher("WEB-INF/views/securityViews/visitorEntry.jsp").forward(request, response);

	    } catch (Exception e) {
	        throw new ServletException("Error processing visitor entry", e);
	    }
	}


}
