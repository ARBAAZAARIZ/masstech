package service;

import dao.AuthDAO;

import model.AuthUser;

public class AuthService {
	AuthDAO authDao;
	public AuthService() {
		 this.authDao=new AuthDAO();
	}
	
	public AuthUser login(String username,String password) {
		return this.authDao.authenticate(username, password);
	}

}
