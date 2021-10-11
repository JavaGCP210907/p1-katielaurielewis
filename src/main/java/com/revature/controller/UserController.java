package com.revature.controller;

import com.google.gson.Gson;
import com.revature.model.LoginDTO;
import com.revature.model.User;
import com.revature.service.UserService;
import com.revature.util.JwtUtil;

import io.javalin.http.Handler;

public class UserController {
	
	UserService us = new UserService();

	public Handler getManagerView = (ctx) -> {
		
		if(ctx.req.getSession(false) != null) {
			
			String username = ctx.queryParam("username");
		
			boolean isManager = us.checkIfManager(username);
		
			Gson gson = new Gson();
		
			String JSONManager = gson.toJson(isManager);
		
			ctx.result(JSONManager);
		
			ctx.status(200);
		
		} else {
			ctx.status(403);
		}
		
	};

}
