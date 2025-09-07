package service;

import dao.ForgotPasswordDAO;

public class ForgotPasswordService {

    private final ForgotPasswordDAO dao = new ForgotPasswordDAO();

    public String getEmailForOtp(String identifier) {
        return dao.resolveEmailByIdentifier(identifier);
    }
    
    public boolean updatePasswordByEmail(String email, String newPassword) {
        ForgotPasswordDAO dao = new ForgotPasswordDAO();
        return dao.updatePassword(email, newPassword);
    }

}
