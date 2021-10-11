package com.revature.service;

import com.revature.dao.UserDao;
import com.revature.dao.UserRoleDao;

import com.revature.model.User;
import com.revature.model.UserRole;

public class UserService {

	UserDao uDao = new UserDao();
	UserRoleDao urDao = new UserRoleDao();
	
	public boolean validatePassword(String username, String password) {
		User u = uDao.findUserByUsername(username);
		if(u == null) {
			return false;
		}
		if(password.equals(u.getPassword())) {
			return true;
		}
		return false;
	}
	
	public void promoteUser(User u) {
		UserRole rl = urDao.findUserRoleByID(1); //1 is manager
		uDao.changeUserRole(u, rl);
	}
	
	public String checkManagerorEmployee(String username) {
		User u = uDao.findUserByUsername(username);
		UserRole ur = u.getRole();
		String role = new String();
		if (ur.getId() == 1) {
			role = "manager";
		} else {
			role = "employee";
		}
		return role;
	}
	
	public boolean checkIfManager(String username) {
		User u = uDao.findUserByUsername(username);
		UserRole ur = u.getRole();
		if (ur.getId() == 1) {
			return true;
		}
		return false;
	}
	
	public User getEmployeeByUsername(String username) {
		User u = uDao.findUserByUsername(username);
		return u;
	}
}
