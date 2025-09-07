package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Society;
import service.SocietyService;

import java.io.IOException;
import java.util.List;

@WebServlet("/SocietyCrudServlet")
public class SocietyCrudServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private SocietyService societyService;

    public SocietyCrudServlet() {
        this.societyService = new SocietyService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) action = "view"; // Default fallback

        switch (action) {
            case "create":
                // Show the form to create a new society
                request.getRequestDispatcher("/WEB-INF/views/createSocietyForm.jsp").forward(request, response);
                break;
                
            case "edit" :
            	long societyId = Long.parseLong(request.getParameter("societyId"));
            	Society society = societyService.getSocietyById(societyId);
            	
            	 request.setAttribute("society", society);
            	 request.getRequestDispatcher("/WEB-INF/views/updateSocietyForm.jsp").forward(request, response);
            	 break;
            	 
            case "delete" :
            	 long sociId = Long.parseLong(request.getParameter("societyId"));
            	    boolean deleted = societyService.deleteSociety(sociId);
            	    
            	    if (deleted) {
            	        response.sendRedirect("SocietyCrudServlet?action=view");
            	    } else {
            	        request.setAttribute("errorMessage", "Failed to delete society. Please try again.");
            	        doGet(request, response);
            	    }
            	    
            	    break;
        

            case "view":
            default:
                // Show all societies
                List<Society> societyList = societyService.getAllSocieties();
                request.setAttribute("societyList", societyList);
                request.getRequestDispatcher("/WEB-INF/views/viewSocieties.jsp").forward(request, response);
                break;
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) action = "";

        switch (action) {
            case "saveCreate": {
                String name = request.getParameter("name");
                String addressLine1 = request.getParameter("addressLine1");
                String addressLine2 = request.getParameter("addressLine2");
                String city = request.getParameter("city");
                String state = request.getParameter("state");
                String pincode = request.getParameter("pincode");

                boolean success = societyService.createSociety(name, addressLine1, addressLine2, city, state, pincode);

                if (success) {
                    response.sendRedirect("SocietyCrudServlet?action=view");
                } else {
                    request.setAttribute("errorMessage", "Failed to create society. Society name already exists.");
                    request.getRequestDispatcher("/WEB-INF/views/createSocietyForm.jsp").forward(request, response);
                }
                break;
            }

            case "saveUpdate": {
                long societyId = Long.parseLong(request.getParameter("societyId"));
                String name = request.getParameter("name");
                String addressLine1 = request.getParameter("addressLine1");
                String addressLine2 = request.getParameter("addressLine2");
                String city = request.getParameter("city");
                String state = request.getParameter("state");
                String pincode = request.getParameter("pincode");

                boolean updated = societyService.updateSociety(societyId, name, addressLine1, addressLine2, city, state, pincode);

                if (updated) {
                    response.sendRedirect("SocietyCrudServlet?action=view");
                } else {
                    request.setAttribute("errorMessage", "Failed to update society. Please try again.");
                    doGet(request, response); // fallback to view
                }
                break;
            }

            default:
                doGet(request, response); // fallback
        }
    }

}
