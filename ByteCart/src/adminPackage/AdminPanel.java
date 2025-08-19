package adminPackage;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import AuthenticationPackage.Authentication;
import AuthenticationPackage.UserDetails;

public class AdminPanel {
	
	Connection conn;
	public AdminPanel() throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		String url="jdbc:mysql://localhost:3306/byte_cart";
		String username="root";
		
		conn=DriverManager.getConnection(url,username,"");
	}
	
	
	static boolean status=true;
	static Scanner sc=new Scanner(System.in);
	static int choice;
	static Authentication auth;
	
	
	public boolean adminInterface(UserDetails user) throws Exception{
		
		while(status) {
			System.out.println("Please choose an option:");
			System.out.println("--------------------------------------------------");
		    System.out.println("  View Products                  | Press 1");
		    System.out.println("  Add Product                    | Press 2");
		    System.out.println("  Update Product        		 | Press 3");
		    System.out.println("  delete Product                 | Press 4");
		    System.out.println("  Purchase History               | Press 4");
		    System.out.println("--------------------------------------------------");
		    
		    choice=sc.nextInt();
		    sc.nextLine();
		    
		    if(choice==1) {
		    	viewProducts();
		    	System.out.println("Do you want to go main menu   | press 1");
		    	System.out.println("Want to exit                  | press 2");
		    	choice=sc.nextInt();
		    	
		    	if(choice!=1) {
		    		status=false;
		    	}
		    }else if(choice==2) {
		    	addProduct();
		    	System.out.println("Do you want to go main menu   | press 1");
		    	System.out.println("Want to exit                  | press 2");
		    	choice=sc.nextInt();
		    	
		    	if(choice!=1) {
		    		status=false;
		    	}
		    	
		    }else if(choice==3) {
		    	updateProduct();
		    	System.out.println("Do you want to go main menu   | press 1");
		    	System.out.println("Want to exit                  | press 2");
		    	choice=sc.nextInt();
		    	
		    	if(choice!=1) {
		    		status=false;
		    	}
		    }else if(choice == 4) {
		    	
		    	deleteProduct();
		    	System.out.println("Do you want to go main menu   | press 1");
		    	System.out.println("Want to exit                  | press 2");
		    	choice=sc.nextInt();
		    	
		    	if(choice!=1) {
		    		status=false;
		    	}
		    }else if(choice == 5) {
		    	
		    	purchaseHistoryByCustomer();
		    	System.out.println("Do you want to go main menu   | press 1");
		    	System.out.println("Want to exit                  | press 2");
		    	choice=sc.nextInt();
		    	
		    	if(choice!=1) {
		    		status=false;
		    	}
		    }else {
		    	System.out.println("Wrong input!!");
		    }
		    
		    
		    
		}
		
		
		return true;
	}
	
	
	public void viewProducts() throws Exception {
	    System.out.println("\n===============================================");
	    System.out.println("               PRODUCT CATALOG                 ");
	    System.out.println("===============================================\n");

	    String viewProductQuery = "SELECT * FROM products";
	    PreparedStatement psmt = conn.prepareStatement(viewProductQuery);
	    ResultSet rs = psmt.executeQuery();

	    System.out.println("---------------------------------------------------------------------------------------------------------------------------------");
	    System.out.println(" ID   | Product Name                                      | Description                                  | Price     | Stock     ");
	    System.out.println("---------------------------------------------------------------------------------------------------------------------------------");

	    while (rs.next()) {
	        int id = rs.getInt("product_id"); 
	        String productName = rs.getString("product_name");
	        String p_description = rs.getString("product_description");
	        double price = rs.getDouble("price");
	        int stock = rs.getInt("stock");

	        System.out.println(" " + id + "     | " + productName + "                 " + p_description + "             " + price + "       " + stock);
	    }

	    System.out.println("---------------------------------------------------------------------------------------------\n");
	}

	
	
	
	
	
	public void addProduct() throws Exception {
	    System.out.println("\n========================================");
	    System.out.println("           ADD NEW PRODUCT              ");
	    System.out.println("========================================");

	    System.out.print("Enter Product Name     : ");
	    String pName = sc.nextLine();

	    System.out.print("Enter Description       : ");
	    String pDescription = sc.nextLine();

	    System.out.print("Enter Price             : ");
	    double price = sc.nextDouble();
	    sc.nextLine();

	    System.out.print("Enter Stock Quantity    : ");
	    int stocks = sc.nextInt();

	    String addProdutQuery = "call add_product('" + pName + "','" + pDescription + "','" + price + "','" + stocks + "')";
	    PreparedStatement psmt = conn.prepareStatement(addProdutQuery);
	    ResultSet rs = psmt.executeQuery();

	    System.out.println("\n----------------------------------------");
	    if (rs.next()) {
	        String msg = rs.getString("message");
	        int status = rs.getInt("status");
	        if (status == 1) {
	            System.out.println("Status : Success");
	            System.out.println("Message: " + msg);
	        } else {
	            System.out.println("Status : Failed");
	            System.out.println("Message: Something went wrong, try again.");
	        }
	    } else {
	        System.out.println("Status : Failed");
	        System.out.println("Message: No response from server.");
	    }
	    System.out.println("----------------------------------------\n");
	}

	
	public void updateProduct() throws Exception {
	    System.out.println("\n========================================");
	    System.out.println("           UPDATE PRODUCT INFO          ");
	    System.out.println("========================================");

	    System.out.print("Enter Product ID        : ");
	    int id = sc.nextInt();

	    System.out.print("Enter New Price         : ");
	    double price = sc.nextDouble();
	    sc.nextLine();

	    System.out.print("Enter Updated Stock Qty : ");
	    int stocks = sc.nextInt();

	    String updateProductQuery = "call update_product('" + id + "','" + price + "','" + stocks + "') ";
	    PreparedStatement psmt = conn.prepareStatement(updateProductQuery);
	    ResultSet rs = psmt.executeQuery();

	    System.out.println("\n----------------------------------------");
	    if (rs.next()) {
	        int status = rs.getInt("status");
	        String msg = rs.getString("msg");

	        if (status == 1) {
	            System.out.println("Status : Success");
	        } else {
	            System.out.println("Status : Failed");
	        }
	        System.out.println("Message: " + msg);
	    } else {
	        System.out.println("Status : Failed");
	        System.out.println("Message: No response from server.");
	    }
	    System.out.println("----------------------------------------\n");
	}

	
	public void deleteProduct() throws Exception {
	    System.out.println("\n========================================");
	    System.out.println("           DELETE PRODUCT               ");
	    System.out.println("========================================");

	    System.out.print("Enter Product ID to delete : ");
	    int id = sc.nextInt();

	    String deleteProductQuery = "delete from products where product_id = '" + id + "'";
	    PreparedStatement psmt = conn.prepareStatement(deleteProductQuery);

	    int success = psmt.executeUpdate();

	    System.out.println("\n----------------------------------------");
	    if (success >= 1) {
	        System.out.println("Status : Success");
	        System.out.println("Message: Product successfully deleted.");
	    } else {
	        System.out.println("Status : Failed");
	        System.out.println("Message: Invalid Product ID. Please try again.");
	    }
	    System.out.println("----------------------------------------\n");
	}
	
	public void purchaseHistoryByCustomer() throws Exception {
	    System.out.println("\n========================================");
	    System.out.println("     HISTORY OF PURCHASED PRODUCTS      ");
	    System.out.println("========================================");

	    String historyQuery = "select * from product_purchased";
	    PreparedStatement psmt = conn.prepareStatement(historyQuery);
	    ResultSet rs = psmt.executeQuery();

	    boolean hasData = false;

	    while (rs.next()) {
	        hasData = true;

	        int purchaseId = rs.getInt("pro_pur_id");
	        int productId = rs.getInt("product_id");
	        int userId = rs.getInt("user_id");
	        String productName = rs.getString("product_name");
	        Date orderedDate = rs.getDate("ordered_date");
	        int quantity = rs.getInt("quantity");
	        Date deliverDate = rs.getDate("deliver_date");
	        double productPrice = rs.getDouble("product_price");
	        double totalPrice = rs.getDouble("total_price");

	        System.out.println("----------------------------------------");
	        System.out.println("Purchase ID     : " + purchaseId);
	        System.out.println("Product ID      : " + productId);
	        System.out.println("User ID         : " + userId);
	        System.out.println("Product Name    : " + productName);
	        System.out.println("Ordered Date    : " + orderedDate);
	        System.out.println("Quantity        : " + quantity);
	        System.out.println("Delivery Date   : " + deliverDate);
	        System.out.println("Product Price   : " + productPrice);
	        System.out.println("Total Price     : " + totalPrice);
	    }

	    if (!hasData) {
	        System.out.println("\nNo purchase history found.");
	    } else {
	        System.out.println("----------------------------------------");
	        System.out.println("End of purchase history.");
	    }

	    System.out.println("========================================\n");
	}


	
	
	
	
	

}
