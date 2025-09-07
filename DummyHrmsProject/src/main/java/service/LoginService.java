package service;

import dao.LoginDAO;
import model.Userdetails;

public class LoginService {

    private LoginDAO loginDAO;

    public LoginService() {
        loginDAO = new LoginDAO();
    }

    public Userdetails authenticate(String emailOrUsername, String password) {
        return loginDAO.login(emailOrUsername, password);
    }
}
