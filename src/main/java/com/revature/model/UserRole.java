package com.revature.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_roles")
public class UserRole {

	@Id //This makes this field the Primary Key
	@GeneratedValue(strategy = GenerationType.IDENTITY) //This will make our PK serial
	@Column(name = "role_id")
	private int id;
	@Column(name = "user_role")
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
