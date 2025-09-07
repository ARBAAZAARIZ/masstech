package controller.societyManagerController;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.AuthUser;
import model.ParkingSlot;
import service.ParkingSlotService;

import java.io.IOException;
import java.util.List;


@WebServlet("/ViewParkingMap")
public class ViewParkingMap extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    private ParkingSlotService parkingSlotService;
    public ViewParkingMap() {
        this.parkingSlotService=new ParkingSlotService();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);

        AuthUser user = (AuthUser) session.getAttribute("authUser");
        
		List<ParkingSlot> parkingSlotlist=parkingSlotService
				.getAllParkingSlotsBysocietyId(user.getSocietyId());
		
		request.setAttribute("parkingSlotlist", parkingSlotlist);
		
		request.getRequestDispatcher("WEB-INF/views/societyManagerViews/viewParkingSlots.jsp")
		.forward(request, response);
		
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
