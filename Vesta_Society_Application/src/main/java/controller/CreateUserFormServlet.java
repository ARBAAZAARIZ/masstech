package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import dao.SocietyDAO;
import model.Society;

@WebServlet("/CreateUserFormServlet")
public class CreateUserFormServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private SocietyDAO societyDAO;

    public CreateUserFormServlet() {
        this.societyDAO = new SocietyDAO(); // Fix: assign to class-level field
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Society> societyList = societyDAO.getAllSocieties(); // Fix: use correct type
        request.setAttribute("societyList", societyList); // Send to JSP
        request.getRequestDispatcher("/WEB-INF/views/createUserForm.jsp").forward(request, response); // Forward
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response); // Optional: reuse for form display
    }
}
