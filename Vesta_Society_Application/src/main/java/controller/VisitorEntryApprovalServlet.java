package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import model.AuthUser;
import model.Building;
import model.Flat;
import model.Society;
import service.BuildingService;
import service.FlatService;
import service.SocietyService;
import service.VisitorApprovalService;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

import jakarta.servlet.http.Part;
import java.nio.file.Paths;

import java.io.File;
import java.io.IOException;


@WebServlet("/VisitorEntryApprovalServlet")
@MultipartConfig(
	    fileSizeThreshold = 1024 * 1024 * 2,  // 2MB
	    maxFileSize = 1024 * 1024 * 10,       // 10MB
	    maxRequestSize = 1024 * 1024 * 100    // 100MB
	)
public class VisitorEntryApprovalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public VisitorEntryApprovalServlet() {
        super();
        
    }

	
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("authUser") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        AuthUser user = (AuthUser) session.getAttribute("authUser");
        int societyId = user.getSocietyId(); // Assuming this getter exists

        String buildingIdParam = request.getParameter("buildingId");

        try {
            BuildingService buildingService = new BuildingService();
            FlatService flatService = new FlatService();

            List<Building> buildingList = buildingService.getBuildingsBySocietyID(societyId);
            request.setAttribute("buildingList", buildingList);
            SocietyService societyService=new  SocietyService();
            Society society=societyService.getSocietyById(societyId);
            request.setAttribute("society", society); // Optional: for display

            if (buildingIdParam != null && !buildingIdParam.isBlank()) {
                long buildingId = Long.parseLong(buildingIdParam);
                List<Flat> flatList = flatService.getFlatsByBuildingId(buildingId);
                request.setAttribute("flatList", flatList);
                request.setAttribute("selectedBuilding", buildingId);
            }

            request.getRequestDispatcher("WEB-INF/views/securityViews/VisitorApproval.jsp")
                   .forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Something went wrong while loading visitor approval form.");
            request.getRequestDispatcher("WEB-INF/views/securityViews/visitorApproval.jsp")
                   .forward(request, response);
        }
    }


	
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("authUser") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        AuthUser user = (AuthUser) session.getAttribute("authUser");
        int societyId = user.getSocietyId();

        String visitorName = request.getParameter("visitorName");
        
        String purpose = request.getParameter("purpose");
        String flatIdParam = request.getParameter("flatId");

        long flatId = Long.parseLong(flatIdParam);

       
        Part photoPart = request.getPart("visitorPhoto");
        String fileName = Paths.get(photoPart.getSubmittedFileName()).getFileName().toString();
        String photoPath;
        
        if (fileName != null && !fileName.isEmpty()) {
        	String uploadPath = getServletContext().getRealPath("/images");
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }
            String uniqueName = "visitor" + "_" + System.currentTimeMillis() + "_" + fileName;
            String fullPath = uploadPath + File.separator + uniqueName;
            photoPart.write(fullPath);
            
            photoPath =   uniqueName;
        }else {
            photoPath = null;
        }

       

       

        
        VisitorApprovalService service = new VisitorApprovalService();
        boolean success = service.logVisitorEntry(visitorName, purpose, flatId, photoPath);

        
        BuildingService buildingService = new BuildingService();
        FlatService flatService = new FlatService();
        SocietyService societyService = new SocietyService();

        List<Building> buildingList = buildingService.getBuildingsBySocietyID(societyId);
        List<Flat> flatList = flatService.getFlatsByBuildingId(flatId);
        Society society = societyService.getSocietyById(societyId);

        request.setAttribute("buildingList", buildingList);
        request.setAttribute("flatList", flatList);
        request.setAttribute("selectedBuilding", flatId);
        request.setAttribute("society", society);

        if (success) {
            request.setAttribute("message", "Visitor entry logged successfully.");
        } else {
            request.setAttribute("error", "Failed to log visitor entry. Please check flat occupancy.");
        }

        request.getRequestDispatcher("WEB-INF/views/securityViews/VisitorApproval.jsp")
               .forward(request, response);
    }


}
