package com.revature.model;

public class UserRole {

	private int id;
	private String role;
	
	public UserRole(int id, String role) {
		super();
		this.setId(id);
		this.setRole(role);
	}

	public UserRole() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	
}
