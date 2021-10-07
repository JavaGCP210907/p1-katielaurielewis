package com.revature.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.model.ReimbursementType;
import com.revature.model.User;
import com.revature.util.HibernateUtil;

public class ReimbursementTypeDao implements ReimbursementTypeDaoInterface{

	@Override
	public void addRT(ReimbursementType rt) {
		Session ses = HibernateUtil.getSession();
		
		ses.save(rt); 
		
		HibernateUtil.closeSession();
	}

	@Override
	public void removeRT(ReimbursementType rt) {
		Session ses = HibernateUtil.getSession();
		Transaction transRights = ses.beginTransaction();
		ses.delete(rt); 
		transRights.commit();
		HibernateUtil.closeSession();	
	}

	@Override
	public ReimbursementType findRTById(int id) {
		
		Session ses = HibernateUtil.getSession();
		
		ReimbursementType reimbursementType = ses.get(ReimbursementType.class, id);
		
		HibernateUtil.closeSession();
		
		return reimbursementType;
	}

	@Override
	public List<ReimbursementType> findAllRT() {
		Session ses = HibernateUtil.getSession();
		
		List<ReimbursementType> typeList = ses.createQuery("FROM ReimbursementType").list();
		
		HibernateUtil.closeSession();
		
		return typeList;
	}

}
