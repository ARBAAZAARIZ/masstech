package paymentFolder;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

/**
 * Servlet implementation class PaymentServlet
 */
@WebServlet("/GenerateUPIQrServlet")
public class GenerateUPIQrServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GenerateUPIQrServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String upiId = request.getParameter("upiId");
        String amount = request.getParameter("amount");
        String name = "Society Admin"; 
        
        String upiUri = "upi://pay?pa=" + upiId + "&pn=" + name + "&am=" + amount + "&cu=INR";
        
        try {
        	BitMatrix matrix=new MultiFormatWriter().encode(upiUri, BarcodeFormat.QR_CODE, 300, 300); 
        	BufferedImage qrImage = MatrixToImageWriter.toBufferedImage(matrix);
        	
        	response.setContentType("image/png");
        	  OutputStream out = response.getOutputStream();
        	  ImageIO.write(qrImage, "png", out);
        	  out.close();
        	  
        	
        }catch(Exception e) {
        	System.out.println(e.getMessage());
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
