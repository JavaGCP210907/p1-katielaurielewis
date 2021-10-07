package com.revature.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.model.ReimbursementStatus;
import com.revature.model.User;
import com.revature.util.HibernateUtil;

public class ReimbursementStatusDao implements ReimbursementStatusDaoInterface {

	@Override
	public void addRS(ReimbursementStatus rs) {
		Session ses = HibernateUtil.getSession();
		
		ses.save(rs); 
		
		HibernateUtil.closeSession();
	}

	@Override
	public void removeRS(ReimbursementStatus rs) {
		Session ses = HibernateUtil.getSession();
		Transaction transRights = ses.beginTransaction();
		ses.delete(rs); 
		transRights.commit();
		HibernateUtil.closeSession();	
	}

	@Override
	public ReimbursementStatus findRSById(int id) {
		
		Session ses = HibernateUtil.getSession();
		
		ReimbursementStatus reimbursementStatus = ses.get(ReimbursementStatus.class, id);
		
		HibernateUtil.closeSession();
		
		return reimbursementStatus;
	}

	@Override
	public List<ReimbursementStatus> findAllRS() {
		Session ses = HibernateUtil.getSession();
		
		List<ReimbursementStatus> statusList = ses.createQuery("FROM ReimbursementStatus").list();
		
		HibernateUtil.closeSession();
		
		return statusList;
	}

}
