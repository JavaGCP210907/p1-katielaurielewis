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

public class ReimbursementDao implements ReimbursementDaoInterface {

	@Override
	public void addReimb(Reimbursement r) {
		Session ses = HibernateUtil.getSession();
		
		ses.save(r); 
		
		HibernateUtil.closeSession();
	}

	@Override
	public void removeReimb(Reimbursement r) {
		Session ses = HibernateUtil.getSession();
		Transaction transRights = ses.beginTransaction();
		ses.delete(r); 
		transRights.commit();
		HibernateUtil.closeSession();		
	}

	@Override
	public void changeStatus(Reimbursement r, ReimbursementStatus rs) {
		Session ses = HibernateUtil.getSession();
		Transaction transRights = ses.beginTransaction(); 
		
		String HQL = "UPDATE Reimbursement SET reimbursement_status = '" + rs.getId() + "' WHERE id = " + r.getId();
		
		Query q = ses.createQuery(HQL);
		
		q.executeUpdate();
		
		transRights.commit();
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

}
