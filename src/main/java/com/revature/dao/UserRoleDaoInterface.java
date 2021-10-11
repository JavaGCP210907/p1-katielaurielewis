package com.revature.dao;

import java.util.List;

import com.revature.model.UserRole;

public interface UserRoleDaoInterface {

	public void addUserRole(UserRole ur);
	
	public void removeUserRole(UserRole ur);
	
	public UserRole findUserRoleByID(int id);
	
	public UserRole findUserRoleByName(String name);
	
	public List<UserRole> findAllUserRoles();
}
