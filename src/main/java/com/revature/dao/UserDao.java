package com.revature.dao;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.model.Reimbursement;
import com.revature.model.ReimbursementStatus;
import com.revature.model.ReimbursementType;
import com.revature.model.User;
import com.revature.model.UserRole;
import com.revature.util.HibernateUtil;

public class UserDao implements UserDaoInterface{
	
	public void addUser(User u) {
		Session ses = HibernateUtil.getSession();
		
		ses.save(u); 
		
		HibernateUtil.closeSession();
	}
	

	@Override
	public void removeUser(User u) {
		Session ses = HibernateUtil.getSession();
		Transaction transRights = ses.beginTransaction();
		ses.clear();
		ses.delete(u); 
		transRights.commit();
		HibernateUtil.closeSession();		
	}
	
	@Override
	public User findUserByID(int id) {
		
		Session ses = HibernateUtil.getSession();
		
		User user = ses.get(User.class, id);
		
		HibernateUtil.closeSession();
		
		return user;
	}

	@Override
	public User findUserByUsername(String username) {
		Session ses = HibernateUtil.getSession();
		
		User user = (User) ses.createQuery("FROM User WHERE username = \'" + username + "\'").uniqueResult();
		
		return user;
	}
	
	@Override
	public void changeUserRole(User u, UserRole rl) {
		Session ses = HibernateUtil.getSession();
		Transaction transRights = ses.beginTransaction(); 
		
		String HQL = "UPDATE Users SET role_id = '" + rl.getId() + "' WHERE id = " + u.getId();
		
		Query q = ses.createQuery(HQL);
		
		q.executeUpdate();
		
		transRights.commit();
		HibernateUtil.closeSession();
		
	}

	@Override
	public List<User> findAllUsers() {
		Session ses = HibernateUtil.getSession();
		
		List<User> userList = ses.createQuery("FROM User").list();
		
		HibernateUtil.closeSession();
		
		return userList;
	}

	@Override
	public List<User> findAllManagers() {
		Session ses = HibernateUtil.getSession();
		
		List<User> userList = ses.createQuery("FROM User").list();
		
		HibernateUtil.closeSession();
		
		return userList;
	}


}
