package com.revature.dao;

import java.util.List;

import com.revature.model.ReimbursementStatus;

public interface ReimbursementStatusDaoInterface {
	
	public void addRS(ReimbursementStatus rs);
	
	public void removeRS(ReimbursementStatus rs);
	
	public ReimbursementStatus findRSById(int id);
	
	public List<ReimbursementStatus> findAllRS();

}
