package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Complaint;
import service.ComplaintService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


@WebServlet("/ComplaintServlet")
public class ComplaintServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private final ComplaintService complaintService;
    public ComplaintServlet() {
    	complaintService = new ComplaintService();;
    }

	 
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            if ("view".equals(action)) {
                List<Complaint> complaints = complaintService.getAllComplaints();
                request.setAttribute("complaintList", complaints);
                request.getRequestDispatcher("WEB-INF/views/view_complaints.jsp").forward(request, response);
            } else if ("edit".equals(action)) {
                Long id = Long.parseLong(request.getParameter("complaintId"));
                Complaint complaint = complaintService.getComplaintById(id);
                request.setAttribute("complaint", complaint);
                request.getRequestDispatcher("WEB-INF/views/edit_complaint.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

	 
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("updateStatus".equals(action)) {
            try {
                Long id = Long.parseLong(request.getParameter("complaintId"));
                String status = request.getParameter("status");
                complaintService.updateComplaintStatus(id, status);

                // Redirect to view with success message
                response.sendRedirect("ComplaintServlet?action=view&message=Complaint+status+updated+successfully");
            } catch (Exception e) {
                // Redirect with error message
                response.sendRedirect("ComplaintServlet?action=view&error=Failed+to+update+complaint+status");
            }
        }
    }


}
