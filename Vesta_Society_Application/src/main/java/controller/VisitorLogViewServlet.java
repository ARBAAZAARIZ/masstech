package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;


import jakarta.servlet.http.*;
import model.AuthUser;
import model.VisitorLogView;
import service.VisitorLogViewService;


import java.util.List;

import dao.VisitorApprovalDAO;

/**
 * Servlet implementation class VisitorLogViewServlet
 */
@WebServlet("/VisitorLogViewServlet")
public class VisitorLogViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VisitorLogViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("authUser") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        AuthUser user = (AuthUser) session.getAttribute("authUser");
        long societyId = user.getSocietyId();

        VisitorLogViewService service = new VisitorLogViewService();
        List<VisitorLogView> logs = service.getLogsForSociety(societyId);

        request.setAttribute("visitorLogs", logs);
        request.getRequestDispatcher("WEB-INF/views/securityViews/VisitorLogView.jsp").forward(request, response);
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("approve".equals(action)) {
            String visitorIdParam = request.getParameter("visitorId");

            try {
                long visitorId = Long.parseLong(visitorIdParam);
                VisitorApprovalDAO dao = new VisitorApprovalDAO();
                boolean updated = dao.updateVisitorStatus(visitorId, "Approved");

                if (updated) {
                    request.setAttribute("message", "Visitor approved successfully.");
                } else {
                    request.setAttribute("error", "Failed to approve visitor.");
                }

            } catch (NumberFormatException e) {
                request.setAttribute("error", "Invalid visitor ID.");
            }
        }

        // Reload logs after update
        HttpSession session = request.getSession(false);
        AuthUser user = (AuthUser) session.getAttribute("authUser");
        long societyId = user.getSocietyId();

        VisitorLogViewService service = new VisitorLogViewService();
        List<VisitorLogView> logs = service.getLogsForSociety(societyId);

        request.setAttribute("visitorLogs", logs);
        request.getRequestDispatcher("WEB-INF/views/securityViews/VisitorLogView.jsp").forward(request, response);
    }


}
