package com.revature.service;

import java.sql.Timestamp;
import java.util.List;

import com.revature.dao.ReimbursementDao;
import com.revature.dao.ReimbursementStatusDao;
import com.revature.dao.ReimbursementTypeDao;
import com.revature.model.Reimbursement;
import com.revature.model.ReimbursementDTO;
import com.revature.model.ReimbursementStatus;
import com.revature.model.ReimbursementType;
import com.revature.model.User;

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
	
	public List<Reimbursement> seeAllApproved(){
		List<Reimbursement> rList = rDao.findAllApproved();
		return rList;
	}
	
	public List<Reimbursement> seeAllDenied(){
		List<Reimbursement> rList = rDao.findAllDenied();
		return rList;
	}
	
	public void approve(Reimbursement r, User resolver) {
		ReimbursementStatus status = rsDao.findRSById(2); //2 is approval, this seems fine to hard code
		rDao.changeStatus(r, status);
		rDao.changeResolver(r, resolver);
		rDao.changeResolvedTime(r,  new Timestamp(System.currentTimeMillis()));
	}

	public void deny(Reimbursement r, User resolver) {
		ReimbursementStatus status = rsDao.findRSById(3); //3 is denial, this still seems fine to hard code
		rDao.changeStatus(r, status);
		rDao.changeResolver(r, resolver);
		rDao.changeResolvedTime(r,  new Timestamp(System.currentTimeMillis()));
	}
	
	public Reimbursement getReimbursementById(int id) {
		Reimbursement r = rDao.findReimbById(id);
		return r;
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
