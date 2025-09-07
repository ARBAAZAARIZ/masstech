package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.AuthUser;
import model.Member;
import model.Society;
import service.MemberService;
import service.SocietyService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

@WebServlet("/UpdateProfileServlet")
@MultipartConfig(
    fileSizeThreshold = 1024 * 1024 * 2,  // 2MB
    maxFileSize = 1024 * 1024 * 10,       // 10MB
    maxRequestSize = 1024 * 1024 * 100    // 100MB
)
public class UpdateProfileServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final MemberService memberService = new MemberService();
    private final SocietyService societyService = new SocietyService();

    public UpdateProfileServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AuthUser user = (AuthUser) request.getSession(false).getAttribute("authUser");

        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        int memberId = user.getMemberId();
        int societyId = user.getSocietyId();

        Member member = memberService.getMemberById(memberId);
        
        Society society = societyService.getSocietyById(societyId);

        request.setAttribute("authUser", user);     // for username and profile photo
        request.setAttribute("member", member);     // for editable member fields
        request.setAttribute("society", society);   // for society name

        if(user.getRole().trim().equalsIgnoreCase("ADMIN")) {
        	request.getRequestDispatcher("WEB-INF/views/adminUpdate.jsp").forward(request, response);
        }else if(user.getRole().trim().equalsIgnoreCase("SECURITY")) {
        	request.getRequestDispatcher("WEB-INF/views/securityViews/updateProfile.jsp").forward(request, response);
        }else if(user.getRole().trim().equalsIgnoreCase("SOCIETY MANAGER")) {
        	request.getRequestDispatcher("WEB-INF/views/societyManagerViews/societyManagerUpdateProfile.jsp").forward(request, response);
        }
        
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int memberId = Integer.parseInt(request.getParameter("memberId"));
        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String existingPhoto = request.getParameter("existingPhoto");

        Part photoPart = request.getPart("profilePhoto");
        String fileName = Paths.get(photoPart.getSubmittedFileName()).getFileName().toString();
        String photoPath;

        if (fileName != null && !fileName.isEmpty()) {
            String uploadPath = getServletContext().getRealPath("/images");
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            String uniqueName = "member_" + memberId + "_" + System.currentTimeMillis() + "_" + fileName;
            String fullPath = uploadPath + File.separator + uniqueName;
            photoPart.write(fullPath);

            photoPath =   uniqueName;
        } else {
            photoPath = existingPhoto;
        }

        boolean updated = memberService.updateMemberDetails(memberId, fullName, email, phone, photoPath);

        if (updated) {
        	// Fetch updated member info
            Member updatedMember = memberService.getMemberById(memberId);
         // Get current authUser from session
            AuthUser user = (AuthUser) request.getSession(false).getAttribute("authUser");
            
            user.setFullName(updatedMember.getFullName());
            user.setEmail(updatedMember.getEmail());
            user.setPhone(updatedMember.getPhone());
            user.setProfile_photo(updatedMember.getPofile_photo()); 
            
         // Set updated object back into session
            request.getSession().setAttribute("authUser", user);
            System.out.println(user.getProfile_photo() + "from updated servlet ");
            
            request.getSession().setAttribute("message", "Profile updated successfully.");
        } else {
            request.getSession().setAttribute("error", "Failed to update profile.");
        }

        response.sendRedirect("ProfileServlet");
    }
}  
