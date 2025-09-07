package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Amenity;
import model.AuthUser;
import model.Society;
import service.AmenityService;
import service.SocietyService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


@WebServlet("/AmenityServlet")
public class AmenityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private final AmenityService service;
	private final SocietyService societyService;
	
    public AmenityServlet() {
    this.	service = new AmenityService();
      this. societyService=new SocietyService();
    }

	
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    		AuthUser user = (AuthUser) request.getSession(false).getAttribute("authUser");
		 
		 
        String action = request.getParameter("action");

        try {
            if ("view".equals(action)) {
                List<Amenity> list = service.getAllAmenities();
                request.setAttribute("amenityList", list);
                request.getRequestDispatcher("WEB-INF/views/view_amenities.jsp").forward(request, response);
            } else if ("edit".equals(action)) {
                Long id = Long.parseLong(request.getParameter("amenityId"));
                Amenity amenity = service.getAmenityById(id);
                request.setAttribute("amenity", amenity);
                request.getRequestDispatcher("WEB-INF/views/edit_amenity.jsp").forward(request, response);
            } else if ("create".equals(action)) {
            	
            	List<Society> societyList=societyService.getAllSocieties();
            	
            	request.setAttribute("societyList", societyList);
                request.getRequestDispatcher("WEB-INF/views/create_amenity.jsp").forward(request, response);
            }else if ("delete".equals(action)) {
                Long id = Long.parseLong(request.getParameter("amenityId"));
                service.deleteAmenity(id);
                response.sendRedirect("AmenityServlet?action=view");
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

	
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            if ("saveCreate".equals(action)) {
                Amenity a = new Amenity();
                a.setSocietyId(Long.parseLong(request.getParameter("societyId")));
                a.setName(request.getParameter("name"));
                a.setAmount(Double.parseDouble(request.getParameter("amount")));
                a.setBookingRequired(request.getParameter("bookingRequired") != null);
                service.createAmenity(a);
                response.sendRedirect("AmenityServlet?action=view");
            } else if ("saveUpdate".equals(action)) {
                Amenity a = new Amenity();
                a.setAmenityId(Long.parseLong(request.getParameter("amenityId")));
                a.setSocietyId(Long.parseLong(request.getParameter("societyId")));
                a.setName(request.getParameter("name"));
                a.setAmount(Double.parseDouble(request.getParameter("amount")));
                a.setBookingRequired(request.getParameter("bookingRequired") != null);
                service.updateAmenity(a);
                response.sendRedirect("AmenityServlet?action=view");
            } 
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

}
