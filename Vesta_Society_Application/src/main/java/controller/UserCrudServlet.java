package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Users;
import service.UserService;

import java.io.IOException;
import java.util.List;

@WebServlet("/UserCrudServlet")
public class UserCrudServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserService userService;

    public UserCrudServlet() {
        this.userService = new UserService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        try {
            if ("create".equals(action)) {
                request.getRequestDispatcher("/WEB-INF/views/createUserForm.jsp").forward(request, response);

            } else if ("delete".equals(action)) {
                long userId = Long.parseLong(request.getParameter("userId"));
                boolean deleted = userService.deleteUserByUserId(userId);

                if (deleted) {
                    response.sendRedirect("UserCrudServlet?action=view");
                } else {
                    request.setAttribute("errorMessage", "Failed to delete user.");
                    doGet(request, response);
                }

            } else {
                List<Users> usersList = userService.getAllUsers();
                request.setAttribute("usersList", usersList);
                request.getRequestDispatcher("/WEB-INF/views/viewUsers.jsp").forward(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("UserCrudServlet?action=view");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if ("create".equals(action)) {
            String username = request.getParameter("username");
            String fullname = request.getParameter("fullname");
            String email = request.getParameter("email");
            String role = request.getParameter("role");
            int societyID = Integer.parseInt(request.getParameter("societyID"));

            boolean success = userService.createUserAccount(fullname, email, username, role, societyID);

            if (success) {
                response.sendRedirect("UserCrudServlet?action=view");
            } else {
                request.setAttribute("errorMessage", "Something went wrong while creating the user.");
                request.getRequestDispatcher("/WEB-INF/views/createUserForm.jsp").forward(request, response);
            }

        } else if ("saveUpdate".equals(action)) {
            try {
                long userId = Long.parseLong(request.getParameter("userId"));
                long memberId = Long.parseLong(request.getParameter("memberId"));
                String role = request.getParameter("role");
                String status = request.getParameter("status");
                int societyID = Integer.parseInt(request.getParameter("societyID"));

                boolean updated = userService.updateUserDetails(userId, memberId, role, status, societyID);

                if (updated) {
                    response.sendRedirect("UserCrudServlet?action=view");
                } else {
                    request.setAttribute("errorMessage", "Failed to update user.");
                    doGet(request, response);
                }

            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("errorMessage", "Invalid input or server error.");
                doGet(request, response);
            }

        } else {
            doGet(request, response);
        }
    }
}
