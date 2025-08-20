package userPackage;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import AuthenticationPackage.Authentication;
import AuthenticationPackage.UserDetails;
import exceptionsPackage.InsufficientBalanceException;

import productPackage.Product;

public class UserPanel {
	Connection conn;
	static boolean status=true;
	static Scanner sc=new Scanner(System.in);
	static int choice;
	static List<Product> products = new ArrayList<>();
	static int cartCount=0;
	
	

	
	
	public UserPanel() throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		String url="jdbc:mysql://localhost:3306/byte_cart";
		String username="root";
		
		conn=DriverManager.getConnection(url,username,"");
	}
	
	
	public boolean userInterface(UserDetails user) throws Exception {
		
		status=true;

	    while (status) {
	        System.out.println("\n========================================");
	        System.out.println("              MAIN MENU                 ");
	        System.out.println("========================================");
	        System.out.println("Please choose an option:");
	        System.out.println("----------------------------------------");
	        System.out.println("  1. View Products");
	        System.out.println("  2. View Cart");
	        System.out.println("  3. View Purchased Products");
	        System.out.println("  4. Update Account");
	        System.out.println("  5. Create New Bank Account");
	        System.out.println("  6. Deposit Money");
	        System.out.println("  7. Logout");
	        System.out.println("----------------------------------------");
	        System.out.print("Your choice: ");

	        choice = sc.nextInt();

	        if (choice == 1) {
	            viewProducts();

	            System.out.println("\n----------------------------------------");
	            System.out.println("  1. Add Product to Cart and Place Order");
	            System.out.println("  2. Exit");
	            System.out.println("----------------------------------------");
	            System.out.print("Your choice: ");
	            choice = sc.nextInt();

	            if (choice == 1) {
	                boolean success = addProductToCart(user);
	                if (success) {
	                    viewCart(user);
	                  boolean orderplaced=  placingOrder(user);
	                  System.out.println();
	                  
	                  if(orderplaced) {
	                	  purchaseProductHistory(user);
	                  }
	                }
	            }else {
	            	status=false;
	            }
	           
	            
	            
	        } else if (choice == 2) {
	            viewCart(user);

	            if (cartCount > 0) {
	               boolean orderedPurchased= placingOrder(user);
	               if(orderedPurchased) {
	            	   purchaseProductHistory(user);
	               }
	            }

	        } else if (choice == 3) {
	            purchaseProductHistory(user);

	            System.out.println("\n----------------------------------------");
	            System.out.println("  1. Return to Main Menu");
	            System.out.println("  2. Exit");
	            System.out.println("----------------------------------------");
	            System.out.print("Your choice: ");
	            choice = sc.nextInt();

	            if (choice != 1) {
	                status = false;
	            }

	        } else if (choice == 4) {
	            Authentication auth = new Authentication();
	            auth.updateAccount(user);

	            System.out.println("\n----------------------------------------");
	            System.out.println("  1. Return to Main Menu");
	            System.out.println("  2. Exit");
	            System.out.println("----------------------------------------");
	            System.out.print("Your choice: ");
	            choice = sc.nextInt();

	            if (choice != 1) {
	                status = false;
	            }

	        } else if (choice == 5) {
	            insertBankDetails(user);

	            System.out.println("\n----------------------------------------");
	            System.out.println("  1. Return to Main Menu");
	            System.out.println("  2. Exit");
	            System.out.println("----------------------------------------");
	            System.out.print("Your choice: ");
	            choice = sc.nextInt();

	            if (choice != 1) {
	                status = false;
	            }

	        } else if (choice == 6) {
	            depositMoney(user);

	            System.out.println("\n----------------------------------------");
	            System.out.println("  1. Return to Main Menu");
	            System.out.println("  2. Exit");
	            System.out.println("----------------------------------------");
	            System.out.print("Your choice: ");
	            choice = sc.nextInt();

	            if (choice != 1) {
	                status = false;
	            }

	        } else {
	            status = false;
	        }
	    }

	    return true;
	}

	
	
	
	
	public void viewProducts() throws Exception {

	    String viewProductQuery = "SELECT * FROM products";
	    PreparedStatement psmt = conn.prepareStatement(viewProductQuery);
	    ResultSet rs = psmt.executeQuery();
	    products.clear();

	    int count = 0;
	    while (rs.next()) {
	        int id = rs.getInt("product_id");
	        String p_name = rs.getString("product_name");
	        String description = rs.getString("product_description");
	        double price = rs.getDouble("price");
	        int stocks = rs.getInt("stock");

	        Product product = new Product(id, p_name, description, price, stocks);
	        products.add(product);

	        
	        count++;
	    }

	    if (count == 0) {
	        System.out.println("No products found.");
	    } else {
	        
	        
	    }

	    

	    showProducts(); 
	}

	
	
	
	
	public boolean addProductToCart(UserDetails user) throws Exception {
	    System.out.println("\n========================================");
	    System.out.println("           ADD PRODUCT TO CART          ");
	    System.out.println("========================================");

	    System.out.print("Enter Product ID: ");
	    int proId = sc.nextInt();

	    System.out.print("Enter Quantity   : ");
	    int qunt = sc.nextInt();

	    
	    String stockQuery = "SELECT stock FROM products WHERE product_id = '" + proId + "'";
	    PreparedStatement stockPsmt = conn.prepareStatement(stockQuery);
	    ResultSet stockRs = stockPsmt.executeQuery();

	    if (stockRs.next()) {
	        int availableStock = stockRs.getInt("stock");

	        if (availableStock == 0) {
	            System.out.println("\n----------------------------------------");
	            System.out.println("Product is currently out of stock.");
	            System.out.println("----------------------------------------\n");
	            return false;
	        }

	        if (qunt > availableStock) {
	            System.out.println("\n----------------------------------------");
	            System.out.println("Only " + availableStock + " units are available.");
	            System.out.print("Do you want to proceed with available quantity? (y/n): ");
	            sc.nextLine(); 
	            String confirm = sc.nextLine();

	            if (!confirm.equalsIgnoreCase("y")) {
	                System.out.println("Operation cancelled by user.");
	                System.out.println("----------------------------------------\n");
	                return false;
	            }

	            
	            qunt = availableStock;
	        }

	        
	        String addProductIntoCart = "call add_product_into_cart('" + user.id + "','" + proId + "','" + qunt + "')";
	        PreparedStatement psmt = conn.prepareStatement(addProductIntoCart);
	        ResultSet rs = psmt.executeQuery();

	        System.out.println("\n----------------------------------------");
	        if (rs.next()) {
	            System.out.println(rs.getString("msg"));
	            System.out.println("----------------------------------------\n");
	            return rs.getInt("status") == 1;
	        } else {
	            System.out.println("Something went wrong. Please try again.");
	            System.out.println("----------------------------------------\n");
	            return false;
	        }

	    } else {
	        System.out.println("\n----------------------------------------");
	        System.out.println("Invalid Product ID. No such product found.");
	        System.out.println("----------------------------------------\n");
	        return false;
	    }
	}


	
	
	public void viewCart(UserDetails user) throws Exception {
	    System.out.println("\n========================================");
	    System.out.println("              VIEW CART DETAILS         ");
	    System.out.println("========================================");

	    String countCart = "select count(*) as cnt from cart where user_id='" + user.id + "'";
	    PreparedStatement countpsmt = conn.prepareStatement(countCart);
	    ResultSet countRs = countpsmt.executeQuery();

	     
	    if (countRs.next()) {
	        cartCount = countRs.getInt("cnt");
	    }

	    if (cartCount > 0) {
	        System.out.println("\nItems currently in your cart:\n");

	        String viewCartQuery = "select * from cart where user_id ='" + user.id + "'";
	        PreparedStatement psmt = conn.prepareStatement(viewCartQuery);
	        ResultSet rs = psmt.executeQuery();

	        System.out.println("--------------------------------------------------");
	        while (rs.next()) {
	            int cartId = rs.getInt("cart_id");
	            int productId = rs.getInt("product_id");
	            String productName = rs.getString("product_name");
	            int quantity = rs.getInt("quantity");
	            Date addedAt = rs.getDate("added_at");
	            double productPrice = rs.getDouble("product_price");
	            double totalPrice = rs.getDouble("total_price");

	            System.out.println("Cart ID        : " + cartId);
	            System.out.println("Product ID     : " + productId);
	            System.out.println("Product Name   : " + productName);
	            System.out.println("Quantity       : " + quantity);
	            System.out.println("Added At       : " + addedAt);
	            System.out.println("Product Price  : " + productPrice);
	            System.out.println("Total Price    : " + totalPrice);
	            System.out.println("--------------------------------------------------");
	        }
	    } else {
	        System.out.println("\nYour cart is currently empty.");
	        System.out.println();
	        System.out.println("Returning to  menu \n");
	    }
	}

	
	
	
	public boolean placingOrder(UserDetails user) throws Exception {
	    System.out.println("\n========================================");
	    System.out.println("           PLACE YOUR ORDER             ");
	    System.out.println("========================================");
	    

	    int cnt = 0;
	    String cntBankQuery = "select count(*) as cnt from bank where user_id='" + user.id + "'";
	    PreparedStatement psmtCnt = conn.prepareStatement(cntBankQuery);
	    ResultSet cntRs = psmtCnt.executeQuery();

	    if (cntRs.next()) {
	        cnt = cntRs.getInt("cnt");
	    }

	    if (cnt > 0) {
	    	
	    	System.out.println();
		    System.out.println("1. Place items currently in cart");
		    System.out.println();
		    System.out.println("2. Add more products to cart");
		    System.out.print("Enter your choice: ");
		    choice = sc.nextInt();
	    	
	        List<String> bankNames = new ArrayList<>();

	        if (choice == 1) {
	            System.out.println("\n----------------------------------------");
	            System.out.println("           SELECT BANK TO PAY           ");
	            System.out.println("----------------------------------------");

	            String bankNameQuery = "select bank_name from bank where user_id='" + user.id + "'";
	            PreparedStatement psmt = conn.prepareStatement(bankNameQuery);
	            ResultSet rs = psmt.executeQuery();

	            while (rs.next()) {
	                bankNames.add(rs.getString("bank_name"));
	            }

	            for (int i = 0; i < bankNames.size(); i++) {
	                System.out.println((i + 1) + ". " + bankNames.get(i));
	            }

	            System.out.print("Choose bank (1 - " + bankNames.size() + "): ");
	            choice = sc.nextInt() - 1;
	            String bankName = bankNames.get(choice);

	            double balance = 0;
	            String balanceQuery = "select balance from bank where user_id = '" + user.id + "' and bank_name='" + bankName + "'";
	            psmt = conn.prepareStatement(balanceQuery);
	            rs = psmt.executeQuery();

	            if (rs.next()) {
	                balance = rs.getDouble("balance");
	            }

	            double totalAmountToBePaid = 0;
	            String totalAmountQuery = "select sum(total_price) as total_amount from cart where user_id='" + user.id + "'";
	            psmt = conn.prepareStatement(totalAmountQuery);
	            rs = psmt.executeQuery();

	            if (rs.next()) {
	                totalAmountToBePaid = rs.getDouble("total_amount");
	            }

	            if (balance >= totalAmountToBePaid) {
	                String cartListQuery = "select * from cart where user_id='" + user.id + "'";
	                psmt = conn.prepareStatement(cartListQuery);
	                rs = psmt.executeQuery();

	                System.out.println("\n----------------------------------------");
	                System.out.println("         PROCESSING YOUR ORDER          ");
	                System.out.println("----------------------------------------");

	                while (rs.next()) {
	                    int cartId = rs.getInt("cart_id");
	                    int productId = rs.getInt("product_id");
	                    String productName = rs.getString("product_name");
	                    int quantity = rs.getInt("quantity");
	                    double productPrice = rs.getDouble("product_price");
	                    double totalPrice = rs.getDouble("total_price");

	                    String purchasingProductQuery = "call placeing_order('" + cartId + "','" + user.id + "','" + productId + "','" + productName + "','" + quantity + "','" + productPrice + "','" + totalPrice + "','" + bankName + "')";
	                    PreparedStatement psmtPurchase = conn.prepareStatement(purchasingProductQuery);
	                    ResultSet rsPurchase = psmtPurchase.executeQuery();

	                    if (rsPurchase.next()) {
	                        Thread.sleep(200);
	                        int status = rsPurchase.getInt("status");
	                        if (status == 1) {
	                            System.out.println(" " + productName + " purchased successfully.");
	                        } else {
	                            System.out.println(" " + rsPurchase.getString("msg"));
	                        }
	                    }
	                }

	                System.out.println("----------------------------------------");
	                System.out.println("         ORDER COMPLETED SUCCESSFULLY   ");
	                System.out.println("----------------------------------------\n");
	                
	                return true;

	            } else {
	                try {
	                    throw new InsufficientBalanceException("Insufficient Balance Available\nCurrent balance: " + balance + "\nAmount to be paid: " + totalAmountToBePaid);
	                } catch (InsufficientBalanceException e) {
	                    System.out.println("\n----------------------------------------");
	                    System.out.println("           PAYMENT FAILED               ");
	                    System.out.println("----------------------------------------");
	                    System.out.println(e.getMessage());
	                    System.out.println("----------------------------------------\n");
	                }
	                return false;
	            }
	        }else {
	        	return false;
	        }
	        
	        
	    }
	    else {
	        System.out.println("\n----------------------------------------");
	        System.out.println("   No bank account linked for payment   ");
	        System.out.println("   Please return to main menu to add    ");
	        System.out.println("         a new bank account.            ");
	        System.out.println("----------------------------------------\n");
	        return false;
	    }
	}

	
	
	
	
	
	
	
	public void purchaseProductHistory(UserDetails user) throws Exception {
	    System.out.println("\n========================================");
	    System.out.println("         PURCHASE HISTORY SECTION       ");
	    System.out.println("========================================");

	    String countCart = "select count(*) as cnt from product_purchased where user_id='" + user.id + "'";
	    PreparedStatement countpsmt = conn.prepareStatement(countCart);
	    ResultSet countRs = countpsmt.executeQuery();

	    int count = 0;
	    if (countRs.next()) {
	        count = countRs.getInt("cnt");
	    }

	    if (count > 0) {
	        System.out.println("\nYour Purchased Products:\n");

	        String purchaseQuery = "select * from product_purchased where user_id='" + user.id + "'";
	        PreparedStatement psmt = conn.prepareStatement(purchaseQuery);
	        ResultSet rs = psmt.executeQuery();

	        System.out.println("--------------------------------------------------");
	        while (rs.next()) {
	            System.out.println("Product Name        : " + rs.getString("product_name"));
	            System.out.println("Quantity            : " + rs.getInt("quantity"));
	            System.out.println("Total Price         : " + rs.getDouble("total_price"));
	            System.out.println("Ordered Date        : " + rs.getDate("ordered_date"));
	            System.out.println("Delivery Date       : " + rs.getDate("deliver_date"));
	            System.out.println("Delivery Location   : " + user.address);
	            System.out.println("--------------------------------------------------");
	        }
	    } else {
	        System.out.println("\nNo items purchased yet.");
	        System.out.println("Return to main menu to explore products.\n");
	    }
	}

	
	
	
	
	public void insertBankDetails(UserDetails user) throws Exception {
	    System.out.println("\n========================================");
	    System.out.println("         CREATE NEW BANK ACCOUNT        ");
	    System.out.println("========================================");

	    System.out.println("Choose your bank:");
	    System.out.println("1. SBI");
	    System.out.println("2. PNB");
	    System.out.println("3. AXIS");
	    System.out.println("4. ICICI");
	    System.out.println("5. HDFC");
	    System.out.print("Enter your choice (1-5): ");

	    int bankChoice = sc.nextInt();
	    sc.nextLine();

	    String bankName = "";
	    switch (bankChoice) {
	        case 1: bankName = "SBI"; break;
	        case 2: bankName = "PNB"; break;
	        case 3: bankName = "AXIS"; break;
	        case 4: bankName = "ICICI"; break;
	        case 5: bankName = "HDFC"; break;
	        default:
	            System.out.println("\nInvalid bank choice. Please try again.\n");
	            return;
	    }

	    System.out.print("Enter initial balance: ");
	    double balance = sc.nextDouble();
	    sc.nextLine();

	    Random rand = new Random();
	    Integer randAcc = rand.nextInt(10000, 100000);
	    String accountNumber = bankName + randAcc.toString();

	    String createBankQuery = "call insert_bank_details('" + user.id + "', '" + accountNumber + "', '" + balance + "', '" + bankName + "')";
	    PreparedStatement psmt = conn.prepareStatement(createBankQuery);
	    ResultSet rs = psmt.executeQuery();

	    System.out.println("\n----------------------------------------");
	    if (rs.next()) {
	        if (rs.getInt("status") == 1) {
	            System.out.println("Bank account created successfully.");
	            System.out.println("Account Number : " + accountNumber);
	            System.out.println("Bank Name      : " + bankName);
	            System.out.println("Initial Balance: " + balance);
	        } else {
	            System.out.println(rs.getString("msg"));
	        }
	    } else {
	        System.out.println("Something went wrong. Please try again.");
	    }
	    System.out.println("----------------------------------------\n");
	}


	
	
	public void depositMoney(UserDetails user) throws Exception {
	    System.out.println("\n========================================");
	    System.out.println("           MONEY DEPOSIT SECTION        ");
	    System.out.println("========================================");

	    int cnt = 0;
	    String cntBankQuery = "select count(*) as cnt from bank where user_id='" + user.id + "'";
	    PreparedStatement psmtCnt = conn.prepareStatement(cntBankQuery);
	    ResultSet cntRs = psmtCnt.executeQuery();

	    if (cntRs.next()) {
	        cnt = cntRs.getInt("cnt");
	    }

	    if (cnt > 0) {
	        String linkedBanksQuery = "select bank_name from bank where user_id='" + user.id + "'";
	        PreparedStatement psmt = conn.prepareStatement(linkedBanksQuery);
	        ResultSet rs = psmt.executeQuery();

	        List<String> linkedBanks = new ArrayList<>();

	        while (rs.next()) {
	            linkedBanks.add(rs.getString("bank_name"));
	        }

	        System.out.println("\n----------------------------------------");
	        System.out.println("        LINKED BANK ACCOUNTS            ");
	        System.out.println("----------------------------------------");

	        for (int i = 0; i < linkedBanks.size(); i++) {
	            System.out.println((i + 1) + " -> " + linkedBanks.get(i));
	        }

	        System.out.print("Choose a bank (1 to " + linkedBanks.size() + "): ");
	        int bankChoice = sc.nextInt() - 1;

	        if (bankChoice >= 0 && bankChoice < linkedBanks.size()) {
	            System.out.print("Enter amount to deposit: ");
	            double amount = sc.nextDouble();

	            String bankName = linkedBanks.get(bankChoice);
	            String updateBalanceQuery = "call depositeMoney('" + user.id + "','" + bankName + "','" + amount + "')";
	            psmt = conn.prepareStatement(updateBalanceQuery);
	            rs = psmt.executeQuery();

	            System.out.println("\n----------------------------------------");
	            if (rs.next()) {
	                System.out.println(rs.getString("msg"));
	                System.out.println();
	                System.out.println("  Your Curret Balance :  "+ rs.getDouble("balance"));
	            } else {
	                System.out.println("Something went wrong. Please try again.");
	            }
	            System.out.println("----------------------------------------\n");

	        } else {
	            System.out.println("\nInvalid bank choice. Please try again.\n");
	        }

	    } else {
	        System.out.println("\n----------------------------------------");
	        System.out.println("No bank accounts linked to your profile.");
	        System.out.println("Return to main menu to create a new account.");
	        System.out.println("----------------------------------------\n");
	    }
	}

	
	
	public void showProducts() {
	    System.out.println("\n========================================");
	    System.out.println("             PRODUCT CATALOG            ");
	    System.out.println("========================================");

	    if (products.isEmpty()) {
	        System.out.println("No products available at the moment.");
	    } else {
	        for (Product p : products) {
	            System.out.println("\n----------------------------------------");
	            System.out.println("Product ID      : " + p.product_id);
	            System.out.println("Name            : " + p.product_name);
	            System.out.println("Description     : " + p.product_description);
	            System.out.println("Price           : " + p.price);

	            
	            if (p.stock == 0) {
	                System.out.println("Status          : Currently Not Available");
	            } else if (p.stock <= 5) {
	                System.out.println("Status          : Limited Availability");
	            } else {
	                System.out.println("Status          : Available");
	            }
	        }
	        System.out.println("\n----------------------------------------");
	    }

	    System.out.println("========================================\n");
	}




	

}
