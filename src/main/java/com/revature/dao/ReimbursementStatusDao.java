package com.revature.dao;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.model.ReimbursementStatus;
import com.revature.util.HibernateUtil;

public class ReimbursementStatusDao implements ReimbursementStatusDaoInterface {
	
	Logger log = LogManager.getLogger(ReimbursementStatusDaoInterface.class);

	@Override
	public void addRS(ReimbursementStatus rs) {
		Session ses = HibernateUtil.getSession();
		
		ses.save(rs);
		
		log.info("Saved new Reimbursement Status with id " + rs.getId());
		
		HibernateUtil.closeSession();
	}

	@Override
	public void removeRS(ReimbursementStatus rs) {
		Session ses = HibernateUtil.getSession();
		Transaction transRights = ses.beginTransaction();
		ses.delete(rs);
		log.warn("Deleted Reimbursement Status with id " + rs.getId());
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
