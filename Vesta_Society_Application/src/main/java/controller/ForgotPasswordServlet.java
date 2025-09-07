package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.ForgotPasswordService;

import java.io.IOException;


@WebServlet("/ForgotPasswordServlet")
public class ForgotPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ForgotPasswordServlet() {
        super();
        
    }

	
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/views/forgotPasswordInputView.jsp").forward(request, response);
    }

	
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("sendOtp".equals(action)) {
            String identifier = request.getParameter("identifier");

            ForgotPasswordService service = new ForgotPasswordService();
            String resolvedEmail = service.getEmailForOtp(identifier);

            if (resolvedEmail == null) {
                request.setAttribute("error", "No account found with that email or username.");
                request.getRequestDispatcher("WEB-INF/views/forgotPasswordInputView.jsp").forward(request, response);
                return;
            }

            int otp = (int)(Math.random() * 900000) + 100000;

            HttpSession session = request.getSession();
            session.setAttribute("otp", otp);
            session.setAttribute("email", resolvedEmail);

            util.EmailUtil.sendOtpToEmail(resolvedEmail, otp);

//            response.sendRedirect("WEB-INF/views/forgotPasswordOtpView.jsp");
            request.getRequestDispatcher("WEB-INF/views/forgotPasswordOtpView.jsp").forward(request, response);
            
            return;
        }

        if ("verifyOtp".equals(action)) {
            String enteredOtp = request.getParameter("otp");
            HttpSession session = request.getSession();
            Object sessionOtpObj = session.getAttribute("otp");

            if (sessionOtpObj == null || enteredOtp == null) {
                request.setAttribute("error", "Session expired or invalid request.");
                request.getRequestDispatcher("WEB-INF/views/forgotPasswordOtpView.jsp").forward(request, response);
                return;
            }

            String sessionOtp = String.valueOf(sessionOtpObj);

            if (enteredOtp.trim().equals(sessionOtp)) {
//                response.sendRedirect("WEB-INF/views/forgotPasswordChangeView.jsp");
            	request.getRequestDispatcher("WEB-INF/views/forgotPasswordChangeView.jsp").forward(request, response);
            } else {
                request.setAttribute("error", "Invalid OTP. Please try again.");
                request.getRequestDispatcher("WEB-INF/views/forgotPasswordOtpView.jsp").forward(request, response);
            }

            return;
        }
        
        if ("changePassword".equals(action)) {
            String newPassword = request.getParameter("newPassword");
            String confirmPassword = request.getParameter("confirmPassword");

            if (newPassword == null || confirmPassword == null || !newPassword.equals(confirmPassword)) {
                request.setAttribute("error", "Passwords do not match.");
                request.getRequestDispatcher("WEB-INF/views/forgotPasswordChangeView.jsp").forward(request, response);
                return;
            }

            HttpSession session = request.getSession();
            String email = (String) session.getAttribute("email");

            if (email == null) {
                response.sendRedirect("login.jsp");
                return;
            }

            ForgotPasswordService service = new ForgotPasswordService();
            boolean updated = service.updatePasswordByEmail(email, newPassword);

            if (updated) {
                session.removeAttribute("otp");
                session.removeAttribute("email");
                request.setAttribute("successChangeMessage", " Your password has been changed");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            } else {
                request.setAttribute("error", "Failed to update password. Please try again.");
                request.getRequestDispatcher("WEB-INF/views/forgotPasswordChangeView.jsp").forward(request, response);
            }
        }


        // fallback: unknown action
        response.sendRedirect("login.jsp");
    }




}
