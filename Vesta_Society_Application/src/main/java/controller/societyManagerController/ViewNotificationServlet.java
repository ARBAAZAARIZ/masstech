package controller.societyManagerController;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.AuthUser;
import model.NotificationDetails;
import service.NotificationService;

import java.io.IOException;
import java.util.List;

/**
 * Servlet implementation class ViewNotificationServlet
 */
@WebServlet("/ViewNotificationServlet")
public class ViewNotificationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private NotificationService notificationService;
    
    public ViewNotificationServlet() {
        this.notificationService=new NotificationService();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        AuthUser user = (AuthUser) session.getAttribute("authUser");
        String action = request.getParameter("action");

        if (action == null || action.equals("view")) {
            List<NotificationDetails> notificationList = notificationService.getNotificationsBySocietyId(user.getSocietyId());
            request.setAttribute("notificationList", notificationList);
            request.getRequestDispatcher("WEB-INF/views/societyManagerViews/viewNotifications.jsp").forward(request, response);

        } else if (action.equals("markRead")) {
            Long notificationId = Long.parseLong(request.getParameter("notificationId"));
            notificationService.markNotificationAsRead(notificationId);
            response.sendRedirect("ViewNotificationServlet?action=view");
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
