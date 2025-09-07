package controller.societyManagerController;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.AuthUser;
import model.Member;
import service.MemberService;
import service.SocietyService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/MemberServlet")
public class MemberServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private MemberService memberService;

    public MemberServlet() {
        memberService = new MemberService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        AuthUser user = (AuthUser) session.getAttribute("authUser");

        String searchName = request.getParameter("searchName");
        List<Member> memberList;

        if (searchName != null && !searchName.trim().isEmpty()) {
            memberList = memberService.searchMembersByName(user.getSocietyId(), searchName.trim());
            if (memberList.isEmpty()) {
                request.setAttribute("error", "No members found matching \"" + searchName + "\".");
            }
        } else {
            memberList = memberService.getMembersBySocietyId(user.getSocietyId());
        }

        request.setAttribute("memberList", memberList);

        SocietyService societyService = new SocietyService();
        String societyName = societyService.getSocietyById(user.getSocietyId()).getName();
        request.setAttribute("societyName", societyName);

        request.getRequestDispatcher("WEB-INF/views/societyManagerViews/view_members.jsp").forward(request, response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    }
}
