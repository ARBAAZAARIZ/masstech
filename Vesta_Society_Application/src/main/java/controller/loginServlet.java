package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.AuthUser;
import service.AuthService;

import java.io.IOException;


@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	AuthService authService;
    
    public loginServlet() {
        
        this.authService=new AuthService();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.sendRedirect("login.jsp");
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("username");
		 String password = request.getParameter("password");
		 
		 AuthService authService = new AuthService();
		 AuthUser user = authService.login(username, password);
		 
		 if (user != null) {
		        HttpSession session = request.getSession();
		        session.setAttribute("authUser", user);
		        session.setMaxInactiveInterval(60 * 60);
		        
		        if(user.getRole().trim().equalsIgnoreCase("ADMIN")) {
		        	response.sendRedirect("AdminServlet");
		        	
		        }else if(user.getRole().trim().equalsIgnoreCase("SECURITY")) {
		        	response.sendRedirect("SecurityServlet");
		        }else if(user.getRole().trim().equalsIgnoreCase("SOCIETY MANAGER")) {
		        	response.sendRedirect("SocietyManagerServlet");
		        }
		    }else {
		        request.setAttribute("error", "Invalid credentials");
		        request.getRequestDispatcher("login.jsp").forward(request, response);
		    }
	}

}
