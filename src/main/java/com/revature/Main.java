package com.revature;

import com.revature.controller.LoginController;
import com.revature.controller.UserController;
import com.revature.controller.ReimbursementController;
import com.revature.dao.ReimbursementDao;

import io.javalin.Javalin;

public class Main {

		public static void main(String[] args) {
			ReimbursementDao rd = new ReimbursementDao();
			rd.findAllSubmitted().forEach(arg0 -> {
				System.out.println(arg0);
			});
			
			LoginController lc = new LoginController();
			UserController uc = new UserController();
			ReimbursementController rc = new ReimbursementController();
			
			Javalin app = Javalin.create(
					config -> {
						config.enableCorsForAllOrigins(); 
					}
					).start(8090);
			
			app.post("/login", lc.loginHandler);
			app.get("/logout", lc.logoutHandler);
			app.get("/user", uc.getManagerView);
			app.get("/reimbursement/pending", rc.getAllSubmitted);
			app.get("/reimbursement/approved", rc.getAllApproved);
			app.get("/reimbursement/denied", rc.getAllDenied);
			app.post("/reimbursement", rc.submit);
			app.post("/reimbursement/:id/approve", rc.approve);
			app.post("/reimbursement/:id/deny", rc.deny);
		}
}
