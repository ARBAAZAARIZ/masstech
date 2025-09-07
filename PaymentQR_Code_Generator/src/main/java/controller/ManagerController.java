package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Manager;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


@WebServlet("/ManagerController")
public class ManagerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static final String url="jdbc:mysql://localhost:3306/vesta_db";
	private static final String username="root";
	private static final String password="";
   Connection conn;
    public ManagerController() {
        try {
        	Class.forName("com.mysql.cj.jdbc.Driver");
        	conn=DriverManager.getConnection(url,username,password);
        }catch(Exception e) {
        	
        }
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.sendRedirect("AddManager.jsp");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name=request.getParameter("name");
		
		String insertQuery="insert into manager(mname) values ('"+name+"')";
		
		try {
			PreparedStatement psmt=conn.prepareStatement(insertQuery);
			int success=psmt.executeUpdate();
			if(success>0) {
				
				String viewQuerManager="select * from manager";
				PreparedStatement viewQuerManagerpsmt=conn.prepareStatement(viewQuerManager);
				ResultSet rs=viewQuerManagerpsmt.executeQuery();
				
				List<Manager> mamangerList=new ArrayList();
				
				while(rs.next()) {
					int id=rs.getInt("mid");
					name=rs.getString("mname");
					mamangerList.add(new Manager(id,name));
					
				}
				request.setAttribute("mamangerList", mamangerList);
				
				request.getRequestDispatcher("/ViewManagers.jsp").forward(request, response);
				
				
			}else {
				System.out.println("Something went wrong");
			}
		}catch(Exception e) {
			
		}
		
		
		
		
		
	}

}
