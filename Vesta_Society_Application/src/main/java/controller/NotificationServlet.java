package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Notification;
import service.NotificationService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;




@WebServlet("/NotificationServlet")
public class NotificationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private final NotificationService notificationService;
    public NotificationServlet() {
    	notificationService = new NotificationService();
        
    }

	
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String message = request.getParameter("message");
        String error = request.getParameter("error");

        try {
            List<Notification> list = notificationService.getAllNotifications();
            request.setAttribute("notificationList", list);
            if (message != null) request.setAttribute("message", message);
            if (error != null) request.setAttribute("error", error);
            request.getRequestDispatcher("WEB-INF/views/view_notifications.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }


	 
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("update".equals(action)) {
            try {
                Long id = Long.parseLong(request.getParameter("notificationId"));
                notificationService.markAsRead(id);
                response.sendRedirect("NotificationServlet?message=Notification+marked+as+read");
            } catch (Exception e) {
                response.sendRedirect("NotificationServlet?error=Failed+to+update+notification");
            }
        }
    }

}
