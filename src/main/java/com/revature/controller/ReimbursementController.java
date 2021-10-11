package com.revature.controller;

import com.google.gson.Gson;
import com.revature.model.LoginDTO;
import com.revature.model.Reimbursement;
import com.revature.model.ReimbursementDTO;
import com.revature.model.ReimbursementStatus;
import com.revature.model.ReimbursementType;
import com.revature.model.User;
import com.revature.service.ReimbursementService;
import com.revature.service.UserService;
import com.revature.util.JwtUtil;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.hibernate.bytecode.enhance.spi.interceptor.SessionAssociableInterceptor;

import io.javalin.http.Handler;

public class ReimbursementController {

	ReimbursementService rs = new ReimbursementService();
	UserService us = new UserService();
	
		public Handler getAllSubmitted = (ctx) -> {
			
			if(ctx.req.getSession(false) != null) {
			
			List<Reimbursement> allSubmitted = rs.seeAllSubmitted();
			
			Gson gson = new Gson();
			
			String JSONAvengers = gson.toJson(allSubmitted); 
			
			ctx.result(JSONAvengers); 
			
			ctx.status(200); 
			
			} else {
				ctx.status(403); 
			}
			
		};
		
		public Handler submit = (ctx) -> {
			HttpSession session = ctx.req.getSession(false);
			if(session != null) {
				String body = ctx.body();
				String username = (String)session.getAttribute("username");
				
				Gson gson = new Gson();
				
				ReimbursementDTO rDTO = gson.fromJson(body, ReimbursementDTO.class);
				ReimbursementType rType = rs.getTypeById(rDTO.getType());
				// we know the status is 1 = Submitted
				ReimbursementStatus rStatus = rs.getStatusById(1);
				User user = us.getEmployeeByUsername(username);
				Reimbursement r = new Reimbursement(rDTO.getAmount(), rDTO.getDescription(), user, rStatus, rType);
				rs.submit(r);
				
				ctx.status(200);
				
			} else {
				ctx.status(403);
			}
		};
	
	


}
