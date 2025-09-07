 	package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Emp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Servlet implementation class EmployeeCrudServlet
 */
@WebServlet("/EmployeeCrudServlet")
public class EmployeeCrudServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	Connection conn;
    public EmployeeCrudServlet() throws Exception {
        super();
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url="jdbc:mysql://localhost:3306/servlet_example";
		String username="root";
		
		conn=DriverManager.getConnection(url,username,"");
        
        
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String getEmployeeQuery = "SELECT * FROM emp";
        List<Emp> emplList = new ArrayList<>();

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            PreparedStatement psmt = conn.prepareStatement(getEmployeeQuery);
            ResultSet rs = psmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double salary = rs.getDouble("salary");
                String image = rs.getString("image");
                emplList.add(new Emp(id, name, salary, image));
            }
            out.println("<html><head></head><body>");
            
            out.println("<table border='1'>");
            out.println("<tr><th>ID</th><th>Name</th><th>Salary</th><th>Image</th></tr>");

            for (Emp emp : emplList) {
                out.println("<tr>");
                out.println("<td>" + emp.id + "</td>");
                out.println("<td>" + emp.name + "</td>");
                out.println("<td>" + emp.salary + "</td>");
                out.println("<td>" + emp.image + "</td>");
                out.println("</tr>");
            }

            out.println("</table>");
            out.println("</body></html>");
            
            
            

        } catch (Exception e) {
            out.println("<p>Error: " + e.getMessage() + "</p>");
            e.printStackTrace();
        }
         
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
	        throws ServletException, IOException {
	    PrintWriter out = response.getWriter();
	    String action = request.getParameter("action");

	    try {
	        if ("insert".equalsIgnoreCase(action)) {
	            String name = request.getParameter("name");
	            double salary = Double.parseDouble(request.getParameter("salary"));
	            String image = request.getParameter("image");

	            String insertQuery = "INSERT INTO emp (name, salary, image) VALUES (?, ?, ?)";
	            PreparedStatement psmt = conn.prepareStatement(insertQuery);
	            psmt.setString(1, name);
	            psmt.setDouble(2, salary);
	            psmt.setString(3, image);
	            int rows = psmt.executeUpdate();
	            out.println(rows > 0 ? " Employee inserted successfully." : " Insert failed.");
	            
	            Thread.sleep(6000);
	            response.sendRedirect("index.html");

	        } else if ("update".equalsIgnoreCase(action)) {
	            String id = request.getParameter("id");
	            String name = request.getParameter("name");
	            double salary = Double.parseDouble(request.getParameter("salary"));

	            String updateQuery = "UPDATE emp SET name = ?, salary = ? WHERE id = ?";
	            PreparedStatement psmt = conn.prepareStatement(updateQuery);
	            psmt.setString(1, name);
	            psmt.setDouble(2, salary);
	            psmt.setString(3, id);
	            int rows = psmt.executeUpdate();
	            out.println(rows > 0 ? " Employee updated successfully." : " No employee found with ID " + id);

	            Thread.sleep(6000);
	            response.sendRedirect("index.html");
	            
	            
	        } else if ("delete".equalsIgnoreCase(action)) {
	            String id = request.getParameter("id");

	            String deleteQuery = "DELETE FROM emp WHERE id = ?";
	            PreparedStatement psmt = conn.prepareStatement(deleteQuery);
	            psmt.setString(1, id);
	            int rows = psmt.executeUpdate();
	            out.println(rows > 0 ? " Employee deleted successfully." : " No employee found with ID " + id);

	            Thread.sleep(6000);
	            response.sendRedirect("index.html");
	            
	        } else {
	            out.println(" Unknown action: " + action);
	        }

	    } catch (SQLException | NumberFormatException | InterruptedException e) {
	        e.printStackTrace();
	        out.println(" Database error: " + e.getMessage());
	    }
	}


	
	


}
