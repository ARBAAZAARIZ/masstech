package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import model.Employee;
import service.EmployeeService;

import java.io.File;
import java.io.IOException;

/**
 * Servlet implementation class AddEmpServlet
 */
@WebServlet("/AddEmpServlet")
@MultipartConfig(
			fileSizeThreshold = 1024*1024*2,
			maxFileSize = 1024*1024*10,
			maxRequestSize = 1024*1024*100
		)
public class AddEmpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    EmployeeService service=new EmployeeService();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddEmpServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String ename,email,profile_photo;
		double esalary;
		
		ename=request.getParameter("ename");
		email=request.getParameter("email");
		esalary=Double.parseDouble(request.getParameter("esalary"));
	
		Part filePart = request.getPart("profile_photo");
String fileName = filePart.getSubmittedFileName();

		
		String uploadPath = getServletContext().getRealPath("/Images");
		File uploadDir = new File(uploadPath);
		if (!uploadDir.exists()) {
		    uploadDir.mkdirs(); // make directories if not exist
		}

		// Full path where file will be saved
		String filePath = uploadPath + File.separator + fileName;
		filePart.write(filePath);

		
		String dbPath = "Images/" + fileName;
		
		Employee em=new Employee();
		em.setEname(ename);
		em.setEmail(email);
		em.setEsalary(esalary);
		em.setProfile_photo(dbPath);
		service.AddEmployee(em);
		
		response.sendRedirect("EmployeeController");
	}

}
