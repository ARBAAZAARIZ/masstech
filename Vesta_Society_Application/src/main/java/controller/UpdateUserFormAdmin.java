package controller;

import dao.SocietyDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Society;
import model.Users;
import service.UserService;

import java.io.IOException;
import java.util.List;

@WebServlet("/UpdateUserFormAdmin")
public class UpdateUserFormAdmin extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserService userService;

    public UpdateUserFormAdmin() {
        super();
        this.userService = new UserService(); // Initialize service
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            // Extract userId from URL
            long userId = Long.parseLong(request.getParameter("userId"));

            // Fetch user details
            Users user = userService.getUserById(userId);

            // Fetch society list
            List<Society> societyList = new SocietyDAO().getAllSocieties();

            // Set attributes for JSP
            request.setAttribute("user", user);
            request.setAttribute("societyList", societyList);

            // Forward to update form
            request.getRequestDispatcher("/WEB-INF/views/updateUserFormAdmin.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            // Redirect to user list if something goes wrong
            response.sendRedirect("UserCrudServlet?action=view");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Not used for form display — fallback to GET
        doGet(request, response);
    }
}
