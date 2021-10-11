package com.revature.dao;

import java.sql.Timestamp;
import java.util.List;

import com.revature.model.Reimbursement;
import com.revature.model.ReimbursementStatus;
import com.revature.model.ReimbursementType;
import com.revature.model.User;
import com.revature.model.UserRole;

public interface ReimbursementDaoInterface {
	
	public void addReimb(Reimbursement r);
	
	public void removeReimb(Reimbursement r);
	
	public void changeStatus(Reimbursement r, ReimbursementStatus rs);
	
	public void changeResolver(Reimbursement r, User u);
	
	public void changeResolvedTime(Reimbursement r, Timestamp t);
	
	public Reimbursement findReimbById(int id);
	
	public List<Reimbursement> findAllReimbs();
	
	public List<Reimbursement> findAllSubmitted();
	
	public List<Reimbursement> findAllApproved();
	
	public List<Reimbursement> findAllDenied();
	
	public List<Reimbursement> findAllSubmittedByUser(int id);
	
	public List<Reimbursement> findAllApprovedByUser(int id);
	
	public List<Reimbursement> findAllDeniedByUser(int id);

}
