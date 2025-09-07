package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.AuthUser;
import service.NotificationService;

import java.io.IOException;


@WebServlet("/IncidentNotificationServlet")
public class IncidentNotificationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	 private final NotificationService notificationService;
   
    public IncidentNotificationServlet() {
        notificationService = new NotificationService();
        
    }

	
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/views/securityViews/incidentNotification.jsp").forward(request, response);
    }

	
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AuthUser user = (AuthUser) request.getSession(false).getAttribute("authUser");

        String message = request.getParameter("message");

        boolean sent = notificationService.sendNotification(user.getUserId(), message); // admin user_id = 1

        if (sent) {
            request.setAttribute("message", "Incident notification sent to admin.");
        } else {
            request.setAttribute("error", "Failed to send notification.");
        }

        request.getRequestDispatcher("WEB-INF/views/securityViews/incidentNotification.jsp").forward(request, response);
    }

}
