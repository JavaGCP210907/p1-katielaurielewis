package com.revature.dao;

import java.util.List;

import com.revature.model.Reimbursement;
import com.revature.model.ReimbursementType;

public interface ReimbursementTypeDaoInterface {

	public void addRT(ReimbursementType rt);
	
	public void removeRT(ReimbursementType rt);
	
	public Reimbursement findRTById(int id);
	
	public List<ReimbursementType> findAllRT();
}
