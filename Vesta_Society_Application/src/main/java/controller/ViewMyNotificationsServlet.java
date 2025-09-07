package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.AuthUser;
import model.Notification;
import service.NotificationService;

import java.io.IOException;
import java.util.List;



/**
 * Servlet implementation class ViewMyNotificationsServlet
 */
@WebServlet("/ViewMyNotificationsServlet")
public class ViewMyNotificationsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private final NotificationService service;
    public ViewMyNotificationsServlet() {
    	service= new NotificationService();
       
    }

	
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AuthUser user = (AuthUser) request.getSession(false).getAttribute("authUser");

        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        List<Notification> myNotifications = service.getNotificationsByUserId(user.getUserId());
        request.setAttribute("notifications", myNotifications);

        request.getRequestDispatcher("WEB-INF/views/securityViews/viewMyNotifications.jsp").forward(request, response);
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
