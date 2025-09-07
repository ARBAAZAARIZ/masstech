package controller.societyManagerController;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.AuthUser;
import model.Member;
import model.Users;
import service.SocietyService;
import service.UserService;
import service.MemberService;
import java.io.IOException;
import java.util.List;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    UserService userService;
    private MemberService memberService;
    public UserServlet() {
    	userService=new UserService();
    	memberService = new MemberService();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false); // false avoids creating new session

        AuthUser user = (AuthUser) session.getAttribute("authUser");
        
        String action=request.getParameter("action");
        System.out.println(action);
        
        switch(action) {
        case "view" :
        	List<Users> userList=userService.getUsersBySocietyId(user.getSocietyId());
        	SocietyService societyService=new SocietyService();
        	String societyName=societyService.getSocietyById(user.getSocietyId()).getName();
        	request.setAttribute("societyName", societyName);
        	request.setAttribute("usersList", userList);
        	request.getRequestDispatcher("WEB-INF/views/societyManagerViews/ViewUsers.jsp").forward(request, response);
        	break;
        	
        	
        case "edit" :
        	String memberIdParam = request.getParameter("memberID");
        	if (memberIdParam != null) {
        		int memberId = Integer.parseInt(memberIdParam);
                Member member = memberService.getMemberById(memberId);
                System.out.println(member.getEmail());
                request.setAttribute("member", member);
                request.getRequestDispatcher("WEB-INF/views/societyManagerViews/edit_Member.jsp").forward(request, response);          
        	}
        	else {
        		response.sendRedirect("UserServlet?action=view");
        	} 	
        	break;
         }
		
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		  AuthUser user = (AuthUser) session.getAttribute("authUser");
		
		 Long memberId = Long.parseLong(request.getParameter("memberId"));
		    String fullName = request.getParameter("fullName");
		    String email = request.getParameter("email");
		    String phone = request.getParameter("phone");
		    String status = request.getParameter("status");
		    
		    Member updatedMember = new Member();
		    updatedMember.setMemberId(memberId);
		    updatedMember.setFullName(fullName);
		    updatedMember.setEmail(email);
		    updatedMember.setPhone(phone);
		    updatedMember.setStatus(status);
		    
		    boolean success = memberService.updateMember(updatedMember);
		    
		    if (success) {
		        request.setAttribute("message", "Member updated successfully.");
		    } else {
		        request.setAttribute("error", "Failed to update member.");
		    }
		    
		    List<Member> memberList = memberService.getMembersBySocietyId(user.getSocietyId());
		    request.setAttribute("memberList", memberList);
		    SocietyService societyService=new SocietyService();
        	String societyName=societyService.getSocietyById(user.getSocietyId()).getName();
        	request.setAttribute("societyName", societyName);
		    request.setAttribute("societyName", societyName);
		    request.getRequestDispatcher("WEB-INF/views/societyManagerViews/view_members.jsp").forward(request, response);
	}

}
