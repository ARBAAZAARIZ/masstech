package bank_operation;

import java.sql.*;
import java.util.*;

public class Bank {

	Connection con;
	Scanner s;
	
	public Bank() throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		String url="jdbc:mysql://localhost:3306/mtbank";
		String username="root";
		
		con=DriverManager.getConnection(url,username,"");
		
		s=new Scanner(System.in);
	}

	
	
	public void signUp() throws Exception {

	    System.out.println("\n\n==================================================");
	    System.out.println("              NEW USER REGISTRATION               ");
	    System.out.println("==================================================\n");

	    System.out.println("You will be registered as a new user.\n");

	    System.out.print("Enter Username             : ");
	    String username = s.nextLine();

	    System.out.print("Enter Password             : ");
	    String password = s.nextLine();

	    System.out.print("Enter Email                : ");
	    String email = s.nextLine();

	    String role = "USER"; // fixed role

	    System.out.print("Enter Account Holder Name  : ");
	    String holderName = s.nextLine();

	    System.out.print("Enter Contact Number       : ");
	    String contact = s.nextLine();

	    String query = "call signup('" + username + "', '" + password + "', '" + email + "', '" + role + "', '" + holderName + "', '" + contact + "' )";
	    PreparedStatement psmt = con.prepareStatement(query);

	    System.out.print("\nRegistering");
	    for (int i = 0; i <= 15; i++) {
	        Thread.sleep(300);
	        System.out.print(".");
	    }
	    System.out.println("\n");

	    ResultSet response = psmt.executeQuery();

	    System.out.println("--------------------------------------------------");
	    if (response.next()) {
	        int success = response.getInt("status");
	        String message = response.getString("message");

	        if (success == 1) {
	            System.out.println("STATUS  : SUCCESS");
	            System.out.println("MESSAGE : " + message);
	        } else {
	            System.out.println("STATUS  : FAILED");
	            System.out.println("MESSAGE : " + message);
	            System.out.println("ACTION  : PLEASE TRY AGAIN");
	        }
	    } else {
	        System.out.println("STATUS  : ERROR");
	        System.out.println("MESSAGE : Something went wrong. Please try again.");
	    }
	    System.out.println("--------------------------------------------------\n");
	}

	
	
	public String login() throws Exception {

	    System.out.println("\n\n==================================================");
	    System.out.println("                  LOGIN PORTAL                    ");
	    System.out.println("==================================================\n");

	    String email = "";

	    System.out.println("Login using:");
	    System.out.println("  1 - Username");
	    System.out.println("  2 - Email");
	    System.out.print("Enter choice (1 or 2): ");

	    int choice = s.nextInt();
	    s.nextLine();

	    System.out.print(choice == 1 ? "Enter Username : " : "Enter Email    : ");
	    String uname = s.nextLine();

	    System.out.print("Enter Password  : ");
	    String pass = s.nextLine();

	    String query = "call login('" + choice + "','" + uname + "','" + pass + "')";
	    PreparedStatement psmt = con.prepareStatement(query);

	    System.out.print("\nAuthenticating");
	    for (int i = 0; i < 4; i++) {
	        Thread.sleep(300);
	        System.out.print(".");
	    }
	    System.out.println("\n");

	    ResultSet response = psmt.executeQuery();

	    if (response.next()) {
	        int success = response.getInt("status");
	        String message = response.getString("message");
	        email = response.getString("email");

	        System.out.println("--------------------------------------------------");
	        System.out.println("Status : " + (success == 1 ? "SUCCESS" : "FAILED"));
	        System.out.println("Message: " + message);
	        System.out.println("--------------------------------------------------");

	        return success == 1 ? email : null;
	    } else {
	        System.out.println("--------------------------------------------------");
	        System.out.println("Error: No response from server.");
	        System.out.println("--------------------------------------------------");
	        return null;
	    }
	}

	
	
	public void createBankAccount(String email) throws Exception {

	    System.out.println("\n\n==================================================");
	    System.out.println("              CREATE NEW BANK ACCOUNT             ");
	    System.out.println("==================================================\n");

	    String[] bankNames = {"SBI", "ICICI", "HDFC", "AXIS", "PNB"};
	    String[] branchNames = {"ADH", "KRL", "BDR", "THA", "DDR"};

	    System.out.println("Choose Bank:");
	    for (int i = 0; i < bankNames.length; i++) {
	        System.out.printf("  %d - %s\n", i + 1, bankNames[i]);
	    }
	    System.out.print("Enter choice: ");
	    int bankChoice = s.nextInt() - 1;

	    System.out.println("\nChoose Branch:");
	    System.out.println("  1 - Andheri");
	    System.out.println("  2 - Kurla");
	    System.out.println("  3 - Bandra");
	    System.out.println("  4 - Thane");
	    System.out.println("  5 - Dadar");
	    System.out.print("Enter choice: ");
	    int branchChoice = s.nextInt() - 1;

	    Random rand = new Random();
	    String accountNo = bankNames[bankChoice] + rand.nextInt(10000, 100000);

	    // Fetch contact for IFSC generation
	    String conQuery = "SELECT contact FROM bankuser WHERE email = ?";
	    PreparedStatement psmtContact = con.prepareStatement(conQuery);
	    psmtContact.setString(1, email);
	    ResultSet rsContact = psmtContact.executeQuery();

	    String contact = "";
	    if (rsContact.next()) {
	        contact = rsContact.getString("contact");
	    }
	    

	    String ifsc = branchNames[branchChoice] + contact;
	    String bankName = bankNames[bankChoice];
	    String branchName = branchNames[branchChoice];

	    System.out.println("\n--------------------------------------------------");
	    System.out.println("NOTE: ₹230 will be deducted for account creation.");
	    System.out.println("Minimum required balance after deduction: ₹5000");
	    System.out.println("--------------------------------------------------");
	    System.out.print("Enter initial deposit amount: ");
	    double balance = s.nextDouble() - 230;

	    while (balance < 5000) {
	        try {
	            throw new Invalid_Initial_Balance_Exception("Balance should be greater than ₹5000 after deduction.");
	        } catch (Invalid_Initial_Balance_Exception e) {
	            System.out.println("\n[!] " + e.getMessage());
	            System.out.print("Enter initial deposit amount: ");
	            balance = s.nextDouble() - 230;
	        }
	    }

	    // Prepare procedure call
	    String query = "CALL create_bank_account_by_email(?, ?, ?, ?, ?, ?)";
	    PreparedStatement psmtCreate = con.prepareStatement(query);
	    psmtCreate.setString(1, email);
	    psmtCreate.setString(2, accountNo);
	    psmtCreate.setString(3, ifsc);
	    psmtCreate.setString(4, bankName);
	    psmtCreate.setString(5, branchName);
	    psmtCreate.setDouble(6, balance);

	    System.out.print("\nCreating account. Please wait");
	    for (int i = 0; i < 6; i++) {
	        Thread.sleep(300);
	        System.out.print(".");
	    }
	    System.out.println("\n");

	    int rowsAffected = psmtCreate.executeUpdate();
	    

	    System.out.println("--------------------------------------------------");
	    if (rowsAffected > 0) {
	        System.out.println("STATUS  : SUCCESS");
	        System.out.println("MESSAGE : Account created successfully.");
	        System.out.printf("ACCOUNT NO : %-15s\n", accountNo);
	        System.out.printf("IFSC CODE  : %-15s\n", ifsc);
	        System.out.printf("BANK       : %-15s\n", bankName);
	        System.out.printf("BRANCH     : %-15s\n", branchName);
	        System.out.printf("BALANCE    : ₹%.2f\n", balance);
	    } else {
	        System.out.println("STATUS  : FAILED");
	        System.out.println("MESSAGE : Could not create account. Please try again.");
	    }
	    System.out.println("--------------------------------------------------\n");
	}


	
	
	
	public void deposit(String email) throws Exception {
	    System.out.println("\n==================================================");
	    System.out.println("                 DEPOSIT SECTION                  ");
	    System.out.println("==================================================\n");

	    System.out.println("You have accounts with these banks:\n");

	    //  Get userid from email
	    String idQuery = "CALL get_userid_by_email('" + email + "')";
	    PreparedStatement psmtId = con.prepareStatement(idQuery);
	    ResultSet idR = psmtId.executeQuery();

	    int id = 0;
	    if (idR.next()) {
	        id = idR.getInt("userid");
	    } else {
	        System.out.println("No user found with this email.");
	        return;
	    }
	    

	    //  Get bank names for this user
	    String bankQuery = "CALL get_bankname_by_userid('" + id + "')";
	    PreparedStatement psmtBank = con.prepareStatement(bankQuery);
	    ResultSet bankR = psmtBank.executeQuery();

	    List<String> bankNames = new ArrayList<>();
	    while (bankR.next()) {
	        bankNames.add(bankR.getString("bankname"));
	    }
	    

	    if (bankNames.isEmpty()) {
	        System.out.println("No bank accounts found for this user.");
	        return;
	    }

	    for (int i = 0; i < bankNames.size(); i++) {
	        System.out.println((i + 1) + " : " + bankNames.get(i));
	    }

	    System.out.print("Choose your Bank name: ");
	    int choice = s.nextInt() - 1;
	    s.nextLine();

	    if (choice < 0 || choice >= bankNames.size()) {
	        System.out.println("Invalid bank choice.");
	        return;
	    }

	    String bankname = bankNames.get(choice);

	    //  Get account details
	    String accountDetailsQuery = "CALL get_account_details('" + bankname + "', '" + id + "')";
	    PreparedStatement psmtDetails = con.prepareStatement(accountDetailsQuery);
	    ResultSet detailsR = psmtDetails.executeQuery();

	    String accountNo = "";
	    String ifscCode = "";
	    if (detailsR.next()) {
	        accountNo = detailsR.getString("accountno");
	        ifscCode = detailsR.getString("accountifsc");
	    } else {
	        System.out.println("No account details found for selected bank.");
	        return;
	    }
	    

	    //  Get deposit amount
	    System.out.print("Enter your amount: ");
	    double amt = s.nextDouble();
	    s.nextLine();

	    //  Call deposit procedure
	    String depositQuery = "CALL deposite_money('" + accountNo + "', '" + ifscCode + "', '" + amt + "')";
	    PreparedStatement psmtDeposit = con.prepareStatement(depositQuery);
	    ResultSet rs = psmtDeposit.executeQuery();

	    System.out.print("Depositing");
	    for (int i = 0; i < 6; i++) {
	        Thread.sleep(300);
	        System.out.print(".");
	    }
	    System.out.println();

	    //  Show result
	    if (rs.next()) {
	        String message = rs.getString("message");
	        int status = rs.getInt("status");
	        double balance = rs.getDouble("updated_balance");

	        System.out.println("\n" + message);
	        if (status == 1) {
	            System.out.println("Deposit Successful");
	            System.out.println("Your Current Balance ---> " + balance);
	        } else {
	            System.out.println("Deposit Failed");
	        }
	    } else {
	        System.out.println("Deposit Failed: No response received.");
	    }

	    
	}



	
	
	public void transferFund(String email) throws Exception {
	    System.out.println("\n==================================================");
	    System.out.println("               FUND TRANSFER SECTION              ");
	    System.out.println("==================================================\n");

	    //  Getting userid from email 
	    String idQuery = "CALL get_userid_by_email('" + email + "')";
	    PreparedStatement psmtId = con.prepareStatement(idQuery);
	    ResultSet idR = psmtId.executeQuery();

	    int id = 0;
	    if (idR.next()) {
	        id = idR.getInt("userid");
	    } else {
	        System.out.println("  No user found with this email.");
	        return;
	    }
	    

	    //  Geting bank names 
	    String bankQuery = "CALL get_bankname_by_userid('" + id + "')";
	    PreparedStatement psmtBank = con.prepareStatement(bankQuery);
	    ResultSet bankR = psmtBank.executeQuery();

	    List<String> bankNames = new ArrayList<>();
	    while (bankR.next()) {
	        bankNames.add(bankR.getString("bankname"));
	    }
	    

	    if (bankNames.isEmpty()) {
	        System.out.println("  No bank accounts found for this user.");
	        return;
	    }

	    System.out.println(" Your Linked Bank Accounts:");
	    for (int i = 0; i < bankNames.size(); i++) {
	        System.out.println("  " + (i + 1) + " → " + bankNames.get(i));
	    }

	    System.out.print("\n Choose your Bank to transfer from (1-" + bankNames.size() + "): ");
	    int choice = s.nextInt() - 1;
	    s.nextLine();

	    if (choice < 0 || choice >= bankNames.size()) {
	        System.out.println(" Invalid bank choice.");
	        return;
	    }

	    String bankname = bankNames.get(choice);

	    //  Get sender account details
	    String accountDetailsQuery = "CALL get_account_details('" + bankname + "','" + id + "')";
	    PreparedStatement psmtDetails = con.prepareStatement(accountDetailsQuery);
	    ResultSet detailsR = psmtDetails.executeQuery();

	    String senderAccNo = "";
	    String senderIFSC = "";
	    if (detailsR.next()) {
	        senderAccNo = detailsR.getString("accountno");
	        senderIFSC = detailsR.getString("accountifsc");
	    } else {
	        System.out.println("  No account details found for selected bank.");
	        return;
	    }
	    

	    // Get receiver details and amount
	    System.out.print("\n Enter Receiver Account No: ");
	    String receiverAccNo = s.nextLine();

	    System.out.print(" Enter Receiver IFSC Code: ");
	    String receiverIFSC = s.nextLine();

	    System.out.print(" Enter Amount to Transfer: ");
	    double amount = s.nextDouble();
	    s.nextLine();

	    //Call transfer procedure
	    String transferQuery = "CALL transfer_fund('" + senderAccNo + "','" + senderIFSC + "','" + amount + "','" + receiverAccNo + "','" + receiverIFSC + "')";
	    PreparedStatement psmtTransfer = con.prepareStatement(transferQuery);

	    System.out.print("\n Transferring");
	    for (int i = 0; i < 6; i++) {
	        Thread.sleep(300);
	        System.out.print(".");
	    }
	    System.out.println();

	    ResultSet rs = psmtTransfer.executeQuery();

	    
	    if (rs.next()) {
	        int status = rs.getInt("status");
	        double balance = rs.getDouble("available_balance");
	        String message = rs.getString("message");

	        System.out.println("\n " + message);
	        if (status == 1) {
	            System.out.println(" Transfer Successful");
	            System.out.print(" Your Updated Balance → \n"+ balance);
	        } else {
	            
	            if (balance > 0) {
	            	
	            	try {
	            		throw new InsufficientBalance(" Your Current Balance → "+ balance);
	            	}catch(InsufficientBalance e) {
	            		System.out.println(e.getMessage());
	            	}
	            	
	                
	            }
	        }
	    } else {
	        System.out.println(" Transfer Failed: No response received.");
	    }

	    
	}




	
	public void viewBalance(String email) throws Exception {
	    System.out.println("\n==================================================");
	    System.out.println("             BALANCE INQUIRY SECTION              ");
	    System.out.println("==================================================\n");

	    //  Get userid from email
	    String idQuery = "CALL get_userid_by_email('" + email + "')";
	    PreparedStatement psmtId = con.prepareStatement(idQuery);
	    ResultSet idR = psmtId.executeQuery();

	    int id = 0;
	    if (idR.next()) {
	        id = idR.getInt("userid");
	    } else {
	        System.out.println("  No user found with this email.");
	        return;
	    }

	    // Get bank names
	    String bankQuery = "CALL get_bankname_by_userid('" + id + "')";
	    PreparedStatement psmtBank = con.prepareStatement(bankQuery);
	    ResultSet bankR = psmtBank.executeQuery();

	    List<String> bankNames = new ArrayList<>();
	    while (bankR.next()) {
	        bankNames.add(bankR.getString("bankname"));
	    }

	    if (bankNames.isEmpty()) {
	        System.out.println("  No bank accounts found for this user.");
	        return;
	    }

	    System.out.println(" Your Linked Bank Accounts:");
	    for (int i = 0; i < bankNames.size(); i++) {
	        System.out.println("  " + (i + 1) + " → " + bankNames.get(i));
	    }

	    System.out.print("\n Choose your bank (1-" + bankNames.size() + "): ");
	    int choice = s.nextInt() - 1;
	    s.nextLine();

	    if (choice < 0 || choice >= bankNames.size()) {
	        System.out.println(" Invalid bank choice.");
	        return;
	    }

	    String bankname = bankNames.get(choice);

	    //  Get account number
	    String accQuery = "CALL get_account_details('" + bankname + "','" + id + "')";
	    PreparedStatement psmtAcc = con.prepareStatement(accQuery);
	    ResultSet accR = psmtAcc.executeQuery();

	    String accNo = "";
	    if (accR.next()) {
	        accNo = accR.getString("accountno");
	    } else {
	        System.out.println("  No account found for selected bank.");
	        return;
	    }

	    // Fetch balance
	    String balanceQuery = "CALL fetch_balance('" + bankname + "','" + accNo + "')";
	    PreparedStatement psmtBal = con.prepareStatement(balanceQuery);
	    ResultSet rs = psmtBal.executeQuery();

	    System.out.print("\n Fetching Balance");
	    for (int i = 0; i < 6; i++) {
	        Thread.sleep(300);
	        System.out.print(".");
	    }
	    System.out.println();

	   
	    if (rs.next()) {
	        String message = rs.getString("message");
	        int success = rs.getInt("status");
	        double balance = rs.getDouble("balance");

	        System.out.println("\n " + message);
	        if (success == 1) {
	            System.out.print(" Available Balance → "+ balance);
	        } else {
	            System.out.println(" Failed to retrieve balance.");
	        }
	    } else {
	        System.out.println("  Something went wrong. Please try again.");
	    }
	}


	
	
	public void withdrawMoney(String email) throws Exception {
	    System.out.println("\nYou are now in Withdraw Section\n");

	    // Get userid from email
	    String idQuery = "CALL get_userid_by_email('" + email + "')";
	    PreparedStatement psmtId = con.prepareStatement(idQuery);
	    ResultSet idR = psmtId.executeQuery();

	    int id = 0;
	    if (idR.next()) {
	        id = idR.getInt("userid");
	    } else {
	        System.out.println(" No user found with this email.");
	        return;
	    }

	    // Get bank names
	    String bankQuery = "CALL get_bankname_by_userid('" + id + "')";
	    PreparedStatement psmtBank = con.prepareStatement(bankQuery);
	    ResultSet bankR = psmtBank.executeQuery();

	    List<String> bankNames = new ArrayList<>();
	    while (bankR.next()) {
	        bankNames.add(bankR.getString("bankname"));
	    }

	    if (bankNames.isEmpty()) {
	        System.out.println(" No bank accounts found for this user.");
	        return;
	    }

	    for (int i = 0; i < bankNames.size(); i++) {
	        System.out.println((i + 1) + ". " + bankNames.get(i));
	    }

	    System.out.print("Choose your bank: ");
	    int choice = s.nextInt() - 1;
	    s.nextLine();

	    if (choice < 0 || choice >= bankNames.size()) {
	        System.out.println(" Invalid bank choice.");
	        return;
	    }

	    String bankname = bankNames.get(choice);

	    // Get account number and IFSC
	    String accQuery = "CALL get_account_details('" + bankname + "','" + id + "')";
	    PreparedStatement psmtAcc = con.prepareStatement(accQuery);
	    ResultSet accR = psmtAcc.executeQuery();

	    String accNo = "";
	    String ifsc = "";
	    if (accR.next()) {
	        accNo = accR.getString("accountno");
	        ifsc = accR.getString("accountifsc");
	    } else {
	        System.out.println(" No account found for selected bank.");
	        return;
	    }

	    // Ask for withdrawal amount
	    System.out.print("Enter amount to withdraw: ");
	    double amount = s.nextDouble();
	    s.nextLine();

	    // Call withdraw procedure
	    String withdrawQuery = "CALL withdraw_money('" + accNo + "','" + ifsc + "','" + amount + "')";
	    PreparedStatement psmtWithdraw = con.prepareStatement(withdrawQuery);
	    ResultSet rs = psmtWithdraw.executeQuery();

	    System.out.print("Processing");
	    for (int i = 0; i < 6; i++) {
	        Thread.sleep(300);
	        System.out.print(".");
	    }
	    System.out.println();

	    if (rs.next()) {
	        String message = rs.getString("message");
	        int success = rs.getInt("status");
	        double balance = rs.getDouble("balance");

	        try {
	            if (success == 1) {
	                System.out.println(" " + message);
	                System.out.println("Current Balance → " + balance);
	            } else {
	                throw new Withdraw_Money_Exception(" " + message + " → " + balance);
	            }
	        } catch (Withdraw_Money_Exception e) {
	            System.out.println(e.getMessage());
	        }
	    } else {
	        System.out.println(" Something went wrong.");
	    }
	}

	
	
	
	
	
	
	
	
	
	
	public static void main(String[] args) throws Exception {
	    System.out.println("==================================================");
	    System.out.println("           WELCOME TO OUR BANKING SERVICE         ");
	    System.out.println("==================================================\n");

	    System.out.println("Please choose an option:");
	    System.out.println("--------------------------------------------------");
	    System.out.println("  New User       | Sign Up       | Press 1");
	    System.out.println("  Existing User  | Login         | Press 2");
	    System.out.println("  Exit                           | Press 3");
	    System.out.println("--------------------------------------------------");

	    Scanner sc = new Scanner(System.in);
	    Bank bankUser = new Bank();

	    String email = null;
	    int choice = sc.nextInt();

	    if (choice == 1) {
	        bankUser.signUp();
	        
	    } else if(choice == 2) {
	        email = bankUser.login();
	        System.out.println();
	        
	        if(email!=null) { System.out.println("	 WELCOM BACK " + email);}
	        else {
	        	System.out.println("      Try again!!");
	        }
	       
	    }else {
	    	System.out.println();
	    	System.out.println("         Come again!!");
	    }

	    boolean endop = true;

	    while (endop) {
	        if (email != null) {
	            System.out.println("\n==================================================");
	            System.out.println("                BANKING OPERATIONS                ");
	            System.out.println("==================================================");
	            System.out.println("  1 - Create New Bank Account");
	            System.out.println("  2 - Deposit Money");
	            System.out.println("  3 - Transfer Funds");
	            System.out.println("  4 - View Balance");
	            System.out.println("  5 - Withdraw Money");
	            System.out.print("\nEnter your choice: ");
	            choice = sc.nextInt();

	            if (choice == 1) {
	                bankUser.createBankAccount(email);
	                System.out.println("\n--------------------------------------------------");
	                System.out.println("Repeat Operation → Press 1");
	                System.out.println("End Operation    → Press 2");
	                System.out.println("--------------------------------------------------");
	                choice = sc.nextInt();
	                if (choice != 1) {
	                    endop = false;
	                    System.out.println("\nThank you for choosing our service.");
	                }
	            } else if (choice == 2) {
	                bankUser.deposit(email);
	                System.out.println("\n--------------------------------------------------");
	                System.out.println("Repeat Operation → Press 1");
	                System.out.println("End Operation    → Press 2");
	                System.out.println("--------------------------------------------------");
	                choice = sc.nextInt();
	                sc.nextLine();
	                if (choice != 1) {
	                    endop = false;
	                    System.out.println("\nThank you for choosing our service.");
	                }
	            } else if (choice == 3) {
	                bankUser.transferFund(email);
	                System.out.println("\n--------------------------------------------------");
	                System.out.println("Repeat Operation → Press 1");
	                System.out.println("End Operation    → Press 2");
	                System.out.println("--------------------------------------------------");
	                choice = sc.nextInt();
	                if (choice != 1) {
	                    endop = false;
	                    System.out.println("\nThank you for choosing our service.");
	                }
	            } else if (choice == 4) {
	                bankUser.viewBalance(email);
	                System.out.println("\n--------------------------------------------------");
	                System.out.println("Repeat Operation → Press 1");
	                System.out.println("End Operation    → Press 2");
	                System.out.println("--------------------------------------------------");
	                choice = sc.nextInt();
	                if (choice != 1) {
	                    endop = false;
	                    System.out.println("\nThank you for choosing our service.");
	                }
	            } else if (choice == 5) {
	                bankUser.withdrawMoney(email);
	                System.out.println("\n--------------------------------------------------");
	                System.out.println("Repeat Operation → Press 1");
	                System.out.println("End Operation    → Press 2");
	                System.out.println("--------------------------------------------------");
	                choice = sc.nextInt();
	                sc.nextLine();
	                if (choice != 1) {
	                    endop = false;
	                    System.out.println("\nThank you for choosing our service.");
	                }
	            } else {
	                System.out.println("\nInvalid input. Please try again.");
	            }
	        } else {
	            endop = false;
	        }
	    }
	}


}
