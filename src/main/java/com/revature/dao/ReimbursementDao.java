package com.revature.dao;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.model.Reimbursement;
import com.revature.model.ReimbursementStatus;
import com.revature.model.User;
import com.revature.util.HibernateUtil;

public class ReimbursementDao implements ReimbursementDaoInterface {
	
	Logger log = LogManager.getLogger(ReimbursementDao.class);

	@Override
	public void addReimb(Reimbursement r) {
		Session ses = HibernateUtil.getSession();
		
		ses.save(r);
		log.info("Saved new Reimbursement with id " + r.getId());
		
		HibernateUtil.closeSession();
	}

	@Override
	public void removeReimb(Reimbursement r) {
		Session ses = HibernateUtil.getSession();
		Transaction transRights = ses.beginTransaction();
		ses.delete(r); 
		transRights.commit();
		log.warn("Deleted Reimbursement with id " + r.getId());
		HibernateUtil.closeSession();		
	}

	@Override
	public void changeStatus(Reimbursement r, ReimbursementStatus rs) {
		Session ses = HibernateUtil.getSession();
		Transaction transRights = ses.beginTransaction(); 
		
		String HQL = "UPDATE Reimbursement SET status_id = '" + rs.getId() + "' WHERE id = " + r.getId();
		
		Query q = ses.createQuery(HQL);
		
		q.executeUpdate();
		
		transRights.commit();
		log.info("Updated Status of Reimbursement with id " + r.getId() + " to " + rs.getId());
		HibernateUtil.closeSession();
	}
	
	@Override
	public void changeResolver(Reimbursement r, User u) {
		Session ses = HibernateUtil.getSession();
		Transaction transRights = ses.beginTransaction(); 
		
		String HQL = "UPDATE Reimbursement SET reimb_resolver = '" + u.getId() + "' WHERE id = " + r.getId();
		
		Query q = ses.createQuery(HQL);
		
		q.executeUpdate();
		
		transRights.commit();
		log.info("Updated Resolver of Reimbursement with id " + r.getId() + " to " + u.getId());
		HibernateUtil.closeSession();
		
	}
	
	@Override
	public void changeResolvedTime(Reimbursement r, Timestamp t) {
		Session ses = HibernateUtil.getSession();
		Transaction transRights = ses.beginTransaction(); 
		
		String HQL = "UPDATE Reimbursement SET reimb_resolved = '" + t + "' WHERE id = " + r.getId();
		
		Query q = ses.createQuery(HQL);
		
		q.executeUpdate();
		
		transRights.commit();
		log.info("Updated Resolved Time of Reimbursement with id " + r.getId() + " to " + t);
		HibernateUtil.closeSession();
	}

	@Override
	public Reimbursement findReimbById(int id) {
		
		Session ses = HibernateUtil.getSession();
		
		Reimbursement reimbursement = ses.get(Reimbursement.class, id);
		
		HibernateUtil.closeSession();
		
		return reimbursement;
	}

	@Override
	public List<Reimbursement> findAllReimbs() {
		Session ses = HibernateUtil.getSession();
		
		List<Reimbursement> reimbursementList = ses.createQuery("FROM Reimbursement").list();
		
		HibernateUtil.closeSession();
		
		return reimbursementList;
	}

	@Override
	public List<Reimbursement> findAllSubmitted() {
		Session ses = HibernateUtil.getSession();
		
		List<Reimbursement> reimbursementList = ses.createQuery("FROM Reimbursement WHERE status_id = 1").list();
		
		HibernateUtil.closeSession();
		
		return reimbursementList;
	}

	@Override
	public List<Reimbursement> findAllApproved() {
		Session ses = HibernateUtil.getSession();
		
		List<Reimbursement> reimbursementList = ses.createQuery("FROM Reimbursement WHERE status_id = 2").list();
		
		HibernateUtil.closeSession();
		
		return reimbursementList;
	}

	@Override
	public List<Reimbursement> findAllDenied() {
		Session ses = HibernateUtil.getSession();
		
		List<Reimbursement> reimbursementList = ses.createQuery("FROM Reimbursement WHERE status_id = 3").list();
		
		HibernateUtil.closeSession();
		
		return reimbursementList;
	}

	
	

}
