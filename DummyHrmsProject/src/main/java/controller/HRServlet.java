package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Userdetails;

import java.io.IOException;

@WebServlet("/HRServlet")
public class HRServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public HRServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve user object from request scope
        Userdetails user = (Userdetails) request.getAttribute("user");

        if (user == null) {
            request.setAttribute("error", "Session expired or invalid access.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        // Set attributes for dashboard display
        request.setAttribute("hrName", user.getName());
        request.setAttribute("hrId", user.getEmpId());

        // Forward to dashboard JSP
        request.getRequestDispatcher("WEB-INF/views/hr_dashboard.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response); // or handle POST logic here
    }


   
}
