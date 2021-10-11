package com.revature.model;

public class ReimbursementDTO {
	private float amount;
	private String description;
	private int type;
	
	
	public ReimbursementDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ReimbursementDTO(float amount, String description, int type) {
		super();
		this.amount = amount;
		this.description = description;
		this.type = type;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
	
}
