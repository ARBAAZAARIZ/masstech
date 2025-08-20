package mainPackage;

import java.util.Scanner;

import AuthenticationPackage.Authentication;
import AuthenticationPackage.UserDetails;
import adminPackage.AdminPanel;
import userPackage.UserPanel;

public class E_Commerce_Application {

    static int choice;
    static boolean status = true;
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws Exception {

        
        System.out.println("\n==================================================");
        System.out.println("           WELCOME TO BYTECART SYSTEM             ");
        System.out.println("        Making Your Life Easier, One Click        ");
        System.out.println("==================================================\n");

        Authentication auth = new Authentication();
        UserDetails user = new UserDetails();

        
        while (status) {
            System.out.println("Please choose an option:");
            System.out.println("--------------------------------------------------");
            System.out.println("  New User       | Sign Up       | Press 1");
            System.out.println("  Existing User  | Login         | Press 2");
            System.out.println("  Exit                           | Press 3");
            System.out.println("--------------------------------------------------");
            System.out.print("Your choice: ");

            choice = sc.nextInt();

            if (choice == 1) {
                auth.signUp();

            } else if (choice == 2) {
                user = auth.login();

                if (user.authenticated && user.status.equals("ACTIVE")) {
                    status = false;
                } else if (user.authenticated) {
                    System.out.println("You have been BLOCKED. Please Contact Admin");
                }

            } else {
                System.out.println("\nThank you for visiting. Come again!\n");
                status = false;
            }
        

        
        status = true;
        
       


        
        if (user.authenticated && user.status.equals("ACTIVE")) {
            System.out.println("\n--------------------------------------------------");
            System.out.println("Authentication successful. Welcome, " + user.username + "!");
            System.out.println("--------------------------------------------------");
            
            

            if (user.role.equals("USER")) {
                UserPanel userInterface = new UserPanel();
                status = userInterface.userInterface(user);

            } else if (user.role.equals("ADMIN")) {
            	
                AdminPanel adminInterface = new AdminPanel();
               status= adminInterface.adminInterface(user);
            }
        }
     
        System.out.println("\n==================================================");
        System.out.println("     Thanks for choosing BYTECART. See you soon!  ");
        System.out.println("==================================================\n");
        
        Thread.sleep(600);
        
        
        }

        
    }
}
