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

import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.hibernate.bytecode.enhance.spi.interceptor.SessionAssociableInterceptor;

import io.javalin.http.Handler;

public class ReimbursementController {

	ReimbursementService rs = new ReimbursementService();
	UserService us = new UserService();
	
		public Handler getAllSubmitted = (ctx) -> {
			HttpSession session = ctx.req.getSession(false);
			
			if(session != null) {
				
				boolean manager = Boolean.parseBoolean(ctx.queryParam("manager"));
				
				List<Reimbursement> allSubmitted;
				
				// if username is passed in, only retrieve reimbursements authored by that user
				if(manager) {
					allSubmitted = rs.seeAllSubmitted();
				} else {
					String username = (String)session.getAttribute("username");
					int id = us.getEmployeeByUsername(username).getId();
					allSubmitted = rs.seeAllSubmittedByUser(id);
				}
			
				Gson gson = new Gson();
			
				String submitted = gson.toJson(allSubmitted); 
			
				ctx.result(submitted); 
			
				ctx.status(200); 
			
			} else {
				ctx.status(403); 
			}
			
		};
		
		public Handler getAllApproved = (ctx) -> {
			HttpSession session = ctx.req.getSession(false);
			if(session != null) {
				
				boolean manager = Boolean.parseBoolean(ctx.queryParam("manager"));
				
				List<Reimbursement> allApproved;
				
				// if username is passed in, only retrieve reimbursements authored by that user
				if(manager) {
					allApproved = rs.seeAllApproved();
				} else {
					String username = (String)session.getAttribute("username");
					int id = us.getEmployeeByUsername(username).getId();
					allApproved = rs.seeAllApprovedByUser(id);
				}
				
				Gson gson = new Gson();
				
				String approved = gson.toJson(allApproved); 
				
				ctx.result(approved); 
				
				ctx.status(200); 
				
			} else {
				ctx.status(403); 
			}
		};
		
		public Handler getAllDenied = (ctx) -> {
			HttpSession session = ctx.req.getSession(false);
			if(session != null) {
				
				boolean manager = Boolean.parseBoolean(ctx.queryParam("manager"));
				
				List<Reimbursement> allDenied;
				
				if(manager) {
					allDenied = rs.seeAllDenied();
				} else {
					String username = (String)session.getAttribute("username");
					int id = us.getEmployeeByUsername(username).getId();
					allDenied = rs.seeAllDeniedByUser(id);
				}
				
				Gson gson = new Gson();
				
				String denied = gson.toJson(allDenied); 
				
				ctx.result(denied); 
				
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
		
		public Handler approve = (ctx) -> {
			HttpSession session = ctx.req.getSession(false);
			if(session != null) {
				int id = Integer.parseInt(ctx.pathParam("id"));
				Reimbursement r = rs.getReimbursementById(id);
				User resolver = us.getEmployeeByUsername((String)session.getAttribute("username"));
				rs.approve(r, resolver);
			}
		};
	
		public Handler deny = (ctx) -> {
			HttpSession session = ctx.req.getSession(false);
			if(session != null) {
				int id = Integer.parseInt(ctx.pathParam("id"));
				Reimbursement r = rs.getReimbursementById(id);
				User resolver = us.getEmployeeByUsername((String)session.getAttribute("username"));
				rs.deny(r, resolver);
			}
		};


}
