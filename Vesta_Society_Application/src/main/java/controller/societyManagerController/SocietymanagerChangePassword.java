package controller.societyManagerController;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.AuthUser;
import service.UserService;
import util.EmailUtil;

import java.io.IOException;
import java.util.Random;


@WebServlet("/SocietymanagerChangePassword")
public class SocietymanagerChangePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public SocietymanagerChangePassword() {
        super();
       
    }

	
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("authUser") == null) {
            response.sendRedirect("LoginServlet");
            return;
        }

        AuthUser user = (AuthUser) session.getAttribute("authUser");
        String action = request.getParameter("action");

        if (action == null) {
            response.sendRedirect("ProfileServlet");
            return;
        }

        switch (action) {
            case "generateOTP":
                
                int otp = new Random().nextInt(900000) + 100000;

               
                session.setAttribute("otp", otp);

                
                String email = user.getEmail();
                EmailUtil.sendOtpToEmail(email, otp);

                
                request.getRequestDispatcher("WEB-INF/views/societyManagerViews/societyManagerOptView.jsp")
                       .forward(request, response);
                break;

            case "checkOTP":
                String userOTP = request.getParameter("otp");
                Object sessionOtpObj = session.getAttribute("otp");

                if (userOTP != null && sessionOtpObj != null &&
                    userOTP.trim().equals(String.valueOf(sessionOtpObj).trim())) {

                    
                    request.getRequestDispatcher("WEB-INF/views/societyManagerViews/societyChnagePasswordView.jsp")
                           .forward(request, response);
                } else {
                    
                    request.setAttribute("otpErrorMessage", "Invalid OTP !!");
                    request.getRequestDispatcher("WEB-INF/views/societyManagerViews/societyManagerOptView.jsp")
                           .forward(request, response);
                }
                break;

            default:
                response.sendRedirect("ProfileServlet");
                break;
        }
    }


	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
	    AuthUser user = (AuthUser) session.getAttribute("authUser");
	    System.out.println(user.getEmail() + " SocietymanagerChangePassword dopost method ");
		
		String password=request.getParameter("newPassword").trim();
		String confirmPassword=request.getParameter("confirmPassword").trim();
		System.out.println(password.equals(confirmPassword) + " from SocietymanagerChangePassword do post method ");
		
		if(!password.equals(confirmPassword)) {
			request.setAttribute("passwordError", "New Password and Confim Password is not same");
			request.getRequestDispatcher("WEB-INF/views/societyManagerViews/societyChnagePasswordView.jsp").forward(request, response);
		}else {
			UserService userService=new UserService();
			
			long userId=user.getUserId();
			
			boolean success=userService.changePasswordForSocietymanager(userId,password);
			
			if(success) {
				System.out.println("true");
				request.setAttribute("passwordSuccessMessage", "Your Password has been changed");
				request.getRequestDispatcher("WEB-INF/views/societyManagerViews/societyChnagePasswordView.jsp")
				.forward(request, response);
			}else {
				System.out.println("false");
				request.setAttribute("passwordErrorMessage", "Something went Wrong. Try again !!");
				request.getRequestDispatcher("WEB-INF/views/societyManagerViews/societyChnagePasswordView.jsp")
				.forward(request, response);
			}
			
		}
		
		
		
		
		
	}

}
