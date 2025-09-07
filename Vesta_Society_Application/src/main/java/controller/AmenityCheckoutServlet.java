package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.AuthUser;
import service.AmenityCheckinService;

import java.io.IOException;



@WebServlet("/AmenityCheckoutServlet")
public class AmenityCheckoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private final AmenityCheckinService serviceAmenityCheckinService;

    public AmenityCheckoutServlet() {
    	serviceAmenityCheckinService = new AmenityCheckinService();

    }

    

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
        Long checkinId = Long.parseLong(request.getParameter("checkinId"));

        boolean updated = serviceAmenityCheckinService.markCheckout(checkinId);

        AuthUser user = (AuthUser) request.getSession(false).getAttribute("authUser");
        request.setAttribute("logs", serviceAmenityCheckinService.getCheckinsBySocietyId(user.getSocietyId()));

        request.getRequestDispatcher("WEB-INF/views/securityViews/view_amenity_checkins.jsp").forward(request, response);
    }

}
