package controller.societyManagerController;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.AuthUser;
import service.NotificationService;

import java.io.IOException;


@WebServlet("/SocietyManagerServlet")
public class SocietyManagerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public SocietyManagerServlet() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false); // false avoids creating new session

        AuthUser user = (AuthUser) session.getAttribute("authUser");
        System.out.println(user.getUserId());
        
       
        
        try {
        	 NotificationService notificationService = new NotificationService();
             int unreadCount = notificationService.getUnreadNotificationCountBySocietyId(user.getSocietyId());
             session.setAttribute("unreadCount", unreadCount);

        } catch (Exception e) {
            request.setAttribute("unreadCount", 0); // fallback
            e.printStackTrace();
        }
        
        request.getRequestDispatcher("WEB-INF/views/societyManagerViews/societyManagerDashboard.jsp").forward(request, response);
 
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}

}
