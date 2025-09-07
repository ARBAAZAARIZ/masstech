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


@WebServlet("/DeliveryEntryServlet")
public class DeliveryEntryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private final BuildingService buildingService ;
    private final FlatService flatService ;
    private final SecurityService securityService ;
    public DeliveryEntryServlet() {
    	buildingService = new BuildingService();
    	flatService = new FlatService();
    	securityService = new SecurityService();
    }

	
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AuthUser user = (AuthUser) request.getSession(false).getAttribute("authUser");
        int societyId = user.getSocietyId();

        List<Building> buildingList = buildingService.getAllBuildings();
        List<Flat> flatList = flatService.getAllFlats();
        Society society = new SocietyService().getSocietyById(societyId);

        request.setAttribute("society", society);
        request.setAttribute("buildingList", buildingList);
        request.setAttribute("flatList", flatList);
        request.getRequestDispatcher("WEB-INF/views/securityViews/deliveryEntry.jsp").forward(request, response);
    }

	
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Long societyId = Long.parseLong(request.getParameter("societyId"));
            String name = request.getParameter("deliveryPersonName");
            String mobile = request.getParameter("mobile");
            String type = request.getParameter("deliveryType");
            String buildingName = request.getParameter("buildingId");
            String flatNo = request.getParameter("flatId");

            boolean success = securityService.logDeliveryEntry(societyId, name, mobile, type, flatNo, buildingName);

            if (success) {
                request.setAttribute("message", "Delivery entry logged successfully.");
            } else {
                request.setAttribute("error", "Failed to log delivery entry.");
            }

            doGet(request, response);
        } catch (Exception e) {
            throw new ServletException("Error processing delivery entry", e);
        }
    }

}
