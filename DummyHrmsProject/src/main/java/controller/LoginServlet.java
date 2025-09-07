package controller;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import model.Userdetails;
import service.LoginService;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private LoginService loginService;
    

    @Override
    public void init() throws ServletException {
        loginService = new LoginService();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Redirect to login.jsp
        response.sendRedirect("login.jsp");
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String emailOrUsername = request.getParameter("email");
        String password = request.getParameter("password");

        Userdetails user = loginService.authenticate(emailOrUsername, password);

        if (user != null && "Login successful".equalsIgnoreCase(user.getMessage())) {
            String role = user.getRole();
            request.setAttribute("user", user); 
            
            if ("HR".equalsIgnoreCase(role)) {
                request.getRequestDispatcher("HRServlet").forward(request, response);
            } else if ("Employee".equalsIgnoreCase(role)) {        
                request.getRequestDispatcher("EmployeeServlet").forward(request, response);
            } else {
                request.setAttribute("error", "Unknown role. Please contact admin.");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("error", "Invalid email or password");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
