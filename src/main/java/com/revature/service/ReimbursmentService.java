package com.revature.service;

import java.util.List;

import com.revature.dao.ReimbursementDao;
import com.revature.model.Reimbursement;
import com.revature.model.ReimbursementStatus;

public class ReimbursmentService {
	
	ReimbursementDao rDao = new ReimbursementDao();
	Reimbursment
	
	public void submit(Reimbursement r) {
		
	}
	
	public List<Reimbursement> seeAll(){
		List<Reimbursement> rList = rDao.findAllReimbs();
		return rList;
	}
	
	public void approve(Reimbursement r) {
		ReimbursementStatus rs = rs.getId(1); //1 is approval, this seems fine to hardcode
		rDao.changeStatus(r, );
	}

}
