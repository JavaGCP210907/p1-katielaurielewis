package com.revature.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LoginServiceTest {

	LoginService ls = new LoginService();
	
	@Before
	public void setup() {
		// make fake user here
	}
	
	@After
	public void teardown() {
		// delete fake user here
	}
	
	@Test
	public void testLoginSuccess() {
		assertTrue(ls.login("sampleuser", "samplepassword"));
	}
	
	@Test
	public void testLoginFailureWrongPassword() {
		assertFalse(ls.login("sampleuser", "wrong-password"));
	}
	
	@Test
	public void testLoginFailureUserDoesntExist() {
		assertFalse(ls.login("fakeuser", "a-password"));
	}
}
