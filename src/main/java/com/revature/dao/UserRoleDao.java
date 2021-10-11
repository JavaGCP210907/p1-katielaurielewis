package com.revature.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.model.User;
import com.revature.model.UserRole;
import com.revature.util.HibernateUtil;

public class UserRoleDao implements UserRoleDaoInterface {

	@Override
	public void addUserRole(UserRole ur) {
		Session ses = HibernateUtil.getSession();
		
		ses.save(ur); 
		
		HibernateUtil.closeSession();
	}

	@Override
	public void removeUserRole(UserRole ur) {
		Session ses = HibernateUtil.getSession();
		Transaction transRights = ses.beginTransaction();
		ses.delete(ur); 
		transRights.commit();
		HibernateUtil.closeSession();	
		
	}

	@Override
	public UserRole findUserRoleByID(int id) {
		
		Session ses = HibernateUtil.getSession();
		
		UserRole userRole = ses.get(UserRole.class, id);
		
		HibernateUtil.closeSession();
		
		return userRole;
	}
	
	@Override
	public UserRole findUserRoleByName(String roleName) {
		Session ses = HibernateUtil.getSession();
		
		UserRole role = (UserRole) ses.createQuery("FROM UserRole WHERE role = \'" + roleName + "\'").uniqueResult();
		
		return role;
	}

	@Override
	public List<UserRole> findAllUserRoles() {
		Session ses = HibernateUtil.getSession();
		
		List<UserRole> roleList = ses.createQuery("FROM UserRole").list();
		
		HibernateUtil.closeSession();
		
		return roleList;
	}

}
