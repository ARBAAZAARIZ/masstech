package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Amenity;
import model.AmenityCheckinLog;
import model.AuthUser;
import service.AmenityCheckinService;
import service.AmenityService;
import service.SocietyService;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;


@WebServlet("/AmenityCheckinServlet")
public class AmenityCheckinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   private AmenityCheckinService amenityCheckinService;
    public AmenityCheckinServlet() {
    	amenityCheckinService=new AmenityCheckinService();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 AuthUser user = (AuthUser) request.getSession(false).getAttribute("authUser");
		 
		 if (user == null) {
		        response.sendRedirect("login.jsp");
		        return;
		    }
		 
		 List<Amenity> amenities = new AmenityService().getAmenitiesBySocietyId(user.getSocietyId());
		 request.setAttribute("amenities", amenities);
		 
		 request.setAttribute("societyName", new SocietyService().getSocietyById(user.getSocietyId()).getName());
		 request.getRequestDispatcher("WEB-INF/views/securityViews/amenity_checkin_log.jsp").forward(request, response);
	}

	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    AuthUser user = (AuthUser) request.getSession(false).getAttribute("authUser");

	    Long memberId = Long.parseLong(request.getParameter("memberId"));
	    Long amenityId = Long.parseLong(request.getParameter("amenityId"));
	    String remarks = request.getParameter("remarks");

	    AmenityCheckinLog log = new AmenityCheckinLog();
	    log.setMemberId(memberId);
	    log.setAmenityId(amenityId);
	    log.setCheckinTime(Timestamp.valueOf(LocalDateTime.now()));
	    log.setGuardId(user.getUserId());
	    log.setSocietyId((long) user.getSocietyId());
	    log.setStatus("IN");
	    log.setRemarks(remarks);

	    boolean inserted = amenityCheckinService.logCheckin(log);

	    
	    List<Amenity> amenities = new AmenityService().getAmenitiesBySocietyId(user.getSocietyId());
	    String societyName = new SocietyService().getSocietyById(user.getSocietyId()).getName();

	    request.setAttribute("amenities", amenities);
	    request.setAttribute("societyName", societyName);

	    if (inserted) {
	        request.setAttribute("message", "Amenity check-in logged successfully.");
	    } else {
	        request.setAttribute("error", "Failed to log check-in.");
	    }

	    request.getRequestDispatcher("WEB-INF/views/securityViews/amenity_checkin_log.jsp").forward(request, response);
	}


}
