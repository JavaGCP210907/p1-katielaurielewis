package com.revature.service;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class LoginServiceTest {

	LoginService ls = new LoginService();
	
	@Test
	public void loginTest() {
		assertTrue(ls.login("username", "password"));
	}
}
