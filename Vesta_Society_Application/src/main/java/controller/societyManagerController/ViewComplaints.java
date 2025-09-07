package controller.societyManagerController;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.AuthUser;
import model.ComplaintDetails;
import service.ComplaintService;

import java.io.IOException;
import java.util.List;



@WebServlet("/ViewComplaints")
public class ViewComplaints extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	ComplaintService complaintService;

    public ViewComplaints() {
        
    	this.complaintService=new ComplaintService();
    	
    }



    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        AuthUser user = (AuthUser) session.getAttribute("authUser");
        String action = request.getParameter("action");

        if (action == null || action.equals("view")) {
            List<ComplaintDetails> complaintList = complaintService.getComplaintsBySocietyId(user.getSocietyId());
            request.setAttribute("complaintList", complaintList);
            request.getRequestDispatcher("WEB-INF/views/societyManagerViews/viewComplaints.jsp").forward(request, response);

        } else if (action.equals("update")) {
            Long complaintId = Long.parseLong(request.getParameter("complaintId"));
            String status = request.getParameter("status");
            complaintService.updateComplaintStatusBySocietyManager(complaintId, status);
            response.sendRedirect("ViewComplaints?action=view");
        }
    }




	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
