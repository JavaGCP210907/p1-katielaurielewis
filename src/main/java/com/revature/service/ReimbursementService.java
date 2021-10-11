package com.revature.service;

import java.util.List;

import com.revature.dao.ReimbursementDao;
import com.revature.dao.ReimbursementStatusDao;
import com.revature.dao.ReimbursementTypeDao;
import com.revature.model.Reimbursement;
import com.revature.model.ReimbursementDTO;
import com.revature.model.ReimbursementStatus;
import com.revature.model.ReimbursementType;

public class ReimbursementService {
	
	ReimbursementDao rDao = new ReimbursementDao();
	ReimbursementStatusDao rsDao = new ReimbursementStatusDao();
	ReimbursementTypeDao rtDao = new ReimbursementTypeDao();
	
	public void submit(Reimbursement r) {
		rDao.addReimb(r);
	}
	
	public List<Reimbursement> seeAll(){
		List<Reimbursement> rList = rDao.findAllReimbs();
		return rList;
	}
	
	public List<Reimbursement> seeAllSubmitted(){
		List<Reimbursement> rList = rDao.findAllSubmitted();
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
	
	public ReimbursementType getTypeById(int id) {
		ReimbursementType rt = rtDao.findRTById(id);
		return rt;
	}
	
	public ReimbursementStatus getStatusById(int id) {
		ReimbursementStatus rs = rsDao.findRSById(id);
		return rs;
	}
}
