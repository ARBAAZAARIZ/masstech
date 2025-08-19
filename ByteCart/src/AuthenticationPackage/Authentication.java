package AuthenticationPackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;



public class Authentication {
	
	Connection conn;
	Scanner sc;
	
	public Authentication() throws Exception {
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		String url="jdbc:mysql://localhost:3306/byte_cart";
		String username="root";
		
		conn=DriverManager.getConnection(url,username,"");
		
		sc=new Scanner(System.in);
		
	}
	
	
	public void signUp() throws Exception {
		
		System.out.println("\n\n==================================================");
	    System.out.println("              NEW USER REGISTRATION               ");
	    System.out.println("==================================================\n");
		
		System.out.print("Enter Full Name: ");
	    String fullname = sc.nextLine();

	    System.out.print("Enter Username: ");
	    String username = sc.nextLine();

	    System.out.print("Enter Password: ");
	    String password = sc.nextLine();

	    System.out.print("Enter Email: ");
	    String email = sc.nextLine();

	    String role = "USER"; 
	    
	    System.out.println("Enter your Address");
	    String address=sc.nextLine();
	    
	    String signUPQuery="call signup('"+fullname+"','"+username+"','"+password+"','"+email+"','"+role+"','"+address+"')";
	    
	    PreparedStatement psmt=conn.prepareStatement(signUPQuery);
	    
	    ResultSet rs=psmt.executeQuery();
	    
	    System.out.print("Regestring");
	    for(int i=0;i<=5;i++) {
	    	Thread.sleep(200);
	    	System.out.print(".");
	    }
	    
	    if (rs.next()) {
            String msg = rs.getString("msg");
            int status = rs.getInt("status");

            if(status==1) {System.out.println(msg);}else {System.out.println(msg);}
           
           
	    }
		
	}
	
	
	
	public UserDetails login() throws Exception{
		
		 System.out.println("\n\n==================================================");
		 System.out.println("                  LOGIN PORTAL                    ");
		 System.out.println("==================================================\n");
		
		System.out.print("Enter Email or Username: ");
	    String loginId = sc.nextLine();
	    
	    System.out.print("Enter Password: ");
	    String password = sc.nextLine();
	    
	    UserDetails user = new UserDetails();
	    
	    String loginQuery = "call login('" + loginId + "', '" + password + "')";
	    
	    PreparedStatement psmt=conn.prepareStatement(loginQuery);
	    
	    ResultSet rs=psmt.executeQuery();
	    

	    System.out.print("Logging.");
	    for(int i=0;i<=5;i++) {
	    	Thread.sleep(200);
	    	System.out.print(".");
	    }
	    
	    if(rs.next()) {
	    	int status = rs.getInt("status");
	    	
	    	if(status==1) {
	    		user.authenticated = true;
	            user.id = rs.getInt("user_id");
	            user.username = rs.getString("username");
	            user.email = rs.getString("email");
	            user.address=rs.getString("address");
	            user.role = rs.getString("role");
	            
	            System.out.println(" Login Successful. Welcome, " + user.username + "!");
	    	} else {
                user.authenticated = false;
                System.out.println(" Invalid credentials. Please try again.");
            }
	    }
	    
	    return user;
		
	}
	
	public void updateAccount(UserDetails user) throws Exception {
		System.out.println("Your Accounts Details");
		System.out.println(user.username);
		System.out.println(user.email);
		
		System.out.println("Please choose an option:");
		System.out.println("Update password         |  press1");
		System.out.println("Change Email            |  press2");
		
		
		int choice=sc.nextInt();
		sc.nextLine();
		
		if(choice==1) {
			System.out.println("Enter existing password");
			String existingPassword=sc.nextLine();
			
			System.out.println("Enter new password");
			String newPassword=sc.nextLine();
			
			String changePassQuery="call change_password('"+existingPassword+"','"+newPassword+"','"+user.id+"')";
			
			PreparedStatement psmt=conn.prepareStatement(changePassQuery);
			
			ResultSet rs= psmt.executeQuery();
			
			if(rs.next()) {
				int status=rs.getInt("status");
				String msg=rs.getString("msg");
				
				if(status==1) {
					System.out.println(msg);
				}else {
					System.out.println(msg);
					System.out.println("Try again!!");
				}
				
			}
			
			
		}else if(choice==2) {
			System.out.println("Enter new Email");
			String newEmail=sc.nextLine();
			
			String changeEmailQuery="update users set email='"+newEmail+"' where user_id = '"+user.id+"'";
			
			PreparedStatement psmt=conn.prepareStatement(changeEmailQuery);
			
			int success=psmt.executeUpdate();
			
			if(success>=1) {
				System.out.println("Email changed Successfully");
				user.email=newEmail;
			}else {
				System.out.println("Something went wrong or duplicate Email");
				System.out.println("Try again!!");
			}
			
		}else {
			System.out.println("Wrong Input");
		}
		
	}
	
	 

	

}
