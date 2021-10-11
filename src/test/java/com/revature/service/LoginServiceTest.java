package com.revature.service;

import com.revature.dao.UserDao;
import com.revature.model.User;
import com.revature.util.HibernateUtil;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class LoginServiceTest {

	static UserDao uDao = new UserDao();
	LoginService ls = new LoginService();
	
	static User u = new User();
	
	@BeforeClass
	public static void setup() {
		u.setUsername("sampleuser");
		u.setPassword("samplepassword");
		uDao.addUser(u);
	}
	
	@AfterClass
	public static void teardown() {
		uDao.removeUser(u);
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
