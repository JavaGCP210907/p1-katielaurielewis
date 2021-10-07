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
			UserDao uDao = new UserDao();
			UserRoleDao urDao = new UserRoleDao();
			
			User u = new User();
			u.setFirstName("first");
			u.setLastName("last");
			u.setEmail("email@place.com");
			u.setUsername("firstlast");
			u.setPassword("password");
			u.setRole(urDao.getRole("Employee"));
			
			uDao.addUser(u);
		}
}
