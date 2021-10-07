package com.revature.dao;

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
	
	public Reimbursement findReimbById(int id);
	
	public List<Reimbursement> findAllReimbs();
	
	public List<Reimbursement> findAllSubmitted();

}
