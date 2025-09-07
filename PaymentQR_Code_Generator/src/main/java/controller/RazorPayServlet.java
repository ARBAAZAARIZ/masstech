package controller;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.json.JSONObject;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;

/**
 * Servlet implementation class RazorPayServlet
 */
@WebServlet("/RazorPayServlet")
public class RazorPayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RazorPayServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("razorPay.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		double amt=Double.parseDouble(request.getParameter("amount"));
		System.out.println(amt);
		String key = "rzp_test_Kl7588Yie2yJTV";
		String secret = "6dN9Nqs7M6HPFMlL45AhaTgp";
		
		try {
			RazorpayClient client = new RazorpayClient(key, secret);
			 // Create Order Request
			JSONObject orderRequest = new JSONObject();
            orderRequest.put("amount", amt*100); 
            orderRequest.put("currency", "INR");
            orderRequest.put("receipt", "txn_123456");
            orderRequest.put("payment_capture", 1);
            
         // Create order
            Order order = client.orders.create(orderRequest);
            
            request.setAttribute("orderId", order.get("id"));
            request.setAttribute("amount", order.get("amount"));
            request.setAttribute("key", key);
            
            request.getRequestDispatcher("generateOrder.jsp").forward(request, response);



		}catch(Exception e) {
			
		}
		



		
	}

}
