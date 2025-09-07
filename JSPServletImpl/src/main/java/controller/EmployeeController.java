package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Employee;
import service.EmployeeService;

import java.io.IOException;
import java.util.List;

/**
 * Servlet implementation class EmployeeController
 */
@WebServlet("/EmployeeController")
public class EmployeeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeeController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		EmployeeService ser=new EmployeeService();
		
		String action=request.getParameter("action");
		if(action==null) action="list";
		
		switch(action)
		{
			case "list":
					List<Employee> emps=ser.fetchEmployee();
					System.out.println(emps);
					request.setAttribute("emps", emps);
					RequestDispatcher r1=request.getRequestDispatcher("WEB-INF/views/emplist.jsp");
					r1.forward(request, response);
					break;
					
			case "add":
				RequestDispatcher dis=request.getRequestDispatcher("WEB-INF/views/AddEmp.jsp");
				dis.forward(request, response);
				break;
				
			case "delete":
				int id=Integer.parseInt(request.getParameter("id"));
				ser.deleteEmpById(id);
				response.sendRedirect("EmployeeController");
				break;
		
			case "edit":
				int eid=Integer.parseInt(request.getParameter("id"));
				Employee emp=ser.findEmpById(eid);
				request.setAttribute("em", emp);
				RequestDispatcher r2=request.getRequestDispatcher("WEB-INF/views/edit.jsp");
				r2.forward(request, response);
				break;
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
