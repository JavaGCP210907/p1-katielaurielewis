package com.revature;

import com.revature.dao.UserDao;
import com.revature.dao.UserRoleDao;
import com.revature.dao.ReimbursementDao;
import com.revature.dao.ReimbursementStatusDao;
import com.revature.dao.ReimbursementTypeDao;

import com.revature.model.User;
import com.revature.model.UserRole;
import com.revature.model.Reimbursement;
import com.revature.model.ReimbursementStatus;
import com.revature.model.ReimbursementType;

import java.util.List;

public class Main {

		public static void main(String[] args) {
			ReimbursementDao rd = new ReimbursementDao();
			rd.findAllSubmitted().forEach(arg0 -> {
				System.out.println(arg0);
			});
		}
}
