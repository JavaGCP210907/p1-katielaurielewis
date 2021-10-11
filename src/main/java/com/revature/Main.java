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
			app.get("/user", uc.getManagerView);
			app.get("/reimbursement", rc.getAllSubmitted);
			app.post("/reimbursement", rc.submit);
		}
}
