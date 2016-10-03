package com.test.model;

public class RelationshipManager extends Role {
	
	private String name;
	private String userId;
	private String password;
	
	public RelationshipManager() {
		super("rm");
	}
	
	public void setPassword(){
		password = userId+name;
	}
	
	public String getPassword(){
		return password;
	}

}
