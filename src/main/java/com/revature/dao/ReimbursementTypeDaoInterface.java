package com.revature.dao;

import java.util.List;

import com.revature.model.ReimbursementType;

public interface ReimbursementTypeDaoInterface {

	public void addRT(ReimbursementType rt);
	
	public void removeRT(ReimbursementType rt);
	
	public ReimbursementType findRTById(int id);
	
	public List<ReimbursementType> findAllRT();
}
