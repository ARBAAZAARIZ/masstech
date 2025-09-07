package controller.societyManagerController;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Amenity;
import model.AmenityBooking;
import model.AuthUser;
import service.AmenityService;

import java.io.IOException;
import java.util.List;



@WebServlet("/ViewAmenityServlet")
public class ViewAmenityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	private AmenityService amenityService;

    public ViewAmenityServlet() {
       this.amenityService=new AmenityService();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        AuthUser user = (AuthUser) session.getAttribute("authUser");
        String action = request.getParameter("action");

        if (action == null || action.equals("view")) {
            List<Amenity> amenityList = amenityService.getAmenitiesBySocietyId(user.getSocietyId());
            request.setAttribute("amenityList", amenityList);
            request.getRequestDispatcher("WEB-INF/views/societyManagerViews/viewAmenities.jsp").forward(request, response);

        } else if (action.equals("viewBookings")) {
            List<AmenityBooking> bookingList = amenityService.getAmenityBookingsBySocietyId(user.getSocietyId());
            request.setAttribute("bookingList", bookingList);
            request.getRequestDispatcher("WEB-INF/views/societyManagerViews/viewAmenityBookings.jsp").forward(request, response);

        } else if (action.equals("cancel")) {
            Long bookingId = Long.parseLong(request.getParameter("bookingId"));
            amenityService.cancelAmenityBooking(bookingId);
            response.sendRedirect("ViewAmenityServlet?action=viewBookings");
        }else if (action.equals("book")) {
            Long bookingId = Long.parseLong(request.getParameter("bookingId"));
            amenityService.bookAmenityBooking(bookingId);
            response.sendRedirect("ViewAmenityServlet?action=viewBookings");
        }
    }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
