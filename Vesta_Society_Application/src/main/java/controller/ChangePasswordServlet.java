package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.AuthUser;
import service.UserService;
import jakarta.servlet.http.*;

import java.io.IOException;


@WebServlet("/ChangePasswordServlet")
public class ChangePasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    private UserService userService;
    public ChangePasswordServlet() {
    	userService=new UserService();
        
    }

	
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession(false);
    	AuthUser user = (AuthUser) session.getAttribute("authUser");
    	
    	if(user.getRole().trim().equalsIgnoreCase("ADMIN")) {
    		request.getRequestDispatcher("WEB-INF/views/adminChangePassword.jsp")
            .forward(request, response);
    	}else if(user.getRole().trim().equalsIgnoreCase("SECURITY")) {
    		request.getRequestDispatcher("WEB-INF/views/securityViews/changePassword.jsp")
            .forward(request, response);
    	}
        
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 HttpSession session = request.getSession(false);
		 AuthUser user = (AuthUser) session.getAttribute("authUser");
		 if (user == null) {
	            response.sendRedirect("login.jsp");
	            return;
	        }
		 
		 String oldPwd     = request.getParameter("oldPassword");
	        String newPwd     = request.getParameter("newPassword");
	        String confirmPwd = request.getParameter("confirmPassword");
	        
	        if (!newPwd.equals(confirmPwd)) {
	            session.setAttribute("error", "New password and confirm password do not match.");
	            response.sendRedirect("ChangePasswordServlet");
	            return;
	        }
	        
	        boolean changed = userService.changePassword(user.getUserId(), oldPwd, newPwd);
	        
	        if (changed) {
	            session.setAttribute("message", "Password changed successfully.");
	        } else {
	            session.setAttribute("error", "Current password is incorrect or update failed.");
	        }

	        response.sendRedirect("ChangePasswordServlet");
	}

}
