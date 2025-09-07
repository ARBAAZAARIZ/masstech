package controller.societyManagerController;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.AuthUser;
import model.ParkingAssignmentDetails;
import model.ParkingSlot;
import model.Vehicle;
import service.ParkingService;
import service.ParkingSlotService;
import service.VehicleService;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet("/ParkingAssignmentServlet")
public class ParkingAssignmentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private ParkingService parkingService;
    private ParkingSlotService slotService;
    private VehicleService vehicleService;

    public ParkingAssignmentServlet() {
        parkingService = new ParkingService();
        slotService = new ParkingSlotService();
        vehicleService = new VehicleService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        AuthUser user = (AuthUser) session.getAttribute("authUser");
        String action = request.getParameter("action");

        if (action == null || action.equals("view")) {
            List<ParkingAssignmentDetails> parkingList = parkingService.getParkingAssignmentsBySocietyId(user.getSocietyId());
            request.setAttribute("parkingList", parkingList);
            request.getRequestDispatcher("WEB-INF/views/societyManagerViews/parking_assignments.jsp").forward(request, response);

        } else if (action.equals("init")) {
            List<ParkingSlot> availableSlots = slotService.getAvailableSlotsBySocietyId(user.getSocietyId());
            List<Vehicle> unassignedVehicles = vehicleService.getUnassignedVehiclesBySocietyId(user.getSocietyId());

            request.setAttribute("availableSlots", availableSlots);
            request.setAttribute("unassignedVehicles", unassignedVehicles);
            request.setAttribute("parkingList", null);
            request.getRequestDispatcher("WEB-INF/views/societyManagerViews/assign_parking.jsp").forward(request, response);

        } else if (action.equals("delete")) {
            Long vehicleId = Long.parseLong(request.getParameter("vehicleId"));
            parkingService.deleteAssignmentByVehicleId(vehicleId);
            response.sendRedirect("ParkingAssignmentServlet?action=view");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action != null && action.equals("assign")) {
            Long slotId = Long.parseLong(request.getParameter("slotId"));
            Long vehicleId = Long.parseLong(request.getParameter("vehicleId"));
            Date startDate = Date.valueOf(request.getParameter("startDate"));
            String endDateParam = request.getParameter("endDate");
            Date endDate = (endDateParam != null && !endDateParam.isEmpty()) ? Date.valueOf(endDateParam) : null;

            boolean success = parkingService.assignParking(slotId, vehicleId, startDate, endDate);

            if (success) {
                slotService.markSlotAsCovered(slotId); // update slot status
                response.sendRedirect("ParkingAssignmentServlet?action=view");
            } else {
                request.setAttribute("error", "Failed to assign parking.");
                doGet(request, response);
            }
        }
    }
}
