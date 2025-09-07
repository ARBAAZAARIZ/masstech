package service;

import java.util.List;
import java.util.Random;

import dao.UserDAO;
import model.Users;
import util.EmailUtil;

public class UserService {

	UserDAO userDAO;
	public UserService() {
		this.userDAO=new UserDAO();
	}
	
	
	 public List<Users> getAllUsers(){
		return userDAO.getAllUsers();
	 }
	 
	 public static String generateSecurePassword() {
		    String digits = "0123456789";
		    String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		    String allChars = digits + letters;

		    StringBuilder password = new StringBuilder();
		    Random rand = new Random();

		    for (int i = 0; i < 8; i++) {
		        password.append(allChars.charAt(rand.nextInt(allChars.length())));
		    }

		    return password.toString();
		}
	 
	 public boolean createUserAccount(String fullName, String email, String username, String role, int societyId) {
		    try {
		        // Step 1: Insert member and get member_id
		        int memberId = userDAO.insertMember(fullName, email, societyId);
		        if (memberId <= 0) {
		            return false; // Member insertion failed
		        }

		        // Step 2: Generate secure password
		        String password = generateSecurePassword(); // You can rename this class as needed

		        // Step 3: Insert user
		        int userInserted = userDAO.insertUser(username, password, memberId, role);
		        if (userInserted <= 0) {
		            return false; // User insertion failed
		        }

		        // Step 4: Send email with credentials
		        EmailUtil.sendCredentials(email, username, password);

		        return true;// All steps succeeded
		    } catch (Exception e) {
		        e.printStackTrace();
		        return false;
		    }
		}
	 
	 public Users getUserById(long userId) {
	        return userDAO.fetchUserDetails(userId);
	    }
	 
	 public boolean updateUserDetails(long userId, long memberId, String role, String status, int societyId) {
		    return userDAO.updateUserDetails(userId, memberId, role, status, societyId);
		}
	 
	 
	 public boolean deleteUserByUserId(long userId) {
		    Long memberId = userDAO.getMemberIdByUserId(userId);
		    if (memberId != null) {
		        return userDAO.deleteMemberById(memberId); // cascade will delete user too
		    }
		    return false;
		}
	 
	 public boolean changePassword(Long userId, String oldPassword, String newPassword) {
		 return userDAO.changePassword(userId, oldPassword, newPassword);
	 }
	 
	 public List<Users> getUsersBySocietyId(int societyId){
		 return  userDAO.getUsersBySocietyId(societyId);
	 }
	 
	 public boolean changePasswordForSocietymanager(long userId, String password) {
		 return userDAO.changePasswordForSocietymanager(userId, password);
	 }
	 
	 

}
