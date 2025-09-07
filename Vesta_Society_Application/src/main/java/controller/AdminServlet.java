package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import model.AuthUser;
import service.NotificationService;

import java.io.IOException;

@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AdminServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false); // false avoids creating new session

        AuthUser user = (AuthUser) session.getAttribute("authUser");
        System.out.println(user.getUserId());

        if (user != null && "ADMIN".equalsIgnoreCase(user.getRole())) {
            request.setAttribute("authUser", user);

            
            try {
                NotificationService notificationService = new NotificationService();
                int unreadCount = notificationService.getUnreadCount();
                session.setAttribute("unreadCount", unreadCount);

            } catch (Exception e) {
                request.setAttribute("unreadCount", 0); // fallback
                e.printStackTrace();
            }

            request.getRequestDispatcher("WEB-INF/views/adminDashboard.jsp").forward(request, response);
        } else {
        	
            response.sendRedirect("login.jsp"); 
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
