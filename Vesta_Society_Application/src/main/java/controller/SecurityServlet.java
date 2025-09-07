package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.AuthUser;

import java.io.IOException;


@WebServlet("/SecurityServlet")
public class SecurityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public SecurityServlet() {
        super();
        
    }

	
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false); // Don't create new session
        AuthUser user = (AuthUser) session.getAttribute("authUser");

        if (user != null && "SECURITY".equalsIgnoreCase(user.getRole())) {
            request.setAttribute("authUser", user);
            request.getRequestDispatcher("WEB-INF/views/securityViews/securityDashboard.jsp").forward(request, response);
        } else {
            response.sendRedirect("login.jsp"); // fallback if session expired or role mismatch
        }
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
