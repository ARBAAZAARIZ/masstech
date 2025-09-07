package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;
import model.EmployeeDetail;
import service.EmployeeService;

@WebServlet("/ViewEmployeesServlet")
public class ViewEmployeesServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private EmployeeService employeeService;

    public ViewEmployeesServlet() {
        employeeService = new EmployeeService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<EmployeeDetail> employeeList = employeeService.getAllEmployeeDetails();

        request.setAttribute("employeeList", employeeList);
        request.getRequestDispatcher("/WEB-INF/views/viewAllEmployee.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
