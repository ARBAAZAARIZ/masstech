package controller;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;


@WebFilter("/*")
public class AuthFilter extends HttpFilter implements Filter {
       
    
    public AuthFilter() {
        super();
        
    }

    
	public void destroy() {
		
	}

	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        
        String path = req.getRequestURI();
        HttpSession session = req.getSession(false);
        
        boolean isLoggedIn = (session != null && session.getAttribute("authUser") != null);
        boolean isLoginPage = path.endsWith("login.jsp") || path.contains("loginServlet");
        
     // ✅ Allow static resources
        boolean isStaticResource = path.contains("/images/") || path.contains("/css/") || path.contains("/js/");
        
        boolean isForgotFlow = path.contains("ForgotPasswordServlet") || path.contains("forgotPasswordOtpView.jsp") || path.contains("forgotPasswordChangeView.jsp");

        
        if (isLoggedIn || isLoginPage || isStaticResource || isForgotFlow) {
            chain.doFilter(request, response);
        } else {
            res.sendRedirect("login.jsp");
        }

        
		
	}

	
	public void init(FilterConfig fConfig) throws ServletException {
		
	}
	
	

}
