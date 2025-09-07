package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.AmenityCheckinLog;
import model.AuthUser;
import service.AmenityCheckinService;
import service.AmenityService;

import java.io.IOException;
import java.util.List;
import java.util.Map;



@WebServlet("/AmenityCheckinLogViewer")
public class AmenityCheckinLogViewerServlet extends HttpServlet {
    
	private static final long serialVersionUID = 1L;
	private final AmenityCheckinService service = new AmenityCheckinService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
        AuthUser user = (AuthUser) request.getSession(false).getAttribute("authUser");

        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        List<AmenityCheckinLog> logs = service.getCheckinsBySocietyId(user.getSocietyId());
        

        request.setAttribute("logs", logs);
        

        request.getRequestDispatcher("WEB-INF/views/securityViews/view_amenity_checkins.jsp").forward(request, response);
    }
}
