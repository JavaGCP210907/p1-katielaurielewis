package com.revature.service;

import java.util.List;

import com.revature.dao.ReimbursementDao;
import com.revature.dao.ReimbursementStatusDao;
import com.revature.model.Reimbursement;
import com.revature.model.ReimbursementStatus;

public class ReimbursmentService {
	
	ReimbursementDao rDao = new ReimbursementDao();
	ReimbursementStatusDao rsDao = new ReimbursementStatusDao();
	
	public void submit(Reimbursement r) {
		
	}
	
	public List<Reimbursement> seeAll(){
		List<Reimbursement> rList = rDao.findAllReimbs();
		return rList;
	}
	
	public void approve(Reimbursement r) {
		ReimbursementStatus status = rsDao.findRSById(2); //2 is approval, this seems fine to hard code
		rDao.changeStatus(r, status);
	}

	public void deny(Reimbursement r) {
		ReimbursementStatus status = rsDao.findRSById(3); //3 is denial, this still seems fine to hard code
		rDao.changeStatus(r, status);
	}
}
