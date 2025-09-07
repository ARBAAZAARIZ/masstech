package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.AuthUser;
import model.GateLog;
import service.SecurityService;

import java.io.IOException;
import java.util.List;


@WebServlet("/VisitorLogServlet")
public class VisitorLogServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private final SecurityService securityService;
    public VisitorLogServlet() {
    	securityService = new SecurityService();
        
    }

	
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AuthUser user = (AuthUser) request.getSession(false).getAttribute("authUser");
        Long societyId = (long) user.getSocietyId();

        String action = request.getParameter("action");

        if ("update".equalsIgnoreCase(action)) {
            try {
                Long logId = Long.parseLong(request.getParameter("logId"));
                boolean updated = securityService.updateVisitorCheckout(logId);

                if (updated) {
                    request.getSession().setAttribute("message", "Visitor checked out successfully.");
                } else {
                    request.getSession().setAttribute("error", "Failed to check out visitor.");
                }
            } catch (Exception e) {
                request.getSession().setAttribute("error", "Invalid visitor ID or update failed.");
            }
        }

        // Always fetch fresh logs after any action
        List<GateLog> logs = securityService.getVisitorLogsForSociety(societyId);
        request.setAttribute("visitorLogs", logs);

        request.getRequestDispatcher("WEB-INF/views/securityViews/viewVisitorLogs.jsp").forward(request, response);
    }


	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
