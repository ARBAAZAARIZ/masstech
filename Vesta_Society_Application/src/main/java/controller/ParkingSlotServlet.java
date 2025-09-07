package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ParkingSlot;
import model.Society;
import service.ParkingSlotService;
import service.SocietyService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


@WebServlet("/ParkingSlotServlet")
public class ParkingSlotServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private final ParkingSlotService service;
	private final SocietyService societyService;
   
    public ParkingSlotServlet() {
    	this.service = new ParkingSlotService();
       this.societyService=new SocietyService(); 
    }

	
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            if ("view".equals(action)) {
                List<ParkingSlot> slots = service.getAllSlots();
                request.setAttribute("slotList", slots);
                request.getRequestDispatcher("WEB-INF/views/view_parking_slots.jsp").forward(request, response);
            }

            if ("edit".equals(action)) {
                Long slotId = Long.parseLong(request.getParameter("slotId"));
                ParkingSlot slot = service.getSlotById(slotId);
                List<Society> societyList=societyService.getAllSocieties();
                request.setAttribute("slot", slot);
                request.setAttribute("societyList", societyList);
                request.getRequestDispatcher("WEB-INF/views/edit_parking_slot.jsp").forward(request, response);
            }
            
            if("create".equals(action)) {
            	
            	List<Society> societyList=societyService.getAllSocieties();
            request.setAttribute("societyList", societyList);
            request.getRequestDispatcher("WEB-INF/views/create_parking_slot.jsp").forward(request, response);
            
            	
            }

            if ("delete".equals(action)) {
                Long slotId = Long.parseLong(request.getParameter("slotId"));
                service.deleteSlot(slotId);
                response.sendRedirect("ParkingSlotServlet?action=view");
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

	
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            if ("saveCreate".equals(action)) {
                ParkingSlot slot = new ParkingSlot();
                slot.setSocietyId(Long.parseLong(request.getParameter("societyId")));
                slot.setIdentifier(request.getParameter("identifier"));
                slot.setCovered(request.getParameter("isCovered") != null);
                service.createSlot(slot);
                response.sendRedirect("ParkingSlotServlet?action=view");
            }

            if ("saveUpdate".equals(action)) {
                ParkingSlot slot = new ParkingSlot();
                slot.setSlotId(Long.parseLong(request.getParameter("slotId")));
                slot.setSocietyId(Long.parseLong(request.getParameter("societyId")));
                slot.setIdentifier(request.getParameter("identifier"));
                slot.setCovered(request.getParameter("isCovered") != null);
                service.updateSlot(slot);
                response.sendRedirect("ParkingSlotServlet?action=view");
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

}
