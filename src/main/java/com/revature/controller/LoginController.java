package com.revature.controller;

import com.google.gson.Gson;
import com.revature.model.LoginDTO;
import com.revature.service.LoginService;
import com.revature.util.JwtUtil;

import io.javalin.http.Handler;

public class LoginController {
	
	LoginService ls = new LoginService();

	public Handler loginHandler = (ctx) -> {
		
		String body = ctx.body(); 
		
		Gson gson = new Gson();
		
		LoginDTO LDTO = gson.fromJson(body, LoginDTO.class); 

		if(ls.login(LDTO.getUsername(), LDTO.getPassword())) { 
			
			String jwt = JwtUtil.generate(LDTO.getUsername(), LDTO.getPassword());
			
			ctx.req.getSession().setAttribute("username", LDTO.getUsername());; 
			
			ctx.status(200);
			
			ctx.result(jwt);
			
		} else { 
			
			ctx.status(401); 
			ctx.result("Login Failed! :(");
			
		}
		
		
	};

}
