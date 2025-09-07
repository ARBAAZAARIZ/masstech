package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.AuthUser;
import model.Member;
import model.Society;
import service.MemberService;
import service.SocietyService;

import java.io.IOException;




/**
 * Servlet implementation class ProfileServlet
 */
@WebServlet("/ProfileServlet")
public class ProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private final SocietyService societyService ;
    private final MemberService memberService ;
    public ProfileServlet() {
    	societyService = new SocietyService();
    	memberService = new MemberService();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AuthUser user = (AuthUser) request.getSession(false).getAttribute("authUser");
		if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }
		Society society = societyService.getSocietyById(user.getSocietyId());
		Member member = memberService.getMemberById(user.getMemberId());
		
		request.setAttribute("authUser", user);
		
		System.out.println(user.getProfile_photo() + " from profile servlet");
        request.setAttribute("society", society);
        request.setAttribute("member", member);
        
        
        if(user.getRole().equals("ADMIN")) {
        	 request.getRequestDispatcher("WEB-INF/views/AdminProfile.jsp").forward(request, response);
        	 
        }else if(user.getRole().equals("SECURITY")) {
        	request.getRequestDispatcher("WEB-INF/views/securityViews/profile.jsp").forward(request, response);
        	
        }else if(user.getRole().equals("SOCIETY MANAGER")) {
        	request.getRequestDispatcher("WEB-INF/views/societyManagerViews/societyManagerProfile.jsp").forward(request, response);
       }
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
