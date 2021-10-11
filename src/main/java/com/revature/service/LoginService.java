package com.revature.service;

public class LoginService {

	UserService US = new UserService();
	
	//This is where we will check credentials against the database
	public boolean login(String username, String password) {
		boolean result = US.validatePassword(username, password);
		return result;
	}
}
