package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.AuthUser;
import model.DeliveryLog;
import service.SecurityService;

import java.io.IOException;
import java.util.List;


@WebServlet("/DeliveryLogServlet")
public class DeliveryLogServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final SecurityService securityService;
    
    public DeliveryLogServlet() {
    	securityService = new SecurityService();
        
    }

	
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AuthUser user = (AuthUser) request.getSession(false).getAttribute("authUser");
        Long societyId = (long) user.getSocietyId();

        List<DeliveryLog> logs = securityService.getDeliveryLogsForSociety(societyId);
        request.setAttribute("deliveryLogs", logs);
        request.getRequestDispatcher("WEB-INF/views/securityViews/viewDeliveryLogs.jsp").forward(request, response);
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
