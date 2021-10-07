package com.revature.dao;

import java.util.List;

import com.revature.model.Reimbursement;
import com.revature.model.ReimbursementStatus;
import com.revature.model.ReimbursementType;
import com.revature.model.User;
import com.revature.model.UserRole;

public interface UserDaoInterface {
	
	public void addUser(User u);
	
	public void removeUser(User u);
	
	public User findUserByID(int id);
	
	public void promoteUser(User u, UserRole rl);
	
	public List<User> findAllUsers();
	
	public List<User> findAllManagers();
	
}
